package br.com.bluedot.redevalor.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.bluedot.redevalor.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemMenu extends LinearLayout {
    @BindView(R.id.tvTitle)
    TextView mTvTitle;
    @BindView(R.id.ivIcon)
    ImageView mIvIcon;

    String mTitle;
    Drawable mDrawable;

    public ItemMenu(Context context) {
        super(context);
        init(null);
    }

    public ItemMenu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ItemMenu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        View view = inflate(getContext(), R.layout.menu_item, this);
        ButterKnife.bind(this, view);

        if (attrs != null) {
            final TypedArray array = getContext().obtainStyledAttributes(
                    attrs, R.styleable.MenuItem, 0, 0);
            mTitle = array.getString(R.styleable.MenuItem_title);
            mDrawable = array.getDrawable(R.styleable.MenuItem_icon);

            mTvTitle.setText(mTitle != null ? mTitle : "");
            mIvIcon.setImageDrawable(mDrawable);
        }
    }
}
