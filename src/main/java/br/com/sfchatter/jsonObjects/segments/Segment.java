package br.com.sfchatter.jsonObjects.segments;

import java.io.Serializable;

/**
 * Created by Tiago_de_Oliveira on 3/31/2014.
 */
public class Segment implements Serializable {
    private String text;
    private String type;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
