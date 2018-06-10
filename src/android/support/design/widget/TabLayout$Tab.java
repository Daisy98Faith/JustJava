// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.content.res.AppCompatResources;
import android.view.LayoutInflater;
import android.view.View;

// Referenced classes of package android.support.design.widget:
//            TabLayout

public static final class mPosition
{

    public static final int INVALID_POSITION = -1;
    private CharSequence mContentDesc;
    private View mCustomView;
    private Drawable mIcon;
    TabLayout mParent;
    private int mPosition;
    private Object mTag;
    private CharSequence mText;
    iew mView;

    public CharSequence getContentDescription()
    {
        return mContentDesc;
    }

    public View getCustomView()
    {
        return mCustomView;
    }

    public Drawable getIcon()
    {
        return mIcon;
    }

    public int getPosition()
    {
        return mPosition;
    }

    public Object getTag()
    {
        return mTag;
    }

    public CharSequence getText()
    {
        return mText;
    }

    public boolean isSelected()
    {
        if (mParent == null)
        {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }
        return mParent.getSelectedTabPosition() == mPosition;
    }

    void reset()
    {
        mParent = null;
        mView = null;
        mTag = null;
        mIcon = null;
        mText = null;
        mContentDesc = null;
        mPosition = -1;
        mCustomView = null;
    }

    public void select()
    {
        if (mParent == null)
        {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        } else
        {
            mParent.selectTab(this);
            return;
        }
    }

    public ctTab setContentDescription(int i)
    {
        if (mParent == null)
        {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        } else
        {
            return setContentDescription(mParent.getResources().getText(i));
        }
    }

    public esources setContentDescription(CharSequence charsequence)
    {
        mContentDesc = charsequence;
        updateView();
        return this;
    }

    public updateView setCustomView(int i)
    {
        return setCustomView(LayoutInflater.from(mView.getContext()).inflate(i, mView, false));
    }

    public mView setCustomView(View view)
    {
        mCustomView = view;
        updateView();
        return this;
    }

    public updateView setIcon(int i)
    {
        if (mParent == null)
        {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        } else
        {
            return setIcon(AppCompatResources.getDrawable(mParent.getContext(), i));
        }
    }

    public ontext setIcon(Drawable drawable)
    {
        mIcon = drawable;
        updateView();
        return this;
    }

    void setPosition(int i)
    {
        mPosition = i;
    }

    public mPosition setTag(Object obj)
    {
        mTag = obj;
        return this;
    }

    public mTag setText(int i)
    {
        if (mParent == null)
        {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        } else
        {
            return setText(mParent.getResources().getText(i));
        }
    }

    public esources setText(CharSequence charsequence)
    {
        mText = charsequence;
        updateView();
        return this;
    }

    void updateView()
    {
        if (mView != null)
        {
            mView.update();
        }
    }

    iew()
    {
        mPosition = -1;
    }
}
