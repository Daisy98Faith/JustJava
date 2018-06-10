// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.print;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.pdf.PrintedPdfDocument;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.IOException;

// Referenced classes of package android.support.v4.print:
//            PrintHelperKitkat

class tCallback extends AsyncTask
{

    final PrintHelperKitkat this$0;
    final PrintAttributes val$attributes;
    final Bitmap val$bitmap;
    final CancellationSignal val$cancellationSignal;
    final ParcelFileDescriptor val$fileDescriptor;
    final int val$fittingMode;
    final PrintAttributes val$pdfAttributes;
    final android.print..WriteResultCallback val$writeResultCallback;

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((Void[])aobj);
    }

    protected transient Throwable doInBackground(Void avoid[])
    {
        Bitmap bitmap1;
        Object obj;
        android.graphics.pdf.t t;
        Object obj1;
        if (val$cancellationSignal.isCanceled())
        {
            return null;
        }
        boolean flag;
        try
        {
            obj = new PrintedPdfDocument(mContext, val$pdfAttributes);
            bitmap1 = PrintHelperKitkat.access$100(PrintHelperKitkat.this, val$bitmap, val$pdfAttributes.getColorMode());
            flag = val$cancellationSignal.isCanceled();
        }
        // Misplaced declaration of an exception variable
        catch (Void avoid[])
        {
            return avoid;
        }
        if (flag) goto _L2; else goto _L1
_L1:
        t = ((PrintedPdfDocument) (obj)).startPage(1);
        if (!mIsMinMarginsHandlingCorrect) goto _L4; else goto _L3
_L3:
        avoid = new RectF(t.Info().getContentRect());
_L12:
        obj1 = PrintHelperKitkat.access$200(PrintHelperKitkat.this, bitmap1.getWidth(), bitmap1.getHeight(), avoid, val$fittingMode);
        if (!mIsMinMarginsHandlingCorrect) goto _L6; else goto _L5
_L5:
        t.Canvas().drawBitmap(bitmap1, ((Matrix) (obj1)), null);
        ((PrintedPdfDocument) (obj)).finishPage(t);
        flag = val$cancellationSignal.isCanceled();
        if (!flag) goto _L8; else goto _L7
_L7:
        ((PrintedPdfDocument) (obj)).close();
        avoid = val$fileDescriptor;
        if (avoid == null)
        {
            break MISSING_BLOCK_LABEL_181;
        }
        try
        {
            val$fileDescriptor.close();
        }
        // Misplaced declaration of an exception variable
        catch (Void avoid[]) { }
        if (bitmap1 == val$bitmap) goto _L2; else goto _L9
_L9:
        bitmap1.recycle();
        return null;
_L4:
        obj1 = new PrintedPdfDocument(mContext, val$attributes);
        android.graphics.pdf.t t1 = ((PrintedPdfDocument) (obj1)).startPage(1);
        avoid = new RectF(t1.Info().getContentRect());
        ((PrintedPdfDocument) (obj1)).finishPage(t1);
        ((PrintedPdfDocument) (obj1)).close();
        continue; /* Loop/switch isn't completed */
        avoid;
        ((PrintedPdfDocument) (obj)).close();
        obj = val$fileDescriptor;
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_278;
        }
        try
        {
            val$fileDescriptor.close();
        }
        catch (IOException ioexception) { }
        if (bitmap1 != val$bitmap)
        {
            bitmap1.recycle();
        }
        throw avoid;
_L6:
        ((Matrix) (obj1)).postTranslate(((RectF) (avoid)).left, ((RectF) (avoid)).top);
        t.Canvas().clipRect(avoid);
          goto _L5
_L8:
        ((PrintedPdfDocument) (obj)).writeTo(new FileOutputStream(val$fileDescriptor.getFileDescriptor()));
        ((PrintedPdfDocument) (obj)).close();
        avoid = val$fileDescriptor;
        if (avoid == null)
        {
            break MISSING_BLOCK_LABEL_357;
        }
        try
        {
            val$fileDescriptor.close();
        }
        // Misplaced declaration of an exception variable
        catch (Void avoid[]) { }
        if (bitmap1 == val$bitmap) goto _L2; else goto _L10
_L10:
        bitmap1.recycle();
        return null;
_L2:
        return null;
        if (true) goto _L12; else goto _L11
_L11:
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((Throwable)obj);
    }

    protected void onPostExecute(Throwable throwable)
    {
        if (val$cancellationSignal.isCanceled())
        {
            val$writeResultCallback.onWriteCancelled();
            return;
        }
        if (throwable == null)
        {
            val$writeResultCallback.onWriteFinished(new PageRange[] {
                PageRange.ALL_PAGES
            });
            return;
        } else
        {
            Log.e("PrintHelperKitkat", "Error writing printed content", throwable);
            val$writeResultCallback.onWriteFailed(null);
            return;
        }
    }

    tCallback()
    {
        this$0 = final_printhelperkitkat;
        val$cancellationSignal = cancellationsignal;
        val$pdfAttributes = printattributes;
        val$bitmap = bitmap1;
        val$attributes = printattributes1;
        val$fittingMode = i;
        val$fileDescriptor = parcelfiledescriptor;
        val$writeResultCallback = android.print..WriteResultCallback.this;
        super();
    }
}
