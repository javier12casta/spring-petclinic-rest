package org.springframework.samples.petclinic;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.springframework.samples.petclinic.config.BeanConfig;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features = "src/test/resources/features")
@ContextConfiguration(classes = BeanConfig.class)
public class RunCucumberTest {
}
