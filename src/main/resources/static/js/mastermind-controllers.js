angular.module('TIYMastermindApp', [])
    .controller('GameController', function($scope, $http) {

        $scope.testFunction = function() {
            console.log(JSON.stringify($scope.userGuess));
        }

        console.log("Initializing GameController");

        $scope.userGuess = {};
    });
