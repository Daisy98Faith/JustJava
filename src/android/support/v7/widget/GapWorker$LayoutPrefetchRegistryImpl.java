// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import java.util.Arrays;

// Referenced classes of package android.support.v7.widget:
//            GapWorker, RecyclerView, AdapterHelper

static class etchRegistry
    implements etchRegistry
{

    int mCount;
    int mPrefetchArray[];
    int mPrefetchDx;
    int mPrefetchDy;

    public void addPosition(int i, int j)
    {
        int k;
        if (i < 0)
        {
            throw new IllegalArgumentException("Layout positions must be non-negative");
        }
        if (j < 0)
        {
            throw new IllegalArgumentException("Pixel distance must be non-negative");
        }
        k = mCount * 2;
        if (mPrefetchArray != null) goto _L2; else goto _L1
_L1:
        mPrefetchArray = new int[4];
        Arrays.fill(mPrefetchArray, -1);
_L4:
        mPrefetchArray[k] = i;
        mPrefetchArray[k + 1] = j;
        mCount = mCount + 1;
        return;
_L2:
        if (k >= mPrefetchArray.length)
        {
            int ai[] = mPrefetchArray;
            mPrefetchArray = new int[k * 2];
            System.arraycopy(ai, 0, mPrefetchArray, 0, ai.length);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    void clearPrefetchPositions()
    {
        if (mPrefetchArray != null)
        {
            Arrays.fill(mPrefetchArray, -1);
        }
        mCount = 0;
    }

    void collectPrefetchPositionsFromView(RecyclerView recyclerview, boolean flag)
    {
        etchRegistry etchregistry;
        mCount = 0;
        if (mPrefetchArray != null)
        {
            Arrays.fill(mPrefetchArray, -1);
        }
        etchregistry = recyclerview.mLayout;
        if (recyclerview.mAdapter == null || etchregistry == null || !etchregistry.etchEnabled()) goto _L2; else goto _L1
_L1:
        if (!flag) goto _L4; else goto _L3
_L3:
        if (!recyclerview.mAdapterHelper.hasPendingUpdates())
        {
            etchregistry.tialPrefetchPositions(recyclerview.mAdapter.tialPrefetchPositions(), this);
        }
_L6:
        if (mCount > etchregistry.axCountObserved)
        {
            etchregistry.axCountObserved = mCount;
            etchregistry.axObservedInInitialPrefetch = flag;
            recyclerview.mRecycler.Size();
        }
_L2:
        return;
_L4:
        if (!recyclerview.hasPendingAdapterUpdates())
        {
            etchregistry.acentPrefetchPositions(mPrefetchDx, mPrefetchDy, recyclerview.mState, this);
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    boolean lastPrefetchIncludedPosition(int i)
    {
        if (mPrefetchArray != null)
        {
            int k = mCount;
            for (int j = 0; j < k * 2; j += 2)
            {
                if (mPrefetchArray[j] == i)
                {
                    return true;
                }
            }

        }
        return false;
    }

    void setPrefetchVector(int i, int j)
    {
        mPrefetchDx = i;
        mPrefetchDy = j;
    }

    etchRegistry()
    {
    }
}
