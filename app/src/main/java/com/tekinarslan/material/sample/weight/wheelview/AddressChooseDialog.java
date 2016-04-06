package com.tekinarslan.material.sample.weight.wheelview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.tekinarslan.material.sample.R;

/**
 * -----------------------------------------------------------
 * 版 权 ： BigTiger 版权所有 (c) 2015
 * 作 者 : BigTiger
 * 版 本 ： 1.0
 * 创建日期 ：2015/7/9 16:47
 * 描 述 ：
 * <p>
 * -------------------------------------------------------------
 */
public class AddressChooseDialog extends Dialog implements View.OnClickListener{
    private Context context;
    private LinearLayout lLayout_bg;
    private LinearLayout dialog_Group;

    private OnDialogItemClickListener mListener;

    public final static int SAVE = 0;
    public final static int CANCEL = 1;

    public AddressChooseDialog(Context context) {
        this(context, R.style.AlertDialogStyle);
    }

    public AddressChooseDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.alert_dialog_toast_view, null);

        // 获取自定义Dialog布局中的控件
        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        dialog_Group = (LinearLayout) view.findViewById(R.id.dialog_Group);

        view.findViewById(R.id.tv_cancel).setOnClickListener(this);
        view.findViewById(R.id.tv_save).setOnClickListener(this);
        setContentView(view);

    }

    public AddressChooseDialog setView(View view) {
        dialog_Group.addView(view,
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        return this;
    }

    public static AddressChooseDialog show(Context context) {
        AddressChooseDialog dialog = new AddressChooseDialog(context);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        return dialog;
    }

    public AddressChooseDialog addOnDialogItemClickListener(OnDialogItemClickListener listener){
        mListener = listener;
        return this;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null){
            mListener.onClick(v.getId() == R.id.tv_save? SAVE: CANCEL);
            dismiss();
        }
    }

    public interface OnDialogItemClickListener {
        void onClick(int which);
    }
}
