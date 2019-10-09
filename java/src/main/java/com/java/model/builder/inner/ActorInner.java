package com.java.model.builder.inner;

/**
 * 内部构造器
 * @author Ning
 * @date Create in 2019/4/15
 */
public class ActorInner {

    private String type;
    private String sex;
    private String face;
    private String costume;
    private String hairStyle;

    private ActorInner(){}

    private ActorInner(ActorBuilder actorBuilder){

        this.costume = actorBuilder.costume;
        this.type = actorBuilder.type;
        this.sex = actorBuilder.sex;
        this.face = actorBuilder.face;
        this.hairStyle = actorBuilder.hairStyle;
    }

    public String getType() {
        return type;
    }

    public String getSex() {
        return sex;
    }

    public String getFace() {
        return face;
    }

    public String getCostume() {
        return costume;
    }

    public String getHairStyle() {
        return hairStyle;
    }

    public static class ActorBuilder {

        private String type =  "默认";
        private String sex =  "默认";
        private String face =  "默认";
        private String costume =  "默认";
        private String hairStyle =  "默认";

        public ActorBuilder type(String type) {
            this.type = type;
            return this;
        }

        public ActorBuilder sex(String sex) {
            this.sex = sex;
            return this;
        }

        public ActorBuilder face(String face) {
            this.face = face;
            return this;
        }

        public ActorBuilder costume(String costume) {
            this.costume = costume;
            return this;
        }

        public ActorBuilder hairStyle(String hairStyle) {
            this.hairStyle = hairStyle;
            return this;
        }

        public ActorInner build() {

            return new ActorInner(this);
        }
    }
}
