package com.example.arfishing;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBDAO {

    MyDB myDB;
    SQLiteDatabase db;

    DBDAO(Context context ) {
        if(myDB == null) {
            myDB = new MyDB(context);
        }
    }

    void firstMemberDB(String nickName) {
        db = myDB.getWritableDatabase();
        db.execSQL("update member set " +
                "nickname= '"+ nickName + "' " +
                " where id = 1");
    }


    InformationDTO selectMemberDB() {
        db = myDB.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from member",null);
        InformationDTO informationDTO = new InformationDTO("ㅇㅇ",1,1,0);
        while(cursor.moveToNext()) {
            informationDTO = new InformationDTO(
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getInt(4)
            );
        }

        db.close();

        return informationDTO;
    }

    void updateMemberDB(String which,String nickName, int catchFish, int hasFish, int money) {
        db = myDB.getWritableDatabase();
        switch (which) {
            case "All":
                db.execSQL("update member set " +
                        "nickname= '" + nickName + "' " +
                        "catchfish=" + catchFish +
                        "hasFish=" + hasFish +
                        " where id = 1"
                );
                break;
            case "nickName":
                db.execSQL("update member set " +
                        "nickname= '"+ nickName + "' " +
                        " where id = 1"
                );
                break;
            case "catchFish":
                db.execSQL("update member set " +
                        "catchfish=" + catchFish +
                        " where id = 1"
                );
                break;
            case "hasFish":
                db.execSQL("update member set " +
                        "hasfish=" + hasFish +
                        " where id = 1"
                );
                break;
            case "money":
                db.execSQL("update member set " +
                        "money=" + money +
                        " where id = 1"
                );
                break;
        }

        db.close();
    }

    ArrayList<InventoryDTO> selectInventory(String name) {
        ArrayList<InventoryDTO> inventoryDTO_arr = new ArrayList<>();
        db = myDB.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from inventory_"+name,null);
        InventoryDTO inventoryDTO = new InventoryDTO(0,"1","1",0);
        while(cursor.moveToNext()) {
            inventoryDTO = new InventoryDTO(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3)
            );
            inventoryDTO_arr.add(inventoryDTO);
        }

        db.close();

        return inventoryDTO_arr;


    }

    ArrayList<QuestDTO> selectQuest() {
        ArrayList<QuestDTO> questDTO_arr = new ArrayList<>();
        db = myDB.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from quest",null);
        QuestDTO questDTO = new QuestDTO("",0,0,0);
        while(cursor.moveToNext()) {
            questDTO = new QuestDTO(
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getInt(4)
            );
            questDTO_arr.add(questDTO);
        }

        db.close();

        return questDTO_arr;
    }

    FishDTO getFishInfo(String name){
        FishDTO fishDTO = new FishDTO();

        db = myDB.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from fish where fish_name = '"+name+"'", null);
        while(cursor.moveToNext()){
            fishDTO.fish_id = cursor.getInt(0);
            fishDTO.fish_name = cursor.getString(1);
            fishDTO.fish_area = cursor.getString(3);
            fishDTO.fish_scale = cursor.getString(4);
            fishDTO.fish_rotation = cursor.getString(5);
        }
        db.close();

        return fishDTO;
    }

    void plusFishInventory(String name){
        db = myDB.getWritableDatabase();
        
    }

    class MyDB extends SQLiteOpenHelper {

        public MyDB(@Nullable Context context) {
            super(context, "fishingdb", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
