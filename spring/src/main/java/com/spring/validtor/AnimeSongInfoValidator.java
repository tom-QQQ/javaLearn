package com.spring.validtor;

import com.spring.dto.AnimeSongInfo;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Ning
 * @date Create in 2018/12/21
 */
public class AnimeSongInfoValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return AnimeSongInfo.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "songName", "", "歌曲名不能为空");
        ValidationUtils.rejectIfEmpty(errors, "singer", "", "歌手名不能为空");
    }
}
