package mobile.assignment.backend.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;


@RestController
public class Gateway {
    @GetMapping("")
    public ResponseEntity<Map<String, String>> welcome() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Welcome to the Gateway!");
        return ResponseEntity.ok(response);
    }
    
}
