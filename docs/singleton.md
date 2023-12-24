# 싱글톤 패턴

클래스의 인스턴스가 오직 하나만 생성되도록 보장

## 지연 초기화

`getInstance()` 메서드를 사용하기 전 까지 객체의 생성을 미룬다.

```java
public class Singleton {

    private static Singleton singleton;

    private Singleton() {
        System.out.println("instance init");
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }

}
```

Singleton 클래스를 참조하더라도 인스턴스를 생성하지 않아 메모리 관리의 이점이 있으나 쓰레드 세이프 하지 않음

```java
@Test
void test2() {
    ExecutorService threadPool = Executors.newFixedThreadPool(1000);
    Set<Singleton> set = ConcurrentHashMap.newKeySet();
    for (int i = 0; i < 1000; i++) {
        threadPool.submit(() -> {
            set.add(Singleton.getInstance());
        });
    }

    threadPool.shutdown();
    while (true) {
        if (threadPool.isTerminated()) break;
    }

    assertThat(set.size()).isEqualTo(1);
}
```

쓰레드 풀에 쓰레드를 1,000개 생성한 뒤 getInstance() 메서드에 접근하는 테스트 코드

![](https://velog.velcdn.com/images/ksyj8256/post/c127a336-6efe-43e4-b92b-cd13771a5176/image.png)

동일한 객체를 반환한다면 Set의 사이즈는 1이어야 하지만 해당 테스트는 실패한다.

## 이른 초기화

`Singleton` 클래스가 참조되면 클래스 로더에 의해 `static` 필드가 초기화 됨

`getInstance()` 메서드의 호출의 여부와 상관없이 객체가 생성되어 메모리를 점유한다.

```java
public class Singleton {

    private static final Singleton SINGLETON = new Singleton();

    private Singleton() {
        System.out.println("instance init");
    }

    public static Singleton getInstance() {
        return SINGLETON;
    }

}
```

![](https://velog.velcdn.com/images/ksyj8256/post/5129a851-09bd-4ec3-acb0-2a0ee97973e4/image.png)

메모리를 점유하는 단점은 있으나 쓰레드 세이프하다.

![](https://github.com/ber01/refactoring-2nd/assets/28918801/7b652f5c-cc4a-49e6-864e-2713dddb65cb)

잘 알려진 싱글톤 클래스인 `Runtime`이 이른 초기화 방식을 사용한다.

## 지연 초기화 + synchronized

지연 초기화의 이점을 가지면서 쓰레드 세이프 하고 싶을 때 사용

### synchronized 키워드

```java
public class Singleton {

    private static Singleton singleton;

    private Singleton() {
        System.out.println("instance init");
    }

    public static synchronized Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }

}
```

단순한 방법, `getInstance()` 메서드에 `synchronized` 키워드 달기

해당 방법을 이용하면 테스트를 통과한다.

### Double-checked locking

```java
public class Singleton {

    private static Singleton singleton;

    private Singleton() {
        System.out.println("instance init");
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
```

`synchronized` 키워드를 이용하면 `getInstance()`를 호출할 때 마다 매 번 락을 생성한다.

성능 저하의 우려가 있기에 보통 Double-checked locking 방식을 사용한다.

## Holder

똑똑한 IDE 능력을 확인할 시간

![](https://github.com/ber01/refactoring-2nd/assets/28918801/74ceb90f-1b19-4315-8e61-e87132d5bd77)

Double-checked locking 방식을 사용하면 IntelliJ가 Holder 클래스를 이용한 방식으로 자동으로 변환시켜준다.

```java
public class Singleton {

    private Singleton() {
        System.out.println("instance init");
    }

    private static final class SingletonHolder {
        private static final Singleton singleton = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.singleton;
    }

}
```

1. Singleton 클래스의 참조가 일어나도 객체가 생성되지 않음
2. getInstance() 메서드가 호출 될 때 객체가 생성됨 (지연 초기화)
3. 쓰레드 세이프 
4. volatile 키워드를 사용하지 않아도 됨