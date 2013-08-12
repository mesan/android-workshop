package no.mesan.android.demo.model.persistence;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class KeywordDao {

	private static final String TABLE = "keywords";

	private static final String AUTO_ID_COL = "_id";
	private static final String KEYWORD_COL = "keyword";
	
	private DbHelper dbHelper;
	private SQLiteDatabase db;

	private static final String CREATE_TABLE = "create table " + TABLE + " " + "(" + AUTO_ID_COL + " integer primary key autoincrement, " + KEYWORD_COL
			+ " text not null);";

	public KeywordDao(Context context) {
		if (dbHelper == null) {
			dbHelper = new DbHelper(context);
		}
	}

	public KeywordDao open(boolean readOnly) {
		if (db == null || !db.isOpen()) {
			if (readOnly) {
				db = dbHelper.getReadableDatabase();
			} else {
				db = dbHelper.getWritableDatabase();
			}
		}

		return this;
	}

	public void close() {
		if (db != null && db.isOpen()) {
			db.close();
		}
	}

	public List<String> getAllKeywords() {

		List<String> keywords = new ArrayList<String>();
		open(true);

		Cursor cursor = db.query(TABLE, new String[] { KEYWORD_COL}, null, null, null, null, null);

		if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {

			do {
				String keyword = cursor.getString(cursor.getColumnIndex(KEYWORD_COL));
				keywords.add(keyword);
			} while (cursor.moveToNext());
		}

		close();

		return keywords;
	}

	public boolean insertKeyword(String keyword) {
		boolean success = false;

		ContentValues values = new ContentValues(1);

		values.put(KEYWORD_COL, keyword);

		open(false);

		success = db.insert(TABLE, KEYWORD_COL, values) > 0;

		close();

		return success;
	}

	public boolean deletePerson(String keyword) {
		open(false);
		
		String[] args = { keyword };
		boolean deleted = db.delete(TABLE, KEYWORD_COL + "=?", args) > 0;
		
		close();

		return deleted;
	}

	private static class DbHelper extends SQLiteOpenHelper {
		private static final String DATABASE_NAME = "keyword_db";

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, 1);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_TABLE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}

	}

}
