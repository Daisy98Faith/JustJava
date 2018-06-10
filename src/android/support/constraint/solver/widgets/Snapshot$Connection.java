// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver.widgets;


// Referenced classes of package android.support.constraint.solver.widgets:
//            Snapshot, ConstraintAnchor, ConstraintWidget

static class ConnectionCreator
{

    private ConstraintAnchor mAnchor;
    private int mCreator;
    private int mMargin;
    private ength mStrengh;
    private ConstraintAnchor mTarget;

    public void applyTo(ConstraintWidget constraintwidget)
    {
        constraintwidget.getAnchor(mAnchor.getType()).connect(mTarget, mMargin, mStrengh, mCreator);
    }

    public void updateFrom(ConstraintWidget constraintwidget)
    {
        mAnchor = constraintwidget.getAnchor(mAnchor.getType());
        if (mAnchor != null)
        {
            mTarget = mAnchor.getTarget();
            mMargin = mAnchor.getMargin();
            mStrengh = mAnchor.getStrength();
            mCreator = mAnchor.getConnectionCreator();
            return;
        } else
        {
            mTarget = null;
            mMargin = 0;
            mStrengh = ength.STRONG;
            mCreator = 0;
            return;
        }
    }

    public ength(ConstraintAnchor constraintanchor)
    {
        mAnchor = constraintanchor;
        mTarget = constraintanchor.getTarget();
        mMargin = constraintanchor.getMargin();
        mStrengh = constraintanchor.getStrength();
        mCreator = constraintanchor.getConnectionCreator();
    }
}
