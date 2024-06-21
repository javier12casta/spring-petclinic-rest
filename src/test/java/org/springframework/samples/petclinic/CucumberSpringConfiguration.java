package org.springframework.samples.petclinic;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest
@ContextConfiguration(classes = CucumberSpringConfiguration.class)
public class CucumberSpringConfiguration {
}
