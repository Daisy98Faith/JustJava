// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.view.View;
import android.view.WindowId;

// Referenced classes of package android.support.transition:
//            WindowIdImpl

class WindowIdApi18
    implements WindowIdImpl
{

    private final WindowId mWindowId;

    WindowIdApi18(View view)
    {
        mWindowId = view.getWindowId();
    }

    public boolean equals(Object obj)
    {
        return (obj instanceof WindowIdApi18) && ((WindowIdApi18)obj).mWindowId.equals(mWindowId);
    }
}
