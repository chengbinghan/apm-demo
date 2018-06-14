package com.hcb.agent.update;

public class HcbSimplePrincipal {
    private final String name;

    public HcbSimplePrincipal(String name) {
        System.out.println("this is old HcbSimplePrincipal ..........");
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
