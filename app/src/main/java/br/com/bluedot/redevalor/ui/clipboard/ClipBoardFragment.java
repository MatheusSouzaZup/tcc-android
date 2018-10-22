package br.com.bluedot.redevalor.ui.clipboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.bluedot.redevalor.R;
import br.com.bluedot.redevalor.ui.BaseFragment;

public class ClipBoardFragment extends BaseFragment {
    public static final String TAG = ClipBoardFragment.class.getSimpleName();

    public static ClipBoardFragment newInstance() {
        return new ClipBoardFragment();
    }

    @Override
    public View onCreateViewBinder(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_clipboard, container, false);
    }


}
