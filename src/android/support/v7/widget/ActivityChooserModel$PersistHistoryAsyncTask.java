// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.ComponentName;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.xmlpull.v1.XmlSerializer;

// Referenced classes of package android.support.v7.widget:
//            ActivityChooserModel

private final class this._cls0 extends AsyncTask
{

    final ActivityChooserModel this$0;

    public volatile Object doInBackground(Object aobj[])
    {
        return doInBackground(aobj);
    }

    public transient Void doInBackground(Object aobj[])
    {
        Object obj1;
        List list = (List)aobj[0];
        obj1 = (String)aobj[1];
        doInBackground doinbackground;
        int i;
        int j;
        try
        {
            aobj = mContext.openFileOutput(((String) (obj1)), 0);
        }
        // Misplaced declaration of an exception variable
        catch (Object aobj[])
        {
            Log.e(ActivityChooserModel.LOG_TAG, (new StringBuilder()).append("Error writing historical record file: ").append(((String) (obj1))).toString(), ((Throwable) (aobj)));
            return null;
        }
        obj1 = Xml.newSerializer();
        ((XmlSerializer) (obj1)).setOutput(((java.io.OutputStream) (aobj)), null);
        ((XmlSerializer) (obj1)).startDocument("UTF-8", Boolean.valueOf(true));
        ((XmlSerializer) (obj1)).startTag(null, "historical-records");
        j = list.size();
        i = 0;
_L2:
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        doinbackground = (doInBackground)list.remove(0);
        ((XmlSerializer) (obj1)).startTag(null, "historical-record");
        ((XmlSerializer) (obj1)).attribute(null, "activity", doinbackground.y.flattenToString());
        ((XmlSerializer) (obj1)).attribute(null, "time", String.valueOf(doinbackground.y));
        ((XmlSerializer) (obj1)).attribute(null, "weight", String.valueOf(doinbackground.y));
        ((XmlSerializer) (obj1)).endTag(null, "historical-record");
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        ((XmlSerializer) (obj1)).endTag(null, "historical-records");
        ((XmlSerializer) (obj1)).endDocument();
        mCanReadHistoricalData = true;
        Object obj;
        if (aobj != null)
        {
            try
            {
                ((FileOutputStream) (aobj)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object aobj[]) { }
        }
        return null;
        obj;
        Log.e(ActivityChooserModel.LOG_TAG, (new StringBuilder()).append("Error writing historical record file: ").append(mHistoryFileName).toString(), ((Throwable) (obj)));
        mCanReadHistoricalData = true;
        if (aobj != null)
        {
            try
            {
                ((FileOutputStream) (aobj)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object aobj[]) { }
        }
        continue; /* Loop/switch isn't completed */
        obj;
        Log.e(ActivityChooserModel.LOG_TAG, (new StringBuilder()).append("Error writing historical record file: ").append(mHistoryFileName).toString(), ((Throwable) (obj)));
        mCanReadHistoricalData = true;
        if (aobj != null)
        {
            try
            {
                ((FileOutputStream) (aobj)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object aobj[]) { }
        }
        continue; /* Loop/switch isn't completed */
        obj;
        Log.e(ActivityChooserModel.LOG_TAG, (new StringBuilder()).append("Error writing historical record file: ").append(mHistoryFileName).toString(), ((Throwable) (obj)));
        mCanReadHistoricalData = true;
        if (aobj != null)
        {
            try
            {
                ((FileOutputStream) (aobj)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object aobj[]) { }
        }
        if (true) goto _L4; else goto _L3
_L3:
        break MISSING_BLOCK_LABEL_408;
_L4:
        break MISSING_BLOCK_LABEL_236;
        obj;
        mCanReadHistoricalData = true;
        if (aobj != null)
        {
            try
            {
                ((FileOutputStream) (aobj)).close();
            }
            // Misplaced declaration of an exception variable
            catch (Object aobj[]) { }
        }
        throw obj;
    }

    ()
    {
        this$0 = ActivityChooserModel.this;
        super();
    }
}
