package br.com.sfchatter.jsonObjects;

import java.io.Serializable;

/**
 * Created by Tiago Oliveira on 3/29/14.
 */
public class GroupDetails implements Serializable {
    public String name;
    private String description;
    private String memberCount;
    private Photo photo;
    private Boolean canHaveChatterGuests;
    private String myRole;
    private Reference mySubscription;
    private String id;
    private String url;
    private String type;
    private String visibility;
    private String recordViewUrl;
    private String pendingRequests;
    private String lastFeed​ItemPostDate;

    public String getLastFeed​ItemPostDate() {
        return this.lastFeed​ItemPostDate;
    }

    public void setLastFeed​ItemPostDate(String lastFeed​ItemPostDate) {
        this.lastFeed​ItemPostDate = lastFeed​ItemPostDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(String memberCount) {
        this.memberCount = memberCount;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Boolean getCanHaveChatterGuests() {
        return canHaveChatterGuests;
    }

    public void setCanHaveChatterGuests(Boolean canHaveChatterGuests) {
        this.canHaveChatterGuests = canHaveChatterGuests;
    }

    public String getMyRole() {
        return myRole;
    }

    public void setMyRole(String myRole) {
        this.myRole = myRole;
    }

    public Reference getMySubscription() {
        return mySubscription;
    }

    public void setMySubscription(Reference mySubscription) {
        this.mySubscription = mySubscription;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getRecordViewUrl() {
        return recordViewUrl;
    }

    public void setRecordViewUrl(String recordViewUrl) {
        this.recordViewUrl = recordViewUrl;
    }

    public String getPendingRequests() {
        return pendingRequests;
    }

    public void setPendingRequests(String pendingRequests) {
        this.pendingRequests = pendingRequests;
    }

}
