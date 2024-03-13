package Services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import Services.CompanyContract;

public class CompanyDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Company.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CompanyContract.CompanyEntry.TABLE_NAME + " (" +
                    CompanyContract.CompanyEntry._ID + " INTEGER PRIMARY KEY," +
                    CompanyContract.CompanyEntry.COLUMN_NAME + " TEXT," +
                    CompanyContract.CompanyEntry.COLUMN_LOCATION + " TEXT," +
                    CompanyContract.CompanyEntry.COLUMN_LATITUDE + " TEXT," +
                    CompanyContract.CompanyEntry.COLUMN_LONGITUDE + " TEXT," +
                    CompanyContract.CompanyEntry.COLUMN_PHONE_NUMBER + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CompanyContract.CompanyEntry.TABLE_NAME;

    public CompanyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
