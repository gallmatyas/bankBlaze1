let stompClient = null;
let socket = new SockJS('/bankBlaze-websocket');
let id;

function connect() {
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/app', function (desk) {
            sendRefresh(JSON.parse(desk.body));
            clickOnIt();
        });
    });

}

function sendRefresh(desk) {
    id = "#queueNumber" + desk.id;
    $(id).text(desk.queueNumber.number);
}

function clickOnIt() {
    document.querySelector('#notifyButton').click();
}

function notify() {
    let audio = document.getElementById('notify');
    let promise = audio.play();
    if (promise) {
        promise.catch(function (error) {
            console.error(error);
        });
    }
}