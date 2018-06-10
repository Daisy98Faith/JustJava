// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Referenced classes of package android.support.v7.widget:
//            OpReorderer

class AdapterHelper
    implements OpReorderer.Callback
{
    static interface Callback
    {

        public abstract RecyclerView.ViewHolder findViewHolder(int i);

        public abstract void markViewHoldersUpdated(int i, int j, Object obj);

        public abstract void offsetPositionsForAdd(int i, int j);

        public abstract void offsetPositionsForMove(int i, int j);

        public abstract void offsetPositionsForRemovingInvisible(int i, int j);

        public abstract void offsetPositionsForRemovingLaidOutOrNewView(int i, int j);

        public abstract void onDispatchFirstPass(UpdateOp updateop);

        public abstract void onDispatchSecondPass(UpdateOp updateop);
    }

    static class UpdateOp
    {

        static final int ADD = 1;
        static final int MOVE = 8;
        static final int POOL_SIZE = 30;
        static final int REMOVE = 2;
        static final int UPDATE = 4;
        int cmd;
        int itemCount;
        Object payload;
        int positionStart;

        String cmdToString()
        {
            switch (cmd)
            {
            case 3: // '\003'
            case 5: // '\005'
            case 6: // '\006'
            case 7: // '\007'
            default:
                return "??";

            case 1: // '\001'
                return "add";

            case 2: // '\002'
                return "rm";

            case 4: // '\004'
                return "up";

            case 8: // '\b'
                return "mv";
            }
        }

        public boolean equals(Object obj)
        {
            if (this != obj) goto _L2; else goto _L1
_L1:
            return true;
_L2:
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            obj = (UpdateOp)obj;
            if (cmd != ((UpdateOp) (obj)).cmd)
            {
                return false;
            }
            if (cmd == 8 && Math.abs(itemCount - positionStart) == 1 && itemCount == ((UpdateOp) (obj)).positionStart && positionStart == ((UpdateOp) (obj)).itemCount) goto _L1; else goto _L3
_L3:
            if (itemCount != ((UpdateOp) (obj)).itemCount)
            {
                return false;
            }
            if (positionStart != ((UpdateOp) (obj)).positionStart)
            {
                return false;
            }
            if (payload == null)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (payload.equals(((UpdateOp) (obj)).payload)) goto _L1; else goto _L4
_L4:
            return false;
            if (((UpdateOp) (obj)).payload == null) goto _L1; else goto _L5
_L5:
            return false;
        }

        public int hashCode()
        {
            return (cmd * 31 + positionStart) * 31 + itemCount;
        }

        public String toString()
        {
            return (new StringBuilder()).append(Integer.toHexString(System.identityHashCode(this))).append("[").append(cmdToString()).append(",s:").append(positionStart).append("c:").append(itemCount).append(",p:").append(payload).append("]").toString();
        }

        UpdateOp(int i, int j, int k, Object obj)
        {
            cmd = i;
            positionStart = j;
            itemCount = k;
            payload = obj;
        }
    }


    private static final boolean DEBUG = false;
    static final int POSITION_TYPE_INVISIBLE = 0;
    static final int POSITION_TYPE_NEW_OR_LAID_OUT = 1;
    private static final String TAG = "AHT";
    final Callback mCallback;
    final boolean mDisableRecycler;
    private int mExistingUpdateTypes;
    Runnable mOnItemProcessedCallback;
    final OpReorderer mOpReorderer;
    final ArrayList mPendingUpdates;
    final ArrayList mPostponedList;
    private android.support.v4.util.Pools.Pool mUpdateOpPool;

    AdapterHelper(Callback callback)
    {
        this(callback, false);
    }

    AdapterHelper(Callback callback, boolean flag)
    {
        mUpdateOpPool = new android.support.v4.util.Pools.SimplePool(30);
        mPendingUpdates = new ArrayList();
        mPostponedList = new ArrayList();
        mExistingUpdateTypes = 0;
        mCallback = callback;
        mDisableRecycler = flag;
        mOpReorderer = new OpReorderer(this);
    }

    private void applyAdd(UpdateOp updateop)
    {
        postponeAndUpdateViewHolders(updateop);
    }

    private void applyMove(UpdateOp updateop)
    {
        postponeAndUpdateViewHolders(updateop);
    }

    private void applyRemove(UpdateOp updateop)
    {
        int i1 = updateop.positionStart;
        int l = 0;
        int k = updateop.positionStart + updateop.itemCount;
        byte byte1 = -1;
        int i = updateop.positionStart;
        while (i < k) 
        {
            int j = 0;
            byte byte0 = 0;
            if (mCallback.findViewHolder(i) != null || canFindInPreLayout(i))
            {
                if (byte1 == 0)
                {
                    dispatchAndUpdateViewHolders(obtainUpdateOp(2, i1, l, null));
                    byte0 = 1;
                }
                byte1 = 1;
                j = byte0;
                byte0 = byte1;
            } else
            {
                if (byte1 == 1)
                {
                    postponeAndUpdateViewHolders(obtainUpdateOp(2, i1, l, null));
                    j = 1;
                }
                byte0 = 0;
            }
            if (j != 0)
            {
                i -= l;
                k -= l;
                j = 1;
            } else
            {
                j = l + 1;
            }
            i++;
            l = j;
            byte1 = byte0;
        }
        UpdateOp updateop1 = updateop;
        if (l != updateop.itemCount)
        {
            recycleUpdateOp(updateop);
            updateop1 = obtainUpdateOp(2, i1, l, null);
        }
        if (byte1 == 0)
        {
            dispatchAndUpdateViewHolders(updateop1);
            return;
        } else
        {
            postponeAndUpdateViewHolders(updateop1);
            return;
        }
    }

    private void applyUpdate(UpdateOp updateop)
    {
        int k = updateop.positionStart;
        int j = 0;
        int k1 = updateop.positionStart;
        int l1 = updateop.itemCount;
        byte byte0 = -1;
        int i = updateop.positionStart;
        while (i < k1 + l1) 
        {
            int l;
            int i1;
            if (mCallback.findViewHolder(i) != null || canFindInPreLayout(i))
            {
                i1 = j;
                int j1 = k;
                if (byte0 == 0)
                {
                    dispatchAndUpdateViewHolders(obtainUpdateOp(4, k, j, updateop.payload));
                    i1 = 0;
                    j1 = i;
                }
                l = 1;
                k = j1;
            } else
            {
                i1 = j;
                l = k;
                if (byte0 == 1)
                {
                    postponeAndUpdateViewHolders(obtainUpdateOp(4, k, j, updateop.payload));
                    i1 = 0;
                    l = i;
                }
                j = 0;
                k = l;
                l = j;
            }
            j = i1 + 1;
            i++;
            byte0 = l;
        }
        Object obj = updateop;
        if (j != updateop.itemCount)
        {
            obj = updateop.payload;
            recycleUpdateOp(updateop);
            obj = obtainUpdateOp(4, k, j, obj);
        }
        if (byte0 == 0)
        {
            dispatchAndUpdateViewHolders(((UpdateOp) (obj)));
            return;
        } else
        {
            postponeAndUpdateViewHolders(((UpdateOp) (obj)));
            return;
        }
    }

    private boolean canFindInPreLayout(int i)
    {
        int j;
        int l;
        l = mPostponedList.size();
        j = 0;
_L7:
        if (j >= l) goto _L2; else goto _L1
_L1:
        UpdateOp updateop = (UpdateOp)mPostponedList.get(j);
        if (updateop.cmd != 8) goto _L4; else goto _L3
_L3:
        if (findPositionOffset(updateop.itemCount, j + 1) != i)
        {
            continue; /* Loop/switch isn't completed */
        }
_L6:
        return true;
_L4:
        if (updateop.cmd != 1)
        {
            continue; /* Loop/switch isn't completed */
        }
        int i1 = updateop.positionStart;
        int j1 = updateop.itemCount;
        int k = updateop.positionStart;
        do
        {
            if (k >= i1 + j1)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (findPositionOffset(k, j + 1) == i)
            {
                break;
            }
            k++;
        } while (true);
        if (true) goto _L6; else goto _L5
_L5:
        j++;
          goto _L7
_L2:
        return false;
    }

    private void dispatchAndUpdateViewHolders(UpdateOp updateop)
    {
        int i;
        int i1;
        int j1;
        if (updateop.cmd == 1 || updateop.cmd == 8)
        {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        j1 = updatePositionWithPostponed(updateop.positionStart, updateop.cmd);
        i1 = 1;
        i = updateop.positionStart;
        updateop.cmd;
        JVM INSTR tableswitch 2 4: default 80
    //                   2 208
    //                   3 80
    //                   4 107;
           goto _L1 _L2 _L1 _L3
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("op should be remove or update.").append(updateop).toString());
_L3:
        int k = 1;
_L9:
        int l = 1;
_L8:
        int j;
        int k1;
        boolean flag;
        if (l >= updateop.itemCount)
        {
            break MISSING_BLOCK_LABEL_316;
        }
        k1 = updatePositionWithPostponed(updateop.positionStart + k * l, updateop.cmd);
        flag = false;
        j = ((flag) ? 1 : 0);
        updateop.cmd;
        JVM INSTR tableswitch 2 4: default 180
    //                   2 235
    //                   3 184
    //                   4 214;
           goto _L4 _L5 _L6 _L7
_L6:
        break; /* Loop/switch isn't completed */
_L4:
        j = ((flag) ? 1 : 0);
_L10:
        if (j != 0)
        {
            j = i1 + 1;
        } else
        {
            UpdateOp updateop1 = obtainUpdateOp(updateop.cmd, j1, i1, updateop.payload);
            dispatchFirstPassAndUpdateViewHolders(updateop1, i);
            recycleUpdateOp(updateop1);
            j = i;
            if (updateop.cmd == 4)
            {
                j = i + i1;
            }
            j1 = k1;
            i1 = 1;
            i = j;
            j = i1;
        }
        l++;
        i1 = j;
          goto _L8
_L2:
        k = 0;
          goto _L9
_L7:
        if (k1 == j1 + 1)
        {
            j = 1;
        } else
        {
            j = 0;
        }
          goto _L10
_L5:
        if (k1 == j1)
        {
            j = 1;
        } else
        {
            j = 0;
        }
          goto _L10
        Object obj = updateop.payload;
        recycleUpdateOp(updateop);
        if (i1 > 0)
        {
            updateop = obtainUpdateOp(updateop.cmd, j1, i1, obj);
            dispatchFirstPassAndUpdateViewHolders(updateop, i);
            recycleUpdateOp(updateop);
        }
        return;
          goto _L8
    }

    private void postponeAndUpdateViewHolders(UpdateOp updateop)
    {
        mPostponedList.add(updateop);
        switch (updateop.cmd)
        {
        case 3: // '\003'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown update op type for ").append(updateop).toString());

        case 1: // '\001'
            mCallback.offsetPositionsForAdd(updateop.positionStart, updateop.itemCount);
            return;

        case 8: // '\b'
            mCallback.offsetPositionsForMove(updateop.positionStart, updateop.itemCount);
            return;

        case 2: // '\002'
            mCallback.offsetPositionsForRemovingLaidOutOrNewView(updateop.positionStart, updateop.itemCount);
            return;

        case 4: // '\004'
            mCallback.markViewHoldersUpdated(updateop.positionStart, updateop.itemCount, updateop.payload);
            return;
        }
    }

    private int updatePositionWithPostponed(int i, int j)
    {
        int k = mPostponedList.size() - 1;
        int l = i;
        do
        {
            if (k >= 0)
            {
                UpdateOp updateop = (UpdateOp)mPostponedList.get(k);
                if (updateop.cmd == 8)
                {
                    int i1;
                    if (updateop.positionStart < updateop.itemCount)
                    {
                        i1 = updateop.positionStart;
                        i = updateop.itemCount;
                    } else
                    {
                        i1 = updateop.itemCount;
                        i = updateop.positionStart;
                    }
                    if (l >= i1 && l <= i)
                    {
                        if (i1 == updateop.positionStart)
                        {
                            if (j == 1)
                            {
                                updateop.itemCount = updateop.itemCount + 1;
                            } else
                            if (j == 2)
                            {
                                updateop.itemCount = updateop.itemCount - 1;
                            }
                            i = l + 1;
                        } else
                        {
                            if (j == 1)
                            {
                                updateop.positionStart = updateop.positionStart + 1;
                            } else
                            if (j == 2)
                            {
                                updateop.positionStart = updateop.positionStart - 1;
                            }
                            i = l - 1;
                        }
                    } else
                    {
                        i = l;
                        if (l < updateop.positionStart)
                        {
                            if (j == 1)
                            {
                                updateop.positionStart = updateop.positionStart + 1;
                                updateop.itemCount = updateop.itemCount + 1;
                                i = l;
                            } else
                            {
                                i = l;
                                if (j == 2)
                                {
                                    updateop.positionStart = updateop.positionStart - 1;
                                    updateop.itemCount = updateop.itemCount - 1;
                                    i = l;
                                }
                            }
                        }
                    }
                } else
                if (updateop.positionStart <= l)
                {
                    if (updateop.cmd == 1)
                    {
                        i = l - updateop.itemCount;
                    } else
                    {
                        i = l;
                        if (updateop.cmd == 2)
                        {
                            i = l + updateop.itemCount;
                        }
                    }
                } else
                if (j == 1)
                {
                    updateop.positionStart = updateop.positionStart + 1;
                    i = l;
                } else
                {
                    i = l;
                    if (j == 2)
                    {
                        updateop.positionStart = updateop.positionStart - 1;
                        i = l;
                    }
                }
                k--;
                l = i;
                continue;
            }
            i = mPostponedList.size() - 1;
            while (i >= 0) 
            {
                UpdateOp updateop1 = (UpdateOp)mPostponedList.get(i);
                if (updateop1.cmd == 8)
                {
                    if (updateop1.itemCount == updateop1.positionStart || updateop1.itemCount < 0)
                    {
                        mPostponedList.remove(i);
                        recycleUpdateOp(updateop1);
                    }
                } else
                if (updateop1.itemCount <= 0)
                {
                    mPostponedList.remove(i);
                    recycleUpdateOp(updateop1);
                }
                i--;
            }
            return l;
        } while (true);
    }

    transient AdapterHelper addUpdateOp(UpdateOp aupdateop[])
    {
        Collections.addAll(mPendingUpdates, aupdateop);
        return this;
    }

    public int applyPendingUpdatesToPosition(int i)
    {
        int j;
        int l;
        int i1;
        i1 = mPendingUpdates.size();
        l = 0;
        j = i;
_L7:
        i = j;
        if (l >= i1) goto _L2; else goto _L1
_L1:
        UpdateOp updateop = (UpdateOp)mPendingUpdates.get(l);
        updateop.cmd;
        JVM INSTR lookupswitch 3: default 76
    //                   1: 89
    //                   2: 109
    //                   8: 146;
           goto _L3 _L4 _L5 _L6
_L6:
        break MISSING_BLOCK_LABEL_146;
_L3:
        i = j;
_L8:
        l++;
        j = i;
          goto _L7
_L4:
        i = j;
        if (updateop.positionStart <= j)
        {
            i = j + updateop.itemCount;
        }
          goto _L8
_L5:
        i = j;
        if (updateop.positionStart > j) goto _L8; else goto _L9
_L9:
        if (updateop.positionStart + updateop.itemCount <= j) goto _L11; else goto _L10
_L10:
        i = -1;
_L2:
        return i;
_L11:
        i = j - updateop.itemCount;
          goto _L8
        if (updateop.positionStart == j)
        {
            i = updateop.itemCount;
        } else
        {
            int k = j;
            if (updateop.positionStart < j)
            {
                k = j - 1;
            }
            i = k;
            if (updateop.itemCount <= k)
            {
                i = k + 1;
            }
        }
          goto _L8
    }

    void consumePostponedUpdates()
    {
        int j = mPostponedList.size();
        for (int i = 0; i < j; i++)
        {
            mCallback.onDispatchSecondPass((UpdateOp)mPostponedList.get(i));
        }

        recycleUpdateOpsAndClearList(mPostponedList);
        mExistingUpdateTypes = 0;
    }

    void consumeUpdatesInOnePass()
    {
        int i;
        int j;
        consumePostponedUpdates();
        j = mPendingUpdates.size();
        i = 0;
_L7:
        UpdateOp updateop;
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_227;
        }
        updateop = (UpdateOp)mPendingUpdates.get(i);
        updateop.cmd;
        JVM INSTR tableswitch 1 8: default 80
    //                   1 103
    //                   2 133
    //                   3 80
    //                   4 163
    //                   5 80
    //                   6 80
    //                   7 80
    //                   8 197;
           goto _L1 _L2 _L3 _L1 _L4 _L1 _L1 _L1 _L5
_L5:
        break MISSING_BLOCK_LABEL_197;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L8:
        if (mOnItemProcessedCallback != null)
        {
            mOnItemProcessedCallback.run();
        }
        i++;
        if (true) goto _L7; else goto _L6
_L6:
        mCallback.onDispatchSecondPass(updateop);
        mCallback.offsetPositionsForAdd(updateop.positionStart, updateop.itemCount);
          goto _L8
_L3:
        mCallback.onDispatchSecondPass(updateop);
        mCallback.offsetPositionsForRemovingInvisible(updateop.positionStart, updateop.itemCount);
          goto _L8
_L4:
        mCallback.onDispatchSecondPass(updateop);
        mCallback.markViewHoldersUpdated(updateop.positionStart, updateop.itemCount, updateop.payload);
          goto _L8
        mCallback.onDispatchSecondPass(updateop);
        mCallback.offsetPositionsForMove(updateop.positionStart, updateop.itemCount);
          goto _L8
        recycleUpdateOpsAndClearList(mPendingUpdates);
        mExistingUpdateTypes = 0;
        return;
    }

    void dispatchFirstPassAndUpdateViewHolders(UpdateOp updateop, int i)
    {
        mCallback.onDispatchFirstPass(updateop);
        switch (updateop.cmd)
        {
        case 3: // '\003'
        default:
            throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");

        case 2: // '\002'
            mCallback.offsetPositionsForRemovingInvisible(i, updateop.itemCount);
            return;

        case 4: // '\004'
            mCallback.markViewHoldersUpdated(i, updateop.itemCount, updateop.payload);
            break;
        }
    }

    int findPositionOffset(int i)
    {
        return findPositionOffset(i, 0);
    }

    int findPositionOffset(int i, int j)
    {
        int l;
        int i1;
        i1 = mPostponedList.size();
        l = j;
        j = i;
_L5:
        i = j;
        if (l >= i1) goto _L2; else goto _L1
_L1:
        UpdateOp updateop = (UpdateOp)mPostponedList.get(l);
        if (updateop.cmd != 8) goto _L4; else goto _L3
_L3:
        if (updateop.positionStart == j)
        {
            i = updateop.itemCount;
        } else
        {
            int k = j;
            if (updateop.positionStart < j)
            {
                k = j - 1;
            }
            i = k;
            if (updateop.itemCount <= k)
            {
                i = k + 1;
            }
        }
_L7:
        l++;
        j = i;
          goto _L5
_L4:
        i = j;
        if (updateop.positionStart > j) goto _L7; else goto _L6
_L6:
        if (updateop.cmd != 2)
        {
            break MISSING_BLOCK_LABEL_150;
        }
        if (j >= updateop.positionStart + updateop.itemCount) goto _L9; else goto _L8
_L8:
        i = -1;
_L2:
        return i;
_L9:
        i = j - updateop.itemCount;
          goto _L7
        i = j;
        if (updateop.cmd == 1)
        {
            i = j + updateop.itemCount;
        }
          goto _L7
    }

    boolean hasAnyUpdateTypes(int i)
    {
        return (mExistingUpdateTypes & i) != 0;
    }

    boolean hasPendingUpdates()
    {
        return mPendingUpdates.size() > 0;
    }

    boolean hasUpdates()
    {
        return !mPostponedList.isEmpty() && !mPendingUpdates.isEmpty();
    }

    public UpdateOp obtainUpdateOp(int i, int j, int k, Object obj)
    {
        UpdateOp updateop = (UpdateOp)mUpdateOpPool.acquire();
        if (updateop == null)
        {
            return new UpdateOp(i, j, k, obj);
        } else
        {
            updateop.cmd = i;
            updateop.positionStart = j;
            updateop.itemCount = k;
            updateop.payload = obj;
            return updateop;
        }
    }

    boolean onItemRangeChanged(int i, int j, Object obj)
    {
        boolean flag = true;
        if (j < 1)
        {
            return false;
        }
        mPendingUpdates.add(obtainUpdateOp(4, i, j, obj));
        mExistingUpdateTypes = mExistingUpdateTypes | 4;
        if (mPendingUpdates.size() != 1)
        {
            flag = false;
        }
        return flag;
    }

    boolean onItemRangeInserted(int i, int j)
    {
        boolean flag = true;
        if (j < 1)
        {
            return false;
        }
        mPendingUpdates.add(obtainUpdateOp(1, i, j, null));
        mExistingUpdateTypes = mExistingUpdateTypes | 1;
        if (mPendingUpdates.size() != 1)
        {
            flag = false;
        }
        return flag;
    }

    boolean onItemRangeMoved(int i, int j, int k)
    {
        boolean flag = true;
        if (i == j)
        {
            return false;
        }
        if (k != 1)
        {
            throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
        }
        mPendingUpdates.add(obtainUpdateOp(8, i, j, null));
        mExistingUpdateTypes = mExistingUpdateTypes | 8;
        if (mPendingUpdates.size() != 1)
        {
            flag = false;
        }
        return flag;
    }

    boolean onItemRangeRemoved(int i, int j)
    {
        boolean flag = true;
        if (j < 1)
        {
            return false;
        }
        mPendingUpdates.add(obtainUpdateOp(2, i, j, null));
        mExistingUpdateTypes = mExistingUpdateTypes | 2;
        if (mPendingUpdates.size() != 1)
        {
            flag = false;
        }
        return flag;
    }

    void preProcess()
    {
        int i;
        int j;
        mOpReorderer.reorderOps(mPendingUpdates);
        j = mPendingUpdates.size();
        i = 0;
_L7:
        UpdateOp updateop;
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_143;
        }
        updateop = (UpdateOp)mPendingUpdates.get(i);
        updateop.cmd;
        JVM INSTR tableswitch 1 8: default 88
    //                   1 111
    //                   2 119
    //                   3 88
    //                   4 127
    //                   5 88
    //                   6 88
    //                   7 88
    //                   8 135;
           goto _L1 _L2 _L3 _L1 _L4 _L1 _L1 _L1 _L5
_L5:
        break MISSING_BLOCK_LABEL_135;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L8:
        if (mOnItemProcessedCallback != null)
        {
            mOnItemProcessedCallback.run();
        }
        i++;
        if (true) goto _L7; else goto _L6
_L6:
        applyAdd(updateop);
          goto _L8
_L3:
        applyRemove(updateop);
          goto _L8
_L4:
        applyUpdate(updateop);
          goto _L8
        applyMove(updateop);
          goto _L8
        mPendingUpdates.clear();
        return;
    }

    public void recycleUpdateOp(UpdateOp updateop)
    {
        if (!mDisableRecycler)
        {
            updateop.payload = null;
            mUpdateOpPool.release(updateop);
        }
    }

    void recycleUpdateOpsAndClearList(List list)
    {
        int j = list.size();
        for (int i = 0; i < j; i++)
        {
            recycleUpdateOp((UpdateOp)list.get(i));
        }

        list.clear();
    }

    void reset()
    {
        recycleUpdateOpsAndClearList(mPendingUpdates);
        recycleUpdateOpsAndClearList(mPostponedList);
        mExistingUpdateTypes = 0;
    }
}
