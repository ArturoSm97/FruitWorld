package com.example.fruitworld.Models;

public class Fruit {
    private String name;
    private String Origin;
    private int icon;

    public Fruit(String name, String origin, int icon){
        setName(name);
        setOrigin(origin);
        setIcon(icon);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
