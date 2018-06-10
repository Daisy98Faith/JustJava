// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.util;


// Referenced classes of package android.support.v7.util:
//            ListUpdateCallback, DiffUtil

class 
    implements ListUpdateCallback
{

    final d this$0;
    final android.support.v7.widget.otifyItemRangeRemoved val$adapter;

    public void onChanged(int i, int j, Object obj)
    {
        val$adapter.otifyItemRangeChanged(i, j, obj);
    }

    public void onInserted(int i, int j)
    {
        val$adapter.otifyItemRangeInserted(i, j);
    }

    public void onMoved(int i, int j)
    {
        val$adapter.otifyItemMoved(i, j);
    }

    public void onRemoved(int i, int j)
    {
        val$adapter.otifyItemRangeRemoved(i, j);
    }

    ()
    {
        this$0 = final_;
        val$adapter = android.support.v7.widget.l.adapter.this;
        super();
    }
}
