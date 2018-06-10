// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;


// Referenced classes of package android.support.v7.widget:
//            GapWorker, RecyclerView

static class 
{

    public int distanceToItem;
    public boolean immediate;
    public int position;
    public RecyclerView view;
    public int viewVelocity;

    public void clear()
    {
        immediate = false;
        viewVelocity = 0;
        distanceToItem = 0;
        view = null;
        position = 0;
    }

    ()
    {
    }
}
