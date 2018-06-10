// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver.widgets;


public class Rectangle
{

    public int height;
    public int width;
    public int x;
    public int y;

    public Rectangle()
    {
    }

    public boolean contains(int i, int j)
    {
        return i >= x && i < x + width && j >= y && j < y + height;
    }

    public int getCenterX()
    {
        return (x + width) / 2;
    }

    public int getCenterY()
    {
        return (y + height) / 2;
    }

    void grow(int i, int j)
    {
        x = x - i;
        y = y - j;
        width = width + i * 2;
        height = height + j * 2;
    }

    boolean intersects(Rectangle rectangle)
    {
        return x >= rectangle.x && x < rectangle.x + rectangle.width && y >= rectangle.y && y < rectangle.y + rectangle.height;
    }

    public void setBounds(int i, int j, int k, int l)
    {
        x = i;
        y = j;
        width = k;
        height = l;
    }
}
