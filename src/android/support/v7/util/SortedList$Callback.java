// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.util;

import java.util.Comparator;

// Referenced classes of package android.support.v7.util:
//            ListUpdateCallback, SortedList

public static abstract class 
    implements Comparator, ListUpdateCallback
{

    public abstract boolean areContentsTheSame(Object obj, Object obj1);

    public abstract boolean areItemsTheSame(Object obj, Object obj1);

    public abstract int compare(Object obj, Object obj1);

    public abstract void onChanged(int i, int j);

    public void onChanged(int i, int j, Object obj)
    {
        onChanged(i, j);
    }

    public ()
    {
    }
}
