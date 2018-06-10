// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.support.v7.view.menu.ShowableListMenu;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            ForwardingListener, ActionMenuPresenter

class val.this._cls0 extends ForwardingListener
{

    final p this$1;
    final ActionMenuPresenter val$this$0;

    public ShowableListMenu getPopup()
    {
        if (mOverflowPopup == null)
        {
            return null;
        } else
        {
            return mOverflowPopup.p();
        }
    }

    public boolean onForwardingStarted()
    {
        showOverflowMenu();
        return true;
    }

    public boolean onForwardingStopped()
    {
        if (mPostedOpenRunnable != null)
        {
            return false;
        } else
        {
            hideOverflowMenu();
            return true;
        }
    }

    (ActionMenuPresenter actionmenupresenter)
    {
        this$1 = final_;
        val$this$0 = actionmenupresenter;
        super(View.this);
    }
}
