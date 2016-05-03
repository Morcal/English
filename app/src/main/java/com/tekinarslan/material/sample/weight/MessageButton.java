package com.tekinarslan.material.sample.weight;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tekinarslan.material.sample.R;


public class MessageButton extends LinearLayout implements View.OnClickListener {

    public static final int TYPE_MESSAGE = 0;
    public static final int TYPE_NOTICE = 1;

    private int selectType;

    private View layout;
    private TextView msgTextView;
    private TextView noticeTextView;
    private View msgHint;
    private View noticeHint;

    private int selectColor;
    private int normalColor;

    private ISelectListener listener;

    public MessageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MessageButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);
    }

    @SuppressLint("NewApi")
    public MessageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MessageButton, defStyle, 0);
        selectType = a.getInt(R.styleable.MessageButton_selectType, TYPE_MESSAGE);
        a.recycle();

        selectColor = getContext().getResources().getColor(R.color.colorPrimary);
        normalColor = getContext().getResources().getColor(R.color.light);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.view_message_button, null);
        layout = view.findViewById(R.id.layout_message);
        msgTextView = (TextView) view.findViewById(R.id.tx_message);
        noticeTextView = (TextView) view.findViewById(R.id.tx_notice);
        msgHint = view.findViewById(R.id.hint_msg);
        noticeHint = view.findViewById(R.id.hint_notice);

        view.findViewById(R.id.btn_message).setOnClickListener(this);
        view.findViewById(R.id.btn_notice).setOnClickListener(this);

        select(selectType);
        setMessageHint(TYPE_MESSAGE, false);
        setMessageHint(TYPE_NOTICE, false);

        attachViewToParent(view, 0, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    public interface ISelectListener {
        public void select(int type);
    }

    public void setSelectListener(ISelectListener listener) {
        this.listener = listener;
    }

    /**
     * 选中
     */
    public void select(int type) {
        if (type == TYPE_MESSAGE) {
            layout.setBackgroundResource(R.drawable.bg_select_message);
            msgTextView.setTextColor(selectColor);
            noticeTextView.setTextColor(normalColor);
        } else if (type == TYPE_NOTICE) {
            layout.setBackgroundResource(R.drawable.bg_select_notice);
            msgTextView.setTextColor(normalColor);
            noticeTextView.setTextColor(selectColor);
        }
    }

    /**
     * 设置红点提醒
     */
    public void setMessageHint(int type, boolean hasMessage) {
        int visible = hasMessage ? View.VISIBLE : View.INVISIBLE;
        if (type == TYPE_MESSAGE) {
            msgHint.setVisibility(visible);
        } else if (type == TYPE_NOTICE) {
            noticeHint.setVisibility(visible);
        }
    }

    @Override
    public void onClick(View v) {
        if (listener == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.btn_message:
                listener.select(TYPE_MESSAGE);
                select(TYPE_MESSAGE);
                break;
            case R.id.btn_notice:
                listener.select(TYPE_NOTICE);
                select(TYPE_NOTICE);
                break;
        }
    }

}
