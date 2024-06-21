Feature: Owners API

  Scenario: Obtener la lista de propietarios
    Given el servicio de propietarios está disponible
    When hago una solicitud GET a propietarios "owners"
    Then la respuesta tiene un código de estado propietarios 200
    And la respuesta contiene una lista de propietarios
