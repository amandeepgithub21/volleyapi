package com.example.volleyapi;

public class ExampleItem {
    private  String mimageurl;
    private  String mcreator;
    private  String mlikes;
    public ExampleItem(String imageurl,String creator,  String likes){
mimageurl=imageurl;
        mcreator=creator;
        mlikes=likes;
    }

    public String getImageurl() {
        return mimageurl;
    }

    public String getcreator() {
        return mcreator;
    }

    public String getlikes() {
        return mlikes;
    }
}
