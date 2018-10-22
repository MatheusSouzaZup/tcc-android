package br.com.bluedot.redevalor.data.retrofit;

import android.os.Build;
import android.util.Base64;
import android.util.Log;

import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

import br.com.bluedot.redevalor.BuildConfig;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.TlsVersion;
import okhttp3.internal.platform.Platform;

/**
 * Created by gadal on 27/10/17.
 */

public class RetrofitSetup {

    public static final String TAG = RetrofitSetup.class.getSimpleName();

    public static final String PLATFORM = "ANDROID";

    public static final String UID = "x-uid";
    public static final String IP_CONFIG = "ip_config";
    public static final String AUTHORIZATION = "authorization";
    public static final String ACCESS_TOKEN = "x-access-token";

    public static final String APP_CREDENTIAL = "X-Application-Key";

    public static final String X_ORGANIZATION = "X-Organization-Slug";
    public static final String ORGANIZATION = "vivo";

    private static final String X_APPLICATION_ID = "X-Application-Id";
    private static final String X_APPLICATION_VALUE = "";
    public static final String X_APPLICATION_PUSH_VALUE = "";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String USER_AGENT = "User-Agent";
    public static final String X_APP_OS = "x-app-os";
    public static final String X_APP_DEVICE = "x-app-device";
    public static final String X_APP_VERSION = "x-app-version";
    public static final String X_EASY_REQUEST_ID = "x-easy-request-id";
    private static final int TIME_OUT = 300;


    public static final OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient().newBuilder();
        okHttpClient.addInterceptor(requestIntercept);
        okHttpClient.addInterceptor(responseIntercept);
        okHttpClient.addInterceptor(getLoggingInterceptor());
        okHttpClient.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClient.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        //okHttpClient.authenticator(new VivoEasyAuthenticator());
        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
                .tlsVersions(TlsVersion.TLS_1_0)
                .cipherSuites(
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
                .allEnabledTlsVersions()
                .supportsTlsExtensions(false)
                .allEnabledCipherSuites()
                .build();

        /*if (!BuildConfig.FLAVOR.equals("mock")) {
            okHttpClient.connectionSpecs(Collections.singletonList(spec));
        }*/

        return okHttpClient.build();
    }

    private static LoggingInterceptor getLoggingInterceptor() {
        return new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("REQUEST")
                .response("RESPONSE")
                .build();
    }

    private static final Interceptor requestIntercept = chain -> {
        final Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder();
        addFixedHeaders(requestBuilder);
        addDinamicHeaders(requestBuilder);

        return chain.proceed(requestBuilder.build());
    };

    public static final Interceptor responseIntercept = chain -> {
        final Request request = chain.request();
        final Response response = chain.proceed(request);
        final Headers headers = response.headers();

        setupUID(headers);
        setupAccessToken(headers);


        return response;
    };

    private static void addFixedHeaders(Request.Builder requestBuilder) {
        requestBuilder.addHeader(X_ORGANIZATION, ORGANIZATION);
        requestBuilder.addHeader(CONTENT_TYPE, "application/json; charset=utf-8");
        requestBuilder.addHeader(USER_AGENT, PLATFORM);
        requestBuilder.addHeader(X_APP_OS, PLATFORM);
        requestBuilder.addHeader(X_APP_DEVICE, Build.MODEL);
    }



    private static void addDinamicHeaders(Request.Builder requestBuilder) {
        if (!isEmpty(Environment.nextToken)) {
            requestBuilder.addHeader(AUTHORIZATION, Environment.buildToken());

            if (Environment.uid != null)
                requestBuilder.addHeader(UID, Environment.uid);
        }
    }


    private static void setupUID(Headers headers) {
        if (!isEmpty(headers.get(UID))) {
            Environment.uid = headers.get(UID);
        }
    }

    private static void setupAccessToken(Headers headers) {
        if (!isEmpty(headers.get(ACCESS_TOKEN))) {
            Environment.currentToken = Environment.nextToken;
            Environment.nextToken = headers.get(ACCESS_TOKEN);
        }
    }

    private static boolean isEmpty(String str) {
        if (str == null) return true;
        return str.trim().length() == 0;
    }

    public static class Environment {
        public static String uid;
        public static String currentToken;
        public static String nextToken;

        public static final String buildToken() {
            if (isEmpty(nextToken)) {
                Log.d(TAG, "Empty X_UID or x-access-token");
                return "";
            } else {
                try {
                    final String authToken = uid + ":" + nextToken;
                    final byte[] data = authToken.getBytes("UTF-8");
                    String authorization = "Bearer " + Base64.encodeToString(data, Base64.DEFAULT);
                    authorization = authorization.replace("\n", "");
                    authorization = authorization.replace("\r", "");
                    return authorization;
                } catch (UnsupportedEncodingException e) {
                    Log.d(TAG, "Unsupported Encoding Exception");
                    return "";
                }
            }
        }
    }

}
