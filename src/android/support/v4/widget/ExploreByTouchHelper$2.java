// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;

// Referenced classes of package android.support.v4.widget:
//            ExploreByTouchHelper

static final class tyNodeInfoCompat
    implements nAdapter
{

    public AccessibilityNodeInfoCompat get(SparseArrayCompat sparsearraycompat, int i)
    {
        return (AccessibilityNodeInfoCompat)sparsearraycompat.valueAt(i);
    }

    public volatile Object get(Object obj, int i)
    {
        return get((SparseArrayCompat)obj, i);
    }

    public int size(SparseArrayCompat sparsearraycompat)
    {
        return sparsearraycompat.size();
    }

    public volatile int size(Object obj)
    {
        return size((SparseArrayCompat)obj);
    }

    tyNodeInfoCompat()
    {
    }
}
