'use strict';

angular.module('garminUploader', ['ui.router', 'ui.bootstrap', 'lodash', 'ngResource']);

angular.module('garminUploader').config(['$stateProvider', '$urlRouterProvider', '$locationProvider', '$resourceProvider', function ($stateProvider, $urlRouterProvider, $locationProvider, $resourceProvider) {

	$urlRouterProvider.otherwise("/home");

	$stateProvider
    .state('home', {
      url: "/home",
      templateUrl: "views/home.html"
    })
    .state('activities', {
    	controller: "ActivityController",
    	url: "/activities",
    	templateUrl: "views/activities.html"
    })
    .state('user', {
      url: "/user",
      controller: "UserController",
      templateUrl: "views/newuser.html",
    });

}]);






	
	
	
	
	