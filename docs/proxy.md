# 프록시 패턴

## 기본

실제 객체의 대리자로 작동하여 접근 제어 또는 추가 기능을 제공하는 디자인 패턴

구현 자체는 어댑터 패턴과 매우 유사하다.

프록시 클래스 안에 실제 클래스가 필드로 존재하고, 프록시 클래스가 실제 클래스의 메서드를 호출한다.

예시를 보자

```java
public class ProxyClass {
    
    private RealClass realClass;
    
    public ProxyClass(RealClass realClass) {
        this.realClass = realClass;
    }
    
    public void method() {
        this.realClass.method();
    }
    
}
```

차이점으로는 `ProxyClass`와 `RealClass`의 인터페이스가 동일하다는 것

상속으로도 구현이 가능하지만, 난 상속이 싫으니까 인터페이스로 할 것

아래처럼 `My` 인터페이스를 생성하고,

```java
public interface My {
    void method();
}
```

ProxyClass, RealClass가 My 인터페이스를 구현하게 한다.

```java
public class ProxyClass implements My { ... }

public class RealClass implements My { ... }
```

클라이언트는 RealClass 말고 ProxyClass를 생성한다.

```java
My my = new RealClass();
```

여기서,

```java
My my = new ProxyClass(new RealClass());
```

이렇게 생성한다.

느린 초기화 방식을 이용하여 `new RealClass()`도 없애버릴 수 있다.

```java
public class ProxyClass implements My {

    private RealClass realClass;

    public void method() {
        if (realClass == null) {
            realClass = new RealClass();
        }
        this.realClass.method();
    }
    
}
```

이렇게 만들면,

```java
My my = new ProxyClass();
```

이렇게 `ProxyClass`를 호출할 수 있고, `method()` 메서드를 호출할 때, `RealClass` 객체가 생성된다.

이제 프록시 클래스에서 실제 클래스가 실행하기 전에 필요한 부가적인 기능을 구현하면 된다.

- 접근 제어
- 성능 체크
- 등등

가장 많은 예시인 시간 체크의 경우,

```java
public class ProxyClass implements My {

    private RealClass realClass;

    public void method() {
        if (realClass == null) {
            realClass = new RealClass();
        }
        int startTime = System.currentTimeMillis();
        this.realClass.method();
        int endTime = System.currentTimeMillis();
        System.out.println("실행 시간 = " + (endTime - startTime) + "ms");
    }
    
}
```

RealClass의 코드는 하나도 안 건드리고 부가적인 기능을 추가할 수 있음

맨 위의 프록시 패턴의 정의를 다시 보자

> 실제 객체의 대리자로 작동하여 접근 제어 또는 추가 기능을 제공하는 디자인 패턴

근데 문제가 있다.

RealClass의 메서드가 30개라면? 전부 시간 체크가 필요하다면?

대표적인 AOP에 관련된 내용이다.

이걸 구현하려면 InvocationHandler를 알아야한다.

## [InvocationHandler](https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/InvocationHandler.html)

이게 뭐하는 인터페이스냐? 자바 API에서 작성한 주석을 살펴보자

> InvocationHandler is the interface implemented by the invocation handler of a proxy instance.
> 
> Each proxy instance has an associated invocation handler. When a method is invoked on a proxy instance, the method invocation is encoded and dispatched to the invoke method of its invocation handler.

> InvocationHandler는 프록시 인스턴스의 호출 핸들러에 의해 구현되는 인터페이스입니다.
> 
> 각 프록시 인스턴스에는 연관된 호출 핸들러가 있습니다. 프록시 인스턴스에서 메서드가 호출되면 메서드 호출이 인코딩되어 해당 호출 핸들러의 호출 메서드로 전달됩니다.

뭔 소린지 모르겠다.

일단 패키지가 `java.lang.reflect` 이므로 리플렉션과 연관이 있는것 같다.

![스크린샷 2023-12-27 오후 2.12.23.png](..%2F..%2F..%2F..%2FDesktop%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202023-12-27%20%EC%98%A4%ED%9B%84%202.12.23.png)

구현해야 할 메서드는 `invoke()` 메서드 하나이다.

> Processes a method invocation on a proxy instance and returns the result.

> 프록시 인스턴스에서 메서드 호출을 처리하고 결과를 반환합니다.

계속해서 `프록시 인스턴스` 라는 말이 등장한다.

프록시 패턴이 뭔지 모르는 사람이라도, 스프링을 공부했다면 프록시가 가짜라는 것은 안다.

인스턴스가 객체를 의미하는 것도 알 것이다.

그렇다면, 프록시 인스턴스는 가짜 객체라는 소리일 것이다.

`invoke()` 메서드는 가짜 객체가 호출하는 메서드라는 것이다.

위 예시로 따지면 `ProxyClass`의 `method()`가 `invoke()` 메서드라는 소리이다.

이번에는 매개변수를 살펴보자

- proxy
- method
- args

**proxy**

> the proxy instance that the method was invoked on
> 
> 메소드가 호출된 프록시 인스턴스

가짜 객체 그 자체를 의미하는 것 같다.

**method**

> the Method instance corresponding to the interface method invoked on the proxy instance.
> 
> 프록시 인스턴스에서 호출된 인터페이스 메서드에 해당하는 Method 인스턴스입니다.

가짜 객체에서 호출된 인터페이스 메서드

`인터페이스 메서드` 위의 예시로 따지면 `My` 인터페이스의 `method()`를 의미하는 것 같다.

**args**

> an array of objects containing the values of the arguments passed in the method invocation on the proxy instance, or null if interface method takes no arguments.
> 
> 프록시 인스턴스의 메소드 호출에 전달된 인수 값을 포함하는 객체 배열이거나, 인터페이스 메소드가 인수를 사용하지 않는 경우 null입니다.

인터페이스에 선언된 매개변수를 의미하는 것 같다.

그래서 구현은 이렇게 할 수 있겠다.

```java
public class TimingInvocationHandler implements InvocationHandler {

    private final Object target;

    public TimingInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = method.invoke(target, args);
        long endTime = System.currentTimeMillis();
        System.out.println(method.getName() + " 메소드 걸린 시간: " + (endTime - startTime) + "ms");
        return result;
    }
    
}
```

여기서 `target`은 `My` 인터페이스를 의미한다.

솔직히 어찌저찌 참고하면서 만들긴 했는데, 아직까지 이해가 잘 가지는 않음

그다음 이 핸들러를 이용할 가짜 객체(프록시 인스턴스)를 생성하면 된다.

## [Proxy Instance](https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Proxy.html)

가짜 객체는 어떻게 만드느냐?

`Proxy` 클래스에서 스태틱으로 제공하는 `newProxyInstance()` 메서드를 이용하면 된다.

![스크린샷 2023-12-27 오후 2.39.28.png](..%2F..%2F..%2F..%2FDesktop%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202023-12-27%20%EC%98%A4%ED%9B%84%202.39.28.png)

- loader
- interfaces
- h 

이건 이해하기가 좀 쉬운편이다.

**loader**

클래스 로더를 의미한다.

자바에는 부트 스트랩, 익스텐션, 애플리케이션의 세 종류의 클래스 로더가 있는데,

당연히 애플리케이션 클래스 로더이다.

`My.class.getClassLoader()`를 이용하면 자신의 클래스 로더를 호출할 수 있다.

**interfaces**

가짜 객체가 구현해야 할 인터페이스 배열이다.

여러 개 구현할 수도 있어서 배열로 만들었나보다.

`new Class<?>[]{My.class}` 이러면 된다.

**h**

방금 만들었던 핸들러이다.

`InvocationHandler handler = new TimingInvocationHandler();`

근데 타겟이 필요하니까 `RealClass`의 객체가 필요하다.

`InvocationHandler handler = new TimingInvocationHandler(new RealClass());`

그래서 이 모든걸 종합해서 가짜 객체를 만들려면?

```java
InvocationHandler handler = new TimingInvocationHandler(new RealClass());

My proxy = (My) Proxy.newProxyInstance(
        My.class.getClassLoader(),
        new Class<?>[]{My.class},
        handler);
```

이러면 `proxy` 객체를 이용하여 메서드를 호출할 때 마다, `InvocationHandler`의 `invoke()` 메서드가 실행된다.

따라서 시간 측정하는 코드가 실행이 됨

## 결론

여기서 응용해 보라고 하면 진짜 또 하루종일 쳐다보고 있어야 될 것 같다.

리플렉션과 관련된 내용은 너무 난해한 것 같음

아무튼, Spring AOP도 이러한 원리로 돌아가는 것으로 알고 있는데,

불쌍한 개발자들이 이걸 다 치고있는게 안쓰러워서 그런지 애노테이션 기반 추상화를 제공한다.

Spring AOP를 공부하다가 프록시 패턴을 알아야 될 것 같아서 알아봤다.

끗