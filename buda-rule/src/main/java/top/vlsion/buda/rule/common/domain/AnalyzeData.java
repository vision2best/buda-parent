package top.vlsion.buda.rule.common.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * @author : zhanghuang
 * @date : 2022-03-18 15:04
 */
public class AnalyzeData implements Serializable {

    private static final long serialVersionUID = -6324104925917702061L;

    /**
     * 数据样本
     */
    private Map<String, Object> data;

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
