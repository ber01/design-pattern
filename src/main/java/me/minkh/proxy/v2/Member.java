package me.minkh.proxy.v2;

public class Member {

    private Long id;

    private final String name;

    private final int age;

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

