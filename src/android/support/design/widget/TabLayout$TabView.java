// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.content.Context;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.content.res.AppCompatResources;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

// Referenced classes of package android.support.design.widget:
//            TabLayout

class getContext extends LinearLayout
{

    private ImageView mCustomIconView;
    private TextView mCustomTextView;
    private View mCustomView;
    private int mDefaultMaxLines;
    private ImageView mIconView;
    private setSelected mTab;
    private TextView mTextView;
    final TabLayout this$0;

    private float approximateLineWidth(Layout layout, int i, float f)
    {
        return layout.getLineWidth(i) * (f / layout.getPaint().getTextSize());
    }

    private void updateTextAndIcon(TextView textview, ImageView imageview)
    {
        Object obj = null;
        CharSequence charsequence;
        android.graphics.drawable.Drawable drawable;
        CharSequence charsequence1;
        boolean flag;
        if (mTab != null)
        {
            drawable = mTab.con();
        } else
        {
            drawable = null;
        }
        if (mTab != null)
        {
            charsequence1 = mTab.ext();
        } else
        {
            charsequence1 = null;
        }
        if (mTab != null)
        {
            charsequence = mTab.ontentDescription();
        } else
        {
            charsequence = null;
        }
        if (imageview != null)
        {
            int i;
            boolean flag1;
            if (drawable != null)
            {
                imageview.setImageDrawable(drawable);
                imageview.setVisibility(0);
                setVisibility(0);
            } else
            {
                imageview.setVisibility(8);
                imageview.setImageDrawable(null);
            }
            imageview.setContentDescription(charsequence);
        }
        if (!TextUtils.isEmpty(charsequence1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (textview != null)
        {
            if (flag)
            {
                textview.setText(charsequence1);
                textview.setVisibility(0);
                setVisibility(0);
            } else
            {
                textview.setVisibility(8);
                textview.setText(null);
            }
            textview.setContentDescription(charsequence);
        }
        if (imageview != null)
        {
            textview = (android.view.youtParams)imageview.getLayoutParams();
            flag1 = false;
            i = ((flag1) ? 1 : 0);
            if (flag)
            {
                i = ((flag1) ? 1 : 0);
                if (imageview.getVisibility() == 0)
                {
                    i = dpToPx(8);
                }
            }
            if (i != ((android.view.youtParams) (textview)).bottomMargin)
            {
                textview.bottomMargin = i;
                imageview.requestLayout();
            }
        }
        if (flag)
        {
            textview = obj;
        } else
        {
            textview = charsequence;
        }
        ViewCompat.setTooltipText(this, textview);
    }

    public t getTab()
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
        accessibilitynodeinfo.setClassName(android/support/v7/app/ActionBar$Tab.getName());
    }

    public void onMeasure(int i, int j)
    {
        int k = android.view.etSize(i);
        int i1 = android.view.etMode(i);
        int k1 = getTabMaxWidth();
        if (k1 > 0 && (i1 == 0 || k > k1))
        {
            i = android.view.akeMeasureSpec(mTabMaxWidth, 0x80000000);
        }
        super.onMeasure(i, j);
        if (mTextView == null) goto _L2; else goto _L1
_L1:
        float f1;
        int j1;
        getResources();
        f1 = mTabTextSize;
        j1 = mDefaultMaxLines;
        if (mIconView == null || mIconView.getVisibility() != 0) goto _L4; else goto _L3
_L3:
        float f;
        int l;
        l = 1;
        f = f1;
_L6:
        f1 = mTextView.getTextSize();
        int l1 = mTextView.getLineCount();
        j1 = TextViewCompat.getMaxLines(mTextView);
        if (f != f1 || j1 >= 0 && l != j1)
        {
label0:
            {
                boolean flag = true;
                j1 = ((flag) ? 1 : 0);
                if (mMode != 1)
                {
                    break label0;
                }
                j1 = ((flag) ? 1 : 0);
                if (f <= f1)
                {
                    break label0;
                }
                j1 = ((flag) ? 1 : 0);
                if (l1 != 1)
                {
                    break label0;
                }
                Layout layout = mTextView.getLayout();
                if (layout != null)
                {
                    j1 = ((flag) ? 1 : 0);
                    if (approximateLineWidth(layout, 0, f) <= (float)(getMeasuredWidth() - getPaddingLeft() - getPaddingRight()))
                    {
                        break label0;
                    }
                }
                j1 = 0;
            }
            if (j1 != 0)
            {
                mTextView.setTextSize(0, f);
                mTextView.setMaxLines(l);
                super.onMeasure(i, j);
            }
        }
_L2:
        return;
_L4:
        l = j1;
        f = f1;
        if (mTextView != null)
        {
            l = j1;
            f = f1;
            if (mTextView.getLineCount() > 1)
            {
                f = mTabTextMultiLineSize;
                l = j1;
            }
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public boolean performClick()
    {
        boolean flag1 = super.performClick();
        boolean flag = flag1;
        if (mTab != null)
        {
            if (!flag1)
            {
                playSoundEffect(0);
            }
            mTab.ct();
            flag = true;
        }
        return flag;
    }

    void reset()
    {
        setTab(null);
        setSelected(false);
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
        if (flag1 && flag && android.os.INT < 16)
        {
            sendAccessibilityEvent(4);
        }
        if (mTextView != null)
        {
            mTextView.setSelected(flag);
        }
        if (mIconView != null)
        {
            mIconView.setSelected(flag);
        }
        if (mCustomView != null)
        {
            mCustomView.setSelected(flag);
        }
    }

    void setTab(mCustomView mcustomview)
    {
        if (mcustomview != mTab)
        {
            mTab = mcustomview;
            update();
        }
    }

    final void update()
    {
        update update1 = mTab;
        Object obj;
        boolean flag;
        if (update1 != null)
        {
            obj = update1.ustomView();
        } else
        {
            obj = null;
        }
        if (obj != null)
        {
            android.view.ViewParent viewparent = ((View) (obj)).getParent();
            if (viewparent != this)
            {
                if (viewparent != null)
                {
                    ((ViewGroup)viewparent).removeView(((View) (obj)));
                }
                addView(((View) (obj)));
            }
            mCustomView = ((View) (obj));
            if (mTextView != null)
            {
                mTextView.setVisibility(8);
            }
            if (mIconView != null)
            {
                mIconView.setVisibility(8);
                mIconView.setImageDrawable(null);
            }
            mCustomTextView = (TextView)((View) (obj)).findViewById(0x1020014);
            if (mCustomTextView != null)
            {
                mDefaultMaxLines = TextViewCompat.getMaxLines(mCustomTextView);
            }
            mCustomIconView = (ImageView)((View) (obj)).findViewById(0x1020006);
        } else
        {
            if (mCustomView != null)
            {
                removeView(mCustomView);
                mCustomView = null;
            }
            mCustomTextView = null;
            mCustomIconView = null;
        }
        if (mCustomView == null)
        {
            if (mIconView == null)
            {
                obj = (ImageView)LayoutInflater.from(getContext()).inflate(android.support.design.yout_tab_icon, this, false);
                addView(((View) (obj)), 0);
                mIconView = ((ImageView) (obj));
            }
            if (mTextView == null)
            {
                obj = (TextView)LayoutInflater.from(getContext()).inflate(android.support.design.yout_tab_text, this, false);
                addView(((View) (obj)));
                mTextView = ((TextView) (obj));
                mDefaultMaxLines = TextViewCompat.getMaxLines(mTextView);
            }
            TextViewCompat.setTextAppearance(mTextView, mTabTextAppearance);
            if (mTabTextColors != null)
            {
                mTextView.setTextColor(mTabTextColors);
            }
            updateTextAndIcon(mTextView, mIconView);
        } else
        if (mCustomTextView != null || mCustomIconView != null)
        {
            updateTextAndIcon(mCustomTextView, mCustomIconView);
        }
        if (update1 != null && update1.lected())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        setSelected(flag);
    }

    public (Context context)
    {
        this$0 = TabLayout.this;
        super(context);
        mDefaultMaxLines = 2;
        if (mTabBackgroundResId != 0)
        {
            ViewCompat.setBackground(this, AppCompatResources.getDrawable(context, mTabBackgroundResId));
        }
        ViewCompat.setPaddingRelative(this, mTabPaddingStart, mTabPaddingTop, mTabPaddingEnd, mTabPaddingBottom);
        setGravity(17);
        setOrientation(1);
        setClickable(true);
        ViewCompat.setPointerIcon(this, PointerIconCompat.getSystemIcon(getContext(), 1002));
    }
}
