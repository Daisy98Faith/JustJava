// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.util;


// Referenced classes of package android.support.v7.util:
//            ListUpdateCallback

public class BatchingListUpdateCallback
    implements ListUpdateCallback
{

    private static final int TYPE_ADD = 1;
    private static final int TYPE_CHANGE = 3;
    private static final int TYPE_NONE = 0;
    private static final int TYPE_REMOVE = 2;
    int mLastEventCount;
    Object mLastEventPayload;
    int mLastEventPosition;
    int mLastEventType;
    final ListUpdateCallback mWrapped;

    public BatchingListUpdateCallback(ListUpdateCallback listupdatecallback)
    {
        mLastEventType = 0;
        mLastEventPosition = -1;
        mLastEventCount = -1;
        mLastEventPayload = null;
        mWrapped = listupdatecallback;
    }

    public void dispatchLastEvent()
    {
        if (mLastEventType == 0)
        {
            return;
        }
        mLastEventType;
        JVM INSTR tableswitch 1 3: default 40
    //                   1 51
    //                   2 71
    //                   3 91;
           goto _L1 _L2 _L3 _L4
_L1:
        mLastEventPayload = null;
        mLastEventType = 0;
        return;
_L2:
        mWrapped.onInserted(mLastEventPosition, mLastEventCount);
        continue; /* Loop/switch isn't completed */
_L3:
        mWrapped.onRemoved(mLastEventPosition, mLastEventCount);
        continue; /* Loop/switch isn't completed */
_L4:
        mWrapped.onChanged(mLastEventPosition, mLastEventCount, mLastEventPayload);
        if (true) goto _L1; else goto _L5
_L5:
    }

    public void onChanged(int i, int j, Object obj)
    {
        if (mLastEventType == 3 && i <= mLastEventPosition + mLastEventCount && i + j >= mLastEventPosition && mLastEventPayload == obj)
        {
            int k = mLastEventPosition;
            int l = mLastEventCount;
            mLastEventPosition = Math.min(i, mLastEventPosition);
            mLastEventCount = Math.max(k + l, i + j) - mLastEventPosition;
            return;
        } else
        {
            dispatchLastEvent();
            mLastEventPosition = i;
            mLastEventCount = j;
            mLastEventPayload = obj;
            mLastEventType = 3;
            return;
        }
    }

    public void onInserted(int i, int j)
    {
        if (mLastEventType == 1 && i >= mLastEventPosition && i <= mLastEventPosition + mLastEventCount)
        {
            mLastEventCount = mLastEventCount + j;
            mLastEventPosition = Math.min(i, mLastEventPosition);
            return;
        } else
        {
            dispatchLastEvent();
            mLastEventPosition = i;
            mLastEventCount = j;
            mLastEventType = 1;
            return;
        }
    }

    public void onMoved(int i, int j)
    {
        dispatchLastEvent();
        mWrapped.onMoved(i, j);
    }

    public void onRemoved(int i, int j)
    {
        if (mLastEventType == 2 && mLastEventPosition >= i && mLastEventPosition <= i + j)
        {
            mLastEventCount = mLastEventCount + j;
            mLastEventPosition = i;
            return;
        } else
        {
            dispatchLastEvent();
            mLastEventPosition = i;
            mLastEventCount = j;
            mLastEventType = 2;
            return;
        }
    }
}
