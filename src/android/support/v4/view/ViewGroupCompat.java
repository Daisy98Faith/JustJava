// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

// Referenced classes of package android.support.v4.view:
//            NestedScrollingParent

public final class ViewGroupCompat
{
    static class ViewGroupCompatApi18Impl extends ViewGroupCompatBaseImpl
    {

        public int getLayoutMode(ViewGroup viewgroup)
        {
            return viewgroup.getLayoutMode();
        }

        public void setLayoutMode(ViewGroup viewgroup, int i)
        {
            viewgroup.setLayoutMode(i);
        }

        ViewGroupCompatApi18Impl()
        {
        }
    }

    static class ViewGroupCompatApi21Impl extends ViewGroupCompatApi18Impl
    {

        public int getNestedScrollAxes(ViewGroup viewgroup)
        {
            return viewgroup.getNestedScrollAxes();
        }

        public boolean isTransitionGroup(ViewGroup viewgroup)
        {
            return viewgroup.isTransitionGroup();
        }

        public void setTransitionGroup(ViewGroup viewgroup, boolean flag)
        {
            viewgroup.setTransitionGroup(flag);
        }

        ViewGroupCompatApi21Impl()
        {
        }
    }

    static class ViewGroupCompatBaseImpl
    {

        public int getLayoutMode(ViewGroup viewgroup)
        {
            return 0;
        }

        public int getNestedScrollAxes(ViewGroup viewgroup)
        {
            if (viewgroup instanceof NestedScrollingParent)
            {
                return ((NestedScrollingParent)viewgroup).getNestedScrollAxes();
            } else
            {
                return 0;
            }
        }

        public boolean isTransitionGroup(ViewGroup viewgroup)
        {
            return false;
        }

        public void setLayoutMode(ViewGroup viewgroup, int i)
        {
        }

        public void setTransitionGroup(ViewGroup viewgroup, boolean flag)
        {
        }

        ViewGroupCompatBaseImpl()
        {
        }
    }


    static final ViewGroupCompatBaseImpl IMPL;
    public static final int LAYOUT_MODE_CLIP_BOUNDS = 0;
    public static final int LAYOUT_MODE_OPTICAL_BOUNDS = 1;

    private ViewGroupCompat()
    {
    }

    public static int getLayoutMode(ViewGroup viewgroup)
    {
        return IMPL.getLayoutMode(viewgroup);
    }

    public static int getNestedScrollAxes(ViewGroup viewgroup)
    {
        return IMPL.getNestedScrollAxes(viewgroup);
    }

    public static boolean isTransitionGroup(ViewGroup viewgroup)
    {
        return IMPL.isTransitionGroup(viewgroup);
    }

    public static boolean onRequestSendAccessibilityEvent(ViewGroup viewgroup, View view, AccessibilityEvent accessibilityevent)
    {
        return viewgroup.onRequestSendAccessibilityEvent(view, accessibilityevent);
    }

    public static void setLayoutMode(ViewGroup viewgroup, int i)
    {
        IMPL.setLayoutMode(viewgroup, i);
    }

    public static void setMotionEventSplittingEnabled(ViewGroup viewgroup, boolean flag)
    {
        viewgroup.setMotionEventSplittingEnabled(flag);
    }

    public static void setTransitionGroup(ViewGroup viewgroup, boolean flag)
    {
        IMPL.setTransitionGroup(viewgroup, flag);
    }

    static 
    {
        int i = android.os.Build.VERSION.SDK_INT;
        if (i >= 21)
        {
            IMPL = new ViewGroupCompatApi21Impl();
        } else
        if (i >= 18)
        {
            IMPL = new ViewGroupCompatApi18Impl();
        } else
        {
            IMPL = new ViewGroupCompatBaseImpl();
        }
    }
}
