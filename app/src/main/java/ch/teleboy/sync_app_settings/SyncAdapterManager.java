package ch.teleboy.sync_app_settings;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Kursulla on 08/09/15.
 */
public class SyncAdapterManager {
    public static final String AUTHORITY = "com.eutechpro.syncadapterexample.provider";
    public static final String ACCOUNT_TYPE = "com.eutechpro.syncadapterexample";
    public static final String ACCOUNT = "dummyaccount";
    public static final String logDebug = "SyncAdapterManager";
    private static Account newAccount;

    public static void init(Context context) {
        newAccount = new Account(ACCOUNT, ACCOUNT_TYPE);
        AccountManager accountManager = (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);
        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
            System.out.println("Dodat acc");
            Log.d(logDebug, "Account Added success");
        } else {
            System.out.println("Ili postoji ili je doslo do sranja");
            Log.d(logDebug, "Account Already  Added");
        }

        ContentResolver.setSyncAutomatically(newAccount, AUTHORITY, true);
    }

    public static void forceRefresh() {

        Log.d(logDebug, "forceRefresh");

        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);

        ContentResolver.requestSync(newAccount, AUTHORITY, bundle);
    }
}
