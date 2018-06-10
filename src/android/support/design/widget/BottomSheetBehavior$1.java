// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.view.View;

// Referenced classes of package android.support.design.widget:
//            BottomSheetBehavior

class val.state
    implements Runnable
{

    final BottomSheetBehavior this$0;
    final View val$child;
    final int val$state;

    public void run()
    {
        startSettlingAnimation(val$child, val$state);
    }

    _cls9()
    {
        this$0 = final_bottomsheetbehavior;
        val$child = view;
        val$state = I.this;
        super();
    }
}
