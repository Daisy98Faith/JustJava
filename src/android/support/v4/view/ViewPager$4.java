// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.graphics.Rect;
import android.view.View;

// Referenced classes of package android.support.v4.view:
//            OnApplyWindowInsetsListener, ViewPager, ViewCompat, WindowInsetsCompat

class this._cls0
    implements OnApplyWindowInsetsListener
{

    private final Rect mTempRect = new Rect();
    final ViewPager this$0;

    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowinsetscompat)
    {
        view = ViewCompat.onApplyWindowInsets(view, windowinsetscompat);
        if (view.isConsumed())
        {
            return view;
        }
        windowinsetscompat = mTempRect;
        windowinsetscompat.left = view.getSystemWindowInsetLeft();
        windowinsetscompat.top = view.getSystemWindowInsetTop();
        windowinsetscompat.right = view.getSystemWindowInsetRight();
        windowinsetscompat.bottom = view.getSystemWindowInsetBottom();
        int i = 0;
        for (int j = getChildCount(); i < j; i++)
        {
            WindowInsetsCompat windowinsetscompat1 = ViewCompat.dispatchApplyWindowInsets(getChildAt(i), view);
            windowinsetscompat.left = Math.min(windowinsetscompat1.getSystemWindowInsetLeft(), ((Rect) (windowinsetscompat)).left);
            windowinsetscompat.top = Math.min(windowinsetscompat1.getSystemWindowInsetTop(), ((Rect) (windowinsetscompat)).top);
            windowinsetscompat.right = Math.min(windowinsetscompat1.getSystemWindowInsetRight(), ((Rect) (windowinsetscompat)).right);
            windowinsetscompat.bottom = Math.min(windowinsetscompat1.getSystemWindowInsetBottom(), ((Rect) (windowinsetscompat)).bottom);
        }

        return view.replaceSystemWindowInsets(((Rect) (windowinsetscompat)).left, ((Rect) (windowinsetscompat)).top, ((Rect) (windowinsetscompat)).right, ((Rect) (windowinsetscompat)).bottom);
    }

    Compat()
    {
        this$0 = ViewPager.this;
        super();
    }
}
