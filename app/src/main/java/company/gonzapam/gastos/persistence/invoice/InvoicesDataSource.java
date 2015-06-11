package company.gonzapam.gastos.persistence.invoice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gonza on 11/06/15.
 */

public class InvoicesDataSource {

    // Database fields
    private SQLiteDatabase database;
    private InvoicesHelper dbHelper;
    private String[] allColumns = { Invoices.InvoiceEntry.INVOICE_ID,
            Invoices.InvoiceEntry.INVOICE_NAME,
            Invoices.InvoiceEntry.INVOICE_DATE,
            Invoices.InvoiceEntry.INVOICE_AMOUNT};

    public InvoicesDataSource(Context context) {
        dbHelper = new InvoicesHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Invoice createInvoice(String name, Date date, Float amount) {
        ContentValues values = new ContentValues();
        values.put(Invoices.InvoiceEntry.INVOICE_NAME, name);
        values.put(Invoices.InvoiceEntry.INVOICE_DATE, date.toString());
        values.put(Invoices.InvoiceEntry.INVOICE_AMOUNT, amount);
        long insertId = database.insert(Invoices.InvoiceEntry.TABLE_NAME, null,
                values);
        Cursor cursor = database.query(Invoices.InvoiceEntry.TABLE_NAME,
                allColumns, Invoices.InvoiceEntry.INVOICE_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Invoice newComment = cursorToInvoice(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteInvoice(Invoice invoice) {
        long id = invoice.getId();
        System.out.println("Invoice deleted with id: " + id);
        database.delete(Invoices.InvoiceEntry.TABLE_NAME, Invoices.InvoiceEntry.INVOICE_ID
                + " = " + id, null);
    }

    public List<Invoice> getAllInvoices() {
        List<Invoice> invoices = new ArrayList<Invoice>();

        Cursor cursor = database.query(Invoices.InvoiceEntry.TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Invoice invoice = cursorToInvoice(cursor);
            invoices.add(invoice);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return invoices;
    }

    private Invoice cursorToInvoice(Cursor cursor) {
        Invoice invoice = new Invoice();
        invoice.setId(cursor.getLong(0));
        invoice.setName(cursor.getString(1));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
        Date date = new Date();
        try {
            date= dateFormat.parse(cursor.getString(2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        invoice.setDate(date);
        invoice.setAmount(cursor.getFloat(3));
        return invoice;
    }
}
