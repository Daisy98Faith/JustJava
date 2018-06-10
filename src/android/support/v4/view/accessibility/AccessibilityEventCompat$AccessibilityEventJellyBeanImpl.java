// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityEvent;

// Referenced classes of package android.support.v4.view.accessibility:
//            AccessibilityEventCompat, AccessibilityEventCompatJellyBean

static class  extends 
{

    public int getAction(AccessibilityEvent accessibilityevent)
    {
        return AccessibilityEventCompatJellyBean.getAction(accessibilityevent);
    }

    public int getMovementGranularity(AccessibilityEvent accessibilityevent)
    {
        return AccessibilityEventCompatJellyBean.getMovementGranularity(accessibilityevent);
    }

    public void setAction(AccessibilityEvent accessibilityevent, int i)
    {
        AccessibilityEventCompatJellyBean.setAction(accessibilityevent, i);
    }

    public void setMovementGranularity(AccessibilityEvent accessibilityevent, int i)
    {
        AccessibilityEventCompatJellyBean.setMovementGranularity(accessibilityevent, i);
    }

    ()
    {
    }
}
