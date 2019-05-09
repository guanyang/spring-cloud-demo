package org.gy.framework.demobusiness.biz;

import org.gy.framework.demobusiness.config.DynamicDataSourceType;
import org.gy.framework.demobusiness.config.annotation.DataSource;
import org.gy.framework.demobusiness.dao.ConfigInfoMapper;
import org.gy.framework.demobusiness.model.ConfigInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConfigInfoBiz {

    @Autowired
    private ConfigInfoMapper configInfoMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;


    public int updateConfigInfoByMapper(ConfigInfo info) {
        return configInfoMapper.updateByPrimaryKeySelective(info);
    }

    public int updateConfigInfoBySql(ConfigInfo info) {
        return sqlSessionTemplate.update("CONFIG_INFO.updateById", info);
    }

    @DataSource(DynamicDataSourceType.SLAVE)
    public int updateConfigInfoByMapper2(ConfigInfo info) {
        return configInfoMapper.updateByPrimaryKeySelective(info);
    }

    @DataSource(DynamicDataSourceType.SLAVE)
    @Transactional
    public int updateConfigInfoBySql2(ConfigInfo info) {
        return sqlSessionTemplate.update("CONFIG_INFO.updateById", info);
    }

    public ConfigInfo selectConfigInfoByMapper(Long id) {
        return configInfoMapper.selectByPrimaryKey(id);
    }

    public ConfigInfo selectConfigInfoBySql(Long id) {
        return sqlSessionTemplate.selectOne("CONFIG_INFO.selectById", id);
    }

    @DataSource(DynamicDataSourceType.MASTER)
    public ConfigInfo selectConfigInfoByMapper2(Long id) {
        return configInfoMapper.selectByPrimaryKey(id);
    }

    @DataSource(DynamicDataSourceType.SLAVE)
    public ConfigInfo selectConfigInfoBySql2(Long id) {
        return sqlSessionTemplate.selectOne("CONFIG_INFO.selectById", id);
    }
}
