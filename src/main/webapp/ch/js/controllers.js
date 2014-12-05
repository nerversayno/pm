var baseUrl = '../';
var articleType = {
    trends: 'TRENDS',
    technologies: 'TECHNOLOGIES',
    cases: 'CASE'
}
var prodcutType = {
    softWare: 'SOFTWARE_PRODUCT',
    hardWare: 'HARDWARE_PRODUCT'
}
function indexControl($scope, $http) {
    $scope.data = {}
    $http.post(baseUrl + 'products/listLimit?limit=6').success(function (data) {
        $scope.data.products = data;
    })
    $http.post(baseUrl + 'articles/listByTypeLimit?type=' + articleType.trends).success(function (data) {
        $scope.data.trends = data;
    })
    $http.post(baseUrl + 'articles/listByTypeLimit?type=' + articleType.technologies).success(function (data) {
        $scope.data.technologies = data;
    })
    $http.post(baseUrl + 'articles/listByTypeLimit?type=' + articleType.cases).success(function (data) {
        $scope.data.cases = data;
    })

    setTimeout(function () {
        $('#intro-slider').flexslider({
            namespace: "flex-",
            controlsContainer: "",
            animation: 'fade',
            controlNav: false,
            directionNav: true,
            smoothHeight: true,
            slideshowSpeed: 7000,
            animationSpeed: 600,
            randomize: false
        });
        $('#intro-slider').flexslider(0);
    }, 100)
}

function listControl($scope, $http, $routeParams) {
    var type = $routeParams.type;
    $scope.pageData = {};
    $http.post(baseUrl + 'articles/listByType?type=' + articleType[type]).success(function (data) {
        generateListPage($scope, data, 'articles');
    });
}
function recruitsControl($scope, $http) {
    $scope.pageData = {};
    $http.post(baseUrl + 'recruits/list').success(function (data) {
        generateListPage($scope, data, 'recruits');
    });
}

function productsControl($scope, $http) {
    $scope.pageData = {};
    $http.post(baseUrl + 'products/list').success(function (data) {
        data.forEach(function (d) {
            d.list.forEach(function (p) {
                p.summary = Winsion.getSummary(p.content);
            })
        })
        $scope.pageData = data;
        setTimeout(function(){
           $('.portfolio-item').each(function(){
               var num = $(this).attr('name');
               if(num%3==0){
                   $(this).addClass('first');
               }
           });
        },0)
    });
}
function linkControl($routeParams) {
    var link = $routeParams.link;
    setTimeout(function () {
        $('#linkPage').attr('src', 'extends/' + link);
        iFrameHeight
    }, 100)
}

function serviceControl() {
    window.location.href = 'http://www.winsion.net/winsion_ch/login.jsp';
}
function iFrameHeight() {

    var ifm = document.getElementById("linkPage");

    var subWeb = document.frames ? document.frames["linkPage"].document :

        ifm.contentDocument;

    if (ifm != null && subWeb != null) {

        ifm.height = subWeb.body.scrollHeight;

    }

}
function detailControl($scope, $routeParams, $location) {
    var params = {
        type: $routeParams.type,
        id: $routeParams.id
    }
    Winsion.postJson(baseUrl + params.type + '/get', params, function (data) {
        markDownData(data, $location, $scope);
        setTimeout(function () {
            $("#go-top a").click();
        }, 10);
    })
}
function markDownData(data, $location, $scope) {
    $("#title").text(data.title)
    var href = Winsion.getHref(data.content);
    if (href.length == 0) {
        $('#content').html(Winsion.generateHtml(data.content));
    } else {
        window.location.href = 'index.html#/link/' + href;
    }
}

function generateListPage($scope, data, url) {
    var pageData = Winsion.rePage(data);
    pageData.url = url;
    if (pageData.pageList.length > 0) {
        pageData.list = pageData.pageList[0].list;
    } else {
        pageData.list = []
    }
    $scope.pageData = pageData;
    setTimeout(function () {
        //分页选择
        $('.page-numbers').click(function (e) {
            e.preventDefault();
            var pageNo = this.name;
            if (pageData.pageList.length >= pageNo) {
                $scope.pageData.list = pageData.pageList[pageNo - 1].list;
                $scope.pageData.currentNo = pageNo;
            } else {
                return;
            }
            $scope.$apply();
            initPageStatus(pageData, pageNo);
            $('.page-numbers').removeClass('current');
            $('.page-numbers[name=' + pageNo + ']').addClass('current')
            $("#go-top a").click();
        })
        //初始分页状态
        $('.page-numbers[name=1]').addClass('current');
        var pageNo = pageData.currentNo;
        initPageStatus(pageData, pageNo);
    }, 0)
}

function initPageStatus(pageData, pageNo) {
    if (pageNo == 1) {
        $('.prev').addClass('inactive');
    } else {
        $('.prev').removeClass('inactive');
        $('.prev').attr('name', pageNo - 1);
    }
    if (pageNo == pageData.totalPage) {
        $('.next').addClass('inactive');
    } else {
        $('.next').removeClass('inactive');
        $('.next').attr('name', pageNo + 1);
    }
    setTimeout(function () {
        addSummary(pageData.list);
    }, 0)
}
function addSummary(list) {
    list.forEach(function (article) {
        $('#summary-' + article.id).html(Markdown(Winsion.getSummary(article.content)));
    });
}