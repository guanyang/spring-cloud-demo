package org.gy.framework.demobusiness.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.gy.framework.demobusiness.model.ConfigInfo;
import org.gy.framework.demobusiness.model.ConfigInfoExample;

public interface ConfigInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config_info
     *
     * @mbggenerated
     */
    int countByExample(ConfigInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config_info
     *
     * @mbggenerated
     */
    int deleteByExample(ConfigInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config_info
     *
     * @mbggenerated
     */
    int insert(ConfigInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config_info
     *
     * @mbggenerated
     */
    int insertSelective(ConfigInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config_info
     *
     * @mbggenerated
     */
    List<ConfigInfo> selectByExample(ConfigInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config_info
     *
     * @mbggenerated
     */
    ConfigInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config_info
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") ConfigInfo record, @Param("example") ConfigInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config_info
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") ConfigInfo record, @Param("example") ConfigInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ConfigInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table config_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ConfigInfo record);
}