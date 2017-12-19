
package com.diogenes.pokeapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Type {

    @SerializedName("slot")
    @Expose
    private Integer slot;
    @SerializedName("type")
    @Expose
    private GenericCommonEntity type;

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public GenericCommonEntity getType() {
        return type;
    }

    public void setType(GenericCommonEntity type) {
        this.type = type;
    }

}
