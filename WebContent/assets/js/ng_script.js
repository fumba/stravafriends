var app = angular.module('stravaFriendsApp', []);

// Controller for retrieving athletes that do not follow the authenticated
// athlete back.
app
		.controller(
				'notFollowingController',
				[
						'$scope',
						'$http',
						'$window',
						function($scope, $http, $window) {

							$scope.getDataFromServer = function() {
								$http
										.get("retrieveNonFollowers.action")
										.success(
												function(data, status, headers,
														config) {
													$scope.data.athletes = data.athletesNotFollowingBack;
												}).error(
												function(data, status, headers,
														config) {
												});
							};
						} ]);

// Controller for retrieving athletes that follow you but are not followed back.
app
		.controller(
				'notFriendedController',
				[
						'$scope',
						'$http',
						'$window',
						function($scope, $http, $window) {

							$scope.getDataFromServer = function() {
								$http
										.get("retrieveNotFriended.action")
										.success(
												function(data, status, headers,
														config) {
													$scope.data.athletes = data.athletesNotFriendedBack;
												}).error(
												function(data, status, headers,
														config) {
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