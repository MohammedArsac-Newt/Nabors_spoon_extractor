package org.newtglobal.dmapextension.service;

import spoon.compiler.Environment;
import spoon.reflect.visitor.DefaultJavaPrettyPrinter;

public class RoundBracketMinimiser extends DefaultJavaPrettyPrinter {
	public RoundBracketMinimiser(Environment env) {
		super(env);
		this.setMinimizeRoundBrackets(true);
	}
}