package test.runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/************************************************************************************
 * Dedicated Smoke Test Runner Cuke set up
 * Configure the glue for the smoke steps
 * Configure the smoke features
 ************************************************************************************/

@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty", "html:target/cucumber-html-report","json:target/basicreport.json" }, 
		glue = { "test.steps.fares" }
		,features = { "classpath:smoke/" }
		,monochrome=true
)
public class RunSmokeTestFeature {
	//This is just a runner
}
