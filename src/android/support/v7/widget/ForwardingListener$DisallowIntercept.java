// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.view.View;
import android.view.ViewParent;

// Referenced classes of package android.support.v7.widget:
//            ForwardingListener

private class this._cls0
    implements Runnable
{

    final ForwardingListener this$0;

    public void run()
    {
        ViewParent viewparent = mSrc.getParent();
        if (viewparent != null)
        {
            viewparent.requestDisallowInterceptTouchEvent(true);
        }
    }

    ()
    {
        this$0 = ForwardingListener.this;
        super();
    }
}
