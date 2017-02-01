angular.module('TIYMastermindApp', ['ngDragDrop', 'ui.bootstrap'])
    .controller('GameController', function($scope, $http, $q) {

        $scope.sendUserGuess = function() {
            console.log("About to send the following guess: " + JSON.stringify($scope.userGuess));

            $http.post("/submit-user-guess.json", $scope.userGuess)
                .then(
                    function successCallback(response) {
                        console.log(response.data);
                        console.log("Adding data to scope");
                        processResults(response.data);
                        console.log("************ Processed Data **************");
                         console.log(response.data);
                        $scope.gameBoard = response.data;
                    },
                    function errorCallback(response) {
                        console.log("Unable to get data");
                    });
        };

        var processResults = function(gameBoard) {
            gameBoard.userGuesses.forEach(function(userGuess) {
                userGuess.matchingResults.forEach(function(matchingResult) {
                    console.log("processing matching result ...");
                    if (matchingResult.placementMatches) {
                        matchingResult.statusColor = 'black';
                    } else if (matchingResult.colorMatches) {
                        matchingResult.statusColor = 'silver';
                    }
                }, this);
            }, this);
        }



        $scope.testFunction = function() {
            console.log(JSON.stringify($scope.userGuess));
        };


      $scope.list1 = {title: 'Drag and Drop with default confirmation'};
      $scope.list2 = {};

      $scope.onDrop = function(event) {
        console.log("in onDrop() " + event.clientX);
        for (var propertyName in event) {
            console.log("***** " + propertyName);
        }

        for (var propertyName in event.target) {
            console.log("###### " + propertyName);
            console.log("       " + event.target[propertyName]);
        }
      }

      $scope.beforeDrop = function() {
        var deferred = $q.defer();
        if (confirm('Are you sure???')) {
          deferred.resolve();
        } else {
          deferred.reject();
        }
        return deferred.promise;
      };


        console.log("Initializing GameController");

        $scope.testDragSource = {};
        $scope.testDragSource.testText = "testing source";
        $scope.testDragDestination= {};
//        $scope.testDragSource.testText = "testing destination";

        $scope.userGuess = {};
    });

