package com.leise.faas.core.func.db;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leise.faas.core.func.Func;
import com.leise.faas.core.func.context.Context;
import com.leise.faas.core.func.enums.FuncStatus;

@Component("com.leise.faas.core.func.db.SingleQueryFunc")
public class SingleQueryFunc implements Func {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	private String statement;

	public FuncStatus execute(Context context) {

		Map<String, Object> inParams = context.getInParams();
		Map<String, Object> outParams = context.getOutParams();
		Map<String, Object> result = sqlSessionTemplate.selectOne(statement, inParams);
		outParams.putAll(result);

		return FuncStatus.SUCCESS;
	}

	public void setInitParams(Map<String, String> initParams) {
		this.statement = initParams.get("statement");
	}

}
