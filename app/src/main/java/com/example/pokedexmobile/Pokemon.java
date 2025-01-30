package com.example.pokedexmobile;

class Pokemon {
    private int number;
    private String name;
    private String url;
    public String getName() { return name; }
    public String getUrl() { return url; }
    public int getNumber() {
        String[] partesURL = url.split("/");
        return Integer.parseInt(partesURL[partesURL.length - 1]);
    }
}