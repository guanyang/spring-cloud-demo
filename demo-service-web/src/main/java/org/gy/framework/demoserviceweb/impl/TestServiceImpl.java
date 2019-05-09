package org.gy.framework.demoserviceweb.impl;

import org.gy.framework.demobusiness.biz.ConfigInfoBiz;
import org.gy.framework.demobusiness.model.ConfigInfo;
import org.gy.framework.demoservice.dto.ConfigInfoDTO;
import org.gy.framework.demoservice.dto.TestRequestDTO;
import org.gy.framework.demoservice.dto.TestResponseDTO;
import org.gy.framework.demoservice.service.TestService;
import org.gy.framework.demoservice.util.BeanValidatorUtils;
import org.gy.framework.demoservice.util.ResponseDTO;
import org.gy.framework.demoutil.convert.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述：
 */
@RestController
public class TestServiceImpl implements TestService {


    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ConfigInfoBiz configInfoBiz;

    @Override
    public ResponseDTO<Integer> updateConfigInfo(@RequestBody ConfigInfoDTO dto) {
        ResponseDTO<Integer> res = new ResponseDTO<>();
        ConfigInfo info = new ConfigInfo();
        BeanUtil.copyProperties(dto,info);
        int result = configInfoBiz.updateConfigInfoByMapper(info);
        res.setData(result);
        return res;
    }

    @Override
    public ResponseDTO<ConfigInfoDTO> selectConfigInfoById(@RequestParam("id") Long id) {
        ResponseDTO<ConfigInfoDTO> res = new ResponseDTO<>();
        ConfigInfoDTO dto = new ConfigInfoDTO();
        ConfigInfo info = configInfoBiz.selectConfigInfoByMapper(id);
        BeanUtil.copyProperties(info, dto);
        res.setData(dto);
        return res;
    }

    @Override
    public ResponseDTO<TestResponseDTO> test(@RequestBody TestRequestDTO dto) {
        ResponseDTO<TestResponseDTO> res = new ResponseDTO<>();
        TestResponseDTO responseDTO = new TestResponseDTO();
        String validate = BeanValidatorUtils.validate(dto, true, true);
        responseDTO.setMessage(validate);
        res.setData(responseDTO);
        logger.info("service测试日志info：" + System.currentTimeMillis());
        logger.debug("service测试日志debug：" + System.currentTimeMillis());
        logger.warn("service测试日志warn：" + System.currentTimeMillis());
        logger.error("service测试日志error：" + System.currentTimeMillis());
        return res;
    }

}
