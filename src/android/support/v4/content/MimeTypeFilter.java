// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content;

import java.util.ArrayList;

public final class MimeTypeFilter
{

    private MimeTypeFilter()
    {
    }

    public static String matches(String s, String as[])
    {
        if (s != null) goto _L2; else goto _L1
_L1:
        s = null;
_L4:
        return s;
_L2:
        String as1[] = s.split("/");
        int j = as.length;
        int i = 0;
label0:
        do
        {
label1:
            {
                if (i >= j)
                {
                    break label1;
                }
                String s1 = as[i];
                s = s1;
                if (mimeTypeAgainstFilter(as1, s1.split("/")))
                {
                    break label0;
                }
                i++;
            }
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
        return null;
    }

    public static String matches(String as[], String s)
    {
        if (as != null) goto _L2; else goto _L1
_L1:
        s = null;
_L4:
        return s;
_L2:
        String as1[] = s.split("/");
        int j = as.length;
        int i = 0;
label0:
        do
        {
label1:
            {
                if (i >= j)
                {
                    break label1;
                }
                String s1 = as[i];
                s = s1;
                if (mimeTypeAgainstFilter(s1.split("/"), as1))
                {
                    break label0;
                }
                i++;
            }
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
        return null;
    }

    public static boolean matches(String s, String s1)
    {
        if (s == null)
        {
            return false;
        } else
        {
            return mimeTypeAgainstFilter(s.split("/"), s1.split("/"));
        }
    }

    public static String[] matchesMany(String as[], String s)
    {
        int i = 0;
        if (as == null)
        {
            return new String[0];
        }
        ArrayList arraylist = new ArrayList();
        s = s.split("/");
        for (int j = as.length; i < j; i++)
        {
            String s1 = as[i];
            if (mimeTypeAgainstFilter(s1.split("/"), s))
            {
                arraylist.add(s1);
            }
        }

        return (String[])arraylist.toArray(new String[arraylist.size()]);
    }

    private static boolean mimeTypeAgainstFilter(String as[], String as1[])
    {
        while (as.length != 2 || as1.length != 2 || !"*".equals(as1[0]) && !as1[0].equals(as[0]) || !"*".equals(as1[1]) && !as1[1].equals(as[1])) 
        {
            return false;
        }
        return true;
    }
}
