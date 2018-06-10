// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.view.View;

// Referenced classes of package android.support.design.widget:
//            BaseTransientBottomBar, SnackbarManager

class this._cls0
    implements smissListener
{

    final BaseTransientBottomBar this$0;

    public void onDismiss(View view)
    {
        view.setVisibility(8);
        dispatchDismiss(0);
    }

    public void onDragStateChanged(int i)
    {
        switch (i)
        {
        default:
            return;

        case 1: // '\001'
        case 2: // '\002'
            SnackbarManager.getInstance().pauseTimeout(mManagerCallback);
            return;

        case 0: // '\0'
            SnackbarManager.getInstance().restoreTimeoutIfPaused(mManagerCallback);
            return;
        }
    }

    smissListener()
    {
        this$0 = BaseTransientBottomBar.this;
        super();
    }
}
