package me.minkh.factorymethod;

import me.minkh.factorymethod.framework.Factory;
import me.minkh.factorymethod.framework.Product;
import me.minkh.factorymethod.idcard.IDCard;
import me.minkh.factorymethod.idcard.IDCardFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FactoryMethodTest {

    @Test
    void test() {
        Factory factory = new IDCardFactory();
        Product p1 = factory.create("홍길동");
        p1.use();
        assertThat(((IDCard) p1).getOwner()).isEqualTo("홍길동");
        assertThat(((IDCard) p1).getSerialNo()).isEqualTo(100);

        Product p2 = factory.create("이순신");
        p2.use();
        assertThat(((IDCard) p2).getOwner()).isEqualTo("이순신");
        assertThat(((IDCard) p2).getSerialNo()).isEqualTo(101);

        Product p3 = factory.create("강감찬");
        p3.use();
        assertThat(((IDCard) p3).getOwner()).isEqualTo("강감찬");
        assertThat(((IDCard) p3).getSerialNo()).isEqualTo(102);
    }

}