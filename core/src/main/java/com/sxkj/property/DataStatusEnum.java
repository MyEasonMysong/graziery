package com.sxkj.property;

/**
 * com.sxkj.property.DataStatusEnum
 * 表中status_flag专用值
 * @author zwd
 * @Description DataStatusEnum
 * @Date Create in 2018-07-23 0023 9:46
 * @Modified
 */
public enum DataStatusEnum {
    AVAILABLE(new Byte("0"),"可用"),
    UNAVAILABLE(new Byte("1"),"不可用"),
    DELETE(new Byte("2"),"已删除");

    private Byte idnex;
    private String label;

    DataStatusEnum(byte index,String label){
        this.idnex = index;
        this.label = label;
    }

    public Byte getIdnex() {
        return idnex;
    }


    public String getLabel() {
        return label;
    }


}
