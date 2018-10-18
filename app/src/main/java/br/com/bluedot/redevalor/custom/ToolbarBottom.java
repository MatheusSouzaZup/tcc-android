package br.com.bluedot.redevalor.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import br.com.bluedot.redevalor.R;
import butterknife.ButterKnife;

public class ToolbarBottom extends RelativeLayout {
    public ToolbarBottom(Context context) {
        super(context);
        init();
    }

    public ToolbarBottom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ToolbarBottom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.bottom_toolbar, this);
        ButterKnife.bind(this, view);

    }
}
