// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.WindowInsets;

// Referenced classes of package android.support.v4.view:
//            ViewCompat, WindowInsetsCompat, OnApplyWindowInsetsListener

static class _cls1.val.listener extends _cls1.val.listener
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

    public android.graphics._cls21Impl getBackgroundTintMode(View view)
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
        android.view.ViewParent viewparent = view.getParent();
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
        android.view.ViewParent viewparent = view.getParent();
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
        if (android.os.patApi21Impl == 21)
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

    public void setBackgroundTintMode(View view, android.graphics._cls21Impl _pcls21impl)
    {
        view.setBackgroundTintMode(_pcls21impl);
        if (android.os.patApi21Impl == 21)
        {
            _pcls21impl = view.getBackground();
            boolean flag;
            if (view.getBackgroundTintList() != null && view.getBackgroundTintMode() != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (_pcls21impl != null && flag)
            {
                if (_pcls21impl.isStateful())
                {
                    _pcls21impl.setState(view.getDrawableState());
                }
                view.setBackground(_pcls21impl);
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

    public void setOnApplyWindowInsetsListener(View view, final OnApplyWindowInsetsListener listener)
    {
        if (listener == null)
        {
            view.setOnApplyWindowInsetsListener(null);
            return;
        } else
        {
            view.setOnApplyWindowInsetsListener(new android.view.View.OnApplyWindowInsetsListener() {

                final ViewCompat.ViewCompatApi21Impl this$0;
                final OnApplyWindowInsetsListener val$listener;

                public WindowInsets onApplyWindowInsets(View view1, WindowInsets windowinsets)
                {
                    windowinsets = WindowInsetsCompat.wrap(windowinsets);
                    return (WindowInsets)WindowInsetsCompat.unwrap(listener.onApplyWindowInsets(view1, windowinsets));
                }

            
            {
                this$0 = ViewCompat.ViewCompatApi21Impl.this;
                listener = onapplywindowinsetslistener;
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

    _cls1.val.listener()
    {
    }
}
