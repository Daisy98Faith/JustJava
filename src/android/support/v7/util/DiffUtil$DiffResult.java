// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.support.v7.util:
//            DiffUtil, ListUpdateCallback, BatchingListUpdateCallback

public static class findMatchingItems
{

    private static final int FLAG_CHANGED = 2;
    private static final int FLAG_IGNORE = 16;
    private static final int FLAG_MASK = 31;
    private static final int FLAG_MOVED_CHANGED = 4;
    private static final int FLAG_MOVED_NOT_CHANGED = 8;
    private static final int FLAG_NOT_CHANGED = 1;
    private static final int FLAG_OFFSET = 5;
    private final mSnakes mCallback;
    private final boolean mDetectMoves;
    private final int mNewItemStatuses[];
    private final int mNewListSize;
    private final int mOldItemStatuses[];
    private final int mOldListSize;
    private final List mSnakes;

    private void addRootSnake()
    {
        findMatchingItems findmatchingitems;
        if (mSnakes.isEmpty())
        {
            findmatchingitems = null;
        } else
        {
            findmatchingitems = (mSnakes)mSnakes.get(0);
        }
        if (findmatchingitems == null || findmatchingitems.mSnakes != 0 || findmatchingitems.mSnakes != 0)
        {
            findmatchingitems = new >();
            findmatchingitems.> = 0;
            findmatchingitems.> = 0;
            findmatchingitems.al = false;
            findmatchingitems.al = 0;
            findmatchingitems.se = false;
            mSnakes.add(0, findmatchingitems);
        }
    }

    private void dispatchAdditions(List list, ListUpdateCallback listupdatecallback, int i, int j, int k)
    {
        if (mDetectMoves) goto _L2; else goto _L1
_L1:
        listupdatecallback.onInserted(i, j);
_L4:
        return;
_L2:
        j--;
_L5:
        if (j < 0) goto _L4; else goto _L3
_L3:
        int l = mNewItemStatuses[k + j] & 0x1f;
        switch (l)
        {
        default:
            throw new IllegalStateException((new StringBuilder()).append("unknown flag for pos ").append(k + j).append(" ").append(Long.toBinaryString(l)).toString());

        case 16: // '\020'
            break MISSING_BLOCK_LABEL_254;

        case 0: // '\0'
            listupdatecallback.onInserted(i, 1);
            for (Iterator iterator = list.iterator(); iterator.hasNext();)
            {
                date date = (date)iterator.next();
                date.currentPos = date.currentPos + 1;
            }

            break;

        case 4: // '\004'
        case 8: // '\b'
            int i1 = mNewItemStatuses[k + j] >> 5;
            listupdatecallback.onMoved(removePostponedUpdate(list, i1, true).currentPos, i);
            if (l == 4)
            {
                listupdatecallback.onChanged(i, 1, mCallback.tChangePayload(i1, k + j));
            }
            break;
        }
_L6:
        j--;
          goto _L5
          goto _L4
        list.add(new date(k + j, i, false));
          goto _L6
    }

    private void dispatchRemovals(List list, ListUpdateCallback listupdatecallback, int i, int j, int k)
    {
        if (mDetectMoves) goto _L2; else goto _L1
_L1:
        listupdatecallback.onRemoved(i, j);
_L4:
        return;
_L2:
        j--;
_L5:
        if (j < 0) goto _L4; else goto _L3
_L3:
        int l = mOldItemStatuses[k + j] & 0x1f;
        switch (l)
        {
        default:
            throw new IllegalStateException((new StringBuilder()).append("unknown flag for pos ").append(k + j).append(" ").append(Long.toBinaryString(l)).toString());

        case 16: // '\020'
            break MISSING_BLOCK_LABEL_272;

        case 0: // '\0'
            listupdatecallback.onRemoved(i + j, 1);
            for (Iterator iterator = list.iterator(); iterator.hasNext();)
            {
                date date1 = (date)iterator.next();
                date1.currentPos = date1.currentPos - 1;
            }

            break;

        case 4: // '\004'
        case 8: // '\b'
            int i1 = mOldItemStatuses[k + j] >> 5;
            date date = removePostponedUpdate(list, i1, false);
            listupdatecallback.onMoved(i + j, date.currentPos - 1);
            if (l == 4)
            {
                listupdatecallback.onChanged(date.currentPos - 1, 1, mCallback.tChangePayload(k + j, i1));
            }
            break;
        }
_L6:
        j--;
          goto _L5
          goto _L4
        list.add(new date(k + j, i + j, true));
          goto _L6
    }

    private void findAddition(int i, int j, int k)
    {
        if (mOldItemStatuses[i - 1] != 0)
        {
            return;
        } else
        {
            findMatchingItem(i, j, k, false);
            return;
        }
    }

    private boolean findMatchingItem(int i, int j, int k, boolean flag)
    {
        int l;
        int i1;
        if (flag)
        {
            i1 = j - 1;
            int j1 = i;
            l = j - 1;
            j = j1;
        } else
        {
            i1 = i - 1;
            int l1 = i - 1;
            l = j;
            j = l1;
        }
        for (; k >= 0; k--)
        {
            findMatchingItem findmatchingitem = (findMatchingItem)mSnakes.get(k);
            int k1 = findmatchingitem.mSnakes;
            int i2 = findmatchingitem.mSnakes;
            int j2 = findmatchingitem.mSnakes;
            int k2 = findmatchingitem.mSnakes;
            if (flag)
            {
                for (j--; j >= k1 + i2; j--)
                {
                    if (mCallback.eItemsTheSame(j, i1))
                    {
                        if (mCallback.eContentsTheSame(j, i1))
                        {
                            i = 8;
                        } else
                        {
                            i = 4;
                        }
                        mNewItemStatuses[i1] = j << 5 | 0x10;
                        mOldItemStatuses[j] = i1 << 5 | i;
                        return true;
                    }
                }

            } else
            {
                for (j = l - 1; j >= j2 + k2; j--)
                {
                    if (mCallback.eItemsTheSame(i1, j))
                    {
                        if (mCallback.eContentsTheSame(i1, j))
                        {
                            k = 8;
                        } else
                        {
                            k = 4;
                        }
                        mOldItemStatuses[i - 1] = j << 5 | 0x10;
                        mNewItemStatuses[j] = i - 1 << 5 | k;
                        return true;
                    }
                }

            }
            j = findmatchingitem.mNewItemStatuses;
            l = findmatchingitem.mNewItemStatuses;
        }

        return false;
    }

    private void findMatchingItems()
    {
        int j = mOldListSize;
        int i = mNewListSize;
        for (int k = mSnakes.size() - 1; k >= 0; k--)
        {
            mNewItemStatuses mnewitemstatuses = (mSnakes)mSnakes.get(k);
            int i2 = mnewitemstatuses.mSnakes;
            int j2 = mnewitemstatuses.mSnakes;
            int j1 = mnewitemstatuses.mSnakes;
            int l1 = mnewitemstatuses.mSnakes;
            if (mDetectMoves)
            {
                int l;
                do
                {
                    l = i;
                    if (j <= i2 + j2)
                    {
                        break;
                    }
                    findAddition(j, i, k);
                    j--;
                } while (true);
                for (; l > j1 + l1; l--)
                {
                    findRemoval(j, l, k);
                }

            }
            i = 0;
            while (i < mnewitemstatuses.findRemoval) 
            {
                int i1 = mnewitemstatuses.findRemoval + i;
                int k1 = mnewitemstatuses.findRemoval + i;
                if (mCallback.eContentsTheSame(i1, k1))
                {
                    j = 1;
                } else
                {
                    j = 2;
                }
                mOldItemStatuses[i1] = k1 << 5 | j;
                mNewItemStatuses[k1] = i1 << 5 | j;
                i++;
            }
            j = mnewitemstatuses.mNewItemStatuses;
            i = mnewitemstatuses.mNewItemStatuses;
        }

    }

    private void findRemoval(int i, int j, int k)
    {
        if (mNewItemStatuses[j - 1] != 0)
        {
            return;
        } else
        {
            findMatchingItem(i, j, k, true);
            return;
        }
    }

    private static date removePostponedUpdate(List list, int i, boolean flag)
    {
        date date;
label0:
        {
            int j = list.size() - 1;
            do
            {
                if (j < 0)
                {
                    break;
                }
                date date1 = (date)list.get(j);
                if (date1.posInOwnerList == i && date1.removal == flag)
                {
                    list.remove(j);
                    i = j;
                    do
                    {
                        date = date1;
                        if (i >= list.size())
                        {
                            break;
                        }
                        date = (date)list.get(i);
                        int k = date.currentPos;
                        if (flag)
                        {
                            j = 1;
                        } else
                        {
                            j = -1;
                        }
                        date.currentPos = j + k;
                        i++;
                    } while (true);
                    break label0;
                }
                j--;
            } while (true);
            date = null;
        }
        return date;
    }

    public void dispatchUpdatesTo(ListUpdateCallback listupdatecallback)
    {
        ArrayList arraylist;
        int j;
        int k;
        if (listupdatecallback instanceof BatchingListUpdateCallback)
        {
            listupdatecallback = (BatchingListUpdateCallback)listupdatecallback;
        } else
        {
            listupdatecallback = new BatchingListUpdateCallback(listupdatecallback);
        }
        arraylist = new ArrayList();
        k = mOldListSize;
        j = mNewListSize;
        for (int i = mSnakes.size() - 1; i >= 0; i--)
        {
            date.currentPos currentpos = (mSnakes)mSnakes.get(i);
            int l = currentpos.mSnakes;
            int i1 = currentpos.mSnakes + l;
            int j1 = currentpos.mSnakes + l;
            if (i1 < k)
            {
                dispatchRemovals(arraylist, listupdatecallback, i1, k - i1, i1);
            }
            if (j1 < j)
            {
                dispatchAdditions(arraylist, listupdatecallback, i1, j - j1, j1);
            }
            for (j = l - 1; j >= 0; j--)
            {
                if ((mOldItemStatuses[currentpos.mOldItemStatuses + j] & 0x1f) == 2)
                {
                    listupdatecallback.onChanged(currentpos.onChanged + j, 1, mCallback.tChangePayload(currentpos.tChangePayload + j, currentpos.tChangePayload + j));
                }
            }

            k = currentpos.tChangePayload;
            j = currentpos.tChangePayload;
        }

        listupdatecallback.dispatchLastEvent();
    }

    public void dispatchUpdatesTo(final android.support.v7.widget. adapter)
    {
        dispatchUpdatesTo(new ListUpdateCallback() {

            final DiffUtil.DiffResult this$0;
            final android.support.v7.widget.RecyclerView.Adapter val$adapter;

            public void onChanged(int i, int j, Object obj)
            {
                adapter.notifyItemRangeChanged(i, j, obj);
            }

            public void onInserted(int i, int j)
            {
                adapter.notifyItemRangeInserted(i, j);
            }

            public void onMoved(int i, int j)
            {
                adapter.notifyItemMoved(i, j);
            }

            public void onRemoved(int i, int j)
            {
                adapter.notifyItemRangeRemoved(i, j);
            }

            
            {
                this$0 = DiffUtil.DiffResult.this;
                adapter = adapter1;
                super();
            }
        });
    }

    List getSnakes()
    {
        return mSnakes;
    }

    _cls1.val.adapter(_cls1.val.adapter adapter, List list, int ai[], int ai1[], boolean flag)
    {
        mSnakes = list;
        mOldItemStatuses = ai;
        mNewItemStatuses = ai1;
        Arrays.fill(mOldItemStatuses, 0);
        Arrays.fill(mNewItemStatuses, 0);
        mCallback = adapter;
        mOldListSize = adapter.tOldListSize();
        mNewListSize = adapter.tNewListSize();
        mDetectMoves = flag;
        addRootSnake();
        findMatchingItems();
    }
}
