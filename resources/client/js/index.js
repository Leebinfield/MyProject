function pageLoad() {

    let fruitsHTML = '<table>' +
        '<tr>' +
        '<th>Id</th>' +
        '<th>Name</th>' +
        '<th>Image</th>' +
        '<th>Colour</th>' +
        '<th>Size</th>' +
        '<th class="last">Options</th>' +
        '</tr>';

    fetch('/Restaurant/listRest', {method: 'get'}
    ).then(response => response.json())

}

function editFruit(event) {

    const id = event.target.getAttribute("data-id");

    if (id === null) {

        document.getElementById("editHeading").innerHTML = 'Add new fruit:';

        document.getElementById("fruitId").value = '';
        document.getElementById("fruitName").value = '';
        document.getElementById("fruitImage").value = '';
        document.getElementById("fruitColour").value = '';
        document.getElementById("fruitSize").value = '';

        document.getElementById("listDiv").style.display = 'none';
        document.getElementById("editDiv").style.display = 'block';

    } else {

        fetch('/fruit/get/' + id, {method: 'get'}
        ).then(response => response.json()
        ).then(fruit => {

            if (fruit.hasOwnProperty('error')) {
                alert(fruit.error);
            } else {

                document.getElementById("editHeading").innerHTML = 'Editing ' + fruit.name + ':';

                document.getElementById("fruitId").value = id;
                document.getElementById("fruitName").value = fruit.name;
                document.getElementById("fruitImage").value = fruit.image;
                document.getElementById("fruitColour").value = fruit.colour;
                document.getElementById("fruitSize").value = fruit.size;

                document.getElementById("listDiv").style.display = 'none';
                document.getElementById("editDiv").style.display = 'block';

            }

        });

    }

}

function saveEditFruit(event) {

    event.preventDefault();

    if (document.getElementById("fruitName").value.trim() === '') {
        alert("Please provide a fruit name.");
        return;
    }

    if (document.getElementById("fruitImage").value.trim() === '') {
        alert("Please provide a fruit image.");
        return;
    }

    if (document.getElementById("fruitColour").value.trim() === '') {
        alert("Please provide a fruit colour.");
        return;
    }

    if (document.getElementById("fruitSize").value.trim() === '') {
        alert("Please provide a fruit size.");
        return;
    }

    const id = document.getElementById("fruitId").value;
    const form = document.getElementById("fruitForm");
    const formData = new FormData(form);

    let apiPath = '';
    if (id === '') {
        apiPath = '/fruit/new';
    } else {
        apiPath = '/fruit/update';
    }

    fetch(apiPath, {method: 'post', body: formData}
    ).then(response => response.json()
    ).then(responseData => {

        if (responseData.hasOwnProperty('error')) {
            alert(responseData.error);
        } else {
            document.getElementById("listDiv").style.display = 'block';
            document.getElementById("editDiv").style.display = 'none';
            pageLoad();
        }
    });

}

function cancelEditFruit(event) {

    event.preventDefault();

    document.getElementById("listDiv").style.display = 'block';
    document.getElementById("editDiv").style.display = 'none';

}


function deleteFruit(event) {

    const ok = confirm("Are you sure?");

    if (ok === true) {

        let id = event.target.getAttribute("data-id");
        let formData = new FormData();
        formData.append("id", id);

        fetch('/fruit/delete', {method: 'post', body: formData}
        ).then(response => response.json()
        ).then(responseData => {

                if (responseData.hasOwnProperty('error')) {
                    alert(responseData.error);
                } else {
                    pageLoad();
                }
            }
        );
    }

}