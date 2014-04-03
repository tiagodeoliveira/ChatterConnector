package br.com.sfchatter.jsonObjects;

import java.io.Serializable;

/**
 * Created by Tiago Oliveira on 3/29/14.
 */
public class Reference implements Serializable {
    private String id;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
