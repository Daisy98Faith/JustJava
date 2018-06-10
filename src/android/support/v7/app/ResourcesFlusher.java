// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.content.res.Resources;
import android.util.Log;
import android.util.LongSparseArray;
import java.lang.reflect.Field;
import java.util.Map;

class ResourcesFlusher
{

    private static final String TAG = "ResourcesFlusher";
    private static Field sDrawableCacheField;
    private static boolean sDrawableCacheFieldFetched;
    private static Field sResourcesImplField;
    private static boolean sResourcesImplFieldFetched;
    private static Class sThemedResourceCacheClazz;
    private static boolean sThemedResourceCacheClazzFetched;
    private static Field sThemedResourceCache_mUnthemedEntriesField;
    private static boolean sThemedResourceCache_mUnthemedEntriesFieldFetched;

    ResourcesFlusher()
    {
    }

    static boolean flush(Resources resources)
    {
        int i = android.os.Build.VERSION.SDK_INT;
        if (i >= 24)
        {
            return flushNougats(resources);
        }
        if (i >= 23)
        {
            return flushMarshmallows(resources);
        }
        if (i >= 21)
        {
            return flushLollipops(resources);
        } else
        {
            return false;
        }
    }

    private static boolean flushLollipops(Resources resources)
    {
        if (!sDrawableCacheFieldFetched)
        {
            try
            {
                sDrawableCacheField = android/content/res/Resources.getDeclaredField("mDrawableCache");
                sDrawableCacheField.setAccessible(true);
            }
            // Misplaced declaration of an exception variable
            catch (NoSuchFieldException nosuchfieldexception)
            {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", nosuchfieldexception);
            }
            sDrawableCacheFieldFetched = true;
        }
        if (sDrawableCacheField != null)
        {
            NoSuchFieldException nosuchfieldexception = null;
            try
            {
                resources = (Map)sDrawableCacheField.get(resources);
            }
            // Misplaced declaration of an exception variable
            catch (Resources resources)
            {
                Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", resources);
                resources = nosuchfieldexception;
            }
            if (resources != null)
            {
                resources.clear();
                return true;
            }
        }
        return false;
    }

    private static boolean flushMarshmallows(Resources resources)
    {
        boolean flag = true;
        Object obj;
        Object obj1;
        if (!sDrawableCacheFieldFetched)
        {
            try
            {
                sDrawableCacheField = android/content/res/Resources.getDeclaredField("mDrawableCache");
                sDrawableCacheField.setAccessible(true);
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", ((Throwable) (obj)));
            }
            sDrawableCacheFieldFetched = true;
        }
        obj1 = null;
        obj = obj1;
        if (sDrawableCacheField != null)
        {
            try
            {
                obj = sDrawableCacheField.get(resources);
            }
            // Misplaced declaration of an exception variable
            catch (Resources resources)
            {
                Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", resources);
                obj = obj1;
            }
        }
        if (obj == null)
        {
            return false;
        }
        if (obj == null || !flushThemedResourcesCache(obj))
        {
            flag = false;
        }
        return flag;
    }

    private static boolean flushNougats(Resources resources)
    {
        boolean flag = true;
        if (!sResourcesImplFieldFetched)
        {
            try
            {
                sResourcesImplField = android/content/res/Resources.getDeclaredField("mResourcesImpl");
                sResourcesImplField.setAccessible(true);
            }
            catch (NoSuchFieldException nosuchfieldexception)
            {
                Log.e("ResourcesFlusher", "Could not retrieve Resources#mResourcesImpl field", nosuchfieldexception);
            }
            sResourcesImplFieldFetched = true;
        }
        if (sResourcesImplField != null)
        {
            Object obj = null;
            try
            {
                resources = ((Resources) (sResourcesImplField.get(resources)));
            }
            // Misplaced declaration of an exception variable
            catch (Resources resources)
            {
                Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mResourcesImpl", resources);
                resources = ((Resources) (obj));
            }
            if (resources != null)
            {
                Object obj1;
                if (!sDrawableCacheFieldFetched)
                {
                    try
                    {
                        sDrawableCacheField = resources.getClass().getDeclaredField("mDrawableCache");
                        sDrawableCacheField.setAccessible(true);
                    }
                    catch (NoSuchFieldException nosuchfieldexception1)
                    {
                        Log.e("ResourcesFlusher", "Could not retrieve ResourcesImpl#mDrawableCache field", nosuchfieldexception1);
                    }
                    sDrawableCacheFieldFetched = true;
                }
                obj1 = null;
                obj = obj1;
                if (sDrawableCacheField != null)
                {
                    try
                    {
                        obj = sDrawableCacheField.get(resources);
                    }
                    // Misplaced declaration of an exception variable
                    catch (Resources resources)
                    {
                        Log.e("ResourcesFlusher", "Could not retrieve value from ResourcesImpl#mDrawableCache", resources);
                        nosuchfieldexception1 = obj1;
                    }
                }
                if (obj == null || !flushThemedResourcesCache(obj))
                {
                    flag = false;
                }
                return flag;
            }
        }
        return false;
    }

    private static boolean flushThemedResourcesCache(Object obj)
    {
        if (!sThemedResourceCacheClazzFetched)
        {
            try
            {
                sThemedResourceCacheClazz = Class.forName("android.content.res.ThemedResourceCache");
            }
            catch (ClassNotFoundException classnotfoundexception)
            {
                Log.e("ResourcesFlusher", "Could not find ThemedResourceCache class", classnotfoundexception);
            }
            sThemedResourceCacheClazzFetched = true;
        }
        if (sThemedResourceCacheClazz == null)
        {
            return false;
        }
        if (!sThemedResourceCache_mUnthemedEntriesFieldFetched)
        {
            try
            {
                sThemedResourceCache_mUnthemedEntriesField = sThemedResourceCacheClazz.getDeclaredField("mUnthemedEntries");
                sThemedResourceCache_mUnthemedEntriesField.setAccessible(true);
            }
            catch (NoSuchFieldException nosuchfieldexception)
            {
                Log.e("ResourcesFlusher", "Could not retrieve ThemedResourceCache#mUnthemedEntries field", nosuchfieldexception);
            }
            sThemedResourceCache_mUnthemedEntriesFieldFetched = true;
        }
        if (sThemedResourceCache_mUnthemedEntriesField == null)
        {
            return false;
        }
        Object obj1 = null;
        try
        {
            obj = (LongSparseArray)sThemedResourceCache_mUnthemedEntriesField.get(obj);
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            Log.e("ResourcesFlusher", "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", ((Throwable) (obj)));
            obj = obj1;
        }
        if (obj != null)
        {
            ((LongSparseArray) (obj)).clear();
            return true;
        } else
        {
            return false;
        }
    }
}
