function pageload(){
    let restaurantHTML = '<table>'+
        '<tr>' +
        '<th>ID</th>'+
        '<th>Name</th>'+
        '</tr>'
    fetch('/Restaurant/listRest',{method:'get'}
    ).then(response => response.json()
    ).then(restaurants => {
        for (let Restaurant of restaurants){
            restaurantHTML+='<tr>'+
                '<td>${Restaurant.RestID}</td>'+
                '<td>${Restaurant.Rname}</td>'+
                '</tr>';
            restaurantHTML+='</table>';
            document.getElementsByName("listRest").innerHTML=restaurantHTML;

        }
    });
}