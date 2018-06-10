// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.print;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import java.io.FileNotFoundException;

// Referenced classes of package android.support.v4.print:
//            PrintHelperKitkat

class this._cls2
    implements android.os.celListener
{

    final ncel this$2;

    public void onCancel()
    {
        elLoad();
        ncel(false);
    }

    map()
    {
        this$2 = this._cls2.this;
        super();
    }

    // Unreferenced inner class android/support/v4/print/PrintHelperKitkat$3

/* anonymous class */
    class PrintHelperKitkat._cls3 extends PrintDocumentAdapter
    {

        private PrintAttributes mAttributes;
        Bitmap mBitmap;
        AsyncTask mLoadBitmap;
        final PrintHelperKitkat this$0;
        final PrintHelperKitkat.OnPrintFinishCallback val$callback;
        final int val$fittingMode;
        final Uri val$imageFile;
        final String val$jobName;

        private void cancelLoad()
        {
            synchronized (PrintHelperKitkat.access$700(PrintHelperKitkat.this))
            {
                if (mDecodeOptions != null)
                {
                    mDecodeOptions.requestCancelDecode();
                    mDecodeOptions = null;
                }
            }
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onFinish()
        {
            super.onFinish();
            cancelLoad();
            if (mLoadBitmap != null)
            {
                mLoadBitmap.cancel(true);
            }
            if (callback != null)
            {
                callback.onFinish();
            }
            if (mBitmap != null)
            {
                mBitmap.recycle();
                mBitmap = null;
            }
        }

        public void onLayout(final PrintAttributes oldPrintAttributes, final PrintAttributes newPrintAttributes, final CancellationSignal cancellationSignal, android.print.PrintDocumentAdapter.LayoutResultCallback layoutresultcallback, Bundle bundle)
        {
            boolean flag = true;
            this;
            JVM INSTR monitorenter ;
            mAttributes = newPrintAttributes;
            this;
            JVM INSTR monitorexit ;
            if (cancellationSignal.isCanceled())
            {
                layoutresultcallback.onLayoutCancelled();
                return;
            }
            break MISSING_BLOCK_LABEL_30;
            oldPrintAttributes;
            this;
            JVM INSTR monitorexit ;
            throw oldPrintAttributes;
            if (mBitmap != null)
            {
                cancellationSignal = (new android.print.PrintDocumentInfo.Builder(jobName)).setContentType(1).setPageCount(1).build();
                if (newPrintAttributes.equals(oldPrintAttributes))
                {
                    flag = false;
                }
                layoutresultcallback.onLayoutFinished(cancellationSignal, flag);
                return;
            } else
            {
                mLoadBitmap = (layoutresultcallback. new PrintHelperKitkat._cls3._cls1()).execute(new Uri[0]);
                return;
            }
        }

        public void onWrite(PageRange apagerange[], ParcelFileDescriptor parcelfiledescriptor, CancellationSignal cancellationsignal, android.print.PrintDocumentAdapter.WriteResultCallback writeresultcallback)
        {
            PrintHelperKitkat.access$000(PrintHelperKitkat.this, mAttributes, fittingMode, mBitmap, parcelfiledescriptor, cancellationsignal, writeresultcallback);
        }



            
            {
                this$0 = final_printhelperkitkat;
                jobName = s;
                imageFile = uri;
                callback = onprintfinishcallback;
                fittingMode = I.this;
                super();
                mBitmap = null;
            }
    }


    // Unreferenced inner class android/support/v4/print/PrintHelperKitkat$3$1

/* anonymous class */
    class PrintHelperKitkat._cls3._cls1 extends AsyncTask
    {

        final PrintHelperKitkat._cls3 this$1;
        final CancellationSignal val$cancellationSignal;
        final android.print.PrintDocumentAdapter.LayoutResultCallback val$layoutResultCallback;
        final PrintAttributes val$newPrintAttributes;
        final PrintAttributes val$oldPrintAttributes;

        protected transient Bitmap doInBackground(Uri auri[])
        {
            try
            {
                auri = PrintHelperKitkat.access$400(this$0, imageFile, 3500);
            }
            // Misplaced declaration of an exception variable
            catch (Uri auri[])
            {
                return null;
            }
            return auri;
        }

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((Uri[])aobj);
        }

        protected void onCancelled(Bitmap bitmap)
        {
            layoutResultCallback.onLayoutCancelled();
            mLoadBitmap = null;
        }

        protected volatile void onCancelled(Object obj)
        {
            onCancelled((Bitmap)obj);
        }

        protected void onPostExecute(Bitmap bitmap)
        {
            Object obj;
            super.onPostExecute(bitmap);
            obj = bitmap;
            if (bitmap == null)
            {
                break MISSING_BLOCK_LABEL_105;
            }
            if (mPrintActivityRespectsOrientation)
            {
                obj = bitmap;
                if (mOrientation != 0)
                {
                    break MISSING_BLOCK_LABEL_105;
                }
            }
            this;
            JVM INSTR monitorenter ;
            android.print.PrintAttributes.MediaSize mediasize = mAttributes.getMediaSize();
            this;
            JVM INSTR monitorexit ;
            obj = bitmap;
            if (mediasize != null)
            {
                obj = bitmap;
                if (mediasize.isPortrait() != PrintHelperKitkat.access$600(bitmap))
                {
                    obj = new Matrix();
                    ((Matrix) (obj)).postRotate(90F);
                    obj = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), ((Matrix) (obj)), true);
                }
            }
            mBitmap = ((Bitmap) (obj));
            if (obj != null)
            {
                bitmap = (new android.print.PrintDocumentInfo.Builder(jobName)).setContentType(1).setPageCount(1).build();
                boolean flag;
                if (!newPrintAttributes.equals(oldPrintAttributes))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                layoutResultCallback.onLayoutFinished(bitmap, flag);
            } else
            {
                layoutResultCallback.onLayoutFailed(null);
            }
            mLoadBitmap = null;
            return;
            bitmap;
            this;
            JVM INSTR monitorexit ;
            throw bitmap;
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((Bitmap)obj);
        }

        protected void onPreExecute()
        {
            cancellationSignal.setOnCancelListener(new PrintHelperKitkat._cls3._cls1._cls1());
        }

            
            {
                this$1 = final__pcls3;
                cancellationSignal = cancellationsignal;
                newPrintAttributes = printattributes;
                oldPrintAttributes = printattributes1;
                layoutResultCallback = android.print.PrintDocumentAdapter.LayoutResultCallback.this;
                super();
            }
    }

}
