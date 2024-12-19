package com.example.pmpm_tarea3_ijg.MapeoClases;

public class PokemonCapturado {

    private int weight;
    private int height;
    private String name;
    private Sprite sprites;
    private int order;
    private Slot[] types = new Slot[2];
    private String uid;


    public PokemonCapturado(int height, String name, int order, Sprite sprites, int weight, Slot types, String uid) {
        this.height = height;
        this.name = name;
        this.order = order;
        this.sprites = sprites;
        this.types[0] = types;
        this.weight = weight;
        this.uid = uid;

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

    public String getUid() {
        return uid;
    }
}
