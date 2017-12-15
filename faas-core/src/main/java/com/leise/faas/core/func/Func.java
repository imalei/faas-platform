package com.leise.faas.core.func;

import java.util.Map;

import com.leise.faas.core.func.context.Context;
import com.leise.faas.core.func.enums.FuncStatus;

public interface Func {

	public FuncStatus execute(Context context);
	
	default void setInitParams(Map<String, String> initParams) {
		
	}
}
