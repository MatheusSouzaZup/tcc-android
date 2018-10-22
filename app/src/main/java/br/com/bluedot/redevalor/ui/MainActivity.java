package br.com.bluedot.redevalor.ui;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.bluedot.redevalor.R;
import br.com.bluedot.redevalor.custom.ToolbarBottom;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btToolbar)
    ToolbarBottom mToolbarBottom;
    @BindView(R.id.tvTitle)
    TextView mTvTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        mToolbarBottom.setActivity(this);
        mToolbarBottom.onCLickClipBoard();
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    private void addFragment(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment, tag)
                .commit();
    }

    public void replaceFragment(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, tag)
                .commit();
    }

}
