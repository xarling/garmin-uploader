'use strict';


angular.module('garminUploader').controller('ActivityController', ['$scope', '_', 'ActivityService','UserService', function($scope, _, Activity, User) {

	$scope.users = User.query();
	$scope.activities = Activity.query();

	
	$scope.states = ['FILE_ON_DISK', 'TRIED_TO_UPLOAD_TO_STRAVA', 'CANNOT_UPLOAD_TO_STRAVA', 'UPLOADING_TO_STRAVA', 'UPLOADED_TO_STRAVA', 'MARKED_FOR_UPLOAD_TO_STRAVA'];
	
	$scope.sendToStrava = function(user, activity) {
		activity.status = 'MARKED_FOR_UPLOAD_TO_STRAVA';
		activity.user = user;

		Activity.update(activity);
	};
	
	$scope.changeStatus = function(newStatus, activity) {
		activity.status = newStatus;
		
		Activity.update(activity);
	}
	
	$scope.changeUser = function(user, activity) {
		activity.user = user;
		Activity.update(activity);
	}
	
	$scope.showSendToStravaBtn = function(status) {
		return (status === 'FILE_ON_DISK');
	}
	
}]);