package com.base.enums;

public enum  OptionEnum {
    A(0), B(1), C(2), D(3);

    private final int value;
    private OptionEnum(int value) {
        this.value = value;
    }

    public static OptionEnum valueOf(int value) {
        switch (value) {
            case 0:
                return OptionEnum.A;
            case 1:
                return OptionEnum.B;
            case 2:
                return OptionEnum.C;
            case 3:
                return OptionEnum.D;
            default:
                return null;
        }
    }

}
