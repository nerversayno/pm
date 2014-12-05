angular.module('winsion', ['ngRoute']).filter('imgFilter',function(){
    return function(data){
        if(data && data.trim() !=''){
            return data;
        }else
        {
            return 'sample-image.jpg'
        }
    }
}).config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.when('/products', {
            controller:productsControl,
            templateUrl:'products.html'
        }).when('/list/:type', {
            controller:listControl,
            templateUrl:'post-list.html'
        }).when('/recruits', {
            controller:recruitsControl,
            templateUrl:'post-list.html'
        }).when('/detail/:type/:id', {
            controller:detailControl,
            templateUrl:'post.html'
        }).when('/link/:link',{
            controller:linkControl,
            templateUrl:'link.html'
        }).when('/service',{
            controller:serviceControl,
            templateUrl:'main.html'
        }).otherwise({
            controller:indexControl,
            templateUrl:'main.html'
        });
    }]);