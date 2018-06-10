// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.SubMenuBuilder;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            ActionMenuPresenter

private class ack extends MenuPopupHelper
{

    final ActionMenuPresenter this$0;

    protected void onDismiss()
    {
        mActionButtonPopup = null;
        mOpenSubMenuId = 0;
        super.onDismiss();
    }

    public (Context context, SubMenuBuilder submenubuilder, View view)
    {
        this$0 = ActionMenuPresenter.this;
        super(context, submenubuilder, view, false, android.support.v7.appcompat.s._fld0);
        if (!((MenuItemImpl)submenubuilder.getItem()).isActionButton())
        {
            if (mOverflowButton == null)
            {
                context = (View)ActionMenuPresenter.access$200(ActionMenuPresenter.this);
            } else
            {
                context = mOverflowButton;
            }
            setAnchorView(context);
        }
        setPresenterCallback(mPopupPresenterCallback);
    }
}
