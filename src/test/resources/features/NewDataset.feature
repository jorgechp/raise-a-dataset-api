Feature: New Dataset
  In order to register a Dataset
  As a user
  I want to create a new Dataset

  Scenario: Register new Dataset
    Given There is a registered user with username "user" and password "12345" and email "test@test.com"

    When I register a new Dataset with the name "fakeDataset" and the description "fake dataset" and the creation date "05/06/1990" and the registration date "25/03/1999" and the author "Simón Bolivar"
    Then The response code is 201
    And It has been created a dataset with the name "fakeDataset" and the description "fake dataset" and the creation date "1990-06-05" date "1999-03-25"  and the author "Simón Bolivar"

