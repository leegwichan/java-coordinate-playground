# 연료 주입

### 기능 요구사항
- 우리 회사는 렌터카를 운영하고 있다. 현재 보유하고 있는 차량은 Sonata 2대, Avante 1대, K5 2대로 총 5대의 차량을 보유하고 있다.
- 우리 회사는 고객이 인터넷으로부터 예약할 때 여행할 목적지의 대략적인 이동거리를 입력 받는다. 이 이동거리를 활용해 차량 별로 필요한 연료를 주입한다.

- 차량 별로 주입해야 할 연료량을 확인할 수 있는 보고서를 생성해야 한다.
- 각 차량의 연비는 다음과 같다 
    ```text
    * Sonata : 10km/리터
    * Avante : 15km/리터
    * K5 : 13km/리터
    ```

- return 값 예시
    ```text
    Sonata : 15리터
    K5 : 20리터
    Sonata : 12리터
    Avante : 20리터
    K5 : 30리터
    ```

### 프로그래밍 요구 사항
- 상속과 추상 메소드를 활용한다.
- 위 요구사항을 if/else 절을 쓰지 않고 구현해야 한다.
- 인터페이스를 적용해 구현한다.

### 구현 상세 요구 사항
- Factory method 구현 - RentCompany#create()
- 차를 추가하는 method 구현 - RentCompany#addCar()
- 레포트를 생성하는 method 구현 - RentCompany#generateReport()