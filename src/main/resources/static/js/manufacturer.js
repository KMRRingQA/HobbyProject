
let registerSection = document.getElementById("register")
let registerButton = document.getElementById("RegisterButton")
let createSection = document.getElementById("create_item")
let createButton = document.getElementById("CreateButton")
let readSection = document.getElementById("show_items")
let readButton = document.getElementById("ShowButton")
let updateSection = document.getElementById("update_item")
let updateButton = document.getElementById("UpdateButton")
let deleteSection = document.getElementById("delete_item")
let deleteButton = document.getElementById("DeleteButton")


function register_man(){

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