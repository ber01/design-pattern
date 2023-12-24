# 템플릿 메서드 패턴

## 정리

- 상위 클래스에서 처리의 골격을 만든다. 하위 클래스에서 처리의 내용을 구체화 한다.

- 상위 클래스에서 메서드의 실행 순서를 정의한다. 하위 클래스에서 구체적인 구현을 만든다.

## 예시

### InputStream

`InputStream`에서 템플릿 메서드 패턴을 사용한다.

![](https://github.com/ber01/refactoring-2nd/assets/28918801/c354f808-e809-45e1-aa75-9da9c6326e47)

### read()

`read()` 메서드는 추상 메서드이다.

![](https://github.com/ber01/refactoring-2nd/assets/28918801/317a4c9e-416e-4e9b-ad18-3475386197d8)

### read(byte b[], int off, int len)

`read(byte b[], int off int len)` 메서드는 템플릿 메서드이다. 

![](https://github.com/ber01/refactoring-2nd/assets/28918801/2f397970-9f57-45fb-b03e-137f1dad6e23)

추상 메서드 `int read()`의 구현은 하위 클래스에게 맡긴다.

