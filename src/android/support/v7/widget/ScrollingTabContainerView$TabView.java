// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import android.widget.TextView;

// Referenced classes of package android.support.v7.widget:
//            LinearLayoutCompat, ScrollingTabContainerView, TintTypedArray, AppCompatImageView, 
//            AppCompatTextView

private class update extends LinearLayoutCompat
{

    private final int BG_ATTRS[] = {
        0x10100d4
    };
    private View mCustomView;
    private ImageView mIconView;
    private android.support.v7.app.n mTab;
    private TextView mTextView;
    final ScrollingTabContainerView this$0;

    public void bindTab(android.support.v7.app.ew ew)
    {
        mTab = ew;
        update();
    }

    public android.support.v7.app.ew getTab()
    {
        return mTab;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEvent(accessibilityevent);
        accessibilityevent.setClassName(android/support/v7/app/ActionBar$Tab.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfo(accessibilitynodeinfo);
        if (android.os.nfo >= 14)
        {
            accessibilitynodeinfo.setClassName(android/support/v7/app/ActionBar$Tab.getName());
        }
    }

    public void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        if (mMaxTabWidth > 0 && getMeasuredWidth() > mMaxTabWidth)
        {
            super.onMeasure(android.view.Measure(mMaxTabWidth, 0x40000000), j);
        }
    }

    public void setSelected(boolean flag)
    {
        boolean flag1;
        if (isSelected() != flag)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        super.setSelected(flag);
        if (flag1 && flag)
        {
            sendAccessibilityEvent(4);
        }
    }

    public void update()
    {
        Object obj = null;
        android.support.v7.app.ew ew = mTab;
        Object obj1 = ew.mTab();
        if (obj1 != null)
        {
            obj = ((View) (obj1)).getParent();
            if (obj != this)
            {
                if (obj != null)
                {
                    ((ViewGroup)obj).removeView(((View) (obj1)));
                }
                addView(((View) (obj1)));
            }
            mCustomView = ((View) (obj1));
            if (mTextView != null)
            {
                mTextView.setVisibility(8);
            }
            if (mIconView != null)
            {
                mIconView.setVisibility(8);
                mIconView.setImageDrawable(null);
            }
            return;
        }
        if (mCustomView != null)
        {
            removeView(mCustomView);
            mCustomView = null;
        }
        android.graphics.drawable.Drawable drawable = ew.mCustomView();
        obj1 = ew.mCustomView();
        boolean flag;
        if (drawable != null)
        {
            if (mIconView == null)
            {
                AppCompatImageView appcompatimageview = new AppCompatImageView(getContext());
                sendAccessibilityEvent sendaccessibilityevent1 = new nit>(-2, -2);
                sendaccessibilityevent1.avity = 16;
                appcompatimageview.setLayoutParams(sendaccessibilityevent1);
                addView(appcompatimageview, 0);
                mIconView = appcompatimageview;
            }
            mIconView.setImageDrawable(drawable);
            mIconView.setVisibility(0);
        } else
        if (mIconView != null)
        {
            mIconView.setVisibility(8);
            mIconView.setImageDrawable(null);
        }
        if (!TextUtils.isEmpty(((CharSequence) (obj1))))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            if (mTextView == null)
            {
                AppCompatTextView appcompattextview = new AppCompatTextView(getContext(), null, android.support.v7.appcompat.Context);
                appcompattextview.setEllipsize(android.text.bTextStyle);
                sendAccessibilityEvent sendaccessibilityevent = new nit>(-2, -2);
                sendaccessibilityevent.avity = 16;
                appcompattextview.setLayoutParams(sendaccessibilityevent);
                addView(appcompattextview);
                mTextView = appcompattextview;
            }
            mTextView.setText(((CharSequence) (obj1)));
            mTextView.setVisibility(0);
        } else
        if (mTextView != null)
        {
            mTextView.setVisibility(8);
            mTextView.setText(null);
        }
        if (mIconView != null)
        {
            mIconView.setContentDescription(ew.n());
        }
        if (!flag)
        {
            obj = ew.n();
        }
        ViewCompat.setTooltipText(this, ((CharSequence) (obj)));
    }

    public A(Context context, android.support.v7.app.ew ew, boolean flag)
    {
        this$0 = ScrollingTabContainerView.this;
        super(context, null, android.support.v7.appcompat.s._fld0);
        mTab = ew;
        scrollingtabcontainerview = TintTypedArray.obtainStyledAttributes(context, null, BG_ATTRS, android.support.v7.appcompat.ATTRS, 0);
        if (hasValue(0))
        {
            setBackgroundDrawable(getDrawable(0));
        }
        recycle();
        if (flag)
        {
            setGravity(0x800013);
        }
        update();
    }
}
