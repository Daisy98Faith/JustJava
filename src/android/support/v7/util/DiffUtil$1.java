// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.util;

import java.util.Comparator;

// Referenced classes of package android.support.v7.util:
//            DiffUtil

static final class ake
    implements Comparator
{

    public int compare(ake ake, ake ake1)
    {
        int j = ake.x - ake1.x;
        int i = j;
        if (j == 0)
        {
            i = ake.y - ake1.y;
        }
        return i;
    }

    public volatile int compare(Object obj, Object obj1)
    {
        return compare((ake)obj, (ake)obj1);
    }

    ake()
    {
    }
}
