// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;

// Referenced classes of package android.support.v4.view:
//            NestedScrollingParent

public final class ViewParentCompat
{
    static class ViewParentCompatApi19Impl extends ViewParentCompatBaseImpl
    {

        public void notifySubtreeAccessibilityStateChanged(ViewParent viewparent, View view, View view1, int i)
        {
            viewparent.notifySubtreeAccessibilityStateChanged(view, view1, i);
        }

        ViewParentCompatApi19Impl()
        {
        }
    }

    static class ViewParentCompatApi21Impl extends ViewParentCompatApi19Impl
    {

        public boolean onNestedFling(ViewParent viewparent, View view, float f, float f1, boolean flag)
        {
            try
            {
                flag = viewparent.onNestedFling(view, f, f1, flag);
            }
            // Misplaced declaration of an exception variable
            catch (View view)
            {
                Log.e("ViewParentCompat", (new StringBuilder()).append("ViewParent ").append(viewparent).append(" does not implement interface ").append("method onNestedFling").toString(), view);
                return false;
            }
            return flag;
        }

        public boolean onNestedPreFling(ViewParent viewparent, View view, float f, float f1)
        {
            boolean flag;
            try
            {
                flag = viewparent.onNestedPreFling(view, f, f1);
            }
            // Misplaced declaration of an exception variable
            catch (View view)
            {
                Log.e("ViewParentCompat", (new StringBuilder()).append("ViewParent ").append(viewparent).append(" does not implement interface ").append("method onNestedPreFling").toString(), view);
                return false;
            }
            return flag;
        }

        public void onNestedPreScroll(ViewParent viewparent, View view, int i, int j, int ai[])
        {
            try
            {
                viewparent.onNestedPreScroll(view, i, j, ai);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (View view)
            {
                Log.e("ViewParentCompat", (new StringBuilder()).append("ViewParent ").append(viewparent).append(" does not implement interface ").append("method onNestedPreScroll").toString(), view);
            }
        }

        public void onNestedScroll(ViewParent viewparent, View view, int i, int j, int k, int l)
        {
            try
            {
                viewparent.onNestedScroll(view, i, j, k, l);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (View view)
            {
                Log.e("ViewParentCompat", (new StringBuilder()).append("ViewParent ").append(viewparent).append(" does not implement interface ").append("method onNestedScroll").toString(), view);
            }
        }

        public void onNestedScrollAccepted(ViewParent viewparent, View view, View view1, int i)
        {
            try
            {
                viewparent.onNestedScrollAccepted(view, view1, i);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (View view)
            {
                Log.e("ViewParentCompat", (new StringBuilder()).append("ViewParent ").append(viewparent).append(" does not implement interface ").append("method onNestedScrollAccepted").toString(), view);
            }
        }

        public boolean onStartNestedScroll(ViewParent viewparent, View view, View view1, int i)
        {
            boolean flag;
            try
            {
                flag = viewparent.onStartNestedScroll(view, view1, i);
            }
            // Misplaced declaration of an exception variable
            catch (View view)
            {
                Log.e("ViewParentCompat", (new StringBuilder()).append("ViewParent ").append(viewparent).append(" does not implement interface ").append("method onStartNestedScroll").toString(), view);
                return false;
            }
            return flag;
        }

        public void onStopNestedScroll(ViewParent viewparent, View view)
        {
            try
            {
                viewparent.onStopNestedScroll(view);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (View view)
            {
                Log.e("ViewParentCompat", (new StringBuilder()).append("ViewParent ").append(viewparent).append(" does not implement interface ").append("method onStopNestedScroll").toString(), view);
            }
        }

        ViewParentCompatApi21Impl()
        {
        }
    }

    static class ViewParentCompatBaseImpl
    {

        public void notifySubtreeAccessibilityStateChanged(ViewParent viewparent, View view, View view1, int i)
        {
        }

        public boolean onNestedFling(ViewParent viewparent, View view, float f, float f1, boolean flag)
        {
            if (viewparent instanceof NestedScrollingParent)
            {
                return ((NestedScrollingParent)viewparent).onNestedFling(view, f, f1, flag);
            } else
            {
                return false;
            }
        }

        public boolean onNestedPreFling(ViewParent viewparent, View view, float f, float f1)
        {
            if (viewparent instanceof NestedScrollingParent)
            {
                return ((NestedScrollingParent)viewparent).onNestedPreFling(view, f, f1);
            } else
            {
                return false;
            }
        }

        public void onNestedPreScroll(ViewParent viewparent, View view, int i, int j, int ai[])
        {
            if (viewparent instanceof NestedScrollingParent)
            {
                ((NestedScrollingParent)viewparent).onNestedPreScroll(view, i, j, ai);
            }
        }

        public void onNestedScroll(ViewParent viewparent, View view, int i, int j, int k, int l)
        {
            if (viewparent instanceof NestedScrollingParent)
            {
                ((NestedScrollingParent)viewparent).onNestedScroll(view, i, j, k, l);
            }
        }

        public void onNestedScrollAccepted(ViewParent viewparent, View view, View view1, int i)
        {
            if (viewparent instanceof NestedScrollingParent)
            {
                ((NestedScrollingParent)viewparent).onNestedScrollAccepted(view, view1, i);
            }
        }

        public boolean onStartNestedScroll(ViewParent viewparent, View view, View view1, int i)
        {
            if (viewparent instanceof NestedScrollingParent)
            {
                return ((NestedScrollingParent)viewparent).onStartNestedScroll(view, view1, i);
            } else
            {
                return false;
            }
        }

        public void onStopNestedScroll(ViewParent viewparent, View view)
        {
            if (viewparent instanceof NestedScrollingParent)
            {
                ((NestedScrollingParent)viewparent).onStopNestedScroll(view);
            }
        }

        ViewParentCompatBaseImpl()
        {
        }
    }


    static final ViewParentCompatBaseImpl IMPL;
    private static final String TAG = "ViewParentCompat";

    private ViewParentCompat()
    {
    }

    public static void notifySubtreeAccessibilityStateChanged(ViewParent viewparent, View view, View view1, int i)
    {
        IMPL.notifySubtreeAccessibilityStateChanged(viewparent, view, view1, i);
    }

    public static boolean onNestedFling(ViewParent viewparent, View view, float f, float f1, boolean flag)
    {
        return IMPL.onNestedFling(viewparent, view, f, f1, flag);
    }

    public static boolean onNestedPreFling(ViewParent viewparent, View view, float f, float f1)
    {
        return IMPL.onNestedPreFling(viewparent, view, f, f1);
    }

    public static void onNestedPreScroll(ViewParent viewparent, View view, int i, int j, int ai[])
    {
        IMPL.onNestedPreScroll(viewparent, view, i, j, ai);
    }

    public static void onNestedScroll(ViewParent viewparent, View view, int i, int j, int k, int l)
    {
        IMPL.onNestedScroll(viewparent, view, i, j, k, l);
    }

    public static void onNestedScrollAccepted(ViewParent viewparent, View view, View view1, int i)
    {
        IMPL.onNestedScrollAccepted(viewparent, view, view1, i);
    }

    public static boolean onStartNestedScroll(ViewParent viewparent, View view, View view1, int i)
    {
        return IMPL.onStartNestedScroll(viewparent, view, view1, i);
    }

    public static void onStopNestedScroll(ViewParent viewparent, View view)
    {
        IMPL.onStopNestedScroll(viewparent, view);
    }

    public static boolean requestSendAccessibilityEvent(ViewParent viewparent, View view, AccessibilityEvent accessibilityevent)
    {
        return viewparent.requestSendAccessibilityEvent(view, accessibilityevent);
    }

    static 
    {
        int i = android.os.Build.VERSION.SDK_INT;
        if (i >= 21)
        {
            IMPL = new ViewParentCompatApi21Impl();
        } else
        if (i >= 19)
        {
            IMPL = new ViewParentCompatApi19Impl();
        } else
        {
            IMPL = new ViewParentCompatBaseImpl();
        }
    }
}
