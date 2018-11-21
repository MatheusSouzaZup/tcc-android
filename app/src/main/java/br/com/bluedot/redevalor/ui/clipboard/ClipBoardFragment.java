package br.com.bluedot.redevalor.ui.clipboard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackView;

import java.util.ArrayList;
import java.util.List;

import br.com.bluedot.redevalor.R;
import br.com.bluedot.redevalor.custom.status.Status;
import br.com.bluedot.redevalor.custom.status.StatusView;
import br.com.bluedot.redevalor.data.ResponseError;
import br.com.bluedot.redevalor.data.model.Task;
import br.com.bluedot.redevalor.ui.BaseFragment;
import br.com.bluedot.redevalor.ui.MainActivity;
import butterknife.BindView;

public class ClipBoardFragment extends BaseFragment implements ClipBoardContract.View {
    @BindView(R.id.cardStack)
    CardStackView mCardStack;


    public static final String TAG = ClipBoardFragment.class.getSimpleName();

    public static ClipBoardFragment newInstance() {
        return new ClipBoardFragment();
    }

    @Override
    public View onCreateViewBinder(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_clipboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ClipBoardAdapter mAdapter = new ClipBoardAdapter(getList());
        CardStackLayoutManager manager = new CardStackLayoutManager(getContext());
        mCardStack.setAdapter(mAdapter);
        mCardStack.setLayoutManager(manager);
        mCardStack.swipe();
    }


    @Override
    public void showTasks() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading(boolean success) {

    }

    @Override
    public void notifyError(ResponseError error) {

    }

    @Override
    public void showDialogUpdateApplication(ResponseError error) {

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
}
