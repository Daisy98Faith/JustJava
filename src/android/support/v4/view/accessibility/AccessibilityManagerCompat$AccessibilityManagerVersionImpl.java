// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityManager;
import java.util.List;

// Referenced classes of package android.support.v4.view.accessibility:
//            AccessibilityManagerCompat

static interface ner
{

    public abstract boolean addAccessibilityStateChangeListener(AccessibilityManager accessibilitymanager,  );

    public abstract boolean addTouchExplorationStateChangeListener(AccessibilityManager accessibilitymanager, ner ner);

    public abstract List getEnabledAccessibilityServiceList(AccessibilityManager accessibilitymanager, int i);

    public abstract List getInstalledAccessibilityServiceList(AccessibilityManager accessibilitymanager);

    public abstract boolean isTouchExplorationEnabled(AccessibilityManager accessibilitymanager);

    public abstract nerWrapper newAccessibilityStateChangeListener( );

    public abstract eListenerWrapper newTouchExplorationStateChangeListener(ner ner);

    public abstract boolean removeAccessibilityStateChangeListener(AccessibilityManager accessibilitymanager,  );

    public abstract boolean removeTouchExplorationStateChangeListener(AccessibilityManager accessibilitymanager, ner ner);
}
