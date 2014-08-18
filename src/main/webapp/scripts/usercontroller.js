'use strict';


angular.module('garminUploader').controller('UserController', ['$scope', 'UserService', '$window', function($scope, User, $window) {

	$scope.users = User.query();
	
	$scope.newUserUrl = encodeURI("https://www.strava.com/oauth/authorize?client_id=2364&response_type=code&redirect_uri=http://localhost:8080/api/token_exchange&scope=write&state=");
	
	$scope.submit = function() {
		$scope.submitted = true;
		
		$scope.sendBtnDisabled = true;
		
		User.save($scope.user, function (n) {
			$scope.sendBtnDisabled = false;
			$scope.users = User.query();
	    }, function (e) {
	    	$scope.sendBtnDisabled = true;
	    	console.error(e);
	    });
	};

	$scope.connectWithStrava = function(userId) {
		$window.location.href = $scope.newUserUrl + userId;
	};
	
}]);

