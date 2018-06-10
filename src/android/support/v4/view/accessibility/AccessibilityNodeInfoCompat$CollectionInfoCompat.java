// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view.accessibility;


// Referenced classes of package android.support.v4.view.accessibility:
//            AccessibilityNodeInfoCompat

public static class mInfo
{

    public static final int SELECTION_MODE_MULTIPLE = 2;
    public static final int SELECTION_MODE_NONE = 0;
    public static final int SELECTION_MODE_SINGLE = 1;
    final Object mInfo;

    public static mInfo obtain(int i, int j, boolean flag)
    {
        return new <init>(AccessibilityNodeInfoCompat.IMPL.obtainCollectionInfo(i, j, flag));
    }

    public static Impl.obtainCollectionInfo obtain(int i, int j, boolean flag, int k)
    {
        return new <init>(AccessibilityNodeInfoCompat.IMPL.obtainCollectionInfo(i, j, flag, k));
    }

    public int getColumnCount()
    {
        return AccessibilityNodeInfoCompat.IMPL.getCollectionInfoColumnCount(mInfo);
    }

    public int getRowCount()
    {
        return AccessibilityNodeInfoCompat.IMPL.getCollectionInfoRowCount(mInfo);
    }

    public int getSelectionMode()
    {
        return AccessibilityNodeInfoCompat.IMPL.getCollectionInfoSelectionMode(mInfo);
    }

    public boolean isHierarchical()
    {
        return AccessibilityNodeInfoCompat.IMPL.isCollectionInfoHierarchical(mInfo);
    }

    Impl(Object obj)
    {
        mInfo = obj;
    }
}
