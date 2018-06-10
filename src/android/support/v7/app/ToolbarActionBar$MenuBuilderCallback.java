// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.DecorToolbar;
import android.view.MenuItem;

// Referenced classes of package android.support.v7.app:
//            ToolbarActionBar

private final class this._cls0
    implements android.support.v7.view.menu.
{

    final ToolbarActionBar this$0;

    public boolean onMenuItemSelected(MenuBuilder menubuilder, MenuItem menuitem)
    {
        return false;
    }

    public void onMenuModeChange(MenuBuilder menubuilder)
    {
        if (mWindowCallback != null)
        {
            if (mDecorToolbar.isOverflowMenuShowing())
            {
                mWindowCallback.lback(108, menubuilder);
            } else
            if (mWindowCallback.lback(0, null, menubuilder))
            {
                mWindowCallback.lback(108, menubuilder);
                return;
            }
        }
    }

    A()
    {
        this$0 = ToolbarActionBar.this;
        super();
    }
}
