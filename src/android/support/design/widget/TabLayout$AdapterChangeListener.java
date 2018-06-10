// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

// Referenced classes of package android.support.design.widget:
//            TabLayout

private class this._cls0
    implements android.support.v4.view.r
{

    private boolean mAutoRefresh;
    final TabLayout this$0;

    public void onAdapterChanged(ViewPager viewpager, PagerAdapter pageradapter, PagerAdapter pageradapter1)
    {
        if (mViewPager == viewpager)
        {
            setPagerAdapter(pageradapter1, mAutoRefresh);
        }
    }

    void setAutoRefresh(boolean flag)
    {
        mAutoRefresh = flag;
    }

    ()
    {
        this$0 = TabLayout.this;
        super();
    }
}
