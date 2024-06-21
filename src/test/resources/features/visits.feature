Feature: API de visitas de PetClinic

  Scenario: Obtener todas las visitas
    Given el servicio de visitas está disponible
    When hago una solicitud GET a visitas "visits"
    Then la respuesta tiene un código de estado visitas 200
    And la respuesta contiene una lista de visitas
