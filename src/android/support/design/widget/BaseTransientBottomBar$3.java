// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.os.Handler;

// Referenced classes of package android.support.design.widget:
//            BaseTransientBottomBar

class this._cls0
    implements this._cls0
{

    final BaseTransientBottomBar this$0;

    public void dismiss(int i)
    {
        BaseTransientBottomBar.sHandler.sendMessage(BaseTransientBottomBar.sHandler.obtainMessage(1, i, 0, BaseTransientBottomBar.this));
    }

    public void show()
    {
        BaseTransientBottomBar.sHandler.sendMessage(BaseTransientBottomBar.sHandler.obtainMessage(0, BaseTransientBottomBar.this));
    }

    ()
    {
        this$0 = BaseTransientBottomBar.this;
        super();
    }
}
