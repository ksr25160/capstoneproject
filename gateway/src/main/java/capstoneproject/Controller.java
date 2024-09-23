package capstoneproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class Controller {
    
    @GetMapping("/1")
    public String root(){

        return "1";
    }
}
