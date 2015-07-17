package com.example.comarch.firstapp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by Comarch on 2015-07-16.
 */
public class ExampleProvider extends ContentProvider {

    private static final String LOG = ContentProvider.class.getSimpleName();

    private static final String PROVIDER_NAME = "com.example.comarch.Data";
    static final String DATA_URL = "content://" + PROVIDER_NAME + "/DATA";
    static final Uri CONTENT_URI = Uri.parse(DATA_URL);

    static final String NAME = "name";
    static final String SURNAME = "surname";
    private static final String _ID = "_id";

    private static final int DATA = 1;
    private static final int DATA_ID = 2;

    private static HashMap<String, String> DATA_PROJECTION_MAP;

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "DATA", DATA);
        uriMatcher.addURI(PROVIDER_NAME, "DATA/#", DATA_ID);
    }

    private SQLiteDatabase db;
    private static final String DATABASE_NAME = "EXAMPLE";
    private static final String DATA_TABLE_NAME = "DATA";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_DATA_TABLE =
            " CREATE TABLE " + DATA_TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " name TEXT NOT NULL, " +
                    " surname TEXT NOT NULL);";

    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DATA_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        if (db == null) {
            return false;
        }
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(DATA_TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case DATA:
                qb.setProjectionMap(DATA_PROJECTION_MAP);
                break;
            case DATA_ID:
                qb.appendWhere(_ID + "=" + uri.getPathSegments().get(1));
                break;
            default:
                Toast.makeText(getContext(), "No matcher URI", Toast.LENGTH_SHORT).show();
        }

        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowId = db.insert(DATA_TABLE_NAME, "", values);
        if (rowId > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

}
