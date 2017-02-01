var testApp = angular.module("testApp", ["ngRoute"]);

console.log("testApp module created");

testApp.config(function($routeProvider) {
    console.log("Initializing ng-router");
    $routeProvider
        .when("/", {
//            template: "<h2>test 1</h2>"
            templateUrl: "test1.html"
        })
        .when("/test2", {
//            template: "<h2>test 2</h2>"
            templateUrl: "test2.html"
        })
        .when("/test3", {
//            template: "<h2>test 3</h2>"
            templateUrl: "test3.html"
        })
});

testApp.controller('testController', function($scope) {
    console.log("Initializing testController");
    $scope.testVar = "Testing Angular JS Routing";
});

