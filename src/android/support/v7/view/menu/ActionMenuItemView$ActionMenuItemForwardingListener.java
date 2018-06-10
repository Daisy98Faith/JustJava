// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.support.v7.widget.ForwardingListener;

// Referenced classes of package android.support.v7.view.menu:
//            ActionMenuItemView, ShowableListMenu

private class this._cls0 extends ForwardingListener
{

    final ActionMenuItemView this$0;

    public ShowableListMenu getPopup()
    {
        if (mPopupCallback != null)
        {
            return mPopupCallback._mth0();
        } else
        {
            return null;
        }
    }

    protected boolean onForwardingStarted()
    {
        boolean flag1 = false;
        boolean flag = flag1;
        if (mItemInvoker != null)
        {
            flag = flag1;
            if (mItemInvoker._mth0(mItemData))
            {
                ShowableListMenu showablelistmenu = getPopup();
                flag = flag1;
                if (showablelistmenu != null)
                {
                    flag = flag1;
                    if (showablelistmenu.isShowing())
                    {
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }

    public ()
    {
        this$0 = ActionMenuItemView.this;
        super(ActionMenuItemView.this);
    }
}
