package com.spring.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ning
 * @date Create in 2018/12/21
 */
public class AnimeInfo implements Serializable {

    private String animeName;

    private String time;

    private List<String> characters;

    private List<String> cvs;

    public String getAnimeName() {
        return animeName;
    }

    public void setAnimeName(String animeName) {
        this.animeName = animeName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }

    public List<String> getCvs() {
        return cvs;
    }

    public void setCvs(List<String> cvs) {
        this.cvs = cvs;
    }
}
