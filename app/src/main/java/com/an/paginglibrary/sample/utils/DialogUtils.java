package com.an.paginglibrary.sample.utils;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import java.util.List;

public class DialogUtils {
    public static void dismissAllDialogsFragment(FragmentManager manager) {
        List<Fragment> fragments = manager.getFragments();

        if (fragments == null)
            return;

        for (Fragment fragment : fragments) {
            if (fragment instanceof DialogFragment) {
                DialogFragment dialogFragment = (DialogFragment) fragment;
                dialogFragment.dismissAllowingStateLoss();
            }

            if (fragment != null) {
                FragmentManager childFragmentManager = fragment.getChildFragmentManager();
                if (childFragmentManager != null)
                    dismissAllDialogsFragment(childFragmentManager);
            }

        }
    }

    public static void showAlert(Context context, String errorMessage) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
    }
}
