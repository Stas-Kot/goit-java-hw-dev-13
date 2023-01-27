package com.goit.feature.mvc.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Controller
public class TestController {
    private final TestService testService;

    @GetMapping("/test")
    public ModelAndView getCurrentTime() {
        ModelAndView result = new ModelAndView("test");
        result.addObject("time", testService.getCurrentTime());

        return result;
    }
}
