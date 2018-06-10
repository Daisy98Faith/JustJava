// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            ScrollingTabContainerView, LinearLayoutCompat

private class this._cls0
    implements android.view.lickListener
{

    final ScrollingTabContainerView this$0;

    public void onClick(View view)
    {
        ((this._cls0)view)._mth0().();
        int j = mTabLayout.getChildCount();
        int i = 0;
        while (i < j) 
        {
            View view1 = mTabLayout.getChildAt(i);
            boolean flag;
            if (view1 == view)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            view1.setSelected(flag);
            i++;
        }
    }

    ()
    {
        this$0 = ScrollingTabContainerView.this;
        super();
    }
}
