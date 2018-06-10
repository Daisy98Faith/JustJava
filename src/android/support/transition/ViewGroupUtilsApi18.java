// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.util.Log;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Referenced classes of package android.support.transition:
//            ViewGroupUtilsApi14, ViewGroupOverlayApi18, ViewGroupOverlayImpl

class ViewGroupUtilsApi18 extends ViewGroupUtilsApi14
{

    private static final String TAG = "ViewUtilsApi18";
    private static Method sSuppressLayoutMethod;
    private static boolean sSuppressLayoutMethodFetched;

    ViewGroupUtilsApi18()
    {
    }

    private void fetchSuppressLayoutMethod()
    {
        if (!sSuppressLayoutMethodFetched)
        {
            try
            {
                sSuppressLayoutMethod = android/view/ViewGroup.getDeclaredMethod("suppressLayout", new Class[] {
                    Boolean.TYPE
                });
                sSuppressLayoutMethod.setAccessible(true);
            }
            catch (NoSuchMethodException nosuchmethodexception)
            {
                Log.i("ViewUtilsApi18", "Failed to retrieve suppressLayout method", nosuchmethodexception);
            }
            sSuppressLayoutMethodFetched = true;
        }
    }

    public ViewGroupOverlayImpl getOverlay(ViewGroup viewgroup)
    {
        return new ViewGroupOverlayApi18(viewgroup);
    }

    public void suppressLayout(ViewGroup viewgroup, boolean flag)
    {
        fetchSuppressLayoutMethod();
        if (sSuppressLayoutMethod == null)
        {
            break MISSING_BLOCK_LABEL_29;
        }
        sSuppressLayoutMethod.invoke(viewgroup, new Object[] {
            Boolean.valueOf(flag)
        });
        return;
        viewgroup;
        Log.i("ViewUtilsApi18", "Failed to invoke suppressLayout method", viewgroup);
        return;
        viewgroup;
        Log.i("ViewUtilsApi18", "Error invoking suppressLayout method", viewgroup);
        return;
    }
}
