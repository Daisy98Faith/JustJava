// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

// Referenced classes of package android.support.v4.view:
//            AsyncLayoutInflater

private static class  extends LayoutInflater
{

    private static final String sClassPrefixList[] = {
        "android.widget.", "android.webkit.", "android.app."
    };

    public LayoutInflater cloneInContext(Context context)
    {
        return new <init>(context);
    }

    protected View onCreateView(String s, AttributeSet attributeset)
        throws ClassNotFoundException
    {
        String as[];
        int i;
        int j;
        as = sClassPrefixList;
        j = as.length;
        i = 0;
_L3:
        if (i >= j) goto _L2; else goto _L1
_L1:
        Object obj = as[i];
        obj = createView(s, ((String) (obj)), attributeset);
        if (obj != null)
        {
            return ((View) (obj));
        }
        continue; /* Loop/switch isn't completed */
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        i++;
          goto _L3
_L2:
        return super.onCreateView(s, attributeset);
    }


    (Context context)
    {
        super(context);
    }
}
