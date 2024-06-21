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
public class PetsStepDefinitions {

    private static final String BASE_URL = "http://localhost:9966/petclinic/api/";
    private ResponseEntity<String> response;

    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Given("el servicio de mascotas está disponible")
    public void el_servicio_de_mascotas_esta_disponible() {
        String url = BASE_URL + "pets";
        try {
            ResponseEntity<String> response = this.restTemplate().getForEntity(url, String.class);
            if (response.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("El servicio de mascotas no está disponible");
            }
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("El servicio de mascotas no está disponible", e);
        }
    }

    @When("hago una solicitud GET a pets {string}")
    public void hago_una_solicitud_GET_a_pets(String endpoint) {
        String url = BASE_URL + endpoint;
        response = this.restTemplate().getForEntity(url, String.class);
    }

    @Then("la respuesta tiene un código de estado pets {int}")
    public void la_respuesta_tiene_un_codigo_de_estado(Integer statusCode) {
        assertEquals(statusCode.intValue(), response.getStatusCodeValue());
    }

    @Then("la respuesta contiene una lista de mascotas")
    public void la_respuesta_contiene_una_lista_de_mascotas() {
        assertNotNull(response.getBody());
        // Aquí puedes agregar más validaciones específicas del contenido de la respuesta
    }

}
