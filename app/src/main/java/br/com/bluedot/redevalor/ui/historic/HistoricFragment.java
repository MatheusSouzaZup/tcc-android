package br.com.bluedot.redevalor.ui.historic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.bluedot.redevalor.R;
import br.com.bluedot.redevalor.ui.BaseFragment;
import br.com.bluedot.redevalor.ui.MainActivity;
import butterknife.BindView;

public class HistoricFragment extends BaseFragment implements HistoricAdapter.HistoricCallBack {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public static final String TAG = HistoricFragment.class.getSimpleName();

    public static HistoricFragment newInstance() {
        return new HistoricFragment();
    }

    @Override
    public View onCreateViewBinder(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_historic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    public void init() {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(new HistoricAdapter(getMonths(), this));
    }

    public List<String> getMonths() {
        List<String> mMonths = new ArrayList<>();
        mMonths.add("2018");
        return mMonths;
    }

    @Override
    public void onDateClick() {
        ((MainActivity) getActivity()).replaceFragment(HistoricFragmentMonth.newInstance(), HistoricFragmentMonth.TAG);
    }
}
