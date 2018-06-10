// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.SubMenuBuilder;
import android.view.ViewGroup;

// Referenced classes of package android.support.design.internal:
//            BottomNavigationMenuView

public class BottomNavigationPresenter
    implements MenuPresenter
{
    static class SavedState
        implements Parcelable
    {

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        };
        int selectedItemId;

        public int describeContents()
        {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(selectedItemId);
        }


        SavedState()
        {
        }

        SavedState(Parcel parcel)
        {
            selectedItemId = parcel.readInt();
        }
    }


    private int mId;
    private MenuBuilder mMenu;
    private BottomNavigationMenuView mMenuView;
    private boolean mUpdateSuspended;

    public BottomNavigationPresenter()
    {
        mUpdateSuspended = false;
    }

    public boolean collapseItemActionView(MenuBuilder menubuilder, MenuItemImpl menuitemimpl)
    {
        return false;
    }

    public boolean expandItemActionView(MenuBuilder menubuilder, MenuItemImpl menuitemimpl)
    {
        return false;
    }

    public boolean flagActionItems()
    {
        return false;
    }

    public int getId()
    {
        return mId;
    }

    public MenuView getMenuView(ViewGroup viewgroup)
    {
        return mMenuView;
    }

    public void initForMenu(Context context, MenuBuilder menubuilder)
    {
        mMenuView.initialize(mMenu);
        mMenu = menubuilder;
    }

    public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
    {
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        if (parcelable instanceof SavedState)
        {
            mMenuView.tryRestoreSelectedItemId(((SavedState)parcelable).selectedItemId);
        }
    }

    public Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState();
        savedstate.selectedItemId = mMenuView.getSelectedItemId();
        return savedstate;
    }

    public boolean onSubMenuSelected(SubMenuBuilder submenubuilder)
    {
        return false;
    }

    public void setBottomNavigationMenuView(BottomNavigationMenuView bottomnavigationmenuview)
    {
        mMenuView = bottomnavigationmenuview;
    }

    public void setCallback(android.support.v7.view.menu.MenuPresenter.Callback callback)
    {
    }

    public void setId(int i)
    {
        mId = i;
    }

    public void setUpdateSuspended(boolean flag)
    {
        mUpdateSuspended = flag;
    }

    public void updateMenuView(boolean flag)
    {
        if (mUpdateSuspended)
        {
            return;
        }
        if (flag)
        {
            mMenuView.buildMenuView();
            return;
        } else
        {
            mMenuView.updateMenuView();
            return;
        }
    }
}
