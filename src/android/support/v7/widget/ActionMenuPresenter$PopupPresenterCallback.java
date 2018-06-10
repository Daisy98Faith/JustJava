// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.SubMenuBuilder;
import android.view.MenuItem;

// Referenced classes of package android.support.v7.widget:
//            ActionMenuPresenter

private class this._cls0
    implements android.support.v7.view.menu.s._cls0
{

    final ActionMenuPresenter this$0;

    public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
    {
        if (menubuilder instanceof SubMenuBuilder)
        {
            menubuilder.getRootMenu().close(false);
        }
        android.support.v7.view.menu.s._cls0 _lcls0 = getCallback();
        if (_lcls0 != null)
        {
            _lcls0._mth0(menubuilder, flag);
        }
    }

    public boolean onOpenSubMenu(MenuBuilder menubuilder)
    {
        if (menubuilder == null)
        {
            return false;
        }
        mOpenSubMenuId = ((SubMenuBuilder)menubuilder).getItem().getItemId();
        android.support.v7.view.menu.s._cls0 _lcls0 = getCallback();
        boolean flag;
        if (_lcls0 != null)
        {
            flag = _lcls0._mth0(menubuilder);
        } else
        {
            flag = false;
        }
        return flag;
    }

    ()
    {
        this$0 = ActionMenuPresenter.this;
        super();
    }
}
