// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Referenced classes of package android.support.design.widget:
//            DirectedAcyclicGraph, ThemeUtils, MathUtils, ViewGroupUtils, 
//            ViewUtils

public class CoordinatorLayout extends ViewGroup
    implements NestedScrollingParent
{
    public static abstract class Behavior
    {

        public static Object getTag(View view)
        {
            return ((LayoutParams)view.getLayoutParams()).mBehaviorTag;
        }

        public static void setTag(View view, Object obj)
        {
            ((LayoutParams)view.getLayoutParams()).mBehaviorTag = obj;
        }

        public boolean blocksInteractionBelow(CoordinatorLayout coordinatorlayout, View view)
        {
            return getScrimOpacity(coordinatorlayout, view) > 0.0F;
        }

        public boolean getInsetDodgeRect(CoordinatorLayout coordinatorlayout, View view, Rect rect)
        {
            return false;
        }

        public int getScrimColor(CoordinatorLayout coordinatorlayout, View view)
        {
            return 0xff000000;
        }

        public float getScrimOpacity(CoordinatorLayout coordinatorlayout, View view)
        {
            return 0.0F;
        }

        public boolean layoutDependsOn(CoordinatorLayout coordinatorlayout, View view, View view1)
        {
            return false;
        }

        public WindowInsetsCompat onApplyWindowInsets(CoordinatorLayout coordinatorlayout, View view, WindowInsetsCompat windowinsetscompat)
        {
            return windowinsetscompat;
        }

        public void onAttachedToLayoutParams(LayoutParams layoutparams)
        {
        }

        public boolean onDependentViewChanged(CoordinatorLayout coordinatorlayout, View view, View view1)
        {
            return false;
        }

        public void onDependentViewRemoved(CoordinatorLayout coordinatorlayout, View view, View view1)
        {
        }

        public void onDetachedFromLayoutParams()
        {
        }

        public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorlayout, View view, MotionEvent motionevent)
        {
            return false;
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorlayout, View view, int i)
        {
            return false;
        }

        public boolean onMeasureChild(CoordinatorLayout coordinatorlayout, View view, int i, int j, int k, int l)
        {
            return false;
        }

        public boolean onNestedFling(CoordinatorLayout coordinatorlayout, View view, View view1, float f, float f1, boolean flag)
        {
            return false;
        }

        public boolean onNestedPreFling(CoordinatorLayout coordinatorlayout, View view, View view1, float f, float f1)
        {
            return false;
        }

        public void onNestedPreScroll(CoordinatorLayout coordinatorlayout, View view, View view1, int i, int j, int ai[])
        {
        }

        public void onNestedScroll(CoordinatorLayout coordinatorlayout, View view, View view1, int i, int j, int k, int l)
        {
        }

        public void onNestedScrollAccepted(CoordinatorLayout coordinatorlayout, View view, View view1, View view2, int i)
        {
        }

        public boolean onRequestChildRectangleOnScreen(CoordinatorLayout coordinatorlayout, View view, Rect rect, boolean flag)
        {
            return false;
        }

        public void onRestoreInstanceState(CoordinatorLayout coordinatorlayout, View view, Parcelable parcelable)
        {
        }

        public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorlayout, View view)
        {
            return android.view.View.BaseSavedState.EMPTY_STATE;
        }

        public boolean onStartNestedScroll(CoordinatorLayout coordinatorlayout, View view, View view1, View view2, int i)
        {
            return false;
        }

        public void onStopNestedScroll(CoordinatorLayout coordinatorlayout, View view, View view1)
        {
        }

        public boolean onTouchEvent(CoordinatorLayout coordinatorlayout, View view, MotionEvent motionevent)
        {
            return false;
        }

        public Behavior()
        {
        }

        public Behavior(Context context, AttributeSet attributeset)
        {
        }
    }

    public static interface DefaultBehavior
        extends Annotation
    {

        public abstract Class value();
    }

    public static interface DispatchChangeEvent
        extends Annotation
    {
    }

    private class HierarchyChangeListener
        implements android.view.ViewGroup.OnHierarchyChangeListener
    {

        final CoordinatorLayout this$0;

        public void onChildViewAdded(View view, View view1)
        {
            if (mOnHierarchyChangeListener != null)
            {
                mOnHierarchyChangeListener.onChildViewAdded(view, view1);
            }
        }

        public void onChildViewRemoved(View view, View view1)
        {
            onChildViewsChanged(2);
            if (mOnHierarchyChangeListener != null)
            {
                mOnHierarchyChangeListener.onChildViewRemoved(view, view1);
            }
        }

        HierarchyChangeListener()
        {
            this$0 = CoordinatorLayout.this;
            super();
        }
    }

    public static class LayoutParams extends android.view.ViewGroup.MarginLayoutParams
    {

        public int anchorGravity;
        public int dodgeInsetEdges;
        public int gravity;
        public int insetEdge;
        public int keyline;
        View mAnchorDirectChild;
        int mAnchorId;
        View mAnchorView;
        Behavior mBehavior;
        boolean mBehaviorResolved;
        Object mBehaviorTag;
        private boolean mDidAcceptNestedScroll;
        private boolean mDidBlockInteraction;
        private boolean mDidChangeAfterNestedScroll;
        int mInsetOffsetX;
        int mInsetOffsetY;
        final Rect mLastChildRect;

        private void resolveAnchorView(View view, CoordinatorLayout coordinatorlayout)
        {
            mAnchorView = coordinatorlayout.findViewById(mAnchorId);
            if (mAnchorView != null)
            {
                if (mAnchorView == coordinatorlayout)
                {
                    if (coordinatorlayout.isInEditMode())
                    {
                        mAnchorDirectChild = null;
                        mAnchorView = null;
                        return;
                    } else
                    {
                        throw new IllegalStateException("View can not be anchored to the the parent CoordinatorLayout");
                    }
                }
                View view1 = mAnchorView;
                for (ViewParent viewparent = mAnchorView.getParent(); viewparent != coordinatorlayout && viewparent != null; viewparent = viewparent.getParent())
                {
                    if (viewparent == view)
                    {
                        if (coordinatorlayout.isInEditMode())
                        {
                            mAnchorDirectChild = null;
                            mAnchorView = null;
                            return;
                        } else
                        {
                            throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
                        }
                    }
                    if (viewparent instanceof View)
                    {
                        view1 = (View)viewparent;
                    }
                }

                mAnchorDirectChild = view1;
                return;
            }
            if (coordinatorlayout.isInEditMode())
            {
                mAnchorDirectChild = null;
                mAnchorView = null;
                return;
            } else
            {
                throw new IllegalStateException((new StringBuilder()).append("Could not find CoordinatorLayout descendant view with id ").append(coordinatorlayout.getResources().getResourceName(mAnchorId)).append(" to anchor view ").append(view).toString());
            }
        }

        private boolean shouldDodge(View view, int i)
        {
            int j = GravityCompat.getAbsoluteGravity(((LayoutParams)view.getLayoutParams()).insetEdge, i);
            return j != 0 && (GravityCompat.getAbsoluteGravity(dodgeInsetEdges, i) & j) == j;
        }

        private boolean verifyAnchorView(View view, CoordinatorLayout coordinatorlayout)
        {
            if (mAnchorView.getId() != mAnchorId)
            {
                return false;
            }
            View view1 = mAnchorView;
            for (ViewParent viewparent = mAnchorView.getParent(); viewparent != coordinatorlayout; viewparent = viewparent.getParent())
            {
                if (viewparent == null || viewparent == view)
                {
                    mAnchorDirectChild = null;
                    mAnchorView = null;
                    return false;
                }
                if (viewparent instanceof View)
                {
                    view1 = (View)viewparent;
                }
            }

            mAnchorDirectChild = view1;
            return true;
        }

        void acceptNestedScroll(boolean flag)
        {
            mDidAcceptNestedScroll = flag;
        }

        boolean checkAnchorChanged()
        {
            return mAnchorView == null && mAnchorId != -1;
        }

        boolean dependsOn(CoordinatorLayout coordinatorlayout, View view, View view1)
        {
            return view1 == mAnchorDirectChild || shouldDodge(view1, ViewCompat.getLayoutDirection(coordinatorlayout)) || mBehavior != null && mBehavior.layoutDependsOn(coordinatorlayout, view, view1);
        }

        boolean didBlockInteraction()
        {
            if (mBehavior == null)
            {
                mDidBlockInteraction = false;
            }
            return mDidBlockInteraction;
        }

        View findAnchorView(CoordinatorLayout coordinatorlayout, View view)
        {
            if (mAnchorId == -1)
            {
                mAnchorDirectChild = null;
                mAnchorView = null;
                return null;
            }
            if (mAnchorView == null || !verifyAnchorView(view, coordinatorlayout))
            {
                resolveAnchorView(view, coordinatorlayout);
            }
            return mAnchorView;
        }

        public int getAnchorId()
        {
            return mAnchorId;
        }

        public Behavior getBehavior()
        {
            return mBehavior;
        }

        boolean getChangedAfterNestedScroll()
        {
            return mDidChangeAfterNestedScroll;
        }

        Rect getLastChildRect()
        {
            return mLastChildRect;
        }

        void invalidateAnchor()
        {
            mAnchorDirectChild = null;
            mAnchorView = null;
        }

        boolean isBlockingInteractionBelow(CoordinatorLayout coordinatorlayout, View view)
        {
            if (mDidBlockInteraction)
            {
                return true;
            }
            boolean flag1 = mDidBlockInteraction;
            boolean flag;
            if (mBehavior != null)
            {
                flag = mBehavior.blocksInteractionBelow(coordinatorlayout, view);
            } else
            {
                flag = false;
            }
            flag |= flag1;
            mDidBlockInteraction = flag;
            return flag;
        }

        boolean isNestedScrollAccepted()
        {
            return mDidAcceptNestedScroll;
        }

        void resetChangedAfterNestedScroll()
        {
            mDidChangeAfterNestedScroll = false;
        }

        void resetNestedScroll()
        {
            mDidAcceptNestedScroll = false;
        }

        void resetTouchBehaviorTracking()
        {
            mDidBlockInteraction = false;
        }

        public void setAnchorId(int i)
        {
            invalidateAnchor();
            mAnchorId = i;
        }

        public void setBehavior(Behavior behavior)
        {
            if (mBehavior != behavior)
            {
                if (mBehavior != null)
                {
                    mBehavior.onDetachedFromLayoutParams();
                }
                mBehavior = behavior;
                mBehaviorTag = null;
                mBehaviorResolved = true;
                if (behavior != null)
                {
                    behavior.onAttachedToLayoutParams(this);
                }
            }
        }

        void setChangedAfterNestedScroll(boolean flag)
        {
            mDidChangeAfterNestedScroll = flag;
        }

        void setLastChildRect(Rect rect)
        {
            mLastChildRect.set(rect);
        }

        public LayoutParams(int i, int j)
        {
            super(i, j);
            mBehaviorResolved = false;
            gravity = 0;
            anchorGravity = 0;
            keyline = -1;
            mAnchorId = -1;
            insetEdge = 0;
            dodgeInsetEdges = 0;
            mLastChildRect = new Rect();
        }

        LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            mBehaviorResolved = false;
            gravity = 0;
            anchorGravity = 0;
            keyline = -1;
            mAnchorId = -1;
            insetEdge = 0;
            dodgeInsetEdges = 0;
            mLastChildRect = new Rect();
            TypedArray typedarray = context.obtainStyledAttributes(attributeset, android.support.design.R.styleable.CoordinatorLayout_Layout);
            gravity = typedarray.getInteger(android.support.design.R.styleable.CoordinatorLayout_Layout_android_layout_gravity, 0);
            mAnchorId = typedarray.getResourceId(android.support.design.R.styleable.CoordinatorLayout_Layout_layout_anchor, -1);
            anchorGravity = typedarray.getInteger(android.support.design.R.styleable.CoordinatorLayout_Layout_layout_anchorGravity, 0);
            keyline = typedarray.getInteger(android.support.design.R.styleable.CoordinatorLayout_Layout_layout_keyline, -1);
            insetEdge = typedarray.getInt(android.support.design.R.styleable.CoordinatorLayout_Layout_layout_insetEdge, 0);
            dodgeInsetEdges = typedarray.getInt(android.support.design.R.styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
            mBehaviorResolved = typedarray.hasValue(android.support.design.R.styleable.CoordinatorLayout_Layout_layout_behavior);
            if (mBehaviorResolved)
            {
                mBehavior = CoordinatorLayout.parseBehavior(context, attributeset, typedarray.getString(android.support.design.R.styleable.CoordinatorLayout_Layout_layout_behavior));
            }
            typedarray.recycle();
            if (mBehavior != null)
            {
                mBehavior.onAttachedToLayoutParams(this);
            }
        }

        public LayoutParams(LayoutParams layoutparams)
        {
            super(layoutparams);
            mBehaviorResolved = false;
            gravity = 0;
            anchorGravity = 0;
            keyline = -1;
            mAnchorId = -1;
            insetEdge = 0;
            dodgeInsetEdges = 0;
            mLastChildRect = new Rect();
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            mBehaviorResolved = false;
            gravity = 0;
            anchorGravity = 0;
            keyline = -1;
            mAnchorId = -1;
            insetEdge = 0;
            dodgeInsetEdges = 0;
            mLastChildRect = new Rect();
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
            mBehaviorResolved = false;
            gravity = 0;
            anchorGravity = 0;
            keyline = -1;
            mAnchorId = -1;
            insetEdge = 0;
            dodgeInsetEdges = 0;
            mLastChildRect = new Rect();
        }
    }

    class OnPreDrawListener
        implements android.view.ViewTreeObserver.OnPreDrawListener
    {

        final CoordinatorLayout this$0;

        public boolean onPreDraw()
        {
            onChildViewsChanged(0);
            return true;
        }

        OnPreDrawListener()
        {
            this$0 = CoordinatorLayout.this;
            super();
        }
    }

    protected static class SavedState extends AbsSavedState
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
        SparseArray behaviorStates;

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            int ai[];
            Parcelable aparcelable[];
            int j;
            if (behaviorStates != null)
            {
                j = behaviorStates.size();
            } else
            {
                j = 0;
            }
            parcel.writeInt(j);
            ai = new int[j];
            aparcelable = new Parcelable[j];
            for (int k = 0; k < j; k++)
            {
                ai[k] = behaviorStates.keyAt(k);
                aparcelable[k] = (Parcelable)behaviorStates.valueAt(k);
            }

            parcel.writeIntArray(ai);
            parcel.writeParcelableArray(aparcelable, i);
        }


        public SavedState(Parcel parcel, ClassLoader classloader)
        {
            super(parcel, classloader);
            int j = parcel.readInt();
            int ai[] = new int[j];
            parcel.readIntArray(ai);
            parcel = parcel.readParcelableArray(classloader);
            behaviorStates = new SparseArray(j);
            for (int i = 0; i < j; i++)
            {
                behaviorStates.append(ai[i], parcel[i]);
            }

        }

        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }

    static class ViewElevationComparator
        implements Comparator
    {

        public int compare(View view, View view1)
        {
            float f = ViewCompat.getZ(view);
            float f1 = ViewCompat.getZ(view1);
            if (f > f1)
            {
                return -1;
            }
            return f >= f1 ? 0 : 1;
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((View)obj, (View)obj1);
        }

        ViewElevationComparator()
        {
        }
    }


    static final Class CONSTRUCTOR_PARAMS[] = {
        android/content/Context, android/util/AttributeSet
    };
    static final int EVENT_NESTED_SCROLL = 1;
    static final int EVENT_PRE_DRAW = 0;
    static final int EVENT_VIEW_REMOVED = 2;
    static final String TAG = "CoordinatorLayout";
    static final Comparator TOP_SORTED_CHILDREN_COMPARATOR;
    private static final int TYPE_ON_INTERCEPT = 0;
    private static final int TYPE_ON_TOUCH = 1;
    static final String WIDGET_PACKAGE_NAME;
    static final ThreadLocal sConstructors = new ThreadLocal();
    private static final android.support.v4.util.Pools.Pool sRectPool = new android.support.v4.util.Pools.SynchronizedPool(12);
    private OnApplyWindowInsetsListener mApplyWindowInsetsListener;
    private View mBehaviorTouchView;
    private final DirectedAcyclicGraph mChildDag;
    private final List mDependencySortedChildren;
    private boolean mDisallowInterceptReset;
    private boolean mDrawStatusBarBackground;
    private boolean mIsAttachedToWindow;
    private int mKeylines[];
    private WindowInsetsCompat mLastInsets;
    private boolean mNeedsPreDrawListener;
    private View mNestedScrollingDirectChild;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    private View mNestedScrollingTarget;
    android.view.ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;
    private OnPreDrawListener mOnPreDrawListener;
    private Paint mScrimPaint;
    private Drawable mStatusBarBackground;
    private final List mTempDependenciesList;
    private final int mTempIntPair[];
    private final List mTempList1;

    public CoordinatorLayout(Context context)
    {
        this(context, null);
    }

    public CoordinatorLayout(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public CoordinatorLayout(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mDependencySortedChildren = new ArrayList();
        mChildDag = new DirectedAcyclicGraph();
        mTempList1 = new ArrayList();
        mTempDependenciesList = new ArrayList();
        mTempIntPair = new int[2];
        mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        ThemeUtils.checkAppCompatTheme(context);
        attributeset = context.obtainStyledAttributes(attributeset, android.support.design.R.styleable.CoordinatorLayout, i, android.support.design.R.style.Widget_Design_CoordinatorLayout);
        i = attributeset.getResourceId(android.support.design.R.styleable.CoordinatorLayout_keylines, 0);
        if (i != 0)
        {
            context = context.getResources();
            mKeylines = context.getIntArray(i);
            float f = context.getDisplayMetrics().density;
            int j = mKeylines.length;
            for (i = 0; i < j; i++)
            {
                context = mKeylines;
                context[i] = (int)((float)context[i] * f);
            }

        }
        mStatusBarBackground = attributeset.getDrawable(android.support.design.R.styleable.CoordinatorLayout_statusBarBackground);
        attributeset.recycle();
        setupForInsets();
        super.setOnHierarchyChangeListener(new HierarchyChangeListener());
    }

    private static Rect acquireTempRect()
    {
        Rect rect1 = (Rect)sRectPool.acquire();
        Rect rect = rect1;
        if (rect1 == null)
        {
            rect = new Rect();
        }
        return rect;
    }

    private void constrainChildRect(LayoutParams layoutparams, Rect rect, int i, int j)
    {
        int l = getWidth();
        int k = getHeight();
        l = Math.max(getPaddingLeft() + layoutparams.leftMargin, Math.min(rect.left, l - getPaddingRight() - i - layoutparams.rightMargin));
        k = Math.max(getPaddingTop() + layoutparams.topMargin, Math.min(rect.top, k - getPaddingBottom() - j - layoutparams.bottomMargin));
        rect.set(l, k, l + i, k + j);
    }

    private WindowInsetsCompat dispatchApplyWindowInsetsToBehaviors(WindowInsetsCompat windowinsetscompat)
    {
        if (windowinsetscompat.isConsumed())
        {
            return windowinsetscompat;
        }
        int i = 0;
        int j = getChildCount();
        do
        {
            WindowInsetsCompat windowinsetscompat1;
label0:
            {
                windowinsetscompat1 = windowinsetscompat;
                if (i < j)
                {
                    View view = getChildAt(i);
                    windowinsetscompat1 = windowinsetscompat;
                    if (!ViewCompat.getFitsSystemWindows(view))
                    {
                        break label0;
                    }
                    Behavior behavior = ((LayoutParams)view.getLayoutParams()).getBehavior();
                    windowinsetscompat1 = windowinsetscompat;
                    if (behavior == null)
                    {
                        break label0;
                    }
                    windowinsetscompat = behavior.onApplyWindowInsets(this, view, windowinsetscompat);
                    windowinsetscompat1 = windowinsetscompat;
                    if (!windowinsetscompat.isConsumed())
                    {
                        break label0;
                    }
                    windowinsetscompat1 = windowinsetscompat;
                }
                return windowinsetscompat1;
            }
            i++;
            windowinsetscompat = windowinsetscompat1;
        } while (true);
    }

    private void getDesiredAnchoredChildRectWithoutConstraints(View view, int i, Rect rect, Rect rect1, LayoutParams layoutparams, int j, int k)
    {
        int l;
        int j1;
        j1 = GravityCompat.getAbsoluteGravity(resolveAnchoredChildGravity(layoutparams.gravity), i);
        l = GravityCompat.getAbsoluteGravity(resolveGravity(layoutparams.anchorGravity), i);
        l & 7;
        JVM INSTR lookupswitch 2: default 60
    //                   1: 208
    //                   5: 200;
           goto _L1 _L2 _L3
_L1:
        i = rect.left;
_L13:
        l & 0x70;
        JVM INSTR lookupswitch 2: default 96
    //                   16: 232
    //                   80: 223;
           goto _L4 _L5 _L6
_L4:
        l = rect.top;
_L14:
        int i1 = i;
        j1 & 7;
        JVM INSTR lookupswitch 2: default 136
    //                   1: 248
    //                   5: 142;
           goto _L7 _L8 _L9
_L9:
        break; /* Loop/switch isn't completed */
_L7:
        i1 = i - j;
_L15:
        i = l;
        j1 & 0x70;
        JVM INSTR lookupswitch 2: default 176
    //                   16: 259
    //                   80: 182;
           goto _L10 _L11 _L12
_L12:
        break; /* Loop/switch isn't completed */
_L10:
        i = l - k;
_L16:
        rect1.set(i1, i, i1 + j, i + k);
        return;
_L3:
        i = rect.right;
          goto _L13
_L2:
        i = rect.left + rect.width() / 2;
          goto _L13
_L6:
        l = rect.bottom;
          goto _L14
_L5:
        l = rect.top + rect.height() / 2;
          goto _L14
_L8:
        i1 = i - j / 2;
          goto _L15
_L11:
        i = l - k / 2;
          goto _L16
    }

    private int getKeyline(int i)
    {
        if (mKeylines == null)
        {
            Log.e("CoordinatorLayout", (new StringBuilder()).append("No keylines defined for ").append(this).append(" - attempted index lookup ").append(i).toString());
            return 0;
        }
        if (i < 0 || i >= mKeylines.length)
        {
            Log.e("CoordinatorLayout", (new StringBuilder()).append("Keyline index ").append(i).append(" out of range for ").append(this).toString());
            return 0;
        } else
        {
            return mKeylines[i];
        }
    }

    private void getTopSortedChildren(List list)
    {
        list.clear();
        boolean flag = isChildrenDrawingOrderEnabled();
        int k = getChildCount();
        int i = k - 1;
        while (i >= 0) 
        {
            int j;
            if (flag)
            {
                j = getChildDrawingOrder(k, i);
            } else
            {
                j = i;
            }
            list.add(getChildAt(j));
            i--;
        }
        if (TOP_SORTED_CHILDREN_COMPARATOR != null)
        {
            Collections.sort(list, TOP_SORTED_CHILDREN_COMPARATOR);
        }
    }

    private boolean hasDependencies(View view)
    {
        return mChildDag.hasOutgoingEdges(view);
    }

    private void layoutChild(View view, int i)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        Rect rect = acquireTempRect();
        rect.set(getPaddingLeft() + layoutparams.leftMargin, getPaddingTop() + layoutparams.topMargin, getWidth() - getPaddingRight() - layoutparams.rightMargin, getHeight() - getPaddingBottom() - layoutparams.bottomMargin);
        if (mLastInsets != null && ViewCompat.getFitsSystemWindows(this) && !ViewCompat.getFitsSystemWindows(view))
        {
            rect.left = rect.left + mLastInsets.getSystemWindowInsetLeft();
            rect.top = rect.top + mLastInsets.getSystemWindowInsetTop();
            rect.right = rect.right - mLastInsets.getSystemWindowInsetRight();
            rect.bottom = rect.bottom - mLastInsets.getSystemWindowInsetBottom();
        }
        Rect rect1 = acquireTempRect();
        GravityCompat.apply(resolveGravity(layoutparams.gravity), view.getMeasuredWidth(), view.getMeasuredHeight(), rect, rect1, i);
        view.layout(rect1.left, rect1.top, rect1.right, rect1.bottom);
        releaseTempRect(rect);
        releaseTempRect(rect1);
    }

    private void layoutChildWithAnchor(View view, View view1, int i)
    {
        Object obj;
        Rect rect;
        obj = (LayoutParams)view.getLayoutParams();
        obj = acquireTempRect();
        rect = acquireTempRect();
        getDescendantRect(view1, ((Rect) (obj)));
        getDesiredAnchoredChildRect(view, i, ((Rect) (obj)), rect);
        view.layout(rect.left, rect.top, rect.right, rect.bottom);
        releaseTempRect(((Rect) (obj)));
        releaseTempRect(rect);
        return;
        view;
        releaseTempRect(((Rect) (obj)));
        releaseTempRect(rect);
        throw view;
    }

    private void layoutChildWithKeyline(View view, int i, int j)
    {
        LayoutParams layoutparams;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        layoutparams = (LayoutParams)view.getLayoutParams();
        l1 = GravityCompat.getAbsoluteGravity(resolveKeylineGravity(layoutparams.gravity), j);
        k1 = getWidth();
        j1 = getHeight();
        l = view.getMeasuredWidth();
        i1 = view.getMeasuredHeight();
        int k = i;
        if (j == 1)
        {
            k = k1 - i;
        }
        i = getKeyline(k) - l;
        j = 0;
        l1 & 7;
        JVM INSTR lookupswitch 2: default 104
    //                   1: 227
    //                   5: 219;
           goto _L1 _L2 _L3
_L1:
        l1 & 0x70;
        JVM INSTR lookupswitch 2: default 136
    //                   16: 245
    //                   80: 237;
           goto _L4 _L5 _L6
_L4:
        i = Math.max(getPaddingLeft() + layoutparams.leftMargin, Math.min(i, k1 - getPaddingRight() - l - layoutparams.rightMargin));
        j = Math.max(getPaddingTop() + layoutparams.topMargin, Math.min(j, j1 - getPaddingBottom() - i1 - layoutparams.bottomMargin));
        view.layout(i, j, i + l, j + i1);
        return;
_L3:
        i += l;
          goto _L1
_L2:
        i += l / 2;
          goto _L1
_L6:
        j = 0 + i1;
          goto _L4
_L5:
        j = 0 + i1 / 2;
          goto _L4
    }

    private void offsetChildByInset(View view, Rect rect, int i)
    {
        while (!ViewCompat.isLaidOut(view) || view.getWidth() <= 0 || view.getHeight() <= 0) 
        {
            return;
        }
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        Behavior behavior = layoutparams.getBehavior();
        Rect rect1 = acquireTempRect();
        Rect rect2 = acquireTempRect();
        rect2.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        if (behavior != null && behavior.getInsetDodgeRect(this, view, rect1))
        {
            if (!rect2.contains(rect1))
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Rect should be within the child's bounds. Rect:").append(rect1.toShortString()).append(" | Bounds:").append(rect2.toShortString()).toString());
            }
        } else
        {
            rect1.set(rect2);
        }
        releaseTempRect(rect2);
        if (rect1.isEmpty())
        {
            releaseTempRect(rect1);
            return;
        }
        int k = GravityCompat.getAbsoluteGravity(layoutparams.dodgeInsetEdges, i);
        int j = 0;
        i = j;
        if ((k & 0x30) == 48)
        {
            int i1 = rect1.top - layoutparams.topMargin - layoutparams.mInsetOffsetY;
            i = j;
            if (i1 < rect.top)
            {
                setInsetOffsetY(view, rect.top - i1);
                i = 1;
            }
        }
        j = i;
        if ((k & 0x50) == 80)
        {
            int j1 = (getHeight() - rect1.bottom - layoutparams.bottomMargin) + layoutparams.mInsetOffsetY;
            j = i;
            if (j1 < rect.bottom)
            {
                setInsetOffsetY(view, j1 - rect.bottom);
                j = 1;
            }
        }
        if (j == 0)
        {
            setInsetOffsetY(view, 0);
        }
        j = 0;
        i = j;
        if ((k & 3) == 3)
        {
            int k1 = rect1.left - layoutparams.leftMargin - layoutparams.mInsetOffsetX;
            i = j;
            if (k1 < rect.left)
            {
                setInsetOffsetX(view, rect.left - k1);
                i = 1;
            }
        }
        j = i;
        if ((k & 5) == 5)
        {
            int l = (getWidth() - rect1.right - layoutparams.rightMargin) + layoutparams.mInsetOffsetX;
            j = i;
            if (l < rect.right)
            {
                setInsetOffsetX(view, l - rect.right);
                j = 1;
            }
        }
        if (j == 0)
        {
            setInsetOffsetX(view, 0);
        }
        releaseTempRect(rect1);
    }

    static Behavior parseBehavior(Context context, AttributeSet attributeset, String s)
    {
        if (TextUtils.isEmpty(s))
        {
            return null;
        }
        Object obj;
        Object obj1;
        Constructor constructor;
        if (s.startsWith("."))
        {
            s = (new StringBuilder()).append(context.getPackageName()).append(s).toString();
        } else
        if (s.indexOf('.') < 0 && !TextUtils.isEmpty(WIDGET_PACKAGE_NAME))
        {
            s = (new StringBuilder()).append(WIDGET_PACKAGE_NAME).append('.').append(s).toString();
        }
        try
        {
            obj1 = (Map)sConstructors.get();
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new RuntimeException((new StringBuilder()).append("Could not inflate Behavior subclass ").append(s).toString(), context);
        }
        obj = obj1;
        if (obj1 != null)
        {
            break MISSING_BLOCK_LABEL_75;
        }
        obj = new HashMap();
        sConstructors.set(obj);
        constructor = (Constructor)((Map) (obj)).get(s);
        obj1 = constructor;
        if (constructor != null)
        {
            break MISSING_BLOCK_LABEL_129;
        }
        obj1 = Class.forName(s, true, context.getClassLoader()).getConstructor(CONSTRUCTOR_PARAMS);
        ((Constructor) (obj1)).setAccessible(true);
        ((Map) (obj)).put(s, obj1);
        context = (Behavior)((Constructor) (obj1)).newInstance(new Object[] {
            context, attributeset
        });
        return context;
    }

    private boolean performIntercept(MotionEvent motionevent, int i)
    {
        Object obj;
        List list;
        boolean flag;
        int j;
        int k;
        int l;
        boolean flag3;
        flag3 = false;
        flag = false;
        obj = null;
        k = motionevent.getActionMasked();
        list = mTempList1;
        getTopSortedChildren(list);
        l = list.size();
        j = 0;
_L10:
        boolean flag4 = flag3;
        if (j >= l) goto _L2; else goto _L1
_L1:
        Object obj1;
        View view;
        Behavior behavior;
        view = (View)list.get(j);
        obj1 = (LayoutParams)view.getLayoutParams();
        behavior = ((LayoutParams) (obj1)).getBehavior();
        if (!flag3 && !flag || k == 0) goto _L4; else goto _L3
_L3:
        boolean flag2;
        boolean flag5;
        obj1 = obj;
        flag5 = flag3;
        flag2 = flag;
        if (behavior == null) goto _L6; else goto _L5
_L5:
        obj1 = obj;
        if (obj == null)
        {
            long l1 = SystemClock.uptimeMillis();
            obj1 = MotionEvent.obtain(l1, l1, 3, 0.0F, 0.0F, 0);
        }
        i;
        JVM INSTR tableswitch 0 1: default 160
    //                   0 188
    //                   1 210;
           goto _L7 _L8 _L9
_L7:
        flag2 = flag;
        flag5 = flag3;
_L6:
        j++;
        obj = obj1;
        flag3 = flag5;
        flag = flag2;
          goto _L10
_L8:
        behavior.onInterceptTouchEvent(this, view, ((MotionEvent) (obj1)));
        flag5 = flag3;
        flag2 = flag;
          goto _L6
_L9:
        behavior.onTouchEvent(this, view, ((MotionEvent) (obj1)));
        flag5 = flag3;
        flag2 = flag;
          goto _L6
_L4:
        flag4 = flag3;
        if (flag3) goto _L12; else goto _L11
_L11:
        flag4 = flag3;
        if (behavior == null) goto _L12; else goto _L13
_L13:
        i;
        JVM INSTR tableswitch 0 1: default 272
    //                   0 363
    //                   1 377;
           goto _L14 _L15 _L16
_L14:
        break; /* Loop/switch isn't completed */
_L16:
        break MISSING_BLOCK_LABEL_377;
_L18:
        flag4 = flag3;
        if (flag3)
        {
            mBehaviorTouchView = view;
            flag4 = flag3;
        }
_L12:
        flag5 = ((LayoutParams) (obj1)).didBlockInteraction();
        flag3 = ((LayoutParams) (obj1)).isBlockingInteractionBelow(this, view);
        boolean flag1;
        if (flag3 && !flag5)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        obj1 = obj;
        flag5 = flag4;
        flag2 = flag1;
        if (!flag3) goto _L6; else goto _L17
_L17:
        obj1 = obj;
        flag5 = flag4;
        flag2 = flag1;
        if (flag1) goto _L6; else goto _L2
_L2:
        list.clear();
        return flag4;
_L15:
        flag3 = behavior.onInterceptTouchEvent(this, view, motionevent);
          goto _L18
        flag3 = behavior.onTouchEvent(this, view, motionevent);
          goto _L18
    }

    private void prepareChildren()
    {
        mDependencySortedChildren.clear();
        mChildDag.clear();
        int i = 0;
        int k = getChildCount();
        do
        {
            if (i >= k)
            {
                break;
            }
            View view = getChildAt(i);
            getResolvedLayoutParams(view).findAnchorView(this, view);
            mChildDag.addNode(view);
            int j = 0;
            while (j < k) 
            {
                if (j != i)
                {
                    View view1 = getChildAt(j);
                    if (getResolvedLayoutParams(view1).dependsOn(this, view1, view))
                    {
                        if (!mChildDag.contains(view1))
                        {
                            mChildDag.addNode(view1);
                        }
                        mChildDag.addEdge(view, view1);
                    }
                }
                j++;
            }
            i++;
        } while (true);
        mDependencySortedChildren.addAll(mChildDag.getSortedList());
        Collections.reverse(mDependencySortedChildren);
    }

    private static void releaseTempRect(Rect rect)
    {
        rect.setEmpty();
        sRectPool.release(rect);
    }

    private void resetTouchBehaviors()
    {
        if (mBehaviorTouchView != null)
        {
            Behavior behavior = ((LayoutParams)mBehaviorTouchView.getLayoutParams()).getBehavior();
            if (behavior != null)
            {
                long l = SystemClock.uptimeMillis();
                MotionEvent motionevent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
                behavior.onTouchEvent(this, mBehaviorTouchView, motionevent);
                motionevent.recycle();
            }
            mBehaviorTouchView = null;
        }
        int j = getChildCount();
        for (int i = 0; i < j; i++)
        {
            ((LayoutParams)getChildAt(i).getLayoutParams()).resetTouchBehaviorTracking();
        }

        mDisallowInterceptReset = false;
    }

    private static int resolveAnchoredChildGravity(int i)
    {
        int j = i;
        if (i == 0)
        {
            j = 17;
        }
        return j;
    }

    private static int resolveGravity(int i)
    {
        int j = i;
        if ((i & 7) == 0)
        {
            j = i | 0x800003;
        }
        i = j;
        if ((j & 0x70) == 0)
        {
            i = j | 0x30;
        }
        return i;
    }

    private static int resolveKeylineGravity(int i)
    {
        int j = i;
        if (i == 0)
        {
            j = 0x800035;
        }
        return j;
    }

    private void setInsetOffsetX(View view, int i)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if (layoutparams.mInsetOffsetX != i)
        {
            ViewCompat.offsetLeftAndRight(view, i - layoutparams.mInsetOffsetX);
            layoutparams.mInsetOffsetX = i;
        }
    }

    private void setInsetOffsetY(View view, int i)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if (layoutparams.mInsetOffsetY != i)
        {
            ViewCompat.offsetTopAndBottom(view, i - layoutparams.mInsetOffsetY);
            layoutparams.mInsetOffsetY = i;
        }
    }

    private void setupForInsets()
    {
        if (android.os.Build.VERSION.SDK_INT < 21)
        {
            return;
        }
        if (ViewCompat.getFitsSystemWindows(this))
        {
            if (mApplyWindowInsetsListener == null)
            {
                mApplyWindowInsetsListener = new OnApplyWindowInsetsListener() {

                    final CoordinatorLayout this$0;

                    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowinsetscompat)
                    {
                        return setWindowInsets(windowinsetscompat);
                    }

            
            {
                this$0 = CoordinatorLayout.this;
                super();
            }
                };
            }
            ViewCompat.setOnApplyWindowInsetsListener(this, mApplyWindowInsetsListener);
            setSystemUiVisibility(1280);
            return;
        } else
        {
            ViewCompat.setOnApplyWindowInsetsListener(this, null);
            return;
        }
    }

    void addPreDrawListener()
    {
        if (mIsAttachedToWindow)
        {
            if (mOnPreDrawListener == null)
            {
                mOnPreDrawListener = new OnPreDrawListener();
            }
            getViewTreeObserver().addOnPreDrawListener(mOnPreDrawListener);
        }
        mNeedsPreDrawListener = true;
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return (layoutparams instanceof LayoutParams) && super.checkLayoutParams(layoutparams);
    }

    public void dispatchDependentViewsChanged(View view)
    {
        List list = mChildDag.getIncomingEdges(view);
        if (list != null && !list.isEmpty())
        {
            for (int i = 0; i < list.size(); i++)
            {
                View view1 = (View)list.get(i);
                Behavior behavior = ((LayoutParams)view1.getLayoutParams()).getBehavior();
                if (behavior != null)
                {
                    behavior.onDependentViewChanged(this, view1, view);
                }
            }

        }
    }

    public boolean doViewsOverlap(View view, View view1)
    {
        Rect rect;
        boolean flag;
        boolean flag1 = true;
        if (view.getVisibility() != 0 || view1.getVisibility() != 0)
        {
            break MISSING_BLOCK_LABEL_159;
        }
        rect = acquireTempRect();
        int i;
        int j;
        if (view.getParent() != this)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        getChildRect(view, flag, rect);
        view = acquireTempRect();
        if (view1.getParent() != this)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        getChildRect(view1, flag, view);
        if (rect.left > ((Rect) (view)).right || rect.top > ((Rect) (view)).bottom || rect.right < ((Rect) (view)).left) goto _L2; else goto _L1
_L1:
        i = rect.bottom;
        j = ((Rect) (view)).top;
        if (i < j) goto _L2; else goto _L3
_L3:
        flag = flag1;
_L5:
        releaseTempRect(rect);
        releaseTempRect(view);
        return flag;
_L2:
        flag = false;
        if (true) goto _L5; else goto _L4
_L4:
        view1;
        releaseTempRect(rect);
        releaseTempRect(view);
        throw view1;
        return false;
    }

    protected boolean drawChild(Canvas canvas, View view, long l)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if (layoutparams.mBehavior != null)
        {
            float f = layoutparams.mBehavior.getScrimOpacity(this, view);
            if (f > 0.0F)
            {
                if (mScrimPaint == null)
                {
                    mScrimPaint = new Paint();
                }
                mScrimPaint.setColor(layoutparams.mBehavior.getScrimColor(this, view));
                mScrimPaint.setAlpha(MathUtils.constrain(Math.round(255F * f), 0, 255));
                int i = canvas.save();
                if (view.isOpaque())
                {
                    canvas.clipRect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom(), android.graphics.Region.Op.DIFFERENCE);
                }
                canvas.drawRect(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom(), mScrimPaint);
                canvas.restoreToCount(i);
            }
        }
        return super.drawChild(canvas, view, l);
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        int ai[] = getDrawableState();
        boolean flag1 = false;
        Drawable drawable = mStatusBarBackground;
        boolean flag = flag1;
        if (drawable != null)
        {
            flag = flag1;
            if (drawable.isStateful())
            {
                flag = false | drawable.setState(ai);
            }
        }
        if (flag)
        {
            invalidate();
        }
    }

    void ensurePreDrawListener()
    {
        boolean flag1 = false;
        int j = getChildCount();
        int i = 0;
label0:
        do
        {
label1:
            {
                boolean flag = flag1;
                if (i < j)
                {
                    if (!hasDependencies(getChildAt(i)))
                    {
                        break label1;
                    }
                    flag = true;
                }
                if (flag != mNeedsPreDrawListener)
                {
                    if (!flag)
                    {
                        break label0;
                    }
                    addPreDrawListener();
                }
                return;
            }
            i++;
        } while (true);
        removePreDrawListener();
    }

    protected LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(-2, -2);
    }

    protected volatile android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return generateDefaultLayoutParams();
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    protected LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
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

    public volatile android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    protected volatile android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return generateLayoutParams(layoutparams);
    }

    void getChildRect(View view, boolean flag, Rect rect)
    {
        if (view.isLayoutRequested() || view.getVisibility() == 8)
        {
            rect.setEmpty();
            return;
        }
        if (flag)
        {
            getDescendantRect(view, rect);
            return;
        } else
        {
            rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            return;
        }
    }

    public List getDependencies(View view)
    {
        view = mChildDag.getOutgoingEdges(view);
        mTempDependenciesList.clear();
        if (view != null)
        {
            mTempDependenciesList.addAll(view);
        }
        return mTempDependenciesList;
    }

    final List getDependencySortedChildren()
    {
        prepareChildren();
        return Collections.unmodifiableList(mDependencySortedChildren);
    }

    public List getDependents(View view)
    {
        view = mChildDag.getIncomingEdges(view);
        mTempDependenciesList.clear();
        if (view != null)
        {
            mTempDependenciesList.addAll(view);
        }
        return mTempDependenciesList;
    }

    void getDescendantRect(View view, Rect rect)
    {
        ViewGroupUtils.getDescendantRect(this, view, rect);
    }

    void getDesiredAnchoredChildRect(View view, int i, Rect rect, Rect rect1)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        int j = view.getMeasuredWidth();
        int k = view.getMeasuredHeight();
        getDesiredAnchoredChildRectWithoutConstraints(view, i, rect, rect1, layoutparams, j, k);
        constrainChildRect(layoutparams, rect1, j, k);
    }

    void getLastChildRect(View view, Rect rect)
    {
        rect.set(((LayoutParams)view.getLayoutParams()).getLastChildRect());
    }

    final WindowInsetsCompat getLastWindowInsets()
    {
        return mLastInsets;
    }

    public int getNestedScrollAxes()
    {
        return mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    LayoutParams getResolvedLayoutParams(View view)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if (!layoutparams.mBehaviorResolved)
        {
            Class class1 = view.getClass();
            view = null;
            View view1;
            do
            {
                view1 = view;
                if (class1 == null)
                {
                    break;
                }
                view = (DefaultBehavior)class1.getAnnotation(android/support/design/widget/CoordinatorLayout$DefaultBehavior);
                view1 = view;
                if (view != null)
                {
                    break;
                }
                class1 = class1.getSuperclass();
            } while (true);
            if (view1 != null)
            {
                try
                {
                    layoutparams.setBehavior((Behavior)view1.value().newInstance());
                }
                // Misplaced declaration of an exception variable
                catch (View view)
                {
                    Log.e("CoordinatorLayout", (new StringBuilder()).append("Default behavior class ").append(view1.value().getName()).append(" could not be instantiated. Did you forget a default constructor?").toString(), view);
                }
            }
            layoutparams.mBehaviorResolved = true;
        }
        return layoutparams;
    }

    public Drawable getStatusBarBackground()
    {
        return mStatusBarBackground;
    }

    protected int getSuggestedMinimumHeight()
    {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingTop() + getPaddingBottom());
    }

    protected int getSuggestedMinimumWidth()
    {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingLeft() + getPaddingRight());
    }

    public boolean isPointInChildBounds(View view, int i, int j)
    {
        Rect rect;
        rect = acquireTempRect();
        getDescendantRect(view, rect);
        boolean flag = rect.contains(i, j);
        releaseTempRect(rect);
        return flag;
        view;
        releaseTempRect(rect);
        throw view;
    }

    void offsetChildToAnchor(View view, int i)
    {
label0:
        {
            LayoutParams layoutparams;
            Rect rect;
            Rect rect1;
            Rect rect2;
            int k;
            int l;
label1:
            {
                boolean flag = false;
                layoutparams = (LayoutParams)view.getLayoutParams();
                if (layoutparams.mAnchorView == null)
                {
                    break label0;
                }
                rect = acquireTempRect();
                rect1 = acquireTempRect();
                rect2 = acquireTempRect();
                getDescendantRect(layoutparams.mAnchorView, rect);
                getChildRect(view, false, rect1);
                k = view.getMeasuredWidth();
                l = view.getMeasuredHeight();
                getDesiredAnchoredChildRectWithoutConstraints(view, i, rect, rect2, layoutparams, k, l);
                if (rect2.left == rect1.left)
                {
                    i = ((flag) ? 1 : 0);
                    if (rect2.top == rect1.top)
                    {
                        break label1;
                    }
                }
                i = 1;
            }
            constrainChildRect(layoutparams, rect2, k, l);
            int j = rect2.left - rect1.left;
            k = rect2.top - rect1.top;
            if (j != 0)
            {
                ViewCompat.offsetLeftAndRight(view, j);
            }
            if (k != 0)
            {
                ViewCompat.offsetTopAndBottom(view, k);
            }
            if (i != 0)
            {
                Behavior behavior = layoutparams.getBehavior();
                if (behavior != null)
                {
                    behavior.onDependentViewChanged(this, view, layoutparams.mAnchorView);
                }
            }
            releaseTempRect(rect);
            releaseTempRect(rect1);
            releaseTempRect(rect2);
        }
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        resetTouchBehaviors();
        if (mNeedsPreDrawListener)
        {
            if (mOnPreDrawListener == null)
            {
                mOnPreDrawListener = new OnPreDrawListener();
            }
            getViewTreeObserver().addOnPreDrawListener(mOnPreDrawListener);
        }
        if (mLastInsets == null && ViewCompat.getFitsSystemWindows(this))
        {
            ViewCompat.requestApplyInsets(this);
        }
        mIsAttachedToWindow = true;
    }

    final void onChildViewsChanged(int i)
    {
        Rect rect;
        Rect rect1;
        Rect rect2;
        int j;
        int i1;
        int j1;
        i1 = ViewCompat.getLayoutDirection(this);
        j1 = mDependencySortedChildren.size();
        rect = acquireTempRect();
        rect1 = acquireTempRect();
        rect2 = acquireTempRect();
        j = 0;
_L5:
        if (j >= j1) goto _L2; else goto _L1
_L1:
        View view;
        Object obj;
        view = (View)mDependencySortedChildren.get(j);
        obj = (LayoutParams)view.getLayoutParams();
        if (i != 0 || view.getVisibility() != 8) goto _L4; else goto _L3
_L3:
        j++;
          goto _L5
_L4:
        for (int k = 0; k < j; k++)
        {
            View view1 = (View)mDependencySortedChildren.get(k);
            if (((LayoutParams) (obj)).mAnchorDirectChild == view1)
            {
                offsetChildToAnchor(view, i1);
            }
        }

        getChildRect(view, true, rect1);
        if (((LayoutParams) (obj)).insetEdge == 0 || rect1.isEmpty()) goto _L7; else goto _L6
_L6:
        int l = GravityCompat.getAbsoluteGravity(((LayoutParams) (obj)).insetEdge, i1);
        l & 0x70;
        JVM INSTR lookupswitch 2: default 208
    //                   48: 384
    //                   80: 402;
           goto _L8 _L9 _L10
_L8:
        l & 7;
        JVM INSTR tableswitch 3 5: default 240
    //                   3 425
    //                   4 240
    //                   5 443;
           goto _L7 _L11 _L7 _L12
_L7:
        if (((LayoutParams) (obj)).dodgeInsetEdges != 0 && view.getVisibility() == 0)
        {
            offsetChildByInset(view, rect, i1);
        }
        if (i == 2) goto _L14; else goto _L13
_L13:
        getLastChildRect(view, rect2);
        if (rect2.equals(rect1)) goto _L3; else goto _L15
_L15:
        recordLastChildRect(view, rect1);
_L14:
        l = j + 1;
_L21:
        if (l >= j1) goto _L3; else goto _L16
_L16:
        LayoutParams layoutparams;
        Behavior behavior;
        obj = (View)mDependencySortedChildren.get(l);
        layoutparams = (LayoutParams)((View) (obj)).getLayoutParams();
        behavior = layoutparams.getBehavior();
        if (behavior == null || !behavior.layoutDependsOn(this, ((View) (obj)), view)) goto _L18; else goto _L17
_L17:
        if (i != 0 || !layoutparams.getChangedAfterNestedScroll()) goto _L20; else goto _L19
_L19:
        layoutparams.resetChangedAfterNestedScroll();
_L18:
        l++;
          goto _L21
_L9:
        rect.top = Math.max(rect.top, rect1.bottom);
          goto _L8
_L10:
        rect.bottom = Math.max(rect.bottom, getHeight() - rect1.top);
          goto _L8
_L11:
        rect.left = Math.max(rect.left, rect1.right);
          goto _L7
_L12:
        rect.right = Math.max(rect.right, getWidth() - rect1.left);
          goto _L7
_L20:
        i;
        JVM INSTR tableswitch 2 2: default 484
    //                   2 511;
           goto _L22 _L23
_L22:
        boolean flag = behavior.onDependentViewChanged(this, ((View) (obj)), view);
_L24:
        if (i == 1)
        {
            layoutparams.setChangedAfterNestedScroll(flag);
        }
          goto _L18
_L23:
        behavior.onDependentViewRemoved(this, ((View) (obj)), view);
        flag = true;
          goto _L24
_L2:
        releaseTempRect(rect);
        releaseTempRect(rect1);
        releaseTempRect(rect2);
        return;
          goto _L8
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        resetTouchBehaviors();
        if (mNeedsPreDrawListener && mOnPreDrawListener != null)
        {
            getViewTreeObserver().removeOnPreDrawListener(mOnPreDrawListener);
        }
        if (mNestedScrollingTarget != null)
        {
            onStopNestedScroll(mNestedScrollingTarget);
        }
        mIsAttachedToWindow = false;
    }

    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (mDrawStatusBarBackground && mStatusBarBackground != null)
        {
            int i;
            if (mLastInsets != null)
            {
                i = mLastInsets.getSystemWindowInsetTop();
            } else
            {
                i = 0;
            }
            if (i > 0)
            {
                mStatusBarBackground.setBounds(0, 0, getWidth(), i);
                mStatusBarBackground.draw(canvas);
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        int i = motionevent.getActionMasked();
        if (i == 0)
        {
            resetTouchBehaviors();
        }
        boolean flag = performIntercept(motionevent, 0);
        if (false)
        {
            throw new NullPointerException();
        }
        if (i == 1 || i == 3)
        {
            resetTouchBehaviors();
        }
        return flag;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        j = ViewCompat.getLayoutDirection(this);
        k = mDependencySortedChildren.size();
        i = 0;
        while (i < k) 
        {
            View view = (View)mDependencySortedChildren.get(i);
            if (view.getVisibility() != 8)
            {
                Behavior behavior = ((LayoutParams)view.getLayoutParams()).getBehavior();
                if (behavior == null || !behavior.onLayoutChild(this, view, j))
                {
                    onLayoutChild(view, j);
                }
            }
            i++;
        }
    }

    public void onLayoutChild(View view, int i)
    {
        LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
        if (layoutparams.checkAnchorChanged())
        {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        }
        if (layoutparams.mAnchorView != null)
        {
            layoutChildWithAnchor(view, layoutparams.mAnchorView, i);
            return;
        }
        if (layoutparams.keyline >= 0)
        {
            layoutChildWithKeyline(view, layoutparams.keyline, i);
            return;
        } else
        {
            layoutChild(view, i);
            return;
        }
    }

    protected void onMeasure(int i, int j)
    {
        View view;
        boolean flag;
        boolean flag1;
        int i1;
        int j1;
        int k1;
        int l2;
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        int j4;
        int k4;
        int l4;
        prepareChildren();
        ensurePreDrawListener();
        l2 = getPaddingLeft();
        i3 = getPaddingTop();
        j3 = getPaddingRight();
        k3 = getPaddingBottom();
        l3 = ViewCompat.getLayoutDirection(this);
        int l;
        int i5;
        if (l3 == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        i4 = android.view.View.MeasureSpec.getMode(i);
        j4 = android.view.View.MeasureSpec.getSize(i);
        k4 = android.view.View.MeasureSpec.getMode(j);
        l4 = android.view.View.MeasureSpec.getSize(j);
        k1 = getSuggestedMinimumWidth();
        j1 = getSuggestedMinimumHeight();
        i1 = 0;
        if (mLastInsets != null && ViewCompat.getFitsSystemWindows(this))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        i5 = mDependencySortedChildren.size();
        l = 0;
        if (l >= i5)
        {
            break MISSING_BLOCK_LABEL_524;
        }
        view = (View)mDependencySortedChildren.get(l);
        if (view.getVisibility() != 8)
        {
            break; /* Loop/switch isn't completed */
        }
_L4:
        l++;
        if (true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_117;
_L1:
        LayoutParams layoutparams;
        int k;
        int l1;
        int i2;
        int j2;
        layoutparams = (LayoutParams)view.getLayoutParams();
        l1 = 0;
        k = l1;
        if (layoutparams.keyline >= 0)
        {
            k = l1;
            if (i4 != 0)
            {
                i2 = getKeyline(layoutparams.keyline);
                j2 = GravityCompat.getAbsoluteGravity(resolveKeylineGravity(layoutparams.gravity), l3) & 7;
                if ((j2 != 3 || flag) && (j2 != 5 || !flag))
                {
                    break; /* Loop/switch isn't completed */
                }
                k = Math.max(0, j4 - j3 - i2);
            }
        }
_L8:
        i2 = i;
        j2 = j;
        int k2 = i2;
        l1 = j2;
        if (flag1)
        {
            k2 = i2;
            l1 = j2;
            if (!ViewCompat.getFitsSystemWindows(view))
            {
                j2 = mLastInsets.getSystemWindowInsetLeft();
                k2 = mLastInsets.getSystemWindowInsetRight();
                l1 = mLastInsets.getSystemWindowInsetTop();
                i2 = mLastInsets.getSystemWindowInsetBottom();
                k2 = android.view.View.MeasureSpec.makeMeasureSpec(j4 - (j2 + k2), i4);
                l1 = android.view.View.MeasureSpec.makeMeasureSpec(l4 - (l1 + i2), k4);
            }
        }
        Behavior behavior = layoutparams.getBehavior();
        if (behavior == null || !behavior.onMeasureChild(this, view, k2, k, l1, 0))
        {
            onMeasureChild(view, k2, k, l1, 0);
        }
        k1 = Math.max(k1, view.getMeasuredWidth() + (l2 + j3) + layoutparams.leftMargin + layoutparams.rightMargin);
        j1 = Math.max(j1, view.getMeasuredHeight() + (i3 + k3) + layoutparams.topMargin + layoutparams.bottomMargin);
        i1 = View.combineMeasuredStates(i1, view.getMeasuredState());
        if (true) goto _L4; else goto _L3
_L3:
        if (j2 == 5 && !flag) goto _L6; else goto _L5
_L5:
        k = l1;
        if (j2 != 3) goto _L8; else goto _L7
_L7:
        k = l1;
        if (!flag) goto _L8; else goto _L6
_L6:
        k = Math.max(0, i2 - l2);
          goto _L8
        setMeasuredDimension(View.resolveSizeAndState(k1, i, 0xff000000 & i1), View.resolveSizeAndState(j1, j, i1 << 16));
        return;
    }

    public void onMeasureChild(View view, int i, int j, int k, int l)
    {
        measureChildWithMargins(view, i, j, k, l);
    }

    public boolean onNestedFling(View view, float f, float f1, boolean flag)
    {
        boolean flag1 = false;
        int j = getChildCount();
        int i = 0;
        while (i < j) 
        {
            View view1 = getChildAt(i);
            boolean flag2;
            if (view1.getVisibility() == 8)
            {
                flag2 = flag1;
            } else
            {
                Object obj = (LayoutParams)view1.getLayoutParams();
                flag2 = flag1;
                if (((LayoutParams) (obj)).isNestedScrollAccepted())
                {
                    obj = ((LayoutParams) (obj)).getBehavior();
                    flag2 = flag1;
                    if (obj != null)
                    {
                        flag2 = flag1 | ((Behavior) (obj)).onNestedFling(this, view1, view, f, f1, flag);
                    }
                }
            }
            i++;
            flag1 = flag2;
        }
        if (flag1)
        {
            onChildViewsChanged(1);
        }
        return flag1;
    }

    public boolean onNestedPreFling(View view, float f, float f1)
    {
        boolean flag = false;
        int j = getChildCount();
        int i = 0;
        while (i < j) 
        {
            View view1 = getChildAt(i);
            boolean flag1;
            if (view1.getVisibility() == 8)
            {
                flag1 = flag;
            } else
            {
                Object obj = (LayoutParams)view1.getLayoutParams();
                flag1 = flag;
                if (((LayoutParams) (obj)).isNestedScrollAccepted())
                {
                    obj = ((LayoutParams) (obj)).getBehavior();
                    flag1 = flag;
                    if (obj != null)
                    {
                        flag1 = flag | ((Behavior) (obj)).onNestedPreFling(this, view1, view, f, f1);
                    }
                }
            }
            i++;
            flag = flag1;
        }
        return flag;
    }

    public void onNestedPreScroll(View view, int i, int j, int ai[])
    {
        int k = 0;
        int l = 0;
        boolean flag1 = false;
        int l1 = getChildCount();
        int i1 = 0;
        while (i1 < l1) 
        {
            View view1 = getChildAt(i1);
            boolean flag;
            int j1;
            int k1;
            if (view1.getVisibility() == 8)
            {
                k1 = l;
                j1 = k;
                flag = flag1;
            } else
            {
                Object obj = (LayoutParams)view1.getLayoutParams();
                flag = flag1;
                j1 = k;
                k1 = l;
                if (((LayoutParams) (obj)).isNestedScrollAccepted())
                {
                    obj = ((LayoutParams) (obj)).getBehavior();
                    flag = flag1;
                    j1 = k;
                    k1 = l;
                    if (obj != null)
                    {
                        int ai1[] = mTempIntPair;
                        mTempIntPair[1] = 0;
                        ai1[0] = 0;
                        ((Behavior) (obj)).onNestedPreScroll(this, view1, view, i, j, mTempIntPair);
                        if (i > 0)
                        {
                            k = Math.max(k, mTempIntPair[0]);
                        } else
                        {
                            k = Math.min(k, mTempIntPair[0]);
                        }
                        if (j > 0)
                        {
                            l = Math.max(l, mTempIntPair[1]);
                        } else
                        {
                            l = Math.min(l, mTempIntPair[1]);
                        }
                        flag = true;
                        j1 = k;
                        k1 = l;
                    }
                }
            }
            i1++;
            flag1 = flag;
            k = j1;
            l = k1;
        }
        ai[0] = k;
        ai[1] = l;
        if (flag1)
        {
            onChildViewsChanged(1);
        }
    }

    public void onNestedScroll(View view, int i, int j, int k, int l)
    {
        int j1 = getChildCount();
        boolean flag = false;
        int i1 = 0;
        while (i1 < j1) 
        {
            View view1 = getChildAt(i1);
            boolean flag1;
            if (view1.getVisibility() == 8)
            {
                flag1 = flag;
            } else
            {
                Object obj = (LayoutParams)view1.getLayoutParams();
                flag1 = flag;
                if (((LayoutParams) (obj)).isNestedScrollAccepted())
                {
                    obj = ((LayoutParams) (obj)).getBehavior();
                    flag1 = flag;
                    if (obj != null)
                    {
                        ((Behavior) (obj)).onNestedScroll(this, view1, view, i, j, k, l);
                        flag1 = true;
                    }
                }
            }
            i1++;
            flag = flag1;
        }
        if (flag)
        {
            onChildViewsChanged(1);
        }
    }

    public void onNestedScrollAccepted(View view, View view1, int i)
    {
        mNestedScrollingParentHelper.onNestedScrollAccepted(view, view1, i);
        mNestedScrollingDirectChild = view;
        mNestedScrollingTarget = view1;
        int k = getChildCount();
        int j = 0;
        while (j < k) 
        {
            View view2 = getChildAt(j);
            Object obj = (LayoutParams)view2.getLayoutParams();
            if (((LayoutParams) (obj)).isNestedScrollAccepted())
            {
                obj = ((LayoutParams) (obj)).getBehavior();
                if (obj != null)
                {
                    ((Behavior) (obj)).onNestedScrollAccepted(this, view2, view, view1, i);
                }
            }
            j++;
        }
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        if (!(parcelable instanceof SavedState))
        {
            super.onRestoreInstanceState(parcelable);
        } else
        {
            parcelable = (SavedState)parcelable;
            super.onRestoreInstanceState(parcelable.getSuperState());
            parcelable = ((SavedState) (parcelable)).behaviorStates;
            int i = 0;
            int j = getChildCount();
            while (i < j) 
            {
                View view = getChildAt(i);
                int k = view.getId();
                Behavior behavior = getResolvedLayoutParams(view).getBehavior();
                if (k != -1 && behavior != null)
                {
                    Parcelable parcelable1 = (Parcelable)parcelable.get(k);
                    if (parcelable1 != null)
                    {
                        behavior.onRestoreInstanceState(this, view, parcelable1);
                    }
                }
                i++;
            }
        }
    }

    protected Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        SparseArray sparsearray = new SparseArray();
        int i = 0;
        for (int j = getChildCount(); i < j; i++)
        {
            Object obj = getChildAt(i);
            int k = ((View) (obj)).getId();
            Behavior behavior = ((LayoutParams)((View) (obj)).getLayoutParams()).getBehavior();
            if (k == -1 || behavior == null)
            {
                continue;
            }
            obj = behavior.onSaveInstanceState(this, ((View) (obj)));
            if (obj != null)
            {
                sparsearray.append(k, obj);
            }
        }

        savedstate.behaviorStates = sparsearray;
        return savedstate;
    }

    public boolean onStartNestedScroll(View view, View view1, int i)
    {
        boolean flag = false;
        int k = getChildCount();
        int j = 0;
        while (j < k) 
        {
            View view2 = getChildAt(j);
            if (view2.getVisibility() != 8)
            {
                LayoutParams layoutparams = (LayoutParams)view2.getLayoutParams();
                Behavior behavior = layoutparams.getBehavior();
                if (behavior != null)
                {
                    boolean flag1 = behavior.onStartNestedScroll(this, view2, view, view1, i);
                    flag |= flag1;
                    layoutparams.acceptNestedScroll(flag1);
                } else
                {
                    layoutparams.acceptNestedScroll(false);
                }
            }
            j++;
        }
        return flag;
    }

    public void onStopNestedScroll(View view)
    {
        mNestedScrollingParentHelper.onStopNestedScroll(view);
        int j = getChildCount();
        int i = 0;
        while (i < j) 
        {
            View view1 = getChildAt(i);
            LayoutParams layoutparams = (LayoutParams)view1.getLayoutParams();
            if (layoutparams.isNestedScrollAccepted())
            {
                Behavior behavior = layoutparams.getBehavior();
                if (behavior != null)
                {
                    behavior.onStopNestedScroll(this, view1, view);
                }
                layoutparams.resetNestedScroll();
                layoutparams.resetChangedAfterNestedScroll();
            }
            i++;
        }
        mNestedScrollingDirectChild = null;
        mNestedScrollingTarget = null;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        Object obj;
        Object obj1;
        int i;
        boolean flag;
        boolean flag3;
label0:
        {
            boolean flag4 = false;
            boolean flag1 = false;
            obj = null;
            obj1 = null;
            i = motionevent.getActionMasked();
            if (mBehaviorTouchView == null)
            {
                flag1 = performIntercept(motionevent, 1);
                flag3 = flag1;
                flag = flag4;
                if (!flag1)
                {
                    break label0;
                }
            }
            Behavior behavior = ((LayoutParams)mBehaviorTouchView.getLayoutParams()).getBehavior();
            flag3 = flag1;
            flag = flag4;
            if (behavior != null)
            {
                flag = behavior.onTouchEvent(this, mBehaviorTouchView, motionevent);
                flag3 = flag1;
            }
        }
        if (mBehaviorTouchView != null) goto _L2; else goto _L1
_L1:
        boolean flag2;
        flag2 = flag | super.onTouchEvent(motionevent);
        motionevent = obj1;
_L4:
        if (!flag2)
        {
            if (i != 0);
        }
        if (motionevent != null)
        {
            motionevent.recycle();
        }
        if (i == 1 || i == 3)
        {
            resetTouchBehaviors();
        }
        return flag2;
_L2:
        motionevent = obj1;
        flag2 = flag;
        if (flag3)
        {
            motionevent = obj;
            if (true)
            {
                long l = SystemClock.uptimeMillis();
                motionevent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
            }
            super.onTouchEvent(motionevent);
            flag2 = flag;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    void recordLastChildRect(View view, Rect rect)
    {
        ((LayoutParams)view.getLayoutParams()).setLastChildRect(rect);
    }

    void removePreDrawListener()
    {
        if (mIsAttachedToWindow && mOnPreDrawListener != null)
        {
            getViewTreeObserver().removeOnPreDrawListener(mOnPreDrawListener);
        }
        mNeedsPreDrawListener = false;
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean flag)
    {
        Behavior behavior = ((LayoutParams)view.getLayoutParams()).getBehavior();
        if (behavior != null && behavior.onRequestChildRectangleOnScreen(this, view, rect, flag))
        {
            return true;
        } else
        {
            return super.requestChildRectangleOnScreen(view, rect, flag);
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean flag)
    {
        super.requestDisallowInterceptTouchEvent(flag);
        if (flag && !mDisallowInterceptReset)
        {
            resetTouchBehaviors();
            mDisallowInterceptReset = true;
        }
    }

    public void setFitsSystemWindows(boolean flag)
    {
        super.setFitsSystemWindows(flag);
        setupForInsets();
    }

    public void setOnHierarchyChangeListener(android.view.ViewGroup.OnHierarchyChangeListener onhierarchychangelistener)
    {
        mOnHierarchyChangeListener = onhierarchychangelistener;
    }

    public void setStatusBarBackground(Drawable drawable)
    {
        Drawable drawable1 = null;
        if (mStatusBarBackground != drawable)
        {
            if (mStatusBarBackground != null)
            {
                mStatusBarBackground.setCallback(null);
            }
            if (drawable != null)
            {
                drawable1 = drawable.mutate();
            }
            mStatusBarBackground = drawable1;
            if (mStatusBarBackground != null)
            {
                if (mStatusBarBackground.isStateful())
                {
                    mStatusBarBackground.setState(getDrawableState());
                }
                DrawableCompat.setLayoutDirection(mStatusBarBackground, ViewCompat.getLayoutDirection(this));
                drawable = mStatusBarBackground;
                boolean flag;
                if (getVisibility() == 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                drawable.setVisible(flag, false);
                mStatusBarBackground.setCallback(this);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setStatusBarBackgroundColor(int i)
    {
        setStatusBarBackground(new ColorDrawable(i));
    }

    public void setStatusBarBackgroundResource(int i)
    {
        Drawable drawable;
        if (i != 0)
        {
            drawable = ContextCompat.getDrawable(getContext(), i);
        } else
        {
            drawable = null;
        }
        setStatusBarBackground(drawable);
    }

    public void setVisibility(int i)
    {
        super.setVisibility(i);
        boolean flag;
        if (i == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (mStatusBarBackground != null && mStatusBarBackground.isVisible() != flag)
        {
            mStatusBarBackground.setVisible(flag, false);
        }
    }

    final WindowInsetsCompat setWindowInsets(WindowInsetsCompat windowinsetscompat)
    {
        boolean flag1 = true;
        WindowInsetsCompat windowinsetscompat1 = windowinsetscompat;
        if (!ViewUtils.objectEquals(mLastInsets, windowinsetscompat))
        {
            mLastInsets = windowinsetscompat;
            boolean flag;
            if (windowinsetscompat != null && windowinsetscompat.getSystemWindowInsetTop() > 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            mDrawStatusBarBackground = flag;
            if (!mDrawStatusBarBackground && getBackground() == null)
            {
                flag = flag1;
            } else
            {
                flag = false;
            }
            setWillNotDraw(flag);
            windowinsetscompat1 = dispatchApplyWindowInsetsToBehaviors(windowinsetscompat);
            requestLayout();
        }
        return windowinsetscompat1;
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        return super.verifyDrawable(drawable) || drawable == mStatusBarBackground;
    }

    static 
    {
        Object obj = android/support/design/widget/CoordinatorLayout.getPackage();
        if (obj != null)
        {
            obj = ((Package) (obj)).getName();
        } else
        {
            obj = null;
        }
        WIDGET_PACKAGE_NAME = ((String) (obj));
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            TOP_SORTED_CHILDREN_COMPARATOR = new ViewElevationComparator();
        } else
        {
            TOP_SORTED_CHILDREN_COMPARATOR = null;
        }
    }
}
