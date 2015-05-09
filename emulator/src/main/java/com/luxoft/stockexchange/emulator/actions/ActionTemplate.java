package com.luxoft.stockexchange.emulator.actions;

import com.luxoft.stockexchange.emulator.actions.template.FixActivityRow;

import java.util.List;

/**
 * Created by victorvorontsov on 09.05.15.
 */
public class ActionTemplate {

    private String fixVersion;
    private List<FixActivityRow> activityRowList;

    public List<FixActivityRow> getActivityRowList() {
        return activityRowList;
    }

    public void setActivityRowList(List<FixActivityRow> activityRowList) {
        this.activityRowList = activityRowList;
    }

    public String getFixVersion() {
        return fixVersion;
    }

    public void setFixVersion(String fixVersion) {
        this.fixVersion = fixVersion;
    }

    // todo : add business logic here
}
