// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.support.v4.view.ViewCompat;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView, AdapterHelper

private class > extends >
{

    final RecyclerView this$0;

    public void onChanged()
    {
        assertNotInLayoutOrScroll(null);
        mState. = true;
        setDataSetChangedAfterLayout();
        if (!mAdapterHelper.hasPendingUpdates())
        {
            requestLayout();
        }
    }

    public void onItemRangeChanged(int i, int j, Object obj)
    {
        assertNotInLayoutOrScroll(null);
        if (mAdapterHelper.onItemRangeChanged(i, j, obj))
        {
            triggerUpdateProcessor();
        }
    }

    public void onItemRangeInserted(int i, int j)
    {
        assertNotInLayoutOrScroll(null);
        if (mAdapterHelper.onItemRangeInserted(i, j))
        {
            triggerUpdateProcessor();
        }
    }

    public void onItemRangeMoved(int i, int j, int k)
    {
        assertNotInLayoutOrScroll(null);
        if (mAdapterHelper.onItemRangeMoved(i, j, k))
        {
            triggerUpdateProcessor();
        }
    }

    public void onItemRangeRemoved(int i, int j)
    {
        assertNotInLayoutOrScroll(null);
        if (mAdapterHelper.onItemRangeRemoved(i, j))
        {
            triggerUpdateProcessor();
        }
    }

    void triggerUpdateProcessor()
    {
        if (RecyclerView.POST_UPDATES_ON_ANIMATION && mHasFixedSize && mIsAttached)
        {
            ViewCompat.postOnAnimation(RecyclerView.this, mUpdateChildViewsRunnable);
            return;
        } else
        {
            mAdapterUpdateDuringMeasure = true;
            requestLayout();
            return;
        }
    }

    ()
    {
        this$0 = RecyclerView.this;
        super();
    }
}
