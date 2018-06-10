// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.view.menu.ListMenuItemView;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;

// Referenced classes of package android.support.v7.widget:
//            DropDownListView, MenuPopupWindow, MenuItemHoverListener

public static class mRetreatKey extends DropDownListView
{

    final int mAdvanceKey;
    private MenuItemHoverListener mHoverListener;
    private MenuItem mHoveredMenuItem;
    final int mRetreatKey;

    public void clearSelection()
    {
        setSelection(-1);
    }

    public volatile boolean hasFocus()
    {
        return super.hasFocus();
    }

    public volatile boolean hasWindowFocus()
    {
        return super.hasWindowFocus();
    }

    public volatile boolean isFocused()
    {
        return super.isFocused();
    }

    public volatile boolean isInTouchMode()
    {
        return super.isInTouchMode();
    }

    public volatile boolean onForwardedEvent(MotionEvent motionevent, int i)
    {
        return super.onForwardedEvent(motionevent, i);
    }

    public boolean onHoverEvent(MotionEvent motionevent)
    {
        if (mHoverListener != null)
        {
            Object obj = getAdapter();
            MenuItemImpl menuitemimpl;
            MenuItem menuitem;
            int i;
            if (obj instanceof HeaderViewListAdapter)
            {
                obj = (HeaderViewListAdapter)obj;
                i = ((HeaderViewListAdapter) (obj)).getHeadersCount();
                obj = (MenuAdapter)((HeaderViewListAdapter) (obj)).getWrappedAdapter();
            } else
            {
                i = 0;
                obj = (MenuAdapter)obj;
            }
            menuitem = null;
            menuitemimpl = menuitem;
            if (motionevent.getAction() != 10)
            {
                int j = pointToPosition((int)motionevent.getX(), (int)motionevent.getY());
                menuitemimpl = menuitem;
                if (j != -1)
                {
                    i = j - i;
                    menuitemimpl = menuitem;
                    if (i >= 0)
                    {
                        menuitemimpl = menuitem;
                        if (i < ((MenuAdapter) (obj)).getCount())
                        {
                            menuitemimpl = ((MenuAdapter) (obj)).getItem(i);
                        }
                    }
                }
            }
            menuitem = mHoveredMenuItem;
            if (menuitem != menuitemimpl)
            {
                obj = ((MenuAdapter) (obj)).getAdapterMenu();
                if (menuitem != null)
                {
                    mHoverListener.onItemHoverExit(((MenuBuilder) (obj)), menuitem);
                }
                mHoveredMenuItem = menuitemimpl;
                if (menuitemimpl != null)
                {
                    mHoverListener.onItemHoverEnter(((MenuBuilder) (obj)), menuitemimpl);
                }
            }
        }
        return super.onHoverEvent(motionevent);
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        ListMenuItemView listmenuitemview = (ListMenuItemView)getSelectedView();
        if (listmenuitemview != null && i == mAdvanceKey)
        {
            if (listmenuitemview.isEnabled() && listmenuitemview.getItemData().hasSubMenu())
            {
                performItemClick(listmenuitemview, getSelectedItemPosition(), getSelectedItemId());
            }
            return true;
        }
        if (listmenuitemview != null && i == mRetreatKey)
        {
            setSelection(-1);
            ((MenuAdapter)getAdapter()).getAdapterMenu().close(false);
            return true;
        } else
        {
            return super.onKeyDown(i, keyevent);
        }
    }

    public void setHoverListener(MenuItemHoverListener menuitemhoverlistener)
    {
        mHoverListener = menuitemhoverlistener;
    }

    public (Context context, boolean flag)
    {
        super(context, flag);
        context = context.getResources().getConfiguration();
        if (android.os.> >= 17 && 1 == context.getLayoutDirection())
        {
            mAdvanceKey = 21;
            mRetreatKey = 22;
            return;
        } else
        {
            mAdvanceKey = 22;
            mRetreatKey = 21;
            return;
        }
    }
}
