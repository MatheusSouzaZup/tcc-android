package br.com.bluedot.redevalor.utils;

import br.com.bluedot.redevalor.data.model.AuthCredentials;
import br.com.bluedot.redevalor.data.model.AuthResponse;


/**
 * Created by Murilo on 06/12/2017.
 */

public class AuthPreferences {

    private static final String AUTH_CREDENTIALS_KEY = "AUTH_CREDENTIALS_KEY";
    private static final String AUTH_RESPONSE_KEY = "AUTH_RESPONSE_KEY";

    private static final String FINGERPRINT_KEY = "FINGERPRINT_KEY";

    private static AuthCredentials mAuthCredentials;
    private static AuthResponse mAuthResponse;

    public static void login(AuthCredentials authCredentials, AuthResponse authResponse) {
        mAuthCredentials = authCredentials;
        mAuthResponse = authResponse;
        Preferences.saveObject(mAuthCredentials, AUTH_CREDENTIALS_KEY);
        Preferences.saveObject(mAuthResponse, AUTH_RESPONSE_KEY);
    }

    public static void login(AuthCredentials authCredentials) {
        mAuthCredentials = authCredentials;
        Preferences.saveObject(mAuthCredentials, AUTH_CREDENTIALS_KEY);
    }

    public static void changePassword(String password) {
        AuthCredentials credentials = restore();
        credentials.setPassword(password);
        mAuthCredentials = credentials;
        Preferences.saveObject(mAuthCredentials, AUTH_CREDENTIALS_KEY);
    }

    public static AuthCredentials restore() {
        if (mAuthCredentials == null)
            mAuthCredentials = (AuthCredentials) Preferences.restoreObject(AUTH_CREDENTIALS_KEY, AuthCredentials.class);

        return mAuthCredentials;
    }

    public static AuthResponse restoreResponse() {
        if (mAuthResponse == null)
            mAuthResponse = (AuthResponse) Preferences.restoreObject(AUTH_RESPONSE_KEY, AuthResponse.class);

        return mAuthResponse;
    }

    public static void logout() {
        mAuthCredentials = null;
        mAuthResponse = null;
        Preferences.removeObject(AUTH_CREDENTIALS_KEY);
        Preferences.removeObject(AUTH_RESPONSE_KEY);
    }

    public static boolean isLogged() {
        return (mAuthCredentials != null && mAuthResponse != null)
                || (Preferences.containsObject(AUTH_CREDENTIALS_KEY, AuthCredentials.class)
                && Preferences.containsObject(AUTH_RESPONSE_KEY, AuthResponse.class));
    }

    public static boolean isLogged(String number) {
        return isLogged() && restore().getNumber().equals(number);
    }

    public static void enableFingerprintAuth(String number) {
        saveFingerprintAuth(true, number);
    }

    public static void enableFingerprintAuth() {
        if (isLogged())
            enableFingerprintAuth(restore().getNumber());
    }

    public static void disableFingerprintAuth(String number) {
        saveFingerprintAuth(false, number);
    }

    public static void disableFingerprintAuth() {
        if (isLogged())
            disableFingerprintAuth(restore().getNumber());
    }

    public static boolean isFingerprintAuthEnable(String number) {
        return Preferences.restoreBoolean(FINGERPRINT_KEY + number);
    }

    public static boolean isFingerprintAuthEnable() {
        if (!isLogged())
            return false;

        return isFingerprintAuthEnable(restore().getNumber());
    }

    private static void saveFingerprintAuth(boolean enable, String number) {
        Preferences.saveBoolean(enable, FINGERPRINT_KEY + number);
    }

    public static String getPushToken() {
        return Preferences.restoreString(Preferences.PUSH_TOKEN);
    }
}
