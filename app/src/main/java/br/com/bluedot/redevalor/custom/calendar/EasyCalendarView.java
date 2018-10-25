package br.com.bluedot.redevalor.custom.calendar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.bluedot.redevalor.R;
import br.com.bluedot.redevalor.data.model.calendar.Day;
import br.com.bluedot.redevalor.data.model.calendar.MyCalendar;


/**
 * Created by gadal on 12/12/17.
 */

public class EasyCalendarView extends RelativeLayout implements EasyCalendarAdapter.OnCalenderListener {

    private GregorianCalendar mDate;

    RecyclerView mRecyclerView;
    View mIvBall;
    View mViewWhite;
    ImageView mIvCircle;

    private float posYBall;
    private float initScale = 0.5f;
    private int marginTop = 100;
    private float marginTopPx = 0;
    private int timeAnimation;
    private DayItemCallBack mDayItemCallBack;
    private List<String> mDaysKey;
    private String mDayUsed;
    private String mTimeExpend;
    private int mColor;

    public EasyCalendarView(Context context) {
        super(context);
        mDate = new GregorianCalendar(2018, 2 - 1, 1);
        init();
    }

    public EasyCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDate = new GregorianCalendar(2018, 2 - 1, 1);
        init();
    }


    private void init() {
        View layout = inflate(getContext(), R.layout.easy_calendar_view, this);

        mRecyclerView = layout.findViewById(R.id.calendar);
        mIvBall = layout.findViewById(R.id.ball);
        mViewWhite = layout.findViewById(R.id.v_white);
        mIvCircle = layout.findViewById(R.id.iv_circle_calendar);

        marginTopPx = dpToPx(marginTop);

        EasyCalendarAdapter mAdapter =
                new EasyCalendarAdapter(getMonthConfigurated(), this, mColor);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 7);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        setmViewWhiteClickListner();

        setBallColor(mColor);
    }

    private void setmViewWhiteClickListner() {
        mViewWhite.setOnClickListener((v) -> {
            mIvBall.animate()
                    .y(posYBall)
                    .alpha(0f)
                    .scaleX(initScale)
                    .scaleY(initScale)
                    .setDuration(timeAnimation)
                    .setInterpolator(new AnticipateOvershootInterpolator())
                    .withEndAction(() -> mIvBall.setVisibility(View.INVISIBLE))
                    .start();

            mViewWhite.animate()
                    .alpha(0f)
                    .setDuration(200)
                    .withEndAction(() -> {
                        mViewWhite.setVisibility(View.INVISIBLE);
                        mViewWhite.setClickable(false);
                    })
                    .start();
        });
    }

    public int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    @Override
    public void onClickItemDay(View view, Day myCalendar) {
//        if (mDayItemCallBack != null) {
//            setValuesForExtract(myCalendar.getConsuptionDayKey());
//            mDayItemCallBack.dayClicked(mDayUsed, mRegisters, mTimeExpend);
//        }
    }

    private void showBallAnimation(View view) {
        timeAnimation = 650;
        posYBall = marginTopPx + view.getY() - dpToPx(20);
        float x = view.getX() - dpToPx(10);
        if (x < 0) x = 0;

        animationIn(x);

        mViewWhite.setVisibility(View.VISIBLE);
        mViewWhite.setClickable(true);
    }

    private void animationIn(float x) {
        mIvBall.setX(x);
        mIvBall.setY(posYBall + dpToPx(40));
        mIvBall.setAlpha(0f);
        mIvBall.setScaleY(initScale);
        mIvBall.setScaleX(initScale);

        mIvBall.setVisibility(View.VISIBLE);
        mIvBall.animate()
                .y(posYBall - dpToPx(40))
                .alpha(1)
                .scaleX(1)
                .scaleY(1)
                .setDuration(timeAnimation)
                .setInterpolator(new OvershootInterpolator())
                .start();

        mViewWhite.setAlpha(0f);
        mViewWhite.animate()
                .alpha(0.82f)
                .setDuration(200)
                .start();
    }

    public void setBallColor(int color) {
        mIvCircle.setColorFilter(color);
    }

    private List<Day> getMonthConfigurated() {
        MyCalendar myCalendar = setUpValidyDate();
        List<Day> listDay = new ArrayList<>();
        fillWithEmptyDay(myCalendar, listDay);
        createUsefulDays(myCalendar, listDay);
        return listDay;
    }

    private MyCalendar setUpValidyDate() {
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.setFirstDay(mDate.get(Calendar.DAY_OF_WEEK));
        myCalendar.setMonthSize(mDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        myCalendar.setUsedDays(getUsedDaysDefault());
        return myCalendar;
    }

    private void createUsefulDays(MyCalendar myCalendar, List<Day> mList) {
        int cont = 0;
        for (int i = 1; i <= myCalendar.getMonthSize(); i++) {
            cont = setupUsefulDays(myCalendar, mList, cont, i);
        }
    }

    private int setupUsefulDays(MyCalendar myCalendar, List<Day> mList, int cont, int i) {
        boolean isUsedDay = myCalendar.getUsedDays().contains(i);
        if (isUsedDay) {
            mList.add(new Day(i + "", isUsedDay, true, mDaysKey != null ? mDaysKey.get(cont) : null));
            cont++;
        } else {
            mList.add(new Day(i + "", isUsedDay, true, ""));
        }
        return cont;
    }

    private void fillWithEmptyDay(MyCalendar myCalendar, List<Day> mList) {
        for (int i = 0; i < myCalendar.getFirstDay() - 1; i++) {
            mList.add(null);
        }
    }

    private List<Integer> getUsedDaysDefault() {
        List<Integer> mList = new ArrayList<>();
        mList.add(1);
        mList.add(4);
        mList.add(10);
        mList.add(13);
        mList.add(21);
        return mList;
    }


    private Integer convertDay(String day) {
        String[] days = day.split("-");
        return Integer.valueOf(days[0]);
    }

    public interface DayItemCallBack {
//        void dayClicked(String day, List<Register> mRegisters, String TimeExpend);
    }

}
