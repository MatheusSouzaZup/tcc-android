package br.com.bluedot.redevalor.ui.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.bluedot.redevalor.R;
import br.com.bluedot.redevalor.ui.BaseFragment;

public class UserFragment extends BaseFragment {
    public static final String TAG = UserFragment.class.getSimpleName();

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public View onCreateViewBinder(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }
}
