var testApp = angular.module("testApp", ["ngRoute"]);

console.log("testApp module created");

testApp.config(function($routeProvider) {
    console.log("Initializing ng-router");
    $routeProvider
        .when("/", {
            templateUrl: "test1.html",
            controller: "controller1"
        })
        .when("/test2", {
            templateUrl: "test2.html",
            controller: "controller2"
        })
        .when("/test3", {
            templateUrl: "test3.html",
            controller: "controller3"
        })
        .when("/mm", {
            templateUrl: "mastermind-partial.html"
        })
        .when("/spa1", {
            templateUrl: "spa1.html",
            controller: "spaController1"
        })
        .when("/spa2", {
            templateUrl: "spa2.html",
            controller: "spaController2"
        })
        .when("/spa3", {
            templateUrl: "spa3.html",
            controller: "spaController3"
        })
});

testApp.controller('controller1', function($scope, $location, $rootScope) {
    console.log("initializing controller 1 ...");

    $scope.getPersonDetails = function(lastName) {
        console.log("in getPersonDetails() " + lastName);
        $rootScope.lastNameSelected = lastName;
        $location.path("/test3");
    }

    $scope.getFriends = function(lastName) {
        $scope.selectedPersonName = lastName;

        if (lastName == "Kirk") {
            $scope.attendeeList = [
                {
                    "firstName": "James",
                    "lastName" : "MacAdoo"
                },
                {
                    "firstName": "Mary",
                    "lastName" : "Cooper"
                }
            ];
        } else if (lastName == "Doe") {
            $scope.attendeeList = [
                {
                    "firstName": "Carrie",
                    "lastName" : "Fisher"
                },
                {
                    "firstName": "Harrison",
                    "lastName" : "Ford"
                },
                {
                    "firstName": "Donald",
                    "lastName" : "Trump"
                }
            ];

        } else if (lastName == "Harden") {
            $scope.attendeeList = [
                {
                    "firstName": "Kevin",
                    "lastName" : "Durant"
                },
                {
                    "firstName": "LeBron",
                    "lastName" : "James"
                },
                {
                    "firstName": "Russell",
                    "lastName" : "Westbrook"
                }
            ];

        } else {
            $scope.attendeeList = [];
        }
    }
});

testApp.controller('controller2', function($scope, $rootScope) {
    console.log("initializing controller 2 ...");
    $scope.testGlobalValue = "Hello There";
    $rootScope.testGlobalValue = $scope.testGlobalValue;
});

testApp.controller('controller3', function($scope, $rootScope) {
    console.log("initializing controller 3 ...");

    if ($rootScope.lastNameSelected) {
        $scope.lastName = $rootScope.lastNameSelected;

        // this is the kind of call you would make to get the details
        // of the person with this lastName from an endpoint
//        $http.get("/get-person-details.json?lastName=" + lastName)

        $rootScope.lastNameSelected = null;
    } else {
        $scope.lastName = "No Person Selected";
    }
});

testApp.controller('spaController1', function($scope, $rootScope, $location) {
    console.log("initializing SPA controller 1 ...");

    $scope.getData = function() {
        console.log("getData()");
        $scope.testData = {"testString": "test data for playing around with scope"};
        $rootScope.testData = $scope.testData;
        $location.path("/spa2");
    }

});

testApp.controller('spaController2', function($scope, $rootScope) {
    console.log("initializing SPA controller 2 ...");
    $scope.testData = $rootScope.testData;
    $rootScope.testData = null;
});

testApp.controller('spaController3', function($scope) {
    console.log("initializing SPA controller 3 ...");
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
             "profession": "Starship Captain",
             "numFriends": 12
        },
        {
            "firstName": "Jane",
             "lastName": "Doe",
             "profession": "Lost Person",
             "numFriends": 7
        },
        {
            "firstName": "James",
             "lastName": "Harden",
             "profession": "MVP Candidate",
             "numFriends": 11
        },
        {
            "firstName": "Jean-Luc",
             "lastName": "Picard",
             "profession": "Starship Captain",
             "numFriends": 8
        }
    ];


});

