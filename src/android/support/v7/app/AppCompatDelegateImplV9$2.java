// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.view.View;

// Referenced classes of package android.support.v7.app:
//            AppCompatDelegateImplV9

class this._cls0
    implements OnApplyWindowInsetsListener
{

    final AppCompatDelegateImplV9 this$0;

    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowinsetscompat)
    {
        int i = windowinsetscompat.getSystemWindowInsetTop();
        int j = updateStatusGuard(i);
        WindowInsetsCompat windowinsetscompat1 = windowinsetscompat;
        if (i != j)
        {
            windowinsetscompat1 = windowinsetscompat.replaceSystemWindowInsets(windowinsetscompat.getSystemWindowInsetLeft(), j, windowinsetscompat.getSystemWindowInsetRight(), windowinsetscompat.getSystemWindowInsetBottom());
        }
        return ViewCompat.onApplyWindowInsets(view, windowinsetscompat1);
    }

    er()
    {
        this$0 = AppCompatDelegateImplV9.this;
        super();
    }
}
