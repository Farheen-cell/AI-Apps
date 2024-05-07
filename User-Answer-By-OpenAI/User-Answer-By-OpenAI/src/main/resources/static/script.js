function sendMessage() {
    var userInput = document.getElementById("userInput").value;
    document.getElementById("conversation").innerHTML += "<p><strong>You:</strong> " + userInput + "</p>";
    document.getElementById("userInput").value = "";

    fetch('/api/conversation', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userInput)
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById("conversation").innerHTML += "<p><strong>ChatGPT:</strong> " + data.choices[0].text + "</p>";
    })
    .catch(error => console.error('Error:', error));
}
