// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView, LinearSmoothScroller

public abstract class SnapHelper extends RecyclerView.OnFlingListener
{

    static final float MILLISECONDS_PER_INCH = 100F;
    private Scroller mGravityScroller;
    RecyclerView mRecyclerView;
    private final RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {

        boolean mScrolled;
        final SnapHelper this$0;

        public void onScrollStateChanged(RecyclerView recyclerview, int i)
        {
            super.onScrollStateChanged(recyclerview, i);
            if (i == 0 && mScrolled)
            {
                mScrolled = false;
                snapToTargetExistingView();
            }
        }

        public void onScrolled(RecyclerView recyclerview, int i, int j)
        {
            if (i != 0 || j != 0)
            {
                mScrolled = true;
            }
        }

            
            {
                this$0 = SnapHelper.this;
                super();
                mScrolled = false;
            }
    };

    public SnapHelper()
    {
    }

    private void destroyCallbacks()
    {
        mRecyclerView.removeOnScrollListener(mScrollListener);
        mRecyclerView.setOnFlingListener(null);
    }

    private void setupCallbacks()
        throws IllegalStateException
    {
        if (mRecyclerView.getOnFlingListener() != null)
        {
            throw new IllegalStateException("An instance of OnFlingListener already set.");
        } else
        {
            mRecyclerView.addOnScrollListener(mScrollListener);
            mRecyclerView.setOnFlingListener(this);
            return;
        }
    }

    private boolean snapFromFling(RecyclerView.LayoutManager layoutmanager, int i, int j)
    {
        LinearSmoothScroller linearsmoothscroller;
        if (layoutmanager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)
        {
            if ((linearsmoothscroller = createSnapScroller(layoutmanager)) != null && (i = findTargetSnapPosition(layoutmanager, i, j)) != -1)
            {
                linearsmoothscroller.setTargetPosition(i);
                layoutmanager.startSmoothScroll(linearsmoothscroller);
                return true;
            }
        }
        return false;
    }

    public void attachToRecyclerView(RecyclerView recyclerview)
        throws IllegalStateException
    {
        if (mRecyclerView != recyclerview)
        {
            if (mRecyclerView != null)
            {
                destroyCallbacks();
            }
            mRecyclerView = recyclerview;
            if (mRecyclerView != null)
            {
                setupCallbacks();
                mGravityScroller = new Scroller(mRecyclerView.getContext(), new DecelerateInterpolator());
                snapToTargetExistingView();
                return;
            }
        }
    }

    public abstract int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutmanager, View view);

    public int[] calculateScrollDistance(int i, int j)
    {
        mGravityScroller.fling(0, 0, i, j, 0x80000000, 0x7fffffff, 0x80000000, 0x7fffffff);
        return (new int[] {
            mGravityScroller.getFinalX(), mGravityScroller.getFinalY()
        });
    }

    protected LinearSmoothScroller createSnapScroller(RecyclerView.LayoutManager layoutmanager)
    {
        if (!(layoutmanager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider))
        {
            return null;
        } else
        {
            return new LinearSmoothScroller(mRecyclerView.getContext()) {

                final SnapHelper this$0;

                protected float calculateSpeedPerPixel(DisplayMetrics displaymetrics)
                {
                    return 100F / (float)displaymetrics.densityDpi;
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
                this$0 = SnapHelper.this;
                super(context);
            }
            };
        }
    }

    public abstract View findSnapView(RecyclerView.LayoutManager layoutmanager);

    public abstract int findTargetSnapPosition(RecyclerView.LayoutManager layoutmanager, int i, int j);

    public boolean onFling(int i, int j)
    {
        RecyclerView.LayoutManager layoutmanager;
        layoutmanager = mRecyclerView.getLayoutManager();
        break MISSING_BLOCK_LABEL_8;
        if (layoutmanager != null && mRecyclerView.getAdapter() != null)
        {
            int k = mRecyclerView.getMinFlingVelocity();
            if ((Math.abs(j) > k || Math.abs(i) > k) && snapFromFling(layoutmanager, i, j))
            {
                return true;
            }
        }
        return false;
    }

    void snapToTargetExistingView()
    {
        RecyclerView.LayoutManager layoutmanager;
        int ai[];
        View view;
        if (mRecyclerView != null)
        {
            if ((layoutmanager = mRecyclerView.getLayoutManager()) != null && (view = findSnapView(layoutmanager)) != null && ((ai = calculateDistanceToFinalSnap(layoutmanager, view))[0] != 0 || ai[1] != 0))
            {
                mRecyclerView.smoothScrollBy(ai[0], ai[1]);
                return;
            }
        }
    }
}
