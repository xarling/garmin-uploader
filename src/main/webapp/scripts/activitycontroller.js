'use strict';


angular.module('garminUploader').controller('ActivityController', ['$scope', '$resource', function($scope, $resource) {

	$scope.activities = $resource('/api/fit').query();
	$scope.stravaActivities = [];
}]);