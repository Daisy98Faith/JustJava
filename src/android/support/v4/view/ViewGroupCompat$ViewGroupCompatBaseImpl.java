// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.view.ViewGroup;

// Referenced classes of package android.support.v4.view:
//            ViewGroupCompat, NestedScrollingParent

static class 
{

    public int getLayoutMode(ViewGroup viewgroup)
    {
        return 0;
    }

    public int getNestedScrollAxes(ViewGroup viewgroup)
    {
        if (viewgroup instanceof NestedScrollingParent)
        {
            return ((NestedScrollingParent)viewgroup).getNestedScrollAxes();
        } else
        {
            return 0;
        }
    }

    public boolean isTransitionGroup(ViewGroup viewgroup)
    {
        return false;
    }

    public void setLayoutMode(ViewGroup viewgroup, int i)
    {
    }

    public void setTransitionGroup(ViewGroup viewgroup, boolean flag)
    {
    }

    ()
    {
    }
}
