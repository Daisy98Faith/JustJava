// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content;


// Referenced classes of package android.support.v4.content:
//            SharedPreferencesCompat

public static final class Helper
{
    private static class Helper
    {

        public void apply(android.content.SharedPreferences.Editor editor)
        {
            try
            {
                editor.apply();
                return;
            }
            catch (AbstractMethodError abstractmethoderror)
            {
                editor.commit();
            }
        }

        Helper()
        {
        }
    }


    private static Helper.apply sInstance;
    private final Helper mHelper = new Helper();

    public static Helper getInstance()
    {
        if (sInstance == null)
        {
            sInstance = new <init>();
        }
        return sInstance;
    }

    public void apply(android.content.itorCompat itorcompat)
    {
        mHelper.apply(itorcompat);
    }

    private Helper()
    {
    }
}
