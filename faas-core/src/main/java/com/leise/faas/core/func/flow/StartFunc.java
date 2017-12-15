package com.leise.faas.core.func.flow;

import org.springframework.stereotype.Component;

import com.leise.faas.core.func.Func;
import com.leise.faas.core.func.context.Context;
import com.leise.faas.core.func.enums.FuncStatus;

@Component("com.leise.faas.core.func.flow.StartFunc")
public class StartFunc implements Func {

	public FuncStatus execute(Context context) {
		return FuncStatus.SUCCESS;
	}

}
