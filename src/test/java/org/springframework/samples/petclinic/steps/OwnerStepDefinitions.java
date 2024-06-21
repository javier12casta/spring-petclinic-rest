package org.springframework.samples.petclinic.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration
public class OwnerStepDefinitions {


    private static final String BASE_URL = "http://localhost:9966/petclinic/api/";
    private ResponseEntity<String> response;

    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Given("el servicio de propietarios está disponible")
    public void el_servicio_de_propietarios_esta_disponible() {
        String url = BASE_URL + "owners";
        try {
            ResponseEntity<String> response = this.restTemplate().getForEntity(url, String.class);
            if (response.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("El servicio de propietarios no está disponible");
            }
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("El servicio de propietarios no está disponible", e);
        }
    }

    @When("hago una solicitud GET a propietarios {string}")
    public void hago_una_solicitud_GET_a_propietarios(String endpoint) {
        String url = BASE_URL + endpoint;
        response = this.restTemplate().getForEntity(url, String.class);
    }

    @Then("la respuesta tiene un código de estado propietarios {int}")
    public void la_respuesta_tiene_un_codigo_de_estado(Integer statusCode) {
        assertEquals(statusCode.intValue(), response.getStatusCodeValue());
    }

    @Then("la respuesta contiene una lista de propietarios")
    public void la_respuesta_contiene_una_lista_de_propietarios() {
        assertNotNull(response.getBody());
        // Aquí puedes agregar más validaciones específicas del contenido de la respuesta
    }
}
