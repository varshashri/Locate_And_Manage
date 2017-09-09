package venky.com.bookcabapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class DBController extends SQLiteOpenHelper {

	public DBController(Context applicationcontext) {
        super(applicationcontext,"user.db", null, 1);
    }
	//Creates Table
	@Override
	public void onCreate(SQLiteDatabase database) {
		String query;
		    query = "CREATE TABLE userdetails (id INTEGER PRIMARY KEY ASC,name TEXT,contactnum TEXT,Location TEXT,email TEXT,idproof TEXT,password TEXT)";
        database.execSQL(query);

        
	}
	@Override
	public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
		String query;
		query = "DROP TABLE IF EXISTS userdetails";
		database.execSQL(query);
        onCreate(database);
	}
	
	
	/**
	 * Inserts User into SQLite DB
	 * @param queryValues
	 */
	//HashMap<String, String>
	public long insertUser( ContentValues queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();
	//	ContentValues values = new ContentValues();

	/*	values.put("name", queryValues.get("name"));
		values.put("contactnum", queryValues.get("contactnum"));
		values.put("Location", queryValues.get("Location"));
		values.put("email", queryValues.get("email"));
		values.put("idproof", queryValues.get("idproof"));
		values.put("password", queryValues.get("password"));*/
		long r=database.insert("userdetails", null, queryValues);
		database.close();
		return r;
	}



	public String getPassword(HashMap<String, String> queryValues) {
		String pass;
		String userid=queryValues.get("email");
		String selectQuery = "SELECT * FROM userdetails where email='"+userid+"'";
		Log.d("select query",selectQuery);
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {

			 pass=cursor.getString(6);
				return pass;
			} while (cursor.moveToNext());

		}else{
			database.close();
			return null;

		}


	}
	public String getType(HashMap<String, String> queryValues) {
		String pass;
		String userid=queryValues.get("email");
		String selectQuery = "SELECT * FROM userdetails where email='"+userid+"'";
		Log.d("select query",selectQuery);
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {

				pass=cursor.getString(3);
				return pass;
			} while (cursor.moveToNext());

		}else{
			database.close();
			return null;

		}


	}




	


	
	
	

	
	


	
	
}
