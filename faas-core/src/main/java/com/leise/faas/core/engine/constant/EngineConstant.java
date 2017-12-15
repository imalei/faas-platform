package com.leise.faas.core.engine.constant;

public final class EngineConstant {

	public static final String ROOT_NODE = "root";

	public static final String FLOW_NODE = "flow";

	public static final String FUNCS_NODE = "funcs";

	public static final String FUNC_NODE = "func";

	public static final String INPUT_NODE = "input";

	public static final String OUTPUT_NODE = "output";

	public static final String PARAM_NODE = "param";

	public static final String TARGETS_NODE = "targets";

	public static final String TARGET_NODE = "target";
	
	public static final String INIT_NODE = "init";

	public static final String ID_ATTRIBUTE = "id";

	public static final String NAME_ATTRIBUTE = "name";

	public static final String REFCLASS_ATTRIBUTE = "refClass";

	private EngineConstant() {
		throw new IllegalStateException("Utility class");
	}

}
