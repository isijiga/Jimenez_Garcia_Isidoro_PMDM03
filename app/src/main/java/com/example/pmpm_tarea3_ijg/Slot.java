package com.example.pmpm_tarea3_ijg;

public class Slot {


    private int slot;
    private TypePokemon type;

    public Slot(int slot, TypePokemon type) {
        this.slot = slot;
        this.type = type;
    }

    public int getSlot() {
        return slot;
    }

    public TypePokemon getType() {
        return type;
    }
}
