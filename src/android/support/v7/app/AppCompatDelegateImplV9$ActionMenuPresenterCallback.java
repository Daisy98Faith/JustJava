// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.support.v7.view.menu.MenuBuilder;

// Referenced classes of package android.support.v7.app:
//            AppCompatDelegateImplV9

private final class this._cls0
    implements android.support.v7.view.menu.
{

    final AppCompatDelegateImplV9 this$0;

    public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
    {
        checkCloseActionMenu(menubuilder);
    }

    public boolean onOpenSubMenu(MenuBuilder menubuilder)
    {
        android.view.rCallback rcallback = getWindowCallback();
        if (rcallback != null)
        {
            rcallback.rCallback(108, menubuilder);
        }
        return true;
    }

    ()
    {
        this$0 = AppCompatDelegateImplV9.this;
        super();
    }
}
