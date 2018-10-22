package br.com.bluedot.redevalor;

import android.app.Application;
import android.content.Intent;

import br.com.bluedot.redevalor.ui.MainActivity;
import br.com.bluedot.redevalor.utils.AuthPreferences;
import br.com.bluedot.redevalor.utils.Preferences;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MyApplication extends Application {
    public static MyApplication mInstance;

    public static MyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeCalligraphy();
        mInstance = this;
    }

    private void initializeCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/sansation_regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public void restartAppAndClearAllData() {
        AuthPreferences.logout();
        Preferences.clear();
        restartApp();
    }

    public void restartApp() {

        Intent i = getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    public void exitApp() {
        ((MainActivity) getApplicationContext()).finishAffinity();
    }
}
