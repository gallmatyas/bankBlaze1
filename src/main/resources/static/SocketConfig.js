var stompClient = null;

function connect() {
    var socket = new SockJS('/bankBlaze-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/app', function (desk) {
            console.log("akarmi");
            sendRefresh(JSON.parse(desk.body));
        });
    });
}

function sendRefresh(desk) {
    var id = "#queueNumber" + desk.id
    console.log(desk.queueNumber.number);
    console.log(id);
    $(id).text(desk.queueNumber.number);
}
