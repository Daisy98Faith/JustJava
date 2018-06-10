// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

// Referenced classes of package android.support.v7.widget:
//            ShareActionProvider, ActivityChooserModel

private class this._cls0
    implements android.view.lickListener
{

    final ShareActionProvider this$0;

    public boolean onMenuItemClick(MenuItem menuitem)
    {
        menuitem = ActivityChooserModel.get(mContext, mShareHistoryFileName).chooseActivity(menuitem.getItemId());
        if (menuitem != null)
        {
            String s = menuitem.getAction();
            if ("android.intent.action.SEND".equals(s) || "android.intent.action.SEND_MULTIPLE".equals(s))
            {
                updateIntent(menuitem);
            }
            mContext.startActivity(menuitem);
        }
        return true;
    }

    ()
    {
        this$0 = ShareActionProvider.this;
        super();
    }
}
