package org.gy.framework.demoweb.controller;


import org.gy.framework.demoservice.dto.ConfigInfoDTO;
import org.gy.framework.demoservice.dto.TestRequestDTO;
import org.gy.framework.demoservice.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class HelloController {

    @Autowired
    private TestService testService;


    @RequestMapping("/demo")
    public ModelAndView demo() {
        ModelAndView mav = new ModelAndView("demo");
        mav.addObject("time", System.currentTimeMillis());
        return mav;
    }

    @RequestMapping("/test")
    @ResponseBody
    public Object test(TestRequestDTO dto) {
        return testService.test(dto);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(ConfigInfoDTO dto) {
        return testService.updateConfigInfo(dto);
    }

    @RequestMapping("/select")
    @ResponseBody
    public Object select(Long id) {
        return testService.selectConfigInfoById(id);
    }

}
