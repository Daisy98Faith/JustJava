// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.util.Property;

// Referenced classes of package android.support.v7.widget:
//            SwitchCompat

static final class  extends Property
{

    public Float get(SwitchCompat switchcompat)
    {
        return Float.valueOf(SwitchCompat.access$000(switchcompat));
    }

    public volatile Object get(Object obj)
    {
        return get((SwitchCompat)obj);
    }

    public void set(SwitchCompat switchcompat, Float float1)
    {
        switchcompat.setThumbPosition(float1.floatValue());
    }

    public volatile void set(Object obj, Object obj1)
    {
        set((SwitchCompat)obj, (Float)obj1);
    }

    (Class class1, String s)
    {
        super(class1, s);
    }
}
