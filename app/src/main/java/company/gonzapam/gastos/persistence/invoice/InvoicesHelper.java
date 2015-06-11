package company.gonzapam.gastos.persistence.invoice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gonza on 11/06/15.
 */

public class InvoicesHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String DATE_TYPE = " TEXT";
    private static final String REAL_TYPE = " REAL";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Invoices.InvoiceEntry.TABLE_NAME + " (" +
                    Invoices.InvoiceEntry.INVOICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Invoices.InvoiceEntry.INVOICE_NAME + TEXT_TYPE + " NOT NULL, " +
                    Invoices.InvoiceEntry.INVOICE_DATE + DATE_TYPE + " NOT NULL, " +
                    Invoices.InvoiceEntry.INVOICE_AMOUNT + REAL_TYPE + " NOT NULL" +
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Invoices.InvoiceEntry.TABLE_NAME;

    public InvoicesHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}