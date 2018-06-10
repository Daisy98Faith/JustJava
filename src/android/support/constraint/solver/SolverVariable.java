// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.constraint.solver;

import java.util.Arrays;

// Referenced classes of package android.support.constraint.solver:
//            ArrayRow

public class SolverVariable
{
    public static final class Type extends Enum
    {

        private static final Type $VALUES[];
        public static final Type CONSTANT;
        public static final Type ERROR;
        public static final Type SLACK;
        public static final Type UNKNOWN;
        public static final Type UNRESTRICTED;

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(android/support/constraint/solver/SolverVariable$Type, s);
        }

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        static 
        {
            UNRESTRICTED = new Type("UNRESTRICTED", 0);
            CONSTANT = new Type("CONSTANT", 1);
            SLACK = new Type("SLACK", 2);
            ERROR = new Type("ERROR", 3);
            UNKNOWN = new Type("UNKNOWN", 4);
            $VALUES = (new Type[] {
                UNRESTRICTED, CONSTANT, SLACK, ERROR, UNKNOWN
            });
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }


    private static final boolean INTERNAL_DEBUG = false;
    static final int MAX_STRENGTH = 6;
    public static final int STRENGTH_EQUALITY = 5;
    public static final int STRENGTH_HIGH = 3;
    public static final int STRENGTH_HIGHEST = 4;
    public static final int STRENGTH_LOW = 1;
    public static final int STRENGTH_MEDIUM = 2;
    public static final int STRENGTH_NONE = 0;
    private static int uniqueId = 1;
    public float computedValue;
    int definitionId;
    public int id;
    ArrayRow mClientEquations[];
    int mClientEquationsCount;
    private String mName;
    Type mType;
    public int strength;
    float strengthVector[];

    public SolverVariable(Type type)
    {
        id = -1;
        definitionId = -1;
        strength = 0;
        strengthVector = new float[6];
        mClientEquations = new ArrayRow[8];
        mClientEquationsCount = 0;
        mType = type;
    }

    public SolverVariable(String s, Type type)
    {
        id = -1;
        definitionId = -1;
        strength = 0;
        strengthVector = new float[6];
        mClientEquations = new ArrayRow[8];
        mClientEquationsCount = 0;
        mName = s;
        mType = type;
    }

    private static String getUniqueName(Type type)
    {
        uniqueId++;
        static class _cls1
        {

            static final int $SwitchMap$android$support$constraint$solver$SolverVariable$Type[];

            static 
            {
                $SwitchMap$android$support$constraint$solver$SolverVariable$Type = new int[Type.values().length];
                try
                {
                    $SwitchMap$android$support$constraint$solver$SolverVariable$Type[Type.UNRESTRICTED.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$android$support$constraint$solver$SolverVariable$Type[Type.CONSTANT.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$android$support$constraint$solver$SolverVariable$Type[Type.SLACK.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$android$support$constraint$solver$SolverVariable$Type[Type.ERROR.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        switch (_cls1..SwitchMap.android.support.constraint.solver.SolverVariable.Type[type.ordinal()])
        {
        default:
            return (new StringBuilder()).append("V").append(uniqueId).toString();

        case 1: // '\001'
            return (new StringBuilder()).append("U").append(uniqueId).toString();

        case 2: // '\002'
            return (new StringBuilder()).append("C").append(uniqueId).toString();

        case 3: // '\003'
            return (new StringBuilder()).append("S").append(uniqueId).toString();

        case 4: // '\004'
            return (new StringBuilder()).append("e").append(uniqueId).toString();
        }
    }

    void addClientEquation(ArrayRow arrayrow)
    {
        for (int i = 0; i < mClientEquationsCount; i++)
        {
            if (mClientEquations[i] == arrayrow)
            {
                return;
            }
        }

        if (mClientEquationsCount >= mClientEquations.length)
        {
            mClientEquations = (ArrayRow[])Arrays.copyOf(mClientEquations, mClientEquations.length * 2);
        }
        mClientEquations[mClientEquationsCount] = arrayrow;
        mClientEquationsCount = mClientEquationsCount + 1;
    }

    void clearStrengths()
    {
        for (int i = 0; i < 6; i++)
        {
            strengthVector[i] = 0.0F;
        }

    }

    public String getName()
    {
        return mName;
    }

    void removeClientEquation(ArrayRow arrayrow)
    {
        int i = 0;
        do
        {
label0:
            {
                if (i < mClientEquationsCount)
                {
                    if (mClientEquations[i] != arrayrow)
                    {
                        break label0;
                    }
                    for (int j = 0; j < mClientEquationsCount - i - 1; j++)
                    {
                        mClientEquations[i + j] = mClientEquations[i + j + 1];
                    }

                    mClientEquationsCount = mClientEquationsCount - 1;
                }
                return;
            }
            i++;
        } while (true);
    }

    public void reset()
    {
        mName = null;
        mType = Type.UNKNOWN;
        strength = 0;
        id = -1;
        definitionId = -1;
        computedValue = 0.0F;
        mClientEquationsCount = 0;
    }

    public void setName(String s)
    {
        mName = s;
    }

    public void setType(Type type)
    {
        mType = type;
    }

    String strengthsToString()
    {
        String s = (new StringBuilder()).append(this).append("[").toString();
        int i = 0;
        while (i < strengthVector.length) 
        {
            s = (new StringBuilder()).append(s).append(strengthVector[i]).toString();
            if (i < strengthVector.length - 1)
            {
                s = (new StringBuilder()).append(s).append(", ").toString();
            } else
            {
                s = (new StringBuilder()).append(s).append("] ").toString();
            }
            i++;
        }
        return s;
    }

    public String toString()
    {
        return (new StringBuilder()).append("").append(mName).toString();
    }

}
