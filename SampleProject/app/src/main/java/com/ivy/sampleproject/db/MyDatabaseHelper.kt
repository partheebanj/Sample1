package com.ivy.sampleproject.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.ivy.sampleproject.bo.Post

class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "my_database.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "Posts"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "Title"
        const val COLUMN_DATE = "Date"
        const val COLUMN_PATH = "Path"
        const val COLUMN_CAPTION = "Caption"
        const val COLUMN_FAVORITE = "isFavorite"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID TEXT, $COLUMN_TITLE TEXT, $COLUMN_DATE TEXT, $COLUMN_PATH TEXT, $COLUMN_CAPTION TEXT, $COLUMN_FAVORITE TEXT)";
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertData(post: Post) {
        val database = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_ID, post.id)
        contentValues.put(COLUMN_TITLE, post.title)
        contentValues.put(COLUMN_DATE, post.dateStr)
        contentValues.put(COLUMN_PATH, post.imagePath)
        contentValues.put(COLUMN_CAPTION, post.caption)
        val isFavorite = if (post.isFavorite == "1") "1" else "0"
        contentValues.put(COLUMN_FAVORITE,isFavorite)
        database.insert(TABLE_NAME, null, contentValues)
        database.close()
    }

    fun readAllData(): ArrayList<Post> {
        val dataList = ArrayList<Post>()
        val database = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(cursor.getColumnIndex("id"))
                val post = Post(id)
                post.title = cursor.getString(cursor.getColumnIndex("Title"))
                post.dateStr = cursor.getString(cursor.getColumnIndex("Date"))
                post.imagePath = cursor.getString(cursor.getColumnIndex("Path"))
                post.caption = cursor.getString(cursor.getColumnIndex("Caption"))
                post.isFavorite = cursor.getString(cursor.getColumnIndex("isFavorite"))
                dataList.add(post)
            } while (cursor.moveToNext())
        }
        cursor.close()
        database.close()
        return dataList
    }

    fun readAllFavorites(): ArrayList<Post> {
        val dataList = ArrayList<Post>()
        val database = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE isFavorite = 1"
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(cursor.getColumnIndex("id"))
                val post = Post(id)
                post.title = cursor.getString(cursor.getColumnIndex("Title"))
                post.dateStr = cursor.getString(cursor.getColumnIndex("Date"))
                post.imagePath = cursor.getString(cursor.getColumnIndex("Path"))
                post.caption = cursor.getString(cursor.getColumnIndex("Caption"))
                post.isFavorite = cursor.getString(cursor.getColumnIndex("isFavorite"))
                dataList.add(post)
            } while (cursor.moveToNext())
        }
        cursor.close()
        database.close()
        return dataList
    }
}
