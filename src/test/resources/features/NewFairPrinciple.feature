Feature: New Fair Principle
  In order to register a new FAIR Principle
  As a admin
  I want to create a new FAIR Principle

  Scenario: Register new FAIR Principle
    Given There is a registered admin with username "admin" and password "12345" and email "test@test.com"

    When I register a new FAIR Principle with the name "FAKEFAIRPRINCIPLE" and the name prefix "A1.1" and the description "FAKEFAIRPRINCIPLE" and the url "https://fake.com" and the category "FINDABILITY"
    Then The response code is 201
    And It has been created a new FAIR Principle with the name "FAKEFAIRPRINCIPLE" and the name prefix "A1.1" and the description "FAKEFAIRPRINCIPLE" and the url "https://fake.com" and the category "FINDABILITY"

  Scenario: Register new FAIR Principle not being admin
    Given There is a registered user with username "user" and password "12345" and email "test@test.com"

    When I register a new FAIR Principle with the name "FAKEFAIRPRINCIPLE" and the name prefix "A1.1" and the description "FAKEFAIRPRINCIPLE" and the url "https://fake.com" and the category "FINDABILITY"
    Then The response code is 201
    And It has been created a new FAIR Principle with the name "FAKEFAIRPRINCIPLE" and the name prefix "A1.1" and the description "FAKEFAIRPRINCIPLE" and the url "https://fake.com" and the category "FINDABILITY"