package br.com.bluedot.redevalor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.bluedot.redevalor.R;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment {
    public static final String TAG = LoginFragment.class.getSimpleName();

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateViewBinder(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @OnClick(R.id.btLogin)
    public void onClickLogin() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tvRegister)
    public void onClickRegister() {
        ((LoginActivity )getActivity()).replaceFragment(RegisterFragment.newInstance(), RegisterFragment.TAG);
    }

}
