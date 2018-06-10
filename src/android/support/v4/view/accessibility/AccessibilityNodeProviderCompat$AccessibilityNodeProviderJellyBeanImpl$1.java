// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view.accessibility;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.v4.view.accessibility:
//            AccessibilityNodeProviderCompat, AccessibilityNodeInfoCompat

class val.compat
    implements val.compat
{

    final val.compat this$0;
    final AccessibilityNodeProviderCompat val$compat;

    public Object createAccessibilityNodeInfo(int i)
    {
        AccessibilityNodeInfoCompat accessibilitynodeinfocompat = val$compat.createAccessibilityNodeInfo(i);
        if (accessibilitynodeinfocompat == null)
        {
            return null;
        } else
        {
            return accessibilitynodeinfocompat.getInfo();
        }
    }

    public List findAccessibilityNodeInfosByText(String s, int i)
    {
        List list = val$compat.findAccessibilityNodeInfosByText(s, i);
        if (list != null) goto _L2; else goto _L1
_L1:
        s = null;
_L4:
        return s;
_L2:
        ArrayList arraylist = new ArrayList();
        int j = list.size();
        i = 0;
        do
        {
            s = arraylist;
            if (i >= j)
            {
                continue;
            }
            arraylist.add(((AccessibilityNodeInfoCompat)list.get(i)).getInfo());
            i++;
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    public boolean performAction(int i, int j, Bundle bundle)
    {
        return val$compat.performAction(i, j, bundle);
    }

    ()
    {
        this$0 = final_;
        val$compat = AccessibilityNodeProviderCompat.this;
        super();
    }
}
