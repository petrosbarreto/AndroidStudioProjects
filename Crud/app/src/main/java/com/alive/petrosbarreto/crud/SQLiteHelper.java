package com.alive.petrosbarreto.crud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by petrosbarreto on 22/03/15.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    //Objetos e variaveis
    public static final NOME_BD = "APP_DB_DEV_VENDAS";
    public static final int VERSAO_BD = 1;
    public static final String CATEGORIA = "SQLiteHelper";
    private String scriptSQLCreate;
    private String scriptSQLDelete;

    public SQLiteHelper(Context context, String nome_bd, int versao_bd, String scriptCreate, String scriptDelete){
        super(context, nome_bd, null, versao_bd);

        this.scriptSQLCreate = scriptCreate;
        this.scriptSQLDelete = scriptDelete;
    }

    @Override
    public void onCreate (SQLiteDatabase db) {

        db.execSQL(scriptSQLCreate);



    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int nweVersion) {


        db.execSQL(scriptSQLDelete);

        onCreate(db);
    }


}
