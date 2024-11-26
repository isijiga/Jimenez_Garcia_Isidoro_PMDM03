package com.example.pmpm_tarea3_ijg;

import java.util.ArrayList;

public class JsonRespuestaDetalle {


    private int weight;
    private int height;
    private String name;
    private Sprite sprites;
    private int order;
    private Slot[] types= new Slot[2];


    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    public Sprite getSprites() {
        return sprites;
    }

    public int getWeight() {
        return weight;
    }

    public Slot getTypes() {

        return types[0];
    }

}
