angular.module('TIYMastermindApp', [])
    .controller(

        'GameController',

        function($scope, $http) {
            console.log("Initializing GameController");

            $scope.sampleFunction = function() {
                console.log("insinde sampleFunction() ...");
            }
        }


    );

