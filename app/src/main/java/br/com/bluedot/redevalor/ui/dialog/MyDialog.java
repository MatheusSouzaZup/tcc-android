package br.com.bluedot.redevalor.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.bluedot.redevalor.MyApplication;
import br.com.bluedot.redevalor.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

/**
 * Created by Murilo on 27/10/2017.
 */

public class MyDialog {

    protected final Builder mBuilder;

    @BindView(R.id.ll_content_popup)
    LinearLayout mLlContentPopup;
    @BindView(R.id.rl_message)
    LinearLayout mRlMessage;
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.tv_top_button)
    TextView mTvTopButton;
    @BindView(R.id.tv_bottom_button)
    TextView mTvBottomButton;
    @BindView(R.id.divider)
    View mDivider;
    @BindView(R.id.view_background)
    View mBackground;

    private float mInitialTranslactionYPos = 0;
    private boolean mIsTranslaction;
    private Drawable mDrawableBackground;

    public MyDialog(Builder builder) {
        mBuilder = builder;
        mBuilder.bottomDialog = inflate(builder);

        if (mBuilder.mBackgroundView != null && !mBuilder.mTranslucentBackgroundView)
            mDrawableBackground = mBuilder.mBackgroundView.getBackground();
    }

    public void show() {
        if (mBuilder != null && mBuilder.bottomDialog != null && !((Activity) mBuilder.context).isFinishing()) {
            mBuilder.bottomDialog.show();
        }
    }

    public void show(long delay) {
        new Handler().postDelayed(() -> {
            try {
                show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }, delay);
    }

    @OnClick(R.id.view_top)
    public void onClickViewTop() {
        if (mBuilder.mCancelable) {
            dismiss();
        }
    }

    public void dismiss() {
        dismiss(null);
    }

    public void dismiss(Runnable endAnimateCallback) {
        if (mBuilder != null && mBuilder.bottomDialog != null) {
            removeScaleOnBackgroundView();
            animateBackground(mBackground, 0);
            animatePopup(mLlContentPopup.getMeasuredHeight(),
                    endAnimateCallback != null ? endAnimateCallback : () -> mBuilder.bottomDialog.dismiss());
        }
    }

    private Dialog inflate(final Builder builder) {
        final Dialog bottomDialog = new Dialog(builder.context, R.style.EasyDialog);
        View view = LayoutInflater.from(builder.context).inflate(R.layout.dialog_easy, null);
        ButterKnife.bind(this, view);

        makeScaleOnBackgroundView();

        mLlContentPopup.post(() -> {
            mLlContentPopup.setTranslationY(mLlContentPopup.getMeasuredHeight());
            animatePopup(-mLlContentPopup.getMeasuredHeight(), null);
        });

        mTvMessage.setText(builder.message);

        if (builder.message == null) {
            mRlMessage.setVisibility(View.GONE);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mLlContentPopup.getLayoutParams();
            params.weight = 0;
            params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            mLlContentPopup.setLayoutParams(params);
        }

        if (builder.messageColor != null)
            mTvMessage.setTextColor(ContextCompat.getColor(builder.context, builder.messageColor));

        if (builder.backgroundColor != null)
            mLlContentPopup.setBackgroundColor(ContextCompat.getColor(builder.context, builder.backgroundColor));

        if (builder.dividerColor != null)
            mDivider.setBackgroundColor(ContextCompat.getColor(builder.context, builder.dividerColor));

        if (builder.bottomButtonColor != null) {
            int color;
            try {
                color = ContextCompat.getColor(builder.context, builder.bottomButtonColor);
            } catch (Resources.NotFoundException e) {
                color = builder.bottomButtonColor;
                e.printStackTrace();
            }

            mTvBottomButton.setTextColor(color);
        }

        if (builder.bottomButton != null) {
            mTvBottomButton.setText(builder.bottomButton);
            mTvBottomButton.setAlpha(builder.bottomButtonAlpha != null ? builder.bottomButtonAlpha : mTvBottomButton.getAlpha());
        } else {
            mDivider.setVisibility(View.GONE);
            mTvBottomButton.setVisibility(View.GONE);
        }

        if (builder.topButton != null)
            mTvTopButton.setText(builder.topButton);

        if (builder.topButtonColor != null) {
            int color;
            try {
                color = ContextCompat.getColor(builder.context, builder.topButtonColor);
            } catch (Resources.NotFoundException e) {
                color = builder.topButtonColor;
                e.printStackTrace();
            }

            mTvTopButton.setTextColor(color);
        }

        mTvMessage.setMovementMethod(new ScrollingMovementMethod());

        if (builder.mOnDismissListener != null) {
            bottomDialog.setOnDismissListener(builder.mOnDismissListener);
        }

        bottomDialog.setCanceledOnTouchOutside(builder.mCancelable);
        bottomDialog.setCancelable(builder.mCancelable);

        animateBackground(mBackground, 1);

        bottomDialog.setContentView(view);
        bottomDialog.setOnKeyListener((dialogInterface, keyCode, keyEvent) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK && builder.mCancelable) {
                dismiss();
                return true;
            }
            return false;
        });
        bottomDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);

        return bottomDialog;
    }

//    private void resizeText() {
//        int maxLenght = Utils.deviceHasNavigationBar(mBuilder.context) ? 200 : 250;
//        float textSize = mTvBottomButton.getVisibility() == View.GONE ?
//                mBuilder.context.getResources().getDimension(R.dimen.sketch_font_16)
//                : mBuilder.context.getResources().getDimension(R.dimen.sketch_font_14);
//
//        if (mBuilder.message.length() > maxLenght) {
//            mTvMessage.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
//        }
//    }

    private void animatePopup(int translationY, Runnable endAnimCallback) {
        mLlContentPopup.animate()
                .translationYBy(translationY)
                .setDuration(300)
                .withEndAction(endAnimCallback)
                .setInterpolator(new LinearInterpolator())
                .start();
    }

    private void animateBackground(View view, float alpha) {
        view.animate()
                .alpha(alpha)
                .setDuration(300)
                .setInterpolator(new LinearInterpolator())
                .start();
    }

    private void makeScaleOnBackgroundView() {
        if (mBuilder.mBackgroundView == null || mBuilder.mScaleView == null)
            return;

        mBuilder.mBackgroundView.setBackgroundResource(mBuilder.mTranslucentBackgroundView ? R.color.colorBlack75 : R.color.colorBlack);

        mBuilder.mScaleView.animate()
                .scaleX(0.95f)
                .scaleY(0.95f)
                .setDuration(220)
                .start();
    }

    private void removeScaleOnBackgroundView() {
        if (mBuilder.mBackgroundView == null || mBuilder.mScaleView == null)
            return;

        mBuilder.mBackgroundView.setBackground(mDrawableBackground);
        if (mBuilder.mBackgroundColorRootView != null) {
            mBuilder.mBackgroundView.setBackgroundColor(mBuilder.mBackgroundColorRootView);
        }

        mBuilder.mScaleView.animate()
                .scaleX(1)
                .scaleY(1)
                .setDuration(220)
                .start();
    }

    @OnClick(R.id.tv_top_button)
    public void onClickTopButton() {
        if (!mBuilder.mAutoDismiss) {
            sendTopButtonCallback();
            return;
        }

        dismiss(() -> {
            if (mBuilder.bottomDialog != null && mBuilder.bottomDialog.isShowing()) {
                mBuilder.bottomDialog.dismiss();
                sendTopButtonCallback();
            }
        });
    }

    private void sendTopButtonCallback() {
        if (mBuilder.onTopButtonListener != null)
            mBuilder.onTopButtonListener.onClickTopButton();
    }

    @OnClick(R.id.tv_bottom_button)
    public void onClickBottomButton() {
        if (!mBuilder.mAutoDismiss) {
            sendBottomButtonCallback();
            return;
        }

        dismiss(() -> {
            mBuilder.bottomDialog.dismiss();
            sendBottomButtonCallback();
        });
    }

    private void sendBottomButtonCallback() {
        if (mBuilder.onBottomButtonListener != null)
            mBuilder.onBottomButtonListener.onClickBottomButton();
    }

    @OnTouch(R.id.ll_content_popup)
    public boolean onTouchHeaderPlans(MotionEvent event) {
        if (mBuilder.mCancelable) {
            int limit = mLlContentPopup.getMeasuredHeight();
            int limitToClosePopup = limit + (limit / 2);
            float translactionY = event.getRawY();
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    mInitialTranslactionYPos = event.getRawY();
                    break;
                case MotionEvent.ACTION_UP:
                    if (translactionY > limitToClosePopup)
                        dismiss();
                    else if (mLlContentPopup.getTranslationY() != 0) {
                        animateBackground(mBackground, 1);
                        animatePopup((int) -mLlContentPopup.getTranslationY(), null);
                    }

                    mIsTranslaction = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (!mIsTranslaction && mInitialTranslactionYPos != 0 && translactionY > mInitialTranslactionYPos) {
                        mIsTranslaction = true;
                    } else if (mIsTranslaction && translactionY > limit) {
                        float alpha = 1 - ((translactionY - mLlContentPopup.getMeasuredHeight()) / mLlContentPopup.getMeasuredHeight());
                        mBackground.setAlpha(alpha > 1 ? 1 : alpha);
                        mLlContentPopup.setTranslationY(translactionY - limit);
                    }
                    break;
            }
        }

        return true;
    }

    public static void showLostSessionDialog() {
        Context context = MyApplication.getInstance().getApplicationContext();
        new MyDialog.Builder(context)
                .setMessage(context.getString(R.string.lost_session_message))
                .setTopButton(context.getString(R.string.ok))
                .setTopButtonColor(R.color.colorRedOrage)
                .setOnTopButtonListener(() -> MyApplication.getInstance().restartAppAndClearAllData())
                .setCancelable(false)
                .build()
                .show();
    }

    public static void showMaintenanceDialog(String message) {
        Context context = MyApplication.getInstance().getApplicationContext();
        new MyDialog.Builder(context)
                .setMessage(message)
                .setTopButton(context.getString(R.string.exit))
                .setTopButtonColor(R.color.colorRedOrage)
                .setOnTopButtonListener(() -> MyApplication.getInstance().exitApp())
                .setCancelable(false)
                .build()
                .show();
    }


    public static class Builder {
        private Context context;

        private Dialog bottomDialog;

        protected String message;

        @ColorRes
        private Integer messageColor;

        private String topButton;

        @ColorRes
        private Integer topButtonColor;

        private String bottomButton;

        @ColorRes
        private Integer bottomButtonColor;

        private Float bottomButtonAlpha;

        @ColorRes
        private Integer backgroundColor;

        @ColorRes
        private Integer dividerColor;

        private OnTopButtonListener onTopButtonListener;

        private OnBottomButtonListener onBottomButtonListener;

        private DialogInterface.OnDismissListener mOnDismissListener;

        private boolean mCancelable = true;

        private View mBackgroundView;

        private View mScaleView;

        private boolean mTranslucentBackgroundView;
        private Integer mBackgroundColorRootView;

        private boolean mAutoDismiss = true;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessageColor(Integer messageColor) {
            this.messageColor = messageColor;
            return this;
        }

        public Builder setTopButton(String topButton) {
            this.topButton = topButton;
            return this;
        }

        public Builder setTopButtonColor(Integer topButtonColor) {
            this.topButtonColor = topButtonColor;
            return this;
        }

        public Builder setBottomButton(String bottomButton) {
            this.bottomButton = bottomButton;
            return this;
        }

        public Builder setBottomButtonColor(Integer bottomButtonColor) {
            this.bottomButtonColor = bottomButtonColor;
            return this;
        }

        public Builder setBottomButtonAlpha(Float bottomButtonAlpha) {
            this.bottomButtonAlpha = bottomButtonAlpha;
            return this;
        }

        public Builder setBackgroundColor(Integer backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder setDividerColor(Integer dividerColor) {
            this.dividerColor = dividerColor;
            return this;
        }

        public Builder setOnTopButtonListener(OnTopButtonListener onTopButtonListener) {
            this.onTopButtonListener = onTopButtonListener;
            return this;
        }

        public Builder setOnBottomButtonListener(OnBottomButtonListener onBottomButtonListener) {
            this.onBottomButtonListener = onBottomButtonListener;
            return this;
        }

        public Builder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            this.mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.mCancelable = cancelable;
            return this;
        }

        public Builder setBackgroundViews(View backgroundView, View scaleView) {
            this.mBackgroundView = backgroundView;
            this.mScaleView = scaleView;
            return this;
        }

        public Builder setTranslucentBackgroundView(boolean translucent) {
            mTranslucentBackgroundView = translucent;
            return this;
        }

        public Builder setBackgroundColorRootView(Integer backgroundColorRootView) {
            mBackgroundColorRootView = backgroundColorRootView;
            return this;
        }

        public Builder setAutoDismiss(boolean autoDismiss) {
            mAutoDismiss = autoDismiss;
            return this;
        }

        public MyDialog build() {
            return new MyDialog(this);
        }

    }

    public interface OnTopButtonListener {
        void onClickTopButton();
    }

    public interface OnBottomButtonListener {
        void onClickBottomButton();
    }
}
