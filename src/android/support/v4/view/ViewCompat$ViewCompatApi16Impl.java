// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.view.View;
import android.view.ViewParent;

// Referenced classes of package android.support.v4.view:
//            ViewCompat

static class ProviderCompat extends ProviderCompat
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

    ProviderCompat()
    {
    }
}
