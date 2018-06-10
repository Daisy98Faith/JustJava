// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;

// Referenced classes of package android.support.v4.view:
//            AccessibilityDelegateCompat

static class _cls1.val.compat extends _cls1.val.compat
{

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(android.view.eApi16Impl eapi16impl, View view)
    {
        eapi16impl = eapi16impl.eApi16Impl(view);
        if (eapi16impl != null)
        {
            return new AccessibilityNodeProviderCompat(eapi16impl);
        } else
        {
            return null;
        }
    }

    public android.view.eApi16Impl newAccessibilityDelegateBridge(final AccessibilityDelegateCompat compat)
    {
        return new android.view.View.AccessibilityDelegate() {

            final AccessibilityDelegateCompat.AccessibilityDelegateApi16Impl this$0;
            final AccessibilityDelegateCompat val$compat;

            public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
            {
                return compat.dispatchPopulateAccessibilityEvent(view, accessibilityevent);
            }

            public AccessibilityNodeProvider getAccessibilityNodeProvider(View view)
            {
                view = compat.getAccessibilityNodeProvider(view);
                if (view != null)
                {
                    return (AccessibilityNodeProvider)view.getProvider();
                } else
                {
                    return null;
                }
            }

            public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
            {
                compat.onInitializeAccessibilityEvent(view, accessibilityevent);
            }

            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilitynodeinfo)
            {
                compat.onInitializeAccessibilityNodeInfo(view, new AccessibilityNodeInfoCompat(accessibilitynodeinfo));
            }

            public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
            {
                compat.onPopulateAccessibilityEvent(view, accessibilityevent);
            }

            public boolean onRequestSendAccessibilityEvent(ViewGroup viewgroup, View view, AccessibilityEvent accessibilityevent)
            {
                return compat.onRequestSendAccessibilityEvent(viewgroup, view, accessibilityevent);
            }

            public boolean performAccessibilityAction(View view, int i, Bundle bundle)
            {
                return compat.performAccessibilityAction(view, i, bundle);
            }

            public void sendAccessibilityEvent(View view, int i)
            {
                compat.sendAccessibilityEvent(view, i);
            }

            public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityevent)
            {
                compat.sendAccessibilityEventUnchecked(view, accessibilityevent);
            }

            
            {
                this$0 = AccessibilityDelegateCompat.AccessibilityDelegateApi16Impl.this;
                compat = accessibilitydelegatecompat;
                super();
            }
        };
    }

    public boolean performAccessibilityAction(android.view.eApi16Impl eapi16impl, View view, int i, Bundle bundle)
    {
        return eapi16impl.eApi16Impl(view, i, bundle);
    }

    _cls1.val.compat()
    {
    }
}
