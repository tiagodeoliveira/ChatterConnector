package br.com.sfchatter.jsonObjects;

import java.io.Serializable;

/**
 * Created by Tiago_de_Oliveira on 3/31/2014.
 */
public class LikePage implements Serializable {
    private String currentPageUrl;
    private Like[] likes;
    private Reference myLike;
    private String nextPageUrl;
    private String previousPageUrl;
    private Integer total;

    public String getCurrentPageUrl() {
        return currentPageUrl;
    }

    public void setCurrentPageUrl(String currentPageUrl) {
        this.currentPageUrl = currentPageUrl;
    }

    public Like[] getLikes() {
        return likes;
    }

    public void setLikes(Like[] likes) {
        this.likes = likes;
    }

    public Reference getMyLike() {
        return myLike;
    }

    public void setMyLike(Reference myLike) {
        this.myLike = myLike;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getPreviousPageUrl() {
        return previousPageUrl;
    }

    public void setPreviousPageUrl(String previousPageUrl) {
        this.previousPageUrl = previousPageUrl;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
