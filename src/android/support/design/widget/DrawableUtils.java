// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.graphics.drawable.DrawableContainer;
import android.util.Log;
import java.lang.reflect.Method;

class DrawableUtils
{

    private static final String LOG_TAG = "DrawableUtils";
    private static Method sSetConstantStateMethod;
    private static boolean sSetConstantStateMethodFetched;

    private DrawableUtils()
    {
    }

    static boolean setContainerConstantState(DrawableContainer drawablecontainer, android.graphics.drawable.Drawable.ConstantState constantstate)
    {
        return setContainerConstantStateV9(drawablecontainer, constantstate);
    }

    private static boolean setContainerConstantStateV9(DrawableContainer drawablecontainer, android.graphics.drawable.Drawable.ConstantState constantstate)
    {
        if (!sSetConstantStateMethodFetched)
        {
            try
            {
                sSetConstantStateMethod = android/graphics/drawable/DrawableContainer.getDeclaredMethod("setConstantState", new Class[] {
                    android/graphics/drawable/DrawableContainer$DrawableContainerState
                });
                sSetConstantStateMethod.setAccessible(true);
            }
            catch (NoSuchMethodException nosuchmethodexception)
            {
                Log.e("DrawableUtils", "Could not fetch setConstantState(). Oh well.");
            }
            sSetConstantStateMethodFetched = true;
        }
        if (sSetConstantStateMethod == null)
        {
            break MISSING_BLOCK_LABEL_81;
        }
        sSetConstantStateMethod.invoke(drawablecontainer, new Object[] {
            constantstate
        });
        return true;
        drawablecontainer;
        Log.e("DrawableUtils", "Could not invoke setConstantState(). Oh well.");
        return false;
    }
}
