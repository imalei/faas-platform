package com.leise.faas.core.engine.flow;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.leise.faas.core.cache.FlowCache;
import com.leise.faas.core.cache.FlowMetadataCache;
import com.leise.faas.core.cache.FuncCache;
import com.leise.faas.core.cache.OperationMetadataCache;
import com.leise.faas.core.context.Context;
import com.leise.faas.core.engine.template.jaxb.Flow;
import com.leise.faas.core.engine.template.jaxb.Operation;
import com.leise.faas.core.engine.template.jaxb.Data;
import com.leise.faas.core.engine.template.jaxb.Target;
import com.leise.faas.core.func.Func;
import com.leise.faas.core.func.enums.FuncStatus;

@Component
public class FlowEngine {

	@Autowired
	FlowCache flowCache;

	@Autowired
	FuncCache funcCache;

	public String executeFlow(String flowId, Context context) {

		Flow flow = flowCache.getFlow(flowId);
		System.out.println("==============================>" + flow);
		Map<String, Operation> operations = FlowMetadataCache.getFlowMetadata(flow).getOperationsMap();
		String startFuncId = "aaa";
		String curIdx = startFuncId;
		while (curIdx != null) {
			Operation operation = operations.get(curIdx);

			Map<String, Data> inParamsMap = OperationMetadataCache.getOperationMetadata(operation).getInParamsMap();

			Map<String, Object> inParams = null;

			if (!CollectionUtils.isEmpty(inParamsMap)) {

				inParams = new HashMap<>();

				for (Map.Entry<String, Data> entry : inParamsMap.entrySet()) {
					if (context.getInParams().containsKey(entry.getKey())) {
						inParams.put(entry.getKey(), context.getInParams().get(entry.getKey()));
					}
				}
			}

			String refClass = operation.getRefClass();
			Func func = funcCache.getFunc(refClass);
			Context funcContext = new Context();
			funcContext.setInParams(inParams);

			Map<String, Data> initParamsMap = OperationMetadataCache.getOperationMetadata(operation)
					.getInitParamsMap();
			Map<String, String> initParams = null;
			if (!CollectionUtils.isEmpty(initParamsMap)) {
				initParams = new HashMap<>();
				for (Map.Entry<String, Data> entry : initParamsMap.entrySet()) {
					initParams.put(entry.getKey(), entry.getValue().getValue());
				}
			}

			func.setInitParams(initParams);
			FuncStatus status = func.execute(funcContext);

			if (FuncStatus.SUCCESS.compareTo(status) == 0) {
				System.out.println("执行成功");
				Map<String, Target> targetsMap = OperationMetadataCache.getOperationMetadata(operation).getTargetsMap();
				if (targetsMap.size() < 1) {
					curIdx = null;
				} else if (targetsMap.size() == 1) {
					Target target = null;
					for (Map.Entry<String, Target> entry : targetsMap.entrySet()) {
						target = entry.getValue();
						curIdx = target.getId();
					}
				} else {
					curIdx = getTargetByCondition(targetsMap, funcContext);
				}
			} else {
				curIdx = null;
			}
		}

		return null;
	}

	public String getTargetByCondition(Map<String, Target> targetsMap, Context context) {
		String targetId = null;
		ExpressionParser parser = new SpelExpressionParser();
		for (Map.Entry<String, Target> entry : targetsMap.entrySet()) {
			Target target = entry.getValue();
			String condition = target.getCondition();
			StandardEvaluationContext expressionContext = new StandardEvaluationContext();
			expressionContext.setVariables(context.getInParams());
			expressionContext.setVariables(context.getOutParams());
			Boolean pass = parser.parseExpression(condition).getValue(expressionContext, Boolean.class);

			if (pass) {
				targetId = target.getId();
				break;
			}
		}
		return targetId;
	}

	public static void main(String[] args) throws Exception {
		ExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setVariable("key1", new Date());
		context.setVariable("key2", new Date());
		String condition = "#key1.compareTo(#key2) ==  0";
		Object key1 = parser.parseExpression("#key1").getValue(context);
		Object key2 = parser.parseExpression("#key2").getValue(context);
		System.out.println("key1:===>" + key1);
		System.out.println("key2:===>" + key2);
		Boolean pass = parser.parseExpression(condition).getValue(context, Boolean.class);
		System.out.println(pass);

	}

}
