// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import java.util.ArrayList;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView

static class mBindRunningAverageNs
{

    long mBindRunningAverageNs;
    long mCreateRunningAverageNs;
    int mMaxScrap;
    ArrayList mScrapHeap;

    ()
    {
        mScrapHeap = new ArrayList();
        mMaxScrap = 5;
        mCreateRunningAverageNs = 0L;
        mBindRunningAverageNs = 0L;
    }
}
