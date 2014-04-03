package br.com.sfchatter;

import com.google.gson.Gson;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Connector {

    private HttpClient httpClientConnector;
    private CredentialsProvider credentialsProvider;
    private RequestConfig requestConfig;
    private String authorization;
    private ConnectionAttributes connAttr;

    public Connector() throws ConfigurationException {
        this.connAttr = new ConnectionAttributes();
        this.connAttr.loadAttributes();
    }

    public String executeGETMethod(String relativePath) {
        String response = null;
        HttpGet httpGet = new HttpGet(this.connAttr.getBaseURL() + this.connAttr.getApiPath() + relativePath);
        try {
            httpGet.addHeader("Authorization", "OAuth " + this.getAuthorization());
            httpGet.setConfig(this.getRequestConfig());
            response = this.getResponse(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String executePOSTMethod(String relativePath, String parameters) {
        String response = "";
        HttpPost httpPost = new HttpPost(this.connAttr.getBaseURL() + this.connAttr.getApiPath() + relativePath);
        try {
            httpPost.addHeader("Authorization", "OAuth " + this.getAuthorization());
            httpPost.addHeader(new BasicHeader("Content-Type", "application/json"));
            httpPost.setEntity(new StringEntity(parameters));
            httpPost.setConfig(this.getRequestConfig());
            response = this.getResponse(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String executeLoginMethod() {
        String response = "";
        HttpPost httpPost = new HttpPost(this.connAttr.getBaseURL() + Constants.loginURL);
        List<BasicNameValuePair> parametersBody = new ArrayList<BasicNameValuePair>();
        parametersBody.add(new BasicNameValuePair("grant_type", "password"));
        parametersBody.add(new BasicNameValuePair("username", this.connAttr.getUsername()));
        parametersBody.add(new BasicNameValuePair("password", this.connAttr.getPassword()));
        parametersBody.add(new BasicNameValuePair("client_id", this.connAttr.getClientID()));
        parametersBody.add(new BasicNameValuePair("client_secret", this.connAttr.getClientSecret()));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(parametersBody));
            httpPost.setConfig(this.getRequestConfig());

            response = this.getResponse(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String getResponse(HttpUriRequest method) throws IOException {
        HttpResponse response = this.getHTTPConnector().execute(method);
        return EntityUtils.toString(response.getEntity());
    }

    private HttpClient getHTTPConnector() {
        if (this.httpClientConnector == null) {
            this.httpClientConnector = HttpClients.custom().setDefaultCredentialsProvider(this.getCredentialsProvider()).build();
        }
        return this.httpClientConnector;
    }

    private CredentialsProvider getCredentialsProvider() {
        if ((this.connAttr.getUseProxy()) && (this.credentialsProvider == null)) {
            this.credentialsProvider = new BasicCredentialsProvider();
            AuthScope authScope = new AuthScope(this.connAttr.getProxyURL(), this.connAttr.getProxyPort());
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(this.connAttr.getProxyUser(), this.connAttr.getProxyPass());
            this.credentialsProvider.setCredentials(authScope, credentials);
        }

        return this.credentialsProvider;
    }

    private RequestConfig getRequestConfig() {
        if (this.connAttr.getUseProxy() && this.requestConfig == null) {
            HttpHost proxy = new HttpHost(this.connAttr.getProxyURL(), this.connAttr.getProxyPort());
            this.requestConfig = RequestConfig.custom().setProxy(proxy).build();
        }
        return this.requestConfig;
    }

    private String getAuthorization() {
        if (this.authorization == null) {
            String result = this.executeLoginMethod();
            Map<String, String> dataMapping = new Gson().fromJson(result, Map.class);
            this.authorization = dataMapping.get("access_token");
        }
        return this.authorization;
    }
}
