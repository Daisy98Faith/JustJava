// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            RecyclerViewAccessibilityDelegate, RecyclerView

public static class mRecyclerViewDelegate extends AccessibilityDelegateCompat
{

    final RecyclerViewAccessibilityDelegate mRecyclerViewDelegate;

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
        if (!mRecyclerViewDelegate.shouldIgnore() && mRecyclerViewDelegate.mRecyclerView.getLayoutManager() != null)
        {
            mRecyclerViewDelegate.mRecyclerView.getLayoutManager().ilityNodeInfoForItem(view, accessibilitynodeinfocompat);
        }
    }

    public boolean performAccessibilityAction(View view, int i, Bundle bundle)
    {
        if (super.performAccessibilityAction(view, i, bundle))
        {
            return true;
        }
        if (!mRecyclerViewDelegate.shouldIgnore() && mRecyclerViewDelegate.mRecyclerView.getLayoutManager() != null)
        {
            return mRecyclerViewDelegate.mRecyclerView.getLayoutManager().ActionForItem(view, i, bundle);
        } else
        {
            return false;
        }
    }

    public (RecyclerViewAccessibilityDelegate recyclerviewaccessibilitydelegate)
    {
        mRecyclerViewDelegate = recyclerviewaccessibilitydelegate;
    }
}
