package company.gonzapam.gastos.persistence.invoice;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;


/**
 * Created by gonza on 11/06/15.
 */

public class InvoicesHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "expenses.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Invoice, Integer> invoiceDao;

    public InvoicesHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Invoice.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        onCreate(db, connectionSource);
    }

    public Dao<Invoice, Integer> getInvoiceDao() throws SQLException {
        if (invoiceDao == null) {
            try {
                invoiceDao = getDao(Invoice.class);
            } catch (java.sql.SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return invoiceDao;
    }

    @Override
    public void close() {
        super.close();
        invoiceDao = null;
    }

}