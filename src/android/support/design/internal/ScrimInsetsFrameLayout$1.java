// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.graphics.Rect;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.view.View;

// Referenced classes of package android.support.design.internal:
//            ScrimInsetsFrameLayout

class this._cls0
    implements OnApplyWindowInsetsListener
{

    final ScrimInsetsFrameLayout this$0;

    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowinsetscompat)
    {
        if (mInsets == null)
        {
            mInsets = new Rect();
        }
        mInsets.set(windowinsetscompat.getSystemWindowInsetLeft(), windowinsetscompat.getSystemWindowInsetTop(), windowinsetscompat.getSystemWindowInsetRight(), windowinsetscompat.getSystemWindowInsetBottom());
        onInsetsChanged(windowinsetscompat);
        view = ScrimInsetsFrameLayout.this;
        boolean flag;
        if (!windowinsetscompat.hasSystemWindowInsets() || mInsetForeground == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        view.setWillNotDraw(flag);
        ViewCompat.postInvalidateOnAnimation(ScrimInsetsFrameLayout.this);
        return windowinsetscompat.consumeSystemWindowInsets();
    }

    ()
    {
        this$0 = ScrimInsetsFrameLayout.this;
        super();
    }
}
