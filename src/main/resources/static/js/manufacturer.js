const REQ = new XMLHttpRequest();

let registerButton = document.getElementById("RegisterButton")
let createButton = document.getElementById("CreateButton")
let showButton = document.getElementById("ShowButton")
let updateButton = document.getElementById("UpdateButton")
let deleteButton = document.getElementById("DeleteButton")

let workSpace = document.getElementById("window")

function register_man(){
    workSpace.innerHTML=
        '<div class="container-table100">'+
        '<div>'+
        '<legend>Register a manufacturer</legend>'+
        '<label for="username">Name:</label>'+
        '<input id="username" name="username" type="text" />'+
        '<label for="email">E-mail:</label>'+
        '<input id="email" name="email" type="email" />'+
        '<label for="password">Password:</label>'+
        '<input id="password" name="password" type="password" />'+
        '<br>'+
        '<button class="button" id="registerButton" type="submit">Submit</button>' +
        '</div>'+
        '</div>';
    document.getElementById("registerButton").addEventListener("click",function(){create("manufacturer")});
}

function create(type) {
    let Data;
    if (type === "door") {
        Data = `{"title" : "${document.getElementById("modelName").value}","description": "${document.getElementById("type").value}","bwf": "${document.getElementById("bwf").value}", "thermalResistance": "${document.getElementById("thermal").value}", "dimensions": "${document.getElementById("dimensions").value}", "cost": "${document.getElementById("cost").value}", "manufacturer":{"id":"${document.getElementById("manufacturerID").value}"}}`;
        REQ.open('POST', 'http://localhost:8181/createDoor');
    } else if (type === "manufacturer"){
        Data = `{"name" : "${document.getElementById("username").value}","email": "${document.getElementById("email").value}","password": "${document.getElementById("password").value}"}`;
        REQ.open('POST', 'http://localhost:8181/createManufacturer');
    } else if (type === "window"){
        Data = `{"title" : "${document.getElementById("modelName").value}","description": "${document.getElementById("type").value}","bwf": "${document.getElementById("bwf").value}", "thermalResistance": "${document.getElementById("thermal").value}", "dimensions": "${document.getElementById("dimensions").value}", "cost": "${document.getElementById("cost").value}", "manufacturer":{"id":"${document.getElementById("manufacturerID").value}"}}`;
        console.log(Data);
        REQ.open('POST', 'http://localhost:8181/createWindow');
    } else if (type === "lift"){
        Data = `{"title" : "${document.getElementById("modelName").value}","description": "${document.getElementById("type").value}","carryCapacity": "${document.getElementById("carry_capacity").value}", "maxSpeed": "${document.getElementById("maxVel").value}", "dimensions": "${document.getElementById("dimensions").value}", "cost": "${document.getElementById("cost").value}", "manufacturer":{"id":"${document.getElementById("manufacturerID").value}"}}`;
        REQ.open('POST', 'http://localhost:8181/createLift');
    } else {
        console.log('handle error');
    }
    REQ.setRequestHeader('Content-Type', 'Application/json');
    REQ.setRequestHeader('Access-Control-Allow-Origin', '*');
    REQ.responseType = "json";
    REQ.onload = () => {
        if (REQ.status === 201) {
            console.log(REQ.response);
            console.log(`${type} created`);
        } else {
            console.log('handle error');
        }
    }
    REQ.send(Data);
}


function updateCreate() {
    if (document.getElementById("Item_Category").value==="Door"){
        workSpace.innerHTML=
            '<div class="container-table100">'+
            '<div>'+
            '<label for="Manufacturer">Manufacturer ID:</label>'+
            '<input id="manufacturerID" name="Manufacturer" type="text" />'+
            '<label for="password">Password:</label>'+
            '<input id="password" name="password" type="password" />'+
            '<label for="modelName">Model Name:</label>'+
            '<input id="modelName" name="modelName" type="text" />'+
            '<label for="type">Door Style:</label>'+
            '<input id="type" name="type" type="text" />'+
            '<label for="bwf">BWF Rating: (FWD)</label>'+
            '<input id="bwf" name="bwf" type="text" />'+
            '<label for="thermal">U-Value:</label>'+
            '<input id="thermal" name="thermal" type="text" />'+
            '<label for="dimensions">Dimensions:</label>'+
            '<input id="dimensions" name="dimensions" type="text" />'+
            '<label for="cost">Price: (£)</label>'+
            '<input id="cost" name="cost" type="text" />'+
            '<br>'+
            '<button class="button" id="createDoorButton" type="submit">Submit</button>' +
            '</div>'+
            '</div>';
        document.getElementById("createDoorButton").addEventListener("click",function(){create("door")});
    } else if (document.getElementById("Item_Category").value==="Lift"){
        workSpace.innerHTML=
            '<div class="container-table100">'+
            '<div>'+
            '<label for="Manufacturer">Manufacturer ID:</label>'+
            '<input id="manufacturerID" name="Manufacturer" type="text" />'+
            '<label for="password">Password:</label>'+
            '<input id="password" name="password" type="password" />'+
            '<label for="modelName">Model Name:</label>'+
            '<input id="modelName" name="modelName" type="text" />'+
            '<label for="type">Lift Type:</label>'+
            '<input id="type" name="type" type="text" />'+
            '<label for="carry_capacity">Carry Capacity</label>'+
            '<input id="carry_capacity" name="carry_capacity" type="text" />'+
            '<label for="maxVel">Max Velocity:</label>'+
            '<input id="maxVel" name="maxVel" type="text" />'+
            '<label for="dimensions">Car Dimensions:</label>'+
            '<input id="dimensions" name="dimensions" type="text" />'+
            '<label for="cost">Price: (£)</label>'+
            '<input id="cost" name="cost" type="text" />'+
            '<br>'+
            '<button class="button" id="createLiftButton" type="submit">Submit</button>' +
            '</div>'+
            '</div>';
        document.getElementById("createLiftButton").addEventListener("click",function(){create("lift")});
    } else {
        workSpace.innerHTML=
            '<div class="container-table100">'+
            '<div>'+
            '<label for="Manufacturer">Manufacturer ID:</label>'+
            '<input id="manufacturerID" name="Manufacturer" type="text" />'+
            '<label for="password">Password:</label>'+
            '<input id="password" name="password" type="password" />'+
            '<label for="modelName">Model Name:</label>'+
            '<input id="modelName" name="modelName" type="text" />'+
            '<label for="type">Window Type:</label>'+
            '<input id="type" name="type" type="text" />'+
            '<label for="bwf">BWF Rating: (FWD)</label>'+
            '<input id="bwf" name="bwf" type="text" />'+
            '<label for="thermal">U-Value:</label>'+
            '<input id="thermal" name="thermal" type="text" />'+
            '<label for="dimensions">Dimensions:</label>'+
            '<input id="dimensions" name="dimensions" type="text" />'+
            '<label for="cost">Price: (£)</label>'+
            '<input id="cost" name="cost" type="text" />'+
            '<br>'+
            '<button class="button" id="createWindowButton" type="submit">Submit</button>' +
            '</div>'+
            '</div>';
        document.getElementById("createWindowButton").addEventListener("click",function(){create("window")});
    }
}

function create_item() {
    workSpace.innerHTML =
        '<div class="container-table100">'+
        '<div>' +
        '<select id="Item_Category" name="Item_Category">' +
        '<option value="Select Item">Select Item</option>' +
        '<option value="Window">Window</option>' +
        '<option value="Door">Door</option>' +
        '<option value="Lift">Lift</option>' +
        '</select>'+
        '</div>';
    document.getElementById("Item_Category").addEventListener("change", updateCreate)
}


function update_item(){

}


function delete_item(){
    workSpace.innerHTML =
        '<div class="container-table100">'+
        '<div>' +
        '<select id="Item_Category" name="Item_Category">' +
        '<option value="Select Item">Select Item Category</option>' +
        '<option value="Window">Window</option>' +
        '<option value="Door">Door</option>' +
        '<option value="Lift">Lift</option>' +
        '</select>'+
        '<label for="Manufacturer">Manufacturer:</label>'+
        '<input id="Manufacturer" name="Manufacturer" type="text" />'+
        '<label for="password">Password:</label>'+
        '<input id="password" name="password" type="password" />'+
        '<label for="ID">Model ID:</label>'+
        '<input id="ID" name="ID" type="text" />'+
        '<br>'+
        '<button class="button" id="butt1" type="submit">Submit</button>' +
        '</div>'+
        '<div>';
}

function show_item() {
    window.location.href = "http://localhost:8181/browse.html";
}

registerButton.addEventListener('click', register_man);
createButton.addEventListener('click', create_item);
showButton.addEventListener('click', show_item);
updateButton.addEventListener('click', update_item);
deleteButton.addEventListener('click', delete_item);

