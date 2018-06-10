// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Activity;
import android.support.v4.util.SimpleArrayMap;

public class SupportActivity extends Activity
{
    public static class ExtraData
    {

        public ExtraData()
        {
        }
    }


    private SimpleArrayMap mExtraDataMap;

    public SupportActivity()
    {
        mExtraDataMap = new SimpleArrayMap();
    }

    public ExtraData getExtraData(Class class1)
    {
        return (ExtraData)mExtraDataMap.get(class1);
    }

    public void putExtraData(ExtraData extradata)
    {
        mExtraDataMap.put(extradata.getClass(), extradata);
    }
}
