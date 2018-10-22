package br.com.bluedot.redevalor.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.bluedot.redevalor.R;

public class RegisterFragment extends BaseFragment {

    public static final String TAG = RegisterFragment.class.getSimpleName();

    public static RegisterFragment newInstance() {

        return new RegisterFragment();
    }

    @Override
    public View onCreateViewBinder(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
}
