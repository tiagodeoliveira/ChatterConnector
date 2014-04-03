package br.com.sfchatter;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created by Tiago Oliveira on 3/28/14.
 */
public class ConnectionAttributes {
    private final String propertyFile = "connector.properties";
    private String username;
    private String password;
    private String clientID;
    private String clientSecret;
    private String baseURL;
    private String proxyURL;
    private String proxyUser;
    private String proxyPass;
    private Integer proxyPort;
    private Boolean useProxy;
    private String apiPath;

    public boolean loadAttributes() throws ConfigurationException {
        boolean result = false;
        PropertiesConfiguration conf;
        conf = new PropertiesConfiguration(this.propertyFile);
        this.username = conf.getString("username");
        this.password = conf.getString("password");
        this.clientID = conf.getString("clientID");
        this.clientSecret = conf.getString("clientSecret");
        this.baseURL = conf.getString("baseURL");
        this.proxyURL = conf.getString("proxyURL");
        this.proxyUser = conf.getString("proxyUser");
        this.proxyPass = conf.getString("proxyPass");
        this.proxyPort = safeStrToInt(conf.getString("proxyPort"));
        this.useProxy = conf.getBoolean("useProxy");
        this.apiPath = conf.getString("apiPath");
        this.username += Constants.domain;

        return result;
    }

    private Integer safeStrToInt(String str){
        Integer result = 0;
        try{
            result = Integer.parseInt(str);
        }catch(Exception e){
            result = 0;
        }

        return result;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public String getProxyURL() {
        return proxyURL;
    }

    public void setProxyURL(String proxyURL) {
        this.proxyURL = proxyURL;
    }

    public String getProxyUser() {
        return proxyUser;
    }

    public void setProxyUser(String proxyUser) {
        this.proxyUser = proxyUser;
    }

    public String getProxyPass() {
        return proxyPass;
    }

    public void setProxyPass(String proxyPass) {
        this.proxyPass = proxyPass;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setUseProxy(Boolean useProxy){
        this.useProxy = useProxy;
    }

    public Boolean getUseProxy(){
        return this.useProxy;
    }

    public String getApiPath(){
        return this.apiPath;
    }

    public void setApiPath(String apiPath){
        this.apiPath = apiPath;
    }
}
