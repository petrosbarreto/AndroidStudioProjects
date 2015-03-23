package com.alive.petrosbarreto.crud;

import java.io.BufferedOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.security.PrivateKey;

/**
 * Created by petrosbarreto on 22/03/15.
 */
public class DaoVendedor {

    //Variaveis e objetos

    public static final String CATEGORIA = "DaoVendedor";

    private String scriptSQLCreate = "CREATE TABLE VENDEDOR (ID_VENDEDOR INTEGER PRIMARY KEY AUTOINCREMENT," +
            "NOME VACHAR(50), ATIVO CHAR(11), TIPO VACHAR(20));";

    private String getScriptSQLDelete = "DROP TABLE IF EXISTS VENDEDOR";
    private static  final String NOME_TABELA = "VENDEDOR";
    private SQLiteHelper dbHelper;
    private SQLiteDatabase db;
    private List<ModelVendedor> objListaVendedor;

    private String[] getColunasTabVendedor(){
        String [] VENDEDOR_COLUNAS_TAB_VENDEDOR = new String[] {"ÏD_VENDEDOR","NOME","ATIVO","TIPO"};
        return VENDEDOR_COLUNAS_TAB_VENDEDOR;
    }

    public  DaoVendedor(Context ctx){
        try {
            log.i(CATEGORIA, "Contrutor DaoVendedor");
            dbHelper = new SQLiteHelper(ctx, SQLiteHelper.NOME_BD , SQLiteHelper.VERSAO_BD.VERSAO_BD, scriptSQLCreate, scriptDelete);

            objListaVendedor = new ArrayList<ModelVendedor>();

            db = dbHelper.getWritableDatabase();


        } catch (Exception e){

            Log.e(CATEGORIA, e.toString());
        }
    }

public void close(){
    if (db != null){
        if (!db.isOpen() ){
            db.close();
        }
    }
}

    public List<ModelVendedor> ListaVendedores(){
        Cursor cursor = null
                objListaVendedor.clear();

        try {
            cursor = db.query(NOME_TABELA, getColunasTabVendedor(), null , null , null , null , "ID_VENDEDOR");
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()){
                    ModelVendedor vendedorLinha = new ModelVendedor();
                    vendedorLinha.setID_VENDEDOR(cursor.getInt(cursor.getColumnIndex("ID_VENDEDOR)));
                    vendedorLinha.setNOME(cursor.getString(cursor.getColumnIndex("Nome")));
                    vendedorLinha.setATIVO(cursor.getString(cursor.getColumnIndex("ATIVO")));
                    vendedorLinha.setTIPO(cursor.getString(cursor.getColumnIndex("TIPO")));
                    objListaVendedor.add(vendedorLinha);


                }
            }
        }catch (Exception e){
            log.e(CATEGORIA, e.toString());
        }

        finally {
            if (cursor != null){
                if(!cursor.isClosed()){
                    cursor.close();
                }
            }
        }
        return  objListaVendedor;

    }

    public ContentValues contentVendedor(ModelVendedor vendedor){
        ContentValues values = new ContentValues();

        values.put("NOME", vendedor.getNOME());
        values.put("ATIVO", vendedor.getATIVO());
        values.put("TIPO", vendedor.getTIPO());

        return values;
    }

    public  long insertVendedor(ModelVendedor novoVendedor){

        long id = 0;
try {
    ContentValues contentValuesVendedor = contentVendedor(novoVendedor);
    id = db.insert(NOME_TABELA, "", contentValuesVendedor);
}catch (Exception e){
    Log.e(CATEGORIA , e.toString());
}
        return id;
    }
public  boolean excluirVendedor(String ID_VENDEDOR){

    boolean resultadoExclusao = false;

    try {
        String where = "ID_VENDEDOR=?";
        String[] args = new String[] {ID_VENDEDOR
    };
        int num = db.delete(NOME_TABELA, where, args);

        if (num == 1){
            resultadoExclusao = true;

        }
}catch (Exception e){
        Log.e(CATEGORIA, e.toString());
    }
    return  resultadoExclusao;
}
}