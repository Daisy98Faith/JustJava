// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.support.v4.os.TraceCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView, ChildHelper, AdapterHelper

final class GapWorker
    implements Runnable
{
    static class LayoutPrefetchRegistryImpl
        implements RecyclerView.LayoutManager.LayoutPrefetchRegistry
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
            RecyclerView.LayoutManager layoutmanager;
            mCount = 0;
            if (mPrefetchArray != null)
            {
                Arrays.fill(mPrefetchArray, -1);
            }
            layoutmanager = recyclerview.mLayout;
            if (recyclerview.mAdapter == null || layoutmanager == null || !layoutmanager.isItemPrefetchEnabled()) goto _L2; else goto _L1
_L1:
            if (!flag) goto _L4; else goto _L3
_L3:
            if (!recyclerview.mAdapterHelper.hasPendingUpdates())
            {
                layoutmanager.collectInitialPrefetchPositions(recyclerview.mAdapter.getItemCount(), this);
            }
_L6:
            if (mCount > layoutmanager.mPrefetchMaxCountObserved)
            {
                layoutmanager.mPrefetchMaxCountObserved = mCount;
                layoutmanager.mPrefetchMaxObservedInInitialPrefetch = flag;
                recyclerview.mRecycler.updateViewCacheSize();
            }
_L2:
            return;
_L4:
            if (!recyclerview.hasPendingAdapterUpdates())
            {
                layoutmanager.collectAdjacentPrefetchPositions(mPrefetchDx, mPrefetchDy, recyclerview.mState, this);
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

        LayoutPrefetchRegistryImpl()
        {
        }
    }

    static class Task
    {

        public int distanceToItem;
        public boolean immediate;
        public int position;
        public RecyclerView view;
        public int viewVelocity;

        public void clear()
        {
            immediate = false;
            viewVelocity = 0;
            distanceToItem = 0;
            view = null;
            position = 0;
        }

        Task()
        {
        }
    }


    static final ThreadLocal sGapWorker = new ThreadLocal();
    static Comparator sTaskComparator = new Comparator() {

        public int compare(Task task, Task task1)
        {
            byte byte0 = -1;
            int i;
            boolean flag;
            if (task.view == null)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (task1.view == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (i != flag)
            {
                return task.view != null ? -1 : 1;
            }
            if (task.immediate != task1.immediate)
            {
                if (task.immediate)
                {
                    i = byte0;
                } else
                {
                    i = 1;
                }
                return i;
            }
            i = task1.viewVelocity - task.viewVelocity;
            if (i != 0)
            {
                return i;
            }
            i = task.distanceToItem - task1.distanceToItem;
            if (i != 0)
            {
                return i;
            } else
            {
                return 0;
            }
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((Task)obj, (Task)obj1);
        }

    };
    long mFrameIntervalNs;
    long mPostTimeNs;
    ArrayList mRecyclerViews;
    private ArrayList mTasks;

    GapWorker()
    {
        mRecyclerViews = new ArrayList();
        mTasks = new ArrayList();
    }

    private void buildTaskList()
    {
        int j;
        int k;
        int l1;
        l1 = mRecyclerViews.size();
        k = 0;
        for (int i = 0; i < l1;)
        {
            RecyclerView recyclerview = (RecyclerView)mRecyclerViews.get(i);
            int l = k;
            if (recyclerview.getWindowVisibility() == 0)
            {
                recyclerview.mPrefetchRegistry.collectPrefetchPositionsFromView(recyclerview, false);
                l = k + recyclerview.mPrefetchRegistry.mCount;
            }
            i++;
            k = l;
        }

        mTasks.ensureCapacity(k);
        j = 0;
        k = 0;
_L2:
        RecyclerView recyclerview1;
        int j1;
        if (k >= l1)
        {
            break MISSING_BLOCK_LABEL_300;
        }
        recyclerview1 = (RecyclerView)mRecyclerViews.get(k);
        if (recyclerview1.getWindowVisibility() == 0)
        {
            break; /* Loop/switch isn't completed */
        }
        j1 = j;
_L4:
        k++;
        j = j1;
        if (true) goto _L2; else goto _L1
_L1:
        LayoutPrefetchRegistryImpl layoutprefetchregistryimpl;
        int i1;
        int i2;
        layoutprefetchregistryimpl = recyclerview1.mPrefetchRegistry;
        i2 = Math.abs(layoutprefetchregistryimpl.mPrefetchDx) + Math.abs(layoutprefetchregistryimpl.mPrefetchDy);
        i1 = 0;
_L5:
        j1 = j;
        if (i1 >= layoutprefetchregistryimpl.mCount * 2) goto _L4; else goto _L3
_L3:
        Task task;
        int k1;
        boolean flag;
        if (j >= mTasks.size())
        {
            task = new Task();
            mTasks.add(task);
        } else
        {
            task = (Task)mTasks.get(j);
        }
        k1 = layoutprefetchregistryimpl.mPrefetchArray[i1 + 1];
        if (k1 <= i2)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        task.immediate = flag;
        task.viewVelocity = i2;
        task.distanceToItem = k1;
        task.view = recyclerview1;
        task.position = layoutprefetchregistryimpl.mPrefetchArray[i1];
        j++;
        i1 += 2;
          goto _L5
          goto _L4
        Collections.sort(mTasks, sTaskComparator);
        return;
    }

    private void flushTaskWithDeadline(Task task, long l)
    {
        long l1;
        if (task.immediate)
        {
            l1 = 0x7fffffffffffffffL;
        } else
        {
            l1 = l;
        }
        task = prefetchPositionWithDeadline(task.view, task.position, l1);
        if (task != null && ((RecyclerView.ViewHolder) (task)).mNestedRecyclerView != null)
        {
            prefetchInnerRecyclerViewWithDeadline((RecyclerView)((RecyclerView.ViewHolder) (task)).mNestedRecyclerView.get(), l);
        }
    }

    private void flushTasksWithDeadline(long l)
    {
        int i = 0;
        do
        {
            Task task;
label0:
            {
                if (i < mTasks.size())
                {
                    task = (Task)mTasks.get(i);
                    if (task.view != null)
                    {
                        break label0;
                    }
                }
                return;
            }
            flushTaskWithDeadline(task, l);
            task.clear();
            i++;
        } while (true);
    }

    static boolean isPrefetchPositionAttached(RecyclerView recyclerview, int i)
    {
        int k = recyclerview.mChildHelper.getUnfilteredChildCount();
        for (int j = 0; j < k; j++)
        {
            RecyclerView.ViewHolder viewholder = RecyclerView.getChildViewHolderInt(recyclerview.mChildHelper.getUnfilteredChildAt(j));
            if (viewholder.mPosition == i && !viewholder.isInvalid())
            {
                return true;
            }
        }

        return false;
    }

    private void prefetchInnerRecyclerViewWithDeadline(RecyclerView recyclerview, long l)
    {
        if (recyclerview != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        LayoutPrefetchRegistryImpl layoutprefetchregistryimpl;
        if (recyclerview.mDataSetHasChangedAfterLayout && recyclerview.mChildHelper.getUnfilteredChildCount() != 0)
        {
            recyclerview.removeAndRecycleViews();
        }
        layoutprefetchregistryimpl = recyclerview.mPrefetchRegistry;
        layoutprefetchregistryimpl.collectPrefetchPositionsFromView(recyclerview, true);
        if (layoutprefetchregistryimpl.mCount == 0) goto _L1; else goto _L3
_L3:
        TraceCompat.beginSection("RV Nested Prefetch");
        recyclerview.mState.prepareForNestedPrefetch(recyclerview.mAdapter);
        int i = 0;
_L5:
        if (i >= layoutprefetchregistryimpl.mCount * 2)
        {
            break; /* Loop/switch isn't completed */
        }
        prefetchPositionWithDeadline(recyclerview, layoutprefetchregistryimpl.mPrefetchArray[i], l);
        i += 2;
        if (true) goto _L5; else goto _L4
_L4:
        TraceCompat.endSection();
        return;
        recyclerview;
        TraceCompat.endSection();
        throw recyclerview;
    }

    private RecyclerView.ViewHolder prefetchPositionWithDeadline(RecyclerView recyclerview, int i, long l)
    {
        if (isPrefetchPositionAttached(recyclerview, i))
        {
            recyclerview = null;
        } else
        {
            RecyclerView.Recycler recycler = recyclerview.mRecycler;
            RecyclerView.ViewHolder viewholder = recycler.tryGetViewHolderForPositionByDeadline(i, false, l);
            recyclerview = viewholder;
            if (viewholder != null)
            {
                if (viewholder.isBound())
                {
                    recycler.recycleView(viewholder.itemView);
                    return viewholder;
                } else
                {
                    recycler.addViewHolderToRecycledViewPool(viewholder, false);
                    return viewholder;
                }
            }
        }
        return recyclerview;
    }

    public void add(RecyclerView recyclerview)
    {
        mRecyclerViews.add(recyclerview);
    }

    void postFromTraversal(RecyclerView recyclerview, int i, int j)
    {
        if (recyclerview.isAttachedToWindow() && mPostTimeNs == 0L)
        {
            mPostTimeNs = recyclerview.getNanoTime();
            recyclerview.post(this);
        }
        recyclerview.mPrefetchRegistry.setPrefetchVector(i, j);
    }

    void prefetch(long l)
    {
        buildTaskList();
        flushTasksWithDeadline(l);
    }

    public void remove(RecyclerView recyclerview)
    {
        mRecyclerViews.remove(recyclerview);
    }

    public void run()
    {
        boolean flag;
        TraceCompat.beginSection("RV Prefetch");
        flag = mRecyclerViews.isEmpty();
        if (flag)
        {
            mPostTimeNs = 0L;
            TraceCompat.endSection();
            return;
        }
        int j = mRecyclerViews.size();
        int i;
        long l;
        l = 0L;
        i = 0;
_L3:
        if (i >= j) goto _L2; else goto _L1
_L1:
        RecyclerView recyclerview = (RecyclerView)mRecyclerViews.get(i);
        long l1 = l;
        if (recyclerview.getWindowVisibility() == 0)
        {
            l1 = Math.max(recyclerview.getDrawingTime(), l);
        }
        i++;
        l = l1;
          goto _L3
_L2:
        if (l == 0L)
        {
            mPostTimeNs = 0L;
            TraceCompat.endSection();
            return;
        }
        prefetch(TimeUnit.MILLISECONDS.toNanos(l) + mFrameIntervalNs);
        mPostTimeNs = 0L;
        TraceCompat.endSection();
        return;
        Exception exception;
        exception;
        mPostTimeNs = 0L;
        TraceCompat.endSection();
        throw exception;
    }

}
