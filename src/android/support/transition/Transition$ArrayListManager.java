// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import java.util.ArrayList;

// Referenced classes of package android.support.transition:
//            Transition

private static class 
{

    static ArrayList add(ArrayList arraylist, Object obj)
    {
        ArrayList arraylist1 = arraylist;
        if (arraylist == null)
        {
            arraylist1 = new ArrayList();
        }
        if (!arraylist1.contains(obj))
        {
            arraylist1.add(obj);
        }
        return arraylist1;
    }

    static ArrayList remove(ArrayList arraylist, Object obj)
    {
        ArrayList arraylist1 = arraylist;
        if (arraylist != null)
        {
            arraylist.remove(obj);
            arraylist1 = arraylist;
            if (arraylist.isEmpty())
            {
                arraylist1 = null;
            }
        }
        return arraylist1;
    }

    private ()
    {
    }
}
