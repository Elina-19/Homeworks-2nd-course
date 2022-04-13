package ru.itis;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@RequiredArgsConstructor
@RequestMapping("/test")
@Controller
public class SimpleController {

    private MessageSourceAccessor msa;

    @Autowired
    private void setMessageSourceAccessor(MessageSource ms){
        this.msa = new MessageSourceAccessor(ms);
    }

    @GetMapping
    public String index(ModelMap map){
        map.put("message0", msa.getMessage("controller.message"));
        map.put("message1", msa.getMessage("controller.param.message", new Double[]{9.78}));
        return "index";
    }

    @RequestMapping("/change")
    public String change(){
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("DC#index").build();
    }
}
