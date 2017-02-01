var testApp = angular.module("testApp", ["ngRoute"]);

console.log("testApp module created");

testApp.config(function($routeProvider) {
    console.log("Initializing ng-router");
    $routeProvider
        .when("/", {
//            template: "<h2>test 1</h2>"
            templateUrl: "test1.html",
            controller: "controller1"
        })
        .when("/test2", {

//            template: "<h2>test 2</h2>"
            templateUrl: "test2.html",
            controller: "controller2"
        })
        .when("/test3", {
//            template: "<h2>test 3</h2>"
            templateUrl: "test3.html",
            controller: "controller3"
        })
        .when("/mm", {
            templateUrl: "mastermind-partial.html"
        })
});

testApp.controller('controller1', function($scope) {
    console.log("initializing controller 1 ...");
    $scope.testItemList = ["Simple1", "Simple2", "Simple3"];
});

testApp.controller('controller2', function($scope, $rootScope) {
    console.log("initializing controller 2 ...");
    $scope.testGlobalValue = "Hello There";
    $rootScope.testGlobalValue = $scope.testGlobalValue;
});

testApp.controller('controller3', function($scope) {
    console.log("initializing controller 3 ...");
});

testApp.controller('testController', function($scope) {
    console.log("Initializing testController");
    $scope.testVar = "Testing Angular JS Routing";

    $scope.getPeople = function() {
        console.log("fetching person list from server ...");
        $http.get("/get-person-list.json")
            .then(
                function successCallback(response) {
                    console.log(response.data);
                    console.log("Adding data to scope");
                    $scope.testItemList = response.data;
                },
                function errorCallback(response) {
                    console.log("Unable to get data");
                });
    };

    $scope.testItemList = [
        {
            "firstName": "James",
             "lastName": "Kirk",
             "profession": "Starship Captain"
        },
        {
            "firstName": "Jane",
             "lastName": "Doe",
             "profession": "Lost Person"
        },
        {
            "firstName": "James",
             "lastName": "Harden",
             "profession": "MVP Candidate"
        },
        {
            "firstName": "Jean-Luc",
             "lastName": "Picard",
             "profession": "Starship Captain"
        }
    ];


});

