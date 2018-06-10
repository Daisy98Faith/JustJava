// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.transition.AutoTransition;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionSet;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuView;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

// Referenced classes of package android.support.design.internal:
//            TextScale, BottomNavigationItemView, BottomNavigationPresenter

public class BottomNavigationMenuView extends ViewGroup
    implements MenuView
{

    private static final long ACTIVE_ANIMATION_DURATION_MS = 115L;
    private final int mActiveItemMaxWidth;
    private BottomNavigationItemView mButtons[];
    private final int mInactiveItemMaxWidth;
    private final int mInactiveItemMinWidth;
    private int mItemBackgroundRes;
    private final int mItemHeight;
    private ColorStateList mItemIconTint;
    private final android.support.v4.util.Pools.Pool mItemPool;
    private ColorStateList mItemTextColor;
    private MenuBuilder mMenu;
    private final android.view.View.OnClickListener mOnClickListener;
    private BottomNavigationPresenter mPresenter;
    private int mSelectedItemId;
    private int mSelectedItemPosition;
    private final TransitionSet mSet;
    private boolean mShiftingMode;
    private int mTempChildWidths[];

    public BottomNavigationMenuView(Context context)
    {
        this(context, null);
    }

    public BottomNavigationMenuView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mItemPool = new android.support.v4.util.Pools.SynchronizedPool(5);
        mShiftingMode = true;
        mSelectedItemId = 0;
        mSelectedItemPosition = 0;
        context = getResources();
        mInactiveItemMaxWidth = context.getDimensionPixelSize(android.support.design.R.dimen.design_bottom_navigation_item_max_width);
        mInactiveItemMinWidth = context.getDimensionPixelSize(android.support.design.R.dimen.design_bottom_navigation_item_min_width);
        mActiveItemMaxWidth = context.getDimensionPixelSize(android.support.design.R.dimen.design_bottom_navigation_active_item_max_width);
        mItemHeight = context.getDimensionPixelSize(android.support.design.R.dimen.design_bottom_navigation_height);
        mSet = new AutoTransition();
        mSet.setOrdering(0);
        mSet.setDuration(115L);
        mSet.setInterpolator(new FastOutSlowInInterpolator());
        mSet.addTransition(new TextScale());
        mOnClickListener = new android.view.View.OnClickListener() {

            final BottomNavigationMenuView this$0;

            public void onClick(View view)
            {
                view = ((BottomNavigationItemView)view).getItemData();
                if (!mMenu.performItemAction(view, mPresenter, 0))
                {
                    view.setChecked(true);
                }
            }

            
            {
                this$0 = BottomNavigationMenuView.this;
                super();
            }
        };
        mTempChildWidths = new int[5];
    }

    private BottomNavigationItemView getNewItem()
    {
        BottomNavigationItemView bottomnavigationitemview1 = (BottomNavigationItemView)mItemPool.acquire();
        BottomNavigationItemView bottomnavigationitemview = bottomnavigationitemview1;
        if (bottomnavigationitemview1 == null)
        {
            bottomnavigationitemview = new BottomNavigationItemView(getContext());
        }
        return bottomnavigationitemview;
    }

    public void buildMenuView()
    {
        removeAllViews();
        if (mButtons != null)
        {
            BottomNavigationItemView abottomnavigationitemview[] = mButtons;
            int k = abottomnavigationitemview.length;
            for (int i = 0; i < k; i++)
            {
                BottomNavigationItemView bottomnavigationitemview1 = abottomnavigationitemview[i];
                mItemPool.release(bottomnavigationitemview1);
            }

        }
        if (mMenu.size() == 0)
        {
            mSelectedItemId = 0;
            mSelectedItemPosition = 0;
            mButtons = null;
            return;
        }
        mButtons = new BottomNavigationItemView[mMenu.size()];
        boolean flag;
        if (mMenu.size() > 3)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mShiftingMode = flag;
        for (int j = 0; j < mMenu.size(); j++)
        {
            mPresenter.setUpdateSuspended(true);
            mMenu.getItem(j).setCheckable(true);
            mPresenter.setUpdateSuspended(false);
            BottomNavigationItemView bottomnavigationitemview = getNewItem();
            mButtons[j] = bottomnavigationitemview;
            bottomnavigationitemview.setIconTintList(mItemIconTint);
            bottomnavigationitemview.setTextColor(mItemTextColor);
            bottomnavigationitemview.setItemBackground(mItemBackgroundRes);
            bottomnavigationitemview.setShiftingMode(mShiftingMode);
            bottomnavigationitemview.initialize((MenuItemImpl)mMenu.getItem(j), 0);
            bottomnavigationitemview.setItemPosition(j);
            bottomnavigationitemview.setOnClickListener(mOnClickListener);
            addView(bottomnavigationitemview);
        }

        mSelectedItemPosition = Math.min(mMenu.size() - 1, mSelectedItemPosition);
        mMenu.getItem(mSelectedItemPosition).setChecked(true);
    }

    public ColorStateList getIconTintList()
    {
        return mItemIconTint;
    }

    public int getItemBackgroundRes()
    {
        return mItemBackgroundRes;
    }

    public ColorStateList getItemTextColor()
    {
        return mItemTextColor;
    }

    public int getSelectedItemId()
    {
        return mSelectedItemId;
    }

    public int getWindowAnimations()
    {
        return 0;
    }

    public void initialize(MenuBuilder menubuilder)
    {
        mMenu = menubuilder;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        int i1 = getChildCount();
        k -= i;
        l -= j;
        j = 0;
        i = 0;
        while (i < i1) 
        {
            View view = getChildAt(i);
            if (view.getVisibility() != 8)
            {
                if (ViewCompat.getLayoutDirection(this) == 1)
                {
                    view.layout(k - j - view.getMeasuredWidth(), 0, k - j, l);
                } else
                {
                    view.layout(j, 0, view.getMeasuredWidth() + j, l);
                }
                j += view.getMeasuredWidth();
            }
            i++;
        }
    }

    protected void onMeasure(int i, int j)
    {
        j = android.view.View.MeasureSpec.getSize(i);
        int i1 = getChildCount();
        int i2 = android.view.View.MeasureSpec.makeMeasureSpec(mItemHeight, 0x40000000);
        if (mShiftingMode)
        {
            i = i1 - 1;
            int j1 = Math.min(j - mInactiveItemMinWidth * i, mActiveItemMaxWidth);
            int l1 = Math.min((j - j1) / i, mInactiveItemMaxWidth);
            j = j - j1 - l1 * i;
            i = 0;
            while (i < i1) 
            {
                int ai[] = mTempChildWidths;
                int k;
                if (i == mSelectedItemPosition)
                {
                    k = j1;
                } else
                {
                    k = l1;
                }
                ai[i] = k;
                k = j;
                if (j > 0)
                {
                    int ai1[] = mTempChildWidths;
                    ai1[i] = ai1[i] + 1;
                    k = j - 1;
                }
                i++;
                j = k;
            }
        } else
        {
            int k1;
            if (i1 == 0)
            {
                i = 1;
            } else
            {
                i = i1;
            }
            k1 = Math.min(j / i, mActiveItemMaxWidth);
            j -= k1 * i1;
            for (i = 0; i < i1;)
            {
                mTempChildWidths[i] = k1;
                int l = j;
                if (j > 0)
                {
                    int ai2[] = mTempChildWidths;
                    ai2[i] = ai2[i] + 1;
                    l = j - 1;
                }
                i++;
                j = l;
            }

        }
        j = 0;
        i = 0;
        while (i < i1) 
        {
            View view = getChildAt(i);
            if (view.getVisibility() != 8)
            {
                view.measure(android.view.View.MeasureSpec.makeMeasureSpec(mTempChildWidths[i], 0x40000000), i2);
                view.getLayoutParams().width = view.getMeasuredWidth();
                j += view.getMeasuredWidth();
            }
            i++;
        }
        setMeasuredDimension(View.resolveSizeAndState(j, android.view.View.MeasureSpec.makeMeasureSpec(j, 0x40000000), 0), View.resolveSizeAndState(mItemHeight, i2, 0));
    }

    public void setIconTintList(ColorStateList colorstatelist)
    {
        mItemIconTint = colorstatelist;
        if (mButtons != null)
        {
            BottomNavigationItemView abottomnavigationitemview[] = mButtons;
            int j = abottomnavigationitemview.length;
            int i = 0;
            while (i < j) 
            {
                abottomnavigationitemview[i].setIconTintList(colorstatelist);
                i++;
            }
        }
    }

    public void setItemBackgroundRes(int i)
    {
        mItemBackgroundRes = i;
        if (mButtons != null)
        {
            BottomNavigationItemView abottomnavigationitemview[] = mButtons;
            int k = abottomnavigationitemview.length;
            int j = 0;
            while (j < k) 
            {
                abottomnavigationitemview[j].setItemBackground(i);
                j++;
            }
        }
    }

    public void setItemTextColor(ColorStateList colorstatelist)
    {
        mItemTextColor = colorstatelist;
        if (mButtons != null)
        {
            BottomNavigationItemView abottomnavigationitemview[] = mButtons;
            int j = abottomnavigationitemview.length;
            int i = 0;
            while (i < j) 
            {
                abottomnavigationitemview[i].setTextColor(colorstatelist);
                i++;
            }
        }
    }

    public void setPresenter(BottomNavigationPresenter bottomnavigationpresenter)
    {
        mPresenter = bottomnavigationpresenter;
    }

    void tryRestoreSelectedItemId(int i)
    {
        int k = mMenu.size();
        int j = 0;
        do
        {
label0:
            {
                if (j < k)
                {
                    MenuItem menuitem = mMenu.getItem(j);
                    if (i != menuitem.getItemId())
                    {
                        break label0;
                    }
                    mSelectedItemId = i;
                    mSelectedItemPosition = j;
                    menuitem.setChecked(true);
                }
                return;
            }
            j++;
        } while (true);
    }

    public void updateMenuView()
    {
        int j = mMenu.size();
        if (j != mButtons.length)
        {
            buildMenuView();
        } else
        {
            int k = mSelectedItemId;
            for (int i = 0; i < j; i++)
            {
                mPresenter.setUpdateSuspended(true);
                MenuItem menuitem = mMenu.getItem(i);
                if (menuitem.isChecked())
                {
                    mSelectedItemId = menuitem.getItemId();
                    mSelectedItemPosition = i;
                }
                mButtons[i].initialize((MenuItemImpl)menuitem, 0);
                mPresenter.setUpdateSuspended(false);
            }

            if (k != mSelectedItemId)
            {
                TransitionManager.beginDelayedTransition(this);
                return;
            }
        }
    }


}
