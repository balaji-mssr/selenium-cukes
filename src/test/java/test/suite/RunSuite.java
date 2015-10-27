package test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import test.runner.RunSmokeTestFeature;


/**
 * Group any number of stories you would like to execute
 *
 */
@RunWith(Suite.class)
@SuiteClasses({RunSmokeTestFeature.class})	//Run single feature
public class RunSuite {

}