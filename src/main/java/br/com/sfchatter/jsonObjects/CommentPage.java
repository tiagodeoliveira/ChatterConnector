package br.com.sfchatter.jsonObjects;

import java.io.Serializable;

/**
 * Created by Tiago_de_Oliveira on 3/31/2014.
 */
public class CommentPage implements Serializable {
    private String currentPageUrl;
    private String nextPageUrl;
    private Integer total;
    private String comments;

    public String getCurrentPageUrl() {
        return currentPageUrl;
    }

    public void setCurrentPageUrl(String currentPageUrl) {
        this.currentPageUrl = currentPageUrl;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
