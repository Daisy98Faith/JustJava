// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.util.AttributeSet;
import android.util.Log;
import org.xmlpull.v1.XmlPullParser;

// Referenced classes of package android.support.v7.widget:
//            AppCompatDrawableManager

private static class 
    implements 
{

    public Drawable createFromXmlInner(Context context, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.egate egate)
    {
        try
        {
            context = AnimatedVectorDrawableCompat.createFromXmlInner(context, context.getResources(), xmlpullparser, attributeset, egate);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", context);
            return null;
        }
        return context;
    }

    ()
    {
    }
}
