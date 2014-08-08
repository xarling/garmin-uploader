'use strict';


angular.module('garminUploader').controller('ActivityController', ['$scope', '$resource', function($scope, $resource) {

	$scope.garminActivities = $resource.get('/api/fit');
	$scope.stravaActivities = $resource.get('/api/activities');
}]);