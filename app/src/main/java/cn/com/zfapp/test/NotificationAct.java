package cn.com.zfapp.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.zfapp.R;
import cn.com.zfapp.manager.NotifiManager;

/**
 * Created by zf on 17-2-15.
 */
public class NotificationAct extends AppCompatActivity {

    public static final int NOTIFICATION_ID = 1;

    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_notification);
        ButterKnife.bind(this);
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, NotificationAct.class);
        return intent;
    }

    @OnClick(R.id.button)
    public void onClick() {
        NotifiManager.sendNotification(this, NOTIFICATION_ID);
    }
}
