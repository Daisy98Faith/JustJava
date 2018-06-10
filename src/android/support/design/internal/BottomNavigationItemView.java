// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.menu.MenuItemImpl;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class BottomNavigationItemView extends FrameLayout
    implements android.support.v7.view.menu.MenuView.ItemView
{

    private static final int CHECKED_STATE_SET[] = {
        0x10100a0
    };
    public static final int INVALID_ITEM_POSITION = -1;
    private final int mDefaultMargin;
    private ImageView mIcon;
    private ColorStateList mIconTint;
    private MenuItemImpl mItemData;
    private int mItemPosition;
    private final TextView mLargeLabel;
    private final float mScaleDownFactor;
    private final float mScaleUpFactor;
    private final int mShiftAmount;
    private boolean mShiftingMode;
    private final TextView mSmallLabel;

    public BottomNavigationItemView(Context context)
    {
        this(context, null);
    }

    public BottomNavigationItemView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public BottomNavigationItemView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mItemPosition = -1;
        attributeset = getResources();
        i = attributeset.getDimensionPixelSize(android.support.design.R.dimen.design_bottom_navigation_text_size);
        int j = attributeset.getDimensionPixelSize(android.support.design.R.dimen.design_bottom_navigation_active_text_size);
        mDefaultMargin = attributeset.getDimensionPixelSize(android.support.design.R.dimen.design_bottom_navigation_margin);
        mShiftAmount = i - j;
        mScaleUpFactor = ((float)j * 1.0F) / (float)i;
        mScaleDownFactor = ((float)i * 1.0F) / (float)j;
        LayoutInflater.from(context).inflate(android.support.design.R.layout.design_bottom_navigation_item, this, true);
        setBackgroundResource(android.support.design.R.drawable.design_bottom_navigation_item_background);
        mIcon = (ImageView)findViewById(android.support.design.R.id.icon);
        mSmallLabel = (TextView)findViewById(android.support.design.R.id.smallLabel);
        mLargeLabel = (TextView)findViewById(android.support.design.R.id.largeLabel);
    }

    public MenuItemImpl getItemData()
    {
        return mItemData;
    }

    public int getItemPosition()
    {
        return mItemPosition;
    }

    public void initialize(MenuItemImpl menuitemimpl, int i)
    {
        mItemData = menuitemimpl;
        setCheckable(menuitemimpl.isCheckable());
        setChecked(menuitemimpl.isChecked());
        setEnabled(menuitemimpl.isEnabled());
        setIcon(menuitemimpl.getIcon());
        setTitle(menuitemimpl.getTitle());
        setId(menuitemimpl.getItemId());
        setContentDescription(menuitemimpl.getContentDescription());
        ViewCompat.setTooltipText(this, menuitemimpl.getTooltipText());
    }

    public int[] onCreateDrawableState(int i)
    {
        int ai[] = super.onCreateDrawableState(i + 1);
        if (mItemData != null && mItemData.isCheckable() && mItemData.isChecked())
        {
            mergeDrawableStates(ai, CHECKED_STATE_SET);
        }
        return ai;
    }

    public boolean prefersCondensedTitle()
    {
        return false;
    }

    public void setCheckable(boolean flag)
    {
        refreshDrawableState();
    }

    public void setChecked(boolean flag)
    {
        mLargeLabel.setPivotX(mLargeLabel.getWidth() / 2);
        mLargeLabel.setPivotY(mLargeLabel.getBaseline());
        mSmallLabel.setPivotX(mSmallLabel.getWidth() / 2);
        mSmallLabel.setPivotY(mSmallLabel.getBaseline());
        if (mShiftingMode)
        {
            if (flag)
            {
                android.widget.FrameLayout.LayoutParams layoutparams = (android.widget.FrameLayout.LayoutParams)mIcon.getLayoutParams();
                layoutparams.gravity = 49;
                layoutparams.topMargin = mDefaultMargin;
                mIcon.setLayoutParams(layoutparams);
                mLargeLabel.setVisibility(0);
                mLargeLabel.setScaleX(1.0F);
                mLargeLabel.setScaleY(1.0F);
            } else
            {
                android.widget.FrameLayout.LayoutParams layoutparams1 = (android.widget.FrameLayout.LayoutParams)mIcon.getLayoutParams();
                layoutparams1.gravity = 17;
                layoutparams1.topMargin = mDefaultMargin;
                mIcon.setLayoutParams(layoutparams1);
                mLargeLabel.setVisibility(4);
                mLargeLabel.setScaleX(0.5F);
                mLargeLabel.setScaleY(0.5F);
            }
            mSmallLabel.setVisibility(4);
        } else
        if (flag)
        {
            android.widget.FrameLayout.LayoutParams layoutparams2 = (android.widget.FrameLayout.LayoutParams)mIcon.getLayoutParams();
            layoutparams2.gravity = 49;
            layoutparams2.topMargin = mDefaultMargin + mShiftAmount;
            mIcon.setLayoutParams(layoutparams2);
            mLargeLabel.setVisibility(0);
            mSmallLabel.setVisibility(4);
            mLargeLabel.setScaleX(1.0F);
            mLargeLabel.setScaleY(1.0F);
            mSmallLabel.setScaleX(mScaleUpFactor);
            mSmallLabel.setScaleY(mScaleUpFactor);
        } else
        {
            android.widget.FrameLayout.LayoutParams layoutparams3 = (android.widget.FrameLayout.LayoutParams)mIcon.getLayoutParams();
            layoutparams3.gravity = 49;
            layoutparams3.topMargin = mDefaultMargin;
            mIcon.setLayoutParams(layoutparams3);
            mLargeLabel.setVisibility(4);
            mSmallLabel.setVisibility(0);
            mLargeLabel.setScaleX(mScaleDownFactor);
            mLargeLabel.setScaleY(mScaleDownFactor);
            mSmallLabel.setScaleX(1.0F);
            mSmallLabel.setScaleY(1.0F);
        }
        refreshDrawableState();
    }

    public void setEnabled(boolean flag)
    {
        super.setEnabled(flag);
        mSmallLabel.setEnabled(flag);
        mLargeLabel.setEnabled(flag);
        mIcon.setEnabled(flag);
        if (flag)
        {
            ViewCompat.setPointerIcon(this, PointerIconCompat.getSystemIcon(getContext(), 1002));
            return;
        } else
        {
            ViewCompat.setPointerIcon(this, null);
            return;
        }
    }

    public void setIcon(Drawable drawable)
    {
        Object obj = drawable;
        if (drawable != null)
        {
            obj = drawable.getConstantState();
            if (obj != null)
            {
                drawable = ((android.graphics.drawable.Drawable.ConstantState) (obj)).newDrawable();
            }
            obj = DrawableCompat.wrap(drawable).mutate();
            DrawableCompat.setTintList(((Drawable) (obj)), mIconTint);
        }
        mIcon.setImageDrawable(((Drawable) (obj)));
    }

    public void setIconTintList(ColorStateList colorstatelist)
    {
        mIconTint = colorstatelist;
        if (mItemData != null)
        {
            setIcon(mItemData.getIcon());
        }
    }

    public void setItemBackground(int i)
    {
        Drawable drawable;
        if (i == 0)
        {
            drawable = null;
        } else
        {
            drawable = ContextCompat.getDrawable(getContext(), i);
        }
        ViewCompat.setBackground(this, drawable);
    }

    public void setItemPosition(int i)
    {
        mItemPosition = i;
    }

    public void setShiftingMode(boolean flag)
    {
        mShiftingMode = flag;
    }

    public void setShortcut(boolean flag, char c)
    {
    }

    public void setTextColor(ColorStateList colorstatelist)
    {
        mSmallLabel.setTextColor(colorstatelist);
        mLargeLabel.setTextColor(colorstatelist);
    }

    public void setTitle(CharSequence charsequence)
    {
        mSmallLabel.setText(charsequence);
        mLargeLabel.setText(charsequence);
        setContentDescription(charsequence);
    }

    public boolean showsIcon()
    {
        return true;
    }

}
