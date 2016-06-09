package com.example.alu201416282.healthymeal;


/**
 * Created by I853092 on 09/06/2016.
 */
public class Restaurante {
    public String nome;
    public String latitude;
    public String longitude;
    public String telefone;
    public String endereco;
    public String site;
    public boolean lactose;
    public boolean gluten;
    public boolean vegano;
    public boolean vegetariano;

    public Restaurante() {

    }

    public Restaurante(String nome, String latitude, String longitude, String telefone, String endereco, String site,
                       boolean lactose, boolean gluten, boolean vegano, boolean vegetariano) {
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
        this.telefone = telefone;
        this.endereco = endereco;
        this.site = site;
        this.lactose = lactose;
        this.gluten = gluten;
        this.vegano = vegano;
        this.vegetariano = vegetariano;
    }

}
