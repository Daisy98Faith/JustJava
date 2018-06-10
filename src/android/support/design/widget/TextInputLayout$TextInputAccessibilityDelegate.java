// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.TextView;
import java.util.List;

// Referenced classes of package android.support.design.widget:
//            TextInputLayout, CollapsingTextHelper

private class this._cls0 extends AccessibilityDelegateCompat
{

    final TextInputLayout this$0;

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEvent(view, accessibilityevent);
        accessibilityevent.setClassName(android/support/design/widget/TextInputLayout.getSimpleName());
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
        accessibilitynodeinfocompat.setClassName(android/support/design/widget/TextInputLayout.getSimpleName());
        view = mCollapsingTextHelper.getText();
        if (!TextUtils.isEmpty(view))
        {
            accessibilitynodeinfocompat.setText(view);
        }
        if (mEditText != null)
        {
            accessibilitynodeinfocompat.setLabelFor(mEditText);
        }
        if (mErrorView != null)
        {
            view = mErrorView.getText();
        } else
        {
            view = null;
        }
        if (!TextUtils.isEmpty(view))
        {
            accessibilitynodeinfocompat.setContentInvalid(true);
            accessibilitynodeinfocompat.setError(view);
        }
    }

    public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        super.onPopulateAccessibilityEvent(view, accessibilityevent);
        view = mCollapsingTextHelper.getText();
        if (!TextUtils.isEmpty(view))
        {
            accessibilityevent.getText().add(view);
        }
    }

    ()
    {
        this$0 = TextInputLayout.this;
        super();
    }
}
