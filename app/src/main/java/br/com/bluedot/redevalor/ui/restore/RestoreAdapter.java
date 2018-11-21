package br.com.bluedot.redevalor.ui.restore;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.bluedot.redevalor.R;
import br.com.bluedot.redevalor.custom.status.StatusView;
import br.com.bluedot.redevalor.data.model.Task;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RestoreAdapter extends RecyclerView.Adapter<RestoreAdapter.ClipBoardViewHolder> {

    private List<Task> mList;

    public RestoreAdapter(List<Task> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ClipBoardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.clipboard_item, viewGroup, false);
        return new ClipBoardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClipBoardViewHolder clipBoardViewHolder, int i) {
        clipBoardViewHolder.onBind(mList.get(i));
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    class ClipBoardViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.status)
        StatusView mStatusView;
        @BindView(R.id.ivId)
        TextView mTvStatus;

        public ClipBoardViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void onBind(Task task) {
            mStatusView.updateStatus(task.getStatus());
            mTvStatus.setText(task.getId());
        }
    }
}
