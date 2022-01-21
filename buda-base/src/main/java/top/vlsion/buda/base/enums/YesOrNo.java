package top.vlsion.buda.base.enums;

/**
 * YesOrNo标记 YES表示y NO表示n
 *
 * @author : zhanghuang
 * @date : 2021-12-31 10:46
 */
public enum YesOrNo {

    YES(1, "y"),//是
    NO(-1, "n"),//否
    DEFAULT(0, "");//默认

    private Integer index;
    private String code;

    YesOrNo(int index, String code) {
        this.index = index;
        this.code = code;
    }

    public int getIndex() {
        return index;
    }

    public String getCode() {
        return code;
    }

    /**
     * 根据index获取枚举值
     *
     * @param index 数值
     * @return YesOrNo
     */
    public YesOrNo parseByIndex(Integer index) {
        for (YesOrNo yesOrNo : YesOrNo.values()) {
            if (yesOrNo.getIndex() == index) {
                return yesOrNo;
            }
        }
        return DEFAULT;
    }

    /**
     * 根据code获取枚举值
     *
     * @param code 编码
     * @return YesOrNo
     */
    public YesOrNo parseByCode(String code) {
        for (YesOrNo yesOrNo : YesOrNo.values()) {
            if (yesOrNo.getCode().equalsIgnoreCase(code)) {
                return yesOrNo;
            }
        }
        return DEFAULT;
    }
}
