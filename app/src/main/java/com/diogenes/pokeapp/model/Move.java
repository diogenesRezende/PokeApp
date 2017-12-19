
package com.diogenes.pokeapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Move {

    @SerializedName("move")
    @Expose
    private GenericCommonEntity move;
    @SerializedName("version_group_details")
    @Expose
    private List<VersionGroupDetail> versionGroupDetails = null;

    public GenericCommonEntity getMove() {
        return move;
    }

    public void setMove(GenericCommonEntity move) {
        this.move = move;
    }

    public List<VersionGroupDetail> getVersionGroupDetails() {
        return versionGroupDetails;
    }

    public void setVersionGroupDetails(List<VersionGroupDetail> versionGroupDetails) {
        this.versionGroupDetails = versionGroupDetails;
    }

}
