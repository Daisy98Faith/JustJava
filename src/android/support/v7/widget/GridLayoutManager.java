// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import java.util.Arrays;

// Referenced classes of package android.support.v7.widget:
//            LinearLayoutManager, OrientationHelper, RecyclerView

public class GridLayoutManager extends LinearLayoutManager
{
    public static final class DefaultSpanSizeLookup extends SpanSizeLookup
    {

        public int getSpanIndex(int i, int j)
        {
            return i % j;
        }

        public int getSpanSize(int i)
        {
            return 1;
        }

        public DefaultSpanSizeLookup()
        {
        }
    }

    public static class LayoutParams extends RecyclerView.LayoutParams
    {

        public static final int INVALID_SPAN_ID = -1;
        int mSpanIndex;
        int mSpanSize;

        public int getSpanIndex()
        {
            return mSpanIndex;
        }

        public int getSpanSize()
        {
            return mSpanSize;
        }

        public LayoutParams(int i, int j)
        {
            super(i, j);
            mSpanIndex = -1;
            mSpanSize = 0;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            mSpanIndex = -1;
            mSpanSize = 0;
        }

        public LayoutParams(RecyclerView.LayoutParams layoutparams)
        {
            super(layoutparams);
            mSpanIndex = -1;
            mSpanSize = 0;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            mSpanIndex = -1;
            mSpanSize = 0;
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
            mSpanIndex = -1;
            mSpanSize = 0;
        }
    }

    public static abstract class SpanSizeLookup
    {

        private boolean mCacheSpanIndices;
        final SparseIntArray mSpanIndexCache = new SparseIntArray();

        int findReferenceIndexFromCache(int i)
        {
            int j = 0;
            for (int k = mSpanIndexCache.size() - 1; j <= k;)
            {
                int l = j + k >>> 1;
                if (mSpanIndexCache.keyAt(l) < i)
                {
                    j = l + 1;
                } else
                {
                    k = l - 1;
                }
            }

            i = j - 1;
            if (i >= 0 && i < mSpanIndexCache.size())
            {
                return mSpanIndexCache.keyAt(i);
            } else
            {
                return -1;
            }
        }

        int getCachedSpanIndex(int i, int j)
        {
            int k;
            if (!mCacheSpanIndices)
            {
                k = getSpanIndex(i, j);
            } else
            {
                int l = mSpanIndexCache.get(i, -1);
                k = l;
                if (l == -1)
                {
                    j = getSpanIndex(i, j);
                    mSpanIndexCache.put(i, j);
                    return j;
                }
            }
            return k;
        }

        public int getSpanGroupIndex(int i, int j)
        {
            int k = 0;
            int l = 0;
            int i2 = getSpanSize(i);
            int j1 = 0;
            while (j1 < i) 
            {
                int k1 = getSpanSize(j1);
                int l1 = k + k1;
                int i1;
                if (l1 == j)
                {
                    k = 0;
                    i1 = l + 1;
                } else
                {
                    i1 = l;
                    k = l1;
                    if (l1 > j)
                    {
                        k = k1;
                        i1 = l + 1;
                    }
                }
                j1++;
                l = i1;
            }
            i = l;
            if (k + i2 > j)
            {
                i = l + 1;
            }
            return i;
        }

        public int getSpanIndex(int i, int j)
        {
            int k1 = getSpanSize(i);
            if (k1 == j)
            {
                i = 0;
            } else
            {
                boolean flag = false;
                boolean flag1 = false;
                int k = ((flag) ? 1 : 0);
                int l = ((flag1) ? 1 : 0);
                if (mCacheSpanIndices)
                {
                    k = ((flag) ? 1 : 0);
                    l = ((flag1) ? 1 : 0);
                    if (mSpanIndexCache.size() > 0)
                    {
                        int l1 = findReferenceIndexFromCache(i);
                        k = ((flag) ? 1 : 0);
                        l = ((flag1) ? 1 : 0);
                        if (l1 >= 0)
                        {
                            k = mSpanIndexCache.get(l1) + getSpanSize(l1);
                            l = l1 + 1;
                        }
                    }
                }
                while (l < i) 
                {
                    int i1 = getSpanSize(l);
                    int j1 = k + i1;
                    if (j1 == j)
                    {
                        k = 0;
                    } else
                    {
                        k = j1;
                        if (j1 > j)
                        {
                            k = i1;
                        }
                    }
                    l++;
                }
                i = k;
                if (k + k1 > j)
                {
                    return 0;
                }
            }
            return i;
        }

        public abstract int getSpanSize(int i);

        public void invalidateSpanIndexCache()
        {
            mSpanIndexCache.clear();
        }

        public boolean isSpanIndexCacheEnabled()
        {
            return mCacheSpanIndices;
        }

        public void setSpanIndexCacheEnabled(boolean flag)
        {
            mCacheSpanIndices = flag;
        }

        public SpanSizeLookup()
        {
            mCacheSpanIndices = false;
        }
    }


    private static final boolean DEBUG = false;
    public static final int DEFAULT_SPAN_COUNT = -1;
    private static final String TAG = "GridLayoutManager";
    int mCachedBorders[];
    final Rect mDecorInsets;
    boolean mPendingSpanCountChange;
    final SparseIntArray mPreLayoutSpanIndexCache;
    final SparseIntArray mPreLayoutSpanSizeCache;
    View mSet[];
    int mSpanCount;
    SpanSizeLookup mSpanSizeLookup;

    public GridLayoutManager(Context context, int i)
    {
        super(context);
        mPendingSpanCountChange = false;
        mSpanCount = -1;
        mPreLayoutSpanSizeCache = new SparseIntArray();
        mPreLayoutSpanIndexCache = new SparseIntArray();
        mSpanSizeLookup = new DefaultSpanSizeLookup();
        mDecorInsets = new Rect();
        setSpanCount(i);
    }

    public GridLayoutManager(Context context, int i, int j, boolean flag)
    {
        super(context, j, flag);
        mPendingSpanCountChange = false;
        mSpanCount = -1;
        mPreLayoutSpanSizeCache = new SparseIntArray();
        mPreLayoutSpanIndexCache = new SparseIntArray();
        mSpanSizeLookup = new DefaultSpanSizeLookup();
        mDecorInsets = new Rect();
        setSpanCount(i);
    }

    public GridLayoutManager(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mPendingSpanCountChange = false;
        mSpanCount = -1;
        mPreLayoutSpanSizeCache = new SparseIntArray();
        mPreLayoutSpanIndexCache = new SparseIntArray();
        mSpanSizeLookup = new DefaultSpanSizeLookup();
        mDecorInsets = new Rect();
        setSpanCount(getProperties(context, attributeset, i, j).spanCount);
    }

    private void assignSpans(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int j, boolean flag)
    {
        int k;
        int l;
        if (flag)
        {
            boolean flag1 = false;
            k = i;
            j = 1;
            i = ((flag1) ? 1 : 0);
        } else
        {
            i--;
            k = -1;
            j = -1;
        }
        l = 0;
        for (; i != k; i += j)
        {
            View view = mSet[i];
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            layoutparams.mSpanSize = getSpanSize(recycler, state, getPosition(view));
            layoutparams.mSpanIndex = l;
            l += layoutparams.mSpanSize;
        }

    }

    private void cachePreLayoutSpanMapping()
    {
        int j = getChildCount();
        for (int i = 0; i < j; i++)
        {
            LayoutParams layoutparams = (LayoutParams)getChildAt(i).getLayoutParams();
            int k = layoutparams.getViewLayoutPosition();
            mPreLayoutSpanSizeCache.put(k, layoutparams.getSpanSize());
            mPreLayoutSpanIndexCache.put(k, layoutparams.getSpanIndex());
        }

    }

    private void calculateItemBorders(int i)
    {
        mCachedBorders = calculateItemBorders(mCachedBorders, mSpanCount, i);
    }

    static int[] calculateItemBorders(int ai[], int i, int j)
    {
        int ai1[];
label0:
        {
            if (ai != null && ai.length == i + 1)
            {
                ai1 = ai;
                if (ai[ai.length - 1] == j)
                {
                    break label0;
                }
            }
            ai1 = new int[i + 1];
        }
        ai1[0] = 0;
        int k1 = j / i;
        int i2 = j % i;
        int l = 0;
        j = 0;
        for (int k = 1; k <= i; k++)
        {
            int i1 = k1;
            int l1 = j + i2;
            j = l1;
            int j1 = i1;
            if (l1 > 0)
            {
                j = l1;
                j1 = i1;
                if (i - l1 < i2)
                {
                    j1 = i1 + 1;
                    j = l1 - i;
                }
            }
            l += j1;
            ai1[k] = l;
        }

        return ai1;
    }

    private void clearPreLayoutSpanMappingCache()
    {
        mPreLayoutSpanSizeCache.clear();
        mPreLayoutSpanIndexCache.clear();
    }

    private void ensureAnchorIsInCorrectSpan(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.AnchorInfo anchorinfo, int i)
    {
        boolean flag = true;
        if (i != 1)
        {
            flag = false;
        }
        i = getSpanIndex(recycler, state, anchorinfo.mPosition);
        if (flag)
        {
            for (; i > 0 && anchorinfo.mPosition > 0; i = getSpanIndex(recycler, state, anchorinfo.mPosition))
            {
                anchorinfo.mPosition = anchorinfo.mPosition - 1;
            }

        } else
        {
            int l = state.getItemCount();
            int j = anchorinfo.mPosition;
            do
            {
                if (j >= l - 1)
                {
                    break;
                }
                int k = getSpanIndex(recycler, state, j + 1);
                if (k <= i)
                {
                    break;
                }
                j++;
                i = k;
            } while (true);
            anchorinfo.mPosition = j;
        }
    }

    private void ensureViewSet()
    {
        if (mSet == null || mSet.length != mSpanCount)
        {
            mSet = new View[mSpanCount];
        }
    }

    private int getSpanGroupIndex(RecyclerView.Recycler recycler, RecyclerView.State state, int i)
    {
        if (!state.isPreLayout())
        {
            return mSpanSizeLookup.getSpanGroupIndex(i, mSpanCount);
        }
        int j = recycler.convertPreLayoutPositionToPostLayout(i);
        if (j == -1)
        {
            Log.w("GridLayoutManager", (new StringBuilder()).append("Cannot find span size for pre layout position. ").append(i).toString());
            return 0;
        } else
        {
            return mSpanSizeLookup.getSpanGroupIndex(j, mSpanCount);
        }
    }

    private int getSpanIndex(RecyclerView.Recycler recycler, RecyclerView.State state, int i)
    {
        int j;
        if (!state.isPreLayout())
        {
            j = mSpanSizeLookup.getCachedSpanIndex(i, mSpanCount);
        } else
        {
            int l = mPreLayoutSpanIndexCache.get(i, -1);
            j = l;
            if (l == -1)
            {
                int k = recycler.convertPreLayoutPositionToPostLayout(i);
                if (k == -1)
                {
                    Log.w("GridLayoutManager", (new StringBuilder()).append("Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:").append(i).toString());
                    return 0;
                } else
                {
                    return mSpanSizeLookup.getCachedSpanIndex(k, mSpanCount);
                }
            }
        }
        return j;
    }

    private int getSpanSize(RecyclerView.Recycler recycler, RecyclerView.State state, int i)
    {
        int j;
        if (!state.isPreLayout())
        {
            j = mSpanSizeLookup.getSpanSize(i);
        } else
        {
            int l = mPreLayoutSpanSizeCache.get(i, -1);
            j = l;
            if (l == -1)
            {
                int k = recycler.convertPreLayoutPositionToPostLayout(i);
                if (k == -1)
                {
                    Log.w("GridLayoutManager", (new StringBuilder()).append("Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:").append(i).toString());
                    return 1;
                } else
                {
                    return mSpanSizeLookup.getSpanSize(k);
                }
            }
        }
        return j;
    }

    private void guessMeasurement(float f, int i)
    {
        calculateItemBorders(Math.max(Math.round((float)mSpanCount * f), i));
    }

    private void measureChild(View view, int i, boolean flag)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        Rect rect = layoutparams.mDecorInsets;
        int k = rect.top + rect.bottom + layoutparams.topMargin + layoutparams.bottomMargin;
        int j = rect.left + rect.right + layoutparams.leftMargin + layoutparams.rightMargin;
        int l = getSpaceForSpanRange(layoutparams.mSpanIndex, layoutparams.mSpanSize);
        if (mOrientation == 1)
        {
            j = getChildMeasureSpec(l, i, j, layoutparams.width, false);
            i = getChildMeasureSpec(mOrientationHelper.getTotalSpace(), getHeightMode(), k, layoutparams.height, true);
        } else
        {
            i = getChildMeasureSpec(l, i, k, layoutparams.height, false);
            j = getChildMeasureSpec(mOrientationHelper.getTotalSpace(), getWidthMode(), j, layoutparams.width, true);
        }
        measureChildWithDecorationsAndMargin(view, j, i, flag);
    }

    private void measureChildWithDecorationsAndMargin(View view, int i, int j, boolean flag)
    {
        RecyclerView.LayoutParams layoutparams = (RecyclerView.LayoutParams)view.getLayoutParams();
        if (flag)
        {
            flag = shouldReMeasureChild(view, i, j, layoutparams);
        } else
        {
            flag = shouldMeasureChild(view, i, j, layoutparams);
        }
        if (flag)
        {
            view.measure(i, j);
        }
    }

    private void updateMeasurements()
    {
        int i;
        if (getOrientation() == 1)
        {
            i = getWidth() - getPaddingRight() - getPaddingLeft();
        } else
        {
            i = getHeight() - getPaddingBottom() - getPaddingTop();
        }
        calculateItemBorders(i);
    }

    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutparams)
    {
        return layoutparams instanceof LayoutParams;
    }

    void collectPrefetchPositionsForLayoutState(RecyclerView.State state, LinearLayoutManager.LayoutState layoutstate, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutprefetchregistry)
    {
        int j = mSpanCount;
        for (int i = 0; i < mSpanCount && layoutstate.hasMore(state) && j > 0; i++)
        {
            int k = layoutstate.mCurrentPosition;
            layoutprefetchregistry.addPosition(k, Math.max(0, layoutstate.mScrollingOffset));
            j -= mSpanSizeLookup.getSpanSize(k);
            layoutstate.mCurrentPosition = layoutstate.mCurrentPosition + layoutstate.mItemDirection;
        }

    }

    View findReferenceChild(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int j, int k)
    {
label0:
        {
            ensureLayoutState();
            Object obj1 = null;
            Object obj = null;
            int i1 = mOrientationHelper.getStartAfterPadding();
            int j1 = mOrientationHelper.getEndAfterPadding();
            View view1;
            int l;
            if (j > i)
            {
                l = 1;
            } else
            {
                l = -1;
            }
            while (i != j) 
            {
                View view = getChildAt(i);
                int k1 = getPosition(view);
                view1 = obj1;
                View view2 = obj;
                if (k1 >= 0)
                {
                    view1 = obj1;
                    view2 = obj;
                    if (k1 < k)
                    {
                        if (getSpanIndex(recycler, state, k1) != 0)
                        {
                            view2 = obj;
                            view1 = obj1;
                        } else
                        if (((RecyclerView.LayoutParams)view.getLayoutParams()).isItemRemoved())
                        {
                            view1 = obj1;
                            view2 = obj;
                            if (obj1 == null)
                            {
                                view1 = view;
                                view2 = obj;
                            }
                        } else
                        {
                            if (mOrientationHelper.getDecoratedStart(view) < j1)
                            {
                                view1 = view;
                                if (mOrientationHelper.getDecoratedEnd(view) >= i1)
                                {
                                    break label0;
                                }
                            }
                            view1 = obj1;
                            view2 = obj;
                            if (obj == null)
                            {
                                view1 = obj1;
                                view2 = view;
                            }
                        }
                    }
                }
                i += l;
                obj1 = view1;
                obj = view2;
            }
            if (obj == null)
            {
                obj = obj1;
            }
            view1 = obj;
        }
        return view1;
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams()
    {
        if (mOrientation == 0)
        {
            return new LayoutParams(-2, -1);
        } else
        {
            return new LayoutParams(-1, -2);
        }
    }

    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeset)
    {
        return new LayoutParams(context, attributeset);
    }

    public RecyclerView.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        if (layoutparams instanceof android.view.ViewGroup.MarginLayoutParams)
        {
            return new LayoutParams((android.view.ViewGroup.MarginLayoutParams)layoutparams);
        } else
        {
            return new LayoutParams(layoutparams);
        }
    }

    public int getColumnCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        if (mOrientation == 1)
        {
            return mSpanCount;
        }
        if (state.getItemCount() < 1)
        {
            return 0;
        } else
        {
            return getSpanGroupIndex(recycler, state, state.getItemCount() - 1) + 1;
        }
    }

    public int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        if (mOrientation == 0)
        {
            return mSpanCount;
        }
        if (state.getItemCount() < 1)
        {
            return 0;
        } else
        {
            return getSpanGroupIndex(recycler, state, state.getItemCount() - 1) + 1;
        }
    }

    int getSpaceForSpanRange(int i, int j)
    {
        if (mOrientation == 1 && isLayoutRTL())
        {
            return mCachedBorders[mSpanCount - i] - mCachedBorders[mSpanCount - i - j];
        } else
        {
            return mCachedBorders[i + j] - mCachedBorders[i];
        }
    }

    public int getSpanCount()
    {
        return mSpanCount;
    }

    public SpanSizeLookup getSpanSizeLookup()
    {
        return mSpanSizeLookup;
    }

    void layoutChunk(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.LayoutState layoutstate, LinearLayoutManager.LayoutChunkResult layoutchunkresult)
    {
        int i;
        boolean flag;
        int j1;
        int k1;
        int l1;
        int i2;
        int i4;
        boolean flag1;
        i4 = mOrientationHelper.getModeInOther();
        int j3;
        if (i4 != 0x40000000)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (getChildCount() > 0)
        {
            k1 = mCachedBorders[mSpanCount];
        } else
        {
            k1 = 0;
        }
        if (flag)
        {
            updateMeasurements();
        }
        if (layoutstate.mItemDirection == 1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        i2 = 0;
        j3 = 0;
        i = mSpanCount;
        l1 = i2;
        j1 = j3;
        if (!flag1)
        {
            i = getSpanIndex(recycler, state, layoutstate.mCurrentPosition) + getSpanSize(recycler, state, layoutstate.mCurrentPosition);
            j1 = j3;
            l1 = i2;
        }
_L6:
        if (l1 >= mSpanCount || !layoutstate.hasMore(state) || i <= 0) goto _L2; else goto _L1
_L1:
        j3 = layoutstate.mCurrentPosition;
        i2 = getSpanSize(recycler, state, j3);
        if (i2 > mSpanCount)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Item at position ").append(j3).append(" requires ").append(i2).append(" spans but GridLayoutManager has only ").append(mSpanCount).append(" spans.").toString());
        }
        i -= i2;
        if (i >= 0) goto _L3; else goto _L2
_L2:
        View view;
        if (l1 == 0)
        {
            layoutchunkresult.mFinished = true;
            return;
        }
        break; /* Loop/switch isn't completed */
_L3:
        if ((view = layoutstate.next(recycler)) == null) goto _L2; else goto _L4
_L4:
        j1 += i2;
        mSet[l1] = view;
        l1++;
        if (true) goto _L6; else goto _L5
_L5:
        int j = 0;
        float f = 0.0F;
        assignSpans(recycler, state, l1, j1, flag1);
        j1 = 0;
        while (j1 < l1) 
        {
            recycler = mSet[j1];
            float f1;
            float f2;
            int j2;
            int k3;
            if (layoutstate.mScrapList == null)
            {
                if (flag1)
                {
                    addView(recycler);
                } else
                {
                    addView(recycler, 0);
                }
            } else
            if (flag1)
            {
                addDisappearingView(recycler);
            } else
            {
                addDisappearingView(recycler, 0);
            }
            calculateItemDecorationsForChild(recycler, mDecorInsets);
            measureChild(recycler, i4, false);
            k3 = mOrientationHelper.getDecoratedMeasurement(recycler);
            j2 = j;
            if (k3 > j)
            {
                j2 = k3;
            }
            state = (LayoutParams)recycler.getLayoutParams();
            f2 = (1.0F * (float)mOrientationHelper.getDecoratedMeasurementInOther(recycler)) / (float)((LayoutParams) (state)).mSpanSize;
            f1 = f;
            if (f2 > f)
            {
                f1 = f2;
            }
            j1++;
            j = j2;
            f = f1;
        }
        j1 = j;
        if (flag)
        {
            guessMeasurement(f, k1);
            j = 0;
            int k = 0;
            do
            {
                j1 = j;
                if (k >= l1)
                {
                    break;
                }
                recycler = mSet[k];
                measureChild(recycler, 0x40000000, true);
                k1 = mOrientationHelper.getDecoratedMeasurement(recycler);
                j1 = j;
                if (k1 > j)
                {
                    j1 = k1;
                }
                k++;
                j = j1;
            } while (true);
        }
        j = 0;
        while (j < l1) 
        {
            recycler = mSet[j];
            if (mOrientationHelper.getDecoratedMeasurement(recycler) != j1)
            {
                state = (LayoutParams)recycler.getLayoutParams();
                Rect rect = ((LayoutParams) (state)).mDecorInsets;
                int l = rect.top + rect.bottom + ((LayoutParams) (state)).topMargin + ((LayoutParams) (state)).bottomMargin;
                k1 = rect.left + rect.right + ((LayoutParams) (state)).leftMargin + ((LayoutParams) (state)).rightMargin;
                int k2 = getSpaceForSpanRange(((LayoutParams) (state)).mSpanIndex, ((LayoutParams) (state)).mSpanSize);
                if (mOrientation == 1)
                {
                    k1 = getChildMeasureSpec(k2, 0x40000000, k1, ((LayoutParams) (state)).width, false);
                    l = android.view.View.MeasureSpec.makeMeasureSpec(j1 - l, 0x40000000);
                } else
                {
                    k1 = android.view.View.MeasureSpec.makeMeasureSpec(j1 - k1, 0x40000000);
                    l = getChildMeasureSpec(k2, 0x40000000, l, ((LayoutParams) (state)).height, false);
                }
                measureChildWithDecorationsAndMargin(recycler, k1, l, true);
            }
            j++;
        }
        layoutchunkresult.mConsumed = j1;
        j = 0;
        int l2 = 0;
        int i1 = 0;
        k1 = 0;
        int l3;
        if (mOrientation == 1)
        {
            if (layoutstate.mLayoutDirection == -1)
            {
                k1 = layoutstate.mOffset;
                i1 = k1 - j1;
                j1 = l2;
            } else
            {
                i1 = layoutstate.mOffset;
                k1 = i1 + j1;
                j1 = l2;
            }
        } else
        if (layoutstate.mLayoutDirection == -1)
        {
            int i3 = layoutstate.mOffset;
            j = i3 - j1;
            j1 = i3;
        } else
        {
            j = layoutstate.mOffset;
            j1 = j + j1;
        }
        l2 = 0;
        l3 = j1;
        j1 = i1;
        while (l2 < l1) 
        {
            recycler = mSet[l2];
            state = (LayoutParams)recycler.getLayoutParams();
            if (mOrientation == 1)
            {
                if (isLayoutRTL())
                {
                    i1 = getPaddingLeft() + mCachedBorders[mSpanCount - ((LayoutParams) (state)).mSpanIndex];
                    j = i1 - mOrientationHelper.getDecoratedMeasurementInOther(recycler);
                } else
                {
                    j = getPaddingLeft() + mCachedBorders[((LayoutParams) (state)).mSpanIndex];
                    i1 = j + mOrientationHelper.getDecoratedMeasurementInOther(recycler);
                }
            } else
            {
                j1 = getPaddingTop() + mCachedBorders[((LayoutParams) (state)).mSpanIndex];
                k1 = j1 + mOrientationHelper.getDecoratedMeasurementInOther(recycler);
                i1 = l3;
            }
            layoutDecoratedWithMargins(recycler, j, j1, i1, k1);
            if (state.isItemRemoved() || state.isItemChanged())
            {
                layoutchunkresult.mIgnoreConsumed = true;
            }
            layoutchunkresult.mFocusable = layoutchunkresult.mFocusable | recycler.hasFocusable();
            l2++;
            l3 = i1;
        }
        Arrays.fill(mSet, null);
        return;
    }

    void onAnchorReady(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.AnchorInfo anchorinfo, int i)
    {
        super.onAnchorReady(recycler, state, anchorinfo, i);
        updateMeasurements();
        if (state.getItemCount() > 0 && !state.isPreLayout())
        {
            ensureAnchorIsInCorrectSpan(recycler, state, anchorinfo, i);
        }
        ensureViewSet();
    }

    public View onFocusSearchFailed(View view, int i, RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        View view4 = findContainingItemView(view);
        if (view4 != null) goto _L2; else goto _L1
_L1:
        View view2 = null;
_L10:
        return view2;
_L2:
        Object obj;
        View view1;
        byte byte0;
        int k;
        int l;
        byte byte1;
        int i1;
        int j1;
        int k1;
        int l2;
        int i3;
        int j3;
        obj = (LayoutParams)view4.getLayoutParams();
        l2 = ((LayoutParams) (obj)).mSpanIndex;
        i3 = ((LayoutParams) (obj)).mSpanIndex + ((LayoutParams) (obj)).mSpanSize;
        if (super.onFocusSearchFailed(view, i, recycler, state) == null)
        {
            return null;
        }
        int j;
        boolean flag;
        if (convertFocusDirectionToLayoutDirection(i) == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag != mShouldReverseLayout)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            i = getChildCount() - 1;
            byte0 = -1;
            j = -1;
        } else
        {
            i = 0;
            byte0 = 1;
            j = getChildCount();
        }
        if (mOrientation == 1 && isLayoutRTL())
        {
            k = 1;
        } else
        {
            k = 0;
        }
        obj = null;
        j1 = -1;
        k1 = 0;
        view = null;
        byte1 = -1;
        i1 = 0;
        j3 = getSpanGroupIndex(recycler, state, i);
        l = i;
_L8:
        if (l == j) goto _L4; else goto _L3
_L3:
        i = getSpanGroupIndex(recycler, state, l);
        view1 = getChildAt(l);
        if (view1 != view4) goto _L5; else goto _L4
_L4:
        View view3;
        LayoutParams layoutparams;
        int l1;
        int i2;
        int j2;
        int k2;
        int k3;
        int l3;
        if (obj == null)
        {
            obj = view;
        }
        return ((View) (obj));
_L5:
        if (!view1.hasFocusable() || i == j3)
        {
            break; /* Loop/switch isn't completed */
        }
        if (obj != null) goto _L4; else goto _L6
_L6:
        k2 = byte1;
        j2 = i1;
        view3 = view;
        i2 = j1;
        l1 = k1;
        view2 = ((View) (obj));
_L11:
        l += byte0;
        obj = view2;
        k1 = l1;
        j1 = i2;
        view = view3;
        i1 = j2;
        byte1 = k2;
        if (true) goto _L8; else goto _L7
_L7:
        layoutparams = (LayoutParams)view1.getLayoutParams();
        k3 = layoutparams.mSpanIndex;
        l3 = layoutparams.mSpanIndex + layoutparams.mSpanSize;
        if (!view1.hasFocusable() || k3 != l2)
        {
            break; /* Loop/switch isn't completed */
        }
        view2 = view1;
        if (l3 == i3) goto _L10; else goto _L9
_L9:
        i2 = 0;
        if (view1.hasFocusable() && obj == null || !view1.hasFocusable() && view == null)
        {
            i = 1;
        } else
        {
            i = Math.max(k3, l2);
            l1 = Math.min(l3, i3) - i;
            if (view1.hasFocusable())
            {
                if (l1 > k1)
                {
                    i = 1;
                } else
                {
                    i = i2;
                    if (l1 == k1)
                    {
                        if (k3 > j1)
                        {
                            l1 = 1;
                        } else
                        {
                            l1 = 0;
                        }
                        i = i2;
                        if (k == l1)
                        {
                            i = 1;
                        }
                    }
                }
            } else
            {
                i = i2;
                if (obj == null)
                {
                    i = i2;
                    if (isViewPartiallyVisible(view1, false, true))
                    {
                        if (l1 > i1)
                        {
                            i = 1;
                        } else
                        {
                            i = i2;
                            if (l1 == i1)
                            {
                                if (k3 > byte1)
                                {
                                    l1 = 1;
                                } else
                                {
                                    l1 = 0;
                                }
                                i = i2;
                                if (k == l1)
                                {
                                    i = 1;
                                }
                            }
                        }
                    }
                }
            }
        }
        view2 = ((View) (obj));
        l1 = k1;
        i2 = j1;
        view3 = view;
        j2 = i1;
        k2 = byte1;
        if (i != 0)
        {
            if (view1.hasFocusable())
            {
                i2 = layoutparams.mSpanIndex;
                l1 = Math.min(l3, i3) - Math.max(k3, l2);
                view2 = view1;
                view3 = view;
                j2 = i1;
                k2 = byte1;
            } else
            {
                k2 = layoutparams.mSpanIndex;
                j2 = Math.min(l3, i3) - Math.max(k3, l2);
                view2 = ((View) (obj));
                l1 = k1;
                i2 = j1;
                view3 = view1;
            }
        }
          goto _L11
    }

    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
        if (!(layoutparams instanceof LayoutParams))
        {
            super.onInitializeAccessibilityNodeInfoForItem(view, accessibilitynodeinfocompat);
            return;
        }
        view = (LayoutParams)layoutparams;
        int i = getSpanGroupIndex(recycler, state, view.getViewLayoutPosition());
        if (mOrientation == 0)
        {
            int j = view.getSpanIndex();
            int l = view.getSpanSize();
            boolean flag;
            if (mSpanCount > 1 && view.getSpanSize() == mSpanCount)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            accessibilitynodeinfocompat.setCollectionItemInfo(android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(j, l, i, 1, flag, false));
            return;
        }
        int k = view.getSpanIndex();
        int i1 = view.getSpanSize();
        boolean flag1;
        if (mSpanCount > 1 && view.getSpanSize() == mSpanCount)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        accessibilitynodeinfocompat.setCollectionItemInfo(android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(i, 1, k, i1, flag1, false));
    }

    public void onItemsAdded(RecyclerView recyclerview, int i, int j)
    {
        mSpanSizeLookup.invalidateSpanIndexCache();
    }

    public void onItemsChanged(RecyclerView recyclerview)
    {
        mSpanSizeLookup.invalidateSpanIndexCache();
    }

    public void onItemsMoved(RecyclerView recyclerview, int i, int j, int k)
    {
        mSpanSizeLookup.invalidateSpanIndexCache();
    }

    public void onItemsRemoved(RecyclerView recyclerview, int i, int j)
    {
        mSpanSizeLookup.invalidateSpanIndexCache();
    }

    public void onItemsUpdated(RecyclerView recyclerview, int i, int j, Object obj)
    {
        mSpanSizeLookup.invalidateSpanIndexCache();
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        if (state.isPreLayout())
        {
            cachePreLayoutSpanMapping();
        }
        super.onLayoutChildren(recycler, state);
        clearPreLayoutSpanMappingCache();
    }

    public void onLayoutCompleted(RecyclerView.State state)
    {
        super.onLayoutCompleted(state);
        mPendingSpanCountChange = false;
    }

    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        updateMeasurements();
        ensureViewSet();
        return super.scrollHorizontallyBy(i, recycler, state);
    }

    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        updateMeasurements();
        ensureViewSet();
        return super.scrollVerticallyBy(i, recycler, state);
    }

    public void setMeasuredDimension(Rect rect, int i, int j)
    {
        if (mCachedBorders == null)
        {
            super.setMeasuredDimension(rect, i, j);
        }
        int i1 = getPaddingLeft() + getPaddingRight();
        int j1 = getPaddingTop() + getPaddingBottom();
        if (mOrientation == 1)
        {
            int k = chooseSize(j, rect.height() + j1, getMinimumHeight());
            j = chooseSize(i, mCachedBorders[mCachedBorders.length - 1] + i1, getMinimumWidth());
            i = k;
        } else
        {
            int l = chooseSize(i, rect.width() + i1, getMinimumWidth());
            i = chooseSize(j, mCachedBorders[mCachedBorders.length - 1] + j1, getMinimumHeight());
            j = l;
        }
        setMeasuredDimension(j, i);
    }

    public void setSpanCount(int i)
    {
        if (i == mSpanCount)
        {
            return;
        }
        mPendingSpanCountChange = true;
        if (i < 1)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Span count should be at least 1. Provided ").append(i).toString());
        } else
        {
            mSpanCount = i;
            mSpanSizeLookup.invalidateSpanIndexCache();
            requestLayout();
            return;
        }
    }

    public void setSpanSizeLookup(SpanSizeLookup spansizelookup)
    {
        mSpanSizeLookup = spansizelookup;
    }

    public void setStackFromEnd(boolean flag)
    {
        if (flag)
        {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        } else
        {
            super.setStackFromEnd(false);
            return;
        }
    }

    public boolean supportsPredictiveItemAnimations()
    {
        return mPendingSavedState == null && !mPendingSpanCountChange;
    }
}
