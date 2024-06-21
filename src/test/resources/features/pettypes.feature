Feature: Pettypes API

  Scenario: Obtener la lista de tipos de mascotas
    Given el servicio de tipos de mascotas está disponible
    When hago una solicitud GET a pettypes "pettypes"
    Then la respuesta tiene un código de estado pettypes 200
    And la respuesta contiene una lista de tipos de mascotas
