package vn.techzen.academy_pnv_25.controllor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculator")
@CrossOrigin("http://localhost:5173")
public class CalculatorController {
    @GetMapping
    public ResponseEntity<?> calculate(
            @RequestParam(required = false) String firstNumberStr,
            @RequestParam(required = false) String secondNumberStr,
            @RequestParam String operation) {

        if (firstNumberStr.isEmpty() || secondNumberStr.isEmpty()) {
            return ResponseEntity.badRequest().body("Numbers cannot be empty");
        }

        double firstNumber;
        double secondNumber;
        try {
            firstNumber = Double.parseDouble(firstNumberStr);
            secondNumber = Double.parseDouble(secondNumberStr);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid number format");
        }

        double result;
        switch (operation) {
            case "add":
                result = firstNumber + secondNumber;
                break;
            case "subtract":
                result = firstNumber - secondNumber;
                break;
            case "multiply":
                result = firstNumber * secondNumber;
                break;
            case "divide":
                if (secondNumber == 0) {
                    return ResponseEntity.badRequest().body("Cannot divide by zero");
                }
                result = firstNumber / secondNumber;
                break;
            default:
                return ResponseEntity.badRequest().body("Invalid operation");
        }

        return ResponseEntity.ok("Result: " + result);
    }
}