package Services;

import android.provider.BaseColumns;

public final class CompanyContract {
    private CompanyContract() {}

    public static class CompanyEntry implements BaseColumns {
        public static final String TABLE_NAME = "companies";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_LOCATION = "location";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_PHONE_NUMBER = "phone_number";
    }
}