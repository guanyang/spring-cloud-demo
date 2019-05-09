package org.gy.framework.demoservice.fallback;

import org.gy.framework.demoservice.dto.ConfigInfoDTO;
import org.gy.framework.demoservice.dto.TestRequestDTO;
import org.gy.framework.demoservice.dto.TestResponseDTO;
import org.gy.framework.demoservice.service.TestService;
import org.gy.framework.demoservice.util.ResponseCode;
import org.gy.framework.demoservice.util.ResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class TestServiceFallBack implements TestService {
    @Override
    public ResponseDTO<TestResponseDTO> test(@RequestBody TestRequestDTO dto) {
        ResponseDTO<TestResponseDTO> res = new ResponseDTO<>();
        res.wrapResponse(ResponseCode.E9997, "fallback");
        return res;
    }

    @Override
    public ResponseDTO<ConfigInfoDTO> selectConfigInfoById(@RequestParam("id") Long id) {
        ResponseDTO<ConfigInfoDTO> res = new ResponseDTO<>();
        res.wrapResponse(ResponseCode.E9997, "fallback");
        return res;
    }

    @Override
    public ResponseDTO<Integer> updateConfigInfo(@RequestBody ConfigInfoDTO dto) {
        ResponseDTO<Integer> res = new ResponseDTO<>();
        res.wrapResponse(ResponseCode.E9997, "fallback");
        return res;
    }
}
