package com.leise.faas.core.engine.flow;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.leise.faas.core.cache.FlowCache;
import com.leise.faas.core.cache.FuncCache;
import com.leise.faas.core.engine.template.FlowTemplate;
import com.leise.faas.core.engine.template.FuncTemplate;
import com.leise.faas.core.engine.template.InputTemplate;
import com.leise.faas.core.engine.template.ParamTemplate;
import com.leise.faas.core.engine.template.TargetTemplate;
import com.leise.faas.core.func.Func;
import com.leise.faas.core.func.context.Context;
import com.leise.faas.core.func.enums.FuncStatus;

@Component
public class FlowEngine {

	@Autowired
	FlowCache flowCache;

	@Autowired
	FuncCache funcCache;

	public String executeFlow(String flowId, Context context) {

		FlowTemplate flowTemplate = flowCache.getFlowTemplate(flowId);
		System.out.println("==============================>" + flowTemplate);
		Map<String, FuncTemplate> funcTemplates = flowTemplate.getFuncTemplates();
		String startFuncId = flowTemplate.getStartFuncId();
		String curIdx = startFuncId;
		while (curIdx != null) {
			FuncTemplate funcTemplate = funcTemplates.get(curIdx);
			InputTemplate inputTempalate = funcTemplate.getInputTemplate();
			
			Map<String, Object> defInputParams = new HashMap<>();
			if(inputTempalate != null) {
				Map<String, ParamTemplate> params = inputTempalate.getParams();
				for (Map.Entry<String, ParamTemplate> entry : params.entrySet()) {
					
					if(context.getInParams().containsKey(entry.getKey())) {
						defInputParams.put(entry.getKey(), context.getInParams().get(entry.getKey()));
					}else {
						defInputParams.put(entry.getKey(), entry.getValue().getDefaultValue());
					}
				}
			}
			
			String refClass = funcTemplate.getRefClass();
			Func func = funcCache.getFunc(refClass);
			Context funcContext = new Context();
			funcContext.setInParams(defInputParams);
			func.setInitParams(funcTemplate.getInitParams());
			FuncStatus status = func.execute(funcContext);

			if (FuncStatus.SUCCESS.compareTo(status) == 0) {
				System.out.println("执行成功");
				Map<String, TargetTemplate> targetTemplates = funcTemplate.getTargetTemplates();
				if(CollectionUtils.isEmpty(targetTemplates)) {
					throw new RuntimeException("aaa");
				}else {
					
					if(targetTemplates.size() == 1) {
						TargetTemplate targetTemplate = null;
						for (Map.Entry<String, TargetTemplate> entry : targetTemplates.entrySet()) {
							targetTemplate = entry.getValue();
							curIdx = targetTemplate.getId();
						}
					}else {
						String condition = null;
						
					}
				}
			}
		}

		return null;
	}
	
	public String getTargetByCondition(Map<String, TargetTemplate> targetTemplates, Context context) {
		String targetId = null;
		for (Map.Entry<String, TargetTemplate> entry : targetTemplates.entrySet()) {
			TargetTemplate targetTemplate = entry.getValue();
			String condition = targetTemplate.getCondition();
		}
		return targetId;
	}

}
