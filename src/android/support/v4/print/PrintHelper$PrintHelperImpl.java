// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.print;

import android.graphics.Bitmap;
import android.net.Uri;
import java.io.FileNotFoundException;

// Referenced classes of package android.support.v4.print:
//            PrintHelper, PrintHelperKitkat

private static class mPrintHelper
    implements onImpl
{

    private final PrintHelperKitkat mPrintHelper;

    public int getColorMode()
    {
        return mPrintHelper.getColorMode();
    }

    public int getOrientation()
    {
        return mPrintHelper.getOrientation();
    }

    public int getScaleMode()
    {
        return mPrintHelper.getScaleMode();
    }

    public void printBitmap(String s, Bitmap bitmap, final lback callback)
    {
        ishCallback ishcallback = null;
        if (callback != null)
        {
            ishcallback = new PrintHelperKitkat.OnPrintFinishCallback() {

                final PrintHelper.PrintHelperImpl this$0;
                final PrintHelper.OnPrintFinishCallback val$callback;

                public void onFinish()
                {
                    callback.onFinish();
                }

            
            {
                this$0 = PrintHelper.PrintHelperImpl.this;
                callback = onprintfinishcallback;
                super();
            }
            };
        }
        mPrintHelper.printBitmap(s, bitmap, ishcallback);
    }

    public void printBitmap(String s, Uri uri, final lback callback)
        throws FileNotFoundException
    {
        ishCallback ishcallback = null;
        if (callback != null)
        {
            ishcallback = new PrintHelperKitkat.OnPrintFinishCallback() {

                final PrintHelper.PrintHelperImpl this$0;
                final PrintHelper.OnPrintFinishCallback val$callback;

                public void onFinish()
                {
                    callback.onFinish();
                }

            
            {
                this$0 = PrintHelper.PrintHelperImpl.this;
                callback = onprintfinishcallback;
                super();
            }
            };
        }
        mPrintHelper.printBitmap(s, uri, ishcallback);
    }

    public void setColorMode(int i)
    {
        mPrintHelper.setColorMode(i);
    }

    public void setOrientation(int i)
    {
        mPrintHelper.setOrientation(i);
    }

    public void setScaleMode(int i)
    {
        mPrintHelper.setScaleMode(i);
    }

    protected _cls2.val.callback(PrintHelperKitkat printhelperkitkat)
    {
        mPrintHelper = printhelperkitkat;
    }
}
