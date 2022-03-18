package top.vlsion.buda.rule.common.domain;

import java.io.Serializable;

/**
 * 规则脚本
 *
 * @author : zhanghuang
 * @date : 2022-03-18 15:08
 */
public class RuleScript implements Serializable {

    private static final long serialVersionUID = -1520043062326267254L;

    private String id;

    /**
     * 规则名字
     */
    private String tittle;

    /**
     * 规则描述
     */
    private String desc;

    /**
     * 规则是否启用
     */
    private String enable;

    /**
     * 规则级别
     */
    private String level;


    /**
     * 注意：Jexl引擎能够创建两种解析器，其中JexlExpression不能使用 if、for、while 语句块。
     * 所以此处用isScript=y标记 创建JexlScript来执行 isScript=n标记 创建JexlExpression来执行
     */
    private String isScript;

    /**
     * 规则脚本
     */
    private String ruleScript;

    /**
     * 执行完规则脚本期望得到结果
     */
    private String expectResult;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getIsScript() {
        return isScript;
    }

    public void setIsScript(String isScript) {
        this.isScript = isScript;
    }

    public String getRuleScript() {
        return ruleScript;
    }

    public void setRuleScript(String ruleScript) {
        this.ruleScript = ruleScript;
    }

    public String getExpectResult() {
        return expectResult;
    }

    public void setExpectResult(String expectResult) {
        this.expectResult = expectResult;
    }
}
