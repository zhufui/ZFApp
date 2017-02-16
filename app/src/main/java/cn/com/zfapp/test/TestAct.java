package cn.com.zfapp.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.zfapp.R;

/**
 * Created by zf on 17-2-15.
 */
public class TestAct extends AppCompatActivity {

    @BindView(R.id.bt_logtutils)
    Button btLogtutils;
    @BindView(R.id.bt_notification)
    Button btNotification;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_logtutils, R.id.bt_notification})
    public void onClick(View view) {
        switch (view.getId()) {
            //test logutils
            case R.id.bt_logtutils:
                startActivity(LogUtilAct.newIntent(this));
                break;
            //test notification
            case R.id.bt_notification:
                startActivity(NotificationAct.newIntent(this));
                break;
        }
    }
}
