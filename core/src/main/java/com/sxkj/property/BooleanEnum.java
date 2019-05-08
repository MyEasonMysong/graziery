package com.sxkj.property;

/**
 * com.sxkj.property.BooleanEnum
 *
 * @author zwd
 * @Description BooleanEnum
 * @Date Create in 2018-07-11 0011 11:31
 * @Modified
 */
public enum BooleanEnum {
    TRUE(1),FALSE(0);

    private int index;
    BooleanEnum(int index){
        this.index = index;
    }

}
