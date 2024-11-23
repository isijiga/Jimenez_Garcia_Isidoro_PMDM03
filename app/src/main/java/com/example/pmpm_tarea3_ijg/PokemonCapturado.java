package com.example.pmpm_tarea3_ijg;

public class PokemonCapturado {

    private int weight;
    private int height;
    private String name;
    private Sprite sprites;
    private int order;

    public PokemonCapturado(int height, String name, int order, Sprite sprites, int weight) {
        this.height = height;
        this.name = name;
        this.order = order;
        this.sprites = sprites;
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
}
