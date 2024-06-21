Feature: Specialties API

  Scenario: Obtener la lista de especialidades
    Given el servicio de especialidades está disponible
    When hago una solicitud GET a specialties "specialties"
    Then la respuesta tiene un código de estado specialties 200
    And la respuesta contiene una lista de especialidades
