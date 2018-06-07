package com.hcb.javaassist.innerclass;

public class OutClazz {

    // 非静态内部类，只有一个外部类对象存在的时候，才有意义
    // 战斗成绩只有在一个英雄对象存在的时候才有意义
    public class InnerClazz {


        String innerField;

        public InnerClazz(String innerField) {
            System.out.println("start==================>");
            this.innerField = innerField;
            System.out.println("end==================>");


        }


        public String getInnerField() {
            return innerField;
        }

        public void setInnerField(String innerField) {
            this.innerField = innerField;
        }
    }


}