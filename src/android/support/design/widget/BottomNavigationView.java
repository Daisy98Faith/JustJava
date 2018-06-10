// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.internal.BottomNavigationPresenter;
import android.support.v4.content.ContextCompat;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

// Referenced classes of package android.support.design.widget:
//            ThemeUtils

public class BottomNavigationView extends FrameLayout
{
    public static interface OnNavigationItemReselectedListener
    {

        public abstract void onNavigationItemReselected(MenuItem menuitem);
    }

    public static interface OnNavigationItemSelectedListener
    {

        public abstract boolean onNavigationItemSelected(MenuItem menuitem);
    }

    static class SavedState extends AbsSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks() {

            public SavedState createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return new SavedState(parcel, classloader);
            }

            public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return createFromParcel(parcel, classloader);
            }

            public SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        });
        Bundle menuPresenterState;

        private void readFromParcel(Parcel parcel, ClassLoader classloader)
        {
            menuPresenterState = parcel.readBundle(classloader);
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(menuPresenterState);
        }


        public SavedState(Parcel parcel, ClassLoader classloader)
        {
            super(parcel, classloader);
            readFromParcel(parcel, classloader);
        }

        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }


    private static final int CHECKED_STATE_SET[] = {
        0x10100a0
    };
    private static final int DISABLED_STATE_SET[] = {
        0xfefeff62
    };
    private static final int MENU_PRESENTER_ID = 1;
    private final MenuBuilder mMenu;
    private MenuInflater mMenuInflater;
    private final BottomNavigationMenuView mMenuView;
    private final BottomNavigationPresenter mPresenter;
    private OnNavigationItemReselectedListener mReselectedListener;
    private OnNavigationItemSelectedListener mSelectedListener;

    public BottomNavigationView(Context context)
    {
        this(context, null);
    }

    public BottomNavigationView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public BottomNavigationView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mPresenter = new BottomNavigationPresenter();
        ThemeUtils.checkAppCompatTheme(context);
        mMenu = new BottomNavigationMenu(context);
        mMenuView = new BottomNavigationMenuView(context);
        android.widget.FrameLayout.LayoutParams layoutparams = new android.widget.FrameLayout.LayoutParams(-2, -2);
        layoutparams.gravity = 17;
        mMenuView.setLayoutParams(layoutparams);
        mPresenter.setBottomNavigationMenuView(mMenuView);
        mPresenter.setId(1);
        mMenuView.setPresenter(mPresenter);
        mMenu.addMenuPresenter(mPresenter);
        mPresenter.initForMenu(getContext(), mMenu);
        attributeset = TintTypedArray.obtainStyledAttributes(context, attributeset, android.support.design.R.styleable.BottomNavigationView, i, android.support.design.R.style.Widget_Design_BottomNavigationView);
        if (attributeset.hasValue(android.support.design.R.styleable.BottomNavigationView_itemIconTint))
        {
            mMenuView.setIconTintList(attributeset.getColorStateList(android.support.design.R.styleable.BottomNavigationView_itemIconTint));
        } else
        {
            mMenuView.setIconTintList(createDefaultColorStateList(0x1010038));
        }
        if (attributeset.hasValue(android.support.design.R.styleable.BottomNavigationView_itemTextColor))
        {
            mMenuView.setItemTextColor(attributeset.getColorStateList(android.support.design.R.styleable.BottomNavigationView_itemTextColor));
        } else
        {
            mMenuView.setItemTextColor(createDefaultColorStateList(0x1010038));
        }
        if (attributeset.hasValue(android.support.design.R.styleable.BottomNavigationView_elevation))
        {
            ViewCompat.setElevation(this, attributeset.getDimensionPixelSize(android.support.design.R.styleable.BottomNavigationView_elevation, 0));
        }
        i = attributeset.getResourceId(android.support.design.R.styleable.BottomNavigationView_itemBackground, 0);
        mMenuView.setItemBackgroundRes(i);
        if (attributeset.hasValue(android.support.design.R.styleable.BottomNavigationView_menu))
        {
            inflateMenu(attributeset.getResourceId(android.support.design.R.styleable.BottomNavigationView_menu, 0));
        }
        attributeset.recycle();
        addView(mMenuView, layoutparams);
        if (android.os.Build.VERSION.SDK_INT < 21)
        {
            addCompatibilityTopDivider(context);
        }
        mMenu.setCallback(new android.support.v7.view.menu.MenuBuilder.Callback() {

            final BottomNavigationView this$0;

            public boolean onMenuItemSelected(MenuBuilder menubuilder, MenuItem menuitem)
            {
                if (mReselectedListener != null && menuitem.getItemId() == getSelectedItemId())
                {
                    mReselectedListener.onNavigationItemReselected(menuitem);
                } else
                if (mSelectedListener == null || mSelectedListener.onNavigationItemSelected(menuitem))
                {
                    return false;
                }
                return true;
            }

            public void onMenuModeChange(MenuBuilder menubuilder)
            {
            }

            
            {
                this$0 = BottomNavigationView.this;
                super();
            }
        });
    }

    private void addCompatibilityTopDivider(Context context)
    {
        View view = new View(context);
        view.setBackgroundColor(ContextCompat.getColor(context, android.support.design.R.color.design_bottom_navigation_shadow_color));
        view.setLayoutParams(new android.widget.FrameLayout.LayoutParams(-1, getResources().getDimensionPixelSize(android.support.design.R.dimen.design_bottom_navigation_shadow_height)));
        addView(view);
    }

    private ColorStateList createDefaultColorStateList(int i)
    {
        TypedValue typedvalue = new TypedValue();
        if (getContext().getTheme().resolveAttribute(i, typedvalue, true))
        {
            ColorStateList colorstatelist = AppCompatResources.getColorStateList(getContext(), typedvalue.resourceId);
            if (getContext().getTheme().resolveAttribute(android.support.v7.appcompat.R.attr.colorPrimary, typedvalue, true))
            {
                i = typedvalue.data;
                int j = colorstatelist.getDefaultColor();
                int ai[] = DISABLED_STATE_SET;
                int ai1[] = CHECKED_STATE_SET;
                int ai2[] = EMPTY_STATE_SET;
                int k = colorstatelist.getColorForState(DISABLED_STATE_SET, j);
                return new ColorStateList(new int[][] {
                    ai, ai1, ai2
                }, new int[] {
                    k, i, j
                });
            }
        }
        return null;
    }

    private MenuInflater getMenuInflater()
    {
        if (mMenuInflater == null)
        {
            mMenuInflater = new SupportMenuInflater(getContext());
        }
        return mMenuInflater;
    }

    public int getItemBackgroundResource()
    {
        return mMenuView.getItemBackgroundRes();
    }

    public ColorStateList getItemIconTintList()
    {
        return mMenuView.getIconTintList();
    }

    public ColorStateList getItemTextColor()
    {
        return mMenuView.getItemTextColor();
    }

    public int getMaxItemCount()
    {
        return 5;
    }

    public Menu getMenu()
    {
        return mMenu;
    }

    public int getSelectedItemId()
    {
        return mMenuView.getSelectedItemId();
    }

    public void inflateMenu(int i)
    {
        mPresenter.setUpdateSuspended(true);
        getMenuInflater().inflate(i, mMenu);
        mPresenter.setUpdateSuspended(false);
        mPresenter.updateMenuView(true);
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        if (!(parcelable instanceof SavedState))
        {
            super.onRestoreInstanceState(parcelable);
            return;
        } else
        {
            parcelable = (SavedState)parcelable;
            super.onRestoreInstanceState(parcelable.getSuperState());
            mMenu.restorePresenterStates(((SavedState) (parcelable)).menuPresenterState);
            return;
        }
    }

    protected Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        savedstate.menuPresenterState = new Bundle();
        mMenu.savePresenterStates(savedstate.menuPresenterState);
        return savedstate;
    }

    public void setItemBackgroundResource(int i)
    {
        mMenuView.setItemBackgroundRes(i);
    }

    public void setItemIconTintList(ColorStateList colorstatelist)
    {
        mMenuView.setIconTintList(colorstatelist);
    }

    public void setItemTextColor(ColorStateList colorstatelist)
    {
        mMenuView.setItemTextColor(colorstatelist);
    }

    public void setOnNavigationItemReselectedListener(OnNavigationItemReselectedListener onnavigationitemreselectedlistener)
    {
        mReselectedListener = onnavigationitemreselectedlistener;
    }

    public void setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener onnavigationitemselectedlistener)
    {
        mSelectedListener = onnavigationitemselectedlistener;
    }

    public void setSelectedItemId(int i)
    {
        MenuItem menuitem = mMenu.findItem(i);
        if (menuitem != null && !mMenu.performItemAction(menuitem, mPresenter, 0))
        {
            menuitem.setChecked(true);
        }
    }



}
