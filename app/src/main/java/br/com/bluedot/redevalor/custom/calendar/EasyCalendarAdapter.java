package br.com.bluedot.redevalor.custom.calendar;

import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.bluedot.redevalor.R;
import br.com.bluedot.redevalor.data.model.calendar.Day;
import butterknife.BindView;


public class EasyCalendarAdapter extends RecyclerView.Adapter<EasyCalendarAdapter.CalendarViewHolder> {

    private List<Day> mList;
    private OnCalenderListener mListener;
    private int mColor;

    public EasyCalendarAdapter(List<Day> list, OnCalenderListener listener, int color) {
        mList = list;
        mListener = listener;
        mColor = color;
    }

    @Override
    public CalendarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day_easy_calendar, parent, false);
        CalendarViewHolder holder = new CalendarViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final CalendarViewHolder holder, final int position) {
        Day day = mList.get(position);
        holder.onBind(day, mColor);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class CalendarViewHolder extends RecyclerView.ViewHolder {
        TextView mTvDay;
        ImageView mIvDot;
        Day mDay;

        public CalendarViewHolder(View itemView) {
            super(itemView);
            mTvDay = itemView.findViewById(R.id.calendar_day);
            mIvDot = itemView.findViewById(R.id.calendar_dot);
        }

        public void onBind(final Day day, int color) {

            if (day == null) {
                mTvDay.setVisibility(View.INVISIBLE);
                mIvDot.setVisibility(View.INVISIBLE);
                return;
            }
            mIvDot.setColorFilter(color, PorterDuff.Mode.SRC_IN);
            mDay = day;
            mTvDay.setText(day.getDay());

            if (day.isUsedDay()) {
                setupUsedDay(day);
            }

            if (!day.isEnable()) {
                mTvDay.setAlpha(0.2f);
                mIvDot.setAlpha(0.2f);
            }
        }

        private void setupUsedDay(final Day day) {
            mIvDot.setVisibility(View.VISIBLE);
            mTvDay.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorBlack));
            itemView.setOnClickListener(v -> {
                /*for (Day d : mList){
                    if (d == null || day.equals(d)){
                        continue;
                    }

                    d.setEnable(false);
                }
                notifyDataSetChanged();*/

                if (mListener != null) {
                    mListener.onClickItemDay(v, day);
                }
            });
        }
    }

    public interface OnCalenderListener {
        void onClickItemDay(View view, Day myCalendar);
    }
}