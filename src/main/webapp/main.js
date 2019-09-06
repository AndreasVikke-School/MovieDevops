window.onload = function() {
    
    document.getElementById("countBtn").onclick = function() {
        fetchUrl("https://andreasvikke.dk/MovieDevops/api/movie/count", "count");
    }

    document.getElementById("allBtn").onclick = function() {
        fetchUrl("https://andreasvikke.dk/MovieDevops/api/movie/all", "all");
    }

    document.getElementById("idBtn").onclick = function() {
        fetchUrl("https://andreasvikke.dk/MovieDevops/api/movie/" + document.getElementById("inputField").value, "id");
    }

    document.getElementById("nameBtn").onclick = function() {
        fetchUrl("https://andreasvikke.dk/MovieDevops/api/movie/name/" + document.getElementById("inputField").value, "name");
    }
}


function createTable(a, single) {
    const myNode = document.getElementById("table");
    while (myNode.firstChild) {
        myNode.removeChild(myNode.firstChild);
    }

    var header = document.getElementById("table").createTHead();
    var headRow = header.insertRow(0);
    for(i = 0; i < Object.keys(single ? a : a[0]).length; i++) {
        var cell = document.createElement("th");
        cell.innerHTML = Object.keys(single ? a : a[0])[i];
        headRow.appendChild(cell);
    }

    var body = document.getElementById("table").createTFoot();
    for(i = 0; i < single ? 1 : a.length; i++) {
        var row = body.insertRow(body.rows.length);
        for(y = 0; y < Object.keys(single ? a : a[0]).length; y++){
            row.insertCell(body.rows[i].cells.length).innerHTML = single ? a[Object.keys(a)[y]] : a[i][Object.keys(a[0])[y]];
        }
    }
}

function fetchUrl(url, type) {
    fetch(url)
    .then(res => res.json())
    .then(data => {
        switch(type) {
            case "count":
                document.getElementById("output").innerHTML = "Count: " + data.count;
                break;
            case "all":
                createTable(data, false);
                break;
            case "name":
                createTable(data, false);
                break;
            default:
                createTable(data, true);
                break;
        } 
        
    })
}