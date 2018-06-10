// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityManager;

// Referenced classes of package android.support.v4.view.accessibility:
//            AccessibilityManagerCompat, AccessibilityManagerCompatKitKat

static class _cls1.val.listener extends _cls1.val.listener
{

    public boolean addTouchExplorationStateChangeListener(AccessibilityManager accessibilitymanager, ener ener)
    {
        return AccessibilityManagerCompatKitKat.addTouchExplorationStateChangeListener(accessibilitymanager, newTouchExplorationStateChangeListener(ener));
    }

    public geListenerWrapper newTouchExplorationStateChangeListener(final ener listener)
    {
        return new geListenerWrapper(listener, new AccessibilityManagerCompatKitKat.TouchExplorationStateChangeListenerBridge() {

            final AccessibilityManagerCompat.AccessibilityManagerKitKatImpl this$0;
            final AccessibilityManagerCompat.TouchExplorationStateChangeListener val$listener;

            public void onTouchExplorationStateChanged(boolean flag)
            {
                listener.onTouchExplorationStateChanged(flag);
            }

            
            {
                this$0 = AccessibilityManagerCompat.AccessibilityManagerKitKatImpl.this;
                listener = touchexplorationstatechangelistener;
                super();
            }
        });
    }

    public boolean removeTouchExplorationStateChangeListener(AccessibilityManager accessibilitymanager, ener ener)
    {
        return AccessibilityManagerCompatKitKat.removeTouchExplorationStateChangeListener(accessibilitymanager, newTouchExplorationStateChangeListener(ener));
    }

    _cls1.val.listener()
    {
    }
}
