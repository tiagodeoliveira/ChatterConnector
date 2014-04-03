package br.com.sfchatter.jsonObjects;

import java.io.Serializable;

/**
 * Created by Tiago_de_Oliveira on 3/31/2014.
 */
public class Like implements Serializable {
    private String id;
    private Reference likedItem;
    private String url;
    private UserSummary user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Reference getLikedItem() {
        return likedItem;
    }

    public void setLikedItem(Reference likedItem) {
        this.likedItem = likedItem;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UserSummary getUser() {
        return user;
    }

    public void setUser(UserSummary user) {
        this.user = user;
    }
}
