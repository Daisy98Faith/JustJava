// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content.res;

import android.content.res.Configuration;
import android.content.res.Resources;

class ConfigurationHelperJellybeanMr1
{

    ConfigurationHelperJellybeanMr1()
    {
    }

    static int getDensityDpi(Resources resources)
    {
        return resources.getConfiguration().densityDpi;
    }
}
