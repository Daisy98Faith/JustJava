// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view.accessibility;


// Referenced classes of package android.support.v4.view.accessibility:
//            AccessibilityNodeInfoCompat

public static class mInfo
{

    final Object mInfo;

    public static mInfo obtain(int i, int j, int k, int l, boolean flag)
    {
        return new <init>(AccessibilityNodeInfoCompat.IMPL.obtainCollectionItemInfo(i, j, k, l, flag));
    }

    public static .obtainCollectionItemInfo obtain(int i, int j, int k, int l, boolean flag, boolean flag1)
    {
        return new <init>(AccessibilityNodeInfoCompat.IMPL.obtainCollectionItemInfo(i, j, k, l, flag, flag1));
    }

    public int getColumnIndex()
    {
        return AccessibilityNodeInfoCompat.IMPL.getCollectionItemColumnIndex(mInfo);
    }

    public int getColumnSpan()
    {
        return AccessibilityNodeInfoCompat.IMPL.getCollectionItemColumnSpan(mInfo);
    }

    public int getRowIndex()
    {
        return AccessibilityNodeInfoCompat.IMPL.getCollectionItemRowIndex(mInfo);
    }

    public int getRowSpan()
    {
        return AccessibilityNodeInfoCompat.IMPL.getCollectionItemRowSpan(mInfo);
    }

    public boolean isHeading()
    {
        return AccessibilityNodeInfoCompat.IMPL.isCollectionItemHeading(mInfo);
    }

    public boolean isSelected()
    {
        return AccessibilityNodeInfoCompat.IMPL.isCollectionItemSelected(mInfo);
    }

    (Object obj)
    {
        mInfo = obj;
    }
}
