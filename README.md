# CI-CD-Calculator (Backend)

## 1. Introduction

Backend of a simple calculator application: a REST API that exposes four operations (sum, subtract, multiply, divide). It is built with Java and Spring Boot, and deployed as a Docker container. The frontend calls this API to perform calculations.

**Repository:** [https://github.com/haleluque/CI-CD-Calculator](https://github.com/haleluque/CI-CD-Calculator)

---

## 2. Technology stack

- **Java 17**
- **Spring Boot 3.4** (Maven)
- **Docker** (multi-stage build: Maven + Eclipse Temurin JRE)
- **GitHub Actions** (CI/CD)
- **GitHub Container Registry (GHCR)** – stores the built Docker image
- **Render** – hosts the API; pulls the image from GHCR and runs it

**Live API:** [https://ci-cd-calculator-latest.onrender.com](https://ci-cd-calculator-latest.onrender.com)

---

## 3. CI/CD process

The workflow runs on every **push** and **pull request** to `main` or `master`:

1. **Build** – Checkout, prepare Java/Maven (via reusable action), run `mvn package -DskipTests`, upload the JAR artifact.
2. **Lint** – Depends on build; runs `mvn checkstyle:check` for code style and rules.
3. **Test** – Depends on build; runs `mvn test`.
4. **Security** – Depends on build; runs Trivy (filesystem scan), fails on CRITICAL/HIGH vulnerabilities.
5. **Docker** – Runs only on **push** to `main`/`master`, after build, lint, test, and security pass. Builds the image with Docker Buildx, pushes it to GitHub Container Registry with tags `latest` and the commit SHA.

Render is configured to use the image `ghcr.io/<owner>/ci-cd-calculator:latest`. After a successful push, a manual (or automatic) redeploy on Render pulls the new image and updates the live API.
