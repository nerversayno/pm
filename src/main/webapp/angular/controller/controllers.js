function PhoneListCtrl($scope, $http) {
    $http.get('phones.json').success(function (data) {
        $scope.phones = data;
    });

    $scope.orderProp = 'age';
}

var clientScope;
function ClientCtrl($scope) {
    clientScope = $scope;
    clientScope.clients = combineClient();
}

function combineClient() {
    var startNumber = $("#startNumber").val();
    var clientNumber = $("#clientNumber").val();
    var clients = new Array();
    for (var i = 0; i < clientNumber; i++) {
        var client = new Object();
        client.name = parseInt(i) + parseInt(startNumber);
        client.number = Math.floor(Math.random() * 10) + 1;
        client.random = 0;
        client.class = "testTD" +(i%2 +1);
        clients.push(client);
    }
    return clients;
}
var url;
function sellTicket(client, number, random, $result) {
    for(var i =0 ;i<number;i++){
        $.ajax({
            type: "GET",
            url: url,
            data: {client:client, number:Math.floor(Math.random() *random) + 1,date:client + i},
            dataType: "json",
            success: function(data){
                var str ="";
                for(var i=0; i<data.length;i++){
                      str +=data[i].guid+"|"
                }
                $result.append("<tr><td>"+str+"</td></tr>");
            }
        });
    }
}

$(function () {
    url = $("#url").val();
    $("#generateClient").click(function () {
        clientScope.clients = combineClient();
        clientScope.$apply();
    })
    $("#startAll").click(function(){
       $(".start").each(function(){
           startRequest($(this));
       }) ;
    });
    $(".start").click(function () {
        startRequest($(this));
    });

    function startRequest($this){
        var $tr = $this.parent().parent();
        var $tds = $tr.find("td");
        var name = $tds[0].innerText
        var number = $tds[1].innerText;
        var random = $tds[2].innerText;
        var result = $tds[5];
        var table = $(result).find("table");
        sellTicket(name, number, random, $(table));
    }
});

