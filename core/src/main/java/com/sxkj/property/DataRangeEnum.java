package com.sxkj.property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.sxkj.property.DataRangeEnum
 *
 * @author zwd
 * @Description DataRangeEnum
 * @Date Create in 2018-07-10 0010 14:20
 * @Modified
 */
public enum  DataRangeEnum {
    /** 全部数据 */
    DATA_RANGE_ALL("全部",0),
    /** 所在机构及下级数据 */
    DATA_RANGE_ORG_SUB("所在机构及下级",1),
    /** 所在机构本级的数据 */
    DATA_RANGE_ORG("所在机构",2),
    /** 所在部门及下级的数据 */
    DATA_RANGE_DEPT_SUB("所在部门及下级",3),
    /** 所在部门本级的数据 */
    DATA_RANGE_DEPT("所在部门",4),
    /** 创建者为本人的数据 */
    DATA_RANGE_SELF("仅本人",5),
    /** 按机构明细配置的数据 */
    DATA_RANGE_CUSTOM("明细",6);

    private String name;
    private int index;

    DataRangeEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static String getName(int index) {
        for (DataRangeEnum dataRangeEnum : DataRangeEnum.values()) {
            if (dataRangeEnum.getIndex() == index) {
                return dataRangeEnum.name;
            }
        }
        return "";
    }
    public static List<Map<String,Integer>> getItems(){
        List<Map<String,Integer>> items = new ArrayList<>(16);

        for (DataRangeEnum dataRangeEnum : DataRangeEnum.values()) {
            Map<String,Integer> map = new HashMap<>(1);
            map.put(dataRangeEnum.name,dataRangeEnum.index);
            items.add(map);
        }
        return items;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
}
