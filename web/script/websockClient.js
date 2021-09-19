let webSocket;
let player;
let enemy;
function wsOpen() {
}
function getUrl() {
    return window.location.href.substring(0, window.location.href.lastIndexOf("/"));

}
function openCon(elem, secondParam) {
    if (event.key == "Enter" || secondParam != null && secondParam == "Enter") {
        webSocket = getWebSockConnection();
        document.getElementById("viewText").style.display = "";
        document.getElementById("inputName").style.display = "none";
    }
}

function sendCard(card, action) {
    let parentCard = card.parentNode;
    if (player.canTurn) {
        let id = parentCard.id;
        if (action == "Step" && !isResAvailable(parentCard)) {
            alert("Недостаточно ресурсов");
            return;
        }
        id = id.substring(id.length - 1)
        let message = {
            "action": action,
            "mapId": id
        };
        let currentCard = JSON.stringify(message);
        wsSendMessage(currentCard);
        lastIndex = id;
        lastRect = parentCard.getBoundingClientRect();
        let isDrop = action == "Drop" ? true : false;

        if (canPlaceCards("deleteCardEnemy")) backForGameCardDeckAnim("deleteCardEnemy");
        turnCardAnim(parentCard, "deleteCardPlayer", isDrop);
        if (timer != null) {
            clearInterval(timer);
        }
    }
    if (canPlaceCards("deleteCardEnemy")) backForGameCardDeckAnim("deleteCardEnemy");

}


function getWebSockConnection() {
    let url = getUrl();
    let playerName = document.getElementById("name").value;
    let finalUrl = url.replace("http", "ws") + "/arcomage/" + playerName;
    let webSocket = new WebSocket(finalUrl);
    webSocket.onopen = function (message) {
        wsOpen(message);
    };
    webSocket.onmessage = function (message) {
        wsGetMessage(message);
    };
    webSocket.onclose = function (message) {
        wsClose(message);
    };
    webSocket.onerror = function (message) {
        wsError(message);
    };
    return webSocket;
}

function wsSendMessage(msg) {
    console.log("send");
    webSocket.send(msg);
}

function wsCloseConnection() {
    webSocket.close();
}

function wsGetMessage(message) {
    let json = JSON.parse(message.data);
    let action = json["action"];
    let enemyMap = json["turnMap"];
    timerCount = 30;
    switch (action) {
        case "wait":
            let msgLabel = document.getElementById("stepMsg");
            msgLabel.style.display = "";
            msgLabel.innerText = json["message"];
            break;
        case "start":
            showGameScreen();
            json = json["message"];
            player = getPlayerEntity(json[0]);
            enemy = getPlayerEntity(json[1]);
            setLastTowerCont(player["tower"]);
            paintCard(json[0]["maps"]);
            paint("player", player)
            paint("enemy", enemy)
            setPos();
            if (player != null) {
                if (player.canTurn) {
                    document.getElementById("timerDiv").style.display = "";
                    document.getElementById("viewText").innerText = "Ваш ход";
                    turnTimer();
                } else {
                    document.getElementById("viewText").innerText = "Ход соперника...";
                    turnTimer();
                }
            }
            break;
        case "turnPlayer":
            json = json["message"];
            player = getPlayerEntity(json[0]);
            enemy = getPlayerEntity(json[1]);
            let maps = json[0]["maps"];
            paint("player", player)
            paint("enemy", enemy)
            playSoundEffect(player["tower"]);
            setLastTowerCont(player["tower"]);
            paintCard(maps);
            clearInterval(timer)
            turnTimer();
            getNewCardManager(lastCard, "deleteCardPlayer", 500)
            break;
        case "turnEnemy": {
            let map = json["turnMap"];
            let isDrop = json["isDrop"];
            json = json["message"];
            player = getPlayerEntity(json[0]);
            enemy = getPlayerEntity(json[1]);

            paintCard(json[0]["maps"]);
            paint("player", player)
            paint("enemy", enemy)
            playSoundEffect(player["tower"]);
            setLastTowerCont(player["tower"]);
            if (canPlaceCards("deleteCardPlayer")) backForGameCardDeckAnim("deleteCardPlayer");
            paintSingleCard(map, enemyCard);
            turnCardAnim(enemyCard, "deleteCardEnemy", isDrop);
            getNewCardManager(enemyCard, "deleteCardEnemy", 500)
            clearInterval(timer)
            turnTimer();
            break;
        }

        case "end": {
            alert(json["message"]);
            location.reload();
            break;
        }
        case "vin":
            backForGameCardDeckAnim("deleteCardEnemy");
            backForGameCardDeckAnim("deleteCardPlayer");
            showCustomAlert(json["message"], "green", "vin.png");
            clearInterval(timer);
            document.getElementById("timerDiv").style.display="none";
            break;
        case "lose":
            backForGameCardDeckAnim("deleteCardEnemy");
            backForGameCardDeckAnim("deleteCardPlayer");
            showCustomAlert(json["message"], "red", "lose.png");
           clearInterval(timer);
            document.getElementById("timerDiv").style.display="none";
            break;
        case "msg":
            addChatLine(json["message"]);
            break;
    }
    if (player != null) {
        if (player.canTurn) {
            document.getElementById("timerDiv").style.display = "";
            document.getElementById("viewText").innerText = "Ваш ход";
        } else {
            document.getElementById("viewText").innerText = "Ход соперника...";
        }
    }
}

function wsClose(message) {

}

function wsError(message) {

}

function showGameScreen() {
    document.getElementById("connectionForm").style.display = "none";
    document.getElementById("playerCard").style.display = "";
    document.getElementById("player").style.display = "";
    document.getElementById("enemy").style.display = "";
    document.getElementById("gTable").style.display = "";
    document.getElementById("chat").style.display = "";
    document.getElementById("turnTimer").style.display = "";
    document.getElementById("viewText").style.display = "";
    document.getElementById("stepMsg").style.display = "none";
}