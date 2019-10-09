package com.spring.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Ning
 * @date Create in 2018/12/21
 */
@Data
public class AnimeSongInfo implements Serializable {

    private String songName;

    private String singer;
}
