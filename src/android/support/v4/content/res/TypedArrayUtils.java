// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content.res;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import org.xmlpull.v1.XmlPullParser;

public class TypedArrayUtils
{

    private static final String NAMESPACE = "http://schemas.android.com/apk/res/android";

    public TypedArrayUtils()
    {
    }

    public static int getAttr(Context context, int i, int j)
    {
        TypedValue typedvalue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedvalue, true);
        if (typedvalue.resourceId != 0)
        {
            return i;
        } else
        {
            return j;
        }
    }

    public static boolean getBoolean(TypedArray typedarray, int i, int j, boolean flag)
    {
        return typedarray.getBoolean(i, typedarray.getBoolean(j, flag));
    }

    public static Drawable getDrawable(TypedArray typedarray, int i, int j)
    {
        Drawable drawable1 = typedarray.getDrawable(i);
        Drawable drawable = drawable1;
        if (drawable1 == null)
        {
            drawable = typedarray.getDrawable(j);
        }
        return drawable;
    }

    public static int getInt(TypedArray typedarray, int i, int j, int k)
    {
        return typedarray.getInt(i, typedarray.getInt(j, k));
    }

    public static boolean getNamedBoolean(TypedArray typedarray, XmlPullParser xmlpullparser, String s, int i, boolean flag)
    {
        if (!hasAttribute(xmlpullparser, s))
        {
            return flag;
        } else
        {
            return typedarray.getBoolean(i, flag);
        }
    }

    public static int getNamedColor(TypedArray typedarray, XmlPullParser xmlpullparser, String s, int i, int j)
    {
        if (!hasAttribute(xmlpullparser, s))
        {
            return j;
        } else
        {
            return typedarray.getColor(i, j);
        }
    }

    public static float getNamedFloat(TypedArray typedarray, XmlPullParser xmlpullparser, String s, int i, float f)
    {
        if (!hasAttribute(xmlpullparser, s))
        {
            return f;
        } else
        {
            return typedarray.getFloat(i, f);
        }
    }

    public static int getNamedInt(TypedArray typedarray, XmlPullParser xmlpullparser, String s, int i, int j)
    {
        if (!hasAttribute(xmlpullparser, s))
        {
            return j;
        } else
        {
            return typedarray.getInt(i, j);
        }
    }

    public static int getResourceId(TypedArray typedarray, int i, int j, int k)
    {
        return typedarray.getResourceId(i, typedarray.getResourceId(j, k));
    }

    public static String getString(TypedArray typedarray, int i, int j)
    {
        String s1 = typedarray.getString(i);
        String s = s1;
        if (s1 == null)
        {
            s = typedarray.getString(j);
        }
        return s;
    }

    public static CharSequence getText(TypedArray typedarray, int i, int j)
    {
        CharSequence charsequence1 = typedarray.getText(i);
        CharSequence charsequence = charsequence1;
        if (charsequence1 == null)
        {
            charsequence = typedarray.getText(j);
        }
        return charsequence;
    }

    public static CharSequence[] getTextArray(TypedArray typedarray, int i, int j)
    {
        CharSequence acharsequence1[] = typedarray.getTextArray(i);
        CharSequence acharsequence[] = acharsequence1;
        if (acharsequence1 == null)
        {
            acharsequence = typedarray.getTextArray(j);
        }
        return acharsequence;
    }

    public static boolean hasAttribute(XmlPullParser xmlpullparser, String s)
    {
        return xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", s) != null;
    }
}
