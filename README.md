# 🚀 Nishad API Automation Hub

Welcome to the **Nishad API Automation Hub**! This repository is a polyglot showcase of modern API testing strategies, featuring both **Rest Assured (Java-heavy)** and **Karate (BDD-style)** frameworks.

---

## 🏛️ Repository Structure

| Folder | Framework | Approach | Key Features |
| :--- | :--- | :--- | :--- |
| `rest-assured-pro/` | Rest Assured | Professional Java/POJO | Serialization, Base Architecture, Extent Reports |
| `karate-framework/` | Karate DSL | Gherkin/BDD | Low-code, Native JSON handling, Fast execution |

---

## 🛠️ Rest Assured Framework (Java)
Located in `rest-assured-pro/`. 

### **Key Concepts Covered:**
- **Serialization & Deserialization**: Using POJOs with Jackson.
- **Request Chaining**: Passing dynamic IDs between API calls.
- **Data-Driven Testing**: Using TestNG `@DataProvider`.
- **Dynamic Config**: Managed via `config.properties`.

### **How to Run:**
```bash
cd rest-assured-pro
mvn test
```

---

## 🥋 Karate Framework (BDD)
Located in `karate-framework/`.

### **Key Concepts Covered:**
- **Zero Boilerplate**: No Java code needed for test logic.
- **Native JSON**: Use raw JSON directly in `.feature` files.
- **Environment Management**: Managed via `karate-config.js`.

### **How to Run:**
```bash
cd karate-framework
mvn test
```

---

## 📊 Study Notes & Interview Q&A
For a detailed breakdown of all concepts, technical architecture diagrams (Mermaid), and interview preparation, refer to the [STUDY_NOTES.md](rest-assured-pro/STUDY_NOTES.md).

---

### **Contact & Support**
Developed by **Nishad IT Solutions**.
"Empowering the next generation of Automation Experts." 🚀🏾
