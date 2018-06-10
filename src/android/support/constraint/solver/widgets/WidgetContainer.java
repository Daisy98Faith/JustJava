// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver.widgets;

import android.support.constraint.solver.Cache;
import java.util.ArrayList;

// Referenced classes of package android.support.constraint.solver.widgets:
//            ConstraintWidget, Rectangle, ConstraintWidgetContainer

public class WidgetContainer extends ConstraintWidget
{

    protected ArrayList mChildren;

    public WidgetContainer()
    {
        mChildren = new ArrayList();
    }

    public WidgetContainer(int i, int j)
    {
        super(i, j);
        mChildren = new ArrayList();
    }

    public WidgetContainer(int i, int j, int k, int l)
    {
        super(i, j, k, l);
        mChildren = new ArrayList();
    }

    public static Rectangle getBounds(ArrayList arraylist)
    {
        Rectangle rectangle = new Rectangle();
        if (arraylist.size() == 0)
        {
            return rectangle;
        }
        int k1 = 0x7fffffff;
        int l = 0;
        int i1 = 0x7fffffff;
        int i = 0;
        int j = 0;
        for (int l1 = arraylist.size(); j < l1;)
        {
            ConstraintWidget constraintwidget = (ConstraintWidget)arraylist.get(j);
            int k = k1;
            if (constraintwidget.getX() < k1)
            {
                k = constraintwidget.getX();
            }
            int j1 = i1;
            if (constraintwidget.getY() < i1)
            {
                j1 = constraintwidget.getY();
            }
            i1 = l;
            if (constraintwidget.getRight() > l)
            {
                i1 = constraintwidget.getRight();
            }
            k1 = i;
            if (constraintwidget.getBottom() > i)
            {
                k1 = constraintwidget.getBottom();
            }
            j++;
            l = i1;
            i = k1;
            k1 = k;
            i1 = j1;
        }

        rectangle.setBounds(k1, i1, l - k1, i - i1);
        return rectangle;
    }

    public void add(ConstraintWidget constraintwidget)
    {
        mChildren.add(constraintwidget);
        if (constraintwidget.getParent() != null)
        {
            ((WidgetContainer)constraintwidget.getParent()).remove(constraintwidget);
        }
        constraintwidget.setParent(this);
    }

    public ConstraintWidget findWidget(float f, float f1)
    {
        Object obj1 = null;
        int i = getDrawX();
        int j = getDrawY();
        int k = getWidth();
        int i1 = getHeight();
        WidgetContainer widgetcontainer = ((WidgetContainer) (obj1));
        if (f >= (float)i)
        {
            widgetcontainer = ((WidgetContainer) (obj1));
            if (f <= (float)(i + k))
            {
                widgetcontainer = ((WidgetContainer) (obj1));
                if (f1 >= (float)j)
                {
                    widgetcontainer = ((WidgetContainer) (obj1));
                    if (f1 <= (float)(j + i1))
                    {
                        widgetcontainer = this;
                    }
                }
            }
        }
        i = 0;
        j = mChildren.size();
        obj1 = widgetcontainer;
        while (i < j) 
        {
            ConstraintWidget constraintwidget = (ConstraintWidget)mChildren.get(i);
            Object obj;
            if (constraintwidget instanceof WidgetContainer)
            {
                constraintwidget = ((WidgetContainer)constraintwidget).findWidget(f, f1);
                obj = obj1;
                if (constraintwidget != null)
                {
                    obj = constraintwidget;
                }
            } else
            {
                int l = constraintwidget.getDrawX();
                int j1 = constraintwidget.getDrawY();
                int k1 = constraintwidget.getWidth();
                int l1 = constraintwidget.getHeight();
                obj = obj1;
                if (f >= (float)l)
                {
                    obj = obj1;
                    if (f <= (float)(l + k1))
                    {
                        obj = obj1;
                        if (f1 >= (float)j1)
                        {
                            obj = obj1;
                            if (f1 <= (float)(j1 + l1))
                            {
                                obj = constraintwidget;
                            }
                        }
                    }
                }
            }
            i++;
            obj1 = obj;
        }
        return ((ConstraintWidget) (obj1));
    }

    public ArrayList findWidgets(int i, int j, int k, int l)
    {
        ArrayList arraylist = new ArrayList();
        Rectangle rectangle = new Rectangle();
        rectangle.setBounds(i, j, k, l);
        i = 0;
        for (j = mChildren.size(); i < j; i++)
        {
            ConstraintWidget constraintwidget = (ConstraintWidget)mChildren.get(i);
            Rectangle rectangle1 = new Rectangle();
            rectangle1.setBounds(constraintwidget.getDrawX(), constraintwidget.getDrawY(), constraintwidget.getWidth(), constraintwidget.getHeight());
            if (rectangle.intersects(rectangle1))
            {
                arraylist.add(constraintwidget);
            }
        }

        return arraylist;
    }

    public ArrayList getChildren()
    {
        return mChildren;
    }

    public ConstraintWidgetContainer getRootConstraintContainer()
    {
        ConstraintWidget constraintwidget1 = getParent();
        ConstraintWidgetContainer constraintwidgetcontainer = null;
        ConstraintWidget constraintwidget = constraintwidget1;
        if (this instanceof ConstraintWidgetContainer)
        {
            constraintwidgetcontainer = (ConstraintWidgetContainer)this;
            constraintwidget = constraintwidget1;
        }
        do
        {
            ConstraintWidget constraintwidget3 = constraintwidget;
            if (constraintwidget3 == null)
            {
                break;
            }
            ConstraintWidget constraintwidget2 = constraintwidget3.getParent();
            constraintwidget = constraintwidget2;
            if (constraintwidget3 instanceof ConstraintWidgetContainer)
            {
                constraintwidgetcontainer = (ConstraintWidgetContainer)constraintwidget3;
                constraintwidget = constraintwidget2;
            }
        } while (true);
        return constraintwidgetcontainer;
    }

    public void layout()
    {
        updateDrawPosition();
        if (mChildren != null)
        {
            int j = mChildren.size();
            int i = 0;
            while (i < j) 
            {
                ConstraintWidget constraintwidget = (ConstraintWidget)mChildren.get(i);
                if (constraintwidget instanceof WidgetContainer)
                {
                    ((WidgetContainer)constraintwidget).layout();
                }
                i++;
            }
        }
    }

    public void remove(ConstraintWidget constraintwidget)
    {
        mChildren.remove(constraintwidget);
        constraintwidget.setParent(null);
    }

    public void removeAllChildren()
    {
        mChildren.clear();
    }

    public void reset()
    {
        mChildren.clear();
        super.reset();
    }

    public void resetGroups()
    {
        super.resetGroups();
        int j = mChildren.size();
        for (int i = 0; i < j; i++)
        {
            ((ConstraintWidget)mChildren.get(i)).resetGroups();
        }

    }

    public void resetSolverVariables(Cache cache)
    {
        super.resetSolverVariables(cache);
        int j = mChildren.size();
        for (int i = 0; i < j; i++)
        {
            ((ConstraintWidget)mChildren.get(i)).resetSolverVariables(cache);
        }

    }

    public void setOffset(int i, int j)
    {
        super.setOffset(i, j);
        j = mChildren.size();
        for (i = 0; i < j; i++)
        {
            ((ConstraintWidget)mChildren.get(i)).setOffset(getRootX(), getRootY());
        }

    }

    public void updateDrawPosition()
    {
        super.updateDrawPosition();
        if (mChildren != null)
        {
            int j = mChildren.size();
            int i = 0;
            while (i < j) 
            {
                ConstraintWidget constraintwidget = (ConstraintWidget)mChildren.get(i);
                constraintwidget.setOffset(getDrawX(), getDrawY());
                if (!(constraintwidget instanceof ConstraintWidgetContainer))
                {
                    constraintwidget.updateDrawPosition();
                }
                i++;
            }
        }
    }
}
