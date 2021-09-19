class Player {
    constructor(name, monastery, mine, barracks, manaCount, oreCount, armyCount, tower, wall, canTurn, requireDrop) {
        this.name = name;
        this.monastery = monastery;
        this.mine = mine;
        this.barracks = barracks;
        this.manaCount = manaCount;
        this.oreCount = oreCount;
        this.armyCount = armyCount;
        this.tower = tower;
        this.wall = wall;
        this.canTurn = canTurn;
        this.requireDrop = requireDrop;
    }
}
let index = 0;
const fieldUrl = getPath() + "/resources/img/gameField/";
const dropCardHtml="<div class='cardDropBackground'></div><label class='dropText'>Карта сброшена</label>";
const alertHtml= "<div class='customAlert'><label class='customAlertDesc' id='cAlertDesc'></label><button class='okayAlert' id='cOkay'>Okay</button></div>";
let lastRect;
let lastIndex;
let lastCard;
let placeCardPositions;
let gameCardDeckPosition;
let enemyCard;
let lastTowerCount;
const audioTowerAp = new Audio(getPath()+"/resources/audio/tower_up.mp3");
const audioTowerDown = new Audio(getPath()+"/resources/audio/tower_down.mp3");
const audioTurn=new Audio(getPath()+"/resources/audio/turn.mp3")
let timer;
let timerCount=30;

function sendMessage(elem,secondParam) {


    if(event.key=="Enter"||secondParam!=null&&secondParam=="Enter") {
        let content = player.name+": "+elem.value;
        elem.value="";
        addChatLine(content);
        let message = {"action": "Msg", "message": content};
        message = JSON.stringify(message);
        wsSendMessage(message);
    }
}
function addChatLine(str)
{
    document.getElementById("showTextId").value+=str+"\n";
}

function createChatMessage()
{
    let content=player.name+": "+document.getElementById("inputTextId").value;
    document.getElementById("inputTextId").value="";
    return content;
}

function setPos()
{
    placeCardPositions=document.getElementsByClassName("cardBack");
    placeCardPositions = Array.prototype.slice.call(placeCardPositions);
    enemyCard=document.getElementById("enemyCardSpawn")
    placeCardPositions.splice(0,1);
    gameCardDeckPosition=document.getElementById("gameCardDeck").getBoundingClientRect();

}

function setLastTowerCont(count)
{
    lastTowerCount=count;
}

function playSoundEffect(towerCount) {
    if(towerCount<lastTowerCount)
    {
        audioTowerDown.play();
    }
    if(towerCount>lastTowerCount)
    {
        audioTowerAp.play();
    }
}



function getPath() {
    let fullUrl = window.location.href;
    return fullUrl.substring(0, fullUrl.lastIndexOf("/"));
}
function showCustomAlert(alertDesc,alertColor,backgroundSrc) {
    document.getElementById("stepMsg").style.display="none";
    document.getElementById("connectionForm").style.display="none";
    document.getElementById("player").style.display = "";
    document.getElementById("enemy").style.display = "";
    document.getElementById("gTable").style.display="none";
    document.body.insertAdjacentHTML("beforeend",alertHtml);

    let desc =document.getElementById("cAlertDesc");
    let okButton =document.getElementById("cOkay")
    let windowAlert=desc.parentNode;
    let backGroundUrl="url("+fieldUrl+backgroundSrc+")";
    windowAlert.style.backgroundImage=backGroundUrl;


    desc.textContent=alertDesc;
    desc.innerText=alertDesc;
    desc.style.backgroundColor=alertColor;
    desc.style.textAlign="center";
    okButton.style.backgroundColor=alertColor;
    okButton.addEventListener("click",function (){location.reload()});
}

function getPlayerEntity(player) {
    let name = player["name"];
    let monastery = player["monasteryCount"];
    let mine = player["mineCount"];
    let barracks = player["barracksCount"];
    let manaCount = player["manaCount"];
    let oreCount = player["oreCount"]
    let armyCount = player["armyCount"]
    let tower = player["tower"];
    let wall = player["wall"];
    let canTurn = player["canStep"];
    let drop = false;

    return new Player(name, monastery, mine, barracks, manaCount, oreCount, armyCount, tower, wall, canTurn, drop);
}



function paint(id, player) {
    let resIndex = 0;
    let buildIndex = 1;
    let wallIndex = 1;
    let towerIndex = 0;
    if (id == "enemy") {
        resIndex = 1;
        buildIndex = 0;
        towerIndex = 1;
        wallIndex = 0;

    }
    let arr = document.getElementById(id);
    let resources = arr.children[resIndex];
    let build = arr.children[buildIndex];

    let name = resources.children[0];

    let monasteryDiv = resources.children[1];
    let mineDiv = resources.children[2];
    let barracksDiv = resources.children[3];

    let monasteryCount = monasteryDiv.children[1];
    let mineCount = mineDiv.children[1];
    let barracksCount = barracksDiv.children[1];

    let manaCount = monasteryDiv.children[2];
    let oreCount = mineDiv.children[2];
    let armyCount = barracksDiv.children[2];

    let tower = build.children[towerIndex];
    let wall = build.children[wallIndex];
    let tImage = tower.children[0];
    let wImage=wall.children[0];
    let towerCount = tower.children[1];
    let wallCount = wall.children[1];

    name.innerHTML = player.name;
    monasteryCount.innerHTML = "+" + player.monastery;
    manaCount.innerHTML = player.manaCount;
    mineCount.innerHTML = "+" + player.mine;
    oreCount.innerHTML = player.oreCount;
    barracksCount.innerHTML = "+" + player.barracks;
    armyCount.innerHTML = player.armyCount;
    towerCount.innerHTML = player.tower;
    wallCount.innerHTML = player.wall;
    tImage.style.height=player.tower+"%";
    wImage.style.height=player.wall+"%";
}

function turnTimer()
{
    document.getElementById("timerDiv").style.display="";
    let timerOutput=document.getElementById("turnTimer");
    let timerAnimObject = document.getElementById("clockImgObj");
    timerAnimObject.style.animationPlayState="running";
    timerOutput.textContent=timerCount+"";
    timerOutput.style.color="green";
   timer= setInterval(function () {
        timerCount--;
        switch (timerCount)
        {

            case 20:
                timerOutput.style.color="yellow";
                break;
            case 10:
                timerOutput.style.color="red";
                break;
        }
        timerOutput.textContent=timerCount+"";
        if(timerCount==0)
        {

                let cards = document.getElementsByClassName("card");
                let rndCard = getRandomIntInclusive(0,5);
                let card = cards.item(rndCard).children[0];
                sendCard(card,"Drop");

            timerAnimObject.style.animationPlayState="paused";
            clearInterval(timer);
            timerCount=30;
        }

    },1000);
}
function getRandomIntInclusive(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min; //Максимум и минимум включаются
}

function canRes(color, price) {

    switch (color)
    {
        case "blue":
            return player.manaCount>=price
            break;
        case "red":
            return player.oreCount>=price
            break;
        case "green":
            return player.armyCount>=price
            break;
    }

}

function turnCardAnim(card,className,isDrop) {


    audioTurn.play();
    if(index>4)index=0;
    let newCard = cloneCard(card,card.getBoundingClientRect(),className,isDrop);
    card.style.opacity="0";
    addBlockForCardEvent(card);

    let targetRect = placeCardPositions[index].getBoundingClientRect();
    let currentRect=newCard.getBoundingClientRect();
    newCard.style.transform+='translateY('+(targetRect.top-currentRect.top)+'px)';

    newCard.style.transform+='translateX('+(targetRect.left-currentRect.left)+'px)';
    newCard.addEventListener("transitionend", function (){

    }, false);
    index++;
   lastCard=card;

}

function paintCard(card) {
    let deck = document.getElementById("playerCard");
    for (let i = 0; i < deck.children.length; i++) {
        let cardColorImg = deck.children[i].children[0];
        cardColorImg.src = fieldUrl + card[i]["cardColor"] + ".jpg";
        let cardName = deck.children[i].children[1];
        cardName.innerHTML = card[i]["name"];
        let cardImg = deck.children[i].children[2];
        cardImg.src = getPath() + card[i]["imgPath"];
        let cardDesc = deck.children[i].children[3];
        cardDesc.innerHTML = card[i]["description"];
        let price = deck.children[i].children[6];
        price.style.color = card[i]["cardColor"];
        price.innerHTML = card[i]["price"];
        if (canRes(card[i]["cardColor"],card[i]["price"])) {
            price.style.color = "green";
        } else {
            price.style.color = "red";
        }

    }
}

function paintSingleCard(card,elem) {
        let cardColorImg = elem.children[0];
        cardColorImg.src = fieldUrl + card["cardColor"] + ".jpg";
        let cardName = elem.children[1];
        cardName.innerHTML = card["name"];
        let cardImg = elem.children[2];
        cardImg.src = getPath() + card["imgPath"];
        let cardDesc = elem.children[3];
        cardDesc.innerHTML = card["description"];
        let price = elem.children[6];
        price.style.color = card["cardColor"];
        price.innerHTML = card["price"];
}

function getNewCardAnim(card,className)
{
    audioTurn.play();
    let newCard = cloneCard(card,gameCardDeckPosition,className,false);
    let targetRect = gameCardDeckPosition;
    let currentRect=card.getBoundingClientRect();
    if(currentRect==enemyCard.getBoundingClientRect())
    {
        card.insertAdjacentHTML("beforeend", "<div class='cardBackEnemy'></div>");
    }

    newCard.style.transform+='translateY('+(currentRect.top-targetRect.top)+'px)';
    newCard.style.transform+='translateX('+(currentRect.left-targetRect.left)+'px)';
    newCard.addEventListener("transitionend", function (){
        card.style.opacity="1";
        removeBlockForCard(card);
        document.body.removeChild(newCard);
    }, false);


}





function canPlaceCards(classname)
{
    let cards = document.getElementsByClassName(classname);
    return cards.length>0?true:false;
}

function backForGameCardDeckAnim(classname)
{
    let targetRect =gameCardDeckPosition;
    let cards = document.getElementsByClassName(classname);
    if(cards!=null&&cards.length>0)
    {
        for (let i = 0; i < cards.length; i++) {
            let current = cards[i];
            let rect = current.getBoundingClientRect();

            current.style.transform += 'translateY('+(targetRect.top-rect.top)+'px)';
            current.style.transform += 'translateX('+(targetRect.left-rect.left)+'px)';
            current.addEventListener("transitionend", function (){try {
                document.body.removeChild(current);
            }
            catch (e)
            {

            }}, false)
        }
    }
    cards=null;
    index=0;
}

function getNewCardManager(card,className,timeout)
{
    setTimeout(getNewCardAnim,timeout,card,className)
}

    function cloneCard(card,position,className,isDrop) {
        let newCard = card.cloneNode(true);
        newCard.classList.remove("card");
        newCard.classList.add(className);
        newCard.style.width=card.clientWidth+"px";
        newCard.style.height=card.clientHeight+"px";
        newCard.insertAdjacentHTML("beforeend", "<div class='blockingDiv'></div>");
        if(isDrop)newCard.insertAdjacentHTML("beforeend", dropCardHtml);
        newCard.style.left=position.left+"px";
        newCard.style.top=position.top+"px"
        newCard.style.opacity="1";
        document.body.appendChild(newCard);
        return newCard;
    }

function addBlockForCardEvent(card)
{
    for (let i = 0; i < card.children.length; i++) {
        if(card.children[i].classList.contains("blockingDiv"))return;
    }
        card.insertAdjacentHTML("beforeend","<div class='blockingDiv'></div>");
}
function removeBlockForCard(card)
{
    for (let i = 0; i < card.children.length; i++) {
        if(card.children[i].classList.contains("blockingDiv"))card.removeChild(card.children[i]);
    }
}

function isResAvailable(Card)
{
    let colorPath=Card.children[0].src;
    let color = colorPath.substring(colorPath.lastIndexOf("/")+1,colorPath.lastIndexOf("."));
    let price=Card.children[6].textContent;
    return canRes(color,price);
}