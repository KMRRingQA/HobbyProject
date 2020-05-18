
let registerButton = document.getElementById("RegisterButton")
let createButton = document.getElementById("CreateButton")
let readButton = document.getElementById("ShowButton")
let updateButton = document.getElementById("UpdateButton")
let deleteButton = document.getElementById("DeleteButton")

let workSpace = document.getElementById("window")

function register_man(){
    workSpace.innerHTML=
    '<div>'+
        '<legend>Register a manufacturer</legend>'+
        '<label for="username">Username:</label>'+
        '<input id="username" name="username" type="text" />'+
            '<br>'+
            '<label for="email">E-mail:</label>'+
        '<input id="email" name="email" type="email" />'+
            '<br>'+
            '<label for="password">Password:</label>'+
        '<input id="password" name="password" type="password" />'+
            '<br>'+
            '<button class="button" id="butt1" type="submit">Submit</button>'+
    '</div>'
}
function create_item(){
    hideAll();
    createSection.style.visibility = "visible";
}
function read_items(){
    hideAll();
    readSection.style.visibility = "visible";
}
function update_item(){
    hideAll();
    updateSection.style.visibility = "visible";
}
function delete_item(){
    hideAll();
    deleteSection.style.visibility = "visible";
}


registerButton.addEventListener('click', register_man);
createButton.addEventListener('click', create_item);
readButton.addEventListener('click', read_items);
updateButton.addEventListener('click', update_item);
deleteButton.addEventListener('click', delete_item);