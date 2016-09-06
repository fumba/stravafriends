var app = angular.module('stravaFriendsApp', []);

//Controller for retrieving athletes that do not follow the authenticated athlete back. 
app.controller('notFollowingController', [
		'$scope',
		'$http',
		'$window',
		function($scope, $http, $window) {

			$scope.getDataFromServer = function() {
				$http.get("retrieveNonFollowers.action").success(
						function(data, status, headers, config) {
							$scope.data = data;
						}).error(function(data, status, headers, config) {
					// called asynchronously if an error occurs
					// or server returns response with an error status.
				});
			};
		} ]);

// -- GLOBAL CONTROLLER
app.controller('globalController', [ '$scope', '$http', '$window',
		function($scope, $http, $window) {
			$scope.setContent = function(filename) {
				$scope.content = filename;
			};
		} ]);