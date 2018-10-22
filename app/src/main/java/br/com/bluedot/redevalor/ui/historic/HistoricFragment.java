package br.com.bluedot.redevalor.ui.historic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.bluedot.redevalor.R;
import br.com.bluedot.redevalor.ui.BaseFragment;

public class HistoricFragment extends BaseFragment {
    public static final String TAG = HistoricFragment.class.getSimpleName();
    public static HistoricFragment newInstance() {
        return new HistoricFragment();
    }

    @Override
    public View onCreateViewBinder(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_historic, container, false);
    }
}
