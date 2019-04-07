package com.example.zed.enums;

import com.example.zed.interfaces.Painting;

public enum Color implements Painting {
    RED("红色","red"),BLUE("蓝色","blue");

    private String name;
    private String enName;

    Color(String name, String enName) {
        this.name = name;
        this.enName = enName;
    }

    @Override
    public void draw() {
        System.out.println("color:"+this.enName);
    }

    @Override
    public void print() {
        System.out.println("颜色："+this.name);
    }
}
