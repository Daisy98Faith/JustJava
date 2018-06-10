// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;

// Referenced classes of package android.support.v4.view:
//            PointerIconCompat, PointerIconCompatApi24

static class  extends 
{

    public Object create(Bitmap bitmap, float f, float f1)
    {
        return PointerIconCompatApi24.create(bitmap, f, f1);
    }

    public Object getSystemIcon(Context context, int i)
    {
        return PointerIconCompatApi24.getSystemIcon(context, i);
    }

    public Object load(Resources resources, int i)
    {
        return PointerIconCompatApi24.load(resources, i);
    }

    ()
    {
    }
}
