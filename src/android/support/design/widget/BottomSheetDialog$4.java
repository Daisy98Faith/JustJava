// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.view.View;

// Referenced classes of package android.support.design.widget:
//            BottomSheetDialog

class BottomSheetCallback extends BottomSheetCallback
{

    final BottomSheetDialog this$0;

    public void onSlide(View view, float f)
    {
    }

    public void onStateChanged(View view, int i)
    {
        if (i == 5)
        {
            cancel();
        }
    }

    BottomSheetCallback()
    {
        this$0 = BottomSheetDialog.this;
        super();
    }
}
