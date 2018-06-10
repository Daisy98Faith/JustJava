// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

// Referenced classes of package android.support.v4.widget:
//            PopupWindowCompat

static class 
    implements 
{

    private static Method sGetWindowLayoutTypeMethod;
    private static boolean sGetWindowLayoutTypeMethodAttempted;
    private static Method sSetWindowLayoutTypeMethod;
    private static boolean sSetWindowLayoutTypeMethodAttempted;

    public boolean getOverlapAnchor(PopupWindow popupwindow)
    {
        return false;
    }

    public int getWindowLayoutType(PopupWindow popupwindow)
    {
        if (!sGetWindowLayoutTypeMethodAttempted)
        {
            int i;
            try
            {
                sGetWindowLayoutTypeMethod = android/widget/PopupWindow.getDeclaredMethod("getWindowLayoutType", new Class[0]);
                sGetWindowLayoutTypeMethod.setAccessible(true);
            }
            catch (Exception exception) { }
            sGetWindowLayoutTypeMethodAttempted = true;
        }
        if (sGetWindowLayoutTypeMethod == null)
        {
            break MISSING_BLOCK_LABEL_58;
        }
        i = ((Integer)sGetWindowLayoutTypeMethod.invoke(popupwindow, new Object[0])).intValue();
        return i;
        popupwindow;
        return 0;
    }

    public void setOverlapAnchor(PopupWindow popupwindow, boolean flag)
    {
    }

    public void setWindowLayoutType(PopupWindow popupwindow, int i)
    {
        if (!sSetWindowLayoutTypeMethodAttempted)
        {
            try
            {
                sSetWindowLayoutTypeMethod = android/widget/PopupWindow.getDeclaredMethod("setWindowLayoutType", new Class[] {
                    Integer.TYPE
                });
                sSetWindowLayoutTypeMethod.setAccessible(true);
            }
            catch (Exception exception) { }
            sSetWindowLayoutTypeMethodAttempted = true;
        }
        if (sSetWindowLayoutTypeMethod == null)
        {
            break MISSING_BLOCK_LABEL_62;
        }
        sSetWindowLayoutTypeMethod.invoke(popupwindow, new Object[] {
            Integer.valueOf(i)
        });
        return;
        popupwindow;
    }

    public void showAsDropDown(PopupWindow popupwindow, View view, int i, int j, int k)
    {
        int l = i;
        if ((GravityCompat.getAbsoluteGravity(k, ViewCompat.getLayoutDirection(view)) & 7) == 5)
        {
            l = i - (popupwindow.getWidth() - view.getWidth());
        }
        popupwindow.showAsDropDown(view, l, j);
    }

    ()
    {
    }
}
