// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.view.menu.MenuItemImpl;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;

// Referenced classes of package android.support.design.internal:
//            ForegroundLinearLayout

public class NavigationMenuItemView extends ForegroundLinearLayout
    implements android.support.v7.view.menu.MenuView.ItemView
{

    private static final int CHECKED_STATE_SET[] = {
        0x10100a0
    };
    private final AccessibilityDelegateCompat mAccessibilityDelegate;
    private FrameLayout mActionArea;
    boolean mCheckable;
    private Drawable mEmptyDrawable;
    private boolean mHasIconTintList;
    private final int mIconSize;
    private ColorStateList mIconTintList;
    private MenuItemImpl mItemData;
    private boolean mNeedsEmptyIcon;
    private final CheckedTextView mTextView;

    public NavigationMenuItemView(Context context)
    {
        this(context, null);
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mAccessibilityDelegate = new AccessibilityDelegateCompat() {

            final NavigationMenuItemView this$0;

            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
            {
                super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
                accessibilitynodeinfocompat.setCheckable(mCheckable);
            }

            
            {
                this$0 = NavigationMenuItemView.this;
                super();
            }
        };
        setOrientation(0);
        LayoutInflater.from(context).inflate(android.support.design.R.layout.design_navigation_menu_item, this, true);
        mIconSize = context.getResources().getDimensionPixelSize(android.support.design.R.dimen.design_navigation_icon_size);
        mTextView = (CheckedTextView)findViewById(android.support.design.R.id.design_menu_item_text);
        mTextView.setDuplicateParentStateEnabled(true);
        ViewCompat.setAccessibilityDelegate(mTextView, mAccessibilityDelegate);
    }

    private void adjustAppearance()
    {
        if (shouldExpandActionArea())
        {
            mTextView.setVisibility(8);
            if (mActionArea != null)
            {
                android.support.v7.widget.LinearLayoutCompat.LayoutParams layoutparams = (android.support.v7.widget.LinearLayoutCompat.LayoutParams)mActionArea.getLayoutParams();
                layoutparams.width = -1;
                mActionArea.setLayoutParams(layoutparams);
            }
        } else
        {
            mTextView.setVisibility(0);
            if (mActionArea != null)
            {
                android.support.v7.widget.LinearLayoutCompat.LayoutParams layoutparams1 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams)mActionArea.getLayoutParams();
                layoutparams1.width = -2;
                mActionArea.setLayoutParams(layoutparams1);
                return;
            }
        }
    }

    private StateListDrawable createDefaultBackground()
    {
        TypedValue typedvalue = new TypedValue();
        if (getContext().getTheme().resolveAttribute(android.support.v7.appcompat.R.attr.colorControlHighlight, typedvalue, true))
        {
            StateListDrawable statelistdrawable = new StateListDrawable();
            statelistdrawable.addState(CHECKED_STATE_SET, new ColorDrawable(typedvalue.data));
            statelistdrawable.addState(EMPTY_STATE_SET, new ColorDrawable(0));
            return statelistdrawable;
        } else
        {
            return null;
        }
    }

    private void setActionView(View view)
    {
        if (view != null)
        {
            if (mActionArea == null)
            {
                mActionArea = (FrameLayout)((ViewStub)findViewById(android.support.design.R.id.design_menu_item_action_area_stub)).inflate();
            }
            mActionArea.removeAllViews();
            mActionArea.addView(view);
        }
    }

    private boolean shouldExpandActionArea()
    {
        return mItemData.getTitle() == null && mItemData.getIcon() == null && mItemData.getActionView() != null;
    }

    public MenuItemImpl getItemData()
    {
        return mItemData;
    }

    public void initialize(MenuItemImpl menuitemimpl, int i)
    {
        mItemData = menuitemimpl;
        if (menuitemimpl.isVisible())
        {
            i = 0;
        } else
        {
            i = 8;
        }
        setVisibility(i);
        if (getBackground() == null)
        {
            ViewCompat.setBackground(this, createDefaultBackground());
        }
        setCheckable(menuitemimpl.isCheckable());
        setChecked(menuitemimpl.isChecked());
        setEnabled(menuitemimpl.isEnabled());
        setTitle(menuitemimpl.getTitle());
        setIcon(menuitemimpl.getIcon());
        setActionView(menuitemimpl.getActionView());
        setContentDescription(menuitemimpl.getContentDescription());
        ViewCompat.setTooltipText(this, menuitemimpl.getTooltipText());
        adjustAppearance();
    }

    protected int[] onCreateDrawableState(int i)
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

    public void recycle()
    {
        if (mActionArea != null)
        {
            mActionArea.removeAllViews();
        }
        mTextView.setCompoundDrawables(null, null, null, null);
    }

    public void setCheckable(boolean flag)
    {
        refreshDrawableState();
        if (mCheckable != flag)
        {
            mCheckable = flag;
            mAccessibilityDelegate.sendAccessibilityEvent(mTextView, 2048);
        }
    }

    public void setChecked(boolean flag)
    {
        refreshDrawableState();
        mTextView.setChecked(flag);
    }

    public void setIcon(Drawable drawable)
    {
        if (drawable == null) goto _L2; else goto _L1
_L1:
        Object obj = drawable;
        if (mHasIconTintList)
        {
            obj = drawable.getConstantState();
            if (obj != null)
            {
                drawable = ((android.graphics.drawable.Drawable.ConstantState) (obj)).newDrawable();
            }
            obj = DrawableCompat.wrap(drawable).mutate();
            DrawableCompat.setTintList(((Drawable) (obj)), mIconTintList);
        }
        ((Drawable) (obj)).setBounds(0, 0, mIconSize, mIconSize);
        drawable = ((Drawable) (obj));
_L4:
        TextViewCompat.setCompoundDrawablesRelative(mTextView, drawable, null, null, null);
        return;
_L2:
        if (mNeedsEmptyIcon)
        {
            if (mEmptyDrawable == null)
            {
                mEmptyDrawable = ResourcesCompat.getDrawable(getResources(), android.support.design.R.drawable.navigation_empty_icon, getContext().getTheme());
                if (mEmptyDrawable != null)
                {
                    mEmptyDrawable.setBounds(0, 0, mIconSize, mIconSize);
                }
            }
            drawable = mEmptyDrawable;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    void setIconTintList(ColorStateList colorstatelist)
    {
        mIconTintList = colorstatelist;
        boolean flag;
        if (mIconTintList != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mHasIconTintList = flag;
        if (mItemData != null)
        {
            setIcon(mItemData.getIcon());
        }
    }

    public void setNeedsEmptyIcon(boolean flag)
    {
        mNeedsEmptyIcon = flag;
    }

    public void setShortcut(boolean flag, char c)
    {
    }

    public void setTextAppearance(int i)
    {
        TextViewCompat.setTextAppearance(mTextView, i);
    }

    public void setTextColor(ColorStateList colorstatelist)
    {
        mTextView.setTextColor(colorstatelist);
    }

    public void setTitle(CharSequence charsequence)
    {
        mTextView.setText(charsequence);
    }

    public boolean showsIcon()
    {
        return true;
    }

}
