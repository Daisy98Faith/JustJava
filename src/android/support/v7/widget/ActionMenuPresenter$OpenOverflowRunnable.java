// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.support.v7.view.menu.MenuBuilder;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            ActionMenuPresenter

private class mPopup
    implements Runnable
{

    private mPopup mPopup;
    final ActionMenuPresenter this$0;

    public void run()
    {
        if (ActionMenuPresenter.access$300(ActionMenuPresenter.this) != null)
        {
            ActionMenuPresenter.access$400(ActionMenuPresenter.this).changeMenuMode();
        }
        View view = (View)ActionMenuPresenter.access$500(ActionMenuPresenter.this);
        if (view != null && view.getWindowToken() != null && mPopup.())
        {
            mOverflowPopup = mPopup;
        }
        mPostedOpenRunnable = null;
    }

    public ( )
    {
        this$0 = ActionMenuPresenter.this;
        super();
        mPopup = ;
    }
}
