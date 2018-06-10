// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.annotation;


// Referenced classes of package android.support.annotation:
//            RestrictTo

public static final class  extends Enum
{

    private static final SUBCLASSES $VALUES[];
    public static final SUBCLASSES GROUP_ID;
    public static final SUBCLASSES LIBRARY;
    public static final SUBCLASSES LIBRARY_GROUP;
    public static final SUBCLASSES SUBCLASSES;
    public static final SUBCLASSES TESTS;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(android/support/annotation/RestrictTo$Scope, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        LIBRARY = new <init>("LIBRARY", 0);
        LIBRARY_GROUP = new <init>("LIBRARY_GROUP", 1);
        GROUP_ID = new <init>("GROUP_ID", 2);
        TESTS = new <init>("TESTS", 3);
        SUBCLASSES = new <init>("SUBCLASSES", 4);
        $VALUES = (new .VALUES[] {
            LIBRARY, LIBRARY_GROUP, GROUP_ID, TESTS, SUBCLASSES
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
