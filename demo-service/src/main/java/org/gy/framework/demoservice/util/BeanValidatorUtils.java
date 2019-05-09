package org.gy.framework.demoservice.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanValidatorUtils {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private BeanValidatorUtils() {
    }

    /**
     * 调用JSR303的validate方法，验证失败时返回错误信息string，如果为空，则验证通过，否则不通过
     *
     * @param bean       要验证的JavaBean
     * @param withField  是否拼接属性名，true是，false否
     * @param allMessage 是否需要所有的错误信息，true是，false否，只返回第一条错误信息
     * @return 错误信息string
     */
    public static <T> String validate(T bean, boolean withField, boolean allMessage) {
        Map<String, String> map = validateMapResult(bean, allMessage);
        return validatMapToString(map, withField);
    }

    /**
     * 调用JSR303的validate方法, 验证失败时返回错误信息Map，key属性名，value错误信息
     *
     * @param bean       要验证的JavaBean
     * @param allMessage 是否需要所有的错误信息，true是，false否，只返回第一条错误信息
     * @param <T>
     * @return 错误信息map
     */
    private static <T> Map<String, String> validateMapResult(T bean, boolean allMessage) {
        if (bean == null) {
            return null;
        }
        Map<String, String> errorMessages = new HashMap<>();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(bean);
        // 抛出检验异常
        if (constraintViolations != null) {
            for (ConstraintViolation<T> violation : constraintViolations) {
                errorMessages.put(violation.getPropertyPath().toString(), violation.getMessage());
                if (!allMessage) {
                    break;
                }
            }
        }
        return errorMessages;
    }

    /**
     * 将错误信息Map转换成string
     *
     * @param map       错误信息
     * @param withField 是否拼接属性名，true是，false否
     * @return
     */
    private static String validatMapToString(Map<String, String> map, boolean withField) {
        if (map == null || map.size() == 0) {
            return null;
        }
        StringBuilder buffer = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (withField) {
                buffer.append(entry.getKey()).append(":");
            }
            buffer.append(entry.getValue()).append(";");
        }
        return buffer.toString();
    }

}
