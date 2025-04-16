
function connectClickHouse(){
    const config = {
        host: document.getElementById("host").value,
        port: parseInt(document.getElementById("port").value),
        database: document.getElementById("database").value,
        user: document.getElementById("user").value,
        jwtToken: document.getElementById("jwtToken").value		
    };

    fetch("/api/ingestion/clickhouse/connect", {
        method : "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(config)
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById("status").innerText = `✅ Status: ${data.status} - ${data.message}`;
    })
    .catch(error => {
        document.getElementById("status").innerText = "❌ Error: " + error;
    });
}
