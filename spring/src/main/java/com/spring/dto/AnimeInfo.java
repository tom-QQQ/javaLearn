package com.spring.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ning
 * @date Create in 2018/12/21
 */
@Data
public class AnimeInfo implements Serializable {

    private String animeName;

    private String time;

    private List<String> characters;

    private List<String> cvs;
}
