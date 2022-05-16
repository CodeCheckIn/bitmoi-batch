## BitMoi - 시세조회(Quotation)

---

### 기본 설명
- Bithumb API 에서 제공해주는 Ticker 정보를 이용한다.
- DB에서 각 코인의 가장 최신의 가격 정보 만을 가지고 있게 한다.
- 기존 가격과 비교하여 변화가 확인되면 해당 정보를 이용하여 이벤트를 발생시킨다.
  - Kafka Producer
  - Consume 대상은 체결앱
  

### 동작 방식
- 시세 조회의 동작은 2가지 방식으로 가능하다.
  1. 스케쥴러 방식을 이용한 주기적인 자동 실행
     - 현재 10초 주기
  2. API 호출을 이용한 수동 실행
  
--- 

![Quotation Process](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fff90b247-1e98-40b7-a004-8da9c7af9a54%2FUntitled.png?table=block&id=94854612-2cb1-44cd-8b03-5bacadd2fa74&spaceId=f49cbcf2-ae65-4405-b9a1-b06afbcdf1e0&width=1730&userId=9a2ff310-14d3-432a-91ce-df33b3f0e65f&cache=v2)



