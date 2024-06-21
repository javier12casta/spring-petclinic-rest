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
public class PettypesStepDefinitions {


    private static final String BASE_URL = "http://localhost:9966/petclinic/api/";
    private ResponseEntity<String> response;

    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Given("el servicio de tipos de mascotas está disponible")
    public void el_servicio_de_tipo_de_mascotas_esta_disponible() {
        String url = BASE_URL + "pettypes";
        try {
            ResponseEntity<String> response = this.restTemplate().getForEntity(url, String.class);
            if (response.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("El servicio de tipo de mascotas no está disponible");
            }
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("El servicio de tipo de mascotas no está disponible", e);
        }
    }

    @When("hago una solicitud GET a pettypes {string}")
    public void hago_una_solicitud_GET_a_pettypes(String endpoint) {
        String url = BASE_URL + endpoint;
        response = this.restTemplate().getForEntity(url, String.class);
    }

    @Then("la respuesta tiene un código de estado pettypes {int}")
    public void la_respuesta_tiene_un_codigo_de_estado(Integer statusCode) {
        assertEquals(statusCode.intValue(), response.getStatusCodeValue());
    }

    @Then("la respuesta contiene una lista de tipos de mascotas")
    public void la_respuesta_contiene_una_lista_de_tipo_de_mascotas() {
        assertNotNull(response.getBody());
        // Aquí puedes agregar más validaciones específicas del contenido de la respuesta
    }
}
