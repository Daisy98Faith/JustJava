// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import java.lang.reflect.Field;

// Referenced classes of package android.support.v4.view:
//            LayoutInflaterFactory

public final class LayoutInflaterCompat
{
    static class Factory2Wrapper
        implements android.view.LayoutInflater.Factory2
    {

        final LayoutInflaterFactory mDelegateFactory;

        public View onCreateView(View view, String s, Context context, AttributeSet attributeset)
        {
            return mDelegateFactory.onCreateView(view, s, context, attributeset);
        }

        public View onCreateView(String s, Context context, AttributeSet attributeset)
        {
            return mDelegateFactory.onCreateView(null, s, context, attributeset);
        }

        public String toString()
        {
            return (new StringBuilder()).append(getClass().getName()).append("{").append(mDelegateFactory).append("}").toString();
        }

        Factory2Wrapper(LayoutInflaterFactory layoutinflaterfactory)
        {
            mDelegateFactory = layoutinflaterfactory;
        }
    }

    static class LayoutInflaterCompatApi21Impl extends LayoutInflaterCompatBaseImpl
    {

        public void setFactory(LayoutInflater layoutinflater, LayoutInflaterFactory layoutinflaterfactory)
        {
            if (layoutinflaterfactory != null)
            {
                layoutinflaterfactory = new Factory2Wrapper(layoutinflaterfactory);
            } else
            {
                layoutinflaterfactory = null;
            }
            layoutinflater.setFactory2(layoutinflaterfactory);
        }

        public void setFactory2(LayoutInflater layoutinflater, android.view.LayoutInflater.Factory2 factory2)
        {
            layoutinflater.setFactory2(factory2);
        }

        LayoutInflaterCompatApi21Impl()
        {
        }
    }

    static class LayoutInflaterCompatBaseImpl
    {

        public LayoutInflaterFactory getFactory(LayoutInflater layoutinflater)
        {
            layoutinflater = layoutinflater.getFactory();
            if (layoutinflater instanceof Factory2Wrapper)
            {
                return ((Factory2Wrapper)layoutinflater).mDelegateFactory;
            } else
            {
                return null;
            }
        }

        public void setFactory(LayoutInflater layoutinflater, LayoutInflaterFactory layoutinflaterfactory)
        {
            if (layoutinflaterfactory != null)
            {
                layoutinflaterfactory = new Factory2Wrapper(layoutinflaterfactory);
            } else
            {
                layoutinflaterfactory = null;
            }
            setFactory2(layoutinflater, layoutinflaterfactory);
        }

        public void setFactory2(LayoutInflater layoutinflater, android.view.LayoutInflater.Factory2 factory2)
        {
            layoutinflater.setFactory2(factory2);
            android.view.LayoutInflater.Factory factory = layoutinflater.getFactory();
            if (factory instanceof android.view.LayoutInflater.Factory2)
            {
                LayoutInflaterCompat.forceSetFactory2(layoutinflater, (android.view.LayoutInflater.Factory2)factory);
                return;
            } else
            {
                LayoutInflaterCompat.forceSetFactory2(layoutinflater, factory2);
                return;
            }
        }

        LayoutInflaterCompatBaseImpl()
        {
        }
    }


    static final LayoutInflaterCompatBaseImpl IMPL;
    private static final String TAG = "LayoutInflaterCompatHC";
    private static boolean sCheckedField;
    private static Field sLayoutInflaterFactory2Field;

    private LayoutInflaterCompat()
    {
    }

    static void forceSetFactory2(LayoutInflater layoutinflater, android.view.LayoutInflater.Factory2 factory2)
    {
        if (!sCheckedField)
        {
            try
            {
                sLayoutInflaterFactory2Field = android/view/LayoutInflater.getDeclaredField("mFactory2");
                sLayoutInflaterFactory2Field.setAccessible(true);
            }
            catch (NoSuchFieldException nosuchfieldexception)
            {
                Log.e("LayoutInflaterCompatHC", (new StringBuilder()).append("forceSetFactory2 Could not find field 'mFactory2' on class ").append(android/view/LayoutInflater.getName()).append("; inflation may have unexpected results.").toString(), nosuchfieldexception);
            }
            sCheckedField = true;
        }
        if (sLayoutInflaterFactory2Field == null)
        {
            break MISSING_BLOCK_LABEL_41;
        }
        sLayoutInflaterFactory2Field.set(layoutinflater, factory2);
        return;
        factory2;
        Log.e("LayoutInflaterCompatHC", (new StringBuilder()).append("forceSetFactory2 could not set the Factory2 on LayoutInflater ").append(layoutinflater).append("; inflation may have unexpected results.").toString(), factory2);
        return;
    }

    public static LayoutInflaterFactory getFactory(LayoutInflater layoutinflater)
    {
        return IMPL.getFactory(layoutinflater);
    }

    public static void setFactory(LayoutInflater layoutinflater, LayoutInflaterFactory layoutinflaterfactory)
    {
        IMPL.setFactory(layoutinflater, layoutinflaterfactory);
    }

    public static void setFactory2(LayoutInflater layoutinflater, android.view.LayoutInflater.Factory2 factory2)
    {
        IMPL.setFactory2(layoutinflater, factory2);
    }

    static 
    {
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            IMPL = new LayoutInflaterCompatApi21Impl();
        } else
        {
            IMPL = new LayoutInflaterCompatBaseImpl();
        }
    }
}
