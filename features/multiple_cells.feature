Feature: Evolving a grid with some cells in it
  In order to create a functioning rules engine  
  As a programmer of Conway's Game of Life  
  I can evolve a multiple cell grid  

  Scenario: Sparse grid with nobody staying alive
    Given the following setup
    | . | . | . | . | . |
    | . | x | . | x | . |
    | . | . | . | . | . |
    | . | x | . | x | . |
    | . | . | . | . | . |
    When I evolve the board
    Then I should see the following board
    | . | . | . | . | . |
    | . | . | . | . | . |
    | . | . | . | . | . |
    | . | . | . | . | . |
    | . | . | . | . | . |
