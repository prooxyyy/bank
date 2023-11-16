# Bank

### **Bank - simple banking service, for card creation, transactions, registration and authorization.**

It has nothing to do with real money, here you can understand how the banking system works in a very simple format.
This repository is only **! FOR EDUCATIONAL PURPOSES !**

### Technologies:
* Spring
* Spring Security
* Java
* MariaDB
* JWT
* Lombok
* Docker

## How to run?
1. Clone project: `git clone https://github.com/prooxyyy/bank.git`
2. Open project in Intellij IDEA
3. Install latest version of [Docker](https://www.docker.com/)
4. Configure database credentials in `docker-compose.yml`
5. Run the project via `docker compose up -d` (Or build it to .jar and run it via `java -jar app.jar` REQUIRE SET VARIABLES)
6. Insert 2 roles into `bank_roles` table: `ROLE_USER`, `ROLE_ADMIN`

## API
1. Download [Postman](https://www.postman.com/)
2. Download Postman collection [CLICK HERE](https://nashvpn.s3.eu-central-1.amazonaws.com/Bank.postman_collection.json)
3. Import collection in postman
- Collection last update: **17.11.2023**

## Features
- User
  - [x] Auth
  - [x] Registration
- Card
  - [x] Open new card
  - [x] Get active cards
  - [ ] Close card
  - [ ] Change CVV
  - [ ] Change PIN
  - [ ] Reissue card
- Payments
  - [x] Transfer money by card number
  - [ ] Transfer money by email
  - [ ] Transfer money by phone number
  - [ ] Auto-convert currency while transferring money
  - [ ] Pay invoices
  - [ ] Jar with donation
- Admin features (ROLE_ADMIN needed)
    - [x] Cancel transaction
    - [ ] Metrics
    - [ ] Block user card
- Web
    - [ ] User interface in WEB
    - [ ] Admin interface in WEB


### Contact me
  - [Telegram](https://t.me/proo0xy)
