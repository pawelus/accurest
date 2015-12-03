package io.codearte.accurest.dsl

import com.github.tomakehurst.wiremock.http.ResponseDefinition
import com.github.tomakehurst.wiremock.matching.RequestPattern
import com.github.tomakehurst.wiremock.stubbing.StubMapping
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic

@CompileStatic
class WireMockStubStrategy {

	private final WireMockRequestStubStrategy wireMockRequestStubStrategy
	private final WireMockResponseStubStrategy wireMockResponseStubStrategy
	private final Integer priority
	private final String scenarioName
	private final String requiredScenarioState
	private final String newScenarioState

	WireMockStubStrategy(GroovyDsl groovyDsl) {
		this.wireMockRequestStubStrategy = new WireMockRequestStubStrategy(groovyDsl)
		this.wireMockResponseStubStrategy = new WireMockResponseStubStrategy(groovyDsl)
		this.priority = groovyDsl.priority
		this.scenarioName = groovyDsl.scenarioName
		this.requiredScenarioState = groovyDsl.requiredScenarioState
		this.newScenarioState = groovyDsl.newScenarioState
	}

	@CompileDynamic
	String toWireMockClientStub() {
		StubMapping stubMapping = new StubMapping()
		RequestPattern request = wireMockRequestStubStrategy.buildClientRequestContent()
		ResponseDefinition response = wireMockResponseStubStrategy.buildClientResponseContent()
		if (priority) {
			stubMapping.priority = priority
		}
		if (scenarioName) {
			stubMapping.scenarioName = scenarioName
		}
		if (newScenarioState) {
			stubMapping.newScenarioState = newScenarioState
		}
		if (requiredScenarioState) {
			stubMapping.requiredScenarioState = requiredScenarioState
		}
		stubMapping.request = request
		stubMapping.response = response
		return StubMapping.buildJsonStringFor(stubMapping)
	}
}