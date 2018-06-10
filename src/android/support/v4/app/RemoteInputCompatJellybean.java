// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package android.support.v4.app:
//            RemoteInput

class RemoteInputCompatJellybean
{

    private static final String EXTRA_DATA_TYPE_RESULTS_DATA = "android.remoteinput.dataTypeResultsData";
    public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
    private static final String KEY_ALLOWED_DATA_TYPES = "allowedDataTypes";
    private static final String KEY_ALLOW_FREE_FORM_INPUT = "allowFreeFormInput";
    private static final String KEY_CHOICES = "choices";
    private static final String KEY_EXTRAS = "extras";
    private static final String KEY_LABEL = "label";
    private static final String KEY_RESULT_KEY = "resultKey";
    public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";

    RemoteInputCompatJellybean()
    {
    }

    public static void addDataResultToIntent(RemoteInput remoteinput, Intent intent, Map map)
    {
        Intent intent2 = getClipDataIntentFromIntent(intent);
        Intent intent1 = intent2;
        if (intent2 == null)
        {
            intent1 = new Intent();
        }
        Iterator iterator = map.entrySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            map = (java.util.Map.Entry)iterator.next();
            String s = (String)map.getKey();
            Uri uri = (Uri)map.getValue();
            if (s != null)
            {
                Bundle bundle = intent1.getBundleExtra(getExtraResultsKeyForData(s));
                map = bundle;
                if (bundle == null)
                {
                    map = new Bundle();
                }
                map.putString(remoteinput.getResultKey(), uri.toString());
                intent1.putExtra(getExtraResultsKeyForData(s), map);
            }
        } while (true);
        intent.setClipData(ClipData.newIntent("android.remoteinput.results", intent1));
    }

    static void addResultsToIntent(RemoteInputCompatBase.RemoteInput aremoteinput[], Intent intent, Bundle bundle)
    {
        Object obj = getClipDataIntentFromIntent(intent);
        Intent intent1 = ((Intent) (obj));
        if (obj == null)
        {
            intent1 = new Intent();
        }
        Bundle bundle1 = intent1.getBundleExtra("android.remoteinput.resultsData");
        obj = bundle1;
        if (bundle1 == null)
        {
            obj = new Bundle();
        }
        int j = aremoteinput.length;
        for (int i = 0; i < j; i++)
        {
            RemoteInputCompatBase.RemoteInput remoteinput = aremoteinput[i];
            Object obj1 = bundle.get(remoteinput.getResultKey());
            if (obj1 instanceof CharSequence)
            {
                ((Bundle) (obj)).putCharSequence(remoteinput.getResultKey(), (CharSequence)obj1);
            }
        }

        intent1.putExtra("android.remoteinput.resultsData", ((Bundle) (obj)));
        intent.setClipData(ClipData.newIntent("android.remoteinput.results", intent1));
    }

    static RemoteInputCompatBase.RemoteInput fromBundle(Bundle bundle, RemoteInputCompatBase.RemoteInput.Factory factory)
    {
        Object obj = bundle.getStringArrayList("allowedDataTypes");
        HashSet hashset = new HashSet();
        if (obj != null)
        {
            for (obj = ((ArrayList) (obj)).iterator(); ((Iterator) (obj)).hasNext(); hashset.add((String)((Iterator) (obj)).next())) { }
        }
        return factory.build(bundle.getString("resultKey"), bundle.getCharSequence("label"), bundle.getCharSequenceArray("choices"), bundle.getBoolean("allowFreeFormInput"), bundle.getBundle("extras"), hashset);
    }

    static RemoteInputCompatBase.RemoteInput[] fromBundleArray(Bundle abundle[], RemoteInputCompatBase.RemoteInput.Factory factory)
    {
        if (abundle != null) goto _L2; else goto _L1
_L1:
        RemoteInputCompatBase.RemoteInput aremoteinput[] = null;
_L4:
        return aremoteinput;
_L2:
        RemoteInputCompatBase.RemoteInput aremoteinput1[] = factory.newArray(abundle.length);
        int i = 0;
        do
        {
            aremoteinput = aremoteinput1;
            if (i >= abundle.length)
            {
                continue;
            }
            aremoteinput1[i] = fromBundle(abundle[i], factory);
            i++;
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    private static Intent getClipDataIntentFromIntent(Intent intent)
    {
        intent = intent.getClipData();
        ClipDescription clipdescription;
        if (intent != null)
        {
            if ((clipdescription = intent.getDescription()).hasMimeType("text/vnd.android.intent") && clipdescription.getLabel().equals("android.remoteinput.results"))
            {
                return intent.getItemAt(0).getIntent();
            }
        }
        return null;
    }

    static Map getDataResultsFromIntent(Intent intent, String s)
    {
        intent = getClipDataIntentFromIntent(intent);
        if (intent == null)
        {
            return null;
        }
        HashMap hashmap = new HashMap();
        Iterator iterator = intent.getExtras().keySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            String s2 = (String)iterator.next();
            if (s2.startsWith("android.remoteinput.dataTypeResultsData"))
            {
                String s1 = s2.substring("android.remoteinput.dataTypeResultsData".length());
                if (s1 != null && !s1.isEmpty())
                {
                    s2 = intent.getBundleExtra(s2).getString(s);
                    if (s2 != null && !s2.isEmpty())
                    {
                        hashmap.put(s1, Uri.parse(s2));
                    }
                }
            }
        } while (true);
        intent = hashmap;
        if (hashmap.isEmpty())
        {
            intent = null;
        }
        return intent;
    }

    private static String getExtraResultsKeyForData(String s)
    {
        return (new StringBuilder()).append("android.remoteinput.dataTypeResultsData").append(s).toString();
    }

    static Bundle getResultsFromIntent(Intent intent)
    {
        intent = getClipDataIntentFromIntent(intent);
        if (intent == null)
        {
            return null;
        } else
        {
            return (Bundle)intent.getExtras().getParcelable("android.remoteinput.resultsData");
        }
    }

    static Bundle toBundle(RemoteInputCompatBase.RemoteInput remoteinput)
    {
        Bundle bundle = new Bundle();
        bundle.putString("resultKey", remoteinput.getResultKey());
        bundle.putCharSequence("label", remoteinput.getLabel());
        bundle.putCharSequenceArray("choices", remoteinput.getChoices());
        bundle.putBoolean("allowFreeFormInput", remoteinput.getAllowFreeFormInput());
        bundle.putBundle("extras", remoteinput.getExtras());
        Object obj = remoteinput.getAllowedDataTypes();
        if (obj != null && !((Set) (obj)).isEmpty())
        {
            remoteinput = new ArrayList(((Set) (obj)).size());
            for (obj = ((Set) (obj)).iterator(); ((Iterator) (obj)).hasNext(); remoteinput.add((String)((Iterator) (obj)).next())) { }
            bundle.putStringArrayList("allowedDataTypes", remoteinput);
        }
        return bundle;
    }

    static Bundle[] toBundleArray(RemoteInputCompatBase.RemoteInput aremoteinput[])
    {
        if (aremoteinput != null) goto _L2; else goto _L1
_L1:
        Bundle abundle[] = null;
_L4:
        return abundle;
_L2:
        Bundle abundle1[] = new Bundle[aremoteinput.length];
        int i = 0;
        do
        {
            abundle = abundle1;
            if (i >= aremoteinput.length)
            {
                continue;
            }
            abundle1[i] = toBundle(aremoteinput[i]);
            i++;
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }
}
