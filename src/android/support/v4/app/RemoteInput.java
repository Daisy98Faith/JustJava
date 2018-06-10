// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Referenced classes of package android.support.v4.app:
//            RemoteInputCompatApi20, RemoteInputCompatJellybean

public final class RemoteInput extends RemoteInputCompatBase.RemoteInput
{
    public static final class Builder
    {

        private boolean mAllowFreeFormTextInput;
        private final Set mAllowedDataTypes = new HashSet();
        private CharSequence mChoices[];
        private Bundle mExtras;
        private CharSequence mLabel;
        private final String mResultKey;

        public Builder addExtras(Bundle bundle)
        {
            if (bundle != null)
            {
                mExtras.putAll(bundle);
            }
            return this;
        }

        public RemoteInput build()
        {
            return new RemoteInput(mResultKey, mLabel, mChoices, mAllowFreeFormTextInput, mExtras, mAllowedDataTypes);
        }

        public Bundle getExtras()
        {
            return mExtras;
        }

        public Builder setAllowDataType(String s, boolean flag)
        {
            if (flag)
            {
                mAllowedDataTypes.add(s);
                return this;
            } else
            {
                mAllowedDataTypes.remove(s);
                return this;
            }
        }

        public Builder setAllowFreeFormInput(boolean flag)
        {
            mAllowFreeFormTextInput = flag;
            return this;
        }

        public Builder setChoices(CharSequence acharsequence[])
        {
            mChoices = acharsequence;
            return this;
        }

        public Builder setLabel(CharSequence charsequence)
        {
            mLabel = charsequence;
            return this;
        }

        public Builder(String s)
        {
            mAllowFreeFormTextInput = true;
            mExtras = new Bundle();
            if (s == null)
            {
                throw new IllegalArgumentException("Result key can't be null");
            } else
            {
                mResultKey = s;
                return;
            }
        }
    }

    static interface Impl
    {

        public abstract void addDataResultToIntent(RemoteInput remoteinput, Intent intent, Map map);

        public abstract void addResultsToIntent(RemoteInput aremoteinput[], Intent intent, Bundle bundle);

        public abstract Map getDataResultsFromIntent(Intent intent, String s);

        public abstract Bundle getResultsFromIntent(Intent intent);
    }

    static class ImplApi20
        implements Impl
    {

        public void addDataResultToIntent(RemoteInput remoteinput, Intent intent, Map map)
        {
            RemoteInputCompatApi20.addDataResultToIntent(remoteinput, intent, map);
        }

        public void addResultsToIntent(RemoteInput aremoteinput[], Intent intent, Bundle bundle)
        {
            RemoteInputCompatApi20.addResultsToIntent(aremoteinput, intent, bundle);
        }

        public Map getDataResultsFromIntent(Intent intent, String s)
        {
            return RemoteInputCompatApi20.getDataResultsFromIntent(intent, s);
        }

        public Bundle getResultsFromIntent(Intent intent)
        {
            return RemoteInputCompatApi20.getResultsFromIntent(intent);
        }

        ImplApi20()
        {
        }
    }

    static class ImplBase
        implements Impl
    {

        public void addDataResultToIntent(RemoteInput remoteinput, Intent intent, Map map)
        {
            Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
        }

        public void addResultsToIntent(RemoteInput aremoteinput[], Intent intent, Bundle bundle)
        {
            Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
        }

        public Map getDataResultsFromIntent(Intent intent, String s)
        {
            Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
            return null;
        }

        public Bundle getResultsFromIntent(Intent intent)
        {
            Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
            return null;
        }

        ImplBase()
        {
        }
    }

    static class ImplJellybean
        implements Impl
    {

        public void addDataResultToIntent(RemoteInput remoteinput, Intent intent, Map map)
        {
            RemoteInputCompatJellybean.addDataResultToIntent(remoteinput, intent, map);
        }

        public void addResultsToIntent(RemoteInput aremoteinput[], Intent intent, Bundle bundle)
        {
            RemoteInputCompatJellybean.addResultsToIntent(aremoteinput, intent, bundle);
        }

        public Map getDataResultsFromIntent(Intent intent, String s)
        {
            return RemoteInputCompatJellybean.getDataResultsFromIntent(intent, s);
        }

        public Bundle getResultsFromIntent(Intent intent)
        {
            return RemoteInputCompatJellybean.getResultsFromIntent(intent);
        }

        ImplJellybean()
        {
        }
    }


    private static final String EXTRA_DATA_TYPE_RESULTS_DATA = "android.remoteinput.dataTypeResultsData";
    public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
    public static final RemoteInputCompatBase.RemoteInput.Factory FACTORY = new RemoteInputCompatBase.RemoteInput.Factory() {

        public RemoteInput build(String s, CharSequence charsequence, CharSequence acharsequence[], boolean flag, Bundle bundle, Set set)
        {
            return new RemoteInput(s, charsequence, acharsequence, flag, bundle, set);
        }

        public volatile RemoteInputCompatBase.RemoteInput build(String s, CharSequence charsequence, CharSequence acharsequence[], boolean flag, Bundle bundle, Set set)
        {
            return build(s, charsequence, acharsequence, flag, bundle, set);
        }

        public RemoteInput[] newArray(int i)
        {
            return new RemoteInput[i];
        }

        public volatile RemoteInputCompatBase.RemoteInput[] newArray(int i)
        {
            return newArray(i);
        }

    };
    private static final Impl IMPL;
    public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";
    private static final String TAG = "RemoteInput";
    private final boolean mAllowFreeFormTextInput;
    private final Set mAllowedDataTypes;
    private final CharSequence mChoices[];
    private final Bundle mExtras;
    private final CharSequence mLabel;
    private final String mResultKey;

    RemoteInput(String s, CharSequence charsequence, CharSequence acharsequence[], boolean flag, Bundle bundle, Set set)
    {
        mResultKey = s;
        mLabel = charsequence;
        mChoices = acharsequence;
        mAllowFreeFormTextInput = flag;
        mExtras = bundle;
        mAllowedDataTypes = set;
    }

    public static void addDataResultToIntent(RemoteInput remoteinput, Intent intent, Map map)
    {
        IMPL.addDataResultToIntent(remoteinput, intent, map);
    }

    public static void addResultsToIntent(RemoteInput aremoteinput[], Intent intent, Bundle bundle)
    {
        IMPL.addResultsToIntent(aremoteinput, intent, bundle);
    }

    public static Map getDataResultsFromIntent(Intent intent, String s)
    {
        return IMPL.getDataResultsFromIntent(intent, s);
    }

    public static Bundle getResultsFromIntent(Intent intent)
    {
        return IMPL.getResultsFromIntent(intent);
    }

    public boolean getAllowFreeFormInput()
    {
        return mAllowFreeFormTextInput;
    }

    public Set getAllowedDataTypes()
    {
        return mAllowedDataTypes;
    }

    public CharSequence[] getChoices()
    {
        return mChoices;
    }

    public Bundle getExtras()
    {
        return mExtras;
    }

    public CharSequence getLabel()
    {
        return mLabel;
    }

    public String getResultKey()
    {
        return mResultKey;
    }

    public boolean isDataOnly()
    {
        return !getAllowFreeFormInput() && (getChoices() == null || getChoices().length == 0) && getAllowedDataTypes() != null && !getAllowedDataTypes().isEmpty();
    }

    static 
    {
        if (android.os.Build.VERSION.SDK_INT >= 20)
        {
            IMPL = new ImplApi20();
        } else
        if (android.os.Build.VERSION.SDK_INT >= 16)
        {
            IMPL = new ImplJellybean();
        } else
        {
            IMPL = new ImplBase();
        }
    }
}
