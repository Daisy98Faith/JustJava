// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.database.DataSetObserver;

// Referenced classes of package android.support.design.widget:
//            TabLayout

private class this._cls0 extends DataSetObserver
{

    final TabLayout this$0;

    public void onChanged()
    {
        populateFromPagerAdapter();
    }

    public void onInvalidated()
    {
        populateFromPagerAdapter();
    }

    ()
    {
        this$0 = TabLayout.this;
        super();
    }
}
