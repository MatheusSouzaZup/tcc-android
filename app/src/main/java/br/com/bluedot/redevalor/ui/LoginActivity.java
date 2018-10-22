package br.com.bluedot.redevalor.ui;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.bluedot.redevalor.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        replaceFragment(LoginFragment.newInstance(), LoginFragment.TAG);
    }

    public void replaceFragment(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, tag)
                .commit();
    }
}
