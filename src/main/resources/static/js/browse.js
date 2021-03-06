
const REQ = new XMLHttpRequest();

function getAll(type) {
    REQ.onload = () => {
        if (REQ.status === 200) {
            console.log(REQ.response);
            SortController(REQ.response);
        } else {
            console.log('handle error');
        }
    }
    REQ.open('GET', 'http://localhost:8181/getAll' + type);
    REQ.setRequestHeader('Content-Type', 'Application/json');
    REQ.setRequestHeader('Access-Control-Allow-Origin', '*');
    REQ.responseType = "json";
    REQ.send();
}

function append_json_door(data){
    let table = document.getElementById('table');

    let category = document.getElementById('select_item');
    if (category.value==="Doors" || category.value==="Windows"){
        table.innerHTML=
        '<div class="row header">'+
            '<div class="cell">'+
                'ID'+
            '</div>'+
            '<div class="cell">'+
                'Name'+
            '</div>'+
            '<div class="cell">'+
                'Style'+
            '</div>'+
            '<div class="cell">'+
                'Manufacturer'+
            '</div>'+
            '<div class="cell">'+
                'BWF rating⠀⠀⠀'+
            '</div>'+
            '<div class="cell">'+
                'U⠀Value⠀⠀ (m<sup>2</sup>K)'+
            '</div>'+
            '<div class="cell">'+
                'dimensions⠀⠀ (cm)⠀⠀'+
            '</div>'+
            '<div class="cell">'+
                'price⠀⠀ (£)'+
            '</div>'+
        '</div>'
    } else {
        table.innerHTML=
            '<div class="row header">'+
            '<div class="cell">'+
            'ID'+
            '</div>'+
            '<div class="cell">'+
            'Name'+
            '</div>'+
            '<div class="cell">'+
            'Type'+
            '</div>'+
            '<div class="cell">'+
            'Manufacturer'+
            '</div>'+
            '<div class="cell">'+
            'Carry Capacity⠀⠀ '+
            '</div>'+
            '<div class="cell">'+
            'Max Travel Speed⠀⠀ (m/s)'+
            '</div>'+
            '<div class="cell">'+
            'dimensions⠀⠀ (cm)⠀⠀'+
            '</div>'+
            '<div class="cell">'+
            'price⠀⠀ (£)'+
            '</div>'+
            '</div>'
    }
    data.forEach(function(object) {
        let customFilter = document.getElementById('custom_filter');
        let record = document.createElement('div');
        record.className="row";
        record.id="record";

        if (customFilter.value.split(":")[0]==="Manufacturer"){
            if (customFilter.value.split(":")[1]!==object.manufacturer){
                return;
            }
        }

        if (customFilter.value.split(":")[0]==="MaxPrice"){
            if (customFilter.value.split(":")[1]<object.cost){
                return;
            }
        }

        if (customFilter.value.split(":")[0]==="MinBWF"){
            if (parseInt(customFilter.value.split(":FWD")[1])>parseInt(object.bwf.substring(3,5))){
                return;
            }
        }

        if (customFilter.value.split(":")[0]==="Style"){
            if (customFilter.value.split(":")[1]!==object.description){
                return;
            }
        }

        if (customFilter.value.split(":")[0]==="Type"){
            if (customFilter.value.split(":")[1]!==object.description){
                return;
            }
        }

        if (category.value==="Doors" || category.value==="Windows") {
            record.innerHTML =
                '<div class="cell" data-title="ID">' +
                object.id +
                '</div>' +
                '<div class="cell" data-title="Title">' +
                object.title +
                '</div>' +
                '<div class="cell" data-title="Style">' +
                object.description +
                '</div>' +
                '<div class="cell" data-title="Manufacturer">' +
                object.manufacturer +
                '</div>' +
                '<div class="cell" data-title="BWF">' +
                object.bwf +
                '</div>' +
                '<div class="cell" data-title="UValue">' +
                object.thermalResistance +
                '</div>' +
                '<div class="cell" data-title="Dimensions">' +
                object.dimensions +
                '</div>' +
                '<div class="cell" data-title="Price">' +
                object.cost +
                '</div>'
            table.appendChild(record);
        } else if (category.value==="Lifts") {
            record.innerHTML =
                '<div class="cell" data-title="ID">' +
                object.id +
                '</div>' +
                '<div class="cell" data-title="Name">' +
                object.title +
                '</div>' +
                '<div class="cell" data-title="Style">' +
                object.description +
                '</div>' +
                '<div class="cell" data-title="Manufacturer">' +
                object.manufacturer +
                '</div>' +
                '<div class="cell" data-title="BWF">' +
                object.carryCapacity +
                '</div>' +
                '<div class="cell" data-title="UValue">' +
                object.maxSpeed +
                '</div>' +
                '<div class="cell" data-title="Dimensions">' +
                object.dimensions +
                '</div>' +
                '<div class="cell" data-title="Price">' +
                object.cost +
                '</div>'
            table.appendChild(record);
        }
    });
}

function updateFilters() {
    let category = document.getElementById('select_item');
    if (category.value==="Lifts"){
        document.getElementById("select_sort")[2].innerHTML = "Carry Capacity";
    } else {
        document.getElementById("select_sort")[2].innerHTML ="U-Value";
    }
    console.log(category.value);
}

document.getElementById('select_item').addEventListener("click", updateFilters);
document.addEventListener('DOMContentLoaded', function(){getAll("Doors")});
let buttSearch = document.querySelector('#search');


function Sort(response, type) {
    append_json_door(response.sort(GetSortOrder(type)));
}

function GetSortOrder(prop) {
    return function(a, b) {
        if (a[prop] > b[prop]) {
            return 1;
        } else if (a[prop] < b[prop]) {
            return -1;
        }
        return 0;
    }
}

function Controller() {
    let category = document.getElementById('select_item');
    if (category.value==="Doors"){
        getAll("Doors");
    } else if (category.value==="Windows"){
        getAll("Windows");
    } else {
        getAll("Lifts");
    }
}

function SortController(response){
    let sortBy = document.getElementById('select_sort');
    if (sortBy.value==="U-Value"){
        Sort(response,"thermalResistance")
    }else if (sortBy.value==="Dimensions"){
        Sort(response,"dimensions")
    }else if (sortBy.value==="Price"){
        Sort(response,"cost");
    } else {
        alert.message("error, sort by selection not found.");
    }
}

buttSearch.addEventListener('click', Controller);