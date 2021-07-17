package com.example.newsop;

public class News {

    private String photo;
    private String title;
    private String cont;
    private String url;

    public News(String title,String photo,String url,String cont) {
        this.photo = photo;
        this.url = url;
        this.cont = cont;
        this.title = title;
    }
    public String getPhoto()  {
        return photo;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle()  {
        return title;
    }
    public String getCont()  {
        return cont;
    }
}
