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

public class AccessibilityDelegateCompat
{
    static class AccessibilityDelegateApi16Impl extends AccessibilityDelegateBaseImpl
    {

        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(android.view.View.AccessibilityDelegate accessibilitydelegate, View view)
        {
            accessibilitydelegate = accessibilitydelegate.getAccessibilityNodeProvider(view);
            if (accessibilitydelegate != null)
            {
                return new AccessibilityNodeProviderCompat(accessibilitydelegate);
            } else
            {
                return null;
            }
        }

        public android.view.View.AccessibilityDelegate newAccessibilityDelegateBridge(AccessibilityDelegateCompat accessibilitydelegatecompat)
        {
            return accessibilitydelegatecompat. new android.view.View.AccessibilityDelegate() {

                final AccessibilityDelegateApi16Impl this$0;
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
                this$0 = final_accessibilitydelegateapi16impl;
                compat = AccessibilityDelegateCompat.this;
                super();
            }
            };
        }

        public boolean performAccessibilityAction(android.view.View.AccessibilityDelegate accessibilitydelegate, View view, int i, Bundle bundle)
        {
            return accessibilitydelegate.performAccessibilityAction(view, i, bundle);
        }

        AccessibilityDelegateApi16Impl()
        {
        }
    }

    static class AccessibilityDelegateBaseImpl
    {

        public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(android.view.View.AccessibilityDelegate accessibilitydelegate, View view)
        {
            return null;
        }

        public android.view.View.AccessibilityDelegate newAccessibilityDelegateBridge(AccessibilityDelegateCompat accessibilitydelegatecompat)
        {
            return accessibilitydelegatecompat. new android.view.View.AccessibilityDelegate() {

                final AccessibilityDelegateBaseImpl this$0;
                final AccessibilityDelegateCompat val$compat;

                public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
                {
                    return compat.dispatchPopulateAccessibilityEvent(view, accessibilityevent);
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

                public void sendAccessibilityEvent(View view, int i)
                {
                    compat.sendAccessibilityEvent(view, i);
                }

                public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityevent)
                {
                    compat.sendAccessibilityEventUnchecked(view, accessibilityevent);
                }

            
            {
                this$0 = final_accessibilitydelegatebaseimpl;
                compat = AccessibilityDelegateCompat.this;
                super();
            }
            };
        }

        public boolean performAccessibilityAction(android.view.View.AccessibilityDelegate accessibilitydelegate, View view, int i, Bundle bundle)
        {
            return false;
        }

        AccessibilityDelegateBaseImpl()
        {
        }
    }


    private static final android.view.View.AccessibilityDelegate DEFAULT_DELEGATE = new android.view.View.AccessibilityDelegate();
    private static final AccessibilityDelegateBaseImpl IMPL;
    final android.view.View.AccessibilityDelegate mBridge;

    public AccessibilityDelegateCompat()
    {
        mBridge = IMPL.newAccessibilityDelegateBridge(this);
    }

    public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        return DEFAULT_DELEGATE.dispatchPopulateAccessibilityEvent(view, accessibilityevent);
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view)
    {
        return IMPL.getAccessibilityNodeProvider(DEFAULT_DELEGATE, view);
    }

    android.view.View.AccessibilityDelegate getBridge()
    {
        return mBridge;
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        DEFAULT_DELEGATE.onInitializeAccessibilityEvent(view, accessibilityevent);
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        DEFAULT_DELEGATE.onInitializeAccessibilityNodeInfo(view, (AccessibilityNodeInfo)accessibilitynodeinfocompat.getInfo());
    }

    public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        DEFAULT_DELEGATE.onPopulateAccessibilityEvent(view, accessibilityevent);
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup viewgroup, View view, AccessibilityEvent accessibilityevent)
    {
        return DEFAULT_DELEGATE.onRequestSendAccessibilityEvent(viewgroup, view, accessibilityevent);
    }

    public boolean performAccessibilityAction(View view, int i, Bundle bundle)
    {
        return IMPL.performAccessibilityAction(DEFAULT_DELEGATE, view, i, bundle);
    }

    public void sendAccessibilityEvent(View view, int i)
    {
        DEFAULT_DELEGATE.sendAccessibilityEvent(view, i);
    }

    public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityevent)
    {
        DEFAULT_DELEGATE.sendAccessibilityEventUnchecked(view, accessibilityevent);
    }

    static 
    {
        if (android.os.Build.VERSION.SDK_INT >= 16)
        {
            IMPL = new AccessibilityDelegateApi16Impl();
        } else
        {
            IMPL = new AccessibilityDelegateBaseImpl();
        }
    }
}
