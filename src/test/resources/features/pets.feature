Feature: Pets API

  Scenario: Obtener la lista de mascotas
    Given el servicio de mascotas está disponible
    When hago una solicitud GET a pets "pets"
    Then la respuesta tiene un código de estado pets 200
    And la respuesta contiene una lista de mascotas
