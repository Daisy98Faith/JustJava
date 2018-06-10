// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.animation.LayoutTransition;
import android.util.Log;
import android.view.ViewGroup;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Referenced classes of package android.support.transition:
//            ViewGroupUtilsImpl, ViewGroupOverlayApi14, ViewGroupOverlayImpl

class ViewGroupUtilsApi14
    implements ViewGroupUtilsImpl
{

    private static final int LAYOUT_TRANSITION_CHANGING = 4;
    private static final String TAG = "ViewGroupUtilsApi14";
    private static Method sCancelMethod;
    private static boolean sCancelMethodFetched;
    private static LayoutTransition sEmptyLayoutTransition;
    private static Field sLayoutSuppressedField;
    private static boolean sLayoutSuppressedFieldFetched;

    ViewGroupUtilsApi14()
    {
    }

    private static void cancelLayoutTransition(LayoutTransition layouttransition)
    {
        if (!sCancelMethodFetched)
        {
            try
            {
                sCancelMethod = android/animation/LayoutTransition.getDeclaredMethod("cancel", new Class[0]);
                sCancelMethod.setAccessible(true);
            }
            catch (NoSuchMethodException nosuchmethodexception)
            {
                Log.i("ViewGroupUtilsApi14", "Failed to access cancel method by reflection");
            }
            sCancelMethodFetched = true;
        }
        if (sCancelMethod == null)
        {
            break MISSING_BLOCK_LABEL_49;
        }
        sCancelMethod.invoke(layouttransition, new Object[0]);
        return;
        layouttransition;
        Log.i("ViewGroupUtilsApi14", "Failed to access cancel method by reflection");
        return;
        layouttransition;
        Log.i("ViewGroupUtilsApi14", "Failed to invoke cancel method by reflection");
        return;
    }

    public ViewGroupOverlayImpl getOverlay(ViewGroup viewgroup)
    {
        return ViewGroupOverlayApi14.createFrom(viewgroup);
    }

    public void suppressLayout(ViewGroup viewgroup, boolean flag)
    {
        if (sEmptyLayoutTransition == null)
        {
            sEmptyLayoutTransition = new LayoutTransition() {

                final ViewGroupUtilsApi14 this$0;

                public boolean isChangingLayout()
                {
                    return true;
                }

            
            {
                this$0 = ViewGroupUtilsApi14.this;
                super();
            }
            };
            sEmptyLayoutTransition.setAnimator(2, null);
            sEmptyLayoutTransition.setAnimator(0, null);
            sEmptyLayoutTransition.setAnimator(1, null);
            sEmptyLayoutTransition.setAnimator(3, null);
            sEmptyLayoutTransition.setAnimator(4, null);
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        LayoutTransition layouttransition = viewgroup.getLayoutTransition();
        if (layouttransition != null)
        {
            if (layouttransition.isRunning())
            {
                cancelLayoutTransition(layouttransition);
            }
            if (layouttransition != sEmptyLayoutTransition)
            {
                viewgroup.setTag(R.id.transition_layout_save, layouttransition);
            }
        }
        viewgroup.setLayoutTransition(sEmptyLayoutTransition);
_L4:
        return;
_L2:
        viewgroup.setLayoutTransition(null);
        if (!sLayoutSuppressedFieldFetched)
        {
            LayoutTransition layouttransition1;
            boolean flag1;
            try
            {
                sLayoutSuppressedField = android/view/ViewGroup.getDeclaredField("mLayoutSuppressed");
                sLayoutSuppressedField.setAccessible(true);
            }
            catch (NoSuchFieldException nosuchfieldexception)
            {
                Log.i("ViewGroupUtilsApi14", "Failed to access mLayoutSuppressed field by reflection");
            }
            sLayoutSuppressedFieldFetched = true;
        }
        flag1 = false;
        flag = false;
        if (sLayoutSuppressedField == null)
        {
            break MISSING_BLOCK_LABEL_181;
        }
        flag = flag1;
        flag1 = sLayoutSuppressedField.getBoolean(viewgroup);
        flag = flag1;
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_181;
        }
        flag = flag1;
        sLayoutSuppressedField.setBoolean(viewgroup, false);
        flag = flag1;
_L5:
        if (flag)
        {
            viewgroup.requestLayout();
        }
        layouttransition1 = (LayoutTransition)viewgroup.getTag(R.id.transition_layout_save);
        if (layouttransition1 != null)
        {
            viewgroup.setTag(R.id.transition_layout_save, null);
            viewgroup.setLayoutTransition(layouttransition1);
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        Log.i("ViewGroupUtilsApi14", "Failed to get mLayoutSuppressed field by reflection");
          goto _L5
    }
}
