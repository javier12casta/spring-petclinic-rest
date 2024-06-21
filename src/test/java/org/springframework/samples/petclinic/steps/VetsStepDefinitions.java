package org.springframework.samples.petclinic.steps;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration
public class VetsStepDefinitions {

    private static final String BASE_URL = "http://localhost:9966/petclinic/api/";
    private ResponseEntity<String> response;

    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Given("el servicio de veterinarios está disponible")
    public void el_servicio_de_veterinarios_esta_disponible() {
        String url = BASE_URL + "vets";
        try {
            ResponseEntity<String> response = this.restTemplate().getForEntity(url, String.class);
            if (response.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("El servicio de veterinarios no está disponible");
            }
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("El servicio de veterinarios no está disponible", e);
        }
    }

    @When("hago una solicitud GET a vets {string}")
    public void hago_una_solicitud_GET_a_vets(String endpoint) {
        String url = BASE_URL + endpoint;
        response = this.restTemplate().getForEntity(url, String.class);
    }

    @Then("la respuesta tiene un código de estado vets {int}")
    public void la_respuesta_tiene_un_codigo_de_estado_vets(Integer statusCode) {
        assertEquals(statusCode.intValue(), response.getStatusCodeValue());
    }

    @Then("la respuesta contiene una lista de veterinarios")
    public void la_respuesta_contiene_una_lista_de_veterinarios() {
        assertNotNull(response.getBody());
        // Aquí puedes agregar más validaciones específicas del contenido de la respuesta
    }
}

