package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.service.CalcService;

@Controller
@RequestMapping("/calc")
@RequiredArgsConstructor
public class CalcController {

    private final CalcService calcService;

    @GetMapping(value = "/sum", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getSum(@RequestParam Integer firstParam, @RequestParam Integer secondParam){
        return calcService.getSum(firstParam, secondParam).toString();
    }
}
