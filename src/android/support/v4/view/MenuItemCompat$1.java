// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.view.MenuItem;

// Referenced classes of package android.support.v4.view:
//            MenuItemCompat

static final class val.listener
    implements android.view.ExpandListener
{

    final ActionExpandListener val$listener;

    public boolean onMenuItemActionCollapse(MenuItem menuitem)
    {
        return val$listener.onMenuItemActionCollapse(menuitem);
    }

    public boolean onMenuItemActionExpand(MenuItem menuitem)
    {
        return val$listener.onMenuItemActionExpand(menuitem);
    }

    ActionExpandListener(ActionExpandListener actionexpandlistener)
    {
        val$listener = actionexpandlistener;
        super();
    }
}
