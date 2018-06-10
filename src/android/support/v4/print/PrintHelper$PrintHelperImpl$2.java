// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.print;


// Referenced classes of package android.support.v4.print:
//            PrintHelper

class ack
    implements hCallback
{

    final ack.onFinish this$0;
    final ack val$callback;

    public void onFinish()
    {
        val$callback.onFinish();
    }

    ack()
    {
        this$0 = final_ack;
        val$callback = ack.this;
        super();
    }
}
