package com.leise.faas.core.func.flow;

import org.springframework.stereotype.Component;

import com.leise.faas.core.context.Context;
import com.leise.faas.core.func.Func;
import com.leise.faas.core.func.enums.FuncStatus;

@Component("com.leise.faas.core.func.flow.EndFunc")
public class EndFunc implements Func {

	public FuncStatus execute(Context context) {
		return FuncStatus.SUCCESS;
	}

}
