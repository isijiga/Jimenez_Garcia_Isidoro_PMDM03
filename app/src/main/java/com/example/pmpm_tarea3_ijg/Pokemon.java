package com.example.pmpm_tarea3_ijg;

import java.util.List;
import java.util.Objects;

public class Pokemon {



    private String name;
    private String url;

    public Pokemon(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}

