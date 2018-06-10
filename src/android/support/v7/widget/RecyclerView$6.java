// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;


// Referenced classes of package android.support.v7.widget:
//            RecyclerView, ChildHelper

class this._cls0
    implements allback
{

    final RecyclerView this$0;

    void dispatchUpdate(pdateOp pdateop)
    {
        switch (pdateop.cmd)
        {
        case 3: // '\003'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        default:
            return;

        case 1: // '\001'
            mLayout.onItemsAdded(RecyclerView.this, pdateop.positionStart, pdateop.itemCount);
            return;

        case 2: // '\002'
            mLayout.onItemsRemoved(RecyclerView.this, pdateop.positionStart, pdateop.itemCount);
            return;

        case 4: // '\004'
            mLayout.onItemsUpdated(RecyclerView.this, pdateop.positionStart, pdateop.itemCount, pdateop.payload);
            return;

        case 8: // '\b'
            mLayout.onItemsMoved(RecyclerView.this, pdateop.positionStart, pdateop.itemCount, 1);
            break;
        }
    }

    public ewHolder findViewHolder(int i)
    {
        ewHolder ewholder1 = findViewHolderForPosition(i, true);
        ewHolder ewholder;
        if (ewholder1 == null)
        {
            ewholder = null;
        } else
        {
            ewholder = ewholder1;
            if (mChildHelper.isHidden(ewholder1.itemView))
            {
                return null;
            }
        }
        return ewholder;
    }

    public void markViewHoldersUpdated(int i, int j, Object obj)
    {
        viewRangeUpdate(i, j, obj);
        mItemsChanged = true;
    }

    public void offsetPositionsForAdd(int i, int j)
    {
        offsetPositionRecordsForInsert(i, j);
        mItemsAddedOrRemoved = true;
    }

    public void offsetPositionsForMove(int i, int j)
    {
        offsetPositionRecordsForMove(i, j);
        mItemsAddedOrRemoved = true;
    }

    public void offsetPositionsForRemovingInvisible(int i, int j)
    {
        offsetPositionRecordsForRemove(i, j, true);
        mItemsAddedOrRemoved = true;
        ate ate = mState;
        ate.mDeletedInvisibleItemCountSincePreviousLayout = ate.mDeletedInvisibleItemCountSincePreviousLayout + j;
    }

    public void offsetPositionsForRemovingLaidOutOrNewView(int i, int j)
    {
        offsetPositionRecordsForRemove(i, j, false);
        mItemsAddedOrRemoved = true;
    }

    public void onDispatchFirstPass(pdateOp pdateop)
    {
        dispatchUpdate(pdateop);
    }

    public void onDispatchSecondPass(pdateOp pdateop)
    {
        dispatchUpdate(pdateop);
    }

    pdateOp()
    {
        this$0 = RecyclerView.this;
        super();
    }
}
