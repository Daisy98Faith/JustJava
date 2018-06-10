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
label0:
        {
            MenuBuilder menubuilder1 = menubuilder.getRootMenu();
            AppCompatDelegateImplV9 appcompatdelegateimplv9;
            boolean flag1;
            if (menubuilder1 != menubuilder)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            appcompatdelegateimplv9 = AppCompatDelegateImplV9.this;
            if (flag1)
            {
                menubuilder = menubuilder1;
            }
            menubuilder = appcompatdelegateimplv9.findMenuPanel(menubuilder);
            if (menubuilder != null)
            {
                if (!flag1)
                {
                    break label0;
                }
                callOnPanelClosed(((this._cls0) (menubuilder))., menubuilder, menubuilder1);
                closePanel(menubuilder, true);
            }
            return;
        }
        closePanel(menubuilder, flag);
    }

    public boolean onOpenSubMenu(MenuBuilder menubuilder)
    {
        if (menubuilder == null && mHasActionBar)
        {
            android.view.rCallback rcallback = getWindowCallback();
            if (rcallback != null && !isDestroyed())
            {
                rcallback.(108, menubuilder);
            }
        }
        return true;
    }

    ()
    {
        this$0 = AppCompatDelegateImplV9.this;
        super();
    }
}
