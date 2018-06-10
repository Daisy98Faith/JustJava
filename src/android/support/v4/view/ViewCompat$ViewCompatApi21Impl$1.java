// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.view.View;
import android.view.WindowInsets;

// Referenced classes of package android.support.v4.view:
//            ViewCompat, WindowInsetsCompat, OnApplyWindowInsetsListener

class val.listener
    implements android.view.pi21Impl._cls1
{

    final lyWindowInsets this$0;
    final OnApplyWindowInsetsListener val$listener;

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowinsets)
    {
        windowinsets = WindowInsetsCompat.wrap(windowinsets);
        return (WindowInsets)WindowInsetsCompat.unwrap(val$listener.onApplyWindowInsets(view, windowinsets));
    }

    ()
    {
        this$0 = final_;
        val$listener = OnApplyWindowInsetsListener.this;
        super();
    }
}
