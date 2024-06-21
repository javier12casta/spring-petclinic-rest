Feature: Vets API

  Scenario: Obtener la lista de veterinarios
    Given el servicio de veterinarios está disponible
    When hago una solicitud GET a vets "vets"
    Then la respuesta tiene un código de estado vets 200
    And la respuesta contiene una lista de veterinarios
