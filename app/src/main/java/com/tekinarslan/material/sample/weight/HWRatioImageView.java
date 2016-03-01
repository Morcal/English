package com.tekinarslan.material.sample.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.tekinarslan.material.sample.R;

/**
 * 可以设定宽高比的ImageView
 **/
public class HWRatioImageView extends ImageView {
    private float radio;
    private boolean isKeepWidth = true;//固定宽高的标准, true,则保持宽不变, false, 则保持高不变

    public HWRatioImageView(Context context) {
        this(context, null);
    }

    public HWRatioImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HWRatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.HWRadioImageView);
        radio = a.getFloat(R.styleable.HWRadioImageView_hwRadio, 1);
        isKeepWidth = a.getBoolean(R.styleable.HWRadioImageView_widthStandard, true);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (radio > 0) {
            height = isKeepWidth ? (int) (width * radio) : height;
            width = isKeepWidth ? width : (int) (height * radio);
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setRadio(float radio) {
        this.radio = radio;
        requestLayout();
    }

    public void isKeepWidth(boolean isKeepWidth) {
        this.isKeepWidth = isKeepWidth;
    }
}
