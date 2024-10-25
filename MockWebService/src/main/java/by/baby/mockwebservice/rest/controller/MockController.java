package by.baby.mockwebservice.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock")
public class MockController {

    @PostMapping
    public ResponseEntity<String> post() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
