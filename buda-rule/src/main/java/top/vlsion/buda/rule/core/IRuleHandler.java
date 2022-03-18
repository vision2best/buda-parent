package top.vlsion.buda.rule.core;

import top.vlsion.buda.rule.common.domain.AnalyzeData;
import top.vlsion.buda.rule.common.domain.RuleResult;

import java.util.List;

/**
 * 规则解析处理器
 *
 * @author : zhanghuang
 * @date : 2022-03-18 14:54
 */
public interface IRuleHandler {
    /**
     * 规则处理
     *
     * @param data 待分析数据
     * @return 规则计算结果list {@link RuleResult}
     */
    List<RuleResult> handle(AnalyzeData data);
}
