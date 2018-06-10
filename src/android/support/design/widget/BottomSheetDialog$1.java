// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.view.View;

// Referenced classes of package android.support.design.widget:
//            BottomSheetDialog

class this._cls0
    implements android.view.
{

    final BottomSheetDialog this$0;

    public void onClick(View view)
    {
        if (mCancelable && isShowing() && shouldWindowCloseOnTouchOutside())
        {
            cancel();
        }
    }

    ()
    {
        this$0 = BottomSheetDialog.this;
        super();
    }
}
