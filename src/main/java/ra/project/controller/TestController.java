package ra.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ra.project.exception.NotFoundException;

@RestController
@RequestMapping("/user")
public class TestController {
    @GetMapping
    public ResponseEntity<String> test(@RequestParam(value = "name", required = false) String name){
        if (name == null || name.isBlank()){
            throw new NotFoundException("kooong tìm thấy");
        }
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }
}
