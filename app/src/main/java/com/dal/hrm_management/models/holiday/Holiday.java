package com.dal.hrm_management.models.holiday;

/**
 * Created by Luu Ngoc Lan on 10-Aug-18.
 */

public class Holiday {
    private String nameHoliday;
    private String typeHoliday;
    private String start;
    private String end;
    private String note;

    public Holiday(String nameHoliday, String typeHoliday, String start, String end, String note) {
        this.nameHoliday = nameHoliday;
        this.typeHoliday = typeHoliday;
        this.start = start;
        this.end = end;
        this.note = note;
    }

    public String getNameHoliday() {
        return nameHoliday;
    }

    public void setNameHoliday(String nameHoliday) {
        this.nameHoliday = nameHoliday;
    }

    public String getTypeHoliday() {
        return typeHoliday;
    }

    public void setTypeHoliday(String typeHoliday) {
        this.typeHoliday = typeHoliday;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
