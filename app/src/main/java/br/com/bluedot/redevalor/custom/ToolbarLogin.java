package br.com.bluedot.redevalor.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import br.com.bluedot.redevalor.R;

public class ToolbarLogin extends RelativeLayout {
    public ToolbarLogin(Context context) {
        super(context);
        init();
    }

    public ToolbarLogin(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ToolbarLogin(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.login_toolbar, this);
    }
}
