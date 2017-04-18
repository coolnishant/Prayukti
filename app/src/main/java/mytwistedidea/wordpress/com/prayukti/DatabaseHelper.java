package mytwistedidea.wordpress.com.prayukti;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Nishant on 02-03-2017.
 */

public class DatabaseHelper {

    MyHelper helper;
    public static String filename = "MySharedString";

    public DatabaseHelper(Context applicationContext) {
        helper = new MyHelper(applicationContext);
    }

    public long insertRegStudent(String uniqid, String name, String phone,
                                 String email, String tsize, String rollno){

        Log.e("s","here insertP");
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

//        contentValues.put(MyHelper.PERIOD,periodNo);
        contentValues.put(MyHelper.ID,uniqid);
        contentValues.put(MyHelper.NAME,name);
        contentValues.put(MyHelper.PHONE,phone);
        contentValues.put(MyHelper.EMAIL,email);
        contentValues.put(MyHelper.TSIZE,tsize);
        contentValues.put(MyHelper.ROLLNO,rollno);
//        contentValues.put(MyHelper,);
        long id = sqLiteDatabase.insert(MyHelper.TABLE_NAME_REGISTERED,null,contentValues);
        Log.d("ih"," Here it is inINsert end");
        sqLiteDatabase.close();
        return id;
    }

    public long insertNewContact(String name, String phone,
                                 String email, String post, String event, String subevent){

        Log.e("s","here insertP");
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

//        contentValues.put(MyHelper.PERIOD,periodNo);
        contentValues.put(MyHelper.NAME,name);
        contentValues.put(MyHelper.PHONE,phone);
        contentValues.put(MyHelper.EMAIL,email);
        contentValues.put(MyHelper.POST,post);
        contentValues.put(MyHelper.EVENT,event);
        contentValues.put(MyHelper.SUBEVENT,subevent);
//        contentValues.put(MyHelper,);
        long id = sqLiteDatabase.insert(MyHelper.TABLE_NAME_CONTACT,null,contentValues);
        Log.d("ih"," Here it is inINsert end");
        sqLiteDatabase.close();
        return id;
    }

    public long insertNotification(String nid, String neventname, String neventcontent,
                                 String ntime){

        Log.e("s","here insertP");
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyHelper.NID,nid);
        contentValues.put(MyHelper.NEVENTNAME,neventname);
        contentValues.put(MyHelper.NEVENTCONTENT,neventcontent);
        contentValues.put(MyHelper.NTIME,ntime);
        long id = sqLiteDatabase.insert(MyHelper.TABLE_NAME_NOTIFICATION,null,contentValues);
        Log.d("ih"," Here it is insert end");
        sqLiteDatabase.close();
        return id;
    }

    public void resetAllData(Context context) {
        context.deleteDatabase(MyHelper.DATABASE_NAME);
        saveNidl(context);
        Toast.makeText(context,"RESET DONE!",Toast.LENGTH_SHORT).show();
    }
    private void saveNidl(Context context) {
        SharedPreferences someData = context.getSharedPreferences(filename, 0);
        SharedPreferences.Editor editor = someData.edit();
        editor.clear();
        editor.putString("nidl", "0");
        editor.commit();
    }


    public ArrayList<String> getAllRegStudent(){

        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();
        String columns[] = {
                MyHelper.ID,
                MyHelper.NAME,
                MyHelper.PHONE,
                MyHelper.EMAIL,
                MyHelper.TSIZE,
                MyHelper.ROLLNO,
        };

        String TABLE_NAME = MyHelper.TABLE_NAME_REGISTERED;

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,columns,null,null,null,null, null);
        if(cursor.moveToFirst()){
            do{
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.ID)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.NAME)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.PHONE)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.EMAIL)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.TSIZE)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.ROLLNO)));
//                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper)));
            }while (cursor.moveToNext());
        }if(arrayList.size() == 0){
            arrayList.add(0," ");
            return arrayList;
        }
        return arrayList;
    }


    public ArrayList<String> getAllNOtification(){

        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();
        String columns[] = {
                MyHelper.NIDL,
                MyHelper.NID,
                MyHelper.NEVENTNAME,
                MyHelper.NEVENTCONTENT,
                MyHelper.NTIME
        };

        String TABLE_NAME = MyHelper.TABLE_NAME_NOTIFICATION;

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,columns,null,null,null,null, null);
        if(cursor.moveToFirst()){
            do{
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.NIDL)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.NID)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.NEVENTNAME)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.NEVENTCONTENT)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.NTIME)));
            }while (cursor.moveToNext());
        }if(arrayList.size() == 0){
            arrayList.add(0," ");
            return arrayList;
        }
        return arrayList;
    }


    public ArrayList<String> getContactByPost(String post){

        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();
        String columns[] = {
                MyHelper.NAME,
                MyHelper.PHONE,
                MyHelper.EMAIL,
                MyHelper.EVENT,
                MyHelper.SUBEVENT,
        };

        String TABLE_NAME = MyHelper.TABLE_NAME_CONTACT;

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,columns,null,null,null,null, null);
        if(cursor.moveToFirst()){
            do{
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.NAME)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.PHONE)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.EMAIL)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.EVENT)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.SUBEVENT)));
//                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper)));
            }while (cursor.moveToNext());
        }if(arrayList.size() == 0){
            arrayList.add(0," ");
            return arrayList;
        }
        return arrayList;
    }

    public ArrayList<String> getContactByEvent(String event){

        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();
        String columns[] = {
                MyHelper.NAME,
                MyHelper.PHONE,
                MyHelper.EMAIL,
                MyHelper.POST,
                MyHelper.SUBEVENT,
        };

        String TABLE_NAME = MyHelper.TABLE_NAME_CONTACT;

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,columns,null,null,null,null, null);
        if(cursor.moveToFirst()){
            do{
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.NAME)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.PHONE)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.EMAIL)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.POST)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.SUBEVENT)));
//                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper)));
            }while (cursor.moveToNext());
        }if(arrayList.size() == 0){
            arrayList.add(0," ");
            return arrayList;
        }
        return arrayList;
    }


    public ArrayList<String> getContactBySubEvent(String subevent){

        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();
        String columns[] = {
                MyHelper.NAME,
                MyHelper.PHONE,
                MyHelper.EMAIL,
                MyHelper.POST,
                MyHelper.EVENT,
        };

        String TABLE_NAME = MyHelper.TABLE_NAME_CONTACT;

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,columns,null,null,null,null, null);
        if(cursor.moveToFirst()){
            do{
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.NAME)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.PHONE)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.EMAIL)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.POST)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.EVENT)));
//                arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper)));
            }while (cursor.moveToNext());
        }if(arrayList.size() == 0){
            arrayList.add(0," ");
            return arrayList;
        }
        return arrayList;
    }

    class MyHelper extends SQLiteOpenHelper{

        private static final String DATABASE_NAME="RegisteredData.db";

        /*table definitions for storing user information*/

        private static final String TABLE_NAME_REGISTERED="registered_table";
        private static final String TABLE_NAME_CONTACT="contact_table";
        private static final String TABLE_NAME_NOTIFICATION="notification_table";

        private static final int DATABASE_VERSION=1;

        private static final String TID="TID";
        private static final String ID="ID";
        private static final String NAME="name";
        private static final String PHONE="phone";
        private static final String EMAIL="email";
        private static final String TSIZE="tsize";
        private static final String ROLLNO="rollno";

        private static final String POST="post";
        private static final String EVENT="event";
        private static final String SUBEVENT="subevent";

        private static final String NID = "nid";
        private static final String NIDL = "nidl";
        private static final String NEVENTNAME = "neventname";
        private static final String NEVENTCONTENT = "neventcontent";
        private static final String NTIME = "ntime";

//        private static final String ="";
//
//       private static final String CREATE_TABLE_REGISTERED = " CREATE TABLE IF NOT EXISTS "+TABLE_NAME_REGISTERED+" ( "+
//                TID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
//                ID+" VARCHAR(255) NOT NULL, "+
//                NAME+" VARCHAR(255), "+
//                PHONE+" VARCHAR(255), "+
//                EMAIL+" VARCHAR(255), "+
//                TSIZE+" VARCHAR(255), "+
//                PHONE+" VARCHAR(255), "+
//                ROLLNO+" VARCHAR(255));";

        private static final String CREATE_TABLE_REGISTERED= "CREATE TABLE "+TABLE_NAME_REGISTERED+"("+
                TID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                ID+" VARCHAR(255) NOT NULL,"+
                NAME+" VARCHAR(255) NOT NULL,"+
                PHONE+" VARCHAR(255) NOT NULL,"+
                EMAIL+" VARCHAR(255),"+
                TSIZE+" VARCHAR(255),"+
                ROLLNO+" VARCHAR(255) NOT NULL);";

        private static final String CREATE_TABLE_CONTACT= "CREATE TABLE "+TABLE_NAME_CONTACT+"("+
                TID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                NAME+" VARCHAR(255) NOT NULL,"+
                PHONE+" VARCHAR(255) NOT NULL,"+
                EMAIL+" VARCHAR(255) NOT NULL,"+
                POST+" VARCHAR(255) NOT NULL,"+
                EVENT+" VARCHAR(255) NOT NULL,"+
                SUBEVENT+" VARCHAR(255) NULL);";

        private static final String CREATE_TABLE_NOTIFICATION= "CREATE TABLE "+TABLE_NAME_NOTIFICATION+"("+
                NIDL+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                NID+" VARCHAR(255) NOT NULL UNIQUE,"+
                NEVENTNAME+" VARCHAR(255) NOT NULL,"+
                NEVENTCONTENT+" VARCHAR(500),"+
//                EMAIL+" VARCHAR(255),"+
//                TSIZE+" VARCHAR(255),"+
                NTIME+" VARCHAR(255) NOT NULL);";

//                DEADLINE_ASSINGMENT+" VARCHAR(255));";


        private static final String DROP_TABLE_NAME_REGISTERED="DROP TABLE IF EXISTS " +TABLE_NAME_REGISTERED;
        private static final String DROP_TABLE_NAME_CONTACT="DROP TABLE IF EXISTS " +TABLE_NAME_CONTACT;
        private static final String DROP_TABLE_NAME_NOTIFICATION="DROP TABLE IF EXISTS " +TABLE_NAME_NOTIFICATION;


        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);

        }

        private Context context;

        public MyHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            if(db!=null){
                try {
                    Log.e("e","Table create");
                    db.execSQL(CREATE_TABLE_REGISTERED);
                    db.execSQL(CREATE_TABLE_CONTACT);
                    db.execSQL(CREATE_TABLE_NOTIFICATION);
                } catch(SQLException e){
//                    Message.message(context ,"" +e);
                    e.printStackTrace();
                }}
            Log.e("e","Table created");
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try{
//                Message.message(context,"onDowngrade() called");
                db.execSQL(DROP_TABLE_NAME_REGISTERED);
                db.execSQL(DROP_TABLE_NAME_CONTACT);
                db.execSQL(DROP_TABLE_NAME_NOTIFICATION);
                onCreate(db);
            } catch(SQLException e){
//                Message.message(context , "" +e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try{
//                Message.message(context,"onDowngrade() called");
                db.execSQL(DROP_TABLE_NAME_REGISTERED);
                db.execSQL(DROP_TABLE_NAME_CONTACT);
                db.execSQL(DROP_TABLE_NAME_NOTIFICATION);
                onCreate(db);
            } catch(SQLException e){
//                Message.message(context , "" +e);
            }
        }
    }
}