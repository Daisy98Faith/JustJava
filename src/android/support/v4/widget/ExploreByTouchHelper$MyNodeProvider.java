// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;

// Referenced classes of package android.support.v4.widget:
//            ExploreByTouchHelper

private class rCompat extends AccessibilityNodeProviderCompat
{

    final ExploreByTouchHelper this$0;

    public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int i)
    {
        return AccessibilityNodeInfoCompat.obtain(obtainAccessibilityNodeInfo(i));
    }

    public AccessibilityNodeInfoCompat findFocus(int i)
    {
        if (i == 2)
        {
            i = ExploreByTouchHelper.access$000(ExploreByTouchHelper.this);
        } else
        {
            i = ExploreByTouchHelper.access$100(ExploreByTouchHelper.this);
        }
        if (i == 0x80000000)
        {
            return null;
        } else
        {
            return createAccessibilityNodeInfo(i);
        }
    }

    public boolean performAction(int i, int j, Bundle bundle)
    {
        return ExploreByTouchHelper.this.performAction(i, j, bundle);
    }

    pat()
    {
        this$0 = ExploreByTouchHelper.this;
        super();
    }
}
