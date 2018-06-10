// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.print;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;

// Referenced classes of package android.support.v4.print:
//            PrintHelperKitkat

class PrintFinishCallback extends PrintDocumentAdapter
{

    private PrintAttributes mAttributes;
    final PrintHelperKitkat this$0;
    final Bitmap val$bitmap;
    final PrintFinishCallback val$callback;
    final int val$fittingMode;
    final String val$jobName;

    public void onFinish()
    {
        if (val$callback != null)
        {
            val$callback.onFinish();
        }
    }

    public void onLayout(PrintAttributes printattributes, PrintAttributes printattributes1, CancellationSignal cancellationsignal, android.print..LayoutResultCallback layoutresultcallback, Bundle bundle)
    {
        boolean flag = true;
        mAttributes = printattributes1;
        cancellationsignal = (new android.print.ilder(val$jobName)).setContentType(1).setPageCount(1).build();
        if (printattributes1.equals(printattributes))
        {
            flag = false;
        }
        layoutresultcallback.onLayoutFinished(cancellationsignal, flag);
    }

    public void onWrite(PageRange apagerange[], ParcelFileDescriptor parcelfiledescriptor, CancellationSignal cancellationsignal, android.print..WriteResultCallback writeresultcallback)
    {
        PrintHelperKitkat.access$000(PrintHelperKitkat.this, mAttributes, val$fittingMode, val$bitmap, parcelfiledescriptor, cancellationsignal, writeresultcallback);
    }

    PrintFinishCallback()
    {
        this$0 = final_printhelperkitkat;
        val$jobName = s;
        val$fittingMode = i;
        val$bitmap = bitmap1;
        val$callback = PrintFinishCallback.this;
        super();
    }
}
