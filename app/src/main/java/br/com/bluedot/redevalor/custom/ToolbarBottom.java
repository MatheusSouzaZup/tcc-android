package br.com.bluedot.redevalor.custom;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.bluedot.redevalor.R;
import br.com.bluedot.redevalor.ui.clipboard.ClipBoardFragment;
import br.com.bluedot.redevalor.ui.historic.HistoricFragment;
import br.com.bluedot.redevalor.ui.MainActivity;
import br.com.bluedot.redevalor.ui.restore.RestoreFragment;
import br.com.bluedot.redevalor.ui.user.UserFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToolbarBottom extends RelativeLayout {
    @BindView(R.id.menuClipBoard)
    ItemMenu mMenuClipBoard;
    @BindView(R.id.menuRestore)
    ItemMenu mMenuRestore;
    @BindView(R.id.menuHistoric)
    ItemMenu mMenuHistoric;
    @BindView(R.id.menuUser)
    ItemMenu mMenuUser;

    List<ItemMenu> menuList;
    MainActivity mActivity;

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
        menuList = getMenuList();
    }

    public void setActivity(MainActivity activity) {
        mActivity = activity;
    }

    @OnClick(R.id.menuClipBoard)
    public void onCLickClipBoard() {
        menuSelected(mMenuClipBoard);
        mActivity.replaceFragment(ClipBoardFragment.newInstance(), ClipBoardFragment.TAG);
        mActivity.setTitle(getContext().getString(R.string.title_tasks));
    }

    @OnClick(R.id.menuRestore)
    public void onClickRestore() {
        menuSelected(mMenuRestore);
        mActivity.replaceFragment(RestoreFragment.newInstance(), RestoreFragment.TAG);
        mActivity.setTitle(getContext().getString(R.string.title_restore));
    }

    @OnClick(R.id.menuHistoric)
    public void onClickHistoric() {
        menuSelected(mMenuHistoric);
        mActivity.replaceFragment(HistoricFragment.newInstance(), HistoricFragment.TAG);
        mActivity.setTitle(getContext().getString(R.string.title_historic));
    }

    @OnClick(R.id.menuUser)
    public void onClickUser() {
        menuSelected(mMenuUser);
        mActivity.replaceFragment(UserFragment.newInstance(), UserFragment.TAG);
        mActivity.setTitle(getContext().getString(R.string.title_user));
    }

    private void menuSelected(ItemMenu itemMenu) {
        for (ItemMenu i : menuList) {
            if (i.getId() == itemMenu.getId()) {
                itemMenu.changeColor(ContextCompat.getColor(getContext(), R.color.colorRed), true);
            } else {
                i.changeColor(ContextCompat.getColor(getContext(), R.color.colorGray), false);
            }
        }
    }

    private List<ItemMenu> getMenuList() {
        List<ItemMenu> list = new ArrayList<>();
        list.add(mMenuClipBoard);
        list.add(mMenuHistoric);
        list.add(mMenuRestore);
        list.add(mMenuUser);
        return list;
    }


}
