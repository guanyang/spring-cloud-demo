package org.gy.framework.demoservice.service;


import org.gy.framework.demoservice.dto.ConfigInfoDTO;
import org.gy.framework.demoservice.dto.TestRequestDTO;
import org.gy.framework.demoservice.dto.TestResponseDTO;
import org.gy.framework.demoservice.fallback.TestServiceFallBack;
import org.gy.framework.demoservice.util.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "demo-service-web", fallback = TestServiceFallBack.class)
public interface TestService {

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    ResponseDTO<TestResponseDTO> test(@RequestBody TestRequestDTO dto);

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    ResponseDTO<ConfigInfoDTO> selectConfigInfoById(@RequestParam("id") Long id);

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    ResponseDTO<Integer> updateConfigInfo(@RequestBody ConfigInfoDTO dto);


}
