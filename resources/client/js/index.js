function pageLoad() {

    let leeR = `<table>` +
        `<tr>` +
        `<th>Id</th>` +
        `<th>Name</th>` +
        `<th>Image</th>` +
        `<th>Location</th>` +
        `<th>price</th>` +
        `<th class="Last">Options</th>` +
        `</tr>`;

    fetch('/Restaurant/listRest', {method: 'get'}
    ).then(response => response.json()
    ).then(restaurants => {
        for (let restaurant of restaurants) {
            leeR += '<tr>' +
                `<td>${restaurant.RestID}</td>` +
                `<td>${restaurant.Rname}</td>` +
                `<td>${postcode}</td>` +
                `<td class= "last">` +
                `<button class= "editButton" data-id = "${Restaurant.RestID}">EDIT </button>` +
                `<button class="deleteButton" data-id="${Restaurant.RestID}">Delete</button>` +
                `</td>` +
                `</tr>`;
        }
        leeR += `</table>`;
        document.getElementById("ListRest").innerHTML = leeR;
        let editButtons = document.getElementsByClassName("editButton");
        for (let button of editButtons) {
            button.addEventListener("click", editRest);
        }
        let deleteButtons = document.getElementsByClassName("deleteButton");
        for (let button of deleteButtons) {
            button.addEventListener("click", deleteRest);
        }
    });
    document.getElementById("saveButton").addEventListener("click", saveEditRest);
    document.getElementById("cancelButton").addEventListener("click", cancelEditRest);
}
function editRest(event){
    const id = event.target.getAttribute("data-id");
    if (id === null){
        document.getElementById("EditHeading").innerHTML = 'add new restaurant';
        document.getElementById("RestID").value='';
        document.getElementById("Rname").value='';
        document.getElementById("postcode").value='';

        document.getElementById("lastDiv").style.display = 'none';
        document.getElementById("editDiv").style.display = 'block';

    }else{
        fetch('/Restaurant/get/'+ RestID, {method:'get'}
        ).then(response => response.json()
        ).then(restaurant => {
            if(restaurant.hasOwnProperty('error')){
                alert(restaurant.error);
            }else{
                document.getElementById("EditHeading").innerHTML = 'Editing'+restaurant.name +':';
                document.getElementById("RestID").value=restaurant.name;
                document.getElementById("Rname").value=restaurant.id;
                document.getElementById("postcode").value=restaurant.post;

                document.getElementById("lastDiv").style.display = 'none';
                document.getElementById("editDiv").style.display = 'block';
            }
        }
        )
    }
}



