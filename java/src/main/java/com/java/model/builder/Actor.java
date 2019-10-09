package com.java.model.builder;

/**
 * @author Ning
 * @date Create in 2019/4/15
 */
public class Actor {

    private String type;
    private String sex;
    private String face;
    private String costume;
    private String hairStyle;

    private Actor(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getCostume() {
        return costume;
    }

    public void setCostume(String costume) {
        this.costume = costume;
    }

    public String getHairStyle() {
        return hairStyle;
    }

    public void setHairStyle(String hairStyle) {
        this.hairStyle = hairStyle;
    }

    public static abstract class AbstractBuilder {

        protected Actor actor = new Actor();

        public abstract void buildType();
        public abstract void buildSex();
        public abstract void buildFace();
        public abstract void  buildCostume();
        public abstract void buildHairStyle();

        /**
         * 是否为光头
         * @return 默认为否
         */
        public boolean isBareheaded() {
            return false;
        }

        /**
         * 指定顺序
         * @param abstractBuilder builder
         * @return 构建结果
         */
        public Actor construct(AbstractBuilder abstractBuilder) {

            abstractBuilder.buildCostume();
            abstractBuilder.buildFace();
            abstractBuilder.buildSex();
            if (!isBareheaded()) {
                abstractBuilder.buildHairStyle();
            }
            abstractBuilder.buildType();

            return actor;
        }
    }
}
