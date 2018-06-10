// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityEvent;

class AccessibilityEventCompatJellyBean
{

    AccessibilityEventCompatJellyBean()
    {
    }

    public static int getAction(AccessibilityEvent accessibilityevent)
    {
        return accessibilityevent.getAction();
    }

    public static int getMovementGranularity(AccessibilityEvent accessibilityevent)
    {
        return accessibilityevent.getMovementGranularity();
    }

    public static void setAction(AccessibilityEvent accessibilityevent, int i)
    {
        accessibilityevent.setAction(i);
    }

    public static void setMovementGranularity(AccessibilityEvent accessibilityevent, int i)
    {
        accessibilityevent.setMovementGranularity(i);
    }
}
