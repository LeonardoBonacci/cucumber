package com.demo;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

/**
 * Third-run failsafe integration test that reruns only the Cucumber scenarios
 * that still failed after the first rerun (CucumberRerunIT).
 *
 * <p>The failed scenario locations are passed via the {@code cucumber.features}
 * system property (set by maven-failsafe-plugin from the Ant-loaded rerun2.txt).
 * </p>
 *
 * <p>Lifecycle (all automated via {@code mvn clean verify}):</p>
 * <ol>
 *   <li>test — surefire runs all scenarios, produces rerun.txt on failures</li>
 *   <li>pre-integration-test — antrun loads rerun.txt</li>
 *   <li>integration-test — failsafe runs CucumberRerunIT, produces rerun2.txt on failures</li>
 *   <li>post-integration-test — antrun loads rerun2.txt</li>
 *   <li>verify — failsafe runs this class with only the still-failing scenarios</li>
 * </ol>
 */
@Suite
@IncludeEngines("cucumber")
@SelectPackages("com.demo")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.demo")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports/rerun2-cucumber.html")
@ConfigurationParameter(key = PLUGIN_PUBLISH_QUIET_PROPERTY_NAME, value = "true")
public class CucumberRerun2IT {
}
