// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.animation.ValueAnimator;
import android.content.ClipData;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.os.BuildCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.util.Log;
import android.view.Display;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

// Referenced classes of package android.support.v4.view:
//            ViewPropertyAnimatorCompat, WindowInsetsCompat, AccessibilityDelegateCompat, OnApplyWindowInsetsListener, 
//            PointerIconCompat, NestedScrollingChild, TintableBackgroundView, ViewCompatICS

public class ViewCompat
{
    public static interface FocusDirection
        extends Annotation
    {
    }

    public static interface FocusRealDirection
        extends Annotation
    {
    }

    public static interface FocusRelativeDirection
        extends Annotation
    {
    }

    public static interface ScrollIndicators
        extends Annotation
    {
    }

    static class ViewCompatApi15Impl extends ViewCompatBaseImpl
    {

        public boolean hasOnClickListeners(View view)
        {
            return view.hasOnClickListeners();
        }

        ViewCompatApi15Impl()
        {
        }
    }

    static class ViewCompatApi16Impl extends ViewCompatApi15Impl
    {

        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view)
        {
            view = view.getAccessibilityNodeProvider();
            if (view != null)
            {
                return new AccessibilityNodeProviderCompat(view);
            } else
            {
                return null;
            }
        }

        public boolean getFitsSystemWindows(View view)
        {
            return view.getFitsSystemWindows();
        }

        public int getImportantForAccessibility(View view)
        {
            return view.getImportantForAccessibility();
        }

        public int getMinimumHeight(View view)
        {
            return view.getMinimumHeight();
        }

        public int getMinimumWidth(View view)
        {
            return view.getMinimumWidth();
        }

        public ViewParent getParentForAccessibility(View view)
        {
            return view.getParentForAccessibility();
        }

        public boolean hasOverlappingRendering(View view)
        {
            return view.hasOverlappingRendering();
        }

        public boolean hasTransientState(View view)
        {
            return view.hasTransientState();
        }

        public boolean performAccessibilityAction(View view, int i, Bundle bundle)
        {
            return view.performAccessibilityAction(i, bundle);
        }

        public void postInvalidateOnAnimation(View view)
        {
            view.postInvalidateOnAnimation();
        }

        public void postInvalidateOnAnimation(View view, int i, int j, int k, int l)
        {
            view.postInvalidateOnAnimation(i, j, k, l);
        }

        public void postOnAnimation(View view, Runnable runnable)
        {
            view.postOnAnimation(runnable);
        }

        public void postOnAnimationDelayed(View view, Runnable runnable, long l)
        {
            view.postOnAnimationDelayed(runnable, l);
        }

        public void requestApplyInsets(View view)
        {
            view.requestFitSystemWindows();
        }

        public void setBackground(View view, Drawable drawable)
        {
            view.setBackground(drawable);
        }

        public void setHasTransientState(View view, boolean flag)
        {
            view.setHasTransientState(flag);
        }

        public void setImportantForAccessibility(View view, int i)
        {
            int j = i;
            if (i == 4)
            {
                j = 2;
            }
            view.setImportantForAccessibility(j);
        }

        ViewCompatApi16Impl()
        {
        }
    }

    static class ViewCompatApi17Impl extends ViewCompatApi16Impl
    {

        public Display getDisplay(View view)
        {
            return view.getDisplay();
        }

        public int getLabelFor(View view)
        {
            return view.getLabelFor();
        }

        public int getLayoutDirection(View view)
        {
            return view.getLayoutDirection();
        }

        public int getPaddingEnd(View view)
        {
            return view.getPaddingEnd();
        }

        public int getPaddingStart(View view)
        {
            return view.getPaddingStart();
        }

        public int getWindowSystemUiVisibility(View view)
        {
            return view.getWindowSystemUiVisibility();
        }

        public boolean isPaddingRelative(View view)
        {
            return view.isPaddingRelative();
        }

        public void setLabelFor(View view, int i)
        {
            view.setLabelFor(i);
        }

        public void setLayerPaint(View view, Paint paint)
        {
            view.setLayerPaint(paint);
        }

        public void setLayoutDirection(View view, int i)
        {
            view.setLayoutDirection(i);
        }

        public void setPaddingRelative(View view, int i, int j, int k, int l)
        {
            view.setPaddingRelative(i, j, k, l);
        }

        ViewCompatApi17Impl()
        {
        }
    }

    static class ViewCompatApi18Impl extends ViewCompatApi17Impl
    {

        public Rect getClipBounds(View view)
        {
            return view.getClipBounds();
        }

        public boolean isInLayout(View view)
        {
            return view.isInLayout();
        }

        public void setClipBounds(View view, Rect rect)
        {
            view.setClipBounds(rect);
        }

        ViewCompatApi18Impl()
        {
        }
    }

    static class ViewCompatApi19Impl extends ViewCompatApi18Impl
    {

        public int getAccessibilityLiveRegion(View view)
        {
            return view.getAccessibilityLiveRegion();
        }

        public boolean isAttachedToWindow(View view)
        {
            return view.isAttachedToWindow();
        }

        public boolean isLaidOut(View view)
        {
            return view.isLaidOut();
        }

        public boolean isLayoutDirectionResolved(View view)
        {
            return view.isLayoutDirectionResolved();
        }

        public void setAccessibilityLiveRegion(View view, int i)
        {
            view.setAccessibilityLiveRegion(i);
        }

        public void setImportantForAccessibility(View view, int i)
        {
            view.setImportantForAccessibility(i);
        }

        ViewCompatApi19Impl()
        {
        }
    }

    static class ViewCompatApi21Impl extends ViewCompatApi19Impl
    {

        private static ThreadLocal sThreadLocalRect;

        private static Rect getEmptyTempRect()
        {
            if (sThreadLocalRect == null)
            {
                sThreadLocalRect = new ThreadLocal();
            }
            Rect rect1 = (Rect)sThreadLocalRect.get();
            Rect rect = rect1;
            if (rect1 == null)
            {
                rect = new Rect();
                sThreadLocalRect.set(rect);
            }
            rect.setEmpty();
            return rect;
        }

        public WindowInsetsCompat dispatchApplyWindowInsets(View view, WindowInsetsCompat windowinsetscompat)
        {
            windowinsetscompat = (WindowInsets)WindowInsetsCompat.unwrap(windowinsetscompat);
            WindowInsets windowinsets = view.dispatchApplyWindowInsets(windowinsetscompat);
            view = windowinsetscompat;
            if (windowinsets != windowinsetscompat)
            {
                view = new WindowInsets(windowinsets);
            }
            return WindowInsetsCompat.wrap(view);
        }

        public boolean dispatchNestedFling(View view, float f, float f1, boolean flag)
        {
            return view.dispatchNestedFling(f, f1, flag);
        }

        public boolean dispatchNestedPreFling(View view, float f, float f1)
        {
            return view.dispatchNestedPreFling(f, f1);
        }

        public boolean dispatchNestedPreScroll(View view, int i, int j, int ai[], int ai1[])
        {
            return view.dispatchNestedPreScroll(i, j, ai, ai1);
        }

        public boolean dispatchNestedScroll(View view, int i, int j, int k, int l, int ai[])
        {
            return view.dispatchNestedScroll(i, j, k, l, ai);
        }

        public ColorStateList getBackgroundTintList(View view)
        {
            return view.getBackgroundTintList();
        }

        public android.graphics.PorterDuff.Mode getBackgroundTintMode(View view)
        {
            return view.getBackgroundTintMode();
        }

        public float getElevation(View view)
        {
            return view.getElevation();
        }

        public String getTransitionName(View view)
        {
            return view.getTransitionName();
        }

        public float getTranslationZ(View view)
        {
            return view.getTranslationZ();
        }

        public float getZ(View view)
        {
            return view.getZ();
        }

        public boolean hasNestedScrollingParent(View view)
        {
            return view.hasNestedScrollingParent();
        }

        public boolean isImportantForAccessibility(View view)
        {
            return view.isImportantForAccessibility();
        }

        public boolean isNestedScrollingEnabled(View view)
        {
            return view.isNestedScrollingEnabled();
        }

        public void offsetLeftAndRight(View view, int i)
        {
            Rect rect = getEmptyTempRect();
            boolean flag = false;
            ViewParent viewparent = view.getParent();
            if (viewparent instanceof View)
            {
                View view1 = (View)viewparent;
                rect.set(view1.getLeft(), view1.getTop(), view1.getRight(), view1.getBottom());
                if (!rect.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
            }
            super.offsetLeftAndRight(view, i);
            if (flag && rect.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()))
            {
                ((View)viewparent).invalidate(rect);
            }
        }

        public void offsetTopAndBottom(View view, int i)
        {
            Rect rect = getEmptyTempRect();
            boolean flag = false;
            ViewParent viewparent = view.getParent();
            if (viewparent instanceof View)
            {
                View view1 = (View)viewparent;
                rect.set(view1.getLeft(), view1.getTop(), view1.getRight(), view1.getBottom());
                if (!rect.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
            }
            super.offsetTopAndBottom(view, i);
            if (flag && rect.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()))
            {
                ((View)viewparent).invalidate(rect);
            }
        }

        public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowinsetscompat)
        {
            windowinsetscompat = (WindowInsets)WindowInsetsCompat.unwrap(windowinsetscompat);
            WindowInsets windowinsets = view.onApplyWindowInsets(windowinsetscompat);
            view = windowinsetscompat;
            if (windowinsets != windowinsetscompat)
            {
                view = new WindowInsets(windowinsets);
            }
            return WindowInsetsCompat.wrap(view);
        }

        public void requestApplyInsets(View view)
        {
            view.requestApplyInsets();
        }

        public void setBackgroundTintList(View view, ColorStateList colorstatelist)
        {
            view.setBackgroundTintList(colorstatelist);
            if (android.os.Build.VERSION.SDK_INT == 21)
            {
                colorstatelist = view.getBackground();
                boolean flag;
                if (view.getBackgroundTintList() != null && view.getBackgroundTintMode() != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (colorstatelist != null && flag)
                {
                    if (colorstatelist.isStateful())
                    {
                        colorstatelist.setState(view.getDrawableState());
                    }
                    view.setBackground(colorstatelist);
                }
            }
        }

        public void setBackgroundTintMode(View view, android.graphics.PorterDuff.Mode mode)
        {
            view.setBackgroundTintMode(mode);
            if (android.os.Build.VERSION.SDK_INT == 21)
            {
                mode = view.getBackground();
                boolean flag;
                if (view.getBackgroundTintList() != null && view.getBackgroundTintMode() != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (mode != null && flag)
                {
                    if (mode.isStateful())
                    {
                        mode.setState(view.getDrawableState());
                    }
                    view.setBackground(mode);
                }
            }
        }

        public void setElevation(View view, float f)
        {
            view.setElevation(f);
        }

        public void setNestedScrollingEnabled(View view, boolean flag)
        {
            view.setNestedScrollingEnabled(flag);
        }

        public void setOnApplyWindowInsetsListener(View view, OnApplyWindowInsetsListener onapplywindowinsetslistener)
        {
            if (onapplywindowinsetslistener == null)
            {
                view.setOnApplyWindowInsetsListener(null);
                return;
            } else
            {
                view.setOnApplyWindowInsetsListener(onapplywindowinsetslistener. new android.view.View.OnApplyWindowInsetsListener() {

                    final ViewCompatApi21Impl this$0;
                    final OnApplyWindowInsetsListener val$listener;

                    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowinsets)
                    {
                        windowinsets = WindowInsetsCompat.wrap(windowinsets);
                        return (WindowInsets)WindowInsetsCompat.unwrap(listener.onApplyWindowInsets(view, windowinsets));
                    }

            
            {
                this$0 = final_viewcompatapi21impl;
                listener = OnApplyWindowInsetsListener.this;
                super();
            }
                });
                return;
            }
        }

        public void setTransitionName(View view, String s)
        {
            view.setTransitionName(s);
        }

        public void setTranslationZ(View view, float f)
        {
            view.setTranslationZ(f);
        }

        public void setZ(View view, float f)
        {
            view.setZ(f);
        }

        public boolean startNestedScroll(View view, int i)
        {
            return view.startNestedScroll(i);
        }

        public void stopNestedScroll(View view)
        {
            view.stopNestedScroll();
        }

        ViewCompatApi21Impl()
        {
        }
    }

    static class ViewCompatApi23Impl extends ViewCompatApi21Impl
    {

        public int getScrollIndicators(View view)
        {
            return view.getScrollIndicators();
        }

        public void offsetLeftAndRight(View view, int i)
        {
            view.offsetLeftAndRight(i);
        }

        public void offsetTopAndBottom(View view, int i)
        {
            view.offsetTopAndBottom(i);
        }

        public void setScrollIndicators(View view, int i)
        {
            view.setScrollIndicators(i);
        }

        public void setScrollIndicators(View view, int i, int j)
        {
            view.setScrollIndicators(i, j);
        }

        ViewCompatApi23Impl()
        {
        }
    }

    static class ViewCompatApi24Impl extends ViewCompatApi23Impl
    {

        public void cancelDragAndDrop(View view)
        {
            view.cancelDragAndDrop();
        }

        public void dispatchFinishTemporaryDetach(View view)
        {
            view.dispatchFinishTemporaryDetach();
        }

        public void dispatchStartTemporaryDetach(View view)
        {
            view.dispatchStartTemporaryDetach();
        }

        public void setPointerIcon(View view, PointerIconCompat pointericoncompat)
        {
            if (pointericoncompat != null)
            {
                pointericoncompat = ((PointerIconCompat) (pointericoncompat.getPointerIcon()));
            } else
            {
                pointericoncompat = null;
            }
            view.setPointerIcon((PointerIcon)(PointerIcon)pointericoncompat);
        }

        public boolean startDragAndDrop(View view, ClipData clipdata, android.view.View.DragShadowBuilder dragshadowbuilder, Object obj, int i)
        {
            return view.startDragAndDrop(clipdata, dragshadowbuilder, obj, i);
        }

        public void updateDragShadow(View view, android.view.View.DragShadowBuilder dragshadowbuilder)
        {
            view.updateDragShadow(dragshadowbuilder);
        }

        ViewCompatApi24Impl()
        {
        }
    }

    static class ViewCompatApi26Impl extends ViewCompatApi24Impl
    {

        public void setTooltipText(View view, CharSequence charsequence)
        {
            view.setTooltipText(charsequence);
        }

        ViewCompatApi26Impl()
        {
        }
    }

    static class ViewCompatBaseImpl
    {

        static boolean sAccessibilityDelegateCheckFailed = false;
        static Field sAccessibilityDelegateField;
        private static Method sChildrenDrawingOrderMethod;
        private static Field sMinHeightField;
        private static boolean sMinHeightFieldFetched;
        private static Field sMinWidthField;
        private static boolean sMinWidthFieldFetched;
        private static WeakHashMap sTransitionNameMap;
        private Method mDispatchFinishTemporaryDetach;
        private Method mDispatchStartTemporaryDetach;
        private boolean mTempDetachBound;
        WeakHashMap mViewPropertyAnimatorCompatMap;

        private void bindTempDetach()
        {
            try
            {
                mDispatchStartTemporaryDetach = android/view/View.getDeclaredMethod("dispatchStartTemporaryDetach", new Class[0]);
                mDispatchFinishTemporaryDetach = android/view/View.getDeclaredMethod("dispatchFinishTemporaryDetach", new Class[0]);
            }
            catch (NoSuchMethodException nosuchmethodexception)
            {
                Log.e("ViewCompat", "Couldn't find method", nosuchmethodexception);
            }
            mTempDetachBound = true;
        }

        private static void tickleInvalidationFlag(View view)
        {
            float f = view.getTranslationY();
            view.setTranslationY(1.0F + f);
            view.setTranslationY(f);
        }

        public ViewPropertyAnimatorCompat animate(View view)
        {
            if (mViewPropertyAnimatorCompatMap == null)
            {
                mViewPropertyAnimatorCompatMap = new WeakHashMap();
            }
            ViewPropertyAnimatorCompat viewpropertyanimatorcompat1 = (ViewPropertyAnimatorCompat)mViewPropertyAnimatorCompatMap.get(view);
            ViewPropertyAnimatorCompat viewpropertyanimatorcompat = viewpropertyanimatorcompat1;
            if (viewpropertyanimatorcompat1 == null)
            {
                viewpropertyanimatorcompat = new ViewPropertyAnimatorCompat(view);
                mViewPropertyAnimatorCompatMap.put(view, viewpropertyanimatorcompat);
            }
            return viewpropertyanimatorcompat;
        }

        public void cancelDragAndDrop(View view)
        {
        }

        public WindowInsetsCompat dispatchApplyWindowInsets(View view, WindowInsetsCompat windowinsetscompat)
        {
            return windowinsetscompat;
        }

        public void dispatchFinishTemporaryDetach(View view)
        {
            if (!mTempDetachBound)
            {
                bindTempDetach();
            }
            if (mDispatchFinishTemporaryDetach != null)
            {
                try
                {
                    mDispatchFinishTemporaryDetach.invoke(view, new Object[0]);
                    return;
                }
                // Misplaced declaration of an exception variable
                catch (View view)
                {
                    Log.d("ViewCompat", "Error calling dispatchFinishTemporaryDetach", view);
                }
                return;
            } else
            {
                view.onFinishTemporaryDetach();
                return;
            }
        }

        public boolean dispatchNestedFling(View view, float f, float f1, boolean flag)
        {
            if (view instanceof NestedScrollingChild)
            {
                return ((NestedScrollingChild)view).dispatchNestedFling(f, f1, flag);
            } else
            {
                return false;
            }
        }

        public boolean dispatchNestedPreFling(View view, float f, float f1)
        {
            if (view instanceof NestedScrollingChild)
            {
                return ((NestedScrollingChild)view).dispatchNestedPreFling(f, f1);
            } else
            {
                return false;
            }
        }

        public boolean dispatchNestedPreScroll(View view, int i, int j, int ai[], int ai1[])
        {
            if (view instanceof NestedScrollingChild)
            {
                return ((NestedScrollingChild)view).dispatchNestedPreScroll(i, j, ai, ai1);
            } else
            {
                return false;
            }
        }

        public boolean dispatchNestedScroll(View view, int i, int j, int k, int l, int ai[])
        {
            if (view instanceof NestedScrollingChild)
            {
                return ((NestedScrollingChild)view).dispatchNestedScroll(i, j, k, l, ai);
            } else
            {
                return false;
            }
        }

        public void dispatchStartTemporaryDetach(View view)
        {
            if (!mTempDetachBound)
            {
                bindTempDetach();
            }
            if (mDispatchStartTemporaryDetach != null)
            {
                try
                {
                    mDispatchStartTemporaryDetach.invoke(view, new Object[0]);
                    return;
                }
                // Misplaced declaration of an exception variable
                catch (View view)
                {
                    Log.d("ViewCompat", "Error calling dispatchStartTemporaryDetach", view);
                }
                return;
            } else
            {
                view.onStartTemporaryDetach();
                return;
            }
        }

        public int getAccessibilityLiveRegion(View view)
        {
            return 0;
        }

        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view)
        {
            return null;
        }

        public ColorStateList getBackgroundTintList(View view)
        {
            if (view instanceof TintableBackgroundView)
            {
                return ((TintableBackgroundView)view).getSupportBackgroundTintList();
            } else
            {
                return null;
            }
        }

        public android.graphics.PorterDuff.Mode getBackgroundTintMode(View view)
        {
            if (view instanceof TintableBackgroundView)
            {
                return ((TintableBackgroundView)view).getSupportBackgroundTintMode();
            } else
            {
                return null;
            }
        }

        public Rect getClipBounds(View view)
        {
            return null;
        }

        public Display getDisplay(View view)
        {
            if (isAttachedToWindow(view))
            {
                return ((WindowManager)view.getContext().getSystemService("window")).getDefaultDisplay();
            } else
            {
                return null;
            }
        }

        public float getElevation(View view)
        {
            return 0.0F;
        }

        public boolean getFitsSystemWindows(View view)
        {
            return false;
        }

        long getFrameTime()
        {
            return ValueAnimator.getFrameDelay();
        }

        public int getImportantForAccessibility(View view)
        {
            return 0;
        }

        public int getLabelFor(View view)
        {
            return 0;
        }

        public int getLayoutDirection(View view)
        {
            return 0;
        }

        public int getMinimumHeight(View view)
        {
            if (!sMinHeightFieldFetched)
            {
                int i;
                try
                {
                    sMinHeightField = android/view/View.getDeclaredField("mMinHeight");
                    sMinHeightField.setAccessible(true);
                }
                catch (NoSuchFieldException nosuchfieldexception) { }
                sMinHeightFieldFetched = true;
            }
            if (sMinHeightField == null)
            {
                break MISSING_BLOCK_LABEL_50;
            }
            i = ((Integer)sMinHeightField.get(view)).intValue();
            return i;
            view;
            return 0;
        }

        public int getMinimumWidth(View view)
        {
            if (!sMinWidthFieldFetched)
            {
                int i;
                try
                {
                    sMinWidthField = android/view/View.getDeclaredField("mMinWidth");
                    sMinWidthField.setAccessible(true);
                }
                catch (NoSuchFieldException nosuchfieldexception) { }
                sMinWidthFieldFetched = true;
            }
            if (sMinWidthField == null)
            {
                break MISSING_BLOCK_LABEL_50;
            }
            i = ((Integer)sMinWidthField.get(view)).intValue();
            return i;
            view;
            return 0;
        }

        public int getPaddingEnd(View view)
        {
            return view.getPaddingRight();
        }

        public int getPaddingStart(View view)
        {
            return view.getPaddingLeft();
        }

        public ViewParent getParentForAccessibility(View view)
        {
            return view.getParent();
        }

        public int getScrollIndicators(View view)
        {
            return 0;
        }

        public String getTransitionName(View view)
        {
            if (sTransitionNameMap == null)
            {
                return null;
            } else
            {
                return (String)sTransitionNameMap.get(view);
            }
        }

        public float getTranslationZ(View view)
        {
            return 0.0F;
        }

        public int getWindowSystemUiVisibility(View view)
        {
            return 0;
        }

        public float getZ(View view)
        {
            return getTranslationZ(view) + getElevation(view);
        }

        public boolean hasAccessibilityDelegate(View view)
        {
            boolean flag = true;
            if (sAccessibilityDelegateCheckFailed)
            {
                return false;
            }
            if (sAccessibilityDelegateField == null)
            {
                try
                {
                    sAccessibilityDelegateField = android/view/View.getDeclaredField("mAccessibilityDelegate");
                    sAccessibilityDelegateField.setAccessible(true);
                }
                // Misplaced declaration of an exception variable
                catch (View view)
                {
                    sAccessibilityDelegateCheckFailed = true;
                    return false;
                }
            }
            try
            {
                view = ((View) (sAccessibilityDelegateField.get(view)));
            }
            // Misplaced declaration of an exception variable
            catch (View view)
            {
                sAccessibilityDelegateCheckFailed = true;
                return false;
            }
            if (view == null)
            {
                flag = false;
            }
            return flag;
        }

        public boolean hasNestedScrollingParent(View view)
        {
            if (view instanceof NestedScrollingChild)
            {
                return ((NestedScrollingChild)view).hasNestedScrollingParent();
            } else
            {
                return false;
            }
        }

        public boolean hasOnClickListeners(View view)
        {
            return false;
        }

        public boolean hasOverlappingRendering(View view)
        {
            return true;
        }

        public boolean hasTransientState(View view)
        {
            return false;
        }

        public boolean isAttachedToWindow(View view)
        {
            return view.getWindowToken() != null;
        }

        public boolean isImportantForAccessibility(View view)
        {
            return true;
        }

        public boolean isInLayout(View view)
        {
            return false;
        }

        public boolean isLaidOut(View view)
        {
            return view.getWidth() > 0 && view.getHeight() > 0;
        }

        public boolean isLayoutDirectionResolved(View view)
        {
            return false;
        }

        public boolean isNestedScrollingEnabled(View view)
        {
            if (view instanceof NestedScrollingChild)
            {
                return ((NestedScrollingChild)view).isNestedScrollingEnabled();
            } else
            {
                return false;
            }
        }

        public boolean isPaddingRelative(View view)
        {
            return false;
        }

        public void offsetLeftAndRight(View view, int i)
        {
            view.offsetLeftAndRight(i);
            if (view.getVisibility() == 0)
            {
                tickleInvalidationFlag(view);
                view = view.getParent();
                if (view instanceof View)
                {
                    tickleInvalidationFlag((View)view);
                }
            }
        }

        public void offsetTopAndBottom(View view, int i)
        {
            view.offsetTopAndBottom(i);
            if (view.getVisibility() == 0)
            {
                tickleInvalidationFlag(view);
                view = view.getParent();
                if (view instanceof View)
                {
                    tickleInvalidationFlag((View)view);
                }
            }
        }

        public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowinsetscompat)
        {
            return windowinsetscompat;
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            view.onInitializeAccessibilityNodeInfo((AccessibilityNodeInfo)accessibilitynodeinfocompat.getInfo());
        }

        public boolean performAccessibilityAction(View view, int i, Bundle bundle)
        {
            return false;
        }

        public void postInvalidateOnAnimation(View view)
        {
            view.invalidate();
        }

        public void postInvalidateOnAnimation(View view, int i, int j, int k, int l)
        {
            view.invalidate(i, j, k, l);
        }

        public void postOnAnimation(View view, Runnable runnable)
        {
            view.postDelayed(runnable, getFrameTime());
        }

        public void postOnAnimationDelayed(View view, Runnable runnable, long l)
        {
            view.postDelayed(runnable, getFrameTime() + l);
        }

        public void requestApplyInsets(View view)
        {
        }

        public void setAccessibilityDelegate(View view, AccessibilityDelegateCompat accessibilitydelegatecompat)
        {
            if (accessibilitydelegatecompat == null)
            {
                accessibilitydelegatecompat = null;
            } else
            {
                accessibilitydelegatecompat = accessibilitydelegatecompat.getBridge();
            }
            view.setAccessibilityDelegate(accessibilitydelegatecompat);
        }

        public void setAccessibilityLiveRegion(View view, int i)
        {
        }

        public void setBackground(View view, Drawable drawable)
        {
            view.setBackgroundDrawable(drawable);
        }

        public void setBackgroundTintList(View view, ColorStateList colorstatelist)
        {
            if (view instanceof TintableBackgroundView)
            {
                ((TintableBackgroundView)view).setSupportBackgroundTintList(colorstatelist);
            }
        }

        public void setBackgroundTintMode(View view, android.graphics.PorterDuff.Mode mode)
        {
            if (view instanceof TintableBackgroundView)
            {
                ((TintableBackgroundView)view).setSupportBackgroundTintMode(mode);
            }
        }

        public void setChildrenDrawingOrderEnabled(ViewGroup viewgroup, boolean flag)
        {
            if (sChildrenDrawingOrderMethod == null)
            {
                try
                {
                    sChildrenDrawingOrderMethod = android/view/ViewGroup.getDeclaredMethod("setChildrenDrawingOrderEnabled", new Class[] {
                        Boolean.TYPE
                    });
                }
                catch (NoSuchMethodException nosuchmethodexception)
                {
                    Log.e("ViewCompat", "Unable to find childrenDrawingOrderEnabled", nosuchmethodexception);
                }
                sChildrenDrawingOrderMethod.setAccessible(true);
            }
            try
            {
                sChildrenDrawingOrderMethod.invoke(viewgroup, new Object[] {
                    Boolean.valueOf(flag)
                });
                return;
            }
            // Misplaced declaration of an exception variable
            catch (ViewGroup viewgroup)
            {
                Log.e("ViewCompat", "Unable to invoke childrenDrawingOrderEnabled", viewgroup);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (ViewGroup viewgroup)
            {
                Log.e("ViewCompat", "Unable to invoke childrenDrawingOrderEnabled", viewgroup);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (ViewGroup viewgroup)
            {
                Log.e("ViewCompat", "Unable to invoke childrenDrawingOrderEnabled", viewgroup);
            }
        }

        public void setClipBounds(View view, Rect rect)
        {
        }

        public void setElevation(View view, float f)
        {
        }

        public void setHasTransientState(View view, boolean flag)
        {
        }

        public void setImportantForAccessibility(View view, int i)
        {
        }

        public void setLabelFor(View view, int i)
        {
        }

        public void setLayerPaint(View view, Paint paint)
        {
            view.setLayerType(view.getLayerType(), paint);
            view.invalidate();
        }

        public void setLayoutDirection(View view, int i)
        {
        }

        public void setNestedScrollingEnabled(View view, boolean flag)
        {
            if (view instanceof NestedScrollingChild)
            {
                ((NestedScrollingChild)view).setNestedScrollingEnabled(flag);
            }
        }

        public void setOnApplyWindowInsetsListener(View view, OnApplyWindowInsetsListener onapplywindowinsetslistener)
        {
        }

        public void setPaddingRelative(View view, int i, int j, int k, int l)
        {
            view.setPadding(i, j, k, l);
        }

        public void setPointerIcon(View view, PointerIconCompat pointericoncompat)
        {
        }

        public void setScrollIndicators(View view, int i)
        {
        }

        public void setScrollIndicators(View view, int i, int j)
        {
        }

        public void setTooltipText(View view, CharSequence charsequence)
        {
            ViewCompatICS.setTooltipText(view, charsequence);
        }

        public void setTransitionName(View view, String s)
        {
            if (sTransitionNameMap == null)
            {
                sTransitionNameMap = new WeakHashMap();
            }
            sTransitionNameMap.put(view, s);
        }

        public void setTranslationZ(View view, float f)
        {
        }

        public void setZ(View view, float f)
        {
        }

        public boolean startDragAndDrop(View view, ClipData clipdata, android.view.View.DragShadowBuilder dragshadowbuilder, Object obj, int i)
        {
            return view.startDrag(clipdata, dragshadowbuilder, obj, i);
        }

        public boolean startNestedScroll(View view, int i)
        {
            if (view instanceof NestedScrollingChild)
            {
                return ((NestedScrollingChild)view).startNestedScroll(i);
            } else
            {
                return false;
            }
        }

        public void stopNestedScroll(View view)
        {
            if (view instanceof NestedScrollingChild)
            {
                ((NestedScrollingChild)view).stopNestedScroll();
            }
        }

        public void updateDragShadow(View view, android.view.View.DragShadowBuilder dragshadowbuilder)
        {
        }


        ViewCompatBaseImpl()
        {
            mViewPropertyAnimatorCompatMap = null;
        }
    }


    public static final int ACCESSIBILITY_LIVE_REGION_ASSERTIVE = 2;
    public static final int ACCESSIBILITY_LIVE_REGION_NONE = 0;
    public static final int ACCESSIBILITY_LIVE_REGION_POLITE = 1;
    static final ViewCompatBaseImpl IMPL;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_AUTO = 0;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO = 2;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS = 4;
    public static final int IMPORTANT_FOR_ACCESSIBILITY_YES = 1;
    public static final int LAYER_TYPE_HARDWARE = 2;
    public static final int LAYER_TYPE_NONE = 0;
    public static final int LAYER_TYPE_SOFTWARE = 1;
    public static final int LAYOUT_DIRECTION_INHERIT = 2;
    public static final int LAYOUT_DIRECTION_LOCALE = 3;
    public static final int LAYOUT_DIRECTION_LTR = 0;
    public static final int LAYOUT_DIRECTION_RTL = 1;
    public static final int MEASURED_HEIGHT_STATE_SHIFT = 16;
    public static final int MEASURED_SIZE_MASK = 0xffffff;
    public static final int MEASURED_STATE_MASK = 0xff000000;
    public static final int MEASURED_STATE_TOO_SMALL = 0x1000000;
    public static final int OVER_SCROLL_ALWAYS = 0;
    public static final int OVER_SCROLL_IF_CONTENT_SCROLLS = 1;
    public static final int OVER_SCROLL_NEVER = 2;
    public static final int SCROLL_AXIS_HORIZONTAL = 1;
    public static final int SCROLL_AXIS_NONE = 0;
    public static final int SCROLL_AXIS_VERTICAL = 2;
    public static final int SCROLL_INDICATOR_BOTTOM = 2;
    public static final int SCROLL_INDICATOR_END = 32;
    public static final int SCROLL_INDICATOR_LEFT = 4;
    public static final int SCROLL_INDICATOR_RIGHT = 8;
    public static final int SCROLL_INDICATOR_START = 16;
    public static final int SCROLL_INDICATOR_TOP = 1;
    private static final String TAG = "ViewCompat";

    protected ViewCompat()
    {
    }

    public static ViewPropertyAnimatorCompat animate(View view)
    {
        return IMPL.animate(view);
    }

    public static boolean canScrollHorizontally(View view, int i)
    {
        return view.canScrollHorizontally(i);
    }

    public static boolean canScrollVertically(View view, int i)
    {
        return view.canScrollVertically(i);
    }

    public static void cancelDragAndDrop(View view)
    {
        IMPL.cancelDragAndDrop(view);
    }

    public static int combineMeasuredStates(int i, int j)
    {
        return View.combineMeasuredStates(i, j);
    }

    public static WindowInsetsCompat dispatchApplyWindowInsets(View view, WindowInsetsCompat windowinsetscompat)
    {
        return IMPL.dispatchApplyWindowInsets(view, windowinsetscompat);
    }

    public static void dispatchFinishTemporaryDetach(View view)
    {
        IMPL.dispatchFinishTemporaryDetach(view);
    }

    public static boolean dispatchNestedFling(View view, float f, float f1, boolean flag)
    {
        return IMPL.dispatchNestedFling(view, f, f1, flag);
    }

    public static boolean dispatchNestedPreFling(View view, float f, float f1)
    {
        return IMPL.dispatchNestedPreFling(view, f, f1);
    }

    public static boolean dispatchNestedPreScroll(View view, int i, int j, int ai[], int ai1[])
    {
        return IMPL.dispatchNestedPreScroll(view, i, j, ai, ai1);
    }

    public static boolean dispatchNestedScroll(View view, int i, int j, int k, int l, int ai[])
    {
        return IMPL.dispatchNestedScroll(view, i, j, k, l, ai);
    }

    public static void dispatchStartTemporaryDetach(View view)
    {
        IMPL.dispatchStartTemporaryDetach(view);
    }

    public static int getAccessibilityLiveRegion(View view)
    {
        return IMPL.getAccessibilityLiveRegion(view);
    }

    public static AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view)
    {
        return IMPL.getAccessibilityNodeProvider(view);
    }

    public static float getAlpha(View view)
    {
        return view.getAlpha();
    }

    public static ColorStateList getBackgroundTintList(View view)
    {
        return IMPL.getBackgroundTintList(view);
    }

    public static android.graphics.PorterDuff.Mode getBackgroundTintMode(View view)
    {
        return IMPL.getBackgroundTintMode(view);
    }

    public static Rect getClipBounds(View view)
    {
        return IMPL.getClipBounds(view);
    }

    public static Display getDisplay(View view)
    {
        return IMPL.getDisplay(view);
    }

    public static float getElevation(View view)
    {
        return IMPL.getElevation(view);
    }

    public static boolean getFitsSystemWindows(View view)
    {
        return IMPL.getFitsSystemWindows(view);
    }

    public static int getImportantForAccessibility(View view)
    {
        return IMPL.getImportantForAccessibility(view);
    }

    public static int getLabelFor(View view)
    {
        return IMPL.getLabelFor(view);
    }

    public static int getLayerType(View view)
    {
        return view.getLayerType();
    }

    public static int getLayoutDirection(View view)
    {
        return IMPL.getLayoutDirection(view);
    }

    public static Matrix getMatrix(View view)
    {
        return view.getMatrix();
    }

    public static int getMeasuredHeightAndState(View view)
    {
        return view.getMeasuredHeightAndState();
    }

    public static int getMeasuredState(View view)
    {
        return view.getMeasuredState();
    }

    public static int getMeasuredWidthAndState(View view)
    {
        return view.getMeasuredWidthAndState();
    }

    public static int getMinimumHeight(View view)
    {
        return IMPL.getMinimumHeight(view);
    }

    public static int getMinimumWidth(View view)
    {
        return IMPL.getMinimumWidth(view);
    }

    public static int getOverScrollMode(View view)
    {
        return view.getOverScrollMode();
    }

    public static int getPaddingEnd(View view)
    {
        return IMPL.getPaddingEnd(view);
    }

    public static int getPaddingStart(View view)
    {
        return IMPL.getPaddingStart(view);
    }

    public static ViewParent getParentForAccessibility(View view)
    {
        return IMPL.getParentForAccessibility(view);
    }

    public static float getPivotX(View view)
    {
        return view.getPivotX();
    }

    public static float getPivotY(View view)
    {
        return view.getPivotY();
    }

    public static float getRotation(View view)
    {
        return view.getRotation();
    }

    public static float getRotationX(View view)
    {
        return view.getRotationX();
    }

    public static float getRotationY(View view)
    {
        return view.getRotationY();
    }

    public static float getScaleX(View view)
    {
        return view.getScaleX();
    }

    public static float getScaleY(View view)
    {
        return view.getScaleY();
    }

    public static int getScrollIndicators(View view)
    {
        return IMPL.getScrollIndicators(view);
    }

    public static String getTransitionName(View view)
    {
        return IMPL.getTransitionName(view);
    }

    public static float getTranslationX(View view)
    {
        return view.getTranslationX();
    }

    public static float getTranslationY(View view)
    {
        return view.getTranslationY();
    }

    public static float getTranslationZ(View view)
    {
        return IMPL.getTranslationZ(view);
    }

    public static int getWindowSystemUiVisibility(View view)
    {
        return IMPL.getWindowSystemUiVisibility(view);
    }

    public static float getX(View view)
    {
        return view.getX();
    }

    public static float getY(View view)
    {
        return view.getY();
    }

    public static float getZ(View view)
    {
        return IMPL.getZ(view);
    }

    public static boolean hasAccessibilityDelegate(View view)
    {
        return IMPL.hasAccessibilityDelegate(view);
    }

    public static boolean hasNestedScrollingParent(View view)
    {
        return IMPL.hasNestedScrollingParent(view);
    }

    public static boolean hasOnClickListeners(View view)
    {
        return IMPL.hasOnClickListeners(view);
    }

    public static boolean hasOverlappingRendering(View view)
    {
        return IMPL.hasOverlappingRendering(view);
    }

    public static boolean hasTransientState(View view)
    {
        return IMPL.hasTransientState(view);
    }

    public static boolean isAttachedToWindow(View view)
    {
        return IMPL.isAttachedToWindow(view);
    }

    public static boolean isImportantForAccessibility(View view)
    {
        return IMPL.isImportantForAccessibility(view);
    }

    public static boolean isInLayout(View view)
    {
        return IMPL.isInLayout(view);
    }

    public static boolean isLaidOut(View view)
    {
        return IMPL.isLaidOut(view);
    }

    public static boolean isLayoutDirectionResolved(View view)
    {
        return IMPL.isLayoutDirectionResolved(view);
    }

    public static boolean isNestedScrollingEnabled(View view)
    {
        return IMPL.isNestedScrollingEnabled(view);
    }

    public static boolean isOpaque(View view)
    {
        return view.isOpaque();
    }

    public static boolean isPaddingRelative(View view)
    {
        return IMPL.isPaddingRelative(view);
    }

    public static void jumpDrawablesToCurrentState(View view)
    {
        view.jumpDrawablesToCurrentState();
    }

    public static void offsetLeftAndRight(View view, int i)
    {
        IMPL.offsetLeftAndRight(view, i);
    }

    public static void offsetTopAndBottom(View view, int i)
    {
        IMPL.offsetTopAndBottom(view, i);
    }

    public static WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowinsetscompat)
    {
        return IMPL.onApplyWindowInsets(view, windowinsetscompat);
    }

    public static void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        view.onInitializeAccessibilityEvent(accessibilityevent);
    }

    public static void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        IMPL.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
    }

    public static void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        view.onPopulateAccessibilityEvent(accessibilityevent);
    }

    public static boolean performAccessibilityAction(View view, int i, Bundle bundle)
    {
        return IMPL.performAccessibilityAction(view, i, bundle);
    }

    public static void postInvalidateOnAnimation(View view)
    {
        IMPL.postInvalidateOnAnimation(view);
    }

    public static void postInvalidateOnAnimation(View view, int i, int j, int k, int l)
    {
        IMPL.postInvalidateOnAnimation(view, i, j, k, l);
    }

    public static void postOnAnimation(View view, Runnable runnable)
    {
        IMPL.postOnAnimation(view, runnable);
    }

    public static void postOnAnimationDelayed(View view, Runnable runnable, long l)
    {
        IMPL.postOnAnimationDelayed(view, runnable, l);
    }

    public static void requestApplyInsets(View view)
    {
        IMPL.requestApplyInsets(view);
    }

    public static int resolveSizeAndState(int i, int j, int k)
    {
        return View.resolveSizeAndState(i, j, k);
    }

    public static void setAccessibilityDelegate(View view, AccessibilityDelegateCompat accessibilitydelegatecompat)
    {
        IMPL.setAccessibilityDelegate(view, accessibilitydelegatecompat);
    }

    public static void setAccessibilityLiveRegion(View view, int i)
    {
        IMPL.setAccessibilityLiveRegion(view, i);
    }

    public static void setActivated(View view, boolean flag)
    {
        view.setActivated(flag);
    }

    public static void setAlpha(View view, float f)
    {
        view.setAlpha(f);
    }

    public static void setBackground(View view, Drawable drawable)
    {
        IMPL.setBackground(view, drawable);
    }

    public static void setBackgroundTintList(View view, ColorStateList colorstatelist)
    {
        IMPL.setBackgroundTintList(view, colorstatelist);
    }

    public static void setBackgroundTintMode(View view, android.graphics.PorterDuff.Mode mode)
    {
        IMPL.setBackgroundTintMode(view, mode);
    }

    public static void setChildrenDrawingOrderEnabled(ViewGroup viewgroup, boolean flag)
    {
        IMPL.setChildrenDrawingOrderEnabled(viewgroup, flag);
    }

    public static void setClipBounds(View view, Rect rect)
    {
        IMPL.setClipBounds(view, rect);
    }

    public static void setElevation(View view, float f)
    {
        IMPL.setElevation(view, f);
    }

    public static void setFitsSystemWindows(View view, boolean flag)
    {
        view.setFitsSystemWindows(flag);
    }

    public static void setHasTransientState(View view, boolean flag)
    {
        IMPL.setHasTransientState(view, flag);
    }

    public static void setImportantForAccessibility(View view, int i)
    {
        IMPL.setImportantForAccessibility(view, i);
    }

    public static void setLabelFor(View view, int i)
    {
        IMPL.setLabelFor(view, i);
    }

    public static void setLayerPaint(View view, Paint paint)
    {
        IMPL.setLayerPaint(view, paint);
    }

    public static void setLayerType(View view, int i, Paint paint)
    {
        view.setLayerType(i, paint);
    }

    public static void setLayoutDirection(View view, int i)
    {
        IMPL.setLayoutDirection(view, i);
    }

    public static void setNestedScrollingEnabled(View view, boolean flag)
    {
        IMPL.setNestedScrollingEnabled(view, flag);
    }

    public static void setOnApplyWindowInsetsListener(View view, OnApplyWindowInsetsListener onapplywindowinsetslistener)
    {
        IMPL.setOnApplyWindowInsetsListener(view, onapplywindowinsetslistener);
    }

    public static void setOverScrollMode(View view, int i)
    {
        view.setOverScrollMode(i);
    }

    public static void setPaddingRelative(View view, int i, int j, int k, int l)
    {
        IMPL.setPaddingRelative(view, i, j, k, l);
    }

    public static void setPivotX(View view, float f)
    {
        view.setPivotX(f);
    }

    public static void setPivotY(View view, float f)
    {
        view.setPivotY(f);
    }

    public static void setPointerIcon(View view, PointerIconCompat pointericoncompat)
    {
        IMPL.setPointerIcon(view, pointericoncompat);
    }

    public static void setRotation(View view, float f)
    {
        view.setRotation(f);
    }

    public static void setRotationX(View view, float f)
    {
        view.setRotationX(f);
    }

    public static void setRotationY(View view, float f)
    {
        view.setRotationY(f);
    }

    public static void setSaveFromParentEnabled(View view, boolean flag)
    {
        view.setSaveFromParentEnabled(flag);
    }

    public static void setScaleX(View view, float f)
    {
        view.setScaleX(f);
    }

    public static void setScaleY(View view, float f)
    {
        view.setScaleY(f);
    }

    public static void setScrollIndicators(View view, int i)
    {
        IMPL.setScrollIndicators(view, i);
    }

    public static void setScrollIndicators(View view, int i, int j)
    {
        IMPL.setScrollIndicators(view, i, j);
    }

    public static void setTooltipText(View view, CharSequence charsequence)
    {
        IMPL.setTooltipText(view, charsequence);
    }

    public static void setTransitionName(View view, String s)
    {
        IMPL.setTransitionName(view, s);
    }

    public static void setTranslationX(View view, float f)
    {
        view.setTranslationX(f);
    }

    public static void setTranslationY(View view, float f)
    {
        view.setTranslationY(f);
    }

    public static void setTranslationZ(View view, float f)
    {
        IMPL.setTranslationZ(view, f);
    }

    public static void setX(View view, float f)
    {
        view.setX(f);
    }

    public static void setY(View view, float f)
    {
        view.setY(f);
    }

    public static void setZ(View view, float f)
    {
        IMPL.setZ(view, f);
    }

    public static boolean startDragAndDrop(View view, ClipData clipdata, android.view.View.DragShadowBuilder dragshadowbuilder, Object obj, int i)
    {
        return IMPL.startDragAndDrop(view, clipdata, dragshadowbuilder, obj, i);
    }

    public static boolean startNestedScroll(View view, int i)
    {
        return IMPL.startNestedScroll(view, i);
    }

    public static void stopNestedScroll(View view)
    {
        IMPL.stopNestedScroll(view);
    }

    public static void updateDragShadow(View view, android.view.View.DragShadowBuilder dragshadowbuilder)
    {
        IMPL.updateDragShadow(view, dragshadowbuilder);
    }

    static 
    {
        int i = android.os.Build.VERSION.SDK_INT;
        if (BuildCompat.isAtLeastO())
        {
            IMPL = new ViewCompatApi26Impl();
        } else
        if (i >= 24)
        {
            IMPL = new ViewCompatApi24Impl();
        } else
        if (i >= 23)
        {
            IMPL = new ViewCompatApi23Impl();
        } else
        if (i >= 21)
        {
            IMPL = new ViewCompatApi21Impl();
        } else
        if (i >= 19)
        {
            IMPL = new ViewCompatApi19Impl();
        } else
        if (i >= 18)
        {
            IMPL = new ViewCompatApi18Impl();
        } else
        if (i >= 17)
        {
            IMPL = new ViewCompatApi17Impl();
        } else
        if (i >= 16)
        {
            IMPL = new ViewCompatApi16Impl();
        } else
        if (i >= 15)
        {
            IMPL = new ViewCompatApi15Impl();
        } else
        {
            IMPL = new ViewCompatBaseImpl();
        }
    }
}
