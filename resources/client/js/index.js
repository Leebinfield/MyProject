function pageLoad() {

    let now = new Date();
    document.getElementById("content").innerHTML = '<div style ="text-align:center;">' + '<h1>Welcome</h1>' +
        '<div style = "font-style:italic;">' + 'Generated at ' + now.toLocaleTimeString()
        + '</div>' + '</div>';



}
