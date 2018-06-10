// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.support.v4.view.ViewCompat;

// Referenced classes of package android.support.v7.widget:
//            ForwardingListener

class rc
    implements android.view.obalLayoutListener
{

    boolean mIsAttached;
    final ForwardingListener this$0;

    public void onGlobalLayout()
    {
        boolean flag = mIsAttached;
        mIsAttached = ViewCompat.isAttachedToWindow(mSrc);
        if (flag && !mIsAttached)
        {
            ForwardingListener.access$000(ForwardingListener.this);
        }
    }

    tener()
    {
        this$0 = ForwardingListener.this;
        super();
        mIsAttached = ViewCompat.isAttachedToWindow(mSrc);
    }
}
