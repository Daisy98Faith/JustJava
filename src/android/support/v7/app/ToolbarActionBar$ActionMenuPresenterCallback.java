// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.DecorToolbar;

// Referenced classes of package android.support.v7.app:
//            ToolbarActionBar

private final class this._cls0
    implements android.support.v7.view.menu.
{

    private boolean mClosingActionMenu;
    final ToolbarActionBar this$0;

    public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
    {
        if (mClosingActionMenu)
        {
            return;
        }
        mClosingActionMenu = true;
        mDecorToolbar.dismissPopupMenus();
        if (mWindowCallback != null)
        {
            mWindowCallback.mClosingActionMenu(108, menubuilder);
        }
        mClosingActionMenu = false;
    }

    public boolean onOpenSubMenu(MenuBuilder menubuilder)
    {
        if (mWindowCallback != null)
        {
            mWindowCallback.rCallback(108, menubuilder);
            return true;
        } else
        {
            return false;
        }
    }

    I()
    {
        this$0 = ToolbarActionBar.this;
        super();
    }
}
