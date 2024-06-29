package com.ksprogramming.equipment.auth;

import java.util.Arrays;
import java.util.List;

class Menu {
    private String name;
    private String url;
    private List<Menu> children;


    public Menu(String name, String url, List<Menu> children) {
        this.name = name;
        this.url = url;
        this.children = children;
    }

    boolean hasChildren() {
        return !getChildren().isEmpty();
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public List<Menu> getChildren() {
        return children;
    }


    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", children=" + children +
                '}';
    }
}
