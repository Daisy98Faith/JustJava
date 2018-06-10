// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SnackbarContentLayout extends LinearLayout
    implements android.support.design.widget.BaseTransientBottomBar.ContentViewCallback
{

    private Button mActionView;
    private int mMaxInlineActionWidth;
    private int mMaxWidth;
    private TextView mMessageView;

    public SnackbarContentLayout(Context context)
    {
        this(context, null);
    }

    public SnackbarContentLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        context = context.obtainStyledAttributes(attributeset, android.support.design.R.styleable.SnackbarLayout);
        mMaxWidth = context.getDimensionPixelSize(android.support.design.R.styleable.SnackbarLayout_android_maxWidth, -1);
        mMaxInlineActionWidth = context.getDimensionPixelSize(android.support.design.R.styleable.SnackbarLayout_maxActionInlineWidth, -1);
        context.recycle();
    }

    private static void updateTopBottomPadding(View view, int i, int j)
    {
        if (ViewCompat.isPaddingRelative(view))
        {
            ViewCompat.setPaddingRelative(view, ViewCompat.getPaddingStart(view), i, ViewCompat.getPaddingEnd(view), j);
            return;
        } else
        {
            view.setPadding(view.getPaddingLeft(), i, view.getPaddingRight(), j);
            return;
        }
    }

    private boolean updateViewsWithinLayout(int i, int j, int k)
    {
        boolean flag = false;
        if (i != getOrientation())
        {
            setOrientation(i);
            flag = true;
        }
        if (mMessageView.getPaddingTop() != j || mMessageView.getPaddingBottom() != k)
        {
            updateTopBottomPadding(mMessageView, j, k);
            flag = true;
        }
        return flag;
    }

    public void animateContentIn(int i, int j)
    {
        mMessageView.setAlpha(0.0F);
        mMessageView.animate().alpha(1.0F).setDuration(j).setStartDelay(i).start();
        if (mActionView.getVisibility() == 0)
        {
            mActionView.setAlpha(0.0F);
            mActionView.animate().alpha(1.0F).setDuration(j).setStartDelay(i).start();
        }
    }

    public void animateContentOut(int i, int j)
    {
        mMessageView.setAlpha(1.0F);
        mMessageView.animate().alpha(0.0F).setDuration(j).setStartDelay(i).start();
        if (mActionView.getVisibility() == 0)
        {
            mActionView.setAlpha(1.0F);
            mActionView.animate().alpha(0.0F).setDuration(j).setStartDelay(i).start();
        }
    }

    public Button getActionView()
    {
        return mActionView;
    }

    public TextView getMessageView()
    {
        return mMessageView;
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        mMessageView = (TextView)findViewById(android.support.design.R.id.snackbar_text);
        mActionView = (Button)findViewById(android.support.design.R.id.snackbar_action);
    }

    protected void onMeasure(int i, int j)
    {
        int l;
        int i1;
        boolean flag;
        super.onMeasure(i, j);
        int k = i;
        if (mMaxWidth > 0)
        {
            k = i;
            if (getMeasuredWidth() > mMaxWidth)
            {
                k = android.view.View.MeasureSpec.makeMeasureSpec(mMaxWidth, 0x40000000);
                super.onMeasure(k, j);
            }
        }
        l = getResources().getDimensionPixelSize(android.support.design.R.dimen.design_snackbar_padding_vertical_2lines);
        i1 = getResources().getDimensionPixelSize(android.support.design.R.dimen.design_snackbar_padding_vertical);
        if (mMessageView.getLayout().getLineCount() > 1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        flag = false;
        if (i == 0 || mMaxInlineActionWidth <= 0 || mActionView.getMeasuredWidth() <= mMaxInlineActionWidth) goto _L2; else goto _L1
_L1:
        i = ((flag) ? 1 : 0);
        if (updateViewsWithinLayout(1, l, l - i1))
        {
            i = 1;
        }
_L4:
        if (i != 0)
        {
            super.onMeasure(k, j);
        }
        return;
_L2:
        if (i == 0)
        {
            l = i1;
        }
        i = ((flag) ? 1 : 0);
        if (updateViewsWithinLayout(0, l, l))
        {
            i = 1;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }
}
