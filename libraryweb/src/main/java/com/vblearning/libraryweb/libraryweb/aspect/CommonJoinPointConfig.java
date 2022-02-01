package com.vblearning.libraryweb.libraryweb.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJoinPointConfig {

	@Pointcut("execution(*  com.vblearning.libraryweb.libraryweb..*.*(..))")
	public void functionExecution() {
	}

	@Pointcut("within(com.vblearning.libraryweb.libraryweb.helper.*)")
	public void libraryHelperExecution() {
	}

	@Pointcut("execution(*  com.vblearning.libraryweb.libraryweb.MainController.returnBookPost(..))")
	public void returnBookPost() {
	}

	@Pointcut("execution(*  com.vblearning.libraryweb.libraryweb.MainController.rentBook(..))")
	public void rentBook() {
	}

	@Pointcut("@annotation(com.vblearning.libraryweb.libraryweb.aspect.TrackCustomFlow)")
	public void trackCustomFlowAnnotation() {
	}

	/*
	 * @Pointcut("functionExecution()") public void allLayerExecution() { }
	 */
	/*
	 * @Pointcut("rentBook() || returnBookPost()") public void allLayerExecution() {
	 * }
	 */

	@Pointcut("trackCustomFlowAnnotation()")
	public void allLayerExecution() {
	}
}
