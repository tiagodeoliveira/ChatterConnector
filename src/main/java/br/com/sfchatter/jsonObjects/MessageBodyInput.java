package br.com.sfchatter.jsonObjects;

import br.com.sfchatter.jsonObjects.segments.Segment;

/**
 * Created by Tiago_de_Oliveira on 4/1/2014.
 */
public class MessageBodyInput {
    private Segment[] messageSegments;

    public Segment[] getMessageSegments() {
        return messageSegments;
    }

    public void setMessageSegments(Segment[] messageSegments) {
        this.messageSegments = messageSegments;
    }
}
