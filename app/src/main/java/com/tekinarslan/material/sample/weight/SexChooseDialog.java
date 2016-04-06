package com.tekinarslan.material.sample.weight;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.tekinarslan.material.sample.R;
import com.tekinarslan.material.sample.utills.UIUtil;

public class SexChooseDialog extends AlertDialog implements View.OnClickListener {
    private Context context;
    public final static int MALE = 0;
    public final static int FEMALE = 1;

    private OnDialogItemClickListener mListener;

    private boolean isMale; //true 男的,  false  女的

    protected SexChooseDialog(Context context, boolean defaultSex) {
        this(context, R.style.ActionSheetDialogStyle);
        isMale = defaultSex;
    }

    protected SexChooseDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sex_choose, null);

        // 获取自定义Dialog布局中的控件
        view.findViewById(R.id.rl_sex_male).setOnClickListener(this);
        view.findViewById(R.id.rl_sex_female).setOnClickListener(this);
        setContentView(view);
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = UIUtil.dp2Px(context, 138);

        dialogWindow.setAttributes(lp);
    }

    public SexChooseDialog addOnDialogItemClickListener(OnDialogItemClickListener listener) {
        mListener = listener;
        return this;
    }

    public static SexChooseDialog show(Context context, boolean isMale) {
        SexChooseDialog dialog = new SexChooseDialog(context, isMale);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        // 修改系统menu菜单不能全屏显示问题
        return dialog;
    }

    @Override
    public void onClick(View v) {
        if (mListener == null) {
            return;
        }
        mListener.onClick(v.getId() == R.id.rl_sex_male ? MALE : FEMALE);
        dismiss();
    }

    public interface OnDialogItemClickListener {
        void onClick(int which);
    }
}
