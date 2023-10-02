# CareTrack System

## Introduction

Welcome to CareTrack, a simple healthcare management system designed to streamline and enhance the healthcare experience. CareTrack empowers healthcare providers and administrators to efficiently manage patient records.

With CareTrack, you can effectively manage and coordinate patient care, ensuring the highest standards of healthcare delivery.

## Features

- **Patient Management**: Easily create and manage patient records, including personal and medical information.

- **Visit Tracking**: Record patient visits, including vital statistics, diagnoses, and notes.

- **Role-Based Access Control**: Ensure data security with role-based access controls for different users, including administrators, healthcare providers, and patients.

- **Location Management**: Maintain and track information about healthcare facilities.

- **User Authentication**: Secure user data with token-based authentication.

- **Responsive User Interface**: Enjoy a user-friendly and responsive web interface for easy navigation.
![Care Track Client](https://github.com/Ogollah/care-track-client.git)

## Getting Started

### Prerequisites

Before running CareTrack, ensure that you have the following prerequisites installed:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Apache Maven](https://maven.apache.org/download.cgi)
- [MySQL Database](https://dev.mysql.com/downloads/installer/)

### Installation

1. Clone this repository to your local machine:

   ```bash
   git clone https://github.com/ogollah/CareTrack.git
   mvn clean install
   java -jar target/caretrack-1.0.0.jar
   create mysql database and run data.sql file in the resources to create tables and dummy users.
   Test the end points through POST MAN or CURL
   
   git clone https://github.com/Ogollah/care-track-client.git
   cd care-track-client
   npm install
   npm start
   Type http://localhost:3000
   ```
   #### Login
   ![User Login](user_login.png)
   
   #### Patients List
   ![Patients](patients_care.png)
   
   #### Patient Profile from the List
   ![Update Patient sub visit](patient_care.png)
   
   #### Patient Registration
   ![Registration](patient_reg_care.png)
   #### Visit
   ![Patient Visit](Visit_care.png)
   
   ### Testing Endpoints With Curl
   ```
    Get Patients: /api/v1/patients
   ~$ curl -X GET -H "Content-Type: application/json" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTY5NjE5NDI3MCwiZXhwIjoxNjk2MTk3ODcwfQ.0ro5NkR_s1pKxzs-7G9_Xmq5VTqShzBncTMS9pDM1Ks9AWavKx7s_KNn5iwasESdz2HjBBKVwO-4neREp1xgUA" http://localhost:8095/api/v1/patients
   [{"id":3,"first_name":"patient","last_name":"patient4","sex":"Male","birth_date":631141200000,"phone":"987","registeredBy":null,"patient_number":"P001","visit":null,"idnumber":0},{"id":4,"first_name":"patient4","last_name":"patient4","sex":"Male","birth_date":631141200000,"phone":"987","registeredBy":null,"patient_number":"P006","visit":null,"idnumber":0},{"id":5,"first_name":"Patient1","last_name":"PatientLast1","sex":"Male","birth_date":1696021200000,"phone":"0987654321","registeredBy":null,"patient_number":"P004","visit":null,"idnumber":0},{"id":6,"first_name":"Patient9","last_name":"PatientLast9","sex":"Female","birth_date":1696021200000,"phone":"0987654321","registeredBy":null,"patient_number":"P0009","visit":null,"idnumber":0},{"id":7,"first_name":"Patient90","last_name":"PatientLast90","sex":"Female","birth_date":1696021200000,"phone":"0987654321","registeredBy":null,"patient_number":"P00090","visit":null,"idnumber":0},{"id":8,"first_name":"Patient905","last_name":"PatientLast905","sex":"Female","birth_date":1696021200000,"phone":"0987654321","registeredBy":null,"patient_number":"P000906","visit":null,"idnumber":0},{"id":9,"first_name":"Patient9059","last_name":"PatientLast9059","sex":"Female","birth_date":1696021200000,"phone":"0987654321","registeredBy":null,"patient_number":"P0009069","visit":null,"idnumber":0},{"id":10,"first_name":"Patient90591","last_name":"PatientLast90591","sex":"Male","birth_date":1696021200000,"phone":"0987654321","registeredBy":{"id":2,"first_name":"Alice","last_name":"Smith","sex":"Female","birth_date":574376400000,"phone":"9876543210","registeredBy":null,"username":"user1","password":"$2a$10$kWWOnNOiToOxcIQ7UJ.cB.XFAflYvMS5BPASR1eqqojc6H9ELWUfC","enabled":true,"roles":[{"id":2,"name":"PROVIDER"}],"location":{"id":2,"name":"Clinic B","longitude":"23.4567","latitude":"45.6789","mfl":"MFL002","user":null},"idnumber":654321},"patient_number":"P00090691","visit":null,"idnumber":0}]
    ```
    ###  Access Swagger Doc through
    ```
    http://localhost:8095/swagger-ui/index.html
    This is after running the code.
    ```

![Swagger](swagger.png)
