package top.vlsion.buda.rule.core.jexl;

import com.sun.tools.javac.util.StringUtils;
import org.apache.commons.jexl3.*;
import top.vlsion.buda.rule.common.domain.AnalyzeData;
import top.vlsion.buda.rule.common.domain.RuleResult;
import top.vlsion.buda.rule.common.domain.RuleScript;
import top.vlsion.buda.rule.core.AbstractRuleHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * apache common jexl3 【Java Expression Language】
 *
 * @author : zhanghuang
 * @date : 2022-03-18 15:12
 */
public class JexlRuleHandler extends AbstractRuleHandler {

    private static JexlEngine jexl = new JexlBuilder().create();

    public JexlRuleHandler(Collection<RuleScript> ruleScripts) {
        super(ruleScripts);
    }

    /**
     * 规则处理
     *
     * @param data 待分析数据
     * @return 规则计算结果list {@link RuleResult}
     */
    @Override
    public List<RuleResult> handle(AnalyzeData data) {
        List<RuleResult> results = new ArrayList<>();
        JexlContext jc = new MapContext();
        buildContextData(jc, data.getData());
        for (RuleScript ruleScript : getRuleScripts()) {
            Object evaluate;
            String script = ruleScript.getRuleScript();
            if ("y".equals(StringUtils.toLowerCase(ruleScript.getIsScript()))) {
                JexlScript jexlScript = jexl.createScript(script);
                evaluate = jexlScript.execute(jc);
            } else {
                JexlExpression expression = jexl.createExpression(ruleScript.getRuleScript());
                evaluate = expression.evaluate(jc);
            }
            if (evaluate.toString().equals(ruleScript.getExpectResult())) {
                results.add(new RuleResult(ruleScript, true));
            } else {
                results.add(new RuleResult(ruleScript, false));
            }
        }
        return results;
    }

    /**
     * 构建JexlContext 并填充数据
     *
     * @param jc   JexlContext上下文
     * @param data 样本数据中的真实数据
     */
    private void buildContextData(JexlContext jc, Map<String, Object> data) {
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            jc.set(entry.getKey(), entry.getValue());
        }
    }
}
