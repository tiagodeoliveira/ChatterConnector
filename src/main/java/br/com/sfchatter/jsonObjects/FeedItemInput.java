package br.com.sfchatter.jsonObjects;

import br.com.sfchatter.jsonObjects.segments.Attachment;

public class FeedItemInput {
    private Attachment attachment;
    private MessageBodyInput body;
    private Boolean isBookmarked​ByCurrentUser;
    private String originalFeedItemId;
    private String visibility;

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public void setBody(MessageBodyInput body) {
        this.body = body;
    }

    public void setIsBookmarked​ByCurrentUser(Boolean isBookmarked​ByCurrentUser) {
        this.isBookmarked​ByCurrentUser = isBookmarked​ByCurrentUser;
    }

    public void setOriginalFeedItemId(String originalFeedItemId) {
        this.originalFeedItemId = originalFeedItemId;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getOriginalFeedItemId() {
        return this.originalFeedItemId;
    }

    public String getVisibility() {
        return this.visibility;
    }

    public Boolean getIsBookmarked​ByCurrentUser() {
        return this.isBookmarked​ByCurrentUser;
    }

    public MessageBodyInput getBody() {
        return this.body;
    }

    public Attachment getAttachment() {
        return this.attachment;
    }
}
