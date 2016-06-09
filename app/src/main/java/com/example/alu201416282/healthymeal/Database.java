package com.example.alu201416282.healthymeal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by I853092 on 09/06/2016.
 */
public class Database {

    static SQLiteDatabase myDatabase;

    public static boolean createDatabase(){

        myDatabase = SQLiteDatabase.openOrCreateDatabase("HealthyMeal",null,null);

        if(myDatabase!= null){
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS Restaurante(idRestaurante SERIAL, nome VARCHAR, latitude VARCHAR, longitude VARCHAR, telefone VARCHAR, endereco VARCHAR, site VARCHAR, lactose BOOLEAN, gluten BOOLEAN, vegano BOOLEAN, vegetariano BOOLEAN);");
            return true;
        }
        return false;
    }

    public static void addRestaurantes(ArrayList<Restaurante> restaurantes){
        for(int i=0;i<restaurantes.size();i++){
            myDatabase.execSQL("INSERT INTO Restaurante VALUES ('"+restaurantes.get(i).nome+"','"+restaurantes.get(i).latitude+"','"+restaurantes.get(i).longitude+"','"+restaurantes.get(i).telefone+"','"+restaurantes.get(i).endereco+"','"+restaurantes.get(i).site+"','"+restaurantes.get(i).lactose+"','"+restaurantes.get(i).gluten+"','"+restaurantes.get(i).vegano+"','"+restaurantes.get(i).vegetariano+"');");
        }
    }

    public static ArrayList<Restaurante> getRestaurantes(){
        Cursor resultSet = myDatabase.rawQuery("SELECT * FROM Restaurante",null);
        resultSet.moveToFirst();

        ArrayList<Restaurante> restaurantes = new ArrayList<>();

        while(!resultSet.isAfterLast()){
            boolean lactose;
            boolean gluten;
            boolean vegano;
            boolean vegetariano;
            if(resultSet.getString(7).toLowerCase().equals("true"))
                lactose=true;
            else
                lactose=false;

            if(resultSet.getString(8).toLowerCase().equals("true"))
                gluten=true;
            else
                gluten=false;

            if(resultSet.getString(9).toLowerCase().equals("true"))
                vegano=true;
            else
                vegano=false;

            if(resultSet.getString(10).toLowerCase().equals("true"))
                vegetariano=true;
            else
                vegetariano=false;

            restaurantes.add(new Restaurante(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),lactose,gluten,vegano,vegetariano));
        }

        return restaurantes;
    }

}
