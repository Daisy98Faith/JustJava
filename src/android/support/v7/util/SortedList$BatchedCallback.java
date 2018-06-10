// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.util;


// Referenced classes of package android.support.v7.util:
//            SortedList, BatchingListUpdateCallback

public static class mWrappedCallback extends mWrappedCallback
{

    private final BatchingListUpdateCallback mBatchingListUpdateCallback;
    final onRemoved mWrappedCallback;

    public boolean areContentsTheSame(Object obj, Object obj1)
    {
        return mWrappedCallback.entsTheSame(obj, obj1);
    }

    public boolean areItemsTheSame(Object obj, Object obj1)
    {
        return mWrappedCallback.sTheSame(obj, obj1);
    }

    public int compare(Object obj, Object obj1)
    {
        return mWrappedCallback.(obj, obj1);
    }

    public void dispatchLastEvent()
    {
        mBatchingListUpdateCallback.dispatchLastEvent();
    }

    public void onChanged(int i, int j)
    {
        mBatchingListUpdateCallback.onChanged(i, j, null);
    }

    public void onInserted(int i, int j)
    {
        mBatchingListUpdateCallback.onInserted(i, j);
    }

    public void onMoved(int i, int j)
    {
        mBatchingListUpdateCallback.onMoved(i, j);
    }

    public void onRemoved(int i, int j)
    {
        mBatchingListUpdateCallback.onRemoved(i, j);
    }

    public ( )
    {
        mWrappedCallback = ;
        mBatchingListUpdateCallback = new BatchingListUpdateCallback(mWrappedCallback);
    }
}
