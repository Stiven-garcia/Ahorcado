package com.example.Logica;

import java.util.Random;

public class Ahorcado {
    private String juego [][] = {   {"Animales Terrestres", "perro", "gato", "cebra", "leon", "elefante"},
                                    {"Animales Acuaticos", "pez", "foca", "pulpo", "ballena", "medusa"},
                                    {"Animales: Aves", "paloma", "pato", "cisne", "loro", "colibri"}
                                };
    private Random aleatorio;
    private int item;
    private int tipo;
    private String palabra;
    private int res;
    public Ahorcado() {
        this.aleatorio = new Random();
        this.tipo = this.aleatorio.nextInt(3);
        this.item = this.aleatorio. nextInt(6)+1;
        this.palabra = this.juego [tipo][item];
        this.res = this.palabra.length();
    }
    public String getTipo (){
        return this.juego [tipo][0];
    }
    public int res(){
        return this.res;
    }
    public int getPalabra (String letra, int n){
        for(int i=n; i<this.palabra.length(); i++){
            if(this.palabra.charAt(i)== letra.charAt(0)){
                this.res-=1;
                return i+1;

            }
        }
        return 0;
    }
    public String getItem (){
        return this.juego [tipo][item];
    }
}
