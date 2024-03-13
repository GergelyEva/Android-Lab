package Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CompanyDataSource {
    private SQLiteDatabase database;
    private CompanyDbHelper dbHelper;

    public CompanyDataSource(Context context) {
        dbHelper = new CompanyDbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long addCompany(Company company) {
        ContentValues values = new ContentValues();
        values.put(CompanyContract.CompanyEntry.COLUMN_NAME, company.getName());
        values.put(CompanyContract.CompanyEntry.COLUMN_LOCATION, company.getLocation());
        values.put(CompanyContract.CompanyEntry.COLUMN_LATITUDE, company.getLatitude());
        values.put(CompanyContract.CompanyEntry.COLUMN_LONGITUDE, company.getLongitude());
        values.put(CompanyContract.CompanyEntry.COLUMN_PHONE_NUMBER, company.getPhoneNumber());

        return database.insert(CompanyContract.CompanyEntry.TABLE_NAME, null, values);
    }

}
