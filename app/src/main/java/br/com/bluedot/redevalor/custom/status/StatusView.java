package br.com.bluedot.redevalor.custom.status;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import br.com.bluedot.redevalor.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StatusView extends RelativeLayout {
    @BindView(R.id.tvStatus)
    TextView mTvStatus;
    @BindView(R.id.ivStatus)
    ImageView mIvStatus;

    public StatusView(Context context) {
        super(context);
        init();
    }

    public StatusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.status_view, this);
        ButterKnife.bind(this, view);
    }

    public void updateStatus(Status status) {
        mIvStatus.setImageResource(status.getColor());
        mTvStatus.setText(status.getStatus());
    }
}
