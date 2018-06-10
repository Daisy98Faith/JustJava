// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.util;


// Referenced classes of package android.support.v7.util:
//            DiffUtil

public static abstract class 
{

    public abstract boolean areContentsTheSame(int i, int j);

    public abstract boolean areItemsTheSame(int i, int j);

    public Object getChangePayload(int i, int j)
    {
        return null;
    }

    public abstract int getNewListSize();

    public abstract int getOldListSize();

    public ()
    {
    }
}
