package me.minkh.factorymethod.idcard;

import me.minkh.factorymethod.framework.Product;

// 인식 카드 번호
public class IDCard extends Product {

    private final String owner;
    private final int serialNo;

    IDCard(String owner, int serialNo) {
        System.out.println(owner + "(" + serialNo + ") 의 카드 생성");
        this.owner = owner;
        this.serialNo = serialNo;
    }

    @Override
    public void use() {
        System.out.println(owner + "(" + serialNo + ") 의 카드 사용");
    }

    public String getOwner() {
        return owner;
    }

    public int getSerialNo() {
        return serialNo;
    }

}
