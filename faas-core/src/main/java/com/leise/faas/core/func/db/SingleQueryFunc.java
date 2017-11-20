package com.leise.faas.core.func.db;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leise.faas.core.func.Func;
import com.leise.faas.core.func.context.Context;
import com.leise.faas.core.func.enums.FuncStatus;

@Component
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
	
	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

}
