// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view.accessibility;


// Referenced classes of package android.support.v4.view.accessibility:
//            AccessibilityManagerCompatKitKat

public static class mListenerBridge
    implements android.view.accessibility.nerWrapper
{

    final Object mListener;
    final nTouchExplorationStateChanged mListenerBridge;

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            obj = (mListenerBridge)obj;
            if (mListener == null)
            {
                if (((mListener) (obj)).mListener != null)
                {
                    return false;
                }
            } else
            {
                return mListener.equals(((mListener) (obj)).mListener);
            }
        }
        return true;
    }

    public int hashCode()
    {
        if (mListener == null)
        {
            return 0;
        } else
        {
            return mListener.hashCode();
        }
    }

    public void onTouchExplorationStateChanged(boolean flag)
    {
        mListenerBridge.nTouchExplorationStateChanged(flag);
    }

    public (Object obj,  )
    {
        mListener = obj;
        mListenerBridge = ;
    }
}
