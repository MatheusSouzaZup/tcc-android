package br.com.bluedot.redevalor.ui.historic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.bluedot.redevalor.R;
import br.com.bluedot.redevalor.ui.BaseFragment;
import br.com.bluedot.redevalor.ui.MainActivity;
import butterknife.BindView;

public class HistoricFragmentMonth extends BaseFragment implements HistoricAdapter.HistoricCallBack {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    public static final String TAG = HistoricFragmentMonth.class.getSimpleName();

    public static HistoricFragmentMonth newInstance() {
        return new HistoricFragmentMonth();
    }

    @Override
    public View onCreateViewBinder(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_historic_month, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setAdapter(new HistoricAdapter(getList(),this));
        mRecyclerView.setLayoutManager(llm);
    }

    private List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("Janeiro");
        list.add("Fevereiro");
        return list;
    }

    @Override
    public void onDateClick() {
        ((MainActivity) getActivity()).replaceFragment(HistoricFragmentCalendar.newInstance(), HistoricFragmentCalendar.TAG);
    }
}
