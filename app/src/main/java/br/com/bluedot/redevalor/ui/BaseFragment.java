package br.com.bluedot.redevalor.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Luiz on 27/09/17.
 */

public abstract class BaseFragment extends Fragment implements FragmentViewBinder {

    protected Unbinder mBinder;

    public BaseFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = onCreateViewBinder(inflater, container, savedInstanceState);
        mBinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinder.unbind();
    }

    public String getTAG() {
        return getClass().getSimpleName();
    }

    protected Animation enterAnimation(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    protected Animation exitAnimation(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return enter ? enterAnimation(transit, enter, nextAnim) : exitAnimation(transit, enter, nextAnim);
    }

}
