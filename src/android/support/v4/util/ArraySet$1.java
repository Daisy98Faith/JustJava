// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.util;

import java.util.Map;

// Referenced classes of package android.support.v4.util:
//            MapCollections, ArraySet

class ons extends MapCollections
{

    final ArraySet this$0;

    protected void colClear()
    {
        clear();
    }

    protected Object colGetEntry(int i, int j)
    {
        return mArray[i];
    }

    protected Map colGetMap()
    {
        throw new UnsupportedOperationException("not a map");
    }

    protected int colGetSize()
    {
        return mSize;
    }

    protected int colIndexOfKey(Object obj)
    {
        return indexOf(obj);
    }

    protected int colIndexOfValue(Object obj)
    {
        return indexOf(obj);
    }

    protected void colPut(Object obj, Object obj1)
    {
        add(obj);
    }

    protected void colRemoveAt(int i)
    {
        removeAt(i);
    }

    protected Object colSetValue(int i, Object obj)
    {
        throw new UnsupportedOperationException("not a map");
    }

    tion()
    {
        this$0 = ArraySet.this;
        super();
    }
}
