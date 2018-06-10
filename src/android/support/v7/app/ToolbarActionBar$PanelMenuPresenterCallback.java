// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.support.v7.view.menu.MenuBuilder;

// Referenced classes of package android.support.v7.app:
//            ToolbarActionBar

private final class this._cls0
    implements android.support.v7.view.menu.
{

    final ToolbarActionBar this$0;

    public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
    {
        if (mWindowCallback != null)
        {
            mWindowCallback.rCallback(0, menubuilder);
        }
    }

    public boolean onOpenSubMenu(MenuBuilder menubuilder)
    {
        if (menubuilder == null && mWindowCallback != null)
        {
            mWindowCallback.rCallback(0, menubuilder);
        }
        return true;
    }

    ()
    {
        this$0 = ToolbarActionBar.this;
        super();
    }
}
