# 🍽️ Menu Management Service — CTSE Module Project

This project is developed as part of the **CTSE (Cloud Technologies and Software Engineering)** module to demonstrate **DevOps practices** and the **microservices architecture** using modern cloud infrastructure and CI/CD workflows.

---

## 👨‍💻 Team Members
  - IT21244698 - Thilakasiri P.K.V.C.
  - IT21307430 - Chanthuka U.L.D.
  - IT21238512 - Soysa W.M.Y.
  

---

## 🛠️ Tech Stack

### 🔧 Backend
- **Java 17**
- **Spring Boot**
- **Maven**

### ☁️ Cloud Infrastructure (AWS)
- **ECS (Fargate)** for container orchestration
- **ECR** for storing container images
- **Cognito** for identity and access management
- **Application Load Balancer** for traffic distribution

### 🔁 CI/CD
- **GitHub Actions** for continuous integration and delivery
- **SonarCloud** for static code analysis and quality checks

---

## 🚀 Features

- Containerized microservice for **menu management**
- End-to-end deployment via **CI/CD pipelines**
- Infrastructure-as-code principles using **GitHub Actions**
- Secure authentication and authorization with **AWS Cognito**
- Scalable and highly available service with **ECS + ALB**

---

## 🔄 CI/CD Workflow

- On every `push` or `pull_request` to `master` or `development`:
  - Code is built and tested using Maven
  - Docker image is built and pushed to **Amazon ECR**
  - ECS service is updated with the new image
  - **SonarCloud** analysis is triggered for quality checks

---

## 📦 Project Structure

```text
menu-management-service/
├── src/
├── Dockerfile
├── pom.xml
└── ...
.github/
└── workflows/
    ├── deploy.yml
    └── sonarcloud.yml
```


---

## 📸 CI/CD Workflow
![CI/CD Pipeline](https://github.com/vidura-chathuranga/CTSE-Assignment-01/blob/master/screenshots/overall_diagram.png)


## ✅ How to Run Locally

```bash
# Clone the repo
git clone https://github.com/CTSE-Assignment-01.git

# Navigate to the service
cd menu-management-service

# Build the project
mvn clean install

# Run the service
mvn spring-boot:run
