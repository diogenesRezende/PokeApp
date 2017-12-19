
package com.diogenes.pokeapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ability {

    @SerializedName("is_hidden")
    @Expose
    private Boolean isHidden;
    @SerializedName("slot")
    @Expose
    private Integer slot;
    @SerializedName("ability")
    @Expose
    private GenericCommonEntity ability;

    public Boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public GenericCommonEntity getAbility() {
        return ability;
    }

    public void setAbility(GenericCommonEntity ability) {
        this.ability = ability;
    }

}
