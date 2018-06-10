// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;


// Referenced classes of package android.support.v7.app:
//            AppCompatDelegateImplV9

class this._cls0
    implements Runnable
{

    final AppCompatDelegateImplV9 this$0;

    public void run()
    {
        if ((mInvalidatePanelMenuFeatures & 1) != 0)
        {
            doInvalidatePanelMenu(0);
        }
        if ((mInvalidatePanelMenuFeatures & 0x1000) != 0)
        {
            doInvalidatePanelMenu(108);
        }
        mInvalidatePanelMenuPosted = false;
        mInvalidatePanelMenuFeatures = 0;
    }

    ()
    {
        this$0 = AppCompatDelegateImplV9.this;
        super();
    }
}
