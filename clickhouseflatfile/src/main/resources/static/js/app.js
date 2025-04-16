function connectClickHouse(){
	
	const config = {
		host: document.getElementById("host").value,
		port: parseInt(document.getElementById("port").value),
		database: document.getElementById("database").value,
		user: documnet.getElementById("user").value,
		jwtToken: document.getElementById("jwtToken").value		
	};
	
	fetch("/api/clickhouse/connect", {
		method : "POST",
		headers: {
			"Content-Type": "application/jason"
		},
		body: JSON.stringify(config)
	})
	.then(response => response.text())
	.then(data => {
		document.getElementById("status").innerText = "✅ Connected: " + data;
	})
	.catch(error => {
	        document.getElementById("status").innerText = "❌ Error: " + error;
	});
}