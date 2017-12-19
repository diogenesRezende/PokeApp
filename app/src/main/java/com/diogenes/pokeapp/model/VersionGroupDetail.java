
package com.diogenes.pokeapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VersionGroupDetail {

    @SerializedName("level_learned_at")
    @Expose
    private Integer levelLearnedAt;
    @SerializedName("version_group")
    @Expose
    private GenericCommonEntity versionGroup;
    @SerializedName("move_learn_method")
    @Expose
    private GenericCommonEntity moveLearnMethod;

    public Integer getLevelLearnedAt() {
        return levelLearnedAt;
    }

    public void setLevelLearnedAt(Integer levelLearnedAt) {
        this.levelLearnedAt = levelLearnedAt;
    }

    public GenericCommonEntity getVersionGroup() {
        return versionGroup;
    }

    public void setVersionGroup(GenericCommonEntity versionGroup) {
        this.versionGroup = versionGroup;
    }

    public GenericCommonEntity getMoveLearnMethod() {
        return moveLearnMethod;
    }

    public void setMoveLearnMethod(GenericCommonEntity moveLearnMethod) {
        this.moveLearnMethod = moveLearnMethod;
    }

}
