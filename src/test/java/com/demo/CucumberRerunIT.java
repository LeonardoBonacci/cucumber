package com.demo;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

/**
 * Failsafe integration test that reruns only the Cucumber scenarios that failed
 * in the initial surefire run.
 *
 * <p>The failed scenario locations are passed via the {@code cucumber.features}
 * system property (set by maven-failsafe-plugin from the Ant-loaded rerun.txt).
 * When that property is present, Cucumber ignores all other discovery selectors
 * and runs only the specified scenarios.</p>
 *
 * <p>This class is named with the {@code IT} suffix so that:
 * <ul>
 *   <li>surefire (test phase) excludes it</li>
 *   <li>failsafe (integration-test phase) picks it up</li>
 * </ul></p>
 *
 * <p>Lifecycle (all automated via {@code mvn clean verify}):</p>
 * <ol>
 *   <li>test — surefire runs all scenarios, produces rerun.txt on failures</li>
 *   <li>pre-integration-test — antrun loads rerun.txt into a Maven property</li>
 *   <li>integration-test — failsafe runs this class with only the failed scenarios</li>
 *   <li>verify — failsafe:verify fails the build if reruns still fail</li>
 * </ol>
 */
@Suite
@IncludeEngines("cucumber")
@SelectPackages("com.demo")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.demo")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports/rerun-cucumber.html, rerun:target/cucumber-reports/rerun2.txt")
@ConfigurationParameter(key = PLUGIN_PUBLISH_QUIET_PROPERTY_NAME, value = "true")
public class CucumberRerunIT {
}
