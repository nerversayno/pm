/**
 * Created by lcf on 2014/12/4.
 */
var allApi = {};
var testScope = {}
function TestAllCrl($http,$scope){
    testScope = $scope;
    setTimeout(init,0);
}
function AllApiCtrl($http,$scope){
    var url = "http://localhost:8080/doc.json";
    $scope.allApi = {};
    $http.get(url).success(function (data) {
        refactorData(data);
        combineData($http,data);
        $scope.allApi = data;
        testScope.allApi = data;
        setTimeout(init,0);
    });
}
function combineData($http,data){
  for(var i=0; i< data.apis.length; i++){
      var service = data.apis[i];
      for(var j=0;j<service.operations.length;j++){
          var operation = service.operations[j];
          operation.cases = [];
          testData.urls.forEach(function(url){
             if(url.path ==operation.path){
                 operation.cases = url.cases;
                 for(var i=0; i< operation.cases.length;i++){
                     getResult($http,operation.path,operation.method,operation.cases[i]);
                 }
             }
          });

      }
  }
}
function getResult($http,path,method,aCase){
    if(method.toLowerCase() == "post"){
        $http.post(testData.baseUrl +path,aCase.params).success(function(data){
            console.log(data);
        })
    }else if(method.toLowerCase() == "get"){
        $http.get(testData.baseUrl +path,aCase.params).success(function(data){
            console.log(data);
        })
    }else{
        $http.get(testData.baseUrl +path,aCase.params).success(function(data){
            console.log(data);
        })
    }

}
function refactorData(data){
    for(var i=0; i<data.apis.length;i++){
        var service = data.apis[i];
        service.id = service.path;
        var operations = new Array();
        for(var j=0;j<service.apis.length;j++){
            var mapping = service.apis[j];
            for(var k=0;k<mapping.operations.length;k++){
                var operation = new Object();
                var tmp = mapping.operations[k];
                operation.id = operation.path;
                operation.path = mapping.path;
                operation.description = mapping.description;
                operation.method = tmp.method;
                operation.summary = tmp.summary;
                operation.notes = tmp.notes;
                operation.type = tmp.type;
                operation.nickname = tmp.nickname;
                operation.items = tmp.items;
                operation.parameters = tmp.parameters;
                operation.docResponseMessages = tmp.docResponseMessages;
                operations.push(operation);
            }
        }
        service.operations = operations;
    }
}
$(function(){
   init();
})

function init(){
    $(".nav li").click(function(){
        if($(this).hasClass("active")){
            $(this).removeClass("active");
        }else{
            $(".nav li").removeClass("active");
            $(this).addClass("active");
        }
    });
    $(".back-to-top").click(function(){
        $(".nav li").removeClass("active");
        $(".nav li")[0].addClass("active");
    });
    $(".bs-docs-section").mouseenter(function(){
        var $h1=  $(this).find(".page-header");
        $(".nav li").removeClass("active");
        $("a[href='#"+$h1.attr("id")+"']").parent().addClass("active");
    });
    $(".service-header").click(function(){
    });
}
