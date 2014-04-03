package br.com.sfchatter.jsonObjects;

import java.io.Serializable;

/**
 * Created by Tiago_de_Oliveira on 3/31/2014.
 */
public class ClientInfo implements Serializable{
    private String applicationName;
    private String applicationUrl;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }
}
