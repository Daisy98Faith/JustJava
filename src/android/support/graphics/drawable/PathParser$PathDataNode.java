// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.graphics.drawable;

import android.graphics.Path;
import android.util.Log;

// Referenced classes of package android.support.graphics.drawable:
//            PathParser

public static class params
{

    float params[];
    char type;

    private static void addCommand(Path path, float af[], char c, char c1, float af1[])
    {
        float f;
        float f1;
        float f2;
        float f3;
        float f4;
        float f5;
        byte byte0;
        byte0 = 2;
        f = af[0];
        f1 = af[1];
        f3 = af[2];
        f5 = af[3];
        f2 = af[4];
        f4 = af[5];
        c1;
        JVM INSTR lookupswitch 20: default 204
    //                   65: 483
    //                   67: 470
    //                   72: 464
    //                   76: 458
    //                   77: 458
    //                   81: 477
    //                   83: 477
    //                   84: 458
    //                   86: 464
    //                   90: 427
    //                   97: 483
    //                   99: 470
    //                   104: 464
    //                   108: 458
    //                   109: 458
    //                   113: 477
    //                   115: 477
    //                   116: 458
    //                   118: 464
    //                   122: 427;
           goto _L1 _L2 _L3 _L4 _L5 _L5 _L6 _L6 _L5 _L4 _L7 _L2 _L3 _L4 _L5 _L5 _L6 _L6 _L5 _L4 _L7
_L1:
        float f6;
        float f7;
        float f8;
        char c2;
        boolean flag = false;
        c2 = c;
        c = flag;
        f6 = f4;
        f4 = f2;
        f7 = f5;
        f8 = f3;
_L29:
        if (c >= af1.length) goto _L9; else goto _L8
_L8:
        c1;
        JVM INSTR lookupswitch 18: default 392
    //                   65: 1863
    //                   67: 1003
    //                   72: 805
    //                   76: 721
    //                   77: 582
    //                   81: 1417
    //                   83: 1213
    //                   84: 1603
    //                   86: 877
    //                   97: 1716
    //                   99: 912
    //                   104: 768
    //                   108: 668
    //                   109: 490
    //                   113: 1338
    //                   115: 1082
    //                   116: 1484
    //                   118: 840;
           goto _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28
_L10:
        f5 = f6;
        f3 = f7;
        f2 = f8;
_L30:
        c2 = c1;
        c += byte0;
        f8 = f2;
        f7 = f3;
        f6 = f5;
          goto _L29
_L7:
        path.close();
        f = f2;
        f1 = f4;
        f3 = f2;
        f5 = f4;
        path.moveTo(f, f1);
        continue; /* Loop/switch isn't completed */
_L5:
        byte0 = 2;
        continue; /* Loop/switch isn't completed */
_L4:
        byte0 = 1;
        continue; /* Loop/switch isn't completed */
_L3:
        byte0 = 6;
        continue; /* Loop/switch isn't completed */
_L6:
        byte0 = 4;
        continue; /* Loop/switch isn't completed */
_L2:
        byte0 = 7;
        continue; /* Loop/switch isn't completed */
_L24:
        f += af1[c + 0];
        f1 += af1[c + 1];
        if (c > 0)
        {
            path.rLineTo(af1[c + 0], af1[c + 1]);
            f2 = f8;
            f3 = f7;
            f5 = f6;
        } else
        {
            path.rMoveTo(af1[c + 0], af1[c + 1]);
            f4 = f;
            f5 = f1;
            f2 = f8;
            f3 = f7;
        }
          goto _L30
_L15:
        f = af1[c + 0];
        f1 = af1[c + 1];
        if (c > 0)
        {
            path.lineTo(af1[c + 0], af1[c + 1]);
            f2 = f8;
            f3 = f7;
            f5 = f6;
        } else
        {
            path.moveTo(af1[c + 0], af1[c + 1]);
            f4 = f;
            f5 = f1;
            f2 = f8;
            f3 = f7;
        }
          goto _L30
_L23:
        path.rLineTo(af1[c + 0], af1[c + 1]);
        f += af1[c + 0];
        f1 += af1[c + 1];
        f2 = f8;
        f3 = f7;
        f5 = f6;
          goto _L30
_L14:
        path.lineTo(af1[c + 0], af1[c + 1]);
        f = af1[c + 0];
        f1 = af1[c + 1];
        f2 = f8;
        f3 = f7;
        f5 = f6;
          goto _L30
_L22:
        path.rLineTo(af1[c + 0], 0.0F);
        f += af1[c + 0];
        f2 = f8;
        f3 = f7;
        f5 = f6;
          goto _L30
_L13:
        path.lineTo(af1[c + 0], f1);
        f = af1[c + 0];
        f2 = f8;
        f3 = f7;
        f5 = f6;
          goto _L30
_L28:
        path.rLineTo(0.0F, af1[c + 0]);
        f1 += af1[c + 0];
        f2 = f8;
        f3 = f7;
        f5 = f6;
          goto _L30
_L19:
        path.lineTo(f, af1[c + 0]);
        f1 = af1[c + 0];
        f2 = f8;
        f3 = f7;
        f5 = f6;
          goto _L30
_L21:
        path.rCubicTo(af1[c + 0], af1[c + 1], af1[c + 2], af1[c + 3], af1[c + 4], af1[c + 5]);
        f2 = f + af1[c + 2];
        f3 = f1 + af1[c + 3];
        f += af1[c + 4];
        f1 += af1[c + 5];
        f5 = f6;
          goto _L30
_L12:
        path.cubicTo(af1[c + 0], af1[c + 1], af1[c + 2], af1[c + 3], af1[c + 4], af1[c + 5]);
        f = af1[c + 4];
        f1 = af1[c + 5];
        f2 = af1[c + 2];
        f3 = af1[c + 3];
        f5 = f6;
          goto _L30
_L26:
        f2 = 0.0F;
        f3 = 0.0F;
        if (c2 == 'c' || c2 == 's' || c2 == 'C' || c2 == 'S')
        {
            f2 = f - f8;
            f3 = f1 - f7;
        }
        path.rCubicTo(f2, f3, af1[c + 0], af1[c + 1], af1[c + 2], af1[c + 3]);
        f2 = f + af1[c + 0];
        f3 = f1 + af1[c + 1];
        f += af1[c + 2];
        f1 += af1[c + 3];
        f5 = f6;
          goto _L30
_L17:
        f3 = f;
        f2 = f1;
        if (c2 == 'c' || c2 == 's' || c2 == 'C' || c2 == 'S')
        {
            f3 = 2.0F * f - f8;
            f2 = 2.0F * f1 - f7;
        }
        path.cubicTo(f3, f2, af1[c + 0], af1[c + 1], af1[c + 2], af1[c + 3]);
        f2 = af1[c + 0];
        f3 = af1[c + 1];
        f = af1[c + 2];
        f1 = af1[c + 3];
        f5 = f6;
          goto _L30
_L25:
        path.rQuadTo(af1[c + 0], af1[c + 1], af1[c + 2], af1[c + 3]);
        f2 = f + af1[c + 0];
        f3 = f1 + af1[c + 1];
        f += af1[c + 2];
        f1 += af1[c + 3];
        f5 = f6;
          goto _L30
_L16:
        path.quadTo(af1[c + 0], af1[c + 1], af1[c + 2], af1[c + 3]);
        f2 = af1[c + 0];
        f3 = af1[c + 1];
        f = af1[c + 2];
        f1 = af1[c + 3];
        f5 = f6;
          goto _L30
_L27:
        f3 = 0.0F;
        f2 = 0.0F;
        if (c2 == 'q' || c2 == 't' || c2 == 'Q' || c2 == 'T')
        {
            f3 = f - f8;
            f2 = f1 - f7;
        }
        path.rQuadTo(f3, f2, af1[c + 0], af1[c + 1]);
        f3 = f + f3;
        f5 = f1 + f2;
        f += af1[c + 0];
        f1 += af1[c + 1];
        f2 = f3;
        f3 = f5;
        f5 = f6;
          goto _L30
_L18:
        f3 = f;
        f2 = f1;
        if (c2 == 'q' || c2 == 't' || c2 == 'Q' || c2 == 'T')
        {
            f3 = 2.0F * f - f8;
            f2 = 2.0F * f1 - f7;
        }
        path.quadTo(f3, f2, af1[c + 0], af1[c + 1]);
        f = f2;
        f7 = af1[c + 0];
        f1 = af1[c + 1];
        f2 = f3;
        f3 = f;
        f5 = f6;
        f = f7;
          goto _L30
_L20:
        f2 = af1[c + 5];
        f3 = af1[c + 6];
        f5 = af1[c + 0];
        f7 = af1[c + 1];
        f8 = af1[c + 2];
        boolean flag1;
        boolean flag3;
        if (af1[c + 3] != 0.0F)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (af1[c + 4] != 0.0F)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        drawArc(path, f, f1, f2 + f, f3 + f1, f5, f7, f8, flag1, flag3);
        f += af1[c + 5];
        f1 += af1[c + 6];
        f2 = f;
        f3 = f1;
        f5 = f6;
          goto _L30
_L11:
        f2 = af1[c + 5];
        f3 = af1[c + 6];
        f5 = af1[c + 0];
        f7 = af1[c + 1];
        f8 = af1[c + 2];
        boolean flag2;
        boolean flag4;
        if (af1[c + 3] != 0.0F)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (af1[c + 4] != 0.0F)
        {
            flag4 = true;
        } else
        {
            flag4 = false;
        }
        drawArc(path, f, f1, f2, f3, f5, f7, f8, flag2, flag4);
        f = af1[c + 5];
        f1 = af1[c + 6];
        f2 = f;
        f3 = f1;
        f5 = f6;
          goto _L30
_L9:
        af[0] = f;
        af[1] = f1;
        af[2] = f8;
        af[3] = f7;
        af[4] = f4;
        af[5] = f6;
        return;
        if (true) goto _L1; else goto _L31
_L31:
    }

    private static void arcToBezier(Path path, double d, double d1, double d2, double d3, double d4, double d5, double d6, 
            double d7, double d8)
    {
        int j = (int)Math.ceil(Math.abs((4D * d8) / 3.1415926535897931D));
        double d9 = d7;
        double d17 = Math.cos(d6);
        double d18 = Math.sin(d6);
        d6 = Math.cos(d9);
        d7 = Math.sin(d9);
        double d11 = -d2 * d17 * d7 - d3 * d18 * d6;
        double d13 = -d2 * d18 * d7 + d3 * d17 * d6;
        double d19 = d8 / (double)j;
        int i = 0;
        d7 = d5;
        d6 = d4;
        d8 = d9;
        d5 = d13;
        d4 = d11;
        for (; i < j; i++)
        {
            double d16 = d8 + d19;
            double d14 = Math.sin(d16);
            double d20 = Math.cos(d16);
            double d15 = (d2 * d17 * d20 + d) - d3 * d18 * d14;
            double d12 = d2 * d18 * d20 + d1 + d3 * d17 * d14;
            double d10 = -d2 * d17 * d14 - d3 * d18 * d20;
            d14 = -d2 * d18 * d14 + d3 * d17 * d20;
            d20 = Math.tan((d16 - d8) / 2D);
            d8 = (Math.sin(d16 - d8) * (Math.sqrt(4D + 3D * d20 * d20) - 1.0D)) / 3D;
            path.rCubicTo((float)(d6 + d8 * d4) - (float)d6, (float)(d7 + d8 * d5) - (float)d7, (float)(d15 - d8 * d10) - (float)d6, (float)(d12 - d8 * d14) - (float)d7, (float)d15 - (float)d6, (float)d12 - (float)d7);
            d8 = d16;
            d6 = d15;
            d7 = d12;
            d4 = d10;
            d5 = d14;
        }

    }

    private static void drawArc(Path path, float f, float f1, float f2, float f3, float f4, float f5, float f6, 
            boolean flag, boolean flag1)
    {
        double d4 = Math.toRadians(f6);
        double d5 = Math.cos(d4);
        double d6 = Math.sin(d4);
        double d7 = ((double)f * d5 + (double)f1 * d6) / (double)f4;
        double d8 = ((double)(-f) * d6 + (double)f1 * d5) / (double)f5;
        double d = ((double)f2 * d5 + (double)f3 * d6) / (double)f4;
        double d3 = ((double)(-f2) * d6 + (double)f3 * d5) / (double)f5;
        double d10 = d7 - d;
        double d9 = d8 - d3;
        double d2 = (d7 + d) / 2D;
        double d1 = (d8 + d3) / 2D;
        double d11 = d10 * d10 + d9 * d9;
        if (d11 == 0.0D)
        {
            Log.w("PathParser", " Points are coincident");
            return;
        }
        double d12 = 1.0D / d11 - 0.25D;
        if (d12 < 0.0D)
        {
            Log.w("PathParser", (new StringBuilder()).append("Points are too far apart ").append(d11).toString());
            float f7 = (float)(Math.sqrt(d11) / 1.9999899999999999D);
            drawArc(path, f, f1, f2, f3, f4 * f7, f5 * f7, f6, flag, flag1);
            return;
        }
        d11 = Math.sqrt(d12);
        d10 = d11 * d10;
        d9 = d11 * d9;
        if (flag == flag1)
        {
            d2 -= d9;
            d1 += d10;
        } else
        {
            d2 += d9;
            d1 -= d10;
        }
        d7 = Math.atan2(d8 - d1, d7 - d2);
        d3 = Math.atan2(d3 - d1, d - d2) - d7;
        if (d3 >= 0.0D)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        d = d3;
        if (flag1 != flag)
        {
            if (d3 > 0.0D)
            {
                d = d3 - 6.2831853071795862D;
            } else
            {
                d = d3 + 6.2831853071795862D;
            }
        }
        d2 *= f4;
        d1 *= f5;
        arcToBezier(path, d2 * d5 - d1 * d6, d2 * d6 + d1 * d5, f4, f5, f, f1, d4, d7, d);
    }

    public static void nodesToPath(arcToBezier aarctobezier[], Path path)
    {
        float af[] = new float[6];
        char c = 'm';
        for (int i = 0; i < aarctobezier.length; i++)
        {
            addCommand(path, af, c, aarctobezier[i].type, aarctobezier[i].params);
            c = aarctobezier[i].type;
        }

    }

    public void interpolatePathDataNode(type type1, type type2, float f)
    {
        for (int i = 0; i < type1.params.length; i++)
        {
            params[i] = type1.params[i] * (1.0F - f) + type2.params[i] * f;
        }

    }

    (char c, float af[])
    {
        type = c;
        params = af;
    }

    params(params params1)
    {
        type = params1.type;
        params = PathParser.copyOfRange(params1.params, 0, params1.params.length);
    }
}
