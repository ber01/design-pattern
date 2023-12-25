package me.minkh.factory.v1;

public class Factory {

    public static Mechanic createMechanic(Unit unit) {
        switch (unit) {
            case GOLIATH -> {
                return new Goliath();
            }
            case TANK -> {
                return new Tank();
            }
            case VULTURE -> {
                return new Vulture();
            }
            default -> throw new IllegalArgumentException("올바른 유닛의 타입이 아닙니다.");
        }
    }

}
