// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver.widgets;

import android.support.constraint.solver.Cache;
import android.support.constraint.solver.SolverVariable;
import java.util.ArrayList;
import java.util.HashSet;

// Referenced classes of package android.support.constraint.solver.widgets:
//            ConstraintWidget, Guideline

public class ConstraintAnchor
{
    public static final class ConnectionType extends Enum
    {

        private static final ConnectionType $VALUES[];
        public static final ConnectionType RELAXED;
        public static final ConnectionType STRICT;

        public static ConnectionType valueOf(String s)
        {
            return (ConnectionType)Enum.valueOf(android/support/constraint/solver/widgets/ConstraintAnchor$ConnectionType, s);
        }

        public static ConnectionType[] values()
        {
            return (ConnectionType[])$VALUES.clone();
        }

        static 
        {
            RELAXED = new ConnectionType("RELAXED", 0);
            STRICT = new ConnectionType("STRICT", 1);
            $VALUES = (new ConnectionType[] {
                RELAXED, STRICT
            });
        }

        private ConnectionType(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class Strength extends Enum
    {

        private static final Strength $VALUES[];
        public static final Strength NONE;
        public static final Strength STRONG;
        public static final Strength WEAK;

        public static Strength valueOf(String s)
        {
            return (Strength)Enum.valueOf(android/support/constraint/solver/widgets/ConstraintAnchor$Strength, s);
        }

        public static Strength[] values()
        {
            return (Strength[])$VALUES.clone();
        }

        static 
        {
            NONE = new Strength("NONE", 0);
            STRONG = new Strength("STRONG", 1);
            WEAK = new Strength("WEAK", 2);
            $VALUES = (new Strength[] {
                NONE, STRONG, WEAK
            });
        }

        private Strength(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class Type extends Enum
    {

        private static final Type $VALUES[];
        public static final Type BASELINE;
        public static final Type BOTTOM;
        public static final Type CENTER;
        public static final Type CENTER_X;
        public static final Type CENTER_Y;
        public static final Type LEFT;
        public static final Type NONE;
        public static final Type RIGHT;
        public static final Type TOP;

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(android/support/constraint/solver/widgets/ConstraintAnchor$Type, s);
        }

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        static 
        {
            NONE = new Type("NONE", 0);
            LEFT = new Type("LEFT", 1);
            TOP = new Type("TOP", 2);
            RIGHT = new Type("RIGHT", 3);
            BOTTOM = new Type("BOTTOM", 4);
            BASELINE = new Type("BASELINE", 5);
            CENTER = new Type("CENTER", 6);
            CENTER_X = new Type("CENTER_X", 7);
            CENTER_Y = new Type("CENTER_Y", 8);
            $VALUES = (new Type[] {
                NONE, LEFT, TOP, RIGHT, BOTTOM, BASELINE, CENTER, CENTER_X, CENTER_Y
            });
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }


    private static final boolean ALLOW_BINARY = false;
    public static final int ANY_GROUP = 0x7fffffff;
    public static final int APPLY_GROUP_RESULTS = -2;
    public static final int AUTO_CONSTRAINT_CREATOR = 2;
    public static final int SCOUT_CREATOR = 1;
    private static final int UNSET_GONE_MARGIN = -1;
    public static final int USER_CREATOR = 0;
    public static final boolean USE_CENTER_ANCHOR = false;
    private int mConnectionCreator;
    private ConnectionType mConnectionType;
    int mGoneMargin;
    int mGroup;
    public int mMargin;
    final ConstraintWidget mOwner;
    SolverVariable mSolverVariable;
    private Strength mStrength;
    ConstraintAnchor mTarget;
    final Type mType;

    public ConstraintAnchor(ConstraintWidget constraintwidget, Type type)
    {
        mMargin = 0;
        mGoneMargin = -1;
        mStrength = Strength.NONE;
        mConnectionType = ConnectionType.RELAXED;
        mConnectionCreator = 0;
        mGroup = 0x7fffffff;
        mOwner = constraintwidget;
        mType = type;
    }

    private boolean isConnectionToMe(ConstraintWidget constraintwidget, HashSet hashset)
    {
        if (!hashset.contains(constraintwidget))
        {
            hashset.add(constraintwidget);
            if (constraintwidget == getOwner())
            {
                return true;
            }
            constraintwidget = constraintwidget.getAnchors();
            int i = 0;
            int j = constraintwidget.size();
            while (i < j) 
            {
                ConstraintAnchor constraintanchor = (ConstraintAnchor)constraintwidget.get(i);
                if (constraintanchor.isSimilarDimensionConnection(this) && constraintanchor.isConnected() && isConnectionToMe(constraintanchor.getTarget().getOwner(), hashset))
                {
                    return true;
                }
                i++;
            }
        }
        return false;
    }

    private String toString(HashSet hashset)
    {
        if (hashset.add(this))
        {
            StringBuilder stringbuilder = (new StringBuilder()).append(mOwner.getDebugName()).append(":").append(mType.toString());
            if (mTarget != null)
            {
                hashset = (new StringBuilder()).append(" connected to ").append(mTarget.toString(hashset)).toString();
            } else
            {
                hashset = "";
            }
            return stringbuilder.append(hashset).toString();
        } else
        {
            return "<-";
        }
    }

    public boolean connect(ConstraintAnchor constraintanchor, int i)
    {
        return connect(constraintanchor, i, -1, Strength.STRONG, 0, false);
    }

    public boolean connect(ConstraintAnchor constraintanchor, int i, int j)
    {
        return connect(constraintanchor, i, -1, Strength.STRONG, j, false);
    }

    public boolean connect(ConstraintAnchor constraintanchor, int i, int j, Strength strength, int k, boolean flag)
    {
        if (constraintanchor == null)
        {
            mTarget = null;
            mMargin = 0;
            mGoneMargin = -1;
            mStrength = Strength.NONE;
            mConnectionCreator = 2;
            return true;
        }
        if (!flag && !isValidConnection(constraintanchor))
        {
            return false;
        }
        mTarget = constraintanchor;
        if (i > 0)
        {
            mMargin = i;
        } else
        {
            mMargin = 0;
        }
        mGoneMargin = j;
        mStrength = strength;
        mConnectionCreator = k;
        return true;
    }

    public boolean connect(ConstraintAnchor constraintanchor, int i, Strength strength, int j)
    {
        return connect(constraintanchor, i, -1, strength, j, false);
    }

    public int getConnectionCreator()
    {
        return mConnectionCreator;
    }

    public ConnectionType getConnectionType()
    {
        return mConnectionType;
    }

    public int getGroup()
    {
        return mGroup;
    }

    public int getMargin()
    {
        if (mOwner.getVisibility() == 8)
        {
            return 0;
        }
        if (mGoneMargin > -1 && mTarget != null && mTarget.mOwner.getVisibility() == 8)
        {
            return mGoneMargin;
        } else
        {
            return mMargin;
        }
    }

    public final ConstraintAnchor getOpposite()
    {
        static class _cls1
        {

            static final int $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[];

            static 
            {
                $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type = new int[Type.values().length];
                try
                {
                    $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[Type.CENTER.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror7) { }
                try
                {
                    $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[Type.LEFT.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[Type.RIGHT.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[Type.TOP.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[Type.BOTTOM.ordinal()] = 5;
                }
                catch (NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[Type.CENTER_X.ordinal()] = 6;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[Type.CENTER_Y.ordinal()] = 7;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[Type.BASELINE.ordinal()] = 8;
                }
                catch (NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        switch (_cls1..SwitchMap.android.support.constraint.solver.widgets.ConstraintAnchor.Type[mType.ordinal()])
        {
        default:
            return null;

        case 2: // '\002'
            return mOwner.mRight;

        case 3: // '\003'
            return mOwner.mLeft;

        case 4: // '\004'
            return mOwner.mBottom;

        case 5: // '\005'
            return mOwner.mTop;
        }
    }

    public ConstraintWidget getOwner()
    {
        return mOwner;
    }

    public int getPriorityLevel()
    {
        switch (_cls1..SwitchMap.android.support.constraint.solver.widgets.ConstraintAnchor.Type[mType.ordinal()])
        {
        case 6: // '\006'
        case 7: // '\007'
        default:
            return 0;

        case 8: // '\b'
            return 1;

        case 2: // '\002'
            return 2;

        case 3: // '\003'
            return 2;

        case 4: // '\004'
            return 2;

        case 5: // '\005'
            return 2;

        case 1: // '\001'
            return 2;
        }
    }

    public int getSnapPriorityLevel()
    {
        int i = 1;
        switch (_cls1..SwitchMap.android.support.constraint.solver.widgets.ConstraintAnchor.Type[mType.ordinal()])
        {
        default:
            i = 0;
            // fall through

        case 2: // '\002'
        case 3: // '\003'
        case 7: // '\007'
            return i;

        case 6: // '\006'
            return 0;

        case 4: // '\004'
            return 0;

        case 5: // '\005'
            return 0;

        case 8: // '\b'
            return 2;

        case 1: // '\001'
            return 3;
        }
    }

    public SolverVariable getSolverVariable()
    {
        return mSolverVariable;
    }

    public Strength getStrength()
    {
        return mStrength;
    }

    public ConstraintAnchor getTarget()
    {
        return mTarget;
    }

    public Type getType()
    {
        return mType;
    }

    public boolean isConnected()
    {
        return mTarget != null;
    }

    public boolean isConnectionAllowed(ConstraintWidget constraintwidget)
    {
        if (!isConnectionToMe(constraintwidget, new HashSet()))
        {
            ConstraintWidget constraintwidget1 = getOwner().getParent();
            if (constraintwidget1 == constraintwidget)
            {
                return true;
            }
            if (constraintwidget.getParent() == constraintwidget1)
            {
                return true;
            }
        }
        return false;
    }

    public boolean isConnectionAllowed(ConstraintWidget constraintwidget, ConstraintAnchor constraintanchor)
    {
        return isConnectionAllowed(constraintwidget);
    }

    public boolean isSideAnchor()
    {
        switch (_cls1..SwitchMap.android.support.constraint.solver.widgets.ConstraintAnchor.Type[mType.ordinal()])
        {
        default:
            return false;

        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
            return true;
        }
    }

    public boolean isSimilarDimensionConnection(ConstraintAnchor constraintanchor)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = false;
        constraintanchor = constraintanchor.getType();
        if (constraintanchor != mType) goto _L2; else goto _L1
_L1:
        flag = true;
_L8:
        return flag;
_L2:
        _cls1..SwitchMap.android.support.constraint.solver.widgets.ConstraintAnchor.Type[mType.ordinal()];
        JVM INSTR tableswitch 1 8: default 80
    //                   1 82
    //                   2 96
    //                   3 96
    //                   4 121
    //                   5 121
    //                   6 96
    //                   7 121
    //                   8 121;
           goto _L3 _L4 _L5 _L5 _L6 _L6 _L5 _L6 _L6
_L3:
        return false;
_L4:
        if (constraintanchor == Type.BASELINE)
        {
            flag = false;
        }
        return flag;
_L5:
        if (constraintanchor == Type.LEFT || constraintanchor == Type.RIGHT)
        {
            break; /* Loop/switch isn't completed */
        }
        flag = flag1;
        if (constraintanchor != Type.CENTER_X) goto _L8; else goto _L7
_L7:
        return true;
_L6:
        if (constraintanchor == Type.TOP || constraintanchor == Type.BOTTOM || constraintanchor == Type.CENTER_Y)
        {
            break; /* Loop/switch isn't completed */
        }
        flag = flag1;
        if (constraintanchor != Type.BASELINE) goto _L8; else goto _L9
_L9:
        return true;
        if (true) goto _L11; else goto _L10
_L11:
        if (true) goto _L8; else goto _L10
_L10:
    }

    public boolean isSnapCompatibleWith(ConstraintAnchor constraintanchor)
    {
        if (mType == Type.CENTER)
        {
            return false;
        }
        if (mType == constraintanchor.getType())
        {
            return true;
        }
        switch (_cls1..SwitchMap.android.support.constraint.solver.widgets.ConstraintAnchor.Type[mType.ordinal()])
        {
        default:
            return false;

        case 2: // '\002'
            switch (_cls1..SwitchMap.android.support.constraint.solver.widgets.ConstraintAnchor.Type[constraintanchor.getType().ordinal()])
            {
            case 4: // '\004'
            case 5: // '\005'
            default:
                return false;

            case 3: // '\003'
                return true;

            case 6: // '\006'
                return true;
            }

        case 3: // '\003'
            switch (_cls1..SwitchMap.android.support.constraint.solver.widgets.ConstraintAnchor.Type[constraintanchor.getType().ordinal()])
            {
            default:
                return false;

            case 2: // '\002'
                return true;

            case 6: // '\006'
                return true;
            }

        case 6: // '\006'
            switch (_cls1..SwitchMap.android.support.constraint.solver.widgets.ConstraintAnchor.Type[constraintanchor.getType().ordinal()])
            {
            default:
                return false;

            case 2: // '\002'
                return true;

            case 3: // '\003'
                return true;
            }

        case 4: // '\004'
            switch (_cls1..SwitchMap.android.support.constraint.solver.widgets.ConstraintAnchor.Type[constraintanchor.getType().ordinal()])
            {
            case 6: // '\006'
            default:
                return false;

            case 5: // '\005'
                return true;

            case 7: // '\007'
                return true;
            }

        case 5: // '\005'
            switch (_cls1..SwitchMap.android.support.constraint.solver.widgets.ConstraintAnchor.Type[constraintanchor.getType().ordinal()])
            {
            case 5: // '\005'
            case 6: // '\006'
            default:
                return false;

            case 4: // '\004'
                return true;

            case 7: // '\007'
                return true;
            }

        case 7: // '\007'
            switch (_cls1..SwitchMap.android.support.constraint.solver.widgets.ConstraintAnchor.Type[constraintanchor.getType().ordinal()])
            {
            default:
                return false;

            case 4: // '\004'
                return true;

            case 5: // '\005'
                return true;
            }
        }
    }

    public boolean isValidConnection(ConstraintAnchor constraintanchor)
    {
        boolean flag = true;
        if (constraintanchor != null)
        {
            Type type = constraintanchor.getType();
            if (type == mType)
            {
                if (mType != Type.CENTER && (mType != Type.BASELINE || constraintanchor.getOwner().hasBaseline() && getOwner().hasBaseline()))
                {
                    return true;
                }
            } else
            {
                switch (_cls1..SwitchMap.android.support.constraint.solver.widgets.ConstraintAnchor.Type[mType.ordinal()])
                {
                default:
                    return false;

                case 1: // '\001'
                    if (type == Type.BASELINE || type == Type.CENTER_X || type == Type.CENTER_Y)
                    {
                        flag = false;
                    }
                    return flag;

                case 2: // '\002'
                case 3: // '\003'
                    boolean flag1;
                    if (type == Type.LEFT || type == Type.RIGHT)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    flag1 = flag;
                    if (constraintanchor.getOwner() instanceof Guideline)
                    {
                        if (flag || type == Type.CENTER_X)
                        {
                            flag1 = true;
                        } else
                        {
                            flag1 = false;
                        }
                    }
                    return flag1;

                case 4: // '\004'
                case 5: // '\005'
                    break;
                }
                boolean flag2;
                if (type == Type.TOP || type == Type.BOTTOM)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                flag2 = flag;
                if (constraintanchor.getOwner() instanceof Guideline)
                {
                    if (flag || type == Type.CENTER_Y)
                    {
                        flag2 = true;
                    } else
                    {
                        flag2 = false;
                    }
                }
                return flag2;
            }
        }
        return false;
    }

    public boolean isVerticalAnchor()
    {
        switch (_cls1..SwitchMap.android.support.constraint.solver.widgets.ConstraintAnchor.Type[mType.ordinal()])
        {
        case 4: // '\004'
        case 5: // '\005'
        default:
            return true;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 6: // '\006'
            return false;
        }
    }

    public void reset()
    {
        mTarget = null;
        mMargin = 0;
        mGoneMargin = -1;
        mStrength = Strength.STRONG;
        mConnectionCreator = 0;
        mConnectionType = ConnectionType.RELAXED;
    }

    public void resetSolverVariable(Cache cache)
    {
        if (mSolverVariable == null)
        {
            mSolverVariable = new SolverVariable(android.support.constraint.solver.SolverVariable.Type.UNRESTRICTED);
            return;
        } else
        {
            mSolverVariable.reset();
            return;
        }
    }

    public void setConnectionCreator(int i)
    {
        mConnectionCreator = i;
    }

    public void setConnectionType(ConnectionType connectiontype)
    {
        mConnectionType = connectiontype;
    }

    public void setGoneMargin(int i)
    {
        if (isConnected())
        {
            mGoneMargin = i;
        }
    }

    public void setGroup(int i)
    {
        mGroup = i;
    }

    public void setMargin(int i)
    {
        if (isConnected())
        {
            mMargin = i;
        }
    }

    public void setStrength(Strength strength)
    {
        if (isConnected())
        {
            mStrength = strength;
        }
    }

    public String toString()
    {
        Object obj = new HashSet();
        StringBuilder stringbuilder = (new StringBuilder()).append(mOwner.getDebugName()).append(":").append(mType.toString());
        if (mTarget != null)
        {
            obj = (new StringBuilder()).append(" connected to ").append(mTarget.toString(((HashSet) (obj)))).toString();
        } else
        {
            obj = "";
        }
        return stringbuilder.append(((String) (obj))).toString();
    }
}
