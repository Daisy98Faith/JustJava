// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;

// Referenced classes of package android.support.v7.widget:
//            RecyclerView

class FastScroller extends RecyclerView.ItemDecoration
    implements RecyclerView.OnItemTouchListener
{
    private class AnimatorListener extends AnimatorListenerAdapter
    {

        private boolean mCanceled;
        final FastScroller this$0;

        public void onAnimationCancel(Animator animator)
        {
            mCanceled = true;
        }

        public void onAnimationEnd(Animator animator)
        {
            if (mCanceled)
            {
                mCanceled = false;
                return;
            }
            if (((Float)mShowHideAnimator.getAnimatedValue()).floatValue() == 0.0F)
            {
                mAnimationState = 0;
                setState(0);
                return;
            } else
            {
                mAnimationState = 2;
                requestRedraw();
                return;
            }
        }

        private AnimatorListener()
        {
            this$0 = FastScroller.this;
            super();
            mCanceled = false;
        }

    }

    private class AnimatorUpdater
        implements android.animation.ValueAnimator.AnimatorUpdateListener
    {

        final FastScroller this$0;

        public void onAnimationUpdate(ValueAnimator valueanimator)
        {
            int i = (int)(((Float)valueanimator.getAnimatedValue()).floatValue() * 255F);
            mVerticalThumbDrawable.setAlpha(i);
            mVerticalTrackDrawable.setAlpha(i);
            requestRedraw();
        }

        private AnimatorUpdater()
        {
            this$0 = FastScroller.this;
            super();
        }

    }


    private static final int ANIMATION_STATE_FADING_IN = 1;
    private static final int ANIMATION_STATE_FADING_OUT = 3;
    private static final int ANIMATION_STATE_IN = 2;
    private static final int ANIMATION_STATE_OUT = 0;
    private static final int DRAG_NONE = 0;
    private static final int DRAG_X = 1;
    private static final int DRAG_Y = 2;
    private static final int EMPTY_STATE_SET[] = new int[0];
    private static final int HIDE_DELAY_AFTER_DRAGGING_MS = 1200;
    private static final int HIDE_DELAY_AFTER_VISIBLE_MS = 1500;
    private static final int HIDE_DURATION_MS = 500;
    private static final int PRESSED_STATE_SET[] = {
        0x10100a7
    };
    private static final int SCROLLBAR_FULL_OPAQUE = 255;
    private static final int SHOW_DURATION_MS = 500;
    private static final int STATE_DRAGGING = 2;
    private static final int STATE_HIDDEN = 0;
    private static final int STATE_VISIBLE = 1;
    private int mAnimationState;
    private int mDragState;
    private final Runnable mHideRunnable = new Runnable() {

        final FastScroller this$0;

        public void run()
        {
            hide(500);
        }

            
            {
                this$0 = FastScroller.this;
                super();
            }
    };
    float mHorizontalDragX;
    private final int mHorizontalRange[] = new int[2];
    int mHorizontalThumbCenterX;
    private final StateListDrawable mHorizontalThumbDrawable;
    private final int mHorizontalThumbHeight;
    int mHorizontalThumbWidth;
    private final Drawable mHorizontalTrackDrawable;
    private final int mHorizontalTrackHeight;
    private final int mMargin;
    private boolean mNeedHorizontalScrollbar;
    private boolean mNeedVerticalScrollbar;
    private final RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        final FastScroller this$0;

        public void onScrolled(RecyclerView recyclerview1, int l, int i1)
        {
            updateScrollPosition(recyclerview1.computeHorizontalScrollOffset(), recyclerview1.computeVerticalScrollOffset());
        }

            
            {
                this$0 = FastScroller.this;
                super();
            }
    };
    private RecyclerView mRecyclerView;
    private int mRecyclerViewHeight;
    private int mRecyclerViewWidth;
    private final int mScrollbarMinimumRange;
    private final ValueAnimator mShowHideAnimator = ValueAnimator.ofFloat(new float[] {
        0.0F, 1.0F
    });
    private int mState;
    float mVerticalDragY;
    private final int mVerticalRange[] = new int[2];
    int mVerticalThumbCenterY;
    private final StateListDrawable mVerticalThumbDrawable;
    int mVerticalThumbHeight;
    private final int mVerticalThumbWidth;
    private final Drawable mVerticalTrackDrawable;
    private final int mVerticalTrackWidth;

    FastScroller(RecyclerView recyclerview, StateListDrawable statelistdrawable, Drawable drawable, StateListDrawable statelistdrawable1, Drawable drawable1, int i, int j, 
            int k)
    {
        mRecyclerViewWidth = 0;
        mRecyclerViewHeight = 0;
        mNeedVerticalScrollbar = false;
        mNeedHorizontalScrollbar = false;
        mState = 0;
        mDragState = 0;
        mAnimationState = 0;
        mVerticalThumbDrawable = statelistdrawable;
        mVerticalTrackDrawable = drawable;
        mHorizontalThumbDrawable = statelistdrawable1;
        mHorizontalTrackDrawable = drawable1;
        mVerticalThumbWidth = Math.max(i, statelistdrawable.getIntrinsicWidth());
        mVerticalTrackWidth = Math.max(i, drawable.getIntrinsicWidth());
        mHorizontalThumbHeight = Math.max(i, statelistdrawable1.getIntrinsicWidth());
        mHorizontalTrackHeight = Math.max(i, drawable1.getIntrinsicWidth());
        mScrollbarMinimumRange = j;
        mMargin = k;
        mVerticalThumbDrawable.setAlpha(255);
        mVerticalTrackDrawable.setAlpha(255);
        mShowHideAnimator.addListener(new AnimatorListener());
        mShowHideAnimator.addUpdateListener(new AnimatorUpdater());
        attachToRecyclerView(recyclerview);
    }

    private void cancelHide()
    {
        mRecyclerView.removeCallbacks(mHideRunnable);
    }

    private void destroyCallbacks()
    {
        mRecyclerView.removeItemDecoration(this);
        mRecyclerView.removeOnItemTouchListener(this);
        mRecyclerView.removeOnScrollListener(mOnScrollListener);
        cancelHide();
    }

    private void drawHorizontalScrollbar(Canvas canvas)
    {
        int i = mRecyclerViewHeight - mHorizontalThumbHeight;
        int j = mHorizontalThumbCenterX - mHorizontalThumbWidth / 2;
        mHorizontalThumbDrawable.setBounds(0, 0, mHorizontalThumbWidth, mHorizontalThumbHeight);
        mHorizontalTrackDrawable.setBounds(0, 0, mRecyclerViewWidth, mHorizontalTrackHeight);
        canvas.translate(0.0F, i);
        mHorizontalTrackDrawable.draw(canvas);
        canvas.translate(j, 0.0F);
        mHorizontalThumbDrawable.draw(canvas);
        canvas.translate(-j, -i);
    }

    private void drawVerticalScrollbar(Canvas canvas)
    {
        int i = mRecyclerViewWidth - mVerticalThumbWidth;
        int j = mVerticalThumbCenterY - mVerticalThumbHeight / 2;
        mVerticalThumbDrawable.setBounds(0, 0, mVerticalThumbWidth, mVerticalThumbHeight);
        mVerticalTrackDrawable.setBounds(0, 0, mVerticalTrackWidth, mRecyclerViewHeight);
        if (isLayoutRTL())
        {
            mVerticalTrackDrawable.draw(canvas);
            canvas.translate(mVerticalThumbWidth, j);
            canvas.scale(-1F, 1.0F);
            mVerticalThumbDrawable.draw(canvas);
            canvas.scale(1.0F, 1.0F);
            canvas.translate(-mVerticalThumbWidth, -j);
            return;
        } else
        {
            canvas.translate(i, 0.0F);
            mVerticalTrackDrawable.draw(canvas);
            canvas.translate(0.0F, j);
            mVerticalThumbDrawable.draw(canvas);
            canvas.translate(-i, -j);
            return;
        }
    }

    private int[] getHorizontalRange()
    {
        mHorizontalRange[0] = mMargin;
        mHorizontalRange[1] = mRecyclerViewWidth - mMargin;
        return mHorizontalRange;
    }

    private int[] getVerticalRange()
    {
        mVerticalRange[0] = mMargin;
        mVerticalRange[1] = mRecyclerViewHeight - mMargin;
        return mVerticalRange;
    }

    private void horizontalScrollTo(float f)
    {
        int ai[] = getHorizontalRange();
        f = Math.max(ai[0], Math.min(ai[1], f));
        if (Math.abs((float)mHorizontalThumbCenterX - f) < 2.0F)
        {
            return;
        }
        int i = scrollTo(mHorizontalDragX, f, ai, mRecyclerView.computeHorizontalScrollRange(), mRecyclerView.computeHorizontalScrollOffset(), mRecyclerViewWidth);
        if (i != 0)
        {
            mRecyclerView.scrollBy(i, 0);
        }
        mHorizontalDragX = f;
    }

    private boolean isLayoutRTL()
    {
        return ViewCompat.getLayoutDirection(mRecyclerView) == 1;
    }

    private void requestRedraw()
    {
        mRecyclerView.invalidate();
    }

    private void resetHideDelay(int i)
    {
        cancelHide();
        mRecyclerView.postDelayed(mHideRunnable, i);
    }

    private int scrollTo(float f, float f1, int ai[], int i, int j, int k)
    {
        int l = ai[1] - ai[0];
        if (l == 0)
        {
            i = 0;
        } else
        {
            f = (f1 - f) / (float)l;
            k = i - k;
            i = (int)((float)k * f);
            j += i;
            if (j >= k || j < 0)
            {
                return 0;
            }
        }
        return i;
    }

    private void setState(int i)
    {
        if (i == 2 && mState != 2)
        {
            mVerticalThumbDrawable.setState(PRESSED_STATE_SET);
            cancelHide();
        }
        if (i == 0)
        {
            requestRedraw();
        } else
        {
            show();
        }
        if (mState != 2 || i == 2) goto _L2; else goto _L1
_L1:
        mVerticalThumbDrawable.setState(EMPTY_STATE_SET);
        resetHideDelay(1200);
_L4:
        mState = i;
        return;
_L2:
        if (i == 1)
        {
            resetHideDelay(1500);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private void setupCallbacks()
    {
        mRecyclerView.addItemDecoration(this);
        mRecyclerView.addOnItemTouchListener(this);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
    }

    private void verticalScrollTo(float f)
    {
        int ai[] = getVerticalRange();
        f = Math.max(ai[0], Math.min(ai[1], f));
        if (Math.abs((float)mVerticalThumbCenterY - f) < 2.0F)
        {
            return;
        }
        int i = scrollTo(mVerticalDragY, f, ai, mRecyclerView.computeVerticalScrollRange(), mRecyclerView.computeVerticalScrollOffset(), mRecyclerViewHeight);
        if (i != 0)
        {
            mRecyclerView.scrollBy(0, i);
        }
        mVerticalDragY = f;
    }

    public void attachToRecyclerView(RecyclerView recyclerview)
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
                return;
            }
        }
    }

    Drawable getHorizontalThumbDrawable()
    {
        return mHorizontalThumbDrawable;
    }

    Drawable getHorizontalTrackDrawable()
    {
        return mHorizontalTrackDrawable;
    }

    Drawable getVerticalThumbDrawable()
    {
        return mVerticalThumbDrawable;
    }

    Drawable getVerticalTrackDrawable()
    {
        return mVerticalTrackDrawable;
    }

    public void hide()
    {
        hide(0);
    }

    void hide(int i)
    {
        switch (mAnimationState)
        {
        default:
            return;

        case 1: // '\001'
            mShowHideAnimator.cancel();
            // fall through

        case 2: // '\002'
            mAnimationState = 3;
            break;
        }
        mShowHideAnimator.setFloatValues(new float[] {
            ((Float)mShowHideAnimator.getAnimatedValue()).floatValue(), 0.0F
        });
        mShowHideAnimator.setDuration(i);
        mShowHideAnimator.start();
    }

    public boolean isDragging()
    {
        return mState == 2;
    }

    boolean isHidden()
    {
        return mState == 0;
    }

    boolean isPointInsideHorizontalThumb(float f, float f1)
    {
        return f1 >= (float)(mRecyclerViewHeight - mHorizontalThumbHeight) && f >= (float)(mHorizontalThumbCenterX - mHorizontalThumbWidth / 2) && f <= (float)(mHorizontalThumbCenterX + mHorizontalThumbWidth / 2);
    }

    boolean isPointInsideVerticalThumb(float f, float f1)
    {
        return (isLayoutRTL() ? f <= (float)(mVerticalThumbWidth / 2) : f >= (float)(mRecyclerViewWidth - mVerticalThumbWidth)) && (f1 >= (float)(mVerticalThumbCenterY - mVerticalThumbHeight / 2) && f1 <= (float)(mVerticalThumbCenterY + mVerticalThumbHeight / 2));
    }

    boolean isVisible()
    {
        return mState == 1;
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerview, RecyclerView.State state)
    {
        if (mRecyclerViewWidth != mRecyclerView.getWidth() || mRecyclerViewHeight != mRecyclerView.getHeight())
        {
            mRecyclerViewWidth = mRecyclerView.getWidth();
            mRecyclerViewHeight = mRecyclerView.getHeight();
            setState(0);
        } else
        if (mAnimationState != 0)
        {
            if (mNeedVerticalScrollbar)
            {
                drawVerticalScrollbar(canvas);
            }
            if (mNeedHorizontalScrollbar)
            {
                drawHorizontalScrollbar(canvas);
                return;
            }
        }
    }

    public boolean onInterceptTouchEvent(RecyclerView recyclerview, MotionEvent motionevent)
    {
        boolean flag;
        boolean flag1;
        if (mState != 1)
        {
            break MISSING_BLOCK_LABEL_102;
        }
        flag = isPointInsideVerticalThumb(motionevent.getX(), motionevent.getY());
        flag1 = isPointInsideHorizontalThumb(motionevent.getX(), motionevent.getY());
        if (motionevent.getAction() != 0 || !flag && !flag1) goto _L2; else goto _L1
_L1:
        if (!flag1) goto _L4; else goto _L3
_L3:
        mDragState = 1;
        mHorizontalDragX = (int)motionevent.getX();
_L5:
        setState(2);
        return true;
_L4:
        if (flag)
        {
            mDragState = 2;
            mVerticalDragY = (int)motionevent.getY();
        }
        if (true) goto _L5; else goto _L2
_L2:
        return false;
        return mState == 2;
    }

    public void onRequestDisallowInterceptTouchEvent(boolean flag)
    {
    }

    public void onTouchEvent(RecyclerView recyclerview, MotionEvent motionevent)
    {
        if (mState != 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (motionevent.getAction() != 0) goto _L4; else goto _L3
_L3:
        boolean flag;
        boolean flag1;
        flag = isPointInsideVerticalThumb(motionevent.getX(), motionevent.getY());
        flag1 = isPointInsideHorizontalThumb(motionevent.getX(), motionevent.getY());
        if (!flag && !flag1)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (!flag1) goto _L6; else goto _L5
_L5:
        mDragState = 1;
        mHorizontalDragX = (int)motionevent.getX();
_L7:
        setState(2);
        return;
_L6:
        if (flag)
        {
            mDragState = 2;
            mVerticalDragY = (int)motionevent.getY();
        }
        if (true) goto _L7; else goto _L4
_L4:
        if (motionevent.getAction() == 1 && mState == 2)
        {
            mVerticalDragY = 0.0F;
            mHorizontalDragX = 0.0F;
            setState(1);
            mDragState = 0;
            return;
        }
        if (motionevent.getAction() == 2 && mState == 2)
        {
            show();
            if (mDragState == 1)
            {
                horizontalScrollTo(motionevent.getX());
            }
            if (mDragState == 2)
            {
                verticalScrollTo(motionevent.getY());
                return;
            }
        }
        if (true) goto _L1; else goto _L8
_L8:
    }

    public void show()
    {
        switch (mAnimationState)
        {
        case 1: // '\001'
        case 2: // '\002'
        default:
            return;

        case 3: // '\003'
            mShowHideAnimator.cancel();
            // fall through

        case 0: // '\0'
            mAnimationState = 1;
            break;
        }
        mShowHideAnimator.setFloatValues(new float[] {
            ((Float)mShowHideAnimator.getAnimatedValue()).floatValue(), 1.0F
        });
        mShowHideAnimator.setDuration(500L);
        mShowHideAnimator.setStartDelay(0L);
        mShowHideAnimator.start();
    }

    void updateScrollPosition(int i, int j)
    {
        int k = mRecyclerView.computeVerticalScrollRange();
        int l = mRecyclerViewHeight;
        int i1;
        int j1;
        boolean flag;
        if (k - l > 0 && mRecyclerViewHeight >= mScrollbarMinimumRange)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mNeedVerticalScrollbar = flag;
        i1 = mRecyclerView.computeHorizontalScrollRange();
        j1 = mRecyclerViewWidth;
        if (i1 - j1 > 0 && mRecyclerViewWidth >= mScrollbarMinimumRange)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mNeedHorizontalScrollbar = flag;
        if (!mNeedVerticalScrollbar && !mNeedHorizontalScrollbar)
        {
            if (mState != 0)
            {
                setState(0);
            }
        } else
        {
            if (mNeedVerticalScrollbar)
            {
                float f = j;
                float f2 = (float)l / 2.0F;
                mVerticalThumbCenterY = (int)(((float)l * (f + f2)) / (float)k);
                mVerticalThumbHeight = Math.min(l, (l * l) / k);
            }
            if (mNeedHorizontalScrollbar)
            {
                float f1 = i;
                float f3 = (float)j1 / 2.0F;
                mHorizontalThumbCenterX = (int)(((float)j1 * (f1 + f3)) / (float)i1);
                mHorizontalThumbWidth = Math.min(j1, (j1 * j1) / i1);
            }
            if (mState == 0 || mState == 1)
            {
                setState(1);
                return;
            }
        }
    }




/*
    static int access$302(FastScroller fastscroller, int i)
    {
        fastscroller.mAnimationState = i;
        return i;
    }

*/




}
