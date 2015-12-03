package io.codearte.accurest.dsl

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TypeChecked
import io.codearte.accurest.dsl.internal.Request
import io.codearte.accurest.dsl.internal.Response

@TypeChecked
@EqualsAndHashCode
@ToString(includeFields = true, includePackage = false, includeNames = true)
class GroovyDsl {

	Integer priority
	String scenarioName
	String requiredScenarioState
	String newScenarioState
	Request request
	Response response

	static GroovyDsl make(Closure closure) {
		GroovyDsl dsl = new GroovyDsl()
		closure.delegate = dsl
		closure()
		return dsl
	}

	void priority(int priority) {
		this.priority = priority
	}

	void scenarioName(String scenarioName) {
		this.scenarioName = scenarioName
	}

	void requiredScenarioState(String requiredScenarioState) {
		this.requiredScenarioState = requiredScenarioState
	}

	void newScenarioState(String newScenarioState) {
		this.newScenarioState = newScenarioState
	}

	void request(@DelegatesTo(Request) Closure closure) {
		this.request = new Request()
		closure.delegate = request
		closure()
	}

	void response(@DelegatesTo(Response) Closure closure) {
		this.response = new Response()
		closure.delegate = response
		closure()
	}

}
