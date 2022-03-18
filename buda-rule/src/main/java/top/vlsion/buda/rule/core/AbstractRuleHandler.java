package top.vlsion.buda.rule.core;

import top.vlsion.buda.rule.common.domain.RuleScript;

import java.util.Collection;

/**
 * @author : zhanghuang
 * @date : 2022-03-18 15:09
 */
public abstract class AbstractRuleHandler implements IRuleHandler {
    private Collection<RuleScript> ruleScripts;

    public AbstractRuleHandler(Collection<RuleScript> ruleScripts) {
        this.ruleScripts = ruleScripts;
    }

    protected Collection<RuleScript> getRuleScripts() {
        return ruleScripts;
    }
}
