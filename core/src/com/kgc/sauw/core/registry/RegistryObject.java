package com.kgc.sauw.core.registry;

public abstract class RegistryObject {
    protected int id;
    protected String stringId;
    protected String package_;

    public int getId() {
        return id;
    }

    public String getStringId() {
        return stringId;
    }

    public String getPackage_() {
        return package_;
    }

    protected void setId(int id) {
        this.id = id;
    }

    protected void setStringId(String stringId) {
        this.stringId = stringId;
    }

    protected void setPackage_(String package_) {
        this.package_ = package_;
    }

    /**
     * The method is called after the object is registered
     * */
    public abstract void init();
}
