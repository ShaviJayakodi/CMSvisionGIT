package com.example.Vision.CMSvision.enums;

public enum stakeHolderValues {
    TEACHER("10"),
    STUDENT("20"),
    OFFICER("30");

    private String code;
    stakeHolderValues (String code)
    {
        this.code=code;
    }
    public String code()
    {
        return code;
    }


}
