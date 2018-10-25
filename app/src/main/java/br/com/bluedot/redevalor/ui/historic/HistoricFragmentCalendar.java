package br.com.bluedot.redevalor.ui.historic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import br.com.bluedot.redevalor.R;
import br.com.bluedot.redevalor.custom.calendar.EasyCalendarView;
import br.com.bluedot.redevalor.ui.BaseFragment;
import butterknife.BindView;

public class HistoricFragmentCalendar extends BaseFragment {

    @BindView(R.id.root)
    RelativeLayout mRoot;
    @BindView(R.id.tvMonth)
    TextView mTvTitle;

    public static final String TAG = HistoricFragmentCalendar.class.getSimpleName();

    public static HistoricFragmentCalendar newInstance() {
        return new HistoricFragmentCalendar();
    }

    @Override
    public View onCreateViewBinder(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_historic_calendar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTvTitle.setText("Mes");
        EasyCalendarView mCalendarView = new EasyCalendarView(getContext());
        mRoot.addView(mCalendarView);
    }
}
