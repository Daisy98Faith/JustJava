// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view.accessibility;


// Referenced classes of package android.support.v4.view.accessibility:
//            AccessibilityNodeInfoCompat

public static class mInfo
{

    public static final int RANGE_TYPE_FLOAT = 1;
    public static final int RANGE_TYPE_INT = 0;
    public static final int RANGE_TYPE_PERCENT = 2;
    final Object mInfo;

    public static mInfo obtain(int i, float f, float f1, float f2)
    {
        return new <init>(AccessibilityNodeInfoCompat.IMPL.obtainRangeInfo(i, f, f1, f2));
    }

    public float getCurrent()
    {
        return getCurrent(mInfo);
    }

    public float getMax()
    {
        return getMax(mInfo);
    }

    public float getMin()
    {
        return getMin(mInfo);
    }

    public int getType()
    {
        return getType(mInfo);
    }

    eInfoImpl(Object obj)
    {
        mInfo = obj;
    }
}
