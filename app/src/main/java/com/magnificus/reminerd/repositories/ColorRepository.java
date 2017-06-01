package com.magnificus.reminerd.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Color;
import android.support.annotation.NonNull;

import com.magnificus.reminerd.Entities.ColorEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allysson on 31/05/17.
 */

public class ColorRepository extends SQLiteOpenHelper {

    public ColorRepository(Context context) {
        super(context, "Colors", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Colors (" +
                "ID INTEGER PRIMARY KEY," +
                "Name TEXT NOT NULL, " +
                "Hexadecimal TEXT NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*String sql = "";
        switch (oldVersion) {
            case 1:
                sql = "QUERY AQUI";
                db.execSQL(sql);
        }*/
    }

    public List<ColorEntity> getColors() {
        String sql = "SELECT * FROM Colors";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<ColorEntity> colors = populateColors(c);

        return colors;
    }

    public ColorEntity getColor(Long id) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM Colors WHERE ID = ?";
        Cursor c = db.rawQuery(sql, new String[]{id.toString()});
        ColorEntity colorEntity = new ColorEntity();
        if (c != null) {
            if(c.moveToFirst()) {
                colorEntity.setID(c.getLong(c.getColumnIndex("ID")));
                colorEntity.setName(c.getString(c.getColumnIndex("Name")));
                colorEntity.setHexadecimal(c.getString(c.getColumnIndex("Hexadecimal")));

                return colorEntity;
            }
        }
        return null;
    }

    @NonNull
    public List<ColorEntity> populateColors(Cursor c) {
        List<ColorEntity> colors = new ArrayList<ColorEntity>();

        while (c.moveToNext()) {
            ColorEntity color = new ColorEntity();
            color.setID(c.getLong(c.getColumnIndex("ID")));
            color.setName(c.getString(c.getColumnIndex("Name")));
            color.setHexadecimal(c.getString(c.getColumnIndex("Hexadecimal")));

            colors.add(color);
        }

        return colors;
    }

    public void insert(ColorEntity colorEntity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = buildColorObject(colorEntity);
        db.insert("Colors", null, data);
    }

    public void update(ColorEntity colorEntity) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues data = buildColorObject(colorEntity);

        String[] params = {colorEntity.getID().toString()};
        db.update("Colors", data, "ID = ?", params);
    }

    public void delete(ColorEntity colorEntity) {
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {colorEntity.getID().toString()};
        db.delete("Colors", "id = ?", params);
    }

    @NonNull
    private ContentValues buildColorObject(ColorEntity colorEntity) {
        ContentValues data = new ContentValues();
        data.put("ID", colorEntity.getID());
        data.put("Name", colorEntity.getName());
        data.put("Hexadecimal", colorEntity.getHexadecimal());

        return data;
    }
}
