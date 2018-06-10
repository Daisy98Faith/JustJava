// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.os.BuildCompat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

// Referenced classes of package android.support.v4.view:
//            ActionProvider

public final class MenuItemCompat
{
    static class MenuItemCompatApi26Impl extends MenuItemCompatBaseImpl
    {

        public CharSequence getContentDescription(MenuItem menuitem)
        {
            return menuitem.getContentDescription();
        }

        public CharSequence getTooltipText(MenuItem menuitem)
        {
            return menuitem.getTooltipText();
        }

        public void setContentDescription(MenuItem menuitem, CharSequence charsequence)
        {
            menuitem.setContentDescription(charsequence);
        }

        public void setTooltipText(MenuItem menuitem, CharSequence charsequence)
        {
            menuitem.setTooltipText(charsequence);
        }

        MenuItemCompatApi26Impl()
        {
        }
    }

    static class MenuItemCompatBaseImpl
        implements MenuVersionImpl
    {

        public CharSequence getContentDescription(MenuItem menuitem)
        {
            return null;
        }

        public CharSequence getTooltipText(MenuItem menuitem)
        {
            return null;
        }

        public void setContentDescription(MenuItem menuitem, CharSequence charsequence)
        {
        }

        public void setTooltipText(MenuItem menuitem, CharSequence charsequence)
        {
        }

        MenuItemCompatBaseImpl()
        {
        }
    }

    static interface MenuVersionImpl
    {

        public abstract CharSequence getContentDescription(MenuItem menuitem);

        public abstract CharSequence getTooltipText(MenuItem menuitem);

        public abstract void setContentDescription(MenuItem menuitem, CharSequence charsequence);

        public abstract void setTooltipText(MenuItem menuitem, CharSequence charsequence);
    }

    public static interface OnActionExpandListener
    {

        public abstract boolean onMenuItemActionCollapse(MenuItem menuitem);

        public abstract boolean onMenuItemActionExpand(MenuItem menuitem);
    }


    static final MenuVersionImpl IMPL;
    public static final int SHOW_AS_ACTION_ALWAYS = 2;
    public static final int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;
    public static final int SHOW_AS_ACTION_IF_ROOM = 1;
    public static final int SHOW_AS_ACTION_NEVER = 0;
    public static final int SHOW_AS_ACTION_WITH_TEXT = 4;
    private static final String TAG = "MenuItemCompat";

    private MenuItemCompat()
    {
    }

    public static boolean collapseActionView(MenuItem menuitem)
    {
        return menuitem.collapseActionView();
    }

    public static boolean expandActionView(MenuItem menuitem)
    {
        return menuitem.expandActionView();
    }

    public static ActionProvider getActionProvider(MenuItem menuitem)
    {
        if (menuitem instanceof SupportMenuItem)
        {
            return ((SupportMenuItem)menuitem).getSupportActionProvider();
        } else
        {
            Log.w("MenuItemCompat", "getActionProvider: item does not implement SupportMenuItem; returning null");
            return null;
        }
    }

    public static View getActionView(MenuItem menuitem)
    {
        return menuitem.getActionView();
    }

    public static CharSequence getContentDescription(MenuItem menuitem)
    {
        if (menuitem instanceof SupportMenuItem)
        {
            return ((SupportMenuItem)menuitem).getContentDescription();
        } else
        {
            return IMPL.getContentDescription(menuitem);
        }
    }

    public static CharSequence getTooltipText(MenuItem menuitem)
    {
        if (menuitem instanceof SupportMenuItem)
        {
            return ((SupportMenuItem)menuitem).getTooltipText();
        } else
        {
            return IMPL.getTooltipText(menuitem);
        }
    }

    public static boolean isActionViewExpanded(MenuItem menuitem)
    {
        return menuitem.isActionViewExpanded();
    }

    public static MenuItem setActionProvider(MenuItem menuitem, ActionProvider actionprovider)
    {
        if (menuitem instanceof SupportMenuItem)
        {
            return ((SupportMenuItem)menuitem).setSupportActionProvider(actionprovider);
        } else
        {
            Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
            return menuitem;
        }
    }

    public static MenuItem setActionView(MenuItem menuitem, int i)
    {
        return menuitem.setActionView(i);
    }

    public static MenuItem setActionView(MenuItem menuitem, View view)
    {
        return menuitem.setActionView(view);
    }

    public static void setContentDescription(MenuItem menuitem, CharSequence charsequence)
    {
        if (menuitem instanceof SupportMenuItem)
        {
            ((SupportMenuItem)menuitem).setContentDescription(charsequence);
            return;
        } else
        {
            IMPL.setContentDescription(menuitem, charsequence);
            return;
        }
    }

    public static MenuItem setOnActionExpandListener(MenuItem menuitem, OnActionExpandListener onactionexpandlistener)
    {
        return menuitem.setOnActionExpandListener(new android.view.MenuItem.OnActionExpandListener(onactionexpandlistener) {

            final OnActionExpandListener val$listener;

            public boolean onMenuItemActionCollapse(MenuItem menuitem1)
            {
                return listener.onMenuItemActionCollapse(menuitem1);
            }

            public boolean onMenuItemActionExpand(MenuItem menuitem1)
            {
                return listener.onMenuItemActionExpand(menuitem1);
            }

            
            {
                listener = onactionexpandlistener;
                super();
            }
        });
    }

    public static void setShowAsAction(MenuItem menuitem, int i)
    {
        menuitem.setShowAsAction(i);
    }

    public static void setTooltipText(MenuItem menuitem, CharSequence charsequence)
    {
        if (menuitem instanceof SupportMenuItem)
        {
            ((SupportMenuItem)menuitem).setTooltipText(charsequence);
            return;
        } else
        {
            IMPL.setTooltipText(menuitem, charsequence);
            return;
        }
    }

    static 
    {
        if (BuildCompat.isAtLeastO())
        {
            IMPL = new MenuItemCompatApi26Impl();
        } else
        {
            IMPL = new MenuItemCompatBaseImpl();
        }
    }
}
