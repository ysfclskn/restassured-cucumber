Feature: Create Booking Test Suite

  Background:
    Given create token

  Scenario: Verify Create Booking and Delete Booking
    When create new booking
    Then delete new booking
