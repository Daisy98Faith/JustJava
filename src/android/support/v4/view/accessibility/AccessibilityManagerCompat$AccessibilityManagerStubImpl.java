// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityManager;
import java.util.Collections;
import java.util.List;

// Referenced classes of package android.support.v4.view.accessibility:
//            AccessibilityManagerCompat

static class stener
    implements pl
{

    public boolean addAccessibilityStateChangeListener(AccessibilityManager accessibilitymanager, ner ner)
    {
        return false;
    }

    public boolean addTouchExplorationStateChangeListener(AccessibilityManager accessibilitymanager, stener stener)
    {
        return false;
    }

    public List getEnabledAccessibilityServiceList(AccessibilityManager accessibilitymanager, int i)
    {
        return Collections.emptyList();
    }

    public List getInstalledAccessibilityServiceList(AccessibilityManager accessibilitymanager)
    {
        return Collections.emptyList();
    }

    public boolean isTouchExplorationEnabled(AccessibilityManager accessibilitymanager)
    {
        return false;
    }

    public stenerWrapper newAccessibilityStateChangeListener(ner ner)
    {
        return null;
    }

    public angeListenerWrapper newTouchExplorationStateChangeListener(stener stener)
    {
        return null;
    }

    public boolean removeAccessibilityStateChangeListener(AccessibilityManager accessibilitymanager, ner ner)
    {
        return false;
    }

    public boolean removeTouchExplorationStateChangeListener(AccessibilityManager accessibilitymanager, stener stener)
    {
        return false;
    }

    stener()
    {
    }
}
