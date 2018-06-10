// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;


// Referenced classes of package android.support.v7.widget:
//            FastScroller, RecyclerView

class ScrollListener extends ScrollListener
{

    final FastScroller this$0;

    public void onScrolled(RecyclerView recyclerview, int i, int j)
    {
        updateScrollPosition(recyclerview.computeHorizontalScrollOffset(), recyclerview.computeVerticalScrollOffset());
    }

    ScrollListener()
    {
        this$0 = FastScroller.this;
        super();
    }
}
