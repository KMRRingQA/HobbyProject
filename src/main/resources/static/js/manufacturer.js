
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
        '<button class="button" id="butt1" type="submit">Submit</button>' +
        '</div>'+
        '</div>';
}

function updateCreate() {
    if (document.getElementById("Item_Category").value==="Door"){
        workSpace.innerHTML=
            '<div class="container-table100">'+
            '<div>'+
            '<label for="Manufacturer">Manufacturer:</label>'+
            '<input id="Manufacturer" name="Manufacturer" type="text" />'+
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
            '<button class="button" id="butt1" type="submit">Submit</button>' +
            '</div>'+
            '</div>';
    } else if (document.getElementById("Item_Category").value==="Lift"){
        workSpace.innerHTML=
            '<div class="container-table100">'+
            '<div>'+
            '<label for="Manufacturer">Manufacturer:</label>'+
            '<input id="Manufacturer" name="Manufacturer" type="text" />'+
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
            '<button class="button" id="butt1" type="submit">Submit</button>' +
            '</div>'+
            '</div>';
    } else {
        workSpace.innerHTML=
            '<div class="container-table100">'+
            '<div>'+
            '<label for="Manufacturer">Manufacturer:</label>'+
            '<input id="Manufacturer" name="Manufacturer" type="text" />'+
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
            '<button class="button" id="butt1" type="submit">Submit</button>' +
            '</div>'+
            '</div>';
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

