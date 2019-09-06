window.onload = function() {
    
    document.getElementById("countBtn").onclick = function() {
        fetchUrl("https://andreasvikke.dk/MovieDevops/api/movie/count", "count");
    }
}


function createTable(a) {
    const myNode = document.getElementById("table");
    while (myNode.firstChild) {
        myNode.removeChild(myNode.firstChild);
    }

    var header = document.getElementById("table").createTHead();
    var headRow = header.insertRow(0);
    for(i = 0; i < Object.keys(a[0]).length; i++) {
        var cell =document.createElement("th");
        cell.innerHTML = Object.keys(a[0])[i];
        headRow.appendChild(cell);
    }

    var body = document.getElementById("table").createTFoot();
    for(i = 0; i < a.length; i++) {
        var row = body.insertRow(body.rows.length);
        for(y = 0; y < Object.keys(a[0]).length; y++){
            row.insertCell(body.rows[i].cells.length).innerHTML = a[i][Object.keys(a[0])[y]];;
        }
    }
}

function fetchUrl(url, type) {
    fetch(url)
    .then(res => res.json())
    .then(data => {
        switch(type) {
            case "count":
                document.getElementById("output").innerHTML = "Count: " + data;
            case "all":
                createTable(data);
                break;
        } 
        
    })
}