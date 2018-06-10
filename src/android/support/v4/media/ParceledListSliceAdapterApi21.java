// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.media;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

class ParceledListSliceAdapterApi21
{

    private static Constructor sConstructor;

    ParceledListSliceAdapterApi21()
    {
    }

    static Object newInstance(List list)
    {
        list = ((List) (sConstructor.newInstance(new Object[] {
            list
        })));
        return list;
        list;
_L2:
        list.printStackTrace();
        return null;
        list;
        continue; /* Loop/switch isn't completed */
        list;
        if (true) goto _L2; else goto _L1
_L1:
    }

    static 
    {
        sConstructor = Class.forName("android.content.pm.ParceledListSlice").getConstructor(new Class[] {
            java/util/List
        });
        return;
        Object obj;
        obj;
_L2:
        ((ReflectiveOperationException) (obj)).printStackTrace();
        return;
        obj;
        if (true) goto _L2; else goto _L1
_L1:
    }
}
