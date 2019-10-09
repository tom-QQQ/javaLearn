package com.spring.validtor;


import com.spring.dto.AnimeInfo;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * 参数验证
 * @author Ning
 * @date Create in 2018/12/21
 */
public class AnimeInfoValidator implements Validator {

    /**
     * 判断支持的Bean类型
     * @param aClass 参数检查类型
     * @return 是否支持
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return AnimeInfo.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        // 判空处理逻辑
        ValidationUtils.rejectIfEmpty(errors, "animeName", "", "动漫名不能为空");
        AnimeInfo animeInfo = (AnimeInfo) o;

        // 自定义处理逻辑
        if (StringUtils.isEmpty(animeInfo.getTime())) {
            errors.rejectValue("time", "", "时间不能为空");
        }
    }
}
