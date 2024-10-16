Feature: Calculator Operations

  Scenario Outline: Adding two numbers
    Given the first number is <firstNumber>
    And the second number is <secondNumber>
    When the user clicks the plus button
    Then the result should be <result>

    Examples:
      | firstNumber | secondNumber | result |
      | -5          | 3            | -2     |
      | 10          | 20           | 30     |
      | 0           | 0            | 0      |
      | 1000        | 200000       | 201000 |

  Scenario Outline: Subtracting two numbers
    Given the first number is <firstNumber>
    And the second number is <secondNumber>
    When the user clicks the minus button
    Then the result should be <result>

    Examples:
      | firstNumber | secondNumber | result |
      | 5           | 3            | 2      |
      | 20          | -10          | 30     |
      | 0           | 0            | 0      |
      | -500        | 700          | -1200  |

  Scenario Outline: Multiplying two numbers
    Given the first number is <firstNumber>
    And the second number is <secondNumber>
    When the user clicks the multiply button
    Then the result should be <result>

    Examples:
      | firstNumber | secondNumber | result |
      | 5           | 3            | 15     |
      | -10         | 20           | -200   |
      | 0           | 0            | 0      |
      | 1000        | 0.001        | 1     |

  Scenario Outline: Dividing two numbers
    Given the first number is <firstNumber>
    And the second number is <secondNumber>
    When the user clicks the divide button
    Then the result should be <result>

    Examples:
      | firstNumber | secondNumber | result |
      | 6           | 2            | 3.0    |
      | 20          | -10          | -2.0   |
      | 0           | 1            | 0.0    |
      | 10          | 0.01         | 1000.0 |

  Scenario Outline: Dividing by zero
    Given the first number is <firstNumber>
    And the second number is <secondNumber>
    When the user clicks the divide button
    Then the result should be <result>

    Examples:
      | firstNumber  | secondNumber  | result                    |
      | 6            | 0             | "ошибка деления на ноль!" |
      | 10           | 0.000000001   | "ошибка деления на ноль!" |
      | 0            | 0.0000000001  | "ошибка деления на ноль!" |
      | 0.000000001  | 0.000000001   | "ошибка деления на ноль!" |