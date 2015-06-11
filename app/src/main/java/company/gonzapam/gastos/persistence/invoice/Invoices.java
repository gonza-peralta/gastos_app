package company.gonzapam.gastos.persistence.invoice;

import android.provider.BaseColumns;

/**
 * Created by gonza on 11/06/15.
 */
public final class Invoices {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public Invoices() {}

    /* Inner class that defines the table contents */
    public static abstract class InvoiceEntry implements BaseColumns {
        public static final String TABLE_NAME = "invoices";
        public static final String INVOICE_ID = "id";
        public static final String INVOICE_NAME = "name";
        public static final String INVOICE_DATE = "date";
        public static final String INVOICE_AMOUNT = "amount";
    }
}
