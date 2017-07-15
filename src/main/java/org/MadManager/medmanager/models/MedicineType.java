package org.MadManager.medmanager.models;

/**
 * Created by Hiren on 7/14/2017.
 */
public enum MedicineType {
    TABLET("Tablet"),
    SYRUP("Syrup"),
    CAPSUL("Capsul"),
    GEL("Gel"),
    DROPS("Drops");

    private final  String name;

    MedicineType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
