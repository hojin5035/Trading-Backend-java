# 📈 Trading System Backend (Java/Spring Boot)
> 트레이딩 엔진의 매매 데이터를 체계적으로 관리하는 전용 백엔드 서버입니다.

## 🛠️ Tech Stack
* **Language**: Java 17
* **Framework**: Spring Boot 3.x
* **Database**: Spring Data JPA (MySQL/PostgreSQL)
* **Library**: Lombok

## 🌟 Key Features

### 1. 초기 데이터 적재 (Initial Data Loading)
* 서버 기동 시 `CommandLineRunner`를 호출하여 `data/trade_log.csv`의 기존 데이터를 DB로 일괄 적재합니다.

### 2. 스케줄링 기반 자동 동기화 (Scheduled Sync)
* `@Scheduled`를 활용하여 **5분 주기**로 CSV 파일을 스캔하고 새로운 매매 로그를 DB에 반영합니다.

### 3. 데이터 무결성 및 중복 방지 (Data Integrity)
* `timestamp`와 `symbol`을 기준으로 중복 여부를 체크하여 동일한 로그가 중복 저장되지 않도록 방어 로직을 수행합니다.

## 📂 Data Structure
* **Timestamp**: 매매 발생 시간
* **Symbol**: 거래 종목 (예: BTC/USDT)
* **Type**: 매수/매도 구분
* **Price**: 체결 가격
* **Profit Rate**: 해당 거래의 수익률
* **Reason**: 진입/청산 사유

## 🚀 How to Run
1. `application.properties`에서 DB 연결 정보를 설정합니다.
2. `data/trade_log.csv` 경로에 로그 파일이 있는지 확인합니다.
3. 어플리케이션을 실행합니다.
   ```bash
   ./gradlew bootRun
