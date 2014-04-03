package br.com.sfchatter.jsonObjects;

import br.com.sfchatter.jsonObjects.segments.Segment;

import java.io.Serializable;

/**
 * Created by Tiago Oliveira on 3/29/14.
 */
public class FeedItem implements Serializable{
    private GroupDetails parent;
    private String id;
    private String type;
    private String url;
    private ClientInfo clientInfo;
    private String createdDate;
    private Segment body;
    private Boolean event;
    private String actor;
    private String modifiedDate;
    private String photoUrl;
    private CommentPage comments;
    private LikePage likes;
    private Boolean isLikedByCurrentUser;
    private Reference currentUserLike;
    private String attachment;

    public GroupDetails getParent() {
        return parent;
    }

    public void setParent(GroupDetails parent) {
        this.parent = parent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Segment getBody() {
        return body;
    }

    public void setBody(Segment body) {
        this.body = body;
    }

    public Boolean getEvent() {
        return event;
    }

    public void setEvent(Boolean event) {
        this.event = event;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public CommentPage getComments() {
        return comments;
    }

    public void setComments(CommentPage comments) {
        this.comments = comments;
    }

    public LikePage getLikes() {
        return likes;
    }

    public void setLikes(LikePage likes) {
        this.likes = likes;
    }

    public Boolean getIsLikedByCurrentUser() {
        return isLikedByCurrentUser;
    }

    public void setIsLikedByCurrentUser(Boolean isLikedByCurrentUser) {
        this.isLikedByCurrentUser = isLikedByCurrentUser;
    }

    public Reference getCurrentUserLike() {
        return currentUserLike;
    }

    public void setCurrentUserLike(Reference currentUserLike) {
        this.currentUserLike = currentUserLike;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
