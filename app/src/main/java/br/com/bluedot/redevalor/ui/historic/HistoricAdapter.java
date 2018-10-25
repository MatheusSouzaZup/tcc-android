package br.com.bluedot.redevalor.ui.historic;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.bluedot.redevalor.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoricAdapter extends RecyclerView.Adapter<HistoricAdapter.HistoricViewHolder> {
    List<String> mList;
    HistoricCallBack mCallback;

    public HistoricAdapter(List<String> mList, HistoricCallBack historicCallBack) {
        this.mList = mList;
        this.mCallback = historicCallBack;
    }

    @NonNull
    @Override
    public HistoricViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_historic, viewGroup, false);
        return new HistoricViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoricViewHolder historicViewHolder, int i) {
        historicViewHolder.onBind(mList.get(i), mCallback);
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    class HistoricViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvDesc)
        TextView mTvDesc;


        public HistoricViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(String desc, HistoricCallBack mCallBack) {
            mTvDesc.setText(desc);
            itemView.setOnClickListener(v -> mCallBack.onDateClick());
        }
    }

    interface HistoricCallBack {
        void onDateClick();
    }

}
