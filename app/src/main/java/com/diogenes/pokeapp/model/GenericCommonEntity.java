
package com.diogenes.pokeapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GenericCommonEntity implements Serializable {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("name")
    @Expose
    private String name;

    public GenericCommonEntity(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public GenericCommonEntity() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return url + " = " + name;
    }
}
