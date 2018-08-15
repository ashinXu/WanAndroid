package com.ashin.wanandroid.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ashin.wanandroid.R;

public class BaseProgressDialog extends Dialog {

    private ProgressBar progressBar;
    private TextView tv_msg,tv_progress;
    private static BaseProgressDialog dialog;


    public static void showDialog(Context context){
        dialog = new BaseProgressDialog(context);
        dialog.show();
    }

    public static void hideDialog(){
        if (dialog != null){
            dialog.dismiss();
        }
    }


    public BaseProgressDialog(Context context) {
        //一开始就设置为透明背景
        super(context);
        createLoadingDialog(context);
    }


    public void createLoadingDialog(final Context context) {

        LayoutInflater inflater = LayoutInflater.from(context);
        //得到加载的view
        View v = inflater.inflate(R.layout.progress_dialog_layout, null);
        //加载布局
        TextView title = (TextView) v.findViewById(R.id.load_view);

        //设置不可通过点击外面区域取消
        setCanceledOnTouchOutside(false);

    }

    // 设置加载信息
    public void setMessage(String msg){
        tv_msg.setText(msg);
    }

    //设置进度条
    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }
}
