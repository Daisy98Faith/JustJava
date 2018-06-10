// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.graphics.drawable;

import android.graphics.Path;
import android.util.Log;

// Referenced classes of package android.support.graphics.drawable:
//            VectorDrawableCompat, PathParser

private static class mNodes
{

    int mChangingConfigurations;
    protected mNodes mNodes[];
    String mPathName;

    public String NodesToString(mNodes amnodes[])
    {
        String s = " ";
        for (int i = 0; i < amnodes.length; i++)
        {
            s = (new StringBuilder()).append(s).append(amnodes[i].e).append(":").toString();
            float af[] = amnodes[i].ams;
            for (int j = 0; j < af.length; j++)
            {
                s = (new StringBuilder()).append(s).append(af[j]).append(",").toString();
            }

        }

        return s;
    }

    public void applyTheme(android.content.res.eCompat.VPath vpath)
    {
    }

    public boolean canApplyTheme()
    {
        return false;
    }

    public ams[] getPathData()
    {
        return mNodes;
    }

    public String getPathName()
    {
        return mPathName;
    }

    public boolean isClipPath()
    {
        return false;
    }

    public void printVPath(int i)
    {
        String s = "";
        for (int j = 0; j < i; j++)
        {
            s = (new StringBuilder()).append(s).append("    ").toString();
        }

        Log.v("VectorDrawableCompat", (new StringBuilder()).append(s).append("current path is :").append(mPathName).append(" pathData is ").append(NodesToString(mNodes)).toString());
    }

    public void setPathData(mNodes amnodes[])
    {
        if (!PathParser.canMorph(mNodes, amnodes))
        {
            mNodes = PathParser.deepCopyNodes(amnodes);
            return;
        } else
        {
            PathParser.updateNodes(mNodes, amnodes);
            return;
        }
    }

    public void toPath(Path path)
    {
        path.reset();
        if (mNodes != null)
        {
            esToPath(mNodes, path);
        }
    }

    public ()
    {
        mNodes = null;
    }

    public mNodes(mNodes mnodes)
    {
        mNodes = null;
        mPathName = mnodes.mPathName;
        mChangingConfigurations = mnodes.mChangingConfigurations;
        mNodes = PathParser.deepCopyNodes(mnodes.mNodes);
    }
}
