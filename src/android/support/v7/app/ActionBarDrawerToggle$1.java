// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.view.View;

// Referenced classes of package android.support.v7.app:
//            ActionBarDrawerToggle

class this._cls0
    implements android.view.rToggle._cls1
{

    final ActionBarDrawerToggle this$0;

    public void onClick(View view)
    {
        if (mDrawerIndicatorEnabled)
        {
            toggle();
        } else
        if (mToolbarNavigationClickListener != null)
        {
            mToolbarNavigationClickListener.lick(view);
            return;
        }
    }

    ()
    {
        this$0 = ActionBarDrawerToggle.this;
        super();
    }
}
