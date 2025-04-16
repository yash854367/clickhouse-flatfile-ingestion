# 🚀 Bidirectional ClickHouse & Flat File Ingestion Tool

This is a web-based application that enables seamless bidirectional data transfer between a **ClickHouse** database and **Flat Files (CSV)**. It supports JWT-based authentication, user-configurable parameters, column selection, and record-count reporting.

---

## 📌 Features

- 🔄 **Bidirectional Ingestion**
  - ClickHouse ➔ Flat File
  - Flat File ➔ ClickHouse
- 🔐 JWT Token-based ClickHouse authentication
- 📟 Dynamic column selection
- 📊 Record count reporting
- ⚙️ Schema discovery (ClickHouse tables or CSV headers)
- 🤖 Error handling and status messages
- ✅ Bonus: ClickHouse table joins

---

## 💪 Tech Stack

| Component  | Technology         |
|------------|--------------------|
| Backend    | Java (Spring Boot) |
| Frontend   | HTML, CSS, JS      |
| Database   | ClickHouse (Docker-based) |
| Build Tool | Maven              |

---

## 📂 Project Structure

```
├── backend
│   ├── src/main/java/com/ingestion
│   └── pom.xml
├── frontend
│   └── index.html
│   └── style.css
├── prompts.txt
└── README.md
```

---

## ⚙️ Setup & Run Instructions

### 1. Clone the Repo
```bash
git clone https://github.com/yashbaberwal/clickhouse-ingestion-tool.git
cd clickhouse-ingestion-tool
```

### 2. Start ClickHouse (optional, if not using external instance)
```bash
docker run -d --name ch-local -p 8123:8123 -p 9000:9000 clickhouse/clickhouse-server
```

### 3. Backend Setup
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

### 4. Frontend Setup
Just open `frontend/index.html` in a browser.

---

## 💻 Usage Instructions

1. Select Source: ClickHouse or Flat File
2. Enter connection details or upload a CSV file
3. Click “Connect” to fetch schema/columns
4. Select columns you want to ingest
5. Choose target (ClickHouse or File) and start ingestion
6. See the result with record count and status


## 🥪 Testing

- Used ClickHouse sample datasets: `uk_price_paid`, `ontime`
- Verified ingestion both ways
- Tested edge cases: invalid JWT, connection failures, bad files


## 🤖 AI Prompts

All AI prompts used for assistance are saved in `prompts.txt`
