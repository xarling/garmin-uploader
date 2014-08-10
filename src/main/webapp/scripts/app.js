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
    .state('newuser', {
      url: "/newuser",
      templateUrl: "views/newuser.html",
    });

}]);



/**
 * 
 * 
 * https://www.strava.com/oauth/authorize?
		  client_id=9
		  &response_type=code
		  &redirect_uri=http://testapp.com/token_exchange
		  &scope=write
		  &state=mystate
		  &approval_prompt=force	
 */





	
	
	
	
	