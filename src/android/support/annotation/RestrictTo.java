// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.annotation;

import java.lang.annotation.Annotation;

public interface RestrictTo
    extends Annotation
{
    public static final class Scope extends Enum
    {

        private static final Scope $VALUES[];
        public static final Scope GROUP_ID;
        public static final Scope LIBRARY;
        public static final Scope LIBRARY_GROUP;
        public static final Scope SUBCLASSES;
        public static final Scope TESTS;

        public static Scope valueOf(String s)
        {
            return (Scope)Enum.valueOf(android/support/annotation/RestrictTo$Scope, s);
        }

        public static Scope[] values()
        {
            return (Scope[])$VALUES.clone();
        }

        static 
        {
            LIBRARY = new Scope("LIBRARY", 0);
            LIBRARY_GROUP = new Scope("LIBRARY_GROUP", 1);
            GROUP_ID = new Scope("GROUP_ID", 2);
            TESTS = new Scope("TESTS", 3);
            SUBCLASSES = new Scope("SUBCLASSES", 4);
            $VALUES = (new Scope[] {
                LIBRARY, LIBRARY_GROUP, GROUP_ID, TESTS, SUBCLASSES
            });
        }

        private Scope(String s, int i)
        {
            super(s, i);
        }
    }


    public abstract Scope[] value();
}
