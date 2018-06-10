// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;


// Referenced classes of package android.support.v7.widget:
//            RecyclerView

private class this._cls0
    implements tener
{

    final RecyclerView this$0;

    public void onAnimationFinished(tener tener)
    {
        tener.tener(true);
        if (tener.tener != null && tener.tener == null)
        {
            tener.tener = null;
        }
        tener.tener = null;
        if (!tener(tener) && !removeAnimatingView(tener.tener) && tener.tener())
        {
            removeDetachedView(tener.tener, false);
        }
    }

    tener()
    {
        this$0 = RecyclerView.this;
        super();
    }
}
