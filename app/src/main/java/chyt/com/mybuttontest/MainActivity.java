package chyt.com.mybuttontest;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button;
    private Handler handler;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.selBtn);
        button.setOnClickListener(this);

//        handler = new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//
//                switch (msg.what) {
//                    case 0:
//                        Toast.makeText(getApplicationContext(), "发起群聊", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 1:
//                        Toast.makeText(getApplicationContext(), "添加好友", Toast.LENGTH_LONG).show();
//                        break;
//                    default:
//                        break;
//                }
//            }
//        };


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selBtn :
//                new ConfirmPopWindow(this).showAtBottom(v);
                showDialog();
                break;

            default:
                break;
        }
    }

    public void showDialog() {
        //弹出提示框
        CommomDialog commomDialog = new CommomDialog(this, R.style.dialog, "确定退出？", new CommomDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if (confirm) {
                    dialog.dismiss();
//                    finish();
                }

            }
        }, new CommomDialog.JustTest() {
            @Override
            public void test(boolean confirm) {
                if (confirm)
                    Toast.makeText(getApplicationContext(), "hahhahh", Toast.LENGTH_LONG).show();
            }
        });
        commomDialog.setTitle("提示").show();
    }

    public static void dotest() {
        System.out.println("do test in Main");
    }
}
