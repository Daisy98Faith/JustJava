// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.annotation;

import java.lang.annotation.Annotation;

public interface Dimension
    extends Annotation
{

    public static final int DP = 0;
    public static final int PX = 1;
    public static final int SP = 2;

    public abstract int unit();
}
