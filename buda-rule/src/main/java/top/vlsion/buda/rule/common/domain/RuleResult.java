package top.vlsion.buda.rule.common.domain;

import java.io.Serializable;

/**
 * 规则计算结果
 *
 * @author : zhanghuang
 * @date : 2022-03-18 15:00
 */
public class RuleResult implements Serializable {

    private static final long serialVersionUID = -163779135427068234L;

    private RuleScript ruleScript;
    private boolean pass;

    public RuleResult(RuleScript ruleScript, boolean pass) {
        this.ruleScript = ruleScript;
        this.pass = pass;
    }

    public RuleScript getRuleScript() {
        return ruleScript;
    }

    public void setRuleScript(RuleScript ruleScript) {
        this.ruleScript = ruleScript;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }
}
