package com.example.pmpm_tarea3_ijg;

import java.util.List;

public class JsonRespuesta {

    int contador;
    String next;
    String previous;
    Pokemon[] results;

    public int getContador() {
        return contador;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public Pokemon[] getResults() {
        return results;
    }
}
