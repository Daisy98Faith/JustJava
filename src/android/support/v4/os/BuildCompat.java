// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.os;


public class BuildCompat
{

    private BuildCompat()
    {
    }

    public static boolean isAtLeastN()
    {
        return android.os.Build.VERSION.SDK_INT >= 24;
    }

    public static boolean isAtLeastNMR1()
    {
        return android.os.Build.VERSION.SDK_INT >= 25;
    }

    public static boolean isAtLeastO()
    {
        return !"REL".equals(android.os.Build.VERSION.CODENAME) && ("O".equals(android.os.Build.VERSION.CODENAME) || android.os.Build.VERSION.CODENAME.startsWith("OMR"));
    }
}
