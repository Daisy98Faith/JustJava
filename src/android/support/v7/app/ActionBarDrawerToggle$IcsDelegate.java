// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;

// Referenced classes of package android.support.v7.app:
//            ActionBarDrawerToggle

private static class egate extends egate
{

    public Context getActionBarThemedContext()
    {
        ActionBar actionbar = mActivity.getActionBar();
        if (actionbar != null)
        {
            return actionbar.getThemedContext();
        } else
        {
            return mActivity;
        }
    }

    egate(Activity activity)
    {
        super(activity);
    }
}
