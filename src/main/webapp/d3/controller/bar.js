var barScope;
var barData;

function barCtrl($scope) {
    d3.json("/cgp/getData", function (error, data) {
        barScope = $scope;
        barData = data;
        barScope.letterFrequencies = combineBar();
        var letterFrequency = new Object();
        letterFrequency.x = 79;
        letterFrequency.width = 68;
        letterFrequency.height = 405 * 0.5;
        letterFrequency.y = 405 - letterFrequency.height;
        barScope.letterFrequency = letterFrequency;
    });
}

function combineBar() {
    var letterFrequencies = new Array();
    for (var i = 0; i < barData.length; i++) {
        var letterFrequency = new Object();
        letterFrequency.x = 79 * (i + 1);
        letterFrequency.width = 68;
        letterFrequency.height = 405 * barData[i].frequency;
        letterFrequency.y = 405 - letterFrequency.height;
        letterFrequencies.push(letterFrequency);
    }
    return letterFrequencies ;
}
function getBar() {
    d3.json("/cgp/getData", function (error, data) {
        barData = data;
        barScope.letterFrequencies = combineBar();
        barScope.apply();
    });
}