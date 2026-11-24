# Enterprise REST Assured API Test Automation Framework

Architected for **SmartHR (TechVG HRMS)** following 5+ years QA Automation standards using **Java 17, REST Assured, Jackson, and TestNG**.

---

## 1. Architectural Highlights (Senior QA Design)

* **Service Object Model (Client Layer)**: Decouples HTTP specifications (`GET`, `POST`, `PUT`, `PATCH`, `DELETE`) from test classes.
* **Central Token Management (`TokenManager`)**: Thread-safe authentication token lifecycle management with automatic caching and retry capability.
* **Specification Builder (`SpecFactory`)**: Centralized `RequestSpecification` and `ResponseSpecification` enforcing standard headers, base URI, relaxed HTTPS validation, and Bearer authorization injection.
* **Externalized Config (`ConfigurationManager`)**: Configuration driven via `config.properties` with support for CLI runtime overrides (`-Dbase.uri=...`).
* **Strong DTO / POJO Modeling**: Strictly typed Jackson objects (`DepartmentDTO`, `DesignationDTO`, `EmployeeDTO`, `CompanyDTO`, `LoginRequest`, `LoginResponse`) with `@JsonInclude(NON_NULL)`.
* **Dynamic Test Data Generation (`TestDataGenerator`)**: Generates unique, non-colliding names, emails, and dates for every test run to guarantee zero unique-constraint conflicts.
* **Multi-Tenant Security Testing (BOLA / IDOR)**: Dedicated security test suites verifying cross-company isolation and access-control enforcement.

---

## 2. Framework Directory Structure

```
API Testing_RestAssured/
├── pom.xml                                           # Maven dependencies & plugins
├── testng.xml                                        # Master test suite configuration
├── api_docs/
│   └── API_HRMS.txt                                  # Full OpenAPI 3.0.1 Specification (381 endpoints)
├── src/
│   ├── main/
│   │   ├── java/com/api/
│   │   │   ├── auth/
│   │   │   │   └── TokenManager.java                 # Thread-safe JWT token caching & auto-login
│   │   │   ├── clients/                              # Service Object Clients (Encapsulating REST calls)
│   │   │   │   ├── BaseClient.java
│   │   │   │   ├── DepartmentClient.java
│   │   │   │   ├── DesignationClient.java
│   │   │   │   ├── EmployeeClient.java
│   │   │   │   ├── CompanyClient.java
│   │   │   │   └── LeaveClient.java
│   │   │   ├── config/
│   │   │   │   └── ConfigurationManager.java         # Reads properties & JVM parameters
│   │   │   ├── constants/
│   │   │   │   └── Endpoints.java                    # Centralized API URI routes
│   │   │   ├── models/                               # Strongly typed request/response DTOs
│   │   │   │   ├── auth/ (LoginRequest, LoginResponse)
│   │   │   │   ├── core/ (CompanyDTO, DepartmentDTO, DesignationDTO, EmployeeDTO)
│   │   │   │   ├── leave/ (LeaveTypeDTO)
│   │   │   │   └── asset/ (AssetTypeDTO)
│   │   │   ├── specs/
│   │   │   │   └── SpecFactory.java                  # Request & Response specs
│   │   │   └── utils/
│   │   │       └── TestDataGenerator.java            # Dynamic non-colliding test data factory
│   │   └── resources/
│   │       └── config.properties                     # Environment URLs, test credentials & IDs
│   └── test/
│       └── java/com/api/tests/
│           ├── base/
│           │   └── BaseTest.java                     # Global suite setup, token verification & helpers
│           ├── SmartHrAuthTest.java                  # Live auth, token introspection & authorities tests
│           ├── core/
│           │   ├── DepartmentTests.java              # CRUD, Boundary, Count & Schema validation
│           │   └── DesignationTests.java             # Linked Department-Designation lifecycle tests
│           └── security/
│               └── TenantIsolationTests.java         # Cross-company BOLA & Auth enforcement tests
```

---

## 3. Test Suites Covered

| Suite | Focus Areas | Key Test Cases Covered |
| :--- | :--- | :--- |
| **`SmartHrAuthTest`** | Authentication & Token Verification | Valid login (`200 OK`), Invalid login (`401`), `/api/authorities`, `/api/authenticate` |
| **`DepartmentTests`** | Core HR - Department Management | `POST` (create), `GET` by ID, `PUT` (full update), `PATCH` (status), `DELETE`, Count, 404 validation |
| **`DesignationTests`** | Core HR - Role & Designation | Create linked to Department, Get by ID, Update, List, Count, Delete, Referential Cleanup |
| **`TenantIsolationTests`** | Security & Compliance (BOLA / IDOR) | Cross-company creation prevention, Unauthenticated 401 checks, Tampered token rejection |

---

## 4. How to Execute Tests

### In IntelliJ IDEA / Eclipse:
* Right-click **`testng.xml`** -> **Run 'testng.xml'**
* Or open any individual test class (e.g. `DepartmentTests.java`, `SmartHrAuthTest.java`) and click the **green Play button (`▶`)**.
