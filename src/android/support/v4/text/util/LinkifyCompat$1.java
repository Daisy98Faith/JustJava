// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.text.util;

import java.util.Comparator;

// Referenced classes of package android.support.v4.text.util:
//            LinkifyCompat

static final class nkSpec
    implements Comparator
{

    public final int compare(nkSpec nkspec, nkSpec nkspec1)
    {
        if (nkspec.start >= nkspec1.start)
        {
            if (nkspec.start > nkspec1.start)
            {
                return 1;
            }
            if (nkspec.end < nkspec1.end)
            {
                return 1;
            }
            if (nkspec.end <= nkspec1.end)
            {
                return 0;
            }
        }
        return -1;
    }

    public volatile int compare(Object obj, Object obj1)
    {
        return compare((nkSpec)obj, (nkSpec)obj1);
    }

    nkSpec()
    {
    }
}
