// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.util.TypedValue;
import java.io.File;

public class ContextCompat
{

    private static final String TAG = "ContextCompat";
    private static final Object sLock = new Object();
    private static TypedValue sTempValue;

    protected ContextCompat()
    {
    }

    private static transient File buildPath(File file, String as[])
    {
        int j = as.length;
        int i = 0;
        while (i < j) 
        {
            String s = as[i];
            if (file == null)
            {
                file = new File(s);
            } else
            if (s != null)
            {
                file = new File(file, s);
            }
            i++;
        }
        return file;
    }

    public static int checkSelfPermission(Context context, String s)
    {
        if (s == null)
        {
            throw new IllegalArgumentException("permission is null");
        } else
        {
            return context.checkPermission(s, Process.myPid(), Process.myUid());
        }
    }

    public static Context createDeviceProtectedStorageContext(Context context)
    {
        if (android.os.Build.VERSION.SDK_INT >= 24)
        {
            return context.createDeviceProtectedStorageContext();
        } else
        {
            return null;
        }
    }

    private static File createFilesDir(File file)
    {
        android/support/v4/content/ContextCompat;
        JVM INSTR monitorenter ;
        File file1 = file;
        if (file.exists()) goto _L2; else goto _L1
_L1:
        file1 = file;
        if (file.mkdirs()) goto _L2; else goto _L3
_L3:
        boolean flag = file.exists();
        if (!flag) goto _L5; else goto _L4
_L4:
        file1 = file;
_L2:
        android/support/v4/content/ContextCompat;
        JVM INSTR monitorexit ;
        return file1;
_L5:
        Log.w("ContextCompat", (new StringBuilder()).append("Unable to create files subdir ").append(file.getPath()).toString());
        file1 = null;
        if (true) goto _L2; else goto _L6
_L6:
        file;
        throw file;
    }

    public static File getCodeCacheDir(Context context)
    {
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            return context.getCodeCacheDir();
        } else
        {
            return createFilesDir(new File(context.getApplicationInfo().dataDir, "code_cache"));
        }
    }

    public static final int getColor(Context context, int i)
    {
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            return context.getColor(i);
        } else
        {
            return context.getResources().getColor(i);
        }
    }

    public static final ColorStateList getColorStateList(Context context, int i)
    {
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            return context.getColorStateList(i);
        } else
        {
            return context.getResources().getColorStateList(i);
        }
    }

    public static File getDataDir(Context context)
    {
        if (android.os.Build.VERSION.SDK_INT >= 24)
        {
            return context.getDataDir();
        }
        context = context.getApplicationInfo().dataDir;
        if (context != null)
        {
            return new File(context);
        } else
        {
            return null;
        }
    }

    public static final Drawable getDrawable(Context context, int i)
    {
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            return context.getDrawable(i);
        }
        if (android.os.Build.VERSION.SDK_INT >= 16)
        {
            return context.getResources().getDrawable(i);
        }
        synchronized (sLock)
        {
            if (sTempValue == null)
            {
                sTempValue = new TypedValue();
            }
            context.getResources().getValue(i, sTempValue, true);
            i = sTempValue.resourceId;
        }
        return context.getResources().getDrawable(i);
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
    }

    public static File[] getExternalCacheDirs(Context context)
    {
        if (android.os.Build.VERSION.SDK_INT >= 19)
        {
            return context.getExternalCacheDirs();
        } else
        {
            return (new File[] {
                context.getExternalCacheDir()
            });
        }
    }

    public static File[] getExternalFilesDirs(Context context, String s)
    {
        if (android.os.Build.VERSION.SDK_INT >= 19)
        {
            return context.getExternalFilesDirs(s);
        } else
        {
            return (new File[] {
                context.getExternalFilesDir(s)
            });
        }
    }

    public static final File getNoBackupFilesDir(Context context)
    {
        if (android.os.Build.VERSION.SDK_INT >= 21)
        {
            return context.getNoBackupFilesDir();
        } else
        {
            return createFilesDir(new File(context.getApplicationInfo().dataDir, "no_backup"));
        }
    }

    public static File[] getObbDirs(Context context)
    {
        if (android.os.Build.VERSION.SDK_INT >= 19)
        {
            return context.getObbDirs();
        } else
        {
            return (new File[] {
                context.getObbDir()
            });
        }
    }

    public static boolean isDeviceProtectedStorage(Context context)
    {
        if (android.os.Build.VERSION.SDK_INT >= 24)
        {
            return context.isDeviceProtectedStorage();
        } else
        {
            return false;
        }
    }

    public static boolean startActivities(Context context, Intent aintent[])
    {
        return startActivities(context, aintent, null);
    }

    public static boolean startActivities(Context context, Intent aintent[], Bundle bundle)
    {
        if (android.os.Build.VERSION.SDK_INT >= 16)
        {
            context.startActivities(aintent, bundle);
        } else
        {
            context.startActivities(aintent);
        }
        return true;
    }

    public static void startActivity(Context context, Intent intent, Bundle bundle)
    {
        if (android.os.Build.VERSION.SDK_INT >= 16)
        {
            context.startActivity(intent, bundle);
            return;
        } else
        {
            context.startActivity(intent);
            return;
        }
    }

}
