package br.com.bluedot.redevalor.ui.restore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.bluedot.redevalor.R;
import br.com.bluedot.redevalor.ui.BaseFragment;

public class RestoreFragment extends BaseFragment {
    public static final String TAG = RestoreFragment.class.getSimpleName();

    public static RestoreFragment newInstance() {
        return new RestoreFragment();
    }

    @Override
    public View onCreateViewBinder(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_restore, container, false);
    }
}
