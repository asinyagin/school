package school.philosophers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PhilosophersController {

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "hi";
    }
}
