// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.view.Menu;
import java.util.List;

// Referenced classes of package android.support.v7.app:
//            AppCompatDelegateImplN

class V23 extends V23
{

    final AppCompatDelegateImplN this$0;

    public void onProvideKeyboardShortcuts(List list, Menu menu, int i)
    {
        V23 v23 = getPanelState(0, true);
        if (v23 != null && v23.V23 != null)
        {
            super.onProvideKeyboardShortcuts(list, v23.onProvideKeyboardShortcuts, i);
            return;
        } else
        {
            super.onProvideKeyboardShortcuts(list, menu, i);
            return;
        }
    }

    V23(android.view.CallbackN callbackn)
    {
        this$0 = AppCompatDelegateImplN.this;
        super(AppCompatDelegateImplN.this, callbackn);
    }
}
