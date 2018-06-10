// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.os.TraceCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Display;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Referenced classes of package android.support.v7.widget:
//            ViewInfoStore, DefaultItemAnimator, RecyclerViewAccessibilityDelegate, ChildHelper, 
//            AdapterHelper, FastScroller, GapWorker, ViewBoundsCheck

public class RecyclerView extends ViewGroup
    implements ScrollingView, NestedScrollingChild
{
    public static abstract class Adapter
    {

        private boolean mHasStableIds;
        private final AdapterDataObservable mObservable = new AdapterDataObservable();

        public final void bindViewHolder(ViewHolder viewholder, int i)
        {
            viewholder.mPosition = i;
            if (hasStableIds())
            {
                viewholder.mItemId = getItemId(i);
            }
            viewholder.setFlags(1, 519);
            TraceCompat.beginSection("RV OnBindView");
            onBindViewHolder(viewholder, i, viewholder.getUnmodifiedPayloads());
            viewholder.clearPayload();
            viewholder = viewholder.itemView.getLayoutParams();
            if (viewholder instanceof LayoutParams)
            {
                ((LayoutParams)viewholder).mInsetsDirty = true;
            }
            TraceCompat.endSection();
        }

        public final ViewHolder createViewHolder(ViewGroup viewgroup, int i)
        {
            TraceCompat.beginSection("RV CreateView");
            viewgroup = onCreateViewHolder(viewgroup, i);
            viewgroup.mItemViewType = i;
            TraceCompat.endSection();
            return viewgroup;
        }

        public abstract int getItemCount();

        public long getItemId(int i)
        {
            return -1L;
        }

        public int getItemViewType(int i)
        {
            return 0;
        }

        public final boolean hasObservers()
        {
            return mObservable.hasObservers();
        }

        public final boolean hasStableIds()
        {
            return mHasStableIds;
        }

        public final void notifyDataSetChanged()
        {
            mObservable.notifyChanged();
        }

        public final void notifyItemChanged(int i)
        {
            mObservable.notifyItemRangeChanged(i, 1);
        }

        public final void notifyItemChanged(int i, Object obj)
        {
            mObservable.notifyItemRangeChanged(i, 1, obj);
        }

        public final void notifyItemInserted(int i)
        {
            mObservable.notifyItemRangeInserted(i, 1);
        }

        public final void notifyItemMoved(int i, int j)
        {
            mObservable.notifyItemMoved(i, j);
        }

        public final void notifyItemRangeChanged(int i, int j)
        {
            mObservable.notifyItemRangeChanged(i, j);
        }

        public final void notifyItemRangeChanged(int i, int j, Object obj)
        {
            mObservable.notifyItemRangeChanged(i, j, obj);
        }

        public final void notifyItemRangeInserted(int i, int j)
        {
            mObservable.notifyItemRangeInserted(i, j);
        }

        public final void notifyItemRangeRemoved(int i, int j)
        {
            mObservable.notifyItemRangeRemoved(i, j);
        }

        public final void notifyItemRemoved(int i)
        {
            mObservable.notifyItemRangeRemoved(i, 1);
        }

        public void onAttachedToRecyclerView(RecyclerView recyclerview)
        {
        }

        public abstract void onBindViewHolder(ViewHolder viewholder, int i);

        public void onBindViewHolder(ViewHolder viewholder, int i, List list)
        {
            onBindViewHolder(viewholder, i);
        }

        public abstract ViewHolder onCreateViewHolder(ViewGroup viewgroup, int i);

        public void onDetachedFromRecyclerView(RecyclerView recyclerview)
        {
        }

        public boolean onFailedToRecycleView(ViewHolder viewholder)
        {
            return false;
        }

        public void onViewAttachedToWindow(ViewHolder viewholder)
        {
        }

        public void onViewDetachedFromWindow(ViewHolder viewholder)
        {
        }

        public void onViewRecycled(ViewHolder viewholder)
        {
        }

        public void registerAdapterDataObserver(AdapterDataObserver adapterdataobserver)
        {
            mObservable.registerObserver(adapterdataobserver);
        }

        public void setHasStableIds(boolean flag)
        {
            if (hasObservers())
            {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            } else
            {
                mHasStableIds = flag;
                return;
            }
        }

        public void unregisterAdapterDataObserver(AdapterDataObserver adapterdataobserver)
        {
            mObservable.unregisterObserver(adapterdataobserver);
        }

        public Adapter()
        {
            mHasStableIds = false;
        }
    }

    static class AdapterDataObservable extends Observable
    {

        public boolean hasObservers()
        {
            return !mObservers.isEmpty();
        }

        public void notifyChanged()
        {
            for (int i = mObservers.size() - 1; i >= 0; i--)
            {
                ((AdapterDataObserver)mObservers.get(i)).onChanged();
            }

        }

        public void notifyItemMoved(int i, int j)
        {
            for (int k = mObservers.size() - 1; k >= 0; k--)
            {
                ((AdapterDataObserver)mObservers.get(k)).onItemRangeMoved(i, j, 1);
            }

        }

        public void notifyItemRangeChanged(int i, int j)
        {
            notifyItemRangeChanged(i, j, null);
        }

        public void notifyItemRangeChanged(int i, int j, Object obj)
        {
            for (int k = mObservers.size() - 1; k >= 0; k--)
            {
                ((AdapterDataObserver)mObservers.get(k)).onItemRangeChanged(i, j, obj);
            }

        }

        public void notifyItemRangeInserted(int i, int j)
        {
            for (int k = mObservers.size() - 1; k >= 0; k--)
            {
                ((AdapterDataObserver)mObservers.get(k)).onItemRangeInserted(i, j);
            }

        }

        public void notifyItemRangeRemoved(int i, int j)
        {
            for (int k = mObservers.size() - 1; k >= 0; k--)
            {
                ((AdapterDataObserver)mObservers.get(k)).onItemRangeRemoved(i, j);
            }

        }

        AdapterDataObservable()
        {
        }
    }

    public static abstract class AdapterDataObserver
    {

        public void onChanged()
        {
        }

        public void onItemRangeChanged(int i, int j)
        {
        }

        public void onItemRangeChanged(int i, int j, Object obj)
        {
            onItemRangeChanged(i, j);
        }

        public void onItemRangeInserted(int i, int j)
        {
        }

        public void onItemRangeMoved(int i, int j, int k)
        {
        }

        public void onItemRangeRemoved(int i, int j)
        {
        }

        public AdapterDataObserver()
        {
        }
    }

    public static interface ChildDrawingOrderCallback
    {

        public abstract int onGetChildDrawingOrder(int i, int j);
    }

    public static abstract class ItemAnimator
    {

        public static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        public static final int FLAG_CHANGED = 2;
        public static final int FLAG_INVALIDATED = 4;
        public static final int FLAG_MOVED = 2048;
        public static final int FLAG_REMOVED = 8;
        private long mAddDuration;
        private long mChangeDuration;
        private ArrayList mFinishedListeners;
        private ItemAnimatorListener mListener;
        private long mMoveDuration;
        private long mRemoveDuration;

        static int buildAdapterChangeFlagsForAnimations(ViewHolder viewholder)
        {
            int j = viewholder.mFlags & 0xe;
            if (viewholder.isInvalid())
            {
                return 4;
            }
            int i = j;
            if ((j & 4) == 0)
            {
                int k = viewholder.getOldPosition();
                int l = viewholder.getAdapterPosition();
                i = j;
                if (k != -1)
                {
                    i = j;
                    if (l != -1)
                    {
                        i = j;
                        if (k != l)
                        {
                            i = j | 0x800;
                        }
                    }
                }
            }
            return i;
        }

        public abstract boolean animateAppearance(ViewHolder viewholder, ItemHolderInfo itemholderinfo, ItemHolderInfo itemholderinfo1);

        public abstract boolean animateChange(ViewHolder viewholder, ViewHolder viewholder1, ItemHolderInfo itemholderinfo, ItemHolderInfo itemholderinfo1);

        public abstract boolean animateDisappearance(ViewHolder viewholder, ItemHolderInfo itemholderinfo, ItemHolderInfo itemholderinfo1);

        public abstract boolean animatePersistence(ViewHolder viewholder, ItemHolderInfo itemholderinfo, ItemHolderInfo itemholderinfo1);

        public boolean canReuseUpdatedViewHolder(ViewHolder viewholder)
        {
            return true;
        }

        public boolean canReuseUpdatedViewHolder(ViewHolder viewholder, List list)
        {
            return canReuseUpdatedViewHolder(viewholder);
        }

        public final void dispatchAnimationFinished(ViewHolder viewholder)
        {
            onAnimationFinished(viewholder);
            if (mListener != null)
            {
                mListener.onAnimationFinished(viewholder);
            }
        }

        public final void dispatchAnimationStarted(ViewHolder viewholder)
        {
            onAnimationStarted(viewholder);
        }

        public final void dispatchAnimationsFinished()
        {
            int j = mFinishedListeners.size();
            for (int i = 0; i < j; i++)
            {
                ((ItemAnimatorFinishedListener)mFinishedListeners.get(i)).onAnimationsFinished();
            }

            mFinishedListeners.clear();
        }

        public abstract void endAnimation(ViewHolder viewholder);

        public abstract void endAnimations();

        public long getAddDuration()
        {
            return mAddDuration;
        }

        public long getChangeDuration()
        {
            return mChangeDuration;
        }

        public long getMoveDuration()
        {
            return mMoveDuration;
        }

        public long getRemoveDuration()
        {
            return mRemoveDuration;
        }

        public abstract boolean isRunning();

        public final boolean isRunning(ItemAnimatorFinishedListener itemanimatorfinishedlistener)
        {
            boolean flag;
label0:
            {
                flag = isRunning();
                if (itemanimatorfinishedlistener != null)
                {
                    if (flag)
                    {
                        break label0;
                    }
                    itemanimatorfinishedlistener.onAnimationsFinished();
                }
                return flag;
            }
            mFinishedListeners.add(itemanimatorfinishedlistener);
            return flag;
        }

        public ItemHolderInfo obtainHolderInfo()
        {
            return new ItemHolderInfo();
        }

        public void onAnimationFinished(ViewHolder viewholder)
        {
        }

        public void onAnimationStarted(ViewHolder viewholder)
        {
        }

        public ItemHolderInfo recordPostLayoutInformation(State state, ViewHolder viewholder)
        {
            return obtainHolderInfo().setFrom(viewholder);
        }

        public ItemHolderInfo recordPreLayoutInformation(State state, ViewHolder viewholder, int i, List list)
        {
            return obtainHolderInfo().setFrom(viewholder);
        }

        public abstract void runPendingAnimations();

        public void setAddDuration(long l)
        {
            mAddDuration = l;
        }

        public void setChangeDuration(long l)
        {
            mChangeDuration = l;
        }

        void setListener(ItemAnimatorListener itemanimatorlistener)
        {
            mListener = itemanimatorlistener;
        }

        public void setMoveDuration(long l)
        {
            mMoveDuration = l;
        }

        public void setRemoveDuration(long l)
        {
            mRemoveDuration = l;
        }

        public ItemAnimator()
        {
            mListener = null;
            mFinishedListeners = new ArrayList();
            mAddDuration = 120L;
            mRemoveDuration = 120L;
            mMoveDuration = 250L;
            mChangeDuration = 250L;
        }
    }

    public static interface ItemAnimator.AdapterChanges
        extends Annotation
    {
    }

    public static interface ItemAnimator.ItemAnimatorFinishedListener
    {

        public abstract void onAnimationsFinished();
    }

    static interface ItemAnimator.ItemAnimatorListener
    {

        public abstract void onAnimationFinished(ViewHolder viewholder);
    }

    public static class ItemAnimator.ItemHolderInfo
    {

        public int bottom;
        public int changeFlags;
        public int left;
        public int right;
        public int top;

        public ItemAnimator.ItemHolderInfo setFrom(ViewHolder viewholder)
        {
            return setFrom(viewholder, 0);
        }

        public ItemAnimator.ItemHolderInfo setFrom(ViewHolder viewholder, int i)
        {
            viewholder = viewholder.itemView;
            left = viewholder.getLeft();
            top = viewholder.getTop();
            right = viewholder.getRight();
            bottom = viewholder.getBottom();
            return this;
        }

        public ItemAnimator.ItemHolderInfo()
        {
        }
    }

    private class ItemAnimatorRestoreListener
        implements ItemAnimator.ItemAnimatorListener
    {

        final RecyclerView this$0;

        public void onAnimationFinished(ViewHolder viewholder)
        {
            viewholder.setIsRecyclable(true);
            if (viewholder.mShadowedHolder != null && viewholder.mShadowingHolder == null)
            {
                viewholder.mShadowedHolder = null;
            }
            viewholder.mShadowingHolder = null;
            if (!viewholder.shouldBeKeptAsChild() && !removeAnimatingView(viewholder.itemView) && viewholder.isTmpDetached())
            {
                removeDetachedView(viewholder.itemView, false);
            }
        }

        ItemAnimatorRestoreListener()
        {
            this$0 = RecyclerView.this;
            super();
        }
    }

    public static abstract class ItemDecoration
    {

        public void getItemOffsets(Rect rect, int i, RecyclerView recyclerview)
        {
            rect.set(0, 0, 0, 0);
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerview, State state)
        {
            getItemOffsets(rect, ((LayoutParams)view.getLayoutParams()).getViewLayoutPosition(), recyclerview);
        }

        public void onDraw(Canvas canvas, RecyclerView recyclerview)
        {
        }

        public void onDraw(Canvas canvas, RecyclerView recyclerview, State state)
        {
            onDraw(canvas, recyclerview);
        }

        public void onDrawOver(Canvas canvas, RecyclerView recyclerview)
        {
        }

        public void onDrawOver(Canvas canvas, RecyclerView recyclerview, State state)
        {
            onDrawOver(canvas, recyclerview);
        }

        public ItemDecoration()
        {
        }
    }

    public static abstract class LayoutManager
    {

        boolean mAutoMeasure;
        ChildHelper mChildHelper;
        private int mHeight;
        private int mHeightMode;
        ViewBoundsCheck mHorizontalBoundCheck;
        private final ViewBoundsCheck.Callback mHorizontalBoundCheckCallback = new _cls1();
        boolean mIsAttachedToWindow;
        private boolean mItemPrefetchEnabled;
        private boolean mMeasurementCacheEnabled;
        int mPrefetchMaxCountObserved;
        boolean mPrefetchMaxObservedInInitialPrefetch;
        RecyclerView mRecyclerView;
        boolean mRequestedSimpleAnimations;
        SmoothScroller mSmoothScroller;
        ViewBoundsCheck mVerticalBoundCheck;
        private final ViewBoundsCheck.Callback mVerticalBoundCheckCallback = new _cls2();
        private int mWidth;
        private int mWidthMode;

        private void addViewInt(View view, int i, boolean flag)
        {
            ViewHolder viewholder;
            LayoutParams layoutparams;
            viewholder = RecyclerView.getChildViewHolderInt(view);
            if (flag || viewholder.isRemoved())
            {
                mRecyclerView.mViewInfoStore.addToDisappearedInLayout(viewholder);
            } else
            {
                mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(viewholder);
            }
            layoutparams = (LayoutParams)view.getLayoutParams();
            if (!viewholder.wasReturnedFromScrap() && !viewholder.isScrap()) goto _L2; else goto _L1
_L1:
            if (viewholder.isScrap())
            {
                viewholder.unScrap();
            } else
            {
                viewholder.clearReturnedFromScrapFlag();
            }
            mChildHelper.attachViewToParent(view, i, view.getLayoutParams(), false);
_L4:
            if (layoutparams.mPendingInvalidate)
            {
                viewholder.itemView.invalidate();
                layoutparams.mPendingInvalidate = false;
            }
            return;
_L2:
            if (view.getParent() == mRecyclerView)
            {
                int k = mChildHelper.indexOfChild(view);
                int j = i;
                if (i == -1)
                {
                    j = mChildHelper.getChildCount();
                }
                if (k == -1)
                {
                    throw new IllegalStateException((new StringBuilder()).append("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:").append(mRecyclerView.indexOfChild(view)).toString());
                }
                if (k != j)
                {
                    mRecyclerView.mLayout.moveView(k, j);
                }
            } else
            {
                mChildHelper.addView(view, i, false);
                layoutparams.mInsetsDirty = true;
                if (mSmoothScroller != null && mSmoothScroller.isRunning())
                {
                    mSmoothScroller.onChildAttachedToWindow(view);
                }
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        public static int chooseSize(int i, int j, int k)
        {
            int i1 = android.view.View.MeasureSpec.getMode(i);
            int l = android.view.View.MeasureSpec.getSize(i);
            i = l;
            switch (i1)
            {
            default:
                i = Math.max(j, k);
                // fall through

            case 1073741824: 
                return i;

            case -2147483648: 
                return Math.min(l, Math.max(j, k));
            }
        }

        private void detachViewInternal(int i, View view)
        {
            mChildHelper.detachViewFromParent(i);
        }

        public static int getChildMeasureSpec(int i, int j, int k, int l, boolean flag)
        {
            int i1;
            i1 = Math.max(0, i - k);
            k = 0;
            i = 0;
            if (!flag) goto _L2; else goto _L1
_L1:
            if (l < 0) goto _L4; else goto _L3
_L3:
            k = l;
            i = 0x40000000;
_L6:
            return android.view.View.MeasureSpec.makeMeasureSpec(k, i);
_L4:
            if (l == -1)
            {
                switch (j)
                {
                case -2147483648: 
                case 1073741824: 
                    k = i1;
                    i = j;
                    break;

                case 0: // '\0'
                    k = 0;
                    i = 0;
                    break;
                }
            } else
            if (l == -2)
            {
                k = 0;
                i = 0;
            }
            continue; /* Loop/switch isn't completed */
_L2:
            if (l >= 0)
            {
                k = l;
                i = 0x40000000;
            } else
            if (l == -1)
            {
                k = i1;
                i = j;
            } else
            if (l == -2)
            {
                k = i1;
                if (j == 0x80000000 || j == 0x40000000)
                {
                    i = 0x80000000;
                } else
                {
                    i = 0;
                }
            }
            if (true) goto _L6; else goto _L5
_L5:
        }

        public static int getChildMeasureSpec(int i, int j, int k, boolean flag)
        {
            int l;
            l = Math.max(0, i - j);
            j = 0;
            i = 0;
            if (!flag) goto _L2; else goto _L1
_L1:
            if (k >= 0)
            {
                j = k;
                i = 0x40000000;
            } else
            {
                j = 0;
                i = 0;
            }
_L4:
            return android.view.View.MeasureSpec.makeMeasureSpec(j, i);
_L2:
            if (k >= 0)
            {
                j = k;
                i = 0x40000000;
            } else
            if (k == -1)
            {
                j = l;
                i = 0x40000000;
            } else
            if (k == -2)
            {
                j = l;
                i = 0x80000000;
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        private int[] getChildRectangleOnScreenScrollAmount(RecyclerView recyclerview, View view, Rect rect, boolean flag)
        {
            int k1 = getPaddingLeft();
            int l = getPaddingTop();
            int l1 = getWidth() - getPaddingRight();
            int j1 = getHeight();
            int k2 = getPaddingBottom();
            int i2 = (view.getLeft() + rect.left) - view.getScrollX();
            int i1 = (view.getTop() + rect.top) - view.getScrollY();
            int j2 = i2 + rect.width();
            int l2 = rect.height();
            int i = Math.min(0, i2 - k1);
            int j = Math.min(0, i1 - l);
            int k = Math.max(0, j2 - l1);
            j1 = Math.max(0, (i1 + l2) - (j1 - k2));
            if (getLayoutDirection() == 1)
            {
                if (k != 0)
                {
                    i = k;
                } else
                {
                    i = Math.max(i, j2 - l1);
                }
            } else
            if (i == 0)
            {
                i = Math.min(i2 - k1, k);
            }
            if (j == 0)
            {
                j = Math.min(i1 - l, j1);
            }
            return (new int[] {
                i, j
            });
        }

        public static Properties getProperties(Context context, AttributeSet attributeset, int i, int j)
        {
            Properties properties = new Properties();
            context = context.obtainStyledAttributes(attributeset, android.support.v7.recyclerview.R.styleable.RecyclerView, i, j);
            properties.orientation = context.getInt(android.support.v7.recyclerview.R.styleable.RecyclerView_android_orientation, 1);
            properties.spanCount = context.getInt(android.support.v7.recyclerview.R.styleable.RecyclerView_spanCount, 1);
            properties.reverseLayout = context.getBoolean(android.support.v7.recyclerview.R.styleable.RecyclerView_reverseLayout, false);
            properties.stackFromEnd = context.getBoolean(android.support.v7.recyclerview.R.styleable.RecyclerView_stackFromEnd, false);
            context.recycle();
            return properties;
        }

        private boolean isFocusedChildVisibleAfterScrolling(RecyclerView recyclerview, int i, int j)
        {
            recyclerview = recyclerview.getFocusedChild();
            if (recyclerview != null)
            {
                int k = getPaddingLeft();
                int l = getPaddingTop();
                int i1 = getWidth();
                int j1 = getPaddingRight();
                int k1 = getHeight();
                int l1 = getPaddingBottom();
                Rect rect = mRecyclerView.mTempRect;
                getDecoratedBoundsWithMargins(recyclerview, rect);
                if (rect.left - i < i1 - j1 && rect.right - i > k && rect.top - j < k1 - l1 && rect.bottom - j > l)
                {
                    return true;
                }
            }
            return false;
        }

        private static boolean isMeasurementUpToDate(int i, int j, int k)
        {
            int l;
            boolean flag1;
            flag1 = true;
            l = android.view.View.MeasureSpec.getMode(j);
            j = android.view.View.MeasureSpec.getSize(j);
            if (k <= 0 || i == k) goto _L2; else goto _L1
_L1:
            boolean flag = false;
_L4:
            return flag;
_L2:
            flag = flag1;
            switch (l)
            {
            default:
                return false;

            case 0: // '\0'
                break;

            case -2147483648: 
                flag = flag1;
                if (j < i)
                {
                    return false;
                }
                break;

            case 1073741824: 
                flag = flag1;
                continue; /* Loop/switch isn't completed */
            }
            if (true) goto _L4; else goto _L3
_L3:
            if (j == i) goto _L4; else goto _L5
_L5:
            return false;
        }

        private void onSmoothScrollerStopped(SmoothScroller smoothscroller)
        {
            if (mSmoothScroller == smoothscroller)
            {
                mSmoothScroller = null;
            }
        }

        private void scrapOrRecycleView(Recycler recycler, int i, View view)
        {
            ViewHolder viewholder = RecyclerView.getChildViewHolderInt(view);
            if (viewholder.shouldIgnore())
            {
                return;
            }
            if (viewholder.isInvalid() && !viewholder.isRemoved() && !mRecyclerView.mAdapter.hasStableIds())
            {
                removeViewAt(i);
                recycler.recycleViewHolderInternal(viewholder);
                return;
            } else
            {
                detachViewAt(i);
                recycler.scrapView(view);
                mRecyclerView.mViewInfoStore.onViewDetached(viewholder);
                return;
            }
        }

        public void addDisappearingView(View view)
        {
            addDisappearingView(view, -1);
        }

        public void addDisappearingView(View view, int i)
        {
            addViewInt(view, i, true);
        }

        public void addView(View view)
        {
            addView(view, -1);
        }

        public void addView(View view, int i)
        {
            addViewInt(view, i, false);
        }

        public void assertInLayoutOrScroll(String s)
        {
            if (mRecyclerView != null)
            {
                mRecyclerView.assertInLayoutOrScroll(s);
            }
        }

        public void assertNotInLayoutOrScroll(String s)
        {
            if (mRecyclerView != null)
            {
                mRecyclerView.assertNotInLayoutOrScroll(s);
            }
        }

        public void attachView(View view)
        {
            attachView(view, -1);
        }

        public void attachView(View view, int i)
        {
            attachView(view, i, (LayoutParams)view.getLayoutParams());
        }

        public void attachView(View view, int i, LayoutParams layoutparams)
        {
            ViewHolder viewholder = RecyclerView.getChildViewHolderInt(view);
            if (viewholder.isRemoved())
            {
                mRecyclerView.mViewInfoStore.addToDisappearedInLayout(viewholder);
            } else
            {
                mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(viewholder);
            }
            mChildHelper.attachViewToParent(view, i, layoutparams, viewholder.isRemoved());
        }

        public void calculateItemDecorationsForChild(View view, Rect rect)
        {
            if (mRecyclerView == null)
            {
                rect.set(0, 0, 0, 0);
                return;
            } else
            {
                rect.set(mRecyclerView.getItemDecorInsetsForChild(view));
                return;
            }
        }

        public boolean canScrollHorizontally()
        {
            return false;
        }

        public boolean canScrollVertically()
        {
            return false;
        }

        public boolean checkLayoutParams(LayoutParams layoutparams)
        {
            return layoutparams != null;
        }

        public void collectAdjacentPrefetchPositions(int i, int j, State state, LayoutPrefetchRegistry layoutprefetchregistry)
        {
        }

        public void collectInitialPrefetchPositions(int i, LayoutPrefetchRegistry layoutprefetchregistry)
        {
        }

        public int computeHorizontalScrollExtent(State state)
        {
            return 0;
        }

        public int computeHorizontalScrollOffset(State state)
        {
            return 0;
        }

        public int computeHorizontalScrollRange(State state)
        {
            return 0;
        }

        public int computeVerticalScrollExtent(State state)
        {
            return 0;
        }

        public int computeVerticalScrollOffset(State state)
        {
            return 0;
        }

        public int computeVerticalScrollRange(State state)
        {
            return 0;
        }

        public void detachAndScrapAttachedViews(Recycler recycler)
        {
            for (int i = getChildCount() - 1; i >= 0; i--)
            {
                scrapOrRecycleView(recycler, i, getChildAt(i));
            }

        }

        public void detachAndScrapView(View view, Recycler recycler)
        {
            scrapOrRecycleView(recycler, mChildHelper.indexOfChild(view), view);
        }

        public void detachAndScrapViewAt(int i, Recycler recycler)
        {
            scrapOrRecycleView(recycler, i, getChildAt(i));
        }

        public void detachView(View view)
        {
            int i = mChildHelper.indexOfChild(view);
            if (i >= 0)
            {
                detachViewInternal(i, view);
            }
        }

        public void detachViewAt(int i)
        {
            detachViewInternal(i, getChildAt(i));
        }

        void dispatchAttachedToWindow(RecyclerView recyclerview)
        {
            mIsAttachedToWindow = true;
            onAttachedToWindow(recyclerview);
        }

        void dispatchDetachedFromWindow(RecyclerView recyclerview, Recycler recycler)
        {
            mIsAttachedToWindow = false;
            onDetachedFromWindow(recyclerview, recycler);
        }

        public void endAnimation(View view)
        {
            if (mRecyclerView.mItemAnimator != null)
            {
                mRecyclerView.mItemAnimator.endAnimation(RecyclerView.getChildViewHolderInt(view));
            }
        }

        public View findContainingItemView(View view)
        {
            if (mRecyclerView == null)
            {
                view = null;
            } else
            {
                View view1 = mRecyclerView.findContainingItemView(view);
                if (view1 == null)
                {
                    return null;
                }
                view = view1;
                if (mChildHelper.isHidden(view1))
                {
                    return null;
                }
            }
            return view;
        }

        public View findViewByPosition(int i)
        {
            int j;
            int k;
            k = getChildCount();
            j = 0;
_L3:
            View view;
            ViewHolder viewholder;
            if (j >= k)
            {
                break; /* Loop/switch isn't completed */
            }
            view = getChildAt(j);
            viewholder = RecyclerView.getChildViewHolderInt(view);
              goto _L1
_L5:
            j++;
            if (true) goto _L3; else goto _L2
_L1:
            if (viewholder == null || viewholder.getLayoutPosition() != i || viewholder.shouldIgnore() || !mRecyclerView.mState.isPreLayout() && viewholder.isRemoved()) goto _L5; else goto _L4
_L4:
            return view;
_L2:
            return null;
        }

        public abstract LayoutParams generateDefaultLayoutParams();

        public LayoutParams generateLayoutParams(Context context, AttributeSet attributeset)
        {
            return new LayoutParams(context, attributeset);
        }

        public LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            if (layoutparams instanceof LayoutParams)
            {
                return new LayoutParams((LayoutParams)layoutparams);
            }
            if (layoutparams instanceof android.view.ViewGroup.MarginLayoutParams)
            {
                return new LayoutParams((android.view.ViewGroup.MarginLayoutParams)layoutparams);
            } else
            {
                return new LayoutParams(layoutparams);
            }
        }

        public int getBaseline()
        {
            return -1;
        }

        public int getBottomDecorationHeight(View view)
        {
            return ((LayoutParams)view.getLayoutParams()).mDecorInsets.bottom;
        }

        public View getChildAt(int i)
        {
            if (mChildHelper != null)
            {
                return mChildHelper.getChildAt(i);
            } else
            {
                return null;
            }
        }

        public int getChildCount()
        {
            if (mChildHelper != null)
            {
                return mChildHelper.getChildCount();
            } else
            {
                return 0;
            }
        }

        public boolean getClipToPadding()
        {
            return mRecyclerView != null && mRecyclerView.mClipToPadding;
        }

        public int getColumnCountForAccessibility(Recycler recycler, State state)
        {
            while (mRecyclerView == null || mRecyclerView.mAdapter == null || !canScrollHorizontally()) 
            {
                return 1;
            }
            return mRecyclerView.mAdapter.getItemCount();
        }

        public int getDecoratedBottom(View view)
        {
            return view.getBottom() + getBottomDecorationHeight(view);
        }

        public void getDecoratedBoundsWithMargins(View view, Rect rect)
        {
            RecyclerView.getDecoratedBoundsWithMarginsInt(view, rect);
        }

        public int getDecoratedLeft(View view)
        {
            return view.getLeft() - getLeftDecorationWidth(view);
        }

        public int getDecoratedMeasuredHeight(View view)
        {
            Rect rect = ((LayoutParams)view.getLayoutParams()).mDecorInsets;
            return view.getMeasuredHeight() + rect.top + rect.bottom;
        }

        public int getDecoratedMeasuredWidth(View view)
        {
            Rect rect = ((LayoutParams)view.getLayoutParams()).mDecorInsets;
            return view.getMeasuredWidth() + rect.left + rect.right;
        }

        public int getDecoratedRight(View view)
        {
            return view.getRight() + getRightDecorationWidth(view);
        }

        public int getDecoratedTop(View view)
        {
            return view.getTop() - getTopDecorationHeight(view);
        }

        public View getFocusedChild()
        {
            if (mRecyclerView != null) goto _L2; else goto _L1
_L1:
            View view = null;
_L4:
            return view;
_L2:
            View view1;
            view1 = mRecyclerView.getFocusedChild();
            if (view1 == null)
            {
                break; /* Loop/switch isn't completed */
            }
            view = view1;
            if (!mChildHelper.isHidden(view1)) goto _L4; else goto _L3
_L3:
            return null;
        }

        public int getHeight()
        {
            return mHeight;
        }

        public int getHeightMode()
        {
            return mHeightMode;
        }

        public int getItemCount()
        {
            Adapter adapter;
            if (mRecyclerView != null)
            {
                adapter = mRecyclerView.getAdapter();
            } else
            {
                adapter = null;
            }
            if (adapter != null)
            {
                return adapter.getItemCount();
            } else
            {
                return 0;
            }
        }

        public int getItemViewType(View view)
        {
            return RecyclerView.getChildViewHolderInt(view).getItemViewType();
        }

        public int getLayoutDirection()
        {
            return ViewCompat.getLayoutDirection(mRecyclerView);
        }

        public int getLeftDecorationWidth(View view)
        {
            return ((LayoutParams)view.getLayoutParams()).mDecorInsets.left;
        }

        public int getMinimumHeight()
        {
            return ViewCompat.getMinimumHeight(mRecyclerView);
        }

        public int getMinimumWidth()
        {
            return ViewCompat.getMinimumWidth(mRecyclerView);
        }

        public int getPaddingBottom()
        {
            if (mRecyclerView != null)
            {
                return mRecyclerView.getPaddingBottom();
            } else
            {
                return 0;
            }
        }

        public int getPaddingEnd()
        {
            if (mRecyclerView != null)
            {
                return ViewCompat.getPaddingEnd(mRecyclerView);
            } else
            {
                return 0;
            }
        }

        public int getPaddingLeft()
        {
            if (mRecyclerView != null)
            {
                return mRecyclerView.getPaddingLeft();
            } else
            {
                return 0;
            }
        }

        public int getPaddingRight()
        {
            if (mRecyclerView != null)
            {
                return mRecyclerView.getPaddingRight();
            } else
            {
                return 0;
            }
        }

        public int getPaddingStart()
        {
            if (mRecyclerView != null)
            {
                return ViewCompat.getPaddingStart(mRecyclerView);
            } else
            {
                return 0;
            }
        }

        public int getPaddingTop()
        {
            if (mRecyclerView != null)
            {
                return mRecyclerView.getPaddingTop();
            } else
            {
                return 0;
            }
        }

        public int getPosition(View view)
        {
            return ((LayoutParams)view.getLayoutParams()).getViewLayoutPosition();
        }

        public int getRightDecorationWidth(View view)
        {
            return ((LayoutParams)view.getLayoutParams()).mDecorInsets.right;
        }

        public int getRowCountForAccessibility(Recycler recycler, State state)
        {
            while (mRecyclerView == null || mRecyclerView.mAdapter == null || !canScrollVertically()) 
            {
                return 1;
            }
            return mRecyclerView.mAdapter.getItemCount();
        }

        public int getSelectionModeForAccessibility(Recycler recycler, State state)
        {
            return 0;
        }

        public int getTopDecorationHeight(View view)
        {
            return ((LayoutParams)view.getLayoutParams()).mDecorInsets.top;
        }

        public void getTransformedBoundingBox(View view, boolean flag, Rect rect)
        {
            if (flag)
            {
                Rect rect1 = ((LayoutParams)view.getLayoutParams()).mDecorInsets;
                rect.set(-rect1.left, -rect1.top, view.getWidth() + rect1.right, view.getHeight() + rect1.bottom);
            } else
            {
                rect.set(0, 0, view.getWidth(), view.getHeight());
            }
            if (mRecyclerView != null)
            {
                Matrix matrix = view.getMatrix();
                if (matrix != null && !matrix.isIdentity())
                {
                    RectF rectf = mRecyclerView.mTempRectF;
                    rectf.set(rect);
                    matrix.mapRect(rectf);
                    rect.set((int)Math.floor(rectf.left), (int)Math.floor(rectf.top), (int)Math.ceil(rectf.right), (int)Math.ceil(rectf.bottom));
                }
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        public int getWidth()
        {
            return mWidth;
        }

        public int getWidthMode()
        {
            return mWidthMode;
        }

        boolean hasFlexibleChildInBothOrientations()
        {
            int j = getChildCount();
            for (int i = 0; i < j; i++)
            {
                android.view.ViewGroup.LayoutParams layoutparams = getChildAt(i).getLayoutParams();
                if (layoutparams.width < 0 && layoutparams.height < 0)
                {
                    return true;
                }
            }

            return false;
        }

        public boolean hasFocus()
        {
            return mRecyclerView != null && mRecyclerView.hasFocus();
        }

        public void ignoreView(View view)
        {
            if (view.getParent() != mRecyclerView || mRecyclerView.indexOfChild(view) == -1)
            {
                throw new IllegalArgumentException("View should be fully attached to be ignored");
            } else
            {
                view = RecyclerView.getChildViewHolderInt(view);
                view.addFlags(128);
                mRecyclerView.mViewInfoStore.removeViewHolder(view);
                return;
            }
        }

        public boolean isAttachedToWindow()
        {
            return mIsAttachedToWindow;
        }

        public boolean isAutoMeasureEnabled()
        {
            return mAutoMeasure;
        }

        public boolean isFocused()
        {
            return mRecyclerView != null && mRecyclerView.isFocused();
        }

        public final boolean isItemPrefetchEnabled()
        {
            return mItemPrefetchEnabled;
        }

        public boolean isLayoutHierarchical(Recycler recycler, State state)
        {
            return false;
        }

        public boolean isMeasurementCacheEnabled()
        {
            return mMeasurementCacheEnabled;
        }

        public boolean isSmoothScrolling()
        {
            return mSmoothScroller != null && mSmoothScroller.isRunning();
        }

        public boolean isViewPartiallyVisible(View view, boolean flag, boolean flag1)
        {
            boolean flag2 = true;
            if (mHorizontalBoundCheck.isViewWithinBoundFlags(view, 24579) && mVerticalBoundCheck.isViewWithinBoundFlags(view, 24579))
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag)
            {
                return flag1;
            }
            if (!flag1)
            {
                flag = flag2;
            } else
            {
                flag = false;
            }
            return flag;
        }

        public void layoutDecorated(View view, int i, int j, int k, int l)
        {
            Rect rect = ((LayoutParams)view.getLayoutParams()).mDecorInsets;
            view.layout(rect.left + i, rect.top + j, k - rect.right, l - rect.bottom);
        }

        public void layoutDecoratedWithMargins(View view, int i, int j, int k, int l)
        {
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            Rect rect = layoutparams.mDecorInsets;
            view.layout(rect.left + i + layoutparams.leftMargin, rect.top + j + layoutparams.topMargin, k - rect.right - layoutparams.rightMargin, l - rect.bottom - layoutparams.bottomMargin);
        }

        public void measureChild(View view, int i, int j)
        {
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            Rect rect = mRecyclerView.getItemDecorInsetsForChild(view);
            int i1 = rect.left;
            int j1 = rect.right;
            int k = rect.top;
            int l = rect.bottom;
            i = getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight() + (i + (i1 + j1)), layoutparams.width, canScrollHorizontally());
            j = getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom() + (j + (k + l)), layoutparams.height, canScrollVertically());
            if (shouldMeasureChild(view, i, j, layoutparams))
            {
                view.measure(i, j);
            }
        }

        public void measureChildWithMargins(View view, int i, int j)
        {
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            Rect rect = mRecyclerView.getItemDecorInsetsForChild(view);
            int i1 = rect.left;
            int j1 = rect.right;
            int k = rect.top;
            int l = rect.bottom;
            i = getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight() + layoutparams.leftMargin + layoutparams.rightMargin + (i + (i1 + j1)), layoutparams.width, canScrollHorizontally());
            j = getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom() + layoutparams.topMargin + layoutparams.bottomMargin + (j + (k + l)), layoutparams.height, canScrollVertically());
            if (shouldMeasureChild(view, i, j, layoutparams))
            {
                view.measure(i, j);
            }
        }

        public void moveView(int i, int j)
        {
            View view = getChildAt(i);
            if (view == null)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Cannot move a child from non-existing index:").append(i).toString());
            } else
            {
                detachViewAt(i);
                attachView(view, j);
                return;
            }
        }

        public void offsetChildrenHorizontal(int i)
        {
            if (mRecyclerView != null)
            {
                mRecyclerView.offsetChildrenHorizontal(i);
            }
        }

        public void offsetChildrenVertical(int i)
        {
            if (mRecyclerView != null)
            {
                mRecyclerView.offsetChildrenVertical(i);
            }
        }

        public void onAdapterChanged(Adapter adapter, Adapter adapter1)
        {
        }

        public boolean onAddFocusables(RecyclerView recyclerview, ArrayList arraylist, int i, int j)
        {
            return false;
        }

        public void onAttachedToWindow(RecyclerView recyclerview)
        {
        }

        public void onDetachedFromWindow(RecyclerView recyclerview)
        {
        }

        public void onDetachedFromWindow(RecyclerView recyclerview, Recycler recycler)
        {
            onDetachedFromWindow(recyclerview);
        }

        public View onFocusSearchFailed(View view, int i, Recycler recycler, State state)
        {
            return null;
        }

        public void onInitializeAccessibilityEvent(Recycler recycler, State state, AccessibilityEvent accessibilityevent)
        {
            boolean flag1 = true;
            recycler = AccessibilityEventCompat.asRecord(accessibilityevent);
            if (mRecyclerView != null && recycler != null)
            {
                boolean flag = flag1;
                if (!mRecyclerView.canScrollVertically(1))
                {
                    flag = flag1;
                    if (!mRecyclerView.canScrollVertically(-1))
                    {
                        flag = flag1;
                        if (!mRecyclerView.canScrollHorizontally(-1))
                        {
                            if (mRecyclerView.canScrollHorizontally(1))
                            {
                                flag = flag1;
                            } else
                            {
                                flag = false;
                            }
                        }
                    }
                }
                recycler.setScrollable(flag);
                if (mRecyclerView.mAdapter != null)
                {
                    recycler.setItemCount(mRecyclerView.mAdapter.getItemCount());
                    return;
                }
            }
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
        {
            onInitializeAccessibilityEvent(mRecyclerView.mRecycler, mRecyclerView.mState, accessibilityevent);
        }

        void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            onInitializeAccessibilityNodeInfo(mRecyclerView.mRecycler, mRecyclerView.mState, accessibilitynodeinfocompat);
        }

        public void onInitializeAccessibilityNodeInfo(Recycler recycler, State state, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            if (mRecyclerView.canScrollVertically(-1) || mRecyclerView.canScrollHorizontally(-1))
            {
                accessibilitynodeinfocompat.addAction(8192);
                accessibilitynodeinfocompat.setScrollable(true);
            }
            if (mRecyclerView.canScrollVertically(1) || mRecyclerView.canScrollHorizontally(1))
            {
                accessibilitynodeinfocompat.addAction(4096);
                accessibilitynodeinfocompat.setScrollable(true);
            }
            accessibilitynodeinfocompat.setCollectionInfo(android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(getRowCountForAccessibility(recycler, state), getColumnCountForAccessibility(recycler, state), isLayoutHierarchical(recycler, state), getSelectionModeForAccessibility(recycler, state)));
        }

        public void onInitializeAccessibilityNodeInfoForItem(Recycler recycler, State state, View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            int i;
            int j;
            if (canScrollVertically())
            {
                i = getPosition(view);
            } else
            {
                i = 0;
            }
            if (canScrollHorizontally())
            {
                j = getPosition(view);
            } else
            {
                j = 0;
            }
            accessibilitynodeinfocompat.setCollectionItemInfo(android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(i, 1, j, 1, false, false));
        }

        void onInitializeAccessibilityNodeInfoForItem(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            ViewHolder viewholder = RecyclerView.getChildViewHolderInt(view);
            if (viewholder != null && !viewholder.isRemoved() && !mChildHelper.isHidden(viewholder.itemView))
            {
                onInitializeAccessibilityNodeInfoForItem(mRecyclerView.mRecycler, mRecyclerView.mState, view, accessibilitynodeinfocompat);
            }
        }

        public View onInterceptFocusSearch(View view, int i)
        {
            return null;
        }

        public void onItemsAdded(RecyclerView recyclerview, int i, int j)
        {
        }

        public void onItemsChanged(RecyclerView recyclerview)
        {
        }

        public void onItemsMoved(RecyclerView recyclerview, int i, int j, int k)
        {
        }

        public void onItemsRemoved(RecyclerView recyclerview, int i, int j)
        {
        }

        public void onItemsUpdated(RecyclerView recyclerview, int i, int j)
        {
        }

        public void onItemsUpdated(RecyclerView recyclerview, int i, int j, Object obj)
        {
            onItemsUpdated(recyclerview, i, j);
        }

        public void onLayoutChildren(Recycler recycler, State state)
        {
            Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
        }

        public void onLayoutCompleted(State state)
        {
        }

        public void onMeasure(Recycler recycler, State state, int i, int j)
        {
            mRecyclerView.defaultOnMeasure(i, j);
        }

        public boolean onRequestChildFocus(RecyclerView recyclerview, State state, View view, View view1)
        {
            return onRequestChildFocus(recyclerview, view, view1);
        }

        public boolean onRequestChildFocus(RecyclerView recyclerview, View view, View view1)
        {
            return isSmoothScrolling() || recyclerview.isComputingLayout();
        }

        public void onRestoreInstanceState(Parcelable parcelable)
        {
        }

        public Parcelable onSaveInstanceState()
        {
            return null;
        }

        public void onScrollStateChanged(int i)
        {
        }

        boolean performAccessibilityAction(int i, Bundle bundle)
        {
            return performAccessibilityAction(mRecyclerView.mRecycler, mRecyclerView.mState, i, bundle);
        }

        public boolean performAccessibilityAction(Recycler recycler, State state, int i, Bundle bundle)
        {
            if (mRecyclerView != null) goto _L2; else goto _L1
_L1:
            return false;
_L2:
            int l;
            boolean flag1;
            boolean flag2;
            flag1 = false;
            flag2 = false;
            boolean flag = false;
            l = 0;
            switch (i)
            {
            default:
                i = ((flag) ? 1 : 0);
                break;

            case 4096: 
                break MISSING_BLOCK_LABEL_141;

            case 8192: 
                break; /* Loop/switch isn't completed */
            }
_L4:
            if (i != 0 || l != 0)
            {
                mRecyclerView.scrollBy(l, i);
                return true;
            }
            if (true) goto _L1; else goto _L3
_L3:
            int j = ((flag1) ? 1 : 0);
            if (mRecyclerView.canScrollVertically(-1))
            {
                j = -(getHeight() - getPaddingTop() - getPaddingBottom());
            }
            i = j;
            if (mRecyclerView.canScrollHorizontally(-1))
            {
                l = -(getWidth() - getPaddingLeft() - getPaddingRight());
                i = j;
            }
              goto _L4
            int k = ((flag2) ? 1 : 0);
            if (mRecyclerView.canScrollVertically(1))
            {
                k = getHeight() - getPaddingTop() - getPaddingBottom();
            }
            i = k;
            if (mRecyclerView.canScrollHorizontally(1))
            {
                l = getWidth() - getPaddingLeft() - getPaddingRight();
                i = k;
            }
              goto _L4
        }

        public boolean performAccessibilityActionForItem(Recycler recycler, State state, View view, int i, Bundle bundle)
        {
            return false;
        }

        boolean performAccessibilityActionForItem(View view, int i, Bundle bundle)
        {
            return performAccessibilityActionForItem(mRecyclerView.mRecycler, mRecyclerView.mState, view, i, bundle);
        }

        public void postOnAnimation(Runnable runnable)
        {
            if (mRecyclerView != null)
            {
                ViewCompat.postOnAnimation(mRecyclerView, runnable);
            }
        }

        public void removeAllViews()
        {
            for (int i = getChildCount() - 1; i >= 0; i--)
            {
                mChildHelper.removeViewAt(i);
            }

        }

        public void removeAndRecycleAllViews(Recycler recycler)
        {
            for (int i = getChildCount() - 1; i >= 0; i--)
            {
                if (!RecyclerView.getChildViewHolderInt(getChildAt(i)).shouldIgnore())
                {
                    removeAndRecycleViewAt(i, recycler);
                }
            }

        }

        void removeAndRecycleScrapInt(Recycler recycler)
        {
            int j = recycler.getScrapCount();
            int i = j - 1;
            while (i >= 0) 
            {
                View view = recycler.getScrapViewAt(i);
                ViewHolder viewholder = RecyclerView.getChildViewHolderInt(view);
                if (!viewholder.shouldIgnore())
                {
                    viewholder.setIsRecyclable(false);
                    if (viewholder.isTmpDetached())
                    {
                        mRecyclerView.removeDetachedView(view, false);
                    }
                    if (mRecyclerView.mItemAnimator != null)
                    {
                        mRecyclerView.mItemAnimator.endAnimation(viewholder);
                    }
                    viewholder.setIsRecyclable(true);
                    recycler.quickRecycleScrapView(view);
                }
                i--;
            }
            recycler.clearScrap();
            if (j > 0)
            {
                mRecyclerView.invalidate();
            }
        }

        public void removeAndRecycleView(View view, Recycler recycler)
        {
            removeView(view);
            recycler.recycleView(view);
        }

        public void removeAndRecycleViewAt(int i, Recycler recycler)
        {
            View view = getChildAt(i);
            removeViewAt(i);
            recycler.recycleView(view);
        }

        public boolean removeCallbacks(Runnable runnable)
        {
            if (mRecyclerView != null)
            {
                return mRecyclerView.removeCallbacks(runnable);
            } else
            {
                return false;
            }
        }

        public void removeDetachedView(View view)
        {
            mRecyclerView.removeDetachedView(view, false);
        }

        public void removeView(View view)
        {
            mChildHelper.removeView(view);
        }

        public void removeViewAt(int i)
        {
            if (getChildAt(i) != null)
            {
                mChildHelper.removeViewAt(i);
            }
        }

        public boolean requestChildRectangleOnScreen(RecyclerView recyclerview, View view, Rect rect, boolean flag)
        {
            return requestChildRectangleOnScreen(recyclerview, view, rect, flag, false);
        }

        public boolean requestChildRectangleOnScreen(RecyclerView recyclerview, View view, Rect rect, boolean flag, boolean flag1)
        {
label0:
            {
                boolean flag2 = false;
                view = getChildRectangleOnScreenScrollAmount(recyclerview, view, rect, flag);
                int i = view[0];
                int j = view[1];
                if (flag1)
                {
                    flag1 = flag2;
                    if (!isFocusedChildVisibleAfterScrolling(recyclerview, i, j))
                    {
                        break label0;
                    }
                }
                if (i == 0)
                {
                    flag1 = flag2;
                    if (j == 0)
                    {
                        break label0;
                    }
                }
                if (flag)
                {
                    recyclerview.scrollBy(i, j);
                } else
                {
                    recyclerview.smoothScrollBy(i, j);
                }
                flag1 = true;
            }
            return flag1;
        }

        public void requestLayout()
        {
            if (mRecyclerView != null)
            {
                mRecyclerView.requestLayout();
            }
        }

        public void requestSimpleAnimationsInNextLayout()
        {
            mRequestedSimpleAnimations = true;
        }

        public int scrollHorizontallyBy(int i, Recycler recycler, State state)
        {
            return 0;
        }

        public void scrollToPosition(int i)
        {
        }

        public int scrollVerticallyBy(int i, Recycler recycler, State state)
        {
            return 0;
        }

        public void setAutoMeasureEnabled(boolean flag)
        {
            mAutoMeasure = flag;
        }

        void setExactMeasureSpecsFrom(RecyclerView recyclerview)
        {
            setMeasureSpecs(android.view.View.MeasureSpec.makeMeasureSpec(recyclerview.getWidth(), 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(recyclerview.getHeight(), 0x40000000));
        }

        public final void setItemPrefetchEnabled(boolean flag)
        {
            if (flag != mItemPrefetchEnabled)
            {
                mItemPrefetchEnabled = flag;
                mPrefetchMaxCountObserved = 0;
                if (mRecyclerView != null)
                {
                    mRecyclerView.mRecycler.updateViewCacheSize();
                }
            }
        }

        void setMeasureSpecs(int i, int j)
        {
            mWidth = android.view.View.MeasureSpec.getSize(i);
            mWidthMode = android.view.View.MeasureSpec.getMode(i);
            if (mWidthMode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC)
            {
                mWidth = 0;
            }
            mHeight = android.view.View.MeasureSpec.getSize(j);
            mHeightMode = android.view.View.MeasureSpec.getMode(j);
            if (mHeightMode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC)
            {
                mHeight = 0;
            }
        }

        public void setMeasuredDimension(int i, int j)
        {
            mRecyclerView.setMeasuredDimension(i, j);
        }

        public void setMeasuredDimension(Rect rect, int i, int j)
        {
            int k = rect.width();
            int l = getPaddingLeft();
            int i1 = getPaddingRight();
            int j1 = rect.height();
            int k1 = getPaddingTop();
            int l1 = getPaddingBottom();
            setMeasuredDimension(chooseSize(i, k + l + i1, getMinimumWidth()), chooseSize(j, j1 + k1 + l1, getMinimumHeight()));
        }

        void setMeasuredDimensionFromChildren(int i, int j)
        {
            int j2 = getChildCount();
            if (j2 == 0)
            {
                mRecyclerView.defaultOnMeasure(i, j);
                return;
            }
            int l1 = 0x7fffffff;
            int l = 0x7fffffff;
            int k1 = 0x80000000;
            int k = 0x80000000;
            for (int i1 = 0; i1 < j2;)
            {
                View view = getChildAt(i1);
                Rect rect = mRecyclerView.mTempRect;
                getDecoratedBoundsWithMargins(view, rect);
                int j1 = l1;
                if (rect.left < l1)
                {
                    j1 = rect.left;
                }
                l1 = k1;
                if (rect.right > k1)
                {
                    l1 = rect.right;
                }
                int i2 = l;
                if (rect.top < l)
                {
                    i2 = rect.top;
                }
                l = k;
                if (rect.bottom > k)
                {
                    l = rect.bottom;
                }
                i1++;
                k1 = l1;
                k = l;
                l1 = j1;
                l = i2;
            }

            mRecyclerView.mTempRect.set(l1, l, k1, k);
            setMeasuredDimension(mRecyclerView.mTempRect, i, j);
        }

        public void setMeasurementCacheEnabled(boolean flag)
        {
            mMeasurementCacheEnabled = flag;
        }

        void setRecyclerView(RecyclerView recyclerview)
        {
            if (recyclerview == null)
            {
                mRecyclerView = null;
                mChildHelper = null;
                mWidth = 0;
                mHeight = 0;
            } else
            {
                mRecyclerView = recyclerview;
                mChildHelper = recyclerview.mChildHelper;
                mWidth = recyclerview.getWidth();
                mHeight = recyclerview.getHeight();
            }
            mWidthMode = 0x40000000;
            mHeightMode = 0x40000000;
        }

        boolean shouldMeasureChild(View view, int i, int j, LayoutParams layoutparams)
        {
            return view.isLayoutRequested() || !mMeasurementCacheEnabled || !isMeasurementUpToDate(view.getWidth(), i, layoutparams.width) || !isMeasurementUpToDate(view.getHeight(), j, layoutparams.height);
        }

        boolean shouldMeasureTwice()
        {
            return false;
        }

        boolean shouldReMeasureChild(View view, int i, int j, LayoutParams layoutparams)
        {
            return !mMeasurementCacheEnabled || !isMeasurementUpToDate(view.getMeasuredWidth(), i, layoutparams.width) || !isMeasurementUpToDate(view.getMeasuredHeight(), j, layoutparams.height);
        }

        public void smoothScrollToPosition(RecyclerView recyclerview, State state, int i)
        {
            Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
        }

        public void startSmoothScroll(SmoothScroller smoothscroller)
        {
            if (mSmoothScroller != null && smoothscroller != mSmoothScroller && mSmoothScroller.isRunning())
            {
                mSmoothScroller.stop();
            }
            mSmoothScroller = smoothscroller;
            mSmoothScroller.start(mRecyclerView, this);
        }

        public void stopIgnoringView(View view)
        {
            view = RecyclerView.getChildViewHolderInt(view);
            view.stopIgnoring();
            view.resetInternal();
            view.addFlags(4);
        }

        void stopSmoothScroller()
        {
            if (mSmoothScroller != null)
            {
                mSmoothScroller.stop();
            }
        }

        public boolean supportsPredictiveItemAnimations()
        {
            return false;
        }


        public LayoutManager()
        {
            mHorizontalBoundCheck = new ViewBoundsCheck(mHorizontalBoundCheckCallback);
            mVerticalBoundCheck = new ViewBoundsCheck(mVerticalBoundCheckCallback);
            mRequestedSimpleAnimations = false;
            mIsAttachedToWindow = false;
            mAutoMeasure = false;
            mMeasurementCacheEnabled = true;
            mItemPrefetchEnabled = true;
        }
    }

    public static interface LayoutManager.LayoutPrefetchRegistry
    {

        public abstract void addPosition(int i, int j);
    }

    public static class LayoutManager.Properties
    {

        public int orientation;
        public boolean reverseLayout;
        public int spanCount;
        public boolean stackFromEnd;

        public LayoutManager.Properties()
        {
        }
    }

    public static class LayoutParams extends android.view.ViewGroup.MarginLayoutParams
    {

        final Rect mDecorInsets;
        boolean mInsetsDirty;
        boolean mPendingInvalidate;
        ViewHolder mViewHolder;

        public int getViewAdapterPosition()
        {
            return mViewHolder.getAdapterPosition();
        }

        public int getViewLayoutPosition()
        {
            return mViewHolder.getLayoutPosition();
        }

        public int getViewPosition()
        {
            return mViewHolder.getPosition();
        }

        public boolean isItemChanged()
        {
            return mViewHolder.isUpdated();
        }

        public boolean isItemRemoved()
        {
            return mViewHolder.isRemoved();
        }

        public boolean isViewInvalid()
        {
            return mViewHolder.isInvalid();
        }

        public boolean viewNeedsUpdate()
        {
            return mViewHolder.needsUpdate();
        }

        public LayoutParams(int i, int j)
        {
            super(i, j);
            mDecorInsets = new Rect();
            mInsetsDirty = true;
            mPendingInvalidate = false;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            mDecorInsets = new Rect();
            mInsetsDirty = true;
            mPendingInvalidate = false;
        }

        public LayoutParams(LayoutParams layoutparams)
        {
            super(layoutparams);
            mDecorInsets = new Rect();
            mInsetsDirty = true;
            mPendingInvalidate = false;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            mDecorInsets = new Rect();
            mInsetsDirty = true;
            mPendingInvalidate = false;
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
            mDecorInsets = new Rect();
            mInsetsDirty = true;
            mPendingInvalidate = false;
        }
    }

    public static interface OnChildAttachStateChangeListener
    {

        public abstract void onChildViewAttachedToWindow(View view);

        public abstract void onChildViewDetachedFromWindow(View view);
    }

    public static abstract class OnFlingListener
    {

        public abstract boolean onFling(int i, int j);

        public OnFlingListener()
        {
        }
    }

    public static interface OnItemTouchListener
    {

        public abstract boolean onInterceptTouchEvent(RecyclerView recyclerview, MotionEvent motionevent);

        public abstract void onRequestDisallowInterceptTouchEvent(boolean flag);

        public abstract void onTouchEvent(RecyclerView recyclerview, MotionEvent motionevent);
    }

    public static abstract class OnScrollListener
    {

        public void onScrollStateChanged(RecyclerView recyclerview, int i)
        {
        }

        public void onScrolled(RecyclerView recyclerview, int i, int j)
        {
        }

        public OnScrollListener()
        {
        }
    }

    public static class RecycledViewPool
    {

        private static final int DEFAULT_MAX_SCRAP = 5;
        private int mAttachCount;
        SparseArray mScrap;

        private ScrapData getScrapDataForType(int i)
        {
            ScrapData scrapdata1 = (ScrapData)mScrap.get(i);
            ScrapData scrapdata = scrapdata1;
            if (scrapdata1 == null)
            {
                scrapdata = new ScrapData();
                mScrap.put(i, scrapdata);
            }
            return scrapdata;
        }

        void attach(Adapter adapter)
        {
            mAttachCount = mAttachCount + 1;
        }

        public void clear()
        {
            for (int i = 0; i < mScrap.size(); i++)
            {
                ((ScrapData)mScrap.valueAt(i)).mScrapHeap.clear();
            }

        }

        void detach()
        {
            mAttachCount = mAttachCount - 1;
        }

        void factorInBindTime(int i, long l)
        {
            ScrapData scrapdata = getScrapDataForType(i);
            scrapdata.mBindRunningAverageNs = runningAverage(scrapdata.mBindRunningAverageNs, l);
        }

        void factorInCreateTime(int i, long l)
        {
            ScrapData scrapdata = getScrapDataForType(i);
            scrapdata.mCreateRunningAverageNs = runningAverage(scrapdata.mCreateRunningAverageNs, l);
        }

        public ViewHolder getRecycledView(int i)
        {
            Object obj = (ScrapData)mScrap.get(i);
            if (obj != null && !((ScrapData) (obj)).mScrapHeap.isEmpty())
            {
                obj = ((ScrapData) (obj)).mScrapHeap;
                return (ViewHolder)((ArrayList) (obj)).remove(((ArrayList) (obj)).size() - 1);
            } else
            {
                return null;
            }
        }

        public int getRecycledViewCount(int i)
        {
            return getScrapDataForType(i).mScrapHeap.size();
        }

        void onAdapterChanged(Adapter adapter, Adapter adapter1, boolean flag)
        {
            if (adapter != null)
            {
                detach();
            }
            if (!flag && mAttachCount == 0)
            {
                clear();
            }
            if (adapter1 != null)
            {
                attach(adapter1);
            }
        }

        public void putRecycledView(ViewHolder viewholder)
        {
            int i = viewholder.getItemViewType();
            ArrayList arraylist = getScrapDataForType(i).mScrapHeap;
            if (((ScrapData)mScrap.get(i)).mMaxScrap <= arraylist.size())
            {
                return;
            } else
            {
                viewholder.resetInternal();
                arraylist.add(viewholder);
                return;
            }
        }

        long runningAverage(long l, long l1)
        {
            if (l == 0L)
            {
                return l1;
            } else
            {
                return (l / 4L) * 3L + l1 / 4L;
            }
        }

        public void setMaxRecycledViews(int i, int j)
        {
            Object obj = getScrapDataForType(i);
            obj.mMaxScrap = j;
            obj = ((ScrapData) (obj)).mScrapHeap;
            if (obj != null)
            {
                for (; ((ArrayList) (obj)).size() > j; ((ArrayList) (obj)).remove(((ArrayList) (obj)).size() - 1)) { }
            }
        }

        int size()
        {
            int j = 0;
            for (int i = 0; i < mScrap.size();)
            {
                ArrayList arraylist = ((ScrapData)mScrap.valueAt(i)).mScrapHeap;
                int k = j;
                if (arraylist != null)
                {
                    k = j + arraylist.size();
                }
                i++;
                j = k;
            }

            return j;
        }

        boolean willBindInTime(int i, long l, long l1)
        {
            long l2 = getScrapDataForType(i).mBindRunningAverageNs;
            return l2 == 0L || l + l2 < l1;
        }

        boolean willCreateInTime(int i, long l, long l1)
        {
            long l2 = getScrapDataForType(i).mCreateRunningAverageNs;
            return l2 == 0L || l + l2 < l1;
        }

        public RecycledViewPool()
        {
            mScrap = new SparseArray();
            mAttachCount = 0;
        }
    }

    static class RecycledViewPool.ScrapData
    {

        long mBindRunningAverageNs;
        long mCreateRunningAverageNs;
        int mMaxScrap;
        ArrayList mScrapHeap;

        RecycledViewPool.ScrapData()
        {
            mScrapHeap = new ArrayList();
            mMaxScrap = 5;
            mCreateRunningAverageNs = 0L;
            mBindRunningAverageNs = 0L;
        }
    }

    public final class Recycler
    {

        static final int DEFAULT_CACHE_SIZE = 2;
        final ArrayList mAttachedScrap = new ArrayList();
        final ArrayList mCachedViews = new ArrayList();
        ArrayList mChangedScrap;
        RecycledViewPool mRecyclerPool;
        private int mRequestedCacheMax;
        private final List mUnmodifiableAttachedScrap;
        private ViewCacheExtension mViewCacheExtension;
        int mViewCacheMax;
        final RecyclerView this$0;

        private void attachAccessibilityDelegate(View view)
        {
            if (isAccessibilityEnabled())
            {
                if (ViewCompat.getImportantForAccessibility(view) == 0)
                {
                    ViewCompat.setImportantForAccessibility(view, 1);
                }
                if (!ViewCompat.hasAccessibilityDelegate(view))
                {
                    ViewCompat.setAccessibilityDelegate(view, mAccessibilityDelegate.getItemDelegate());
                }
            }
        }

        private void invalidateDisplayListInt(ViewHolder viewholder)
        {
            if (viewholder.itemView instanceof ViewGroup)
            {
                invalidateDisplayListInt((ViewGroup)viewholder.itemView, false);
            }
        }

        private void invalidateDisplayListInt(ViewGroup viewgroup, boolean flag)
        {
            for (int i = viewgroup.getChildCount() - 1; i >= 0; i--)
            {
                View view = viewgroup.getChildAt(i);
                if (view instanceof ViewGroup)
                {
                    invalidateDisplayListInt((ViewGroup)view, true);
                }
            }

            if (!flag)
            {
                return;
            }
            if (viewgroup.getVisibility() == 4)
            {
                viewgroup.setVisibility(0);
                viewgroup.setVisibility(4);
                return;
            } else
            {
                int j = viewgroup.getVisibility();
                viewgroup.setVisibility(4);
                viewgroup.setVisibility(j);
                return;
            }
        }

        private boolean tryBindViewHolderByDeadline(ViewHolder viewholder, int i, int j, long l)
        {
            viewholder.mOwnerRecyclerView = RecyclerView.this;
            int k = viewholder.getItemViewType();
            long l1 = getNanoTime();
            if (l != 0x7fffffffffffffffL && !mRecyclerPool.willBindInTime(k, l1, l))
            {
                return false;
            }
            mAdapter.bindViewHolder(viewholder, i);
            l = getNanoTime();
            mRecyclerPool.factorInBindTime(viewholder.getItemViewType(), l - l1);
            attachAccessibilityDelegate(viewholder.itemView);
            if (mState.isPreLayout())
            {
                viewholder.mPreLayoutPosition = j;
            }
            return true;
        }

        void addViewHolderToRecycledViewPool(ViewHolder viewholder, boolean flag)
        {
            RecyclerView.clearNestedRecyclerViewIfNotNested(viewholder);
            ViewCompat.setAccessibilityDelegate(viewholder.itemView, null);
            if (flag)
            {
                dispatchViewRecycled(viewholder);
            }
            viewholder.mOwnerRecyclerView = null;
            getRecycledViewPool().putRecycledView(viewholder);
        }

        public void bindViewToPosition(View view, int i)
        {
            ViewHolder viewholder = RecyclerView.getChildViewHolderInt(view);
            if (viewholder == null)
            {
                throw new IllegalArgumentException("The view does not have a ViewHolder. You cannot pass arbitrary views to this method, they should be created by the Adapter");
            }
            int j = mAdapterHelper.findPositionOffset(i);
            if (j < 0 || j >= mAdapter.getItemCount())
            {
                throw new IndexOutOfBoundsException((new StringBuilder()).append("Inconsistency detected. Invalid item position ").append(i).append("(offset:").append(j).append(").").append("state:").append(mState.getItemCount()).toString());
            }
            tryBindViewHolderByDeadline(viewholder, j, i, 0x7fffffffffffffffL);
            view = viewholder.itemView.getLayoutParams();
            boolean flag;
            if (view == null)
            {
                view = (LayoutParams)generateDefaultLayoutParams();
                viewholder.itemView.setLayoutParams(view);
            } else
            if (!checkLayoutParams(view))
            {
                view = (LayoutParams)generateLayoutParams(view);
                viewholder.itemView.setLayoutParams(view);
            } else
            {
                view = (LayoutParams)view;
            }
            view.mInsetsDirty = true;
            view.mViewHolder = viewholder;
            if (viewholder.itemView.getParent() == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            view.mPendingInvalidate = flag;
        }

        public void clear()
        {
            mAttachedScrap.clear();
            recycleAndClearCachedViews();
        }

        void clearOldPositions()
        {
            int l = mCachedViews.size();
            for (int i = 0; i < l; i++)
            {
                ((ViewHolder)mCachedViews.get(i)).clearOldPosition();
            }

            l = mAttachedScrap.size();
            for (int j = 0; j < l; j++)
            {
                ((ViewHolder)mAttachedScrap.get(j)).clearOldPosition();
            }

            if (mChangedScrap != null)
            {
                int i1 = mChangedScrap.size();
                for (int k = 0; k < i1; k++)
                {
                    ((ViewHolder)mChangedScrap.get(k)).clearOldPosition();
                }

            }
        }

        void clearScrap()
        {
            mAttachedScrap.clear();
            if (mChangedScrap != null)
            {
                mChangedScrap.clear();
            }
        }

        public int convertPreLayoutPositionToPostLayout(int i)
        {
            if (i < 0 || i >= mState.getItemCount())
            {
                throw new IndexOutOfBoundsException((new StringBuilder()).append("invalid position ").append(i).append(". State ").append("item count is ").append(mState.getItemCount()).toString());
            }
            if (!mState.isPreLayout())
            {
                return i;
            } else
            {
                return mAdapterHelper.findPositionOffset(i);
            }
        }

        void dispatchViewRecycled(ViewHolder viewholder)
        {
            if (mRecyclerListener != null)
            {
                mRecyclerListener.onViewRecycled(viewholder);
            }
            if (mAdapter != null)
            {
                mAdapter.onViewRecycled(viewholder);
            }
            if (mState != null)
            {
                mViewInfoStore.removeViewHolder(viewholder);
            }
        }

        ViewHolder getChangedScrapViewForPosition(int i)
        {
            int k;
label0:
            {
                if (mChangedScrap != null)
                {
                    k = mChangedScrap.size();
                    if (k != 0)
                    {
                        break label0;
                    }
                }
                return null;
            }
            for (int j = 0; j < k; j++)
            {
                ViewHolder viewholder = (ViewHolder)mChangedScrap.get(j);
                if (!viewholder.wasReturnedFromScrap() && viewholder.getLayoutPosition() == i)
                {
                    viewholder.addFlags(32);
                    return viewholder;
                }
            }

            if (mAdapter.hasStableIds())
            {
                i = mAdapterHelper.findPositionOffset(i);
                if (i > 0 && i < mAdapter.getItemCount())
                {
                    long l = mAdapter.getItemId(i);
                    for (i = 0; i < k; i++)
                    {
                        ViewHolder viewholder1 = (ViewHolder)mChangedScrap.get(i);
                        if (!viewholder1.wasReturnedFromScrap() && viewholder1.getItemId() == l)
                        {
                            viewholder1.addFlags(32);
                            return viewholder1;
                        }
                    }

                }
            }
            return null;
        }

        RecycledViewPool getRecycledViewPool()
        {
            if (mRecyclerPool == null)
            {
                mRecyclerPool = new RecycledViewPool();
            }
            return mRecyclerPool;
        }

        int getScrapCount()
        {
            return mAttachedScrap.size();
        }

        public List getScrapList()
        {
            return mUnmodifiableAttachedScrap;
        }

        ViewHolder getScrapOrCachedViewForId(long l, int i, boolean flag)
        {
            int j = mAttachedScrap.size() - 1;
_L5:
            if (j < 0) goto _L2; else goto _L1
_L1:
            ViewHolder viewholder1;
            viewholder1 = (ViewHolder)mAttachedScrap.get(j);
            if (viewholder1.getItemId() != l || viewholder1.wasReturnedFromScrap())
            {
                continue; /* Loop/switch isn't completed */
            }
            if (i != viewholder1.getItemViewType()) goto _L4; else goto _L3
_L3:
            ViewHolder viewholder;
            viewholder1.addFlags(32);
            viewholder = viewholder1;
            if (viewholder1.isRemoved())
            {
                viewholder = viewholder1;
                if (!mState.isPreLayout())
                {
                    viewholder1.setFlags(2, 14);
                    viewholder = viewholder1;
                }
            }
_L8:
            return viewholder;
_L4:
            if (!flag)
            {
                mAttachedScrap.remove(j);
                removeDetachedView(viewholder1.itemView, false);
                quickRecycleScrapView(viewholder1.itemView);
            }
            j--;
              goto _L5
_L2:
            j = mCachedViews.size() - 1;
_L9:
            if (j < 0) goto _L7; else goto _L6
_L6:
label0:
            {
                ViewHolder viewholder2 = (ViewHolder)mCachedViews.get(j);
                if (viewholder2.getItemId() != l)
                {
                    continue; /* Loop/switch isn't completed */
                }
                if (i != viewholder2.getItemViewType())
                {
                    break label0;
                }
                viewholder = viewholder2;
                if (!flag)
                {
                    mCachedViews.remove(j);
                    return viewholder2;
                }
            }
              goto _L8
            if (!flag)
            {
                recycleCachedViewAt(j);
                return null;
            }
            j--;
              goto _L9
_L7:
            return null;
              goto _L8
        }

        ViewHolder getScrapOrHiddenOrCachedHolderForPosition(int i, boolean flag)
        {
            int j;
            int l;
            l = mAttachedScrap.size();
            j = 0;
_L8:
            if (j >= l) goto _L2; else goto _L1
_L1:
            Object obj = (ViewHolder)mAttachedScrap.get(j);
            if (((ViewHolder) (obj)).wasReturnedFromScrap() || ((ViewHolder) (obj)).getLayoutPosition() != i || ((ViewHolder) (obj)).isInvalid() || !mState.mInPreLayout && ((ViewHolder) (obj)).isRemoved()) goto _L4; else goto _L3
_L3:
            ((ViewHolder) (obj)).addFlags(32);
_L6:
            return ((ViewHolder) (obj));
_L4:
            j++;
            continue; /* Loop/switch isn't completed */
_L2:
            if (!flag)
            {
                obj = mChildHelper.findHiddenNonRemovedView(i);
                if (obj != null)
                {
                    ViewHolder viewholder = RecyclerView.getChildViewHolderInt(((View) (obj)));
                    mChildHelper.unhide(((View) (obj)));
                    i = mChildHelper.indexOfChild(((View) (obj)));
                    if (i == -1)
                    {
                        throw new IllegalStateException((new StringBuilder()).append("layout index should not be -1 after unhiding a view:").append(viewholder).toString());
                    } else
                    {
                        mChildHelper.detachViewFromParent(i);
                        scrapView(((View) (obj)));
                        viewholder.addFlags(8224);
                        return viewholder;
                    }
                }
            }
            int i1 = mCachedViews.size();
            int k = 0;
label0:
            do
            {
label1:
                {
label2:
                    {
                        if (k >= i1)
                        {
                            break label1;
                        }
                        ViewHolder viewholder1 = (ViewHolder)mCachedViews.get(k);
                        if (viewholder1.isInvalid() || viewholder1.getLayoutPosition() != i)
                        {
                            break label2;
                        }
                        obj = viewholder1;
                        if (!flag)
                        {
                            mCachedViews.remove(k);
                            return viewholder1;
                        }
                    }
                    if (true)
                    {
                        break label0;
                    }
                    k++;
                }
            } while (true);
            if (true) goto _L6; else goto _L5
_L5:
            return null;
            if (true) goto _L8; else goto _L7
_L7:
        }

        View getScrapViewAt(int i)
        {
            return ((ViewHolder)mAttachedScrap.get(i)).itemView;
        }

        public View getViewForPosition(int i)
        {
            return getViewForPosition(i, false);
        }

        View getViewForPosition(int i, boolean flag)
        {
            return tryGetViewHolderForPositionByDeadline(i, flag, 0x7fffffffffffffffL).itemView;
        }

        void markItemDecorInsetsDirty()
        {
            int j = mCachedViews.size();
            for (int i = 0; i < j; i++)
            {
                LayoutParams layoutparams = (LayoutParams)((ViewHolder)mCachedViews.get(i)).itemView.getLayoutParams();
                if (layoutparams != null)
                {
                    layoutparams.mInsetsDirty = true;
                }
            }

        }

        void markKnownViewsInvalid()
        {
            if (mAdapter != null && mAdapter.hasStableIds())
            {
                int j = mCachedViews.size();
                for (int i = 0; i < j; i++)
                {
                    ViewHolder viewholder = (ViewHolder)mCachedViews.get(i);
                    if (viewholder != null)
                    {
                        viewholder.addFlags(6);
                        viewholder.addChangePayload(null);
                    }
                }

            } else
            {
                recycleAndClearCachedViews();
            }
        }

        void offsetPositionRecordsForInsert(int i, int j)
        {
            int l = mCachedViews.size();
            for (int k = 0; k < l; k++)
            {
                ViewHolder viewholder = (ViewHolder)mCachedViews.get(k);
                if (viewholder != null && viewholder.mPosition >= i)
                {
                    viewholder.offsetPosition(j, true);
                }
            }

        }

        void offsetPositionRecordsForMove(int i, int j)
        {
            int k;
            byte byte0;
            int l;
            int i1;
            int j1;
            if (i < j)
            {
                l = i;
                k = j;
                byte0 = -1;
            } else
            {
                l = j;
                k = i;
                byte0 = 1;
            }
            j1 = mCachedViews.size();
            i1 = 0;
            while (i1 < j1) 
            {
                ViewHolder viewholder = (ViewHolder)mCachedViews.get(i1);
                if (viewholder != null && viewholder.mPosition >= l && viewholder.mPosition <= k)
                {
                    if (viewholder.mPosition == i)
                    {
                        viewholder.offsetPosition(j - i, false);
                    } else
                    {
                        viewholder.offsetPosition(byte0, false);
                    }
                }
                i1++;
            }
        }

        void offsetPositionRecordsForRemove(int i, int j, boolean flag)
        {
            int k = mCachedViews.size() - 1;
            while (k >= 0) 
            {
                ViewHolder viewholder = (ViewHolder)mCachedViews.get(k);
                if (viewholder != null)
                {
                    if (viewholder.mPosition >= i + j)
                    {
                        viewholder.offsetPosition(-j, flag);
                    } else
                    if (viewholder.mPosition >= i)
                    {
                        viewholder.addFlags(8);
                        recycleCachedViewAt(k);
                    }
                }
                k--;
            }
        }

        void onAdapterChanged(Adapter adapter, Adapter adapter1, boolean flag)
        {
            clear();
            getRecycledViewPool().onAdapterChanged(adapter, adapter1, flag);
        }

        void quickRecycleScrapView(View view)
        {
            view = RecyclerView.getChildViewHolderInt(view);
            view.mScrapContainer = null;
            view.mInChangeScrap = false;
            view.clearReturnedFromScrapFlag();
            recycleViewHolderInternal(view);
        }

        void recycleAndClearCachedViews()
        {
            for (int i = mCachedViews.size() - 1; i >= 0; i--)
            {
                recycleCachedViewAt(i);
            }

            mCachedViews.clear();
            if (RecyclerView.ALLOW_THREAD_GAP_WORK)
            {
                mPrefetchRegistry.clearPrefetchPositions();
            }
        }

        void recycleCachedViewAt(int i)
        {
            addViewHolderToRecycledViewPool((ViewHolder)mCachedViews.get(i), true);
            mCachedViews.remove(i);
        }

        public void recycleView(View view)
        {
            ViewHolder viewholder;
            viewholder = RecyclerView.getChildViewHolderInt(view);
            if (viewholder.isTmpDetached())
            {
                removeDetachedView(view, false);
            }
            if (!viewholder.isScrap()) goto _L2; else goto _L1
_L1:
            viewholder.unScrap();
_L4:
            recycleViewHolderInternal(viewholder);
            return;
_L2:
            if (viewholder.wasReturnedFromScrap())
            {
                viewholder.clearReturnedFromScrapFlag();
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        void recycleViewHolderInternal(ViewHolder viewholder)
        {
            int i;
            boolean flag2 = false;
            if (viewholder.isScrap() || viewholder.itemView.getParent() != null)
            {
                StringBuilder stringbuilder = (new StringBuilder()).append("Scrapped or attached views may not be recycled. isScrap:").append(viewholder.isScrap()).append(" isAttached:");
                if (viewholder.itemView.getParent() != null)
                {
                    flag2 = true;
                }
                throw new IllegalArgumentException(stringbuilder.append(flag2).toString());
            }
            if (viewholder.isTmpDetached())
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Tmp detached view should be removed from RecyclerView before it can be recycled: ").append(viewholder).toString());
            }
            if (viewholder.shouldIgnore())
            {
                throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle.");
            }
            flag2 = viewholder.doesTransientStatePreventRecycling();
            int j;
            int k;
            boolean flag;
            boolean flag1;
            if (mAdapter != null && flag2 && mAdapter.onFailedToRecycleView(viewholder))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            j = 0;
            flag1 = false;
            flag = false;
            if (i != 0) goto _L2; else goto _L1
_L1:
            k = ((flag) ? 1 : 0);
            if (!viewholder.isRecyclable()) goto _L3; else goto _L2
_L2:
            i = ((flag1) ? 1 : 0);
            if (mViewCacheMax <= 0) goto _L5; else goto _L4
_L4:
            i = ((flag1) ? 1 : 0);
            if (viewholder.hasAnyOfTheFlags(526)) goto _L5; else goto _L6
_L6:
            j = mCachedViews.size();
            i = j;
            if (j >= mViewCacheMax)
            {
                i = j;
                if (j > 0)
                {
                    recycleCachedViewAt(0);
                    i = j - 1;
                }
            }
            j = i;
            k = j;
            if (!RecyclerView.ALLOW_THREAD_GAP_WORK) goto _L8; else goto _L7
_L7:
            k = j;
            if (i <= 0) goto _L8; else goto _L9
_L9:
            k = j;
            if (mPrefetchRegistry.lastPrefetchIncludedPosition(viewholder.mPosition)) goto _L8; else goto _L10
_L10:
            i--;
_L15:
            if (i < 0) goto _L12; else goto _L11
_L11:
            j = ((ViewHolder)mCachedViews.get(i)).mPosition;
            if (mPrefetchRegistry.lastPrefetchIncludedPosition(j)) goto _L13; else goto _L12
_L12:
            k = i + 1;
_L8:
            mCachedViews.add(k, viewholder);
            i = 1;
_L5:
            j = i;
            k = ((flag) ? 1 : 0);
            if (i == 0)
            {
                addViewHolderToRecycledViewPool(viewholder, true);
                k = 1;
                j = i;
            }
_L3:
            mViewInfoStore.removeViewHolder(viewholder);
            if (j == 0 && k == 0 && flag2)
            {
                viewholder.mOwnerRecyclerView = null;
            }
            return;
_L13:
            i--;
            if (true) goto _L15; else goto _L14
_L14:
        }

        void recycleViewInternal(View view)
        {
            recycleViewHolderInternal(RecyclerView.getChildViewHolderInt(view));
        }

        void scrapView(View view)
        {
            view = RecyclerView.getChildViewHolderInt(view);
            if (view.hasAnyOfTheFlags(12) || !view.isUpdated() || canReuseUpdatedViewHolder(view))
            {
                if (view.isInvalid() && !view.isRemoved() && !mAdapter.hasStableIds())
                {
                    throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.");
                } else
                {
                    view.setScrapContainer(this, false);
                    mAttachedScrap.add(view);
                    return;
                }
            }
            if (mChangedScrap == null)
            {
                mChangedScrap = new ArrayList();
            }
            view.setScrapContainer(this, true);
            mChangedScrap.add(view);
        }

        void setAdapterPositionsAsUnknown()
        {
            int j = mCachedViews.size();
            for (int i = 0; i < j; i++)
            {
                ViewHolder viewholder = (ViewHolder)mCachedViews.get(i);
                if (viewholder != null)
                {
                    viewholder.addFlags(512);
                }
            }

        }

        void setRecycledViewPool(RecycledViewPool recycledviewpool)
        {
            if (mRecyclerPool != null)
            {
                mRecyclerPool.detach();
            }
            mRecyclerPool = recycledviewpool;
            if (recycledviewpool != null)
            {
                mRecyclerPool.attach(getAdapter());
            }
        }

        void setViewCacheExtension(ViewCacheExtension viewcacheextension)
        {
            mViewCacheExtension = viewcacheextension;
        }

        public void setViewCacheSize(int i)
        {
            mRequestedCacheMax = i;
            updateViewCacheSize();
        }

        ViewHolder tryGetViewHolderForPositionByDeadline(int i, boolean flag, long l)
        {
            if (i < 0 || i >= mState.getItemCount())
            {
                throw new IndexOutOfBoundsException((new StringBuilder()).append("Invalid item position ").append(i).append("(").append(i).append("). Item count:").append(mState.getItemCount()).toString());
            }
            boolean flag2 = false;
            ViewHolder viewholder = null;
            Object obj;
            boolean flag1;
            if (mState.isPreLayout())
            {
                viewholder = getChangedScrapViewForPosition(i);
                if (viewholder != null)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
            }
            obj = viewholder;
            flag1 = flag2;
            if (viewholder == null)
            {
                viewholder = getScrapOrHiddenOrCachedHolderForPosition(i, flag);
                obj = viewholder;
                flag1 = flag2;
                if (viewholder != null)
                {
                    if (!validateViewHolderForOffsetPosition(viewholder))
                    {
                        if (!flag)
                        {
                            viewholder.addFlags(4);
                            if (viewholder.isScrap())
                            {
                                removeDetachedView(viewholder.itemView, false);
                                viewholder.unScrap();
                            } else
                            if (viewholder.wasReturnedFromScrap())
                            {
                                viewholder.clearReturnedFromScrapFlag();
                            }
                            recycleViewHolderInternal(viewholder);
                        }
                        obj = null;
                        flag1 = flag2;
                    } else
                    {
                        flag1 = true;
                        obj = viewholder;
                    }
                }
            }
            viewholder = ((ViewHolder) (obj));
            flag2 = flag1;
            if (obj == null)
            {
                int i1 = mAdapterHelper.findPositionOffset(i);
                if (i1 < 0 || i1 >= mAdapter.getItemCount())
                {
                    throw new IndexOutOfBoundsException((new StringBuilder()).append("Inconsistency detected. Invalid item position ").append(i).append("(offset:").append(i1).append(").").append("state:").append(mState.getItemCount()).toString());
                }
                int k = mAdapter.getItemViewType(i1);
                viewholder = ((ViewHolder) (obj));
                flag2 = flag1;
                if (mAdapter.hasStableIds())
                {
                    obj = getScrapOrCachedViewForId(mAdapter.getItemId(i1), k, flag);
                    viewholder = ((ViewHolder) (obj));
                    flag2 = flag1;
                    if (obj != null)
                    {
                        obj.mPosition = i1;
                        flag2 = true;
                        viewholder = ((ViewHolder) (obj));
                    }
                }
                obj = viewholder;
                if (viewholder == null)
                {
                    obj = viewholder;
                    if (mViewCacheExtension != null)
                    {
                        View view = mViewCacheExtension.getViewForPositionAndType(this, i, k);
                        obj = viewholder;
                        if (view != null)
                        {
                            viewholder = getChildViewHolder(view);
                            if (viewholder == null)
                            {
                                throw new IllegalArgumentException("getViewForPositionAndType returned a view which does not have a ViewHolder");
                            }
                            obj = viewholder;
                            if (viewholder.shouldIgnore())
                            {
                                throw new IllegalArgumentException("getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view.");
                            }
                        }
                    }
                }
                viewholder = ((ViewHolder) (obj));
                if (obj == null)
                {
                    obj = getRecycledViewPool().getRecycledView(k);
                    viewholder = ((ViewHolder) (obj));
                    if (obj != null)
                    {
                        ((ViewHolder) (obj)).resetInternal();
                        viewholder = ((ViewHolder) (obj));
                        if (RecyclerView.FORCE_INVALIDATE_DISPLAY_LIST)
                        {
                            invalidateDisplayListInt(((ViewHolder) (obj)));
                            viewholder = ((ViewHolder) (obj));
                        }
                    }
                }
                if (viewholder == null)
                {
                    long l1 = getNanoTime();
                    if (l != 0x7fffffffffffffffL && !mRecyclerPool.willCreateInTime(k, l1, l))
                    {
                        return null;
                    }
                    viewholder = mAdapter.createViewHolder(RecyclerView.this, k);
                    if (RecyclerView.ALLOW_THREAD_GAP_WORK)
                    {
                        obj = RecyclerView.findNestedRecyclerView(viewholder.itemView);
                        if (obj != null)
                        {
                            viewholder.mNestedRecyclerView = new WeakReference(obj);
                        }
                    }
                    long l2 = getNanoTime();
                    mRecyclerPool.factorInCreateTime(k, l2 - l1);
                }
            }
            if (flag2 && !mState.isPreLayout() && viewholder.hasAnyOfTheFlags(8192))
            {
                viewholder.setFlags(0, 8192);
                if (mState.mRunSimpleAnimations)
                {
                    int j = ItemAnimator.buildAdapterChangeFlagsForAnimations(viewholder);
                    obj = mItemAnimator.recordPreLayoutInformation(mState, viewholder, j | 0x1000, viewholder.getUnmodifiedPayloads());
                    recordAnimationInfoIfBouncedHiddenView(viewholder, ((ItemAnimator.ItemHolderInfo) (obj)));
                }
            }
            flag = false;
            if (mState.isPreLayout() && viewholder.isBound())
            {
                viewholder.mPreLayoutPosition = i;
            } else
            if (!viewholder.isBound() || viewholder.needsUpdate() || viewholder.isInvalid())
            {
                flag = tryBindViewHolderByDeadline(viewholder, mAdapterHelper.findPositionOffset(i), i, l);
            }
            obj = viewholder.itemView.getLayoutParams();
            if (obj == null)
            {
                obj = (LayoutParams)generateDefaultLayoutParams();
                viewholder.itemView.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
            } else
            if (!checkLayoutParams(((android.view.ViewGroup.LayoutParams) (obj))))
            {
                obj = (LayoutParams)generateLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
                viewholder.itemView.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
            } else
            {
                obj = (LayoutParams)obj;
            }
            obj.mViewHolder = viewholder;
            if (flag2 && flag)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            obj.mPendingInvalidate = flag;
            return viewholder;
        }

        void unscrapView(ViewHolder viewholder)
        {
            if (viewholder.mInChangeScrap)
            {
                mChangedScrap.remove(viewholder);
            } else
            {
                mAttachedScrap.remove(viewholder);
            }
            viewholder.mScrapContainer = null;
            viewholder.mInChangeScrap = false;
            viewholder.clearReturnedFromScrapFlag();
        }

        void updateViewCacheSize()
        {
            int i;
            if (mLayout != null)
            {
                i = mLayout.mPrefetchMaxCountObserved;
            } else
            {
                i = 0;
            }
            mViewCacheMax = mRequestedCacheMax + i;
            for (i = mCachedViews.size() - 1; i >= 0 && mCachedViews.size() > mViewCacheMax; i--)
            {
                recycleCachedViewAt(i);
            }

        }

        boolean validateViewHolderForOffsetPosition(ViewHolder viewholder)
        {
            boolean flag1 = true;
            boolean flag;
            if (viewholder.isRemoved())
            {
                flag = mState.isPreLayout();
            } else
            {
                if (viewholder.mPosition < 0 || viewholder.mPosition >= mAdapter.getItemCount())
                {
                    throw new IndexOutOfBoundsException((new StringBuilder()).append("Inconsistency detected. Invalid view holder adapter position").append(viewholder).toString());
                }
                if (!mState.isPreLayout() && mAdapter.getItemViewType(viewholder.mPosition) != viewholder.getItemViewType())
                {
                    return false;
                }
                flag = flag1;
                if (mAdapter.hasStableIds())
                {
                    flag = flag1;
                    if (viewholder.getItemId() != mAdapter.getItemId(viewholder.mPosition))
                    {
                        return false;
                    }
                }
            }
            return flag;
        }

        void viewRangeUpdate(int i, int j)
        {
            int k = mCachedViews.size() - 1;
            while (k >= 0) 
            {
                ViewHolder viewholder = (ViewHolder)mCachedViews.get(k);
                if (viewholder != null)
                {
                    int l = viewholder.getLayoutPosition();
                    if (l >= i && l < i + j)
                    {
                        viewholder.addFlags(2);
                        recycleCachedViewAt(k);
                    }
                }
                k--;
            }
        }

        public Recycler()
        {
            this$0 = RecyclerView.this;
            super();
            mChangedScrap = null;
            mUnmodifiableAttachedScrap = Collections.unmodifiableList(mAttachedScrap);
            mRequestedCacheMax = 2;
            mViewCacheMax = 2;
        }
    }

    public static interface RecyclerListener
    {

        public abstract void onViewRecycled(ViewHolder viewholder);
    }

    private class RecyclerViewDataObserver extends AdapterDataObserver
    {

        final RecyclerView this$0;

        public void onChanged()
        {
            assertNotInLayoutOrScroll(null);
            mState.mStructureChanged = true;
            setDataSetChangedAfterLayout();
            if (!mAdapterHelper.hasPendingUpdates())
            {
                requestLayout();
            }
        }

        public void onItemRangeChanged(int i, int j, Object obj)
        {
            assertNotInLayoutOrScroll(null);
            if (mAdapterHelper.onItemRangeChanged(i, j, obj))
            {
                triggerUpdateProcessor();
            }
        }

        public void onItemRangeInserted(int i, int j)
        {
            assertNotInLayoutOrScroll(null);
            if (mAdapterHelper.onItemRangeInserted(i, j))
            {
                triggerUpdateProcessor();
            }
        }

        public void onItemRangeMoved(int i, int j, int k)
        {
            assertNotInLayoutOrScroll(null);
            if (mAdapterHelper.onItemRangeMoved(i, j, k))
            {
                triggerUpdateProcessor();
            }
        }

        public void onItemRangeRemoved(int i, int j)
        {
            assertNotInLayoutOrScroll(null);
            if (mAdapterHelper.onItemRangeRemoved(i, j))
            {
                triggerUpdateProcessor();
            }
        }

        void triggerUpdateProcessor()
        {
            if (RecyclerView.POST_UPDATES_ON_ANIMATION && mHasFixedSize && mIsAttached)
            {
                ViewCompat.postOnAnimation(RecyclerView.this, mUpdateChildViewsRunnable);
                return;
            } else
            {
                mAdapterUpdateDuringMeasure = true;
                requestLayout();
                return;
            }
        }

        RecyclerViewDataObserver()
        {
            this$0 = RecyclerView.this;
            super();
        }
    }

    public static class SavedState extends AbsSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks() {

            public SavedState createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return new SavedState(parcel, classloader);
            }

            public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return createFromParcel(parcel, classloader);
            }

            public SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        });
        Parcelable mLayoutState;

        void copyFrom(SavedState savedstate)
        {
            mLayoutState = savedstate.mLayoutState;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(mLayoutState, 0);
        }


        SavedState(Parcel parcel, ClassLoader classloader)
        {
            super(parcel, classloader);
            if (classloader == null)
            {
                classloader = android/support/v7/widget/RecyclerView$LayoutManager.getClassLoader();
            }
            mLayoutState = parcel.readParcelable(classloader);
        }

        SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }

    public static class SimpleOnItemTouchListener
        implements OnItemTouchListener
    {

        public boolean onInterceptTouchEvent(RecyclerView recyclerview, MotionEvent motionevent)
        {
            return false;
        }

        public void onRequestDisallowInterceptTouchEvent(boolean flag)
        {
        }

        public void onTouchEvent(RecyclerView recyclerview, MotionEvent motionevent)
        {
        }

        public SimpleOnItemTouchListener()
        {
        }
    }

    public static abstract class SmoothScroller
    {

        private LayoutManager mLayoutManager;
        private boolean mPendingInitialRun;
        private RecyclerView mRecyclerView;
        private final Action mRecyclingAction = new Action(0, 0);
        private boolean mRunning;
        private int mTargetPosition;
        private View mTargetView;

        private void onAnimation(int i, int j)
        {
label0:
            {
                RecyclerView recyclerview = mRecyclerView;
                if (!mRunning || mTargetPosition == -1 || recyclerview == null)
                {
                    stop();
                }
                mPendingInitialRun = false;
                if (mTargetView != null)
                {
                    if (getChildPosition(mTargetView) == mTargetPosition)
                    {
                        onTargetFound(mTargetView, recyclerview.mState, mRecyclingAction);
                        mRecyclingAction.runIfNecessary(recyclerview);
                        stop();
                    } else
                    {
                        Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                        mTargetView = null;
                    }
                }
                if (mRunning)
                {
                    onSeekTargetStep(i, j, recyclerview.mState, mRecyclingAction);
                    boolean flag = mRecyclingAction.hasJumpTarget();
                    mRecyclingAction.runIfNecessary(recyclerview);
                    if (flag)
                    {
                        if (!mRunning)
                        {
                            break label0;
                        }
                        mPendingInitialRun = true;
                        recyclerview.mViewFlinger.postOnAnimation();
                    }
                }
                return;
            }
            stop();
        }

        public View findViewByPosition(int i)
        {
            return mRecyclerView.mLayout.findViewByPosition(i);
        }

        public int getChildCount()
        {
            return mRecyclerView.mLayout.getChildCount();
        }

        public int getChildPosition(View view)
        {
            return mRecyclerView.getChildLayoutPosition(view);
        }

        public LayoutManager getLayoutManager()
        {
            return mLayoutManager;
        }

        public int getTargetPosition()
        {
            return mTargetPosition;
        }

        public void instantScrollToPosition(int i)
        {
            mRecyclerView.scrollToPosition(i);
        }

        public boolean isPendingInitialRun()
        {
            return mPendingInitialRun;
        }

        public boolean isRunning()
        {
            return mRunning;
        }

        protected void normalize(PointF pointf)
        {
            double d = Math.sqrt(pointf.x * pointf.x + pointf.y * pointf.y);
            pointf.x = (float)((double)pointf.x / d);
            pointf.y = (float)((double)pointf.y / d);
        }

        protected void onChildAttachedToWindow(View view)
        {
            if (getChildPosition(view) == getTargetPosition())
            {
                mTargetView = view;
            }
        }

        protected abstract void onSeekTargetStep(int i, int j, State state, Action action);

        protected abstract void onStart();

        protected abstract void onStop();

        protected abstract void onTargetFound(View view, State state, Action action);

        public void setTargetPosition(int i)
        {
            mTargetPosition = i;
        }

        void start(RecyclerView recyclerview, LayoutManager layoutmanager)
        {
            mRecyclerView = recyclerview;
            mLayoutManager = layoutmanager;
            if (mTargetPosition == -1)
            {
                throw new IllegalArgumentException("Invalid target position");
            } else
            {
                mRecyclerView.mState.mTargetPosition = mTargetPosition;
                mRunning = true;
                mPendingInitialRun = true;
                mTargetView = findViewByPosition(getTargetPosition());
                onStart();
                mRecyclerView.mViewFlinger.postOnAnimation();
                return;
            }
        }

        protected final void stop()
        {
            if (!mRunning)
            {
                return;
            } else
            {
                onStop();
                mRecyclerView.mState.mTargetPosition = -1;
                mTargetView = null;
                mTargetPosition = -1;
                mPendingInitialRun = false;
                mRunning = false;
                mLayoutManager.onSmoothScrollerStopped(this);
                mLayoutManager = null;
                mRecyclerView = null;
                return;
            }
        }


        public SmoothScroller()
        {
            mTargetPosition = -1;
        }
    }

    public static class SmoothScroller.Action
    {

        public static final int UNDEFINED_DURATION = 0x80000000;
        private boolean mChanged;
        private int mConsecutiveUpdates;
        private int mDuration;
        private int mDx;
        private int mDy;
        private Interpolator mInterpolator;
        private int mJumpToPosition;

        private void validate()
        {
            if (mInterpolator != null && mDuration < 1)
            {
                throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
            }
            if (mDuration < 1)
            {
                throw new IllegalStateException("Scroll duration must be a positive number");
            } else
            {
                return;
            }
        }

        public int getDuration()
        {
            return mDuration;
        }

        public int getDx()
        {
            return mDx;
        }

        public int getDy()
        {
            return mDy;
        }

        public Interpolator getInterpolator()
        {
            return mInterpolator;
        }

        boolean hasJumpTarget()
        {
            return mJumpToPosition >= 0;
        }

        public void jumpTo(int i)
        {
            mJumpToPosition = i;
        }

        void runIfNecessary(RecyclerView recyclerview)
        {
            if (mJumpToPosition >= 0)
            {
                int i = mJumpToPosition;
                mJumpToPosition = -1;
                recyclerview.jumpToPositionForSmoothScroller(i);
                mChanged = false;
                return;
            }
            if (mChanged)
            {
                validate();
                if (mInterpolator == null)
                {
                    if (mDuration == 0x80000000)
                    {
                        recyclerview.mViewFlinger.smoothScrollBy(mDx, mDy);
                    } else
                    {
                        recyclerview.mViewFlinger.smoothScrollBy(mDx, mDy, mDuration);
                    }
                } else
                {
                    recyclerview.mViewFlinger.smoothScrollBy(mDx, mDy, mDuration, mInterpolator);
                }
                mConsecutiveUpdates = mConsecutiveUpdates + 1;
                if (mConsecutiveUpdates > 10)
                {
                    Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                }
                mChanged = false;
                return;
            } else
            {
                mConsecutiveUpdates = 0;
                return;
            }
        }

        public void setDuration(int i)
        {
            mChanged = true;
            mDuration = i;
        }

        public void setDx(int i)
        {
            mChanged = true;
            mDx = i;
        }

        public void setDy(int i)
        {
            mChanged = true;
            mDy = i;
        }

        public void setInterpolator(Interpolator interpolator)
        {
            mChanged = true;
            mInterpolator = interpolator;
        }

        public void update(int i, int j, int k, Interpolator interpolator)
        {
            mDx = i;
            mDy = j;
            mDuration = k;
            mInterpolator = interpolator;
            mChanged = true;
        }

        public SmoothScroller.Action(int i, int j)
        {
            this(i, j, 0x80000000, null);
        }

        public SmoothScroller.Action(int i, int j, int k)
        {
            this(i, j, k, null);
        }

        public SmoothScroller.Action(int i, int j, int k, Interpolator interpolator)
        {
            mJumpToPosition = -1;
            mChanged = false;
            mConsecutiveUpdates = 0;
            mDx = i;
            mDy = j;
            mDuration = k;
            mInterpolator = interpolator;
        }
    }

    public static interface SmoothScroller.ScrollVectorProvider
    {

        public abstract PointF computeScrollVectorForPosition(int i);
    }

    public static class State
    {

        static final int STEP_ANIMATIONS = 4;
        static final int STEP_LAYOUT = 2;
        static final int STEP_START = 1;
        private SparseArray mData;
        int mDeletedInvisibleItemCountSincePreviousLayout;
        long mFocusedItemId;
        int mFocusedItemPosition;
        int mFocusedSubChildId;
        boolean mInPreLayout;
        boolean mIsMeasuring;
        int mItemCount;
        int mLayoutStep;
        int mPreviousLayoutItemCount;
        boolean mRunPredictiveAnimations;
        boolean mRunSimpleAnimations;
        boolean mStructureChanged;
        private int mTargetPosition;
        boolean mTrackOldChangeHolders;

        void assertLayoutStep(int i)
        {
            if ((mLayoutStep & i) == 0)
            {
                throw new IllegalStateException((new StringBuilder()).append("Layout state should be one of ").append(Integer.toBinaryString(i)).append(" but it is ").append(Integer.toBinaryString(mLayoutStep)).toString());
            } else
            {
                return;
            }
        }

        public boolean didStructureChange()
        {
            return mStructureChanged;
        }

        public Object get(int i)
        {
            if (mData == null)
            {
                return null;
            } else
            {
                return mData.get(i);
            }
        }

        public int getItemCount()
        {
            if (mInPreLayout)
            {
                return mPreviousLayoutItemCount - mDeletedInvisibleItemCountSincePreviousLayout;
            } else
            {
                return mItemCount;
            }
        }

        public int getTargetScrollPosition()
        {
            return mTargetPosition;
        }

        public boolean hasTargetScrollPosition()
        {
            return mTargetPosition != -1;
        }

        public boolean isMeasuring()
        {
            return mIsMeasuring;
        }

        public boolean isPreLayout()
        {
            return mInPreLayout;
        }

        void prepareForNestedPrefetch(Adapter adapter)
        {
            mLayoutStep = 1;
            mItemCount = adapter.getItemCount();
            mStructureChanged = false;
            mInPreLayout = false;
            mTrackOldChangeHolders = false;
            mIsMeasuring = false;
        }

        public void put(int i, Object obj)
        {
            if (mData == null)
            {
                mData = new SparseArray();
            }
            mData.put(i, obj);
        }

        public void remove(int i)
        {
            if (mData == null)
            {
                return;
            } else
            {
                mData.remove(i);
                return;
            }
        }

        State reset()
        {
            mTargetPosition = -1;
            if (mData != null)
            {
                mData.clear();
            }
            mItemCount = 0;
            mStructureChanged = false;
            mIsMeasuring = false;
            return this;
        }

        public String toString()
        {
            return (new StringBuilder()).append("State{mTargetPosition=").append(mTargetPosition).append(", mData=").append(mData).append(", mItemCount=").append(mItemCount).append(", mPreviousLayoutItemCount=").append(mPreviousLayoutItemCount).append(", mDeletedInvisibleItemCountSincePreviousLayout=").append(mDeletedInvisibleItemCountSincePreviousLayout).append(", mStructureChanged=").append(mStructureChanged).append(", mInPreLayout=").append(mInPreLayout).append(", mRunSimpleAnimations=").append(mRunSimpleAnimations).append(", mRunPredictiveAnimations=").append(mRunPredictiveAnimations).append('}').toString();
        }

        public boolean willRunPredictiveAnimations()
        {
            return mRunPredictiveAnimations;
        }

        public boolean willRunSimpleAnimations()
        {
            return mRunSimpleAnimations;
        }


/*
        static int access$1102(State state, int i)
        {
            state.mTargetPosition = i;
            return i;
        }

*/

        public State()
        {
            mTargetPosition = -1;
            mPreviousLayoutItemCount = 0;
            mDeletedInvisibleItemCountSincePreviousLayout = 0;
            mLayoutStep = 1;
            mItemCount = 0;
            mStructureChanged = false;
            mInPreLayout = false;
            mTrackOldChangeHolders = false;
            mIsMeasuring = false;
            mRunSimpleAnimations = false;
            mRunPredictiveAnimations = false;
        }
    }

    public static abstract class ViewCacheExtension
    {

        public abstract View getViewForPositionAndType(Recycler recycler, int i, int j);

        public ViewCacheExtension()
        {
        }
    }

    class ViewFlinger
        implements Runnable
    {

        private boolean mEatRunOnAnimationRequest;
        Interpolator mInterpolator;
        private int mLastFlingX;
        private int mLastFlingY;
        private boolean mReSchedulePostAnimationCallback;
        private OverScroller mScroller;
        final RecyclerView this$0;

        private int computeScrollDuration(int i, int j, int k, int l)
        {
            int i1 = Math.abs(i);
            int j1 = Math.abs(j);
            float f;
            float f1;
            float f2;
            boolean flag;
            if (i1 > j1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            k = (int)Math.sqrt(k * k + l * l);
            j = (int)Math.sqrt(i * i + j * j);
            if (flag)
            {
                i = getWidth();
            } else
            {
                i = getHeight();
            }
            l = i / 2;
            f2 = Math.min(1.0F, (1.0F * (float)j) / (float)i);
            f = l;
            f1 = l;
            f2 = distanceInfluenceForSnapDuration(f2);
            if (k > 0)
            {
                i = Math.round(1000F * Math.abs((f + f1 * f2) / (float)k)) * 4;
            } else
            {
                if (flag)
                {
                    j = i1;
                } else
                {
                    j = j1;
                }
                i = (int)(((float)j / (float)i + 1.0F) * 300F);
            }
            return Math.min(i, 2000);
        }

        private void disableRunOnAnimationRequests()
        {
            mReSchedulePostAnimationCallback = false;
            mEatRunOnAnimationRequest = true;
        }

        private float distanceInfluenceForSnapDuration(float f)
        {
            return (float)Math.sin((float)((double)(f - 0.5F) * 0.4712389167638204D));
        }

        private void enableRunOnAnimationRequests()
        {
            mEatRunOnAnimationRequest = false;
            if (mReSchedulePostAnimationCallback)
            {
                postOnAnimation();
            }
        }

        public void fling(int i, int j)
        {
            setScrollState(2);
            mLastFlingY = 0;
            mLastFlingX = 0;
            mScroller.fling(0, 0, i, j, 0x80000000, 0x7fffffff, 0x80000000, 0x7fffffff);
            postOnAnimation();
        }

        void postOnAnimation()
        {
            if (mEatRunOnAnimationRequest)
            {
                mReSchedulePostAnimationCallback = true;
                return;
            } else
            {
                removeCallbacks(this);
                ViewCompat.postOnAnimation(RecyclerView.this, this);
                return;
            }
        }

        public void run()
        {
            OverScroller overscroller;
            SmoothScroller smoothscroller;
            if (mLayout == null)
            {
                stop();
                return;
            }
            disableRunOnAnimationRequests();
            consumePendingUpdateOperations();
            overscroller = mScroller;
            smoothscroller = mLayout.mSmoothScroller;
            if (!overscroller.computeScrollOffset()) goto _L2; else goto _L1
_L1:
            int i2;
            int j2;
            int k2 = overscroller.getCurrX();
            int l2 = overscroller.getCurrY();
            i2 = k2 - mLastFlingX;
            j2 = l2 - mLastFlingY;
            int i1 = 0;
            int i = 0;
            int l1 = 0;
            int l = 0;
            mLastFlingX = k2;
            mLastFlingY = l2;
            int j1 = 0;
            int j = 0;
            int k1 = 0;
            int k = 0;
            if (mAdapter != null)
            {
                eatRequestLayout();
                onEnterLayoutOrScroll();
                TraceCompat.beginSection("RV Scroll");
                if (i2 != 0)
                {
                    i = mLayout.scrollHorizontallyBy(i2, mRecycler, mState);
                    j = i2 - i;
                }
                if (j2 != 0)
                {
                    l = mLayout.scrollVerticallyBy(j2, mRecycler, mState);
                    k = j2 - l;
                }
                TraceCompat.endSection();
                repositionShadowingViews();
                onExitLayoutOrScroll();
                resumeRequestLayout(false);
                i1 = i;
                j1 = j;
                k1 = k;
                l1 = l;
                if (smoothscroller != null)
                {
                    i1 = i;
                    j1 = j;
                    k1 = k;
                    l1 = l;
                    if (!smoothscroller.isPendingInitialRun())
                    {
                        i1 = i;
                        j1 = j;
                        k1 = k;
                        l1 = l;
                        if (smoothscroller.isRunning())
                        {
                            i1 = mState.getItemCount();
                            if (i1 == 0)
                            {
                                smoothscroller.stop();
                                l1 = l;
                                k1 = k;
                                j1 = j;
                                i1 = i;
                            } else
                            if (smoothscroller.getTargetPosition() >= i1)
                            {
                                smoothscroller.setTargetPosition(i1 - 1);
                                smoothscroller.onAnimation(i2 - j, j2 - k);
                                i1 = i;
                                j1 = j;
                                k1 = k;
                                l1 = l;
                            } else
                            {
                                smoothscroller.onAnimation(i2 - j, j2 - k);
                                i1 = i;
                                j1 = j;
                                k1 = k;
                                l1 = l;
                            }
                        }
                    }
                }
            }
            if (!mItemDecorations.isEmpty())
            {
                invalidate();
            }
            if (getOverScrollMode() != 2)
            {
                considerReleasingGlowsOnScroll(i2, j2);
            }
            if (j1 != 0 || k1 != 0)
            {
                k = (int)overscroller.getCurrVelocity();
                i = 0;
                if (j1 != k2)
                {
                    if (j1 < 0)
                    {
                        i = -k;
                    } else
                    if (j1 > 0)
                    {
                        i = k;
                    } else
                    {
                        i = 0;
                    }
                }
                j = 0;
                if (k1 != l2)
                {
                    if (k1 < 0)
                    {
                        j = -k;
                    } else
                    if (k1 > 0)
                    {
                        j = k;
                    } else
                    {
                        j = 0;
                    }
                }
                if (getOverScrollMode() != 2)
                {
                    absorbGlows(i, j);
                }
                if ((i != 0 || j1 == k2 || overscroller.getFinalX() == 0) && (j != 0 || k1 == l2 || overscroller.getFinalY() == 0))
                {
                    overscroller.abortAnimation();
                }
            }
            if (i1 != 0 || l1 != 0)
            {
                dispatchOnScrolled(i1, l1);
            }
            if (!awakenScrollBars())
            {
                invalidate();
            }
            if (j2 != 0 && mLayout.canScrollVertically() && l1 == j2)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i2 != 0 && mLayout.canScrollHorizontally() && i1 == i2)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (i2 == 0 && j2 == 0 || j != 0 || i != 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (!overscroller.isFinished() && i != 0) goto _L4; else goto _L3
_L3:
            setScrollState(0);
            if (RecyclerView.ALLOW_THREAD_GAP_WORK)
            {
                mPrefetchRegistry.clearPrefetchPositions();
            }
_L2:
            if (smoothscroller != null)
            {
                if (smoothscroller.isPendingInitialRun())
                {
                    smoothscroller.onAnimation(0, 0);
                }
                if (!mReSchedulePostAnimationCallback)
                {
                    smoothscroller.stop();
                }
            }
            enableRunOnAnimationRequests();
            return;
_L4:
            postOnAnimation();
            if (mGapWorker != null)
            {
                mGapWorker.postFromTraversal(RecyclerView.this, i2, j2);
            }
            if (true) goto _L2; else goto _L5
_L5:
        }

        public void smoothScrollBy(int i, int j)
        {
            smoothScrollBy(i, j, 0, 0);
        }

        public void smoothScrollBy(int i, int j, int k)
        {
            smoothScrollBy(i, j, k, RecyclerView.sQuinticInterpolator);
        }

        public void smoothScrollBy(int i, int j, int k, int l)
        {
            smoothScrollBy(i, j, computeScrollDuration(i, j, k, l));
        }

        public void smoothScrollBy(int i, int j, int k, Interpolator interpolator)
        {
            if (mInterpolator != interpolator)
            {
                mInterpolator = interpolator;
                mScroller = new OverScroller(getContext(), interpolator);
            }
            setScrollState(2);
            mLastFlingY = 0;
            mLastFlingX = 0;
            mScroller.startScroll(0, 0, i, j, k);
            postOnAnimation();
        }

        public void smoothScrollBy(int i, int j, Interpolator interpolator)
        {
            int k = computeScrollDuration(i, j, 0, 0);
            Interpolator interpolator1 = interpolator;
            if (interpolator == null)
            {
                interpolator1 = RecyclerView.sQuinticInterpolator;
            }
            smoothScrollBy(i, j, k, interpolator1);
        }

        public void stop()
        {
            removeCallbacks(this);
            mScroller.abortAnimation();
        }

        ViewFlinger()
        {
            this$0 = RecyclerView.this;
            super();
            mInterpolator = RecyclerView.sQuinticInterpolator;
            mEatRunOnAnimationRequest = false;
            mReSchedulePostAnimationCallback = false;
            mScroller = new OverScroller(getContext(), RecyclerView.sQuinticInterpolator);
        }
    }

    public static abstract class ViewHolder
    {

        static final int FLAG_ADAPTER_FULLUPDATE = 1024;
        static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
        static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
        static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
        static final int FLAG_BOUND = 1;
        static final int FLAG_IGNORE = 128;
        static final int FLAG_INVALID = 4;
        static final int FLAG_MOVED = 2048;
        static final int FLAG_NOT_RECYCLABLE = 16;
        static final int FLAG_REMOVED = 8;
        static final int FLAG_RETURNED_FROM_SCRAP = 32;
        static final int FLAG_TMP_DETACHED = 256;
        static final int FLAG_UPDATE = 2;
        private static final List FULLUPDATE_PAYLOADS;
        static final int PENDING_ACCESSIBILITY_STATE_NOT_SET = -1;
        public final View itemView;
        private int mFlags;
        private boolean mInChangeScrap;
        private int mIsRecyclableCount;
        long mItemId;
        int mItemViewType;
        WeakReference mNestedRecyclerView;
        int mOldPosition;
        RecyclerView mOwnerRecyclerView;
        List mPayloads;
        int mPendingAccessibilityState;
        int mPosition;
        int mPreLayoutPosition;
        private Recycler mScrapContainer;
        ViewHolder mShadowedHolder;
        ViewHolder mShadowingHolder;
        List mUnmodifiedPayloads;
        private int mWasImportantForAccessibilityBeforeHidden;

        private void createPayloadsIfNeeded()
        {
            if (mPayloads == null)
            {
                mPayloads = new ArrayList();
                mUnmodifiedPayloads = Collections.unmodifiableList(mPayloads);
            }
        }

        private boolean doesTransientStatePreventRecycling()
        {
            return (mFlags & 0x10) == 0 && ViewCompat.hasTransientState(itemView);
        }

        private void onEnteredHiddenState(RecyclerView recyclerview)
        {
            mWasImportantForAccessibilityBeforeHidden = ViewCompat.getImportantForAccessibility(itemView);
            recyclerview.setChildImportantForAccessibilityInternal(this, 4);
        }

        private void onLeftHiddenState(RecyclerView recyclerview)
        {
            recyclerview.setChildImportantForAccessibilityInternal(this, mWasImportantForAccessibilityBeforeHidden);
            mWasImportantForAccessibilityBeforeHidden = 0;
        }

        private boolean shouldBeKeptAsChild()
        {
            return (mFlags & 0x10) != 0;
        }

        void addChangePayload(Object obj)
        {
            if (obj == null)
            {
                addFlags(1024);
            } else
            if ((mFlags & 0x400) == 0)
            {
                createPayloadsIfNeeded();
                mPayloads.add(obj);
                return;
            }
        }

        void addFlags(int i)
        {
            mFlags = mFlags | i;
        }

        void clearOldPosition()
        {
            mOldPosition = -1;
            mPreLayoutPosition = -1;
        }

        void clearPayload()
        {
            if (mPayloads != null)
            {
                mPayloads.clear();
            }
            mFlags = mFlags & 0xfffffbff;
        }

        void clearReturnedFromScrapFlag()
        {
            mFlags = mFlags & 0xffffffdf;
        }

        void clearTmpDetachFlag()
        {
            mFlags = mFlags & 0xfffffeff;
        }

        void flagRemovedAndOffsetPosition(int i, int j, boolean flag)
        {
            addFlags(8);
            offsetPosition(j, flag);
            mPosition = i;
        }

        public final int getAdapterPosition()
        {
            if (mOwnerRecyclerView == null)
            {
                return -1;
            } else
            {
                return mOwnerRecyclerView.getAdapterPositionFor(this);
            }
        }

        public final long getItemId()
        {
            return mItemId;
        }

        public final int getItemViewType()
        {
            return mItemViewType;
        }

        public final int getLayoutPosition()
        {
            if (mPreLayoutPosition == -1)
            {
                return mPosition;
            } else
            {
                return mPreLayoutPosition;
            }
        }

        public final int getOldPosition()
        {
            return mOldPosition;
        }

        public final int getPosition()
        {
            if (mPreLayoutPosition == -1)
            {
                return mPosition;
            } else
            {
                return mPreLayoutPosition;
            }
        }

        List getUnmodifiedPayloads()
        {
            if ((mFlags & 0x400) == 0)
            {
                if (mPayloads == null || mPayloads.size() == 0)
                {
                    return FULLUPDATE_PAYLOADS;
                } else
                {
                    return mUnmodifiedPayloads;
                }
            } else
            {
                return FULLUPDATE_PAYLOADS;
            }
        }

        boolean hasAnyOfTheFlags(int i)
        {
            return (mFlags & i) != 0;
        }

        boolean isAdapterPositionUnknown()
        {
            return (mFlags & 0x200) != 0 || isInvalid();
        }

        boolean isBound()
        {
            return (mFlags & 1) != 0;
        }

        boolean isInvalid()
        {
            return (mFlags & 4) != 0;
        }

        public final boolean isRecyclable()
        {
            return (mFlags & 0x10) == 0 && !ViewCompat.hasTransientState(itemView);
        }

        boolean isRemoved()
        {
            return (mFlags & 8) != 0;
        }

        boolean isScrap()
        {
            return mScrapContainer != null;
        }

        boolean isTmpDetached()
        {
            return (mFlags & 0x100) != 0;
        }

        boolean isUpdated()
        {
            return (mFlags & 2) != 0;
        }

        boolean needsUpdate()
        {
            return (mFlags & 2) != 0;
        }

        void offsetPosition(int i, boolean flag)
        {
            if (mOldPosition == -1)
            {
                mOldPosition = mPosition;
            }
            if (mPreLayoutPosition == -1)
            {
                mPreLayoutPosition = mPosition;
            }
            if (flag)
            {
                mPreLayoutPosition = mPreLayoutPosition + i;
            }
            mPosition = mPosition + i;
            if (itemView.getLayoutParams() != null)
            {
                ((LayoutParams)itemView.getLayoutParams()).mInsetsDirty = true;
            }
        }

        void resetInternal()
        {
            mFlags = 0;
            mPosition = -1;
            mOldPosition = -1;
            mItemId = -1L;
            mPreLayoutPosition = -1;
            mIsRecyclableCount = 0;
            mShadowedHolder = null;
            mShadowingHolder = null;
            clearPayload();
            mWasImportantForAccessibilityBeforeHidden = 0;
            mPendingAccessibilityState = -1;
            RecyclerView.clearNestedRecyclerViewIfNotNested(this);
        }

        void saveOldPosition()
        {
            if (mOldPosition == -1)
            {
                mOldPosition = mPosition;
            }
        }

        void setFlags(int i, int j)
        {
            mFlags = mFlags & ~j | i & j;
        }

        public final void setIsRecyclable(boolean flag)
        {
            int i;
            if (flag)
            {
                i = mIsRecyclableCount - 1;
            } else
            {
                i = mIsRecyclableCount + 1;
            }
            mIsRecyclableCount = i;
            if (mIsRecyclableCount < 0)
            {
                mIsRecyclableCount = 0;
                Log.e("View", (new StringBuilder()).append("isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for ").append(this).toString());
            } else
            {
                if (!flag && mIsRecyclableCount == 1)
                {
                    mFlags = mFlags | 0x10;
                    return;
                }
                if (flag && mIsRecyclableCount == 0)
                {
                    mFlags = mFlags & 0xffffffef;
                    return;
                }
            }
        }

        void setScrapContainer(Recycler recycler, boolean flag)
        {
            mScrapContainer = recycler;
            mInChangeScrap = flag;
        }

        boolean shouldIgnore()
        {
            return (mFlags & 0x80) != 0;
        }

        void stopIgnoring()
        {
            mFlags = mFlags & 0xffffff7f;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder((new StringBuilder()).append("ViewHolder{").append(Integer.toHexString(hashCode())).append(" position=").append(mPosition).append(" id=").append(mItemId).append(", oldPos=").append(mOldPosition).append(", pLpos:").append(mPreLayoutPosition).toString());
            if (isScrap())
            {
                StringBuilder stringbuilder1 = stringbuilder.append(" scrap ");
                String s;
                if (mInChangeScrap)
                {
                    s = "[changeScrap]";
                } else
                {
                    s = "[attachedScrap]";
                }
                stringbuilder1.append(s);
            }
            if (isInvalid())
            {
                stringbuilder.append(" invalid");
            }
            if (!isBound())
            {
                stringbuilder.append(" unbound");
            }
            if (needsUpdate())
            {
                stringbuilder.append(" update");
            }
            if (isRemoved())
            {
                stringbuilder.append(" removed");
            }
            if (shouldIgnore())
            {
                stringbuilder.append(" ignored");
            }
            if (isTmpDetached())
            {
                stringbuilder.append(" tmpDetached");
            }
            if (!isRecyclable())
            {
                stringbuilder.append((new StringBuilder()).append(" not recyclable(").append(mIsRecyclableCount).append(")").toString());
            }
            if (isAdapterPositionUnknown())
            {
                stringbuilder.append(" undefined adapter position");
            }
            if (itemView.getParent() == null)
            {
                stringbuilder.append(" no parent");
            }
            stringbuilder.append("}");
            return stringbuilder.toString();
        }

        void unScrap()
        {
            mScrapContainer.unscrapView(this);
        }

        boolean wasReturnedFromScrap()
        {
            return (mFlags & 0x20) != 0;
        }

        static 
        {
            FULLUPDATE_PAYLOADS = Collections.EMPTY_LIST;
        }







/*
        static Recycler access$802(ViewHolder viewholder, Recycler recycler)
        {
            viewholder.mScrapContainer = recycler;
            return recycler;
        }

*/



/*
        static boolean access$902(ViewHolder viewholder, boolean flag)
        {
            viewholder.mInChangeScrap = flag;
            return flag;
        }

*/

        public ViewHolder(View view)
        {
            mPosition = -1;
            mOldPosition = -1;
            mItemId = -1L;
            mItemViewType = -1;
            mPreLayoutPosition = -1;
            mShadowedHolder = null;
            mShadowingHolder = null;
            mPayloads = null;
            mUnmodifiedPayloads = null;
            mIsRecyclableCount = 0;
            mScrapContainer = null;
            mInChangeScrap = false;
            mWasImportantForAccessibilityBeforeHidden = 0;
            mPendingAccessibilityState = -1;
            if (view == null)
            {
                throw new IllegalArgumentException("itemView may not be null");
            } else
            {
                itemView = view;
                return;
            }
        }
    }


    static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC;
    private static final boolean ALLOW_THREAD_GAP_WORK;
    private static final int CLIP_TO_PADDING_ATTR[] = {
        0x10100eb
    };
    static final boolean DEBUG = false;
    static final boolean DISPATCH_TEMP_DETACH = false;
    private static final boolean FORCE_ABS_FOCUS_SEARCH_DIRECTION;
    static final boolean FORCE_INVALIDATE_DISPLAY_LIST;
    static final long FOREVER_NS = 0x7fffffffffffffffL;
    public static final int HORIZONTAL = 0;
    private static final boolean IGNORE_DETACHED_FOCUSED_CHILD;
    private static final int INVALID_POINTER = -1;
    public static final int INVALID_TYPE = -1;
    private static final Class LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE[];
    static final int MAX_SCROLL_DURATION = 2000;
    private static final int NESTED_SCROLLING_ATTRS[] = {
        0x1010436
    };
    public static final long NO_ID = -1L;
    public static final int NO_POSITION = -1;
    static final boolean POST_UPDATES_ON_ANIMATION;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    static final String TAG = "RecyclerView";
    public static final int TOUCH_SLOP_DEFAULT = 0;
    public static final int TOUCH_SLOP_PAGING = 1;
    static final String TRACE_BIND_VIEW_TAG = "RV OnBindView";
    static final String TRACE_CREATE_VIEW_TAG = "RV CreateView";
    private static final String TRACE_HANDLE_ADAPTER_UPDATES_TAG = "RV PartialInvalidate";
    static final String TRACE_NESTED_PREFETCH_TAG = "RV Nested Prefetch";
    private static final String TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG = "RV FullInvalidate";
    private static final String TRACE_ON_LAYOUT_TAG = "RV OnLayout";
    static final String TRACE_PREFETCH_TAG = "RV Prefetch";
    static final String TRACE_SCROLL_TAG = "RV Scroll";
    static final boolean VERBOSE_TRACING = false;
    public static final int VERTICAL = 1;
    static final Interpolator sQuinticInterpolator = new Interpolator() {

        public float getInterpolation(float f)
        {
            f--;
            return f * f * f * f * f + 1.0F;
        }

    };
    RecyclerViewAccessibilityDelegate mAccessibilityDelegate;
    private final AccessibilityManager mAccessibilityManager;
    private OnItemTouchListener mActiveOnItemTouchListener;
    Adapter mAdapter;
    AdapterHelper mAdapterHelper;
    boolean mAdapterUpdateDuringMeasure;
    private EdgeEffect mBottomGlow;
    private ChildDrawingOrderCallback mChildDrawingOrderCallback;
    ChildHelper mChildHelper;
    boolean mClipToPadding;
    boolean mDataSetHasChangedAfterLayout;
    private int mDispatchScrollCounter;
    private int mEatRequestLayout;
    private int mEatenAccessibilityChangeFlags;
    boolean mEnableFastScroller;
    boolean mFirstLayoutComplete;
    GapWorker mGapWorker;
    boolean mHasFixedSize;
    private boolean mIgnoreMotionEventTillDown;
    private int mInitialTouchX;
    private int mInitialTouchY;
    boolean mIsAttached;
    ItemAnimator mItemAnimator;
    private ItemAnimator.ItemAnimatorListener mItemAnimatorListener;
    private Runnable mItemAnimatorRunner = new Runnable() {

        final RecyclerView this$0;

        public void run()
        {
            if (mItemAnimator != null)
            {
                mItemAnimator.runPendingAnimations();
            }
            mPostedAnimatorRunner = false;
        }

            
            {
                this$0 = RecyclerView.this;
                super();
            }
    };
    final ArrayList mItemDecorations;
    boolean mItemsAddedOrRemoved;
    boolean mItemsChanged;
    private int mLastTouchX;
    private int mLastTouchY;
    LayoutManager mLayout;
    boolean mLayoutFrozen;
    private int mLayoutOrScrollCounter;
    boolean mLayoutRequestEaten;
    private EdgeEffect mLeftGlow;
    private final int mMaxFlingVelocity;
    private final int mMinFlingVelocity;
    private final int mMinMaxLayoutPositions[];
    private final int mNestedOffsets[];
    private final RecyclerViewDataObserver mObserver;
    private List mOnChildAttachStateListeners;
    private OnFlingListener mOnFlingListener;
    private final ArrayList mOnItemTouchListeners;
    final List mPendingAccessibilityImportanceChange;
    private SavedState mPendingSavedState;
    boolean mPostedAnimatorRunner;
    GapWorker.LayoutPrefetchRegistryImpl mPrefetchRegistry;
    private boolean mPreserveFocusAfterLayout;
    final Recycler mRecycler;
    RecyclerListener mRecyclerListener;
    private EdgeEffect mRightGlow;
    private final int mScrollConsumed[];
    private float mScrollFactor;
    private OnScrollListener mScrollListener;
    private List mScrollListeners;
    private final int mScrollOffset[];
    private int mScrollPointerId;
    private int mScrollState;
    private NestedScrollingChildHelper mScrollingChildHelper;
    final State mState;
    final Rect mTempRect;
    private final Rect mTempRect2;
    final RectF mTempRectF;
    private EdgeEffect mTopGlow;
    private int mTouchSlop;
    final Runnable mUpdateChildViewsRunnable;
    private VelocityTracker mVelocityTracker;
    final ViewFlinger mViewFlinger;
    private final ViewInfoStore.ProcessCallback mViewInfoProcessCallback;
    final ViewInfoStore mViewInfoStore;

    public RecyclerView(Context context)
    {
        this(context, null);
    }

    public RecyclerView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public RecyclerView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mObserver = new RecyclerViewDataObserver();
        mRecycler = new Recycler();
        mViewInfoStore = new ViewInfoStore();
        mUpdateChildViewsRunnable = new Runnable() {

            final RecyclerView this$0;

            public void run()
            {
                if (!mFirstLayoutComplete || isLayoutRequested())
                {
                    return;
                }
                if (!mIsAttached)
                {
                    requestLayout();
                    return;
                }
                if (mLayoutFrozen)
                {
                    mLayoutRequestEaten = true;
                    return;
                } else
                {
                    consumePendingUpdateOperations();
                    return;
                }
            }

            
            {
                this$0 = RecyclerView.this;
                super();
            }
        };
        mTempRect = new Rect();
        mTempRect2 = new Rect();
        mTempRectF = new RectF();
        mItemDecorations = new ArrayList();
        mOnItemTouchListeners = new ArrayList();
        mEatRequestLayout = 0;
        mDataSetHasChangedAfterLayout = false;
        mLayoutOrScrollCounter = 0;
        mDispatchScrollCounter = 0;
        mItemAnimator = new DefaultItemAnimator();
        mScrollState = 0;
        mScrollPointerId = -1;
        mScrollFactor = 1.401298E-45F;
        mPreserveFocusAfterLayout = true;
        mViewFlinger = new ViewFlinger();
        Object obj;
        boolean flag;
        if (ALLOW_THREAD_GAP_WORK)
        {
            obj = new GapWorker.LayoutPrefetchRegistryImpl();
        } else
        {
            obj = null;
        }
        mPrefetchRegistry = ((GapWorker.LayoutPrefetchRegistryImpl) (obj));
        mState = new State();
        mItemsAddedOrRemoved = false;
        mItemsChanged = false;
        mItemAnimatorListener = new ItemAnimatorRestoreListener();
        mPostedAnimatorRunner = false;
        mMinMaxLayoutPositions = new int[2];
        mScrollOffset = new int[2];
        mScrollConsumed = new int[2];
        mNestedOffsets = new int[2];
        mPendingAccessibilityImportanceChange = new ArrayList();
        mViewInfoProcessCallback = new ViewInfoStore.ProcessCallback() {

            final RecyclerView this$0;

            public void processAppeared(ViewHolder viewholder, ItemAnimator.ItemHolderInfo itemholderinfo, ItemAnimator.ItemHolderInfo itemholderinfo1)
            {
                animateAppearance(viewholder, itemholderinfo, itemholderinfo1);
            }

            public void processDisappeared(ViewHolder viewholder, ItemAnimator.ItemHolderInfo itemholderinfo, ItemAnimator.ItemHolderInfo itemholderinfo1)
            {
                mRecycler.unscrapView(viewholder);
                animateDisappearance(viewholder, itemholderinfo, itemholderinfo1);
            }

            public void processPersistent(ViewHolder viewholder, ItemAnimator.ItemHolderInfo itemholderinfo, ItemAnimator.ItemHolderInfo itemholderinfo1)
            {
                viewholder.setIsRecyclable(false);
                if (mDataSetHasChangedAfterLayout)
                {
                    if (mItemAnimator.animateChange(viewholder, viewholder, itemholderinfo, itemholderinfo1))
                    {
                        postAnimationRunner();
                    }
                } else
                if (mItemAnimator.animatePersistence(viewholder, itemholderinfo, itemholderinfo1))
                {
                    postAnimationRunner();
                    return;
                }
            }

            public void unused(ViewHolder viewholder)
            {
                mLayout.removeAndRecycleView(viewholder.itemView, mRecycler);
            }

            
            {
                this$0 = RecyclerView.this;
                super();
            }
        };
        if (attributeset != null)
        {
            obj = context.obtainStyledAttributes(attributeset, CLIP_TO_PADDING_ATTR, i, 0);
            mClipToPadding = ((TypedArray) (obj)).getBoolean(0, true);
            ((TypedArray) (obj)).recycle();
        } else
        {
            mClipToPadding = true;
        }
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        obj = ViewConfiguration.get(context);
        mTouchSlop = ((ViewConfiguration) (obj)).getScaledTouchSlop();
        mMinFlingVelocity = ((ViewConfiguration) (obj)).getScaledMinimumFlingVelocity();
        mMaxFlingVelocity = ((ViewConfiguration) (obj)).getScaledMaximumFlingVelocity();
        if (getOverScrollMode() == 2)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        setWillNotDraw(flag);
        mItemAnimator.setListener(mItemAnimatorListener);
        initAdapterManager();
        initChildrenHelper();
        if (ViewCompat.getImportantForAccessibility(this) == 0)
        {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
        mAccessibilityManager = (AccessibilityManager)getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
        flag = true;
        if (attributeset != null)
        {
            obj = context.obtainStyledAttributes(attributeset, android.support.v7.recyclerview.R.styleable.RecyclerView, i, 0);
            String s = ((TypedArray) (obj)).getString(android.support.v7.recyclerview.R.styleable.RecyclerView_layoutManager);
            if (((TypedArray) (obj)).getInt(android.support.v7.recyclerview.R.styleable.RecyclerView_android_descendantFocusability, -1) == -1)
            {
                setDescendantFocusability(0x40000);
            }
            mEnableFastScroller = ((TypedArray) (obj)).getBoolean(android.support.v7.recyclerview.R.styleable.RecyclerView_fastScrollEnabled, false);
            if (mEnableFastScroller)
            {
                initFastScroller((StateListDrawable)((TypedArray) (obj)).getDrawable(android.support.v7.recyclerview.R.styleable.RecyclerView_fastScrollVerticalThumbDrawable), ((TypedArray) (obj)).getDrawable(android.support.v7.recyclerview.R.styleable.RecyclerView_fastScrollVerticalTrackDrawable), (StateListDrawable)((TypedArray) (obj)).getDrawable(android.support.v7.recyclerview.R.styleable.RecyclerView_fastScrollHorizontalThumbDrawable), ((TypedArray) (obj)).getDrawable(android.support.v7.recyclerview.R.styleable.RecyclerView_fastScrollHorizontalTrackDrawable));
            }
            ((TypedArray) (obj)).recycle();
            createLayoutManager(context, s, attributeset, i, 0);
            if (android.os.Build.VERSION.SDK_INT >= 21)
            {
                context = context.obtainStyledAttributes(attributeset, NESTED_SCROLLING_ATTRS, i, 0);
                flag = context.getBoolean(0, true);
                context.recycle();
            }
        } else
        {
            setDescendantFocusability(0x40000);
        }
        setNestedScrollingEnabled(flag);
    }

    private void addAnimatingView(ViewHolder viewholder)
    {
        View view = viewholder.itemView;
        boolean flag;
        if (view.getParent() == this)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mRecycler.unscrapView(getChildViewHolder(view));
        if (viewholder.isTmpDetached())
        {
            mChildHelper.attachViewToParent(view, -1, view.getLayoutParams(), true);
            return;
        }
        if (!flag)
        {
            mChildHelper.addView(view, true);
            return;
        } else
        {
            mChildHelper.hide(view);
            return;
        }
    }

    private void animateChange(ViewHolder viewholder, ViewHolder viewholder1, ItemAnimator.ItemHolderInfo itemholderinfo, ItemAnimator.ItemHolderInfo itemholderinfo1, boolean flag, boolean flag1)
    {
        viewholder.setIsRecyclable(false);
        if (flag)
        {
            addAnimatingView(viewholder);
        }
        if (viewholder != viewholder1)
        {
            if (flag1)
            {
                addAnimatingView(viewholder1);
            }
            viewholder.mShadowedHolder = viewholder1;
            addAnimatingView(viewholder);
            mRecycler.unscrapView(viewholder);
            viewholder1.setIsRecyclable(false);
            viewholder1.mShadowingHolder = viewholder;
        }
        if (mItemAnimator.animateChange(viewholder, viewholder1, itemholderinfo, itemholderinfo1))
        {
            postAnimationRunner();
        }
    }

    private void cancelTouch()
    {
        resetTouch();
        setScrollState(0);
    }

    static void clearNestedRecyclerViewIfNotNested(ViewHolder viewholder)
    {
        if (viewholder.mNestedRecyclerView == null) goto _L2; else goto _L1
_L1:
        Object obj = (View)viewholder.mNestedRecyclerView.get();
_L5:
        if (obj == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (obj != viewholder.itemView) goto _L3; else goto _L2
_L2:
        return;
_L3:
        obj = ((View) (obj)).getParent();
        if (obj instanceof View)
        {
            obj = (View)obj;
        } else
        {
            obj = null;
        }
        if (true) goto _L5; else goto _L4
_L4:
        viewholder.mNestedRecyclerView = null;
        return;
    }

    private void createLayoutManager(Context context, String s, AttributeSet attributeset, int i, int j)
    {
        if (s == null) goto _L2; else goto _L1
_L1:
        s = s.trim();
        if (s.length() == 0) goto _L2; else goto _L3
_L3:
        String s1 = getFullClassName(context, s);
        if (!isInEditMode()) goto _L5; else goto _L4
_L4:
        s = getClass().getClassLoader();
_L8:
        Class class1 = s.loadClass(s1).asSubclass(android/support/v7/widget/RecyclerView$LayoutManager);
        s = null;
        Constructor constructor = class1.getConstructor(LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
        s = ((String) (new Object[] {
            context, attributeset, Integer.valueOf(i), Integer.valueOf(j)
        }));
        context = constructor;
_L6:
        NoSuchMethodException nosuchmethodexception;
        try
        {
            context.setAccessible(true);
            setLayoutManager((LayoutManager)context.newInstance(s));
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new IllegalStateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Unable to find LayoutManager ").append(s1).toString(), context);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new IllegalStateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Could not instantiate the LayoutManager: ").append(s1).toString(), context);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new IllegalStateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Could not instantiate the LayoutManager: ").append(s1).toString(), context);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new IllegalStateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Cannot access non-public constructor ").append(s1).toString(), context);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new IllegalStateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Class is not a LayoutManager ").append(s1).toString(), context);
        }
_L5:
        s = context.getClassLoader();
        continue; /* Loop/switch isn't completed */
        nosuchmethodexception;
        context = class1.getConstructor(new Class[0]);
          goto _L6
        context;
        context.initCause(nosuchmethodexception);
        throw new IllegalStateException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Error creating LayoutManager ").append(s1).toString(), context);
_L2:
        return;
        if (true) goto _L8; else goto _L7
_L7:
    }

    private boolean didChildRangeChange(int i, int j)
    {
        boolean flag = false;
        findMinMaxChildLayoutPositions(mMinMaxLayoutPositions);
        if (mMinMaxLayoutPositions[0] != i || mMinMaxLayoutPositions[1] != j)
        {
            flag = true;
        }
        return flag;
    }

    private void dispatchContentChangedIfNecessary()
    {
        int i = mEatenAccessibilityChangeFlags;
        mEatenAccessibilityChangeFlags = 0;
        if (i != 0 && isAccessibilityEnabled())
        {
            AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain();
            accessibilityevent.setEventType(2048);
            AccessibilityEventCompat.setContentChangeTypes(accessibilityevent, i);
            sendAccessibilityEventUnchecked(accessibilityevent);
        }
    }

    private void dispatchLayoutStep1()
    {
        mState.assertLayoutStep(1);
        mState.mIsMeasuring = false;
        eatRequestLayout();
        mViewInfoStore.clear();
        onEnterLayoutOrScroll();
        processAdapterUpdatesAndSetAnimationFlags();
        saveFocusInfo();
        State state = mState;
        boolean flag;
        if (mState.mRunSimpleAnimations && mItemsChanged)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        state.mTrackOldChangeHolders = flag;
        mItemsChanged = false;
        mItemsAddedOrRemoved = false;
        mState.mInPreLayout = mState.mRunPredictiveAnimations;
        mState.mItemCount = mAdapter.getItemCount();
        findMinMaxChildLayoutPositions(mMinMaxLayoutPositions);
        if (mState.mRunSimpleAnimations)
        {
            int k = mChildHelper.getChildCount();
            int i = 0;
            while (i < k) 
            {
                ViewHolder viewholder = getChildViewHolderInt(mChildHelper.getChildAt(i));
                if (!viewholder.shouldIgnore() && (!viewholder.isInvalid() || mAdapter.hasStableIds()))
                {
                    ItemAnimator.ItemHolderInfo itemholderinfo = mItemAnimator.recordPreLayoutInformation(mState, viewholder, ItemAnimator.buildAdapterChangeFlagsForAnimations(viewholder), viewholder.getUnmodifiedPayloads());
                    mViewInfoStore.addToPreLayout(viewholder, itemholderinfo);
                    if (mState.mTrackOldChangeHolders && viewholder.isUpdated() && !viewholder.isRemoved() && !viewholder.shouldIgnore() && !viewholder.isInvalid())
                    {
                        long l1 = getChangedHolderKey(viewholder);
                        mViewInfoStore.addToOldChangeHolders(l1, viewholder);
                    }
                }
                i++;
            }
        }
        if (!mState.mRunPredictiveAnimations) goto _L2; else goto _L1
_L1:
        saveOldPositions();
        boolean flag1 = mState.mStructureChanged;
        mState.mStructureChanged = false;
        mLayout.onLayoutChildren(mRecycler, mState);
        mState.mStructureChanged = flag1;
        int j = 0;
        do
        {
            if (j >= mChildHelper.getChildCount())
            {
                break;
            }
            ViewHolder viewholder1 = getChildViewHolderInt(mChildHelper.getChildAt(j));
            if (!viewholder1.shouldIgnore() && !mViewInfoStore.isInPreLayout(viewholder1))
            {
                int i1 = ItemAnimator.buildAdapterChangeFlagsForAnimations(viewholder1);
                boolean flag2 = viewholder1.hasAnyOfTheFlags(8192);
                int l = i1;
                if (!flag2)
                {
                    l = i1 | 0x1000;
                }
                ItemAnimator.ItemHolderInfo itemholderinfo1 = mItemAnimator.recordPreLayoutInformation(mState, viewholder1, l, viewholder1.getUnmodifiedPayloads());
                if (flag2)
                {
                    recordAnimationInfoIfBouncedHiddenView(viewholder1, itemholderinfo1);
                } else
                {
                    mViewInfoStore.addToAppearedInPreLayoutHolders(viewholder1, itemholderinfo1);
                }
            }
            j++;
        } while (true);
        clearOldPositions();
_L4:
        onExitLayoutOrScroll();
        resumeRequestLayout(false);
        mState.mLayoutStep = 2;
        return;
_L2:
        clearOldPositions();
        if (true) goto _L4; else goto _L3
_L3:
    }

    private void dispatchLayoutStep2()
    {
        eatRequestLayout();
        onEnterLayoutOrScroll();
        mState.assertLayoutStep(6);
        mAdapterHelper.consumeUpdatesInOnePass();
        mState.mItemCount = mAdapter.getItemCount();
        mState.mDeletedInvisibleItemCountSincePreviousLayout = 0;
        mState.mInPreLayout = false;
        mLayout.onLayoutChildren(mRecycler, mState);
        mState.mStructureChanged = false;
        mPendingSavedState = null;
        State state = mState;
        boolean flag;
        if (mState.mRunSimpleAnimations && mItemAnimator != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        state.mRunSimpleAnimations = flag;
        mState.mLayoutStep = 4;
        onExitLayoutOrScroll();
        resumeRequestLayout(false);
    }

    private void dispatchLayoutStep3()
    {
        mState.assertLayoutStep(4);
        eatRequestLayout();
        onEnterLayoutOrScroll();
        mState.mLayoutStep = 1;
        if (mState.mRunSimpleAnimations)
        {
            int i = mChildHelper.getChildCount() - 1;
            while (i >= 0) 
            {
                ViewHolder viewholder = getChildViewHolderInt(mChildHelper.getChildAt(i));
                if (!viewholder.shouldIgnore())
                {
                    long l = getChangedHolderKey(viewholder);
                    ItemAnimator.ItemHolderInfo itemholderinfo1 = mItemAnimator.recordPostLayoutInformation(mState, viewholder);
                    ViewHolder viewholder1 = mViewInfoStore.getFromOldChangeHolders(l);
                    if (viewholder1 != null && !viewholder1.shouldIgnore())
                    {
                        boolean flag = mViewInfoStore.isDisappearing(viewholder1);
                        boolean flag1 = mViewInfoStore.isDisappearing(viewholder);
                        if (flag && viewholder1 == viewholder)
                        {
                            mViewInfoStore.addToPostLayout(viewholder, itemholderinfo1);
                        } else
                        {
                            ItemAnimator.ItemHolderInfo itemholderinfo = mViewInfoStore.popFromPreLayout(viewholder1);
                            mViewInfoStore.addToPostLayout(viewholder, itemholderinfo1);
                            itemholderinfo1 = mViewInfoStore.popFromPostLayout(viewholder);
                            if (itemholderinfo == null)
                            {
                                handleMissingPreInfoForChangeError(l, viewholder, viewholder1);
                            } else
                            {
                                animateChange(viewholder1, viewholder, itemholderinfo, itemholderinfo1, flag, flag1);
                            }
                        }
                    } else
                    {
                        mViewInfoStore.addToPostLayout(viewholder, itemholderinfo1);
                    }
                }
                i--;
            }
            mViewInfoStore.process(mViewInfoProcessCallback);
        }
        mLayout.removeAndRecycleScrapInt(mRecycler);
        mState.mPreviousLayoutItemCount = mState.mItemCount;
        mDataSetHasChangedAfterLayout = false;
        mState.mRunSimpleAnimations = false;
        mState.mRunPredictiveAnimations = false;
        mLayout.mRequestedSimpleAnimations = false;
        if (mRecycler.mChangedScrap != null)
        {
            mRecycler.mChangedScrap.clear();
        }
        if (mLayout.mPrefetchMaxObservedInInitialPrefetch)
        {
            mLayout.mPrefetchMaxCountObserved = 0;
            mLayout.mPrefetchMaxObservedInInitialPrefetch = false;
            mRecycler.updateViewCacheSize();
        }
        mLayout.onLayoutCompleted(mState);
        onExitLayoutOrScroll();
        resumeRequestLayout(false);
        mViewInfoStore.clear();
        if (didChildRangeChange(mMinMaxLayoutPositions[0], mMinMaxLayoutPositions[1]))
        {
            dispatchOnScrolled(0, 0);
        }
        recoverFocusFromState();
        resetFocusInfo();
    }

    private boolean dispatchOnItemTouch(MotionEvent motionevent)
    {
        int i = motionevent.getAction();
        if (mActiveOnItemTouchListener == null) goto _L2; else goto _L1
_L1:
        if (i != 0) goto _L4; else goto _L3
_L3:
        mActiveOnItemTouchListener = null;
_L2:
        if (i == 0) goto _L6; else goto _L5
_L5:
        int j;
        j = mOnItemTouchListeners.size();
        i = 0;
_L11:
        OnItemTouchListener onitemtouchlistener;
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        onitemtouchlistener = (OnItemTouchListener)mOnItemTouchListeners.get(i);
        if (!onitemtouchlistener.onInterceptTouchEvent(this, motionevent)) goto _L8; else goto _L7
_L7:
        mActiveOnItemTouchListener = onitemtouchlistener;
_L10:
        return true;
_L4:
        mActiveOnItemTouchListener.onTouchEvent(this, motionevent);
        if (i != 3 && i != 1) goto _L10; else goto _L9
_L9:
        mActiveOnItemTouchListener = null;
        return true;
_L8:
        i++;
        if (true) goto _L11; else goto _L6
_L6:
        return false;
    }

    private boolean dispatchOnItemTouchIntercept(MotionEvent motionevent)
    {
        int j = motionevent.getAction();
        if (j == 3 || j == 0)
        {
            mActiveOnItemTouchListener = null;
        }
        int k = mOnItemTouchListeners.size();
        for (int i = 0; i < k; i++)
        {
            OnItemTouchListener onitemtouchlistener = (OnItemTouchListener)mOnItemTouchListeners.get(i);
            if (onitemtouchlistener.onInterceptTouchEvent(this, motionevent) && j != 3)
            {
                mActiveOnItemTouchListener = onitemtouchlistener;
                return true;
            }
        }

        return false;
    }

    private void findMinMaxChildLayoutPositions(int ai[])
    {
        int k1 = mChildHelper.getChildCount();
        if (k1 == 0)
        {
            ai[0] = -1;
            ai[1] = -1;
            return;
        }
        int j = 0x7fffffff;
        int l = 0x80000000;
        int k = 0;
        while (k < k1) 
        {
            ViewHolder viewholder = getChildViewHolderInt(mChildHelper.getChildAt(k));
            int j1;
            if (viewholder.shouldIgnore())
            {
                j1 = j;
                j = l;
            } else
            {
                int i1 = viewholder.getLayoutPosition();
                int i = j;
                if (i1 < j)
                {
                    i = i1;
                }
                j = l;
                j1 = i;
                if (i1 > l)
                {
                    j = i1;
                    j1 = i;
                }
            }
            k++;
            l = j;
            j = j1;
        }
        ai[0] = j;
        ai[1] = l;
    }

    static RecyclerView findNestedRecyclerView(View view)
    {
        if (!(view instanceof ViewGroup))
        {
            return null;
        }
        if (view instanceof RecyclerView)
        {
            return (RecyclerView)view;
        }
        view = (ViewGroup)view;
        int j = view.getChildCount();
        for (int i = 0; i < j; i++)
        {
            RecyclerView recyclerview = findNestedRecyclerView(view.getChildAt(i));
            if (recyclerview != null)
            {
                return recyclerview;
            }
        }

        return null;
    }

    private View findNextViewToFocus()
    {
        ViewHolder viewholder;
        int i;
        int j;
        int k;
        if (mState.mFocusedItemPosition != -1)
        {
            i = mState.mFocusedItemPosition;
        } else
        {
            i = 0;
        }
        k = mState.getItemCount();
        j = i;
_L4:
        if (j >= k) goto _L2; else goto _L1
_L1:
        viewholder = findViewHolderForAdapterPosition(j);
        if (viewholder != null) goto _L3; else goto _L2
_L2:
        i = Math.min(k, i) - 1;
_L5:
        if (i >= 0)
        {
            viewholder = findViewHolderForAdapterPosition(i);
            if (viewholder != null)
            {
                break MISSING_BLOCK_LABEL_98;
            }
        }
        return null;
_L3:
        if (viewholder.itemView.hasFocusable())
        {
            return viewholder.itemView;
        }
        j++;
          goto _L4
        if (viewholder.itemView.hasFocusable())
        {
            return viewholder.itemView;
        }
        i--;
          goto _L5
    }

    static ViewHolder getChildViewHolderInt(View view)
    {
        if (view == null)
        {
            return null;
        } else
        {
            return ((LayoutParams)view.getLayoutParams()).mViewHolder;
        }
    }

    static void getDecoratedBoundsWithMarginsInt(View view, Rect rect)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        Rect rect1 = layoutparams.mDecorInsets;
        rect.set(view.getLeft() - rect1.left - layoutparams.leftMargin, view.getTop() - rect1.top - layoutparams.topMargin, view.getRight() + rect1.right + layoutparams.rightMargin, view.getBottom() + rect1.bottom + layoutparams.bottomMargin);
    }

    private int getDeepestFocusedViewWithId(View view)
    {
        int i = view.getId();
        do
        {
            if (view.isFocused() || !(view instanceof ViewGroup) || !view.hasFocus())
            {
                break;
            }
            View view1 = ((ViewGroup)view).getFocusedChild();
            view = view1;
            if (view1.getId() != -1)
            {
                i = view1.getId();
                view = view1;
            }
        } while (true);
        return i;
    }

    private String getFullClassName(Context context, String s)
    {
        if (s.charAt(0) == '.')
        {
            context = (new StringBuilder()).append(context.getPackageName()).append(s).toString();
        } else
        {
            context = s;
            if (!s.contains("."))
            {
                return (new StringBuilder()).append(android/support/v7/widget/RecyclerView.getPackage().getName()).append('.').append(s).toString();
            }
        }
        return context;
    }

    private float getScrollFactor()
    {
label0:
        {
            if (mScrollFactor == 1.401298E-45F)
            {
                TypedValue typedvalue = new TypedValue();
                if (!getContext().getTheme().resolveAttribute(0x101004d, typedvalue, true))
                {
                    break label0;
                }
                mScrollFactor = typedvalue.getDimension(getContext().getResources().getDisplayMetrics());
            }
            return mScrollFactor;
        }
        return 0.0F;
    }

    private NestedScrollingChildHelper getScrollingChildHelper()
    {
        if (mScrollingChildHelper == null)
        {
            mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        return mScrollingChildHelper;
    }

    private void handleMissingPreInfoForChangeError(long l, ViewHolder viewholder, ViewHolder viewholder1)
    {
        int i;
        int j;
        j = mChildHelper.getChildCount();
        i = 0;
_L3:
        ViewHolder viewholder2;
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        viewholder2 = getChildViewHolderInt(mChildHelper.getChildAt(i));
          goto _L1
_L5:
        i++;
        if (true) goto _L3; else goto _L2
_L1:
        if (viewholder2 == viewholder || getChangedHolderKey(viewholder2) != l) goto _L5; else goto _L4
_L4:
        if (mAdapter != null && mAdapter.hasStableIds())
        {
            throw new IllegalStateException((new StringBuilder()).append("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:").append(viewholder2).append(" \n View Holder 2:").append(viewholder).toString());
        } else
        {
            throw new IllegalStateException((new StringBuilder()).append("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:").append(viewholder2).append(" \n View Holder 2:").append(viewholder).toString());
        }
_L2:
        Log.e("RecyclerView", (new StringBuilder()).append("Problem while matching changed view holders with the newones. The pre-layout information for the change holder ").append(viewholder1).append(" cannot be found but it is necessary for ").append(viewholder).toString());
        return;
    }

    private boolean hasUpdatedView()
    {
        int i;
        int j;
        j = mChildHelper.getChildCount();
        i = 0;
_L3:
        ViewHolder viewholder;
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        viewholder = getChildViewHolderInt(mChildHelper.getChildAt(i));
          goto _L1
_L5:
        i++;
        if (true) goto _L3; else goto _L2
_L1:
        if (viewholder == null || viewholder.shouldIgnore() || !viewholder.isUpdated()) goto _L5; else goto _L4
_L4:
        return true;
_L2:
        return false;
    }

    private void initChildrenHelper()
    {
        mChildHelper = new ChildHelper(new ChildHelper.Callback() {

            final RecyclerView this$0;

            public void addView(View view, int i)
            {
                RecyclerView.this.addView(view, i);
                dispatchChildAttached(view);
            }

            public void attachViewToParent(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
            {
                ViewHolder viewholder = RecyclerView.getChildViewHolderInt(view);
                if (viewholder != null)
                {
                    if (!viewholder.isTmpDetached() && !viewholder.shouldIgnore())
                    {
                        throw new IllegalArgumentException((new StringBuilder()).append("Called attach on a child which is not detached: ").append(viewholder).toString());
                    }
                    viewholder.clearTmpDetachFlag();
                }
                RecyclerView.this.attachViewToParent(view, i, layoutparams);
            }

            public void detachViewFromParent(int i)
            {
                Object obj = getChildAt(i);
                if (obj != null)
                {
                    obj = RecyclerView.getChildViewHolderInt(((View) (obj)));
                    if (obj != null)
                    {
                        if (((ViewHolder) (obj)).isTmpDetached() && !((ViewHolder) (obj)).shouldIgnore())
                        {
                            throw new IllegalArgumentException((new StringBuilder()).append("called detach on an already detached child ").append(obj).toString());
                        }
                        ((ViewHolder) (obj)).addFlags(256);
                    }
                }
                RecyclerView.this.detachViewFromParent(i);
            }

            public View getChildAt(int i)
            {
                return RecyclerView.this.getChildAt(i);
            }

            public int getChildCount()
            {
                return RecyclerView.this.getChildCount();
            }

            public ViewHolder getChildViewHolder(View view)
            {
                return RecyclerView.getChildViewHolderInt(view);
            }

            public int indexOfChild(View view)
            {
                return RecyclerView.this.indexOfChild(view);
            }

            public void onEnteredHiddenState(View view)
            {
                view = RecyclerView.getChildViewHolderInt(view);
                if (view != null)
                {
                    view.onEnteredHiddenState(RecyclerView.this);
                }
            }

            public void onLeftHiddenState(View view)
            {
                view = RecyclerView.getChildViewHolderInt(view);
                if (view != null)
                {
                    view.onLeftHiddenState(RecyclerView.this);
                }
            }

            public void removeAllViews()
            {
                int j = getChildCount();
                for (int i = 0; i < j; i++)
                {
                    dispatchChildDetached(getChildAt(i));
                }

                RecyclerView.this.removeAllViews();
            }

            public void removeViewAt(int i)
            {
                View view = RecyclerView.this.getChildAt(i);
                if (view != null)
                {
                    dispatchChildDetached(view);
                }
                RecyclerView.this.removeViewAt(i);
            }

            
            {
                this$0 = RecyclerView.this;
                super();
            }
        });
    }

    private boolean isPreferredNextFocus(View view, View view1, int i)
    {
        boolean flag = false;
        boolean flag2 = true;
        boolean flag1;
        if (view1 == null || view1 == this)
        {
            flag1 = false;
        } else
        {
            flag1 = flag2;
            if (view != null)
            {
                if (i == 2 || i == 1)
                {
                    byte byte0;
                    if (mLayout.getLayoutDirection() == 1)
                    {
                        byte0 = 1;
                    } else
                    {
                        byte0 = 0;
                    }
                    if (i == 2)
                    {
                        flag = true;
                    }
                    if (flag ^ byte0)
                    {
                        byte0 = 66;
                    } else
                    {
                        byte0 = 17;
                    }
                    flag1 = flag2;
                    if (!isPreferredNextFocusAbsolute(view, view1, byte0))
                    {
                        if (i == 2)
                        {
                            return isPreferredNextFocusAbsolute(view, view1, 130);
                        } else
                        {
                            return isPreferredNextFocusAbsolute(view, view1, 33);
                        }
                    }
                } else
                {
                    return isPreferredNextFocusAbsolute(view, view1, i);
                }
            }
        }
        return flag1;
    }

    private boolean isPreferredNextFocusAbsolute(View view, View view1, int i)
    {
        mTempRect.set(0, 0, view.getWidth(), view.getHeight());
        mTempRect2.set(0, 0, view1.getWidth(), view1.getHeight());
        offsetDescendantRectToMyCoords(view, mTempRect);
        offsetDescendantRectToMyCoords(view1, mTempRect2);
        i;
        JVM INSTR lookupswitch 4: default 96
    //                   17: 124
    //                   33: 232
    //                   66: 179
    //                   130: 285;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("direction must be absolute. received:").append(i).toString());
_L2:
        if (mTempRect.right <= mTempRect2.right && mTempRect.left < mTempRect2.right || mTempRect.left <= mTempRect2.left) goto _L7; else goto _L6
_L6:
        return true;
_L7:
        return false;
_L4:
        if (mTempRect.left >= mTempRect2.left && mTempRect.right > mTempRect2.left || mTempRect.right >= mTempRect2.right)
        {
            return false;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (mTempRect.bottom <= mTempRect2.bottom && mTempRect.top < mTempRect2.bottom || mTempRect.top <= mTempRect2.top)
        {
            return false;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (mTempRect.top >= mTempRect2.top && mTempRect.bottom > mTempRect2.top || mTempRect.bottom >= mTempRect2.bottom)
        {
            return false;
        }
        if (true) goto _L6; else goto _L8
_L8:
    }

    private void onPointerUp(MotionEvent motionevent)
    {
        int i = motionevent.getActionIndex();
        if (motionevent.getPointerId(i) == mScrollPointerId)
        {
            int j;
            if (i == 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            mScrollPointerId = motionevent.getPointerId(i);
            j = (int)(motionevent.getX(i) + 0.5F);
            mLastTouchX = j;
            mInitialTouchX = j;
            i = (int)(motionevent.getY(i) + 0.5F);
            mLastTouchY = i;
            mInitialTouchY = i;
        }
    }

    private boolean predictiveItemAnimationsEnabled()
    {
        return mItemAnimator != null && mLayout.supportsPredictiveItemAnimations();
    }

    private void processAdapterUpdatesAndSetAnimationFlags()
    {
        boolean flag2 = true;
        if (mDataSetHasChangedAfterLayout)
        {
            mAdapterHelper.reset();
            mLayout.onItemsChanged(this);
        }
        State state;
        boolean flag;
        boolean flag1;
        if (predictiveItemAnimationsEnabled())
        {
            mAdapterHelper.preProcess();
        } else
        {
            mAdapterHelper.consumeUpdatesInOnePass();
        }
        if (mItemsAddedOrRemoved || mItemsChanged)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        state = mState;
        if (mFirstLayoutComplete && mItemAnimator != null && (mDataSetHasChangedAfterLayout || flag || mLayout.mRequestedSimpleAnimations) && (!mDataSetHasChangedAfterLayout || mAdapter.hasStableIds()))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        state.mRunSimpleAnimations = flag1;
        state = mState;
        if (mState.mRunSimpleAnimations && flag && !mDataSetHasChangedAfterLayout && predictiveItemAnimationsEnabled())
        {
            flag1 = flag2;
        } else
        {
            flag1 = false;
        }
        state.mRunPredictiveAnimations = flag1;
    }

    private void pullGlows(float f, float f1, float f2, float f3)
    {
        boolean flag = false;
        if (f1 < 0.0F)
        {
            ensureLeftGlow();
            EdgeEffectCompat.onPull(mLeftGlow, -f1 / (float)getWidth(), 1.0F - f2 / (float)getHeight());
            flag = true;
        } else
        if (f1 > 0.0F)
        {
            ensureRightGlow();
            EdgeEffectCompat.onPull(mRightGlow, f1 / (float)getWidth(), f2 / (float)getHeight());
            flag = true;
        }
        if (f3 < 0.0F)
        {
            ensureTopGlow();
            EdgeEffectCompat.onPull(mTopGlow, -f3 / (float)getHeight(), f / (float)getWidth());
            flag = true;
        } else
        if (f3 > 0.0F)
        {
            ensureBottomGlow();
            EdgeEffectCompat.onPull(mBottomGlow, f3 / (float)getHeight(), 1.0F - f / (float)getWidth());
            flag = true;
        }
        if (flag || f1 != 0.0F || f3 != 0.0F)
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private void recoverFocusFromState()
    {
        if (mPreserveFocusAfterLayout && mAdapter != null && hasFocus() && getDescendantFocusability() != 0x60000 && (getDescendantFocusability() != 0x20000 || !isFocused())) goto _L2; else goto _L1
_L1:
        return;
_L2:
        View view;
        if (isFocused())
        {
            break; /* Loop/switch isn't completed */
        }
        view = getFocusedChild();
        if (!IGNORE_DETACHED_FOCUSED_CHILD || view.getParent() != null && view.hasFocus())
        {
            continue; /* Loop/switch isn't completed */
        }
        if (mChildHelper.getChildCount() == 0)
        {
            requestFocus();
            return;
        }
        break; /* Loop/switch isn't completed */
        if (!mChildHelper.isHidden(view)) goto _L1; else goto _L3
_L3:
        Object obj1 = null;
        Object obj = obj1;
        if (mState.mFocusedItemId != -1L)
        {
            obj = obj1;
            if (mAdapter.hasStableIds())
            {
                obj = findViewHolderForItemId(mState.mFocusedItemId);
            }
        }
        obj1 = null;
        if (obj == null || mChildHelper.isHidden(((ViewHolder) (obj)).itemView) || !((ViewHolder) (obj)).itemView.hasFocusable())
        {
            obj = obj1;
            if (mChildHelper.getChildCount() > 0)
            {
                obj = findNextViewToFocus();
            }
        } else
        {
            obj = ((ViewHolder) (obj)).itemView;
        }
        if (obj != null)
        {
            Object obj2 = obj;
            if ((long)mState.mFocusedSubChildId != -1L)
            {
                View view1 = ((View) (obj)).findViewById(mState.mFocusedSubChildId);
                obj2 = obj;
                if (view1 != null)
                {
                    obj2 = obj;
                    if (view1.isFocusable())
                    {
                        obj2 = view1;
                    }
                }
            }
            ((View) (obj2)).requestFocus();
            return;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    private void releaseGlows()
    {
        boolean flag1 = false;
        if (mLeftGlow != null)
        {
            mLeftGlow.onRelease();
            flag1 = mLeftGlow.isFinished();
        }
        boolean flag = flag1;
        if (mTopGlow != null)
        {
            mTopGlow.onRelease();
            flag = flag1 | mTopGlow.isFinished();
        }
        flag1 = flag;
        if (mRightGlow != null)
        {
            mRightGlow.onRelease();
            flag1 = flag | mRightGlow.isFinished();
        }
        flag = flag1;
        if (mBottomGlow != null)
        {
            mBottomGlow.onRelease();
            flag = flag1 | mBottomGlow.isFinished();
        }
        if (flag)
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private void requestChildOnScreen(View view, View view1)
    {
        boolean flag1 = true;
        Object obj;
        Rect rect1;
        boolean flag;
        if (view1 != null)
        {
            obj = view1;
        } else
        {
            obj = view;
        }
        mTempRect.set(0, 0, ((View) (obj)).getWidth(), ((View) (obj)).getHeight());
        obj = ((View) (obj)).getLayoutParams();
        if (obj instanceof LayoutParams)
        {
            obj = (LayoutParams)obj;
            if (!((LayoutParams) (obj)).mInsetsDirty)
            {
                obj = ((LayoutParams) (obj)).mDecorInsets;
                Rect rect = mTempRect;
                rect.left = rect.left - ((Rect) (obj)).left;
                rect = mTempRect;
                rect.right = rect.right + ((Rect) (obj)).right;
                rect = mTempRect;
                rect.top = rect.top - ((Rect) (obj)).top;
                rect = mTempRect;
                rect.bottom = rect.bottom + ((Rect) (obj)).bottom;
            }
        }
        if (view1 != null)
        {
            offsetDescendantRectToMyCoords(view1, mTempRect);
            offsetRectIntoDescendantCoords(view, mTempRect);
        }
        obj = mLayout;
        rect1 = mTempRect;
        if (!mFirstLayoutComplete)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (view1 != null)
        {
            flag1 = false;
        }
        ((LayoutManager) (obj)).requestChildRectangleOnScreen(this, view, rect1, flag, flag1);
    }

    private void resetFocusInfo()
    {
        mState.mFocusedItemId = -1L;
        mState.mFocusedItemPosition = -1;
        mState.mFocusedSubChildId = -1;
    }

    private void resetTouch()
    {
        if (mVelocityTracker != null)
        {
            mVelocityTracker.clear();
        }
        stopNestedScroll();
        releaseGlows();
    }

    private void saveFocusInfo()
    {
        State state = null;
        Object obj = state;
        if (mPreserveFocusAfterLayout)
        {
            obj = state;
            if (hasFocus())
            {
                obj = state;
                if (mAdapter != null)
                {
                    obj = getFocusedChild();
                }
            }
        }
        if (obj == null)
        {
            obj = null;
        } else
        {
            obj = findContainingViewHolder(((View) (obj)));
        }
        if (obj == null)
        {
            resetFocusInfo();
            return;
        }
        state = mState;
        int i;
        long l;
        if (mAdapter.hasStableIds())
        {
            l = ((ViewHolder) (obj)).getItemId();
        } else
        {
            l = -1L;
        }
        state.mFocusedItemId = l;
        state = mState;
        if (mDataSetHasChangedAfterLayout)
        {
            i = -1;
        } else
        if (((ViewHolder) (obj)).isRemoved())
        {
            i = ((ViewHolder) (obj)).mOldPosition;
        } else
        {
            i = ((ViewHolder) (obj)).getAdapterPosition();
        }
        state.mFocusedItemPosition = i;
        mState.mFocusedSubChildId = getDeepestFocusedViewWithId(((ViewHolder) (obj)).itemView);
    }

    private void setAdapterInternal(Adapter adapter, boolean flag, boolean flag1)
    {
        if (mAdapter != null)
        {
            mAdapter.unregisterAdapterDataObserver(mObserver);
            mAdapter.onDetachedFromRecyclerView(this);
        }
        if (!flag || flag1)
        {
            removeAndRecycleViews();
        }
        mAdapterHelper.reset();
        Adapter adapter1 = mAdapter;
        mAdapter = adapter;
        if (adapter != null)
        {
            adapter.registerAdapterDataObserver(mObserver);
            adapter.onAttachedToRecyclerView(this);
        }
        if (mLayout != null)
        {
            mLayout.onAdapterChanged(adapter1, mAdapter);
        }
        mRecycler.onAdapterChanged(adapter1, mAdapter, flag);
        mState.mStructureChanged = true;
        markKnownViewsInvalid();
    }

    private void stopScrollersInternal()
    {
        mViewFlinger.stop();
        if (mLayout != null)
        {
            mLayout.stopSmoothScroller();
        }
    }

    void absorbGlows(int i, int j)
    {
        if (i < 0)
        {
            ensureLeftGlow();
            mLeftGlow.onAbsorb(-i);
        } else
        if (i > 0)
        {
            ensureRightGlow();
            mRightGlow.onAbsorb(i);
        }
        if (j < 0)
        {
            ensureTopGlow();
            mTopGlow.onAbsorb(-j);
        } else
        if (j > 0)
        {
            ensureBottomGlow();
            mBottomGlow.onAbsorb(j);
        }
        if (i != 0 || j != 0)
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void addFocusables(ArrayList arraylist, int i, int j)
    {
        if (mLayout == null || !mLayout.onAddFocusables(this, arraylist, i, j))
        {
            super.addFocusables(arraylist, i, j);
        }
    }

    public void addItemDecoration(ItemDecoration itemdecoration)
    {
        addItemDecoration(itemdecoration, -1);
    }

    public void addItemDecoration(ItemDecoration itemdecoration, int i)
    {
        if (mLayout != null)
        {
            mLayout.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
        }
        if (mItemDecorations.isEmpty())
        {
            setWillNotDraw(false);
        }
        if (i < 0)
        {
            mItemDecorations.add(itemdecoration);
        } else
        {
            mItemDecorations.add(i, itemdecoration);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public void addOnChildAttachStateChangeListener(OnChildAttachStateChangeListener onchildattachstatechangelistener)
    {
        if (mOnChildAttachStateListeners == null)
        {
            mOnChildAttachStateListeners = new ArrayList();
        }
        mOnChildAttachStateListeners.add(onchildattachstatechangelistener);
    }

    public void addOnItemTouchListener(OnItemTouchListener onitemtouchlistener)
    {
        mOnItemTouchListeners.add(onitemtouchlistener);
    }

    public void addOnScrollListener(OnScrollListener onscrolllistener)
    {
        if (mScrollListeners == null)
        {
            mScrollListeners = new ArrayList();
        }
        mScrollListeners.add(onscrolllistener);
    }

    void animateAppearance(ViewHolder viewholder, ItemAnimator.ItemHolderInfo itemholderinfo, ItemAnimator.ItemHolderInfo itemholderinfo1)
    {
        viewholder.setIsRecyclable(false);
        if (mItemAnimator.animateAppearance(viewholder, itemholderinfo, itemholderinfo1))
        {
            postAnimationRunner();
        }
    }

    void animateDisappearance(ViewHolder viewholder, ItemAnimator.ItemHolderInfo itemholderinfo, ItemAnimator.ItemHolderInfo itemholderinfo1)
    {
        addAnimatingView(viewholder);
        viewholder.setIsRecyclable(false);
        if (mItemAnimator.animateDisappearance(viewholder, itemholderinfo, itemholderinfo1))
        {
            postAnimationRunner();
        }
    }

    void assertInLayoutOrScroll(String s)
    {
        if (!isComputingLayout())
        {
            if (s == null)
            {
                throw new IllegalStateException("Cannot call this method unless RecyclerView is computing a layout or scrolling");
            } else
            {
                throw new IllegalStateException(s);
            }
        } else
        {
            return;
        }
    }

    void assertNotInLayoutOrScroll(String s)
    {
        if (isComputingLayout())
        {
            if (s == null)
            {
                throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling");
            } else
            {
                throw new IllegalStateException(s);
            }
        }
        if (mDispatchScrollCounter > 0)
        {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException(""));
        }
    }

    boolean canReuseUpdatedViewHolder(ViewHolder viewholder)
    {
        return mItemAnimator == null || mItemAnimator.canReuseUpdatedViewHolder(viewholder, viewholder.getUnmodifiedPayloads());
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return (layoutparams instanceof LayoutParams) && mLayout.checkLayoutParams((LayoutParams)layoutparams);
    }

    void clearOldPositions()
    {
        int j = mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < j; i++)
        {
            ViewHolder viewholder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
            if (!viewholder.shouldIgnore())
            {
                viewholder.clearOldPosition();
            }
        }

        mRecycler.clearOldPositions();
    }

    public void clearOnChildAttachStateChangeListeners()
    {
        if (mOnChildAttachStateListeners != null)
        {
            mOnChildAttachStateListeners.clear();
        }
    }

    public void clearOnScrollListeners()
    {
        if (mScrollListeners != null)
        {
            mScrollListeners.clear();
        }
    }

    public int computeHorizontalScrollExtent()
    {
        while (mLayout == null || !mLayout.canScrollHorizontally()) 
        {
            return 0;
        }
        return mLayout.computeHorizontalScrollExtent(mState);
    }

    public int computeHorizontalScrollOffset()
    {
        while (mLayout == null || !mLayout.canScrollHorizontally()) 
        {
            return 0;
        }
        return mLayout.computeHorizontalScrollOffset(mState);
    }

    public int computeHorizontalScrollRange()
    {
        while (mLayout == null || !mLayout.canScrollHorizontally()) 
        {
            return 0;
        }
        return mLayout.computeHorizontalScrollRange(mState);
    }

    public int computeVerticalScrollExtent()
    {
        while (mLayout == null || !mLayout.canScrollVertically()) 
        {
            return 0;
        }
        return mLayout.computeVerticalScrollExtent(mState);
    }

    public int computeVerticalScrollOffset()
    {
        while (mLayout == null || !mLayout.canScrollVertically()) 
        {
            return 0;
        }
        return mLayout.computeVerticalScrollOffset(mState);
    }

    public int computeVerticalScrollRange()
    {
        while (mLayout == null || !mLayout.canScrollVertically()) 
        {
            return 0;
        }
        return mLayout.computeVerticalScrollRange(mState);
    }

    void considerReleasingGlowsOnScroll(int i, int j)
    {
        boolean flag1 = false;
        boolean flag = flag1;
        if (mLeftGlow != null)
        {
            flag = flag1;
            if (!mLeftGlow.isFinished())
            {
                flag = flag1;
                if (i > 0)
                {
                    mLeftGlow.onRelease();
                    flag = mLeftGlow.isFinished();
                }
            }
        }
        flag1 = flag;
        if (mRightGlow != null)
        {
            flag1 = flag;
            if (!mRightGlow.isFinished())
            {
                flag1 = flag;
                if (i < 0)
                {
                    mRightGlow.onRelease();
                    flag1 = flag | mRightGlow.isFinished();
                }
            }
        }
        flag = flag1;
        if (mTopGlow != null)
        {
            flag = flag1;
            if (!mTopGlow.isFinished())
            {
                flag = flag1;
                if (j > 0)
                {
                    mTopGlow.onRelease();
                    flag = flag1 | mTopGlow.isFinished();
                }
            }
        }
        flag1 = flag;
        if (mBottomGlow != null)
        {
            flag1 = flag;
            if (!mBottomGlow.isFinished())
            {
                flag1 = flag;
                if (j < 0)
                {
                    mBottomGlow.onRelease();
                    flag1 = flag | mBottomGlow.isFinished();
                }
            }
        }
        if (flag1)
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    void consumePendingUpdateOperations()
    {
        if (!mFirstLayoutComplete || mDataSetHasChangedAfterLayout)
        {
            TraceCompat.beginSection("RV FullInvalidate");
            dispatchLayout();
            TraceCompat.endSection();
        } else
        if (mAdapterHelper.hasPendingUpdates())
        {
            if (mAdapterHelper.hasAnyUpdateTypes(4) && !mAdapterHelper.hasAnyUpdateTypes(11))
            {
                TraceCompat.beginSection("RV PartialInvalidate");
                eatRequestLayout();
                onEnterLayoutOrScroll();
                mAdapterHelper.preProcess();
                if (!mLayoutRequestEaten)
                {
                    if (hasUpdatedView())
                    {
                        dispatchLayout();
                    } else
                    {
                        mAdapterHelper.consumePostponedUpdates();
                    }
                }
                resumeRequestLayout(true);
                onExitLayoutOrScroll();
                TraceCompat.endSection();
                return;
            }
            if (mAdapterHelper.hasPendingUpdates())
            {
                TraceCompat.beginSection("RV FullInvalidate");
                dispatchLayout();
                TraceCompat.endSection();
                return;
            }
        }
    }

    void defaultOnMeasure(int i, int j)
    {
        setMeasuredDimension(LayoutManager.chooseSize(i, getPaddingLeft() + getPaddingRight(), ViewCompat.getMinimumWidth(this)), LayoutManager.chooseSize(j, getPaddingTop() + getPaddingBottom(), ViewCompat.getMinimumHeight(this)));
    }

    void dispatchChildAttached(View view)
    {
        ViewHolder viewholder = getChildViewHolderInt(view);
        onChildAttachedToWindow(view);
        if (mAdapter != null && viewholder != null)
        {
            mAdapter.onViewAttachedToWindow(viewholder);
        }
        if (mOnChildAttachStateListeners != null)
        {
            for (int i = mOnChildAttachStateListeners.size() - 1; i >= 0; i--)
            {
                ((OnChildAttachStateChangeListener)mOnChildAttachStateListeners.get(i)).onChildViewAttachedToWindow(view);
            }

        }
    }

    void dispatchChildDetached(View view)
    {
        ViewHolder viewholder = getChildViewHolderInt(view);
        onChildDetachedFromWindow(view);
        if (mAdapter != null && viewholder != null)
        {
            mAdapter.onViewDetachedFromWindow(viewholder);
        }
        if (mOnChildAttachStateListeners != null)
        {
            for (int i = mOnChildAttachStateListeners.size() - 1; i >= 0; i--)
            {
                ((OnChildAttachStateChangeListener)mOnChildAttachStateListeners.get(i)).onChildViewDetachedFromWindow(view);
            }

        }
    }

    void dispatchLayout()
    {
        if (mAdapter == null)
        {
            Log.e("RecyclerView", "No adapter attached; skipping layout");
            return;
        }
        if (mLayout == null)
        {
            Log.e("RecyclerView", "No layout manager attached; skipping layout");
            return;
        }
        mState.mIsMeasuring = false;
        if (mState.mLayoutStep == 1)
        {
            dispatchLayoutStep1();
            mLayout.setExactMeasureSpecsFrom(this);
            dispatchLayoutStep2();
        } else
        if (mAdapterHelper.hasUpdates() || mLayout.getWidth() != getWidth() || mLayout.getHeight() != getHeight())
        {
            mLayout.setExactMeasureSpecsFrom(this);
            dispatchLayoutStep2();
        } else
        {
            mLayout.setExactMeasureSpecsFrom(this);
        }
        dispatchLayoutStep3();
    }

    public boolean dispatchNestedFling(float f, float f1, boolean flag)
    {
        return getScrollingChildHelper().dispatchNestedFling(f, f1, flag);
    }

    public boolean dispatchNestedPreFling(float f, float f1)
    {
        return getScrollingChildHelper().dispatchNestedPreFling(f, f1);
    }

    public boolean dispatchNestedPreScroll(int i, int j, int ai[], int ai1[])
    {
        return getScrollingChildHelper().dispatchNestedPreScroll(i, j, ai, ai1);
    }

    public boolean dispatchNestedScroll(int i, int j, int k, int l, int ai[])
    {
        return getScrollingChildHelper().dispatchNestedScroll(i, j, k, l, ai);
    }

    void dispatchOnScrollStateChanged(int i)
    {
        if (mLayout != null)
        {
            mLayout.onScrollStateChanged(i);
        }
        onScrollStateChanged(i);
        if (mScrollListener != null)
        {
            mScrollListener.onScrollStateChanged(this, i);
        }
        if (mScrollListeners != null)
        {
            for (int j = mScrollListeners.size() - 1; j >= 0; j--)
            {
                ((OnScrollListener)mScrollListeners.get(j)).onScrollStateChanged(this, i);
            }

        }
    }

    void dispatchOnScrolled(int i, int j)
    {
        mDispatchScrollCounter = mDispatchScrollCounter + 1;
        int k = getScrollX();
        int i1 = getScrollY();
        onScrollChanged(k, i1, k, i1);
        onScrolled(i, j);
        if (mScrollListener != null)
        {
            mScrollListener.onScrolled(this, i, j);
        }
        if (mScrollListeners != null)
        {
            for (int l = mScrollListeners.size() - 1; l >= 0; l--)
            {
                ((OnScrollListener)mScrollListeners.get(l)).onScrolled(this, i, j);
            }

        }
        mDispatchScrollCounter = mDispatchScrollCounter - 1;
    }

    void dispatchPendingImportantForAccessibilityChanges()
    {
        int i = mPendingAccessibilityImportanceChange.size() - 1;
        while (i >= 0) 
        {
            ViewHolder viewholder = (ViewHolder)mPendingAccessibilityImportanceChange.get(i);
            if (viewholder.itemView.getParent() == this && !viewholder.shouldIgnore())
            {
                int j = viewholder.mPendingAccessibilityState;
                if (j != -1)
                {
                    ViewCompat.setImportantForAccessibility(viewholder.itemView, j);
                    viewholder.mPendingAccessibilityState = -1;
                }
            }
            i--;
        }
        mPendingAccessibilityImportanceChange.clear();
    }

    protected void dispatchRestoreInstanceState(SparseArray sparsearray)
    {
        dispatchThawSelfOnly(sparsearray);
    }

    protected void dispatchSaveInstanceState(SparseArray sparsearray)
    {
        dispatchFreezeSelfOnly(sparsearray);
    }

    public void draw(Canvas canvas)
    {
        boolean flag = true;
        super.draw(canvas);
        int k = mItemDecorations.size();
        for (int i = 0; i < k; i++)
        {
            ((ItemDecoration)mItemDecorations.get(i)).onDrawOver(canvas, this, mState);
        }

        int j = 0;
        k = j;
        if (mLeftGlow != null)
        {
            k = j;
            if (!mLeftGlow.isFinished())
            {
                int l = canvas.save();
                int i1;
                if (mClipToPadding)
                {
                    j = getPaddingBottom();
                } else
                {
                    j = 0;
                }
                canvas.rotate(270F);
                canvas.translate(-getHeight() + j, 0.0F);
                if (mLeftGlow != null && mLeftGlow.draw(canvas))
                {
                    k = 1;
                } else
                {
                    k = 0;
                }
                canvas.restoreToCount(l);
            }
        }
        j = k;
        if (mTopGlow != null)
        {
            j = k;
            if (!mTopGlow.isFinished())
            {
                l = canvas.save();
                if (mClipToPadding)
                {
                    canvas.translate(getPaddingLeft(), getPaddingTop());
                }
                if (mTopGlow != null && mTopGlow.draw(canvas))
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
                j = k | j;
                canvas.restoreToCount(l);
            }
        }
        k = j;
        if (mRightGlow != null)
        {
            k = j;
            if (!mRightGlow.isFinished())
            {
                l = canvas.save();
                i1 = getWidth();
                if (mClipToPadding)
                {
                    k = getPaddingTop();
                } else
                {
                    k = 0;
                }
                canvas.rotate(90F);
                canvas.translate(-k, -i1);
                if (mRightGlow != null && mRightGlow.draw(canvas))
                {
                    k = 1;
                } else
                {
                    k = 0;
                }
                k = j | k;
                canvas.restoreToCount(l);
            }
        }
        j = k;
        if (mBottomGlow != null)
        {
            j = k;
            if (!mBottomGlow.isFinished())
            {
                l = canvas.save();
                canvas.rotate(180F);
                if (mClipToPadding)
                {
                    canvas.translate(-getWidth() + getPaddingRight(), -getHeight() + getPaddingBottom());
                } else
                {
                    canvas.translate(-getWidth(), -getHeight());
                }
                if (mBottomGlow != null && mBottomGlow.draw(canvas))
                {
                    j = ((flag) ? 1 : 0);
                } else
                {
                    j = 0;
                }
                j = k | j;
                canvas.restoreToCount(l);
            }
        }
        k = j;
        if (j == 0)
        {
            k = j;
            if (mItemAnimator != null)
            {
                k = j;
                if (mItemDecorations.size() > 0)
                {
                    k = j;
                    if (mItemAnimator.isRunning())
                    {
                        k = 1;
                    }
                }
            }
        }
        if (k != 0)
        {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public boolean drawChild(Canvas canvas, View view, long l)
    {
        return super.drawChild(canvas, view, l);
    }

    void eatRequestLayout()
    {
        mEatRequestLayout = mEatRequestLayout + 1;
        if (mEatRequestLayout == 1 && !mLayoutFrozen)
        {
            mLayoutRequestEaten = false;
        }
    }

    void ensureBottomGlow()
    {
        if (mBottomGlow != null)
        {
            return;
        }
        mBottomGlow = new EdgeEffect(getContext());
        if (mClipToPadding)
        {
            mBottomGlow.setSize(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
            return;
        } else
        {
            mBottomGlow.setSize(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
    }

    void ensureLeftGlow()
    {
        if (mLeftGlow != null)
        {
            return;
        }
        mLeftGlow = new EdgeEffect(getContext());
        if (mClipToPadding)
        {
            mLeftGlow.setSize(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
            return;
        } else
        {
            mLeftGlow.setSize(getMeasuredHeight(), getMeasuredWidth());
            return;
        }
    }

    void ensureRightGlow()
    {
        if (mRightGlow != null)
        {
            return;
        }
        mRightGlow = new EdgeEffect(getContext());
        if (mClipToPadding)
        {
            mRightGlow.setSize(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
            return;
        } else
        {
            mRightGlow.setSize(getMeasuredHeight(), getMeasuredWidth());
            return;
        }
    }

    void ensureTopGlow()
    {
        if (mTopGlow != null)
        {
            return;
        }
        mTopGlow = new EdgeEffect(getContext());
        if (mClipToPadding)
        {
            mTopGlow.setSize(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
            return;
        } else
        {
            mTopGlow.setSize(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
    }

    public View findChildViewUnder(float f, float f1)
    {
        for (int i = mChildHelper.getChildCount() - 1; i >= 0; i--)
        {
            View view = mChildHelper.getChildAt(i);
            float f2 = view.getTranslationX();
            float f3 = view.getTranslationY();
            if (f >= (float)view.getLeft() + f2 && f <= (float)view.getRight() + f2 && f1 >= (float)view.getTop() + f3 && f1 <= (float)view.getBottom() + f3)
            {
                return view;
            }
        }

        return null;
    }

    public View findContainingItemView(View view)
    {
        ViewParent viewparent = view.getParent();
        View view1 = view;
        for (view = viewparent; view != null && view != this && (view instanceof View); view = view1.getParent())
        {
            view1 = (View)view;
        }

        if (view == this)
        {
            return view1;
        } else
        {
            return null;
        }
    }

    public ViewHolder findContainingViewHolder(View view)
    {
        view = findContainingItemView(view);
        if (view == null)
        {
            return null;
        } else
        {
            return getChildViewHolder(view);
        }
    }

    public ViewHolder findViewHolderForAdapterPosition(int i)
    {
        if (!mDataSetHasChangedAfterLayout) goto _L2; else goto _L1
_L1:
        ViewHolder viewholder1 = null;
_L4:
        return viewholder1;
_L2:
        int k = mChildHelper.getUnfilteredChildCount();
        viewholder1 = null;
        for (int j = 0; j < k;)
        {
            ViewHolder viewholder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(j));
            ViewHolder viewholder2 = viewholder1;
            if (viewholder != null)
            {
                viewholder2 = viewholder1;
                if (!viewholder.isRemoved())
                {
                    viewholder2 = viewholder1;
                    if (getAdapterPositionFor(viewholder) == i)
                    {
                        viewholder1 = viewholder;
                        if (!mChildHelper.isHidden(viewholder.itemView))
                        {
                            continue; /* Loop/switch isn't completed */
                        }
                        viewholder2 = viewholder;
                    }
                }
            }
            j++;
            viewholder1 = viewholder2;
        }

        return viewholder1;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public ViewHolder findViewHolderForItemId(long l)
    {
        if (mAdapter != null && mAdapter.hasStableIds()) goto _L2; else goto _L1
_L1:
        ViewHolder viewholder1 = null;
_L4:
        return viewholder1;
_L2:
        int j = mChildHelper.getUnfilteredChildCount();
        viewholder1 = null;
        for (int i = 0; i < j;)
        {
            ViewHolder viewholder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
            ViewHolder viewholder2 = viewholder1;
            if (viewholder != null)
            {
                viewholder2 = viewholder1;
                if (!viewholder.isRemoved())
                {
                    viewholder2 = viewholder1;
                    if (viewholder.getItemId() == l)
                    {
                        viewholder1 = viewholder;
                        if (!mChildHelper.isHidden(viewholder.itemView))
                        {
                            continue; /* Loop/switch isn't completed */
                        }
                        viewholder2 = viewholder;
                    }
                }
            }
            i++;
            viewholder1 = viewholder2;
        }

        return viewholder1;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public ViewHolder findViewHolderForLayoutPosition(int i)
    {
        return findViewHolderForPosition(i, false);
    }

    public ViewHolder findViewHolderForPosition(int i)
    {
        return findViewHolderForPosition(i, false);
    }

    ViewHolder findViewHolderForPosition(int i, boolean flag)
    {
        ViewHolder viewholder;
        int j;
        int k;
        k = mChildHelper.getUnfilteredChildCount();
        viewholder = null;
        j = 0;
_L8:
        ViewHolder viewholder1;
        ViewHolder viewholder2;
        if (j >= k)
        {
            break MISSING_BLOCK_LABEL_119;
        }
        viewholder1 = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(j));
        viewholder2 = viewholder;
        if (viewholder1 == null) goto _L2; else goto _L1
_L1:
        viewholder2 = viewholder;
        if (viewholder1.isRemoved()) goto _L2; else goto _L3
_L3:
        if (!flag) goto _L5; else goto _L4
_L4:
        if (viewholder1.mPosition == i) goto _L7; else goto _L6
_L6:
        viewholder2 = viewholder;
_L2:
        j++;
        viewholder = viewholder2;
          goto _L8
_L5:
        viewholder2 = viewholder;
        if (viewholder1.getLayoutPosition() != i) goto _L2; else goto _L7
_L7:
        viewholder = viewholder1;
        if (!mChildHelper.isHidden(viewholder1.itemView))
        {
            break MISSING_BLOCK_LABEL_119;
        }
        viewholder2 = viewholder1;
          goto _L2
        return viewholder;
    }

    public boolean fling(int i, int j)
    {
        if (mLayout != null) goto _L2; else goto _L1
_L1:
        Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
_L4:
        return false;
_L2:
        int k;
        boolean flag;
        boolean flag1;
label0:
        {
            if (mLayoutFrozen)
            {
                continue; /* Loop/switch isn't completed */
            }
            flag = mLayout.canScrollHorizontally();
            flag1 = mLayout.canScrollVertically();
            if (flag)
            {
                k = i;
                if (Math.abs(i) >= mMinFlingVelocity)
                {
                    break label0;
                }
            }
            k = 0;
        }
        if (flag1)
        {
            i = j;
            if (Math.abs(j) >= mMinFlingVelocity)
            {
                continue; /* Loop/switch isn't completed */
            }
        }
        i = 0;
        if (k == 0 && i == 0 || dispatchNestedPreFling(k, i)) goto _L4; else goto _L3
_L3:
        if (flag || flag1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        dispatchNestedFling(k, i, flag);
        if (mOnFlingListener != null && mOnFlingListener.onFling(k, i))
        {
            return true;
        }
        if (flag)
        {
            j = Math.max(-mMaxFlingVelocity, Math.min(k, mMaxFlingVelocity));
            i = Math.max(-mMaxFlingVelocity, Math.min(i, mMaxFlingVelocity));
            mViewFlinger.fling(j, i);
            return true;
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    public View focusSearch(View view, int i)
    {
        Object obj = mLayout.onInterceptFocusSearch(view, i);
        if (obj != null)
        {
            return ((View) (obj));
        }
        int j;
        int k;
        if (mAdapter != null && mLayout != null && !isComputingLayout() && !mLayoutFrozen)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        obj = FocusFinder.getInstance();
        if (j != 0 && (i == 2 || i == 1))
        {
            boolean flag = false;
            j = i;
            char c;
            if (mLayout.canScrollVertically())
            {
                boolean flag1;
                if (i == 2)
                {
                    c = '\202';
                } else
                {
                    c = '!';
                }
                if (((FocusFinder) (obj)).findNextFocus(this, view, c) == null)
                {
                    k = 1;
                } else
                {
                    k = 0;
                }
                flag = k;
                j = i;
                if (FORCE_ABS_FOCUS_SEARCH_DIRECTION)
                {
                    j = c;
                    flag = k;
                }
            }
            flag1 = flag;
            k = j;
            if (!flag)
            {
                flag1 = flag;
                k = j;
                if (mLayout.canScrollHorizontally())
                {
                    if (mLayout.getLayoutDirection() == 1)
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    if (j == 2)
                    {
                        c = '\001';
                    } else
                    {
                        c = '\0';
                    }
                    if ((c ^ i) != 0)
                    {
                        i = 66;
                    } else
                    {
                        i = 17;
                    }
                    if (((FocusFinder) (obj)).findNextFocus(this, view, i) == null)
                    {
                        c = '\001';
                    } else
                    {
                        c = '\0';
                    }
                    flag1 = c;
                    k = j;
                    if (FORCE_ABS_FOCUS_SEARCH_DIRECTION)
                    {
                        k = i;
                        flag1 = c;
                    }
                }
            }
            if (flag1)
            {
                consumePendingUpdateOperations();
                if (findContainingItemView(view) == null)
                {
                    return null;
                }
                eatRequestLayout();
                mLayout.onFocusSearchFailed(view, k, mRecycler, mState);
                resumeRequestLayout(false);
            }
            obj = ((FocusFinder) (obj)).findNextFocus(this, view, k);
        } else
        {
            View view1 = ((FocusFinder) (obj)).findNextFocus(this, view, i);
            obj = view1;
            k = i;
            if (view1 == null)
            {
                obj = view1;
                k = i;
                if (j != 0)
                {
                    consumePendingUpdateOperations();
                    if (findContainingItemView(view) == null)
                    {
                        return null;
                    }
                    eatRequestLayout();
                    obj = mLayout.onFocusSearchFailed(view, i, mRecycler, mState);
                    resumeRequestLayout(false);
                    k = i;
                }
            }
        }
        if (obj != null && !((View) (obj)).hasFocusable())
        {
            if (getFocusedChild() == null)
            {
                return super.focusSearch(view, k);
            } else
            {
                requestChildOnScreen(((View) (obj)), null);
                return view;
            }
        }
        if (!isPreferredNextFocus(view, ((View) (obj)), k))
        {
            obj = super.focusSearch(view, k);
        }
        return ((View) (obj));
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        if (mLayout == null)
        {
            throw new IllegalStateException("RecyclerView has no LayoutManager");
        } else
        {
            return mLayout.generateDefaultLayoutParams();
        }
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        if (mLayout == null)
        {
            throw new IllegalStateException("RecyclerView has no LayoutManager");
        } else
        {
            return mLayout.generateLayoutParams(getContext(), attributeset);
        }
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        if (mLayout == null)
        {
            throw new IllegalStateException("RecyclerView has no LayoutManager");
        } else
        {
            return mLayout.generateLayoutParams(layoutparams);
        }
    }

    public Adapter getAdapter()
    {
        return mAdapter;
    }

    int getAdapterPositionFor(ViewHolder viewholder)
    {
        if (viewholder.hasAnyOfTheFlags(524) || !viewholder.isBound())
        {
            return -1;
        } else
        {
            return mAdapterHelper.applyPendingUpdatesToPosition(viewholder.mPosition);
        }
    }

    public int getBaseline()
    {
        if (mLayout != null)
        {
            return mLayout.getBaseline();
        } else
        {
            return super.getBaseline();
        }
    }

    long getChangedHolderKey(ViewHolder viewholder)
    {
        if (mAdapter.hasStableIds())
        {
            return viewholder.getItemId();
        } else
        {
            return (long)viewholder.mPosition;
        }
    }

    public int getChildAdapterPosition(View view)
    {
        view = getChildViewHolderInt(view);
        if (view != null)
        {
            return view.getAdapterPosition();
        } else
        {
            return -1;
        }
    }

    protected int getChildDrawingOrder(int i, int j)
    {
        if (mChildDrawingOrderCallback == null)
        {
            return super.getChildDrawingOrder(i, j);
        } else
        {
            return mChildDrawingOrderCallback.onGetChildDrawingOrder(i, j);
        }
    }

    public long getChildItemId(View view)
    {
        if (mAdapter != null && mAdapter.hasStableIds())
        {
            if ((view = getChildViewHolderInt(view)) != null)
            {
                return view.getItemId();
            }
        }
        return -1L;
    }

    public int getChildLayoutPosition(View view)
    {
        view = getChildViewHolderInt(view);
        if (view != null)
        {
            return view.getLayoutPosition();
        } else
        {
            return -1;
        }
    }

    public int getChildPosition(View view)
    {
        return getChildAdapterPosition(view);
    }

    public ViewHolder getChildViewHolder(View view)
    {
        ViewParent viewparent = view.getParent();
        if (viewparent != null && viewparent != this)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("View ").append(view).append(" is not a direct child of ").append(this).toString());
        } else
        {
            return getChildViewHolderInt(view);
        }
    }

    public boolean getClipToPadding()
    {
        return mClipToPadding;
    }

    public RecyclerViewAccessibilityDelegate getCompatAccessibilityDelegate()
    {
        return mAccessibilityDelegate;
    }

    public void getDecoratedBoundsWithMargins(View view, Rect rect)
    {
        getDecoratedBoundsWithMarginsInt(view, rect);
    }

    public ItemAnimator getItemAnimator()
    {
        return mItemAnimator;
    }

    Rect getItemDecorInsetsForChild(View view)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if (!layoutparams.mInsetsDirty)
        {
            return layoutparams.mDecorInsets;
        }
        if (mState.isPreLayout() && (layoutparams.isItemChanged() || layoutparams.isViewInvalid()))
        {
            return layoutparams.mDecorInsets;
        }
        Rect rect = layoutparams.mDecorInsets;
        rect.set(0, 0, 0, 0);
        int j = mItemDecorations.size();
        for (int i = 0; i < j; i++)
        {
            mTempRect.set(0, 0, 0, 0);
            ((ItemDecoration)mItemDecorations.get(i)).getItemOffsets(mTempRect, view, this, mState);
            rect.left = rect.left + mTempRect.left;
            rect.top = rect.top + mTempRect.top;
            rect.right = rect.right + mTempRect.right;
            rect.bottom = rect.bottom + mTempRect.bottom;
        }

        layoutparams.mInsetsDirty = false;
        return rect;
    }

    public ItemDecoration getItemDecorationAt(int i)
    {
        if (i < 0 || i > mItemDecorations.size())
        {
            return null;
        } else
        {
            return (ItemDecoration)mItemDecorations.get(i);
        }
    }

    public LayoutManager getLayoutManager()
    {
        return mLayout;
    }

    public int getMaxFlingVelocity()
    {
        return mMaxFlingVelocity;
    }

    public int getMinFlingVelocity()
    {
        return mMinFlingVelocity;
    }

    long getNanoTime()
    {
        if (ALLOW_THREAD_GAP_WORK)
        {
            return System.nanoTime();
        } else
        {
            return 0L;
        }
    }

    public OnFlingListener getOnFlingListener()
    {
        return mOnFlingListener;
    }

    public boolean getPreserveFocusAfterLayout()
    {
        return mPreserveFocusAfterLayout;
    }

    public RecycledViewPool getRecycledViewPool()
    {
        return mRecycler.getRecycledViewPool();
    }

    public int getScrollState()
    {
        return mScrollState;
    }

    public boolean hasFixedSize()
    {
        return mHasFixedSize;
    }

    public boolean hasNestedScrollingParent()
    {
        return getScrollingChildHelper().hasNestedScrollingParent();
    }

    public boolean hasPendingAdapterUpdates()
    {
        return !mFirstLayoutComplete || mDataSetHasChangedAfterLayout || mAdapterHelper.hasPendingUpdates();
    }

    void initAdapterManager()
    {
        mAdapterHelper = new AdapterHelper(new AdapterHelper.Callback() {

            final RecyclerView this$0;

            void dispatchUpdate(AdapterHelper.UpdateOp updateop)
            {
                switch (updateop.cmd)
                {
                case 3: // '\003'
                case 5: // '\005'
                case 6: // '\006'
                case 7: // '\007'
                default:
                    return;

                case 1: // '\001'
                    mLayout.onItemsAdded(RecyclerView.this, updateop.positionStart, updateop.itemCount);
                    return;

                case 2: // '\002'
                    mLayout.onItemsRemoved(RecyclerView.this, updateop.positionStart, updateop.itemCount);
                    return;

                case 4: // '\004'
                    mLayout.onItemsUpdated(RecyclerView.this, updateop.positionStart, updateop.itemCount, updateop.payload);
                    return;

                case 8: // '\b'
                    mLayout.onItemsMoved(RecyclerView.this, updateop.positionStart, updateop.itemCount, 1);
                    break;
                }
            }

            public ViewHolder findViewHolder(int i)
            {
                ViewHolder viewholder1 = findViewHolderForPosition(i, true);
                ViewHolder viewholder;
                if (viewholder1 == null)
                {
                    viewholder = null;
                } else
                {
                    viewholder = viewholder1;
                    if (mChildHelper.isHidden(viewholder1.itemView))
                    {
                        return null;
                    }
                }
                return viewholder;
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
                State state = mState;
                state.mDeletedInvisibleItemCountSincePreviousLayout = state.mDeletedInvisibleItemCountSincePreviousLayout + j;
            }

            public void offsetPositionsForRemovingLaidOutOrNewView(int i, int j)
            {
                offsetPositionRecordsForRemove(i, j, false);
                mItemsAddedOrRemoved = true;
            }

            public void onDispatchFirstPass(AdapterHelper.UpdateOp updateop)
            {
                dispatchUpdate(updateop);
            }

            public void onDispatchSecondPass(AdapterHelper.UpdateOp updateop)
            {
                dispatchUpdate(updateop);
            }

            
            {
                this$0 = RecyclerView.this;
                super();
            }
        });
    }

    void initFastScroller(StateListDrawable statelistdrawable, Drawable drawable, StateListDrawable statelistdrawable1, Drawable drawable1)
    {
        if (statelistdrawable == null || drawable == null || statelistdrawable1 == null || drawable1 == null)
        {
            throw new IllegalArgumentException("Trying to set fast scroller without both required drawables.");
        } else
        {
            Resources resources = getContext().getResources();
            new FastScroller(this, statelistdrawable, drawable, statelistdrawable1, drawable1, resources.getDimensionPixelSize(android.support.v7.recyclerview.R.dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(android.support.v7.recyclerview.R.dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(android.support.v7.recyclerview.R.dimen.fastscroll_margin));
            return;
        }
    }

    void invalidateGlows()
    {
        mBottomGlow = null;
        mTopGlow = null;
        mRightGlow = null;
        mLeftGlow = null;
    }

    public void invalidateItemDecorations()
    {
        if (mItemDecorations.size() == 0)
        {
            return;
        }
        if (mLayout != null)
        {
            mLayout.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll or layout");
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    boolean isAccessibilityEnabled()
    {
        return mAccessibilityManager != null && mAccessibilityManager.isEnabled();
    }

    public boolean isAnimating()
    {
        return mItemAnimator != null && mItemAnimator.isRunning();
    }

    public boolean isAttachedToWindow()
    {
        return mIsAttached;
    }

    public boolean isComputingLayout()
    {
        return mLayoutOrScrollCounter > 0;
    }

    public boolean isLayoutFrozen()
    {
        return mLayoutFrozen;
    }

    public boolean isNestedScrollingEnabled()
    {
        return getScrollingChildHelper().isNestedScrollingEnabled();
    }

    void jumpToPositionForSmoothScroller(int i)
    {
        if (mLayout == null)
        {
            return;
        } else
        {
            mLayout.scrollToPosition(i);
            awakenScrollBars();
            return;
        }
    }

    void markItemDecorInsetsDirty()
    {
        int j = mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < j; i++)
        {
            ((LayoutParams)mChildHelper.getUnfilteredChildAt(i).getLayoutParams()).mInsetsDirty = true;
        }

        mRecycler.markItemDecorInsetsDirty();
    }

    void markKnownViewsInvalid()
    {
        int j = mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < j; i++)
        {
            ViewHolder viewholder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
            if (viewholder != null && !viewholder.shouldIgnore())
            {
                viewholder.addFlags(6);
            }
        }

        markItemDecorInsetsDirty();
        mRecycler.markKnownViewsInvalid();
    }

    public void offsetChildrenHorizontal(int i)
    {
        int k = mChildHelper.getChildCount();
        for (int j = 0; j < k; j++)
        {
            mChildHelper.getChildAt(j).offsetLeftAndRight(i);
        }

    }

    public void offsetChildrenVertical(int i)
    {
        int k = mChildHelper.getChildCount();
        for (int j = 0; j < k; j++)
        {
            mChildHelper.getChildAt(j).offsetTopAndBottom(i);
        }

    }

    void offsetPositionRecordsForInsert(int i, int j)
    {
        int l = mChildHelper.getUnfilteredChildCount();
        for (int k = 0; k < l; k++)
        {
            ViewHolder viewholder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(k));
            if (viewholder != null && !viewholder.shouldIgnore() && viewholder.mPosition >= i)
            {
                viewholder.offsetPosition(j, false);
                mState.mStructureChanged = true;
            }
        }

        mRecycler.offsetPositionRecordsForInsert(i, j);
        requestLayout();
    }

    void offsetPositionRecordsForMove(int i, int j)
    {
        int j1 = mChildHelper.getUnfilteredChildCount();
        int k;
        byte byte0;
        int l;
        int i1;
        if (i < j)
        {
            l = i;
            k = j;
            byte0 = -1;
        } else
        {
            l = j;
            k = i;
            byte0 = 1;
        }
        i1 = 0;
        while (i1 < j1) 
        {
            ViewHolder viewholder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i1));
            if (viewholder != null && viewholder.mPosition >= l && viewholder.mPosition <= k)
            {
                if (viewholder.mPosition == i)
                {
                    viewholder.offsetPosition(j - i, false);
                } else
                {
                    viewholder.offsetPosition(byte0, false);
                }
                mState.mStructureChanged = true;
            }
            i1++;
        }
        mRecycler.offsetPositionRecordsForMove(i, j);
        requestLayout();
    }

    void offsetPositionRecordsForRemove(int i, int j, boolean flag)
    {
        int l = mChildHelper.getUnfilteredChildCount();
        int k = 0;
        while (k < l) 
        {
            ViewHolder viewholder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(k));
            if (viewholder != null && !viewholder.shouldIgnore())
            {
                if (viewholder.mPosition >= i + j)
                {
                    viewholder.offsetPosition(-j, flag);
                    mState.mStructureChanged = true;
                } else
                if (viewholder.mPosition >= i)
                {
                    viewholder.flagRemovedAndOffsetPosition(i - 1, -j, flag);
                    mState.mStructureChanged = true;
                }
            }
            k++;
        }
        mRecycler.offsetPositionRecordsForRemove(i, j, flag);
        requestLayout();
    }

    protected void onAttachedToWindow()
    {
        boolean flag = true;
        super.onAttachedToWindow();
        mLayoutOrScrollCounter = 0;
        mIsAttached = true;
        if (!mFirstLayoutComplete || isLayoutRequested())
        {
            flag = false;
        }
        mFirstLayoutComplete = flag;
        if (mLayout != null)
        {
            mLayout.dispatchAttachedToWindow(this);
        }
        mPostedAnimatorRunner = false;
        if (ALLOW_THREAD_GAP_WORK)
        {
            mGapWorker = (GapWorker)GapWorker.sGapWorker.get();
            if (mGapWorker == null)
            {
                mGapWorker = new GapWorker();
                Display display = ViewCompat.getDisplay(this);
                float f1 = 60F;
                float f = f1;
                if (!isInEditMode())
                {
                    f = f1;
                    if (display != null)
                    {
                        float f2 = display.getRefreshRate();
                        f = f1;
                        if (f2 >= 30F)
                        {
                            f = f2;
                        }
                    }
                }
                mGapWorker.mFrameIntervalNs = (long)(1E+09F / f);
                GapWorker.sGapWorker.set(mGapWorker);
            }
            mGapWorker.add(this);
        }
    }

    public void onChildAttachedToWindow(View view)
    {
    }

    public void onChildDetachedFromWindow(View view)
    {
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if (mItemAnimator != null)
        {
            mItemAnimator.endAnimations();
        }
        stopScroll();
        mIsAttached = false;
        if (mLayout != null)
        {
            mLayout.dispatchDetachedFromWindow(this, mRecycler);
        }
        mPendingAccessibilityImportanceChange.clear();
        removeCallbacks(mItemAnimatorRunner);
        mViewInfoStore.onDetach();
        if (ALLOW_THREAD_GAP_WORK)
        {
            mGapWorker.remove(this);
            mGapWorker = null;
        }
    }

    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        int j = mItemDecorations.size();
        for (int i = 0; i < j; i++)
        {
            ((ItemDecoration)mItemDecorations.get(i)).onDraw(canvas, this, mState);
        }

    }

    void onEnterLayoutOrScroll()
    {
        mLayoutOrScrollCounter = mLayoutOrScrollCounter + 1;
    }

    void onExitLayoutOrScroll()
    {
        mLayoutOrScrollCounter = mLayoutOrScrollCounter - 1;
        if (mLayoutOrScrollCounter < 1)
        {
            mLayoutOrScrollCounter = 0;
            dispatchContentChangedIfNecessary();
            dispatchPendingImportantForAccessibilityChanges();
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionevent)
    {
        if (mLayout != null && !mLayoutFrozen && (motionevent.getSource() & 2) != 0 && motionevent.getAction() == 8)
        {
            float f;
            float f1;
            if (mLayout.canScrollVertically())
            {
                f = -motionevent.getAxisValue(9);
            } else
            {
                f = 0.0F;
            }
            if (mLayout.canScrollHorizontally())
            {
                f1 = motionevent.getAxisValue(10);
            } else
            {
                f1 = 0.0F;
            }
            if (f != 0.0F || f1 != 0.0F)
            {
                float f2 = getScrollFactor();
                scrollByInternal((int)(f1 * f2), (int)(f * f2), motionevent);
                return false;
            }
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        int i;
        int k;
        boolean flag;
        boolean flag1;
        if (mLayoutFrozen)
        {
            return false;
        }
        if (dispatchOnItemTouchIntercept(motionevent))
        {
            cancelTouch();
            return true;
        }
        if (mLayout == null)
        {
            return false;
        }
        flag = mLayout.canScrollHorizontally();
        flag1 = mLayout.canScrollVertically();
        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(motionevent);
        k = motionevent.getActionMasked();
        i = motionevent.getActionIndex();
        k;
        JVM INSTR tableswitch 0 6: default 124
    //                   0 134
    //                   1 549
    //                   2 319
    //                   3 563
    //                   4 124
    //                   5 265
    //                   6 541;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L6 _L7
_L1:
        break; /* Loop/switch isn't completed */
_L5:
        break MISSING_BLOCK_LABEL_563;
_L8:
        return mScrollState == 1;
_L2:
        if (mIgnoreMotionEventTillDown)
        {
            mIgnoreMotionEventTillDown = false;
        }
        mScrollPointerId = motionevent.getPointerId(0);
        i = (int)(motionevent.getX() + 0.5F);
        mLastTouchX = i;
        mInitialTouchX = i;
        i = (int)(motionevent.getY() + 0.5F);
        mLastTouchY = i;
        mInitialTouchY = i;
        if (mScrollState == 2)
        {
            getParent().requestDisallowInterceptTouchEvent(true);
            setScrollState(1);
        }
        motionevent = mNestedOffsets;
        mNestedOffsets[1] = 0;
        motionevent[0] = 0;
        i = 0;
        if (flag)
        {
            i = false | true;
        }
        k = i;
        if (flag1)
        {
            k = i | 2;
        }
        startNestedScroll(k);
          goto _L8
_L6:
        mScrollPointerId = motionevent.getPointerId(i);
        int l = (int)(motionevent.getX(i) + 0.5F);
        mLastTouchX = l;
        mInitialTouchX = l;
        i = (int)(motionevent.getY(i) + 0.5F);
        mLastTouchY = i;
        mInitialTouchY = i;
          goto _L8
_L4:
        int i1 = motionevent.findPointerIndex(mScrollPointerId);
        if (i1 < 0)
        {
            Log.e("RecyclerView", (new StringBuilder()).append("Error processing scroll; pointer index for id ").append(mScrollPointerId).append(" not found. Did any MotionEvents get skipped?").toString());
            return false;
        }
        int j = (int)(motionevent.getX(i1) + 0.5F);
        i1 = (int)(motionevent.getY(i1) + 0.5F);
        if (mScrollState != 1)
        {
            int l1 = j - mInitialTouchX;
            int k1 = i1 - mInitialTouchY;
            i1 = 0;
            j = i1;
            if (flag)
            {
                j = i1;
                if (Math.abs(l1) > mTouchSlop)
                {
                    int j1 = mInitialTouchX;
                    int i2 = mTouchSlop;
                    if (l1 < 0)
                    {
                        j = -1;
                    } else
                    {
                        j = 1;
                    }
                    mLastTouchX = j * i2 + j1;
                    j = 1;
                }
            }
            j1 = j;
            if (flag1)
            {
                j1 = j;
                if (Math.abs(k1) > mTouchSlop)
                {
                    j1 = mInitialTouchY;
                    l1 = mTouchSlop;
                    if (k1 < 0)
                    {
                        j = -1;
                    } else
                    {
                        j = 1;
                    }
                    mLastTouchY = j * l1 + j1;
                    j1 = 1;
                }
            }
            if (j1 != 0)
            {
                setScrollState(1);
            }
        }
          goto _L8
_L7:
        onPointerUp(motionevent);
          goto _L8
_L3:
        mVelocityTracker.clear();
        stopNestedScroll();
          goto _L8
        cancelTouch();
          goto _L8
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        TraceCompat.beginSection("RV OnLayout");
        dispatchLayout();
        TraceCompat.endSection();
        mFirstLayoutComplete = true;
    }

    protected void onMeasure(int i, int j)
    {
        boolean flag1 = false;
        if (mLayout == null)
        {
            defaultOnMeasure(i, j);
        } else
        if (mLayout.mAutoMeasure)
        {
            int k = android.view.View.MeasureSpec.getMode(i);
            int l = android.view.View.MeasureSpec.getMode(j);
            boolean flag = flag1;
            if (k == 0x40000000)
            {
                flag = flag1;
                if (l == 0x40000000)
                {
                    flag = true;
                }
            }
            mLayout.onMeasure(mRecycler, mState, i, j);
            if (!flag && mAdapter != null)
            {
                if (mState.mLayoutStep == 1)
                {
                    dispatchLayoutStep1();
                }
                mLayout.setMeasureSpecs(i, j);
                mState.mIsMeasuring = true;
                dispatchLayoutStep2();
                mLayout.setMeasuredDimensionFromChildren(i, j);
                if (mLayout.shouldMeasureTwice())
                {
                    mLayout.setMeasureSpecs(android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0x40000000));
                    mState.mIsMeasuring = true;
                    dispatchLayoutStep2();
                    mLayout.setMeasuredDimensionFromChildren(i, j);
                    return;
                }
            }
        } else
        {
            if (mHasFixedSize)
            {
                mLayout.onMeasure(mRecycler, mState, i, j);
                return;
            }
            if (mAdapterUpdateDuringMeasure)
            {
                eatRequestLayout();
                onEnterLayoutOrScroll();
                processAdapterUpdatesAndSetAnimationFlags();
                onExitLayoutOrScroll();
                if (mState.mRunPredictiveAnimations)
                {
                    mState.mInPreLayout = true;
                } else
                {
                    mAdapterHelper.consumeUpdatesInOnePass();
                    mState.mInPreLayout = false;
                }
                mAdapterUpdateDuringMeasure = false;
                resumeRequestLayout(false);
            }
            if (mAdapter != null)
            {
                mState.mItemCount = mAdapter.getItemCount();
            } else
            {
                mState.mItemCount = 0;
            }
            eatRequestLayout();
            mLayout.onMeasure(mRecycler, mState, i, j);
            resumeRequestLayout(false);
            mState.mInPreLayout = false;
            return;
        }
    }

    protected boolean onRequestFocusInDescendants(int i, Rect rect)
    {
        if (isComputingLayout())
        {
            return false;
        } else
        {
            return super.onRequestFocusInDescendants(i, rect);
        }
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        if (!(parcelable instanceof SavedState))
        {
            super.onRestoreInstanceState(parcelable);
        } else
        {
            mPendingSavedState = (SavedState)parcelable;
            super.onRestoreInstanceState(mPendingSavedState.getSuperState());
            if (mLayout != null && mPendingSavedState.mLayoutState != null)
            {
                mLayout.onRestoreInstanceState(mPendingSavedState.mLayoutState);
                return;
            }
        }
    }

    protected Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        if (mPendingSavedState != null)
        {
            savedstate.copyFrom(mPendingSavedState);
            return savedstate;
        }
        if (mLayout != null)
        {
            savedstate.mLayoutState = mLayout.onSaveInstanceState();
            return savedstate;
        } else
        {
            savedstate.mLayoutState = null;
            return savedstate;
        }
    }

    public void onScrollStateChanged(int i)
    {
    }

    public void onScrolled(int i, int j)
    {
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        if (i != k || j != l)
        {
            invalidateGlows();
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        MotionEvent motionevent1;
        int i;
        int j;
        int l;
        boolean flag;
        boolean flag1;
        boolean flag2;
        if (mLayoutFrozen || mIgnoreMotionEventTillDown)
        {
            return false;
        }
        if (dispatchOnItemTouch(motionevent))
        {
            cancelTouch();
            return true;
        }
        if (mLayout == null)
        {
            return false;
        }
        flag1 = mLayout.canScrollHorizontally();
        flag2 = mLayout.canScrollVertically();
        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }
        flag = false;
        motionevent1 = MotionEvent.obtain(motionevent);
        l = motionevent.getActionMasked();
        j = motionevent.getActionIndex();
        if (l == 0)
        {
            int ai[] = mNestedOffsets;
            mNestedOffsets[1] = 0;
            ai[0] = 0;
        }
        motionevent1.offsetLocation(mNestedOffsets[0], mNestedOffsets[1]);
        i = ((flag) ? 1 : 0);
        l;
        JVM INSTR tableswitch 0 6: default 184
    //                   0 209
    //                   1 889
    //                   2 373
    //                   3 997
    //                   4 188
    //                   5 306
    //                   6 877;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L6:
        break; /* Loop/switch isn't completed */
_L1:
        i = ((flag) ? 1 : 0);
_L10:
        if (i == 0)
        {
            mVelocityTracker.addMovement(motionevent1);
        }
        motionevent1.recycle();
        return true;
_L2:
        mScrollPointerId = motionevent.getPointerId(0);
        i = (int)(motionevent.getX() + 0.5F);
        mLastTouchX = i;
        mInitialTouchX = i;
        i = (int)(motionevent.getY() + 0.5F);
        mLastTouchY = i;
        mInitialTouchY = i;
        i = 0;
        if (flag1)
        {
            i = false | true;
        }
        j = i;
        if (flag2)
        {
            j = i | 2;
        }
        startNestedScroll(j);
        i = ((flag) ? 1 : 0);
        continue; /* Loop/switch isn't completed */
_L7:
        mScrollPointerId = motionevent.getPointerId(j);
        i = (int)(motionevent.getX(j) + 0.5F);
        mLastTouchX = i;
        mInitialTouchX = i;
        i = (int)(motionevent.getY(j) + 0.5F);
        mLastTouchY = i;
        mInitialTouchY = i;
        i = ((flag) ? 1 : 0);
        continue; /* Loop/switch isn't completed */
_L4:
        i = motionevent.findPointerIndex(mScrollPointerId);
        if (i < 0)
        {
            Log.e("RecyclerView", (new StringBuilder()).append("Error processing scroll; pointer index for id ").append(mScrollPointerId).append(" not found. Did any MotionEvents get skipped?").toString());
            return false;
        }
        int i2 = (int)(motionevent.getX(i) + 0.5F);
        int j2 = (int)(motionevent.getY(i) + 0.5F);
        int j1 = mLastTouchX - i2;
        int i1 = mLastTouchY - j2;
        int k = j1;
        i = i1;
        if (dispatchNestedPreScroll(j1, i1, mScrollConsumed, mScrollOffset))
        {
            k = j1 - mScrollConsumed[0];
            i = i1 - mScrollConsumed[1];
            motionevent1.offsetLocation(mScrollOffset[0], mScrollOffset[1]);
            motionevent = mNestedOffsets;
            motionevent[0] = motionevent[0] + mScrollOffset[0];
            motionevent = mNestedOffsets;
            motionevent[1] = motionevent[1] + mScrollOffset[1];
        }
        i1 = k;
        j1 = i;
        if (mScrollState != 1)
        {
            j1 = 0;
            int k1 = k;
            i1 = j1;
            if (flag1)
            {
                k1 = k;
                i1 = j1;
                if (Math.abs(k) > mTouchSlop)
                {
                    int l1;
                    if (k > 0)
                    {
                        k1 = k - mTouchSlop;
                    } else
                    {
                        k1 = k + mTouchSlop;
                    }
                    i1 = 1;
                }
            }
            k = i;
            l1 = i1;
            if (flag2)
            {
                k = i;
                l1 = i1;
                if (Math.abs(i) > mTouchSlop)
                {
                    if (i > 0)
                    {
                        k = i - mTouchSlop;
                    } else
                    {
                        k = i + mTouchSlop;
                    }
                    l1 = 1;
                }
            }
            i1 = k1;
            j1 = k;
            if (l1 != 0)
            {
                setScrollState(1);
                j1 = k;
                i1 = k1;
            }
        }
        i = ((flag) ? 1 : 0);
        if (mScrollState != 1)
        {
            continue; /* Loop/switch isn't completed */
        }
        mLastTouchX = i2 - mScrollOffset[0];
        mLastTouchY = j2 - mScrollOffset[1];
        if (flag1)
        {
            i = i1;
        } else
        {
            i = 0;
        }
        if (flag2)
        {
            k = j1;
        } else
        {
            k = 0;
        }
        if (scrollByInternal(i, k, motionevent1))
        {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        i = ((flag) ? 1 : 0);
        if (mGapWorker == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (i1 == 0)
        {
            i = ((flag) ? 1 : 0);
            if (j1 == 0)
            {
                continue; /* Loop/switch isn't completed */
            }
        }
        mGapWorker.postFromTraversal(this, i1, j1);
        i = ((flag) ? 1 : 0);
        continue; /* Loop/switch isn't completed */
_L8:
        onPointerUp(motionevent);
        i = ((flag) ? 1 : 0);
        continue; /* Loop/switch isn't completed */
_L3:
        mVelocityTracker.addMovement(motionevent1);
        i = 1;
        mVelocityTracker.computeCurrentVelocity(1000, mMaxFlingVelocity);
        float f;
        float f1;
        if (flag1)
        {
            f = -mVelocityTracker.getXVelocity(mScrollPointerId);
        } else
        {
            f = 0.0F;
        }
        if (flag2)
        {
            f1 = -mVelocityTracker.getYVelocity(mScrollPointerId);
        } else
        {
            f1 = 0.0F;
        }
        if (f == 0.0F && f1 == 0.0F || !fling((int)f, (int)f1))
        {
            setScrollState(0);
        }
        resetTouch();
        continue; /* Loop/switch isn't completed */
_L5:
        cancelTouch();
        i = ((flag) ? 1 : 0);
        if (true) goto _L10; else goto _L9
_L9:
    }

    void postAnimationRunner()
    {
        if (!mPostedAnimatorRunner && mIsAttached)
        {
            ViewCompat.postOnAnimation(this, mItemAnimatorRunner);
            mPostedAnimatorRunner = true;
        }
    }

    void recordAnimationInfoIfBouncedHiddenView(ViewHolder viewholder, ItemAnimator.ItemHolderInfo itemholderinfo)
    {
        viewholder.setFlags(0, 8192);
        if (mState.mTrackOldChangeHolders && viewholder.isUpdated() && !viewholder.isRemoved() && !viewholder.shouldIgnore())
        {
            long l = getChangedHolderKey(viewholder);
            mViewInfoStore.addToOldChangeHolders(l, viewholder);
        }
        mViewInfoStore.addToPreLayout(viewholder, itemholderinfo);
    }

    void removeAndRecycleViews()
    {
        if (mItemAnimator != null)
        {
            mItemAnimator.endAnimations();
        }
        if (mLayout != null)
        {
            mLayout.removeAndRecycleAllViews(mRecycler);
            mLayout.removeAndRecycleScrapInt(mRecycler);
        }
        mRecycler.clear();
    }

    boolean removeAnimatingView(View view)
    {
        eatRequestLayout();
        boolean flag1 = mChildHelper.removeViewIfHidden(view);
        if (flag1)
        {
            view = getChildViewHolderInt(view);
            mRecycler.unscrapView(view);
            mRecycler.recycleViewHolderInternal(view);
        }
        boolean flag;
        if (!flag1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        resumeRequestLayout(flag);
        return flag1;
    }

    protected void removeDetachedView(View view, boolean flag)
    {
        ViewHolder viewholder = getChildViewHolderInt(view);
        if (viewholder != null)
        {
            if (viewholder.isTmpDetached())
            {
                viewholder.clearTmpDetachFlag();
            } else
            if (!viewholder.shouldIgnore())
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Called removeDetachedView with a view which is not flagged as tmp detached.").append(viewholder).toString());
            }
        }
        dispatchChildDetached(view);
        super.removeDetachedView(view, flag);
    }

    public void removeItemDecoration(ItemDecoration itemdecoration)
    {
        if (mLayout != null)
        {
            mLayout.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout");
        }
        mItemDecorations.remove(itemdecoration);
        if (mItemDecorations.isEmpty())
        {
            boolean flag;
            if (getOverScrollMode() == 2)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            setWillNotDraw(flag);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public void removeOnChildAttachStateChangeListener(OnChildAttachStateChangeListener onchildattachstatechangelistener)
    {
        if (mOnChildAttachStateListeners == null)
        {
            return;
        } else
        {
            mOnChildAttachStateListeners.remove(onchildattachstatechangelistener);
            return;
        }
    }

    public void removeOnItemTouchListener(OnItemTouchListener onitemtouchlistener)
    {
        mOnItemTouchListeners.remove(onitemtouchlistener);
        if (mActiveOnItemTouchListener == onitemtouchlistener)
        {
            mActiveOnItemTouchListener = null;
        }
    }

    public void removeOnScrollListener(OnScrollListener onscrolllistener)
    {
        if (mScrollListeners != null)
        {
            mScrollListeners.remove(onscrolllistener);
        }
    }

    void repositionShadowingViews()
    {
        int j = mChildHelper.getChildCount();
        for (int i = 0; i < j; i++)
        {
            View view = mChildHelper.getChildAt(i);
            Object obj = getChildViewHolder(view);
            if (obj == null || ((ViewHolder) (obj)).mShadowingHolder == null)
            {
                continue;
            }
            obj = ((ViewHolder) (obj)).mShadowingHolder.itemView;
            int k = view.getLeft();
            int l = view.getTop();
            if (k != ((View) (obj)).getLeft() || l != ((View) (obj)).getTop())
            {
                ((View) (obj)).layout(k, l, ((View) (obj)).getWidth() + k, ((View) (obj)).getHeight() + l);
            }
        }

    }

    public void requestChildFocus(View view, View view1)
    {
        if (!mLayout.onRequestChildFocus(this, mState, view, view1) && view1 != null)
        {
            requestChildOnScreen(view, view1);
        }
        super.requestChildFocus(view, view1);
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean flag)
    {
        return mLayout.requestChildRectangleOnScreen(this, view, rect, flag);
    }

    public void requestDisallowInterceptTouchEvent(boolean flag)
    {
        int j = mOnItemTouchListeners.size();
        for (int i = 0; i < j; i++)
        {
            ((OnItemTouchListener)mOnItemTouchListeners.get(i)).onRequestDisallowInterceptTouchEvent(flag);
        }

        super.requestDisallowInterceptTouchEvent(flag);
    }

    public void requestLayout()
    {
        if (mEatRequestLayout == 0 && !mLayoutFrozen)
        {
            super.requestLayout();
            return;
        } else
        {
            mLayoutRequestEaten = true;
            return;
        }
    }

    void resumeRequestLayout(boolean flag)
    {
        if (mEatRequestLayout < 1)
        {
            mEatRequestLayout = 1;
        }
        if (!flag)
        {
            mLayoutRequestEaten = false;
        }
        if (mEatRequestLayout == 1)
        {
            if (flag && mLayoutRequestEaten && !mLayoutFrozen && mLayout != null && mAdapter != null)
            {
                dispatchLayout();
            }
            if (!mLayoutFrozen)
            {
                mLayoutRequestEaten = false;
            }
        }
        mEatRequestLayout = mEatRequestLayout - 1;
    }

    void saveOldPositions()
    {
        int j = mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < j; i++)
        {
            ViewHolder viewholder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
            if (!viewholder.shouldIgnore())
            {
                viewholder.saveOldPosition();
            }
        }

    }

    public void scrollBy(int i, int j)
    {
        if (mLayout == null)
        {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else
        if (!mLayoutFrozen)
        {
            boolean flag = mLayout.canScrollHorizontally();
            boolean flag1 = mLayout.canScrollVertically();
            if (flag || flag1)
            {
                if (!flag)
                {
                    i = 0;
                }
                if (!flag1)
                {
                    j = 0;
                }
                scrollByInternal(i, j, null);
                return;
            }
        }
    }

    boolean scrollByInternal(int i, int j, MotionEvent motionevent)
    {
        int l = 0;
        boolean flag2 = false;
        int j1 = 0;
        boolean flag = false;
        int k = 0;
        boolean flag3 = false;
        int i1 = 0;
        boolean flag1 = false;
        consumePendingUpdateOperations();
        if (mAdapter != null)
        {
            eatRequestLayout();
            onEnterLayoutOrScroll();
            TraceCompat.beginSection("RV Scroll");
            k = ((flag3) ? 1 : 0);
            l = ((flag2) ? 1 : 0);
            if (i != 0)
            {
                k = mLayout.scrollHorizontallyBy(i, mRecycler, mState);
                l = i - k;
            }
            i1 = ((flag1) ? 1 : 0);
            j1 = ((flag) ? 1 : 0);
            if (j != 0)
            {
                i1 = mLayout.scrollVerticallyBy(j, mRecycler, mState);
                j1 = j - i1;
            }
            TraceCompat.endSection();
            repositionShadowingViews();
            onExitLayoutOrScroll();
            resumeRequestLayout(false);
        }
        if (!mItemDecorations.isEmpty())
        {
            invalidate();
        }
        if (dispatchNestedScroll(k, i1, l, j1, mScrollOffset))
        {
            mLastTouchX = mLastTouchX - mScrollOffset[0];
            mLastTouchY = mLastTouchY - mScrollOffset[1];
            if (motionevent != null)
            {
                motionevent.offsetLocation(mScrollOffset[0], mScrollOffset[1]);
            }
            motionevent = mNestedOffsets;
            motionevent[0] = motionevent[0] + mScrollOffset[0];
            motionevent = mNestedOffsets;
            motionevent[1] = motionevent[1] + mScrollOffset[1];
        } else
        if (getOverScrollMode() != 2)
        {
            if (motionevent != null)
            {
                pullGlows(motionevent.getX(), l, motionevent.getY(), j1);
            }
            considerReleasingGlowsOnScroll(i, j);
        }
        if (k != 0 || i1 != 0)
        {
            dispatchOnScrolled(k, i1);
        }
        if (!awakenScrollBars())
        {
            invalidate();
        }
        return k != 0 || i1 != 0;
    }

    public void scrollTo(int i, int j)
    {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public void scrollToPosition(int i)
    {
        if (mLayoutFrozen)
        {
            return;
        }
        stopScroll();
        if (mLayout == null)
        {
            Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        } else
        {
            mLayout.scrollToPosition(i);
            awakenScrollBars();
            return;
        }
    }

    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityevent)
    {
        if (shouldDeferAccessibilityEvent(accessibilityevent))
        {
            return;
        } else
        {
            super.sendAccessibilityEventUnchecked(accessibilityevent);
            return;
        }
    }

    public void setAccessibilityDelegateCompat(RecyclerViewAccessibilityDelegate recyclerviewaccessibilitydelegate)
    {
        mAccessibilityDelegate = recyclerviewaccessibilitydelegate;
        ViewCompat.setAccessibilityDelegate(this, mAccessibilityDelegate);
    }

    public void setAdapter(Adapter adapter)
    {
        setLayoutFrozen(false);
        setAdapterInternal(adapter, false, true);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(ChildDrawingOrderCallback childdrawingordercallback)
    {
        if (childdrawingordercallback == mChildDrawingOrderCallback)
        {
            return;
        }
        mChildDrawingOrderCallback = childdrawingordercallback;
        boolean flag;
        if (mChildDrawingOrderCallback != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        setChildrenDrawingOrderEnabled(flag);
    }

    boolean setChildImportantForAccessibilityInternal(ViewHolder viewholder, int i)
    {
        if (isComputingLayout())
        {
            viewholder.mPendingAccessibilityState = i;
            mPendingAccessibilityImportanceChange.add(viewholder);
            return false;
        } else
        {
            ViewCompat.setImportantForAccessibility(viewholder.itemView, i);
            return true;
        }
    }

    public void setClipToPadding(boolean flag)
    {
        if (flag != mClipToPadding)
        {
            invalidateGlows();
        }
        mClipToPadding = flag;
        super.setClipToPadding(flag);
        if (mFirstLayoutComplete)
        {
            requestLayout();
        }
    }

    void setDataSetChangedAfterLayout()
    {
        if (mDataSetHasChangedAfterLayout)
        {
            return;
        }
        mDataSetHasChangedAfterLayout = true;
        int j = mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < j; i++)
        {
            ViewHolder viewholder = getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
            if (viewholder != null && !viewholder.shouldIgnore())
            {
                viewholder.addFlags(512);
            }
        }

        mRecycler.setAdapterPositionsAsUnknown();
        markKnownViewsInvalid();
    }

    public void setHasFixedSize(boolean flag)
    {
        mHasFixedSize = flag;
    }

    public void setItemAnimator(ItemAnimator itemanimator)
    {
        if (mItemAnimator != null)
        {
            mItemAnimator.endAnimations();
            mItemAnimator.setListener(null);
        }
        mItemAnimator = itemanimator;
        if (mItemAnimator != null)
        {
            mItemAnimator.setListener(mItemAnimatorListener);
        }
    }

    public void setItemViewCacheSize(int i)
    {
        mRecycler.setViewCacheSize(i);
    }

    public void setLayoutFrozen(boolean flag)
    {
label0:
        {
            if (flag != mLayoutFrozen)
            {
                assertNotInLayoutOrScroll("Do not setLayoutFrozen in layout or scroll");
                if (flag)
                {
                    break label0;
                }
                mLayoutFrozen = false;
                if (mLayoutRequestEaten && mLayout != null && mAdapter != null)
                {
                    requestLayout();
                }
                mLayoutRequestEaten = false;
            }
            return;
        }
        long l = SystemClock.uptimeMillis();
        onTouchEvent(MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0));
        mLayoutFrozen = true;
        mIgnoreMotionEventTillDown = true;
        stopScroll();
    }

    public void setLayoutManager(LayoutManager layoutmanager)
    {
        if (layoutmanager == mLayout)
        {
            return;
        }
        stopScroll();
        if (mLayout != null)
        {
            if (mItemAnimator != null)
            {
                mItemAnimator.endAnimations();
            }
            mLayout.removeAndRecycleAllViews(mRecycler);
            mLayout.removeAndRecycleScrapInt(mRecycler);
            mRecycler.clear();
            if (mIsAttached)
            {
                mLayout.dispatchDetachedFromWindow(this, mRecycler);
            }
            mLayout.setRecyclerView(null);
            mLayout = null;
        } else
        {
            mRecycler.clear();
        }
        mChildHelper.removeAllViewsUnfiltered();
        mLayout = layoutmanager;
        if (layoutmanager != null)
        {
            if (layoutmanager.mRecyclerView != null)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("LayoutManager ").append(layoutmanager).append(" is already attached to a RecyclerView: ").append(layoutmanager.mRecyclerView).toString());
            }
            mLayout.setRecyclerView(this);
            if (mIsAttached)
            {
                mLayout.dispatchAttachedToWindow(this);
            }
        }
        mRecycler.updateViewCacheSize();
        requestLayout();
    }

    public void setNestedScrollingEnabled(boolean flag)
    {
        getScrollingChildHelper().setNestedScrollingEnabled(flag);
    }

    public void setOnFlingListener(OnFlingListener onflinglistener)
    {
        mOnFlingListener = onflinglistener;
    }

    public void setOnScrollListener(OnScrollListener onscrolllistener)
    {
        mScrollListener = onscrolllistener;
    }

    public void setPreserveFocusAfterLayout(boolean flag)
    {
        mPreserveFocusAfterLayout = flag;
    }

    public void setRecycledViewPool(RecycledViewPool recycledviewpool)
    {
        mRecycler.setRecycledViewPool(recycledviewpool);
    }

    public void setRecyclerListener(RecyclerListener recyclerlistener)
    {
        mRecyclerListener = recyclerlistener;
    }

    void setScrollState(int i)
    {
        if (i == mScrollState)
        {
            return;
        }
        mScrollState = i;
        if (i != 2)
        {
            stopScrollersInternal();
        }
        dispatchOnScrollStateChanged(i);
    }

    public void setScrollingTouchSlop(int i)
    {
        ViewConfiguration viewconfiguration = ViewConfiguration.get(getContext());
        switch (i)
        {
        default:
            Log.w("RecyclerView", (new StringBuilder()).append("setScrollingTouchSlop(): bad argument constant ").append(i).append("; using default value").toString());
            // fall through

        case 0: // '\0'
            mTouchSlop = viewconfiguration.getScaledTouchSlop();
            return;

        case 1: // '\001'
            mTouchSlop = viewconfiguration.getScaledPagingTouchSlop();
            break;
        }
    }

    public void setViewCacheExtension(ViewCacheExtension viewcacheextension)
    {
        mRecycler.setViewCacheExtension(viewcacheextension);
    }

    boolean shouldDeferAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        if (isComputingLayout())
        {
            int i = 0;
            if (accessibilityevent != null)
            {
                i = AccessibilityEventCompat.getContentChangeTypes(accessibilityevent);
            }
            int j = i;
            if (i == 0)
            {
                j = 0;
            }
            mEatenAccessibilityChangeFlags = mEatenAccessibilityChangeFlags | j;
            return true;
        } else
        {
            return false;
        }
    }

    public void smoothScrollBy(int i, int j)
    {
        smoothScrollBy(i, j, null);
    }

    public void smoothScrollBy(int i, int j, Interpolator interpolator)
    {
        if (mLayout == null)
        {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else
        if (!mLayoutFrozen)
        {
            if (!mLayout.canScrollHorizontally())
            {
                i = 0;
            }
            if (!mLayout.canScrollVertically())
            {
                j = 0;
            }
            if (i != 0 || j != 0)
            {
                mViewFlinger.smoothScrollBy(i, j, interpolator);
                return;
            }
        }
    }

    public void smoothScrollToPosition(int i)
    {
        if (mLayoutFrozen)
        {
            return;
        }
        if (mLayout == null)
        {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        } else
        {
            mLayout.smoothScrollToPosition(this, mState, i);
            return;
        }
    }

    public boolean startNestedScroll(int i)
    {
        return getScrollingChildHelper().startNestedScroll(i);
    }

    public void stopNestedScroll()
    {
        getScrollingChildHelper().stopNestedScroll();
    }

    public void stopScroll()
    {
        setScrollState(0);
        stopScrollersInternal();
    }

    public void swapAdapter(Adapter adapter, boolean flag)
    {
        setLayoutFrozen(false);
        setAdapterInternal(adapter, true, flag);
        setDataSetChangedAfterLayout();
        requestLayout();
    }

    void viewRangeUpdate(int i, int j, Object obj)
    {
        int l = mChildHelper.getUnfilteredChildCount();
        int k = 0;
        do
        {
            if (k >= l)
            {
                break;
            }
            View view = mChildHelper.getUnfilteredChildAt(k);
            ViewHolder viewholder = getChildViewHolderInt(view);
            if (viewholder != null && !viewholder.shouldIgnore() && viewholder.mPosition >= i && viewholder.mPosition < i + j)
            {
                viewholder.addFlags(2);
                viewholder.addChangePayload(obj);
                ((LayoutParams)view.getLayoutParams()).mInsetsDirty = true;
            }
            k++;
        } while (true);
        mRecycler.viewRangeUpdate(i, j);
    }

    static 
    {
        boolean flag;
        if (android.os.Build.VERSION.SDK_INT == 18 || android.os.Build.VERSION.SDK_INT == 19 || android.os.Build.VERSION.SDK_INT == 20)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        FORCE_INVALIDATE_DISPLAY_LIST = flag;
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        ALLOW_SIZE_IN_UNSPECIFIED_SPEC = flag;
        if (android.os.Build.VERSION.SDK_INT >= 16)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        POST_UPDATES_ON_ANIMATION = flag;
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        ALLOW_THREAD_GAP_WORK = flag;
        if (android.os.Build.VERSION.SDK_INT <= 15)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        FORCE_ABS_FOCUS_SEARCH_DIRECTION = flag;
        if (android.os.Build.VERSION.SDK_INT <= 15)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        IGNORE_DETACHED_FOCUSED_CHILD = flag;
        LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = (new Class[] {
            android/content/Context, android/util/AttributeSet, Integer.TYPE, Integer.TYPE
        });
    }






    // Unreferenced inner class android/support/v7/widget/RecyclerView$LayoutManager$1

/* anonymous class */
    class LayoutManager._cls1
        implements ViewBoundsCheck.Callback
    {

        final LayoutManager this$0;

        public View getChildAt(int i)
        {
            return LayoutManager.this.getChildAt(i);
        }

        public int getChildCount()
        {
            return LayoutManager.this.getChildCount();
        }

        public int getChildEnd(View view)
        {
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            return getDecoratedRight(view) + layoutparams.rightMargin;
        }

        public int getChildStart(View view)
        {
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            return getDecoratedLeft(view) - layoutparams.leftMargin;
        }

        public View getParent()
        {
            return mRecyclerView;
        }

        public int getParentEnd()
        {
            return getWidth() - getPaddingRight();
        }

        public int getParentStart()
        {
            return getPaddingLeft();
        }

            
            {
                this$0 = LayoutManager.this;
                super();
            }
    }


    // Unreferenced inner class android/support/v7/widget/RecyclerView$LayoutManager$2

/* anonymous class */
    class LayoutManager._cls2
        implements ViewBoundsCheck.Callback
    {

        final LayoutManager this$0;

        public View getChildAt(int i)
        {
            return LayoutManager.this.getChildAt(i);
        }

        public int getChildCount()
        {
            return LayoutManager.this.getChildCount();
        }

        public int getChildEnd(View view)
        {
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            return getDecoratedBottom(view) + layoutparams.bottomMargin;
        }

        public int getChildStart(View view)
        {
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            return getDecoratedTop(view) - layoutparams.topMargin;
        }

        public View getParent()
        {
            return mRecyclerView;
        }

        public int getParentEnd()
        {
            return getHeight() - getPaddingBottom();
        }

        public int getParentStart()
        {
            return getPaddingTop();
        }

            
            {
                this$0 = LayoutManager.this;
                super();
            }
    }

}
