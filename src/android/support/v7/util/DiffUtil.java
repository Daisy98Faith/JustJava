// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package android.support.v7.util:
//            ListUpdateCallback, BatchingListUpdateCallback

public class DiffUtil
{
    public static abstract class Callback
    {

        public abstract boolean areContentsTheSame(int i, int j);

        public abstract boolean areItemsTheSame(int i, int j);

        public Object getChangePayload(int i, int j)
        {
            return null;
        }

        public abstract int getNewListSize();

        public abstract int getOldListSize();

        public Callback()
        {
        }
    }

    public static class DiffResult
    {

        private static final int FLAG_CHANGED = 2;
        private static final int FLAG_IGNORE = 16;
        private static final int FLAG_MASK = 31;
        private static final int FLAG_MOVED_CHANGED = 4;
        private static final int FLAG_MOVED_NOT_CHANGED = 8;
        private static final int FLAG_NOT_CHANGED = 1;
        private static final int FLAG_OFFSET = 5;
        private final Callback mCallback;
        private final boolean mDetectMoves;
        private final int mNewItemStatuses[];
        private final int mNewListSize;
        private final int mOldItemStatuses[];
        private final int mOldListSize;
        private final List mSnakes;

        private void addRootSnake()
        {
            Snake snake;
            if (mSnakes.isEmpty())
            {
                snake = null;
            } else
            {
                snake = (Snake)mSnakes.get(0);
            }
            if (snake == null || snake.x != 0 || snake.y != 0)
            {
                snake = new Snake();
                snake.x = 0;
                snake.y = 0;
                snake.removal = false;
                snake.size = 0;
                snake.reverse = false;
                mSnakes.add(0, snake);
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
                    PostponedUpdate postponedupdate = (PostponedUpdate)iterator.next();
                    postponedupdate.currentPos = postponedupdate.currentPos + 1;
                }

                break;

            case 4: // '\004'
            case 8: // '\b'
                int i1 = mNewItemStatuses[k + j] >> 5;
                listupdatecallback.onMoved(removePostponedUpdate(list, i1, true).currentPos, i);
                if (l == 4)
                {
                    listupdatecallback.onChanged(i, 1, mCallback.getChangePayload(i1, k + j));
                }
                break;
            }
_L6:
            j--;
              goto _L5
              goto _L4
            list.add(new PostponedUpdate(k + j, i, false));
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
                    PostponedUpdate postponedupdate1 = (PostponedUpdate)iterator.next();
                    postponedupdate1.currentPos = postponedupdate1.currentPos - 1;
                }

                break;

            case 4: // '\004'
            case 8: // '\b'
                int i1 = mOldItemStatuses[k + j] >> 5;
                PostponedUpdate postponedupdate = removePostponedUpdate(list, i1, false);
                listupdatecallback.onMoved(i + j, postponedupdate.currentPos - 1);
                if (l == 4)
                {
                    listupdatecallback.onChanged(postponedupdate.currentPos - 1, 1, mCallback.getChangePayload(k + j, i1));
                }
                break;
            }
_L6:
            j--;
              goto _L5
              goto _L4
            list.add(new PostponedUpdate(k + j, i + j, true));
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
                Snake snake = (Snake)mSnakes.get(k);
                int k1 = snake.x;
                int i2 = snake.size;
                int j2 = snake.y;
                int k2 = snake.size;
                if (flag)
                {
                    for (j--; j >= k1 + i2; j--)
                    {
                        if (mCallback.areItemsTheSame(j, i1))
                        {
                            if (mCallback.areContentsTheSame(j, i1))
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
                        if (mCallback.areItemsTheSame(i1, j))
                        {
                            if (mCallback.areContentsTheSame(i1, j))
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
                j = snake.x;
                l = snake.y;
            }

            return false;
        }

        private void findMatchingItems()
        {
            int j = mOldListSize;
            int i = mNewListSize;
            for (int k = mSnakes.size() - 1; k >= 0; k--)
            {
                Snake snake = (Snake)mSnakes.get(k);
                int i2 = snake.x;
                int j2 = snake.size;
                int j1 = snake.y;
                int l1 = snake.size;
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
                while (i < snake.size) 
                {
                    int i1 = snake.x + i;
                    int k1 = snake.y + i;
                    if (mCallback.areContentsTheSame(i1, k1))
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
                j = snake.x;
                i = snake.y;
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

        private static PostponedUpdate removePostponedUpdate(List list, int i, boolean flag)
        {
            PostponedUpdate postponedupdate;
label0:
            {
                int j = list.size() - 1;
                do
                {
                    if (j < 0)
                    {
                        break;
                    }
                    PostponedUpdate postponedupdate1 = (PostponedUpdate)list.get(j);
                    if (postponedupdate1.posInOwnerList == i && postponedupdate1.removal == flag)
                    {
                        list.remove(j);
                        i = j;
                        do
                        {
                            postponedupdate = postponedupdate1;
                            if (i >= list.size())
                            {
                                break;
                            }
                            postponedupdate = (PostponedUpdate)list.get(i);
                            int k = postponedupdate.currentPos;
                            if (flag)
                            {
                                j = 1;
                            } else
                            {
                                j = -1;
                            }
                            postponedupdate.currentPos = j + k;
                            i++;
                        } while (true);
                        break label0;
                    }
                    j--;
                } while (true);
                postponedupdate = null;
            }
            return postponedupdate;
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
                Snake snake = (Snake)mSnakes.get(i);
                int l = snake.size;
                int i1 = snake.x + l;
                int j1 = snake.y + l;
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
                    if ((mOldItemStatuses[snake.x + j] & 0x1f) == 2)
                    {
                        listupdatecallback.onChanged(snake.x + j, 1, mCallback.getChangePayload(snake.x + j, snake.y + j));
                    }
                }

                k = snake.x;
                j = snake.y;
            }

            listupdatecallback.dispatchLastEvent();
        }

        public void dispatchUpdatesTo(android.support.v7.widget.RecyclerView.Adapter adapter)
        {
            dispatchUpdatesTo(adapter. new ListUpdateCallback() {

                final DiffResult this$0;
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
                this$0 = final_diffresult;
                adapter = android.support.v7.widget.RecyclerView.Adapter.this;
                super();
            }
            });
        }

        List getSnakes()
        {
            return mSnakes;
        }

        DiffResult(Callback callback, List list, int ai[], int ai1[], boolean flag)
        {
            mSnakes = list;
            mOldItemStatuses = ai;
            mNewItemStatuses = ai1;
            Arrays.fill(mOldItemStatuses, 0);
            Arrays.fill(mNewItemStatuses, 0);
            mCallback = callback;
            mOldListSize = callback.getOldListSize();
            mNewListSize = callback.getNewListSize();
            mDetectMoves = flag;
            addRootSnake();
            findMatchingItems();
        }
    }

    private static class PostponedUpdate
    {

        int currentPos;
        int posInOwnerList;
        boolean removal;

        public PostponedUpdate(int i, int j, boolean flag)
        {
            posInOwnerList = i;
            currentPos = j;
            removal = flag;
        }
    }

    static class Range
    {

        int newListEnd;
        int newListStart;
        int oldListEnd;
        int oldListStart;

        public Range()
        {
        }

        public Range(int i, int j, int k, int l)
        {
            oldListStart = i;
            oldListEnd = j;
            newListStart = k;
            newListEnd = l;
        }
    }

    static class Snake
    {

        boolean removal;
        boolean reverse;
        int size;
        int x;
        int y;

        Snake()
        {
        }
    }


    private static final Comparator SNAKE_COMPARATOR = new Comparator() {

        public int compare(Snake snake, Snake snake1)
        {
            int j = snake.x - snake1.x;
            int i = j;
            if (j == 0)
            {
                i = snake.y - snake1.y;
            }
            return i;
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((Snake)obj, (Snake)obj1);
        }

    };

    private DiffUtil()
    {
    }

    public static DiffResult calculateDiff(Callback callback)
    {
        return calculateDiff(callback, true);
    }

    public static DiffResult calculateDiff(Callback callback, boolean flag)
    {
        int i = callback.getOldListSize();
        int j = callback.getNewListSize();
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist1 = new ArrayList();
        arraylist1.add(new Range(0, i, 0, j));
        i = i + j + Math.abs(i - j);
        int ai[] = new int[i * 2];
        int ai1[] = new int[i * 2];
        ArrayList arraylist2 = new ArrayList();
        while (!arraylist1.isEmpty()) 
        {
            Range range1 = (Range)arraylist1.remove(arraylist1.size() - 1);
            Snake snake = diffPartial(callback, range1.oldListStart, range1.oldListEnd, range1.newListStart, range1.newListEnd, ai, ai1, i);
            if (snake != null)
            {
                if (snake.size > 0)
                {
                    arraylist.add(snake);
                }
                snake.x = snake.x + range1.oldListStart;
                snake.y = snake.y + range1.newListStart;
                Range range;
                if (arraylist2.isEmpty())
                {
                    range = new Range();
                } else
                {
                    range = (Range)arraylist2.remove(arraylist2.size() - 1);
                }
                range.oldListStart = range1.oldListStart;
                range.newListStart = range1.newListStart;
                if (snake.reverse)
                {
                    range.oldListEnd = snake.x;
                    range.newListEnd = snake.y;
                } else
                if (snake.removal)
                {
                    range.oldListEnd = snake.x - 1;
                    range.newListEnd = snake.y;
                } else
                {
                    range.oldListEnd = snake.x;
                    range.newListEnd = snake.y - 1;
                }
                arraylist1.add(range);
                if (snake.reverse)
                {
                    if (snake.removal)
                    {
                        range1.oldListStart = snake.x + snake.size + 1;
                        range1.newListStart = snake.y + snake.size;
                    } else
                    {
                        range1.oldListStart = snake.x + snake.size;
                        range1.newListStart = snake.y + snake.size + 1;
                    }
                } else
                {
                    range1.oldListStart = snake.x + snake.size;
                    range1.newListStart = snake.y + snake.size;
                }
                arraylist1.add(range1);
            } else
            {
                arraylist2.add(range1);
            }
        }
        Collections.sort(arraylist, SNAKE_COMPARATOR);
        return new DiffResult(callback, arraylist, ai, ai1, flag);
    }

    private static Snake diffPartial(Callback callback, int i, int j, int k, int l, int ai[], int ai1[], int i1)
    {
        int k2 = j - i;
        int l2 = l - k;
        if (j - i < 1 || l - k < 1)
        {
            return null;
        }
        int i3 = k2 - l2;
        int j3 = (k2 + l2 + 1) / 2;
        Arrays.fill(ai, i1 - j3 - 1, i1 + j3 + 1, 0);
        Arrays.fill(ai1, (i1 - j3 - 1) + i3, i1 + j3 + 1 + i3, k2);
        int j1;
        if (i3 % 2 != 0)
        {
            l = 1;
        } else
        {
            l = 0;
        }
        for (j1 = 0; j1 <= j3; j1++)
        {
            for (int k1 = -j1; k1 <= j1; k1 += 2)
            {
                int i2;
                boolean flag;
                if (k1 == -j1 || k1 != j1 && ai[(i1 + k1) - 1] < ai[i1 + k1 + 1])
                {
                    j = ai[i1 + k1 + 1];
                    flag = false;
                } else
                {
                    j = ai[(i1 + k1) - 1] + 1;
                    flag = true;
                }
                for (i2 = j - k1; j < k2 && i2 < l2 && callback.areItemsTheSame(i + j, k + i2); i2++)
                {
                    j++;
                }

                ai[i1 + k1] = j;
                if (l != 0 && k1 >= (i3 - j1) + 1 && k1 <= (i3 + j1) - 1 && ai[i1 + k1] >= ai1[i1 + k1])
                {
                    callback = new Snake();
                    callback.x = ai1[i1 + k1];
                    callback.y = ((Snake) (callback)).x - k1;
                    callback.size = ai[i1 + k1] - ai1[i1 + k1];
                    callback.removal = flag;
                    callback.reverse = false;
                    return callback;
                }
            }

            for (int l1 = -j1; l1 <= j1; l1 += 2)
            {
                int k3 = l1 + i3;
                int j2;
                boolean flag1;
                if (k3 == j1 + i3 || k3 != -j1 + i3 && ai1[(i1 + k3) - 1] < ai1[i1 + k3 + 1])
                {
                    j = ai1[(i1 + k3) - 1];
                    flag1 = false;
                } else
                {
                    j = ai1[i1 + k3 + 1] - 1;
                    flag1 = true;
                }
                for (j2 = j - k3; j > 0 && j2 > 0 && callback.areItemsTheSame((i + j) - 1, (k + j2) - 1); j2--)
                {
                    j--;
                }

                ai1[i1 + k3] = j;
                if (l == 0 && l1 + i3 >= -j1 && l1 + i3 <= j1 && ai[i1 + k3] >= ai1[i1 + k3])
                {
                    callback = new Snake();
                    callback.x = ai1[i1 + k3];
                    callback.y = ((Snake) (callback)).x - k3;
                    callback.size = ai[i1 + k3] - ai1[i1 + k3];
                    callback.removal = flag1;
                    callback.reverse = true;
                    return callback;
                }
            }

        }

        throw new IllegalStateException("DiffUtil hit an unexpected case while trying to calculate the optimal path. Please make sure your data is not changing during the diff calculation.");
    }

}
