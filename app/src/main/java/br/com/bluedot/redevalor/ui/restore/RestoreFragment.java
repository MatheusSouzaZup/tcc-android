package br.com.bluedot.redevalor.ui.restore;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.bluedot.redevalor.R;
import br.com.bluedot.redevalor.custom.status.Status;
import br.com.bluedot.redevalor.data.model.Task;
import br.com.bluedot.redevalor.ui.BaseFragment;
import br.com.bluedot.redevalor.ui.clipboard.ClipBoardAdapter;
import butterknife.BindView;
import butterknife.OnClick;

public class RestoreFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.btSend)
    Button mBtSend;
    @BindView(R.id.tvEmpty)
    TextView mTvEmpty;

    public static final String TAG = RestoreFragment.class.getSimpleName();

    public static RestoreFragment newInstance() {
        return new RestoreFragment();
    }

    @Override
    public View onCreateViewBinder(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_restore, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RestoreAdapter mAdapter = new RestoreAdapter(getList());
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<Task> getList() {
        List<Task> list = new ArrayList<>();
        Task task = new Task("123456", Status.FINISH);
        Task task2 = new Task("123457", Status.PROGRESS);
        Task task3 = new Task("123458", Status.START);
        list.add(task);
        list.add(task2);
        list.add(task3);
        return list;
    }

    @OnClick(R.id.btSend)
    public void onClickSend() {
        //temporary
        mBtSend.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
        mTvEmpty.setVisibility(View.VISIBLE);
    }
}
