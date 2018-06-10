// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.graphics;

import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.util.Pair;

class PaintCompatApi14
{

    private static final String TOFU_STRING = "\uDB3F\uDFFD";
    private static final ThreadLocal sRectThreadLocal = new ThreadLocal();

    PaintCompatApi14()
    {
    }

    static boolean hasGlyph(Paint paint, String s)
    {
        int j;
        boolean flag2;
        flag2 = false;
        j = s.length();
        if (j != 1 || !Character.isWhitespace(s.charAt(0))) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        float f1;
        float f2;
        f1 = paint.measureText("\uDB3F\uDFFD");
        f2 = paint.measureText(s);
        flag = flag2;
        if (f2 == 0.0F) goto _L4; else goto _L3
_L3:
        if (s.codePointCount(0, s.length()) <= 1)
        {
            break; /* Loop/switch isn't completed */
        }
        flag = flag2;
        if (f2 > 2.0F * f1) goto _L4; else goto _L5
_L5:
        float f;
        f = 0.0F;
        int k;
        for (int i = 0; i < j; i += k)
        {
            k = Character.charCount(s.codePointAt(i));
            f += paint.measureText(s, i, i + k);
        }

        flag = flag2;
        if (f2 >= f) goto _L4; else goto _L6
_L6:
        if (f2 != f1)
        {
            return true;
        }
        Pair pair = obtainEmptyRects();
        paint.getTextBounds("\uDB3F\uDFFD", 0, "\uDB3F\uDFFD".length(), (Rect)pair.first);
        paint.getTextBounds(s, 0, j, (Rect)pair.second);
        boolean flag1;
        if (!((Rect)pair.first).equals(pair.second))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        return flag1;
    }

    private static Pair obtainEmptyRects()
    {
        Pair pair = (Pair)sRectThreadLocal.get();
        if (pair == null)
        {
            pair = new Pair(new Rect(), new Rect());
            sRectThreadLocal.set(pair);
            return pair;
        } else
        {
            ((Rect)pair.first).setEmpty();
            ((Rect)pair.second).setEmpty();
            return pair;
        }
    }

}
