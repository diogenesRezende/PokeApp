
package com.diogenes.pokeapp.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemList {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("previous")
    @Expose
    private Object previous;
    @SerializedName("results")
    @Expose
    private List<GenericCommonEntity> results = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public List<GenericCommonEntity> getResults() {
        return results;
    }

    public void setResults(List<GenericCommonEntity> results) {
        this.results = results;
    }

}
