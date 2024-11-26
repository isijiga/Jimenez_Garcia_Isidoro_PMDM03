package com.example.pmpm_tarea3_ijg;

public class PokemonCapturado {

    private int weight;
    private int height;
    private String name;
    private Sprite sprites;
    private int order;
    private Slot[] types = new Slot[2];


    public PokemonCapturado(int height, String name, int order, Sprite sprites,int weight,Slot types) {
        this.height = height;
        this.name = name;
        this.order = order;
        this.sprites = sprites;
        this.types[0] = types;
        this.weight = weight;
    }

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
