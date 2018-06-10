// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            ActionMenuPresenter

private class rCallback extends MenuPopupHelper
{

    final ActionMenuPresenter this$0;

    protected void onDismiss()
    {
        if (ActionMenuPresenter.access$000(ActionMenuPresenter.this) != null)
        {
            ActionMenuPresenter.access$100(ActionMenuPresenter.this).close();
        }
        mOverflowPopup = null;
        super.onDismiss();
    }

    public A(Context context, MenuBuilder menubuilder, View view, boolean flag)
    {
        this$0 = ActionMenuPresenter.this;
        super(context, menubuilder, view, flag, android.support.v7.appcompat.s._fld0);
        setGravity(0x800005);
        setPresenterCallback(mPopupPresenterCallback);
    }
}
