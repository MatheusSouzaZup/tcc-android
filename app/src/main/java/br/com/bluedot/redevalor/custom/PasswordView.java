package br.com.bluedot.redevalor.custom;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import br.com.bluedot.redevalor.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PasswordView extends RelativeLayout {
    @BindView(R.id.tilPassword)
    TextInputLayout mTilPassword;
    @BindView(R.id.edtPassword)
    EditText mEtPass;
    @BindView(R.id.ivEyePassword)
    ImageView mIvEye;

    boolean mEyeOn;

    public PasswordView(Context context) {
        super(context);
        init();
    }

    public PasswordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.password_view, this);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.ivEyePassword)
    public void onClickEye() {
        if (mEyeOn) {
            mEtPass.setInputType(InputType.TYPE_CLASS_TEXT);
            mIvEye.setImageResource(R.drawable.ic_eye_opened);
            mEyeOn = false;
        } else {
            mEtPass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
            mIvEye.setImageResource(R.drawable.ic_eye_hide);
            mEyeOn = true;
        }
        mEtPass.setSelection(mEtPass.getText().length());
    }

}
