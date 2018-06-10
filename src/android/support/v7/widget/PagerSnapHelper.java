// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            SnapHelper, OrientationHelper, RecyclerView, LinearSmoothScroller

public class PagerSnapHelper extends SnapHelper
{

    private static final int MAX_SCROLL_ON_FLING_DURATION = 100;
    private OrientationHelper mHorizontalHelper;
    private OrientationHelper mVerticalHelper;

    public PagerSnapHelper()
    {
    }

    private int distanceToCenter(RecyclerView.LayoutManager layoutmanager, View view, OrientationHelper orientationhelper)
    {
        int j = orientationhelper.getDecoratedStart(view);
        int k = orientationhelper.getDecoratedMeasurement(view) / 2;
        int i;
        if (layoutmanager.getClipToPadding())
        {
            i = orientationhelper.getStartAfterPadding() + orientationhelper.getTotalSpace() / 2;
        } else
        {
            i = orientationhelper.getEnd() / 2;
        }
        return (j + k) - i;
    }

    private View findCenterView(RecyclerView.LayoutManager layoutmanager, OrientationHelper orientationhelper)
    {
        int j1 = layoutmanager.getChildCount();
        if (j1 != 0) goto _L2; else goto _L1
_L1:
        View view1 = null;
_L4:
        return view1;
_L2:
        View view = null;
        int i;
        int j;
        int k;
        int l;
        int i1;
        if (layoutmanager.getClipToPadding())
        {
            i = orientationhelper.getStartAfterPadding() + orientationhelper.getTotalSpace() / 2;
        } else
        {
            i = orientationhelper.getEnd() / 2;
        }
        k = 0x7fffffff;
        j = 0;
        view1 = view;
        if (j < j1)
        {
            view1 = layoutmanager.getChildAt(j);
            i1 = Math.abs((orientationhelper.getDecoratedStart(view1) + orientationhelper.getDecoratedMeasurement(view1) / 2) - i);
            l = k;
            if (i1 < k)
            {
                l = i1;
                view = view1;
            }
            j++;
            k = l;
            break MISSING_BLOCK_LABEL_46;
        }
        continue; /* Loop/switch isn't completed */
        if (true) goto _L4; else goto _L3
_L3:
    }

    private View findStartView(RecyclerView.LayoutManager layoutmanager, OrientationHelper orientationhelper)
    {
        int i1 = layoutmanager.getChildCount();
        if (i1 != 0) goto _L2; else goto _L1
_L1:
        View view1 = null;
_L4:
        return view1;
_L2:
        View view = null;
        int j = 0x7fffffff;
        int i = 0;
        do
        {
            view1 = view;
            if (i >= i1)
            {
                continue;
            }
            view1 = layoutmanager.getChildAt(i);
            int l = orientationhelper.getDecoratedStart(view1);
            int k = j;
            if (l < j)
            {
                k = l;
                view = view1;
            }
            i++;
            j = k;
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    private OrientationHelper getHorizontalHelper(RecyclerView.LayoutManager layoutmanager)
    {
        if (mHorizontalHelper == null || mHorizontalHelper.mLayoutManager != layoutmanager)
        {
            mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutmanager);
        }
        return mHorizontalHelper;
    }

    private OrientationHelper getVerticalHelper(RecyclerView.LayoutManager layoutmanager)
    {
        if (mVerticalHelper == null || mVerticalHelper.mLayoutManager != layoutmanager)
        {
            mVerticalHelper = OrientationHelper.createVerticalHelper(layoutmanager);
        }
        return mVerticalHelper;
    }

    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutmanager, View view)
    {
        int ai[] = new int[2];
        if (layoutmanager.canScrollHorizontally())
        {
            ai[0] = distanceToCenter(layoutmanager, view, getHorizontalHelper(layoutmanager));
        } else
        {
            ai[0] = 0;
        }
        if (layoutmanager.canScrollVertically())
        {
            ai[1] = distanceToCenter(layoutmanager, view, getVerticalHelper(layoutmanager));
            return ai;
        } else
        {
            ai[1] = 0;
            return ai;
        }
    }

    protected LinearSmoothScroller createSnapScroller(RecyclerView.LayoutManager layoutmanager)
    {
        if (!(layoutmanager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider))
        {
            return null;
        } else
        {
            return new LinearSmoothScroller(mRecyclerView.getContext()) {

                final PagerSnapHelper this$0;

                protected float calculateSpeedPerPixel(DisplayMetrics displaymetrics)
                {
                    return 100F / (float)displaymetrics.densityDpi;
                }

                protected int calculateTimeForScrolling(int i)
                {
                    return Math.min(100, super.calculateTimeForScrolling(i));
                }

                protected void onTargetFound(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action)
                {
                    view = calculateDistanceToFinalSnap(mRecyclerView.getLayoutManager(), view);
                    int i = view[0];
                    int j = view[1];
                    int k = calculateTimeForDeceleration(Math.max(Math.abs(i), Math.abs(j)));
                    if (k > 0)
                    {
                        action.update(i, j, k, mDecelerateInterpolator);
                    }
                }

            
            {
                this$0 = PagerSnapHelper.this;
                super(context);
            }
            };
        }
    }

    public View findSnapView(RecyclerView.LayoutManager layoutmanager)
    {
        if (layoutmanager.canScrollVertically())
        {
            return findCenterView(layoutmanager, getVerticalHelper(layoutmanager));
        }
        if (layoutmanager.canScrollHorizontally())
        {
            return findCenterView(layoutmanager, getHorizontalHelper(layoutmanager));
        } else
        {
            return null;
        }
    }

    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutmanager, int i, int j)
    {
        int l = layoutmanager.getItemCount();
        if (l != 0) goto _L2; else goto _L1
_L1:
        j = -1;
_L8:
        return j;
_L2:
        View view = null;
        if (!layoutmanager.canScrollVertically()) goto _L4; else goto _L3
_L3:
        view = findStartView(layoutmanager, getVerticalHelper(layoutmanager));
_L6:
        if (view == null)
        {
            return -1;
        }
        break; /* Loop/switch isn't completed */
_L4:
        if (layoutmanager.canScrollHorizontally())
        {
            view = findStartView(layoutmanager, getHorizontalHelper(layoutmanager));
        }
        if (true) goto _L6; else goto _L5
_L5:
        int k;
        k = layoutmanager.getPosition(view);
        if (k == -1)
        {
            return -1;
        }
        boolean flag;
        if (layoutmanager.canScrollHorizontally())
        {
            if (i > 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
        } else
        if (j > 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        flag = false;
        j = ((flag) ? 1 : 0);
        if (layoutmanager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)
        {
            layoutmanager = ((RecyclerView.SmoothScroller.ScrollVectorProvider)layoutmanager).computeScrollVectorForPosition(l - 1);
            j = ((flag) ? 1 : 0);
            if (layoutmanager != null)
            {
                if (((PointF) (layoutmanager)).x < 0.0F || ((PointF) (layoutmanager)).y < 0.0F)
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
            }
        }
        if (j == 0)
        {
            break; /* Loop/switch isn't completed */
        }
        j = k;
        if (i != 0)
        {
            return k - 1;
        }
        if (true) goto _L8; else goto _L7
_L7:
        j = k;
        if (i != 0)
        {
            return k + 1;
        }
        if (true) goto _L8; else goto _L9
_L9:
    }
}
