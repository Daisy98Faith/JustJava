// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.view.View;
import android.widget.ListView;

public final class ListViewCompat
{

    private ListViewCompat()
    {
    }

    public static void scrollListBy(ListView listview, int i)
    {
        if (android.os.Build.VERSION.SDK_INT >= 19)
        {
            listview.scrollListBy(i);
        } else
        {
            int j = listview.getFirstVisiblePosition();
            if (j != -1)
            {
                View view = listview.getChildAt(0);
                if (view != null)
                {
                    listview.setSelectionFromTop(j, view.getTop() - i);
                    return;
                }
            }
        }
    }
}
