package com.example.pokedexmobile;

import java.util.List;

class Pokemon {
    private int number;
    private String name;
    private String url;
    private List<Tipo> type;
    private List<Habilidad> abilities;
    private List<Tipo> stats;
    public String getName() { return name; }
    public String getUrl() { return url; }
    public int getNumber() {
        String[] partesURL = url.split("/");
        return Integer.parseInt(partesURL[partesURL.length - 1]);
    }

    public static class Tipo {
        public TipoInfo type;
    }
    public static class TipoInfo {
        public String name;
    }

    public static class Habilidad {
        public HabilidadInfo ability;
        public boolean is_hidden;
    }

    public static class HabilidadInfo {
        public String name;
    }

    public static class Estadistica {
        public StatInfo stat;
        public int base_stat;
    }

    public static class StatInfo {
        public String name;
    }
}