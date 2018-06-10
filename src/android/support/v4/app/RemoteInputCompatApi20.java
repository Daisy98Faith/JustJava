// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.RemoteInput;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class RemoteInputCompatApi20
{

    private static final String EXTRA_DATA_TYPE_RESULTS_DATA = "android.remoteinput.dataTypeResultsData";

    RemoteInputCompatApi20()
    {
    }

    public static void addDataResultToIntent(RemoteInputCompatBase.RemoteInput remoteinput, Intent intent, Map map)
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
        Object obj = getResultsFromIntent(intent);
        int j;
        if (obj != null)
        {
            ((Bundle) (obj)).putAll(bundle);
            bundle = ((Bundle) (obj));
        }
        j = aremoteinput.length;
        for (int i = 0; i < j; i++)
        {
            obj = aremoteinput[i];
            Map map = getDataResultsFromIntent(intent, ((RemoteInputCompatBase.RemoteInput) (obj)).getResultKey());
            RemoteInput.addResultsToIntent(fromCompat(new RemoteInputCompatBase.RemoteInput[] {
                obj
            }), intent, bundle);
            if (map != null)
            {
                addDataResultToIntent(((RemoteInputCompatBase.RemoteInput) (obj)), intent, map);
            }
        }

    }

    static RemoteInput[] fromCompat(RemoteInputCompatBase.RemoteInput aremoteinput[])
    {
        if (aremoteinput != null) goto _L2; else goto _L1
_L1:
        RemoteInput aremoteinput1[] = null;
_L4:
        return aremoteinput1;
_L2:
        RemoteInput aremoteinput2[] = new RemoteInput[aremoteinput.length];
        int i = 0;
        do
        {
            aremoteinput1 = aremoteinput2;
            if (i >= aremoteinput.length)
            {
                continue;
            }
            RemoteInputCompatBase.RemoteInput remoteinput = aremoteinput[i];
            aremoteinput2[i] = (new android.app.RemoteInput.Builder(remoteinput.getResultKey())).setLabel(remoteinput.getLabel()).setChoices(remoteinput.getChoices()).setAllowFreeFormInput(remoteinput.getAllowFreeFormInput()).addExtras(remoteinput.getExtras()).build();
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
        return RemoteInput.getResultsFromIntent(intent);
    }

    static RemoteInputCompatBase.RemoteInput[] toCompat(RemoteInput aremoteinput[], RemoteInputCompatBase.RemoteInput.Factory factory)
    {
        if (aremoteinput == null)
        {
            return null;
        }
        RemoteInputCompatBase.RemoteInput aremoteinput1[] = factory.newArray(aremoteinput.length);
        for (int i = 0; i < aremoteinput.length; i++)
        {
            RemoteInput remoteinput = aremoteinput[i];
            aremoteinput1[i] = factory.build(remoteinput.getResultKey(), remoteinput.getLabel(), remoteinput.getChoices(), remoteinput.getAllowFreeFormInput(), remoteinput.getExtras(), null);
        }

        return aremoteinput1;
    }
}
