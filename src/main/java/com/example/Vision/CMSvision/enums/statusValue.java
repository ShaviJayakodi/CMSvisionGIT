package com.example.Vision.CMSvision.enums;


public enum statusValue {
    ACTIVE(1),
    DEACTIVE(0);
    private int sts;

    statusValue(int sts)
    {
        this.sts=sts;

    }
    public int sts()
    {
        return sts;
    }

}
