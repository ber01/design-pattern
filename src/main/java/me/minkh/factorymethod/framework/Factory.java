package me.minkh.factorymethod.framework;

// 템플릿 메서드 패턴 적용
public abstract class Factory {

    // 템플릿 메서드
    public final Product create(String owner) {
        Product product = createProduct(owner);
        registerProduct(product);
        return product;
    }

    protected abstract Product createProduct(String owner);

    protected abstract void registerProduct(Product product);

}
