function getSourceAsDOM(url)
{
    console.log("getting walleet source url...");
    xmlhttp=new XMLHttpRequest();
    xmlhttp.open("GET",url,false);
    xmlhttp.send();
    parser=new DOMParser();
    let walletElement = parser.parseFromString(xmlhttp.responseText,"text/html");
    console.log("returning wallet element...");
    return walletElement;
}

function main() {
    console.log("getting indexpage...");
    let indexPage = getSourceAsDOM("/quest/codecooler/index");
    console.log("getting wallet value...");
    let walletValue = Number(indexPage.getElementById("coolcoins").innerText);
    console.log("setting listeners...");

    document.querySelectorAll('.item').forEach(
        function(element) {
            console.log("setting listener...");
            element.querySelector(".editButton").addEventListener('click', function() {
                console.log("element:");
                console.log(element);
                let prize = Number(element.querySelector('.prize').innerHTML);
                console.log("prize:");
                console.log(prize);
                if(walletValue >= prize) {
                    alert("Item bought!");
                    let buyLink = element.querySelector("#buyLink").getAttribute("data-href");
                    window.location.href = buyLink;
                } else {
                    alert("Not enough coolcoins!")
                }
            })
        }
    );
}

console.log("running script...");
main();