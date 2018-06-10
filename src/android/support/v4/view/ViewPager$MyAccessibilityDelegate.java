// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

// Referenced classes of package android.support.v4.view:
//            AccessibilityDelegateCompat, ViewPager, PagerAdapter

class  extends AccessibilityDelegateCompat
{

    final ViewPager this$0;

    private boolean canScroll()
    {
        return mAdapter != null && mAdapter.getCount() > 1;
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEvent(view, accessibilityevent);
        accessibilityevent.setClassName(android/support/v4/view/ViewPager.getName());
        view = AccessibilityEventCompat.asRecord(accessibilityevent);
        view.setScrollable(canScroll());
        if (accessibilityevent.getEventType() == 4096 && mAdapter != null)
        {
            view.setItemCount(mAdapter.getCount());
            view.setFromIndex(mCurItem);
            view.setToIndex(mCurItem);
        }
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
        accessibilitynodeinfocompat.setClassName(android/support/v4/view/ViewPager.getName());
        accessibilitynodeinfocompat.setScrollable(canScroll());
        if (canScrollHorizontally(1))
        {
            accessibilitynodeinfocompat.addAction(4096);
        }
        if (canScrollHorizontally(-1))
        {
            accessibilitynodeinfocompat.addAction(8192);
        }
    }

    public boolean performAccessibilityAction(View view, int i, Bundle bundle)
    {
        if (super.performAccessibilityAction(view, i, bundle))
        {
            return true;
        }
        switch (i)
        {
        default:
            return false;

        case 4096: 
            if (canScrollHorizontally(1))
            {
                setCurrentItem(mCurItem + 1);
                return true;
            } else
            {
                return false;
            }

        case 8192: 
            break;
        }
        if (canScrollHorizontally(-1))
        {
            setCurrentItem(mCurItem - 1);
            return true;
        } else
        {
            return false;
        }
    }

    oCompat()
    {
        this$0 = ViewPager.this;
        super();
    }
}
