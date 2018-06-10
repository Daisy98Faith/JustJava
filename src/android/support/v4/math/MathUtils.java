// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.math;


public class MathUtils
{

    public MathUtils()
    {
    }

    public static double clamp(double d, double d1, double d2)
    {
        if (d < d1)
        {
            return d1;
        }
        if (d > d2)
        {
            return d2;
        } else
        {
            return d;
        }
    }

    public static float clamp(float f, int i, int j)
    {
        float f1;
        if (f < (float)i)
        {
            f1 = i;
        } else
        {
            f1 = f;
            if (f > (float)j)
            {
                return (float)j;
            }
        }
        return f1;
    }

    public static int clamp(int i, int j, int k)
    {
        if (i < j)
        {
            return j;
        }
        if (i > k)
        {
            return k;
        } else
        {
            return i;
        }
    }
}
