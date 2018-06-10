// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.graphics.PointF;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            SnapHelper, OrientationHelper

public class LinearSnapHelper extends SnapHelper
{

    private static final float INVALID_DISTANCE = 1F;
    private OrientationHelper mHorizontalHelper;
    private OrientationHelper mVerticalHelper;

    public LinearSnapHelper()
    {
    }

    private float computeDistancePerChild(RecyclerView.LayoutManager layoutmanager, OrientationHelper orientationhelper)
    {
        View view = null;
        View view2 = null;
        int i = 0x7fffffff;
        int l = 0x80000000;
        int l1 = layoutmanager.getChildCount();
        if (l1 == 0)
        {
            return 1.0F;
        }
        int i1 = 0;
        while (i1 < l1) 
        {
            View view1 = layoutmanager.getChildAt(i1);
            int j1 = layoutmanager.getPosition(view1);
            View view3;
            int k1;
            if (j1 == -1)
            {
                view3 = view;
                k1 = i;
                i = l;
            } else
            {
                int j = i;
                if (j1 < i)
                {
                    j = j1;
                    view = view1;
                }
                i = l;
                k1 = j;
                view3 = view;
                if (j1 > l)
                {
                    i = j1;
                    view2 = view1;
                    k1 = j;
                    view3 = view;
                }
            }
            i1++;
            l = i;
            i = k1;
            view = view3;
        }
        if (view == null || view2 == null)
        {
            return 1.0F;
        }
        int k = Math.min(orientationhelper.getDecoratedStart(view), orientationhelper.getDecoratedStart(view2));
        k = Math.max(orientationhelper.getDecoratedEnd(view), orientationhelper.getDecoratedEnd(view2)) - k;
        if (k == 0)
        {
            return 1.0F;
        } else
        {
            return (1.0F * (float)k) / (float)((l - i) + 1);
        }
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

    private int estimateNextPositionDiffForFling(RecyclerView.LayoutManager layoutmanager, OrientationHelper orientationhelper, int i, int j)
    {
        int ai[] = calculateScrollDistance(i, j);
        float f = computeDistancePerChild(layoutmanager, orientationhelper);
        if (f <= 0.0F)
        {
            return 0;
        }
        if (Math.abs(ai[0]) > Math.abs(ai[1]))
        {
            i = ai[0];
        } else
        {
            i = ai[1];
        }
        if (i > 0)
        {
            return (int)Math.floor((float)i / f);
        } else
        {
            return (int)Math.ceil((float)i / f);
        }
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
        byte byte0 = -1;
        int k;
        if (!(layoutmanager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider))
        {
            k = byte0;
        } else
        {
            int l = layoutmanager.getItemCount();
            k = byte0;
            if (l != 0)
            {
                View view = findSnapView(layoutmanager);
                k = byte0;
                if (view != null)
                {
                    int i1 = layoutmanager.getPosition(view);
                    k = byte0;
                    if (i1 != -1)
                    {
                        PointF pointf = ((RecyclerView.SmoothScroller.ScrollVectorProvider)layoutmanager).computeScrollVectorForPosition(l - 1);
                        k = byte0;
                        if (pointf != null)
                        {
                            if (layoutmanager.canScrollHorizontally())
                            {
                                k = estimateNextPositionDiffForFling(layoutmanager, getHorizontalHelper(layoutmanager), i, 0);
                                i = k;
                                if (pointf.x < 0.0F)
                                {
                                    i = -k;
                                }
                            } else
                            {
                                i = 0;
                            }
                            if (layoutmanager.canScrollVertically())
                            {
                                k = estimateNextPositionDiffForFling(layoutmanager, getVerticalHelper(layoutmanager), 0, j);
                                j = k;
                                if (pointf.y < 0.0F)
                                {
                                    j = -k;
                                }
                            } else
                            {
                                j = 0;
                            }
                            if (layoutmanager.canScrollVertically())
                            {
                                i = j;
                            }
                            k = byte0;
                            if (i != 0)
                            {
                                j = i1 + i;
                                i = j;
                                if (j < 0)
                                {
                                    i = 0;
                                }
                                k = i;
                                if (i >= l)
                                {
                                    return l - 1;
                                }
                            }
                        }
                    }
                }
            }
        }
        return k;
    }
}
