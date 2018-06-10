// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

// Referenced classes of package android.support.transition:
//            ViewOverlayImpl, ViewGroupOverlayApi14

class ViewOverlayApi14
    implements ViewOverlayImpl
{
    static class OverlayViewGroup extends ViewGroup
    {

        static Method sInvalidateChildInParentFastMethod;
        ArrayList mDrawables;
        ViewGroup mHostView;
        View mRequestingView;
        ViewOverlayApi14 mViewOverlay;

        private void getOffset(int ai[])
        {
            int ai1[] = new int[2];
            int ai2[] = new int[2];
            ViewGroup viewgroup = (ViewGroup)getParent();
            mHostView.getLocationOnScreen(ai1);
            mRequestingView.getLocationOnScreen(ai2);
            ai[0] = ai2[0] - ai1[0];
            ai[1] = ai2[1] - ai1[1];
        }

        public void add(Drawable drawable)
        {
            if (mDrawables == null)
            {
                mDrawables = new ArrayList();
            }
            if (!mDrawables.contains(drawable))
            {
                mDrawables.add(drawable);
                invalidate(drawable.getBounds());
                drawable.setCallback(this);
            }
        }

        public void add(View view)
        {
            if (view.getParent() instanceof ViewGroup)
            {
                ViewGroup viewgroup = (ViewGroup)view.getParent();
                if (viewgroup != mHostView && viewgroup.getParent() != null && ViewCompat.isAttachedToWindow(viewgroup))
                {
                    int ai[] = new int[2];
                    int ai1[] = new int[2];
                    viewgroup.getLocationOnScreen(ai);
                    mHostView.getLocationOnScreen(ai1);
                    ViewCompat.offsetLeftAndRight(view, ai[0] - ai1[0]);
                    ViewCompat.offsetTopAndBottom(view, ai[1] - ai1[1]);
                }
                viewgroup.removeView(view);
                if (view.getParent() != null)
                {
                    viewgroup.removeView(view);
                }
            }
            super.addView(view, getChildCount() - 1);
        }

        public void clear()
        {
            removeAllViews();
            if (mDrawables != null)
            {
                mDrawables.clear();
            }
        }

        protected void dispatchDraw(Canvas canvas)
        {
            int i = 0;
            int ai[] = new int[2];
            int ai1[] = new int[2];
            ViewGroup viewgroup = (ViewGroup)getParent();
            mHostView.getLocationOnScreen(ai);
            mRequestingView.getLocationOnScreen(ai1);
            canvas.translate(ai1[0] - ai[0], ai1[1] - ai[1]);
            canvas.clipRect(new Rect(0, 0, mRequestingView.getWidth(), mRequestingView.getHeight()));
            super.dispatchDraw(canvas);
            int j;
            if (mDrawables != null)
            {
                i = mDrawables.size();
            }
            for (j = 0; j < i; j++)
            {
                ((Drawable)mDrawables.get(j)).draw(canvas);
            }

        }

        public boolean dispatchTouchEvent(MotionEvent motionevent)
        {
            return false;
        }

        public void invalidateChildFast(View view, Rect rect)
        {
            if (mHostView != null)
            {
                int i = view.getLeft();
                int j = view.getTop();
                view = new int[2];
                getOffset(view);
                rect.offset(view[0] + i, view[1] + j);
                mHostView.invalidate(rect);
            }
        }

        public ViewParent invalidateChildInParent(int ai[], Rect rect)
        {
            if (mHostView != null)
            {
                rect.offset(ai[0], ai[1]);
                if (mHostView instanceof ViewGroup)
                {
                    ai[0] = 0;
                    ai[1] = 0;
                    int ai1[] = new int[2];
                    getOffset(ai1);
                    rect.offset(ai1[0], ai1[1]);
                    return super.invalidateChildInParent(ai, rect);
                }
                invalidate(rect);
            }
            return null;
        }

        protected ViewParent invalidateChildInParentFast(int i, int j, Rect rect)
        {
            if ((mHostView instanceof ViewGroup) && sInvalidateChildInParentFastMethod != null)
            {
                try
                {
                    getOffset(new int[2]);
                    sInvalidateChildInParentFastMethod.invoke(mHostView, new Object[] {
                        Integer.valueOf(i), Integer.valueOf(j), rect
                    });
                }
                // Misplaced declaration of an exception variable
                catch (Rect rect)
                {
                    rect.printStackTrace();
                }
                // Misplaced declaration of an exception variable
                catch (Rect rect)
                {
                    rect.printStackTrace();
                }
            }
            return null;
        }

        public void invalidateDrawable(Drawable drawable)
        {
            invalidate(drawable.getBounds());
        }

        boolean isEmpty()
        {
            return getChildCount() == 0 && (mDrawables == null || mDrawables.size() == 0);
        }

        protected void onLayout(boolean flag, int i, int j, int k, int l)
        {
        }

        public void remove(Drawable drawable)
        {
            if (mDrawables != null)
            {
                mDrawables.remove(drawable);
                invalidate(drawable.getBounds());
                drawable.setCallback(null);
            }
        }

        public void remove(View view)
        {
            super.removeView(view);
            if (isEmpty())
            {
                mHostView.removeView(this);
            }
        }

        protected boolean verifyDrawable(Drawable drawable)
        {
            return super.verifyDrawable(drawable) || mDrawables != null && mDrawables.contains(drawable);
        }

        static 
        {
            try
            {
                sInvalidateChildInParentFastMethod = android/view/ViewGroup.getDeclaredMethod("invalidateChildInParentFast", new Class[] {
                    Integer.TYPE, Integer.TYPE, android/graphics/Rect
                });
            }
            catch (NoSuchMethodException nosuchmethodexception) { }
        }

        OverlayViewGroup(Context context, ViewGroup viewgroup, View view, ViewOverlayApi14 viewoverlayapi14)
        {
            super(context);
            mDrawables = null;
            mHostView = viewgroup;
            mRequestingView = view;
            setRight(viewgroup.getWidth());
            setBottom(viewgroup.getHeight());
            viewgroup.addView(this);
            mViewOverlay = viewoverlayapi14;
        }
    }

    static class OverlayViewGroup.TouchInterceptor extends View
    {

        OverlayViewGroup.TouchInterceptor(Context context)
        {
            super(context);
        }
    }


    protected OverlayViewGroup mOverlayViewGroup;

    ViewOverlayApi14(Context context, ViewGroup viewgroup, View view)
    {
        mOverlayViewGroup = new OverlayViewGroup(context, viewgroup, view, this);
    }

    static ViewOverlayApi14 createFrom(View view)
    {
        ViewGroup viewgroup = getContentView(view);
        if (viewgroup != null)
        {
            int j = viewgroup.getChildCount();
            for (int i = 0; i < j; i++)
            {
                View view1 = viewgroup.getChildAt(i);
                if (view1 instanceof OverlayViewGroup)
                {
                    return ((OverlayViewGroup)view1).mViewOverlay;
                }
            }

            return new ViewGroupOverlayApi14(viewgroup.getContext(), viewgroup, view);
        } else
        {
            return null;
        }
    }

    static ViewGroup getContentView(View view)
    {
        do
        {
            if (view == null)
            {
                break;
            }
            if (view.getId() == 0x1020002 && (view instanceof ViewGroup))
            {
                return (ViewGroup)view;
            }
            if (view.getParent() instanceof ViewGroup)
            {
                view = (ViewGroup)view.getParent();
            }
        } while (true);
        return null;
    }

    public void add(Drawable drawable)
    {
        mOverlayViewGroup.add(drawable);
    }

    public void clear()
    {
        mOverlayViewGroup.clear();
    }

    ViewGroup getOverlayView()
    {
        return mOverlayViewGroup;
    }

    boolean isEmpty()
    {
        return mOverlayViewGroup.isEmpty();
    }

    public void remove(Drawable drawable)
    {
        mOverlayViewGroup.remove(drawable);
    }
}
