package org.gy.framework.demoadminweb.controller;


import org.gy.framework.demobusiness.biz.ConfigInfoBiz;
import org.gy.framework.demobusiness.model.ConfigInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class HelloController {

    @Autowired
    private ConfigInfoBiz configInfoBiz;


    @RequestMapping("/test1")
    public ModelAndView test1() {
        ModelAndView mav = new ModelAndView("demo");
        mav.addObject("time", System.currentTimeMillis());
        return mav;
    }

    @RequestMapping("/select")
    @ResponseBody
    public Object select(Long id) {
        return configInfoBiz.selectConfigInfoByMapper(id);
    }

    @RequestMapping("/select2")
    @ResponseBody
    public Object select2(Long id) {
        return configInfoBiz.selectConfigInfoBySql(id);
    }

    @RequestMapping("/select3")
    @ResponseBody
    public Object select3(Long id) {
        return configInfoBiz.selectConfigInfoByMapper2(id);
    }

    @RequestMapping("/select4")
    @ResponseBody
    public Object select4(Long id) {
        return configInfoBiz.selectConfigInfoBySql2(id);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(ConfigInfo info) {
        return configInfoBiz.updateConfigInfoByMapper(info);
    }

    @RequestMapping("/update2")
    @ResponseBody
    public Object update2(ConfigInfo info) {
        return configInfoBiz.updateConfigInfoBySql(info);
    }

    @RequestMapping("/update3")
    @ResponseBody
    public Object update3(ConfigInfo info) {
        return configInfoBiz.updateConfigInfoByMapper2(info);
    }

    @RequestMapping("/update4")
    @ResponseBody
    public Object update4(ConfigInfo info) {
        return configInfoBiz.updateConfigInfoBySql2(info);
    }
}
