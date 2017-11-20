package com.leise.faas.core.func;

import com.leise.faas.core.func.context.Context;
import com.leise.faas.core.func.enums.FuncStatus;

public interface Func {

	public FuncStatus execute(Context context);
}
