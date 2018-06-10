// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import java.io.FileDescriptor;
import java.io.PrintWriter;

// Referenced classes of package android.support.v4.app:
//            FragmentHostCallback, FragmentActivity, ActivityCompat, Fragment

class this._cls0 extends FragmentHostCallback
{

    final FragmentActivity this$0;

    public void onAttachFragment(Fragment fragment)
    {
        FragmentActivity.this.onAttachFragment(fragment);
    }

    public void onDump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        dump(s, filedescriptor, printwriter, as);
    }

    public View onFindViewById(int i)
    {
        return findViewById(i);
    }

    public FragmentActivity onGetHost()
    {
        return FragmentActivity.this;
    }

    public volatile Object onGetHost()
    {
        return onGetHost();
    }

    public LayoutInflater onGetLayoutInflater()
    {
        return getLayoutInflater().cloneInContext(FragmentActivity.this);
    }

    public int onGetWindowAnimations()
    {
        Window window = getWindow();
        if (window == null)
        {
            return 0;
        } else
        {
            return window.getAttributes().owAnimations;
        }
    }

    public boolean onHasView()
    {
        Window window = getWindow();
        return window != null && window.peekDecorView() != null;
    }

    public boolean onHasWindowAnimations()
    {
        return getWindow() != null;
    }

    public void onRequestPermissionsFromFragment(Fragment fragment, String as[], int i)
    {
        requestPermissionsFromFragment(fragment, as, i);
    }

    public boolean onShouldSaveFragmentState(Fragment fragment)
    {
        return !isFinishing();
    }

    public boolean onShouldShowRequestPermissionRationale(String s)
    {
        return ActivityCompat.shouldShowRequestPermissionRationale(FragmentActivity.this, s);
    }

    public void onStartActivityFromFragment(Fragment fragment, Intent intent, int i)
    {
        startActivityFromFragment(fragment, intent, i);
    }

    public void onStartActivityFromFragment(Fragment fragment, Intent intent, int i, Bundle bundle)
    {
        startActivityFromFragment(fragment, intent, i, bundle);
    }

    public void onStartIntentSenderFromFragment(Fragment fragment, IntentSender intentsender, int i, Intent intent, int j, int k, int l, 
            Bundle bundle)
        throws android.content.n
    {
        startIntentSenderFromFragment(fragment, intentsender, i, intent, j, k, l, bundle);
    }

    public void onSupportInvalidateOptionsMenu()
    {
        supportInvalidateOptionsMenu();
    }

    public ()
    {
        this$0 = FragmentActivity.this;
        super(FragmentActivity.this);
    }
}
