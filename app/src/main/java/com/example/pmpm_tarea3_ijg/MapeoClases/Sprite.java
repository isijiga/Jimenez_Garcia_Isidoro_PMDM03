package com.example.pmpm_tarea3_ijg.MapeoClases;

public class Sprite {

    String back_default;
    String back_female;
    String back_shiny;
    String back_shiny_female;
    String front_default;
    String front_female;
    String front_shiny;
    String front_shiny_female;
    Object other;
    Object versions;

    public Sprite(String front_default) {

        this.front_default = front_default;

    }

    public String getBack_default() {
        return back_default;
    }

    public String getBack_female() {
        return back_female;
    }

    public String getBack_shiny() {
        return back_shiny;
    }

    public String getBack_shiny_female() {
        return back_shiny_female;
    }

    public String getFront_default() {
        return front_default;
    }

    public String getFront_female() {
        return front_female;
    }

    public String getFront_shiny() {
        return front_shiny;
    }

    public String getFront_shiny_female() {
        return front_shiny_female;
    }


}
