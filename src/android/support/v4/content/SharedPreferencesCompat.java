// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content;


public final class SharedPreferencesCompat
{
    public static final class EditorCompat
    {

        private static EditorCompat sInstance;
        private final Helper mHelper = new Helper();

        public static EditorCompat getInstance()
        {
            if (sInstance == null)
            {
                sInstance = new EditorCompat();
            }
            return sInstance;
        }

        public void apply(android.content.SharedPreferences.Editor editor)
        {
            mHelper.apply(editor);
        }

        private EditorCompat()
        {
        }
    }

    private static class EditorCompat.Helper
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

        EditorCompat.Helper()
        {
        }
    }


    private SharedPreferencesCompat()
    {
    }
}
