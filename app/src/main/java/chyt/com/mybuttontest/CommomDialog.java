package chyt.com.mybuttontest;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CommomDialog extends Dialog implements View.OnClickListener{
    private TextView contentTxt;
    private TextView titleTxt;
    private Button submitTxt;
    private Button  cancelTxt;

    private Context mContext;
    private String content;
    private OnCloseListener listener;
    private String positiveName;
    private String negativeName;
    private String title;

    private JustTest justTest;

    public CommomDialog(Context context) {
        super(context);
        this.mContext = context;
    }
    public CommomDialog(Context context, String content) {
        super(context, R.style.dialog);
        this.mContext = context;
        this.content = content;
    }

    public CommomDialog(Context context, int themeResId, String content) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
    }

    public CommomDialog(Context context, int themeResId, String content, OnCloseListener listener, JustTest justTest) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.listener = listener;

        this.justTest = justTest;
    }

    protected CommomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public CommomDialog setTitle(String title){
        this.title = title;
        return this;
    }

    public CommomDialog setPositiveButton(String name){
        this.positiveName = name;
        return this;
    }

    public CommomDialog setNegativeButton(String name){
        this.negativeName = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_commom);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView(){
        contentTxt = (TextView)findViewById(R.id.content);
        titleTxt = (TextView)findViewById(R.id.title);
        submitTxt = (
                Button) findViewById(R.id.submit);
        submitTxt.setOnClickListener(this);
        cancelTxt = (Button) findViewById(R.id.cancel);
        cancelTxt.setOnClickListener(this);
        contentTxt.setText(content);
        cancelTxt.setSelected(true);
        if(!TextUtils.isEmpty(positiveName)){
            submitTxt.setText(positiveName);
        }

        if(!TextUtils.isEmpty(negativeName)){
            cancelTxt.setText(negativeName);
        }

        if(!TextUtils.isEmpty(title)){
            titleTxt.setText(title);
        }

    }

//    @Override
//    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
//        switch (keyCode)
//        {
//            case KeyEvent.KEYCODE_ENTER:
//
//                break;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:
                if(listener != null){
                    listener.onClick(this, false);

                    justTest.test(false);
                }
                this.dismiss();
                break;
                    case R.id.submit:
                if(listener != null){
                    listener.onClick(this, true);

                    justTest.test(true);
                }
                break;
        }
    }



    public interface OnCloseListener{
        void onClick(Dialog dialog, boolean confirm);
    }

    public interface JustTest {
        void test(boolean confirm);
    }
}
