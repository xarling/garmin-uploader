'use strict';

angular.module('garminUploader', ['ui.router', 'ui.bootstrap', 'lodash']);

angular.module('garminUploader').config(['$stateProvider', '$urlRouterProvider', '$locationProvider', function ($stateProvider, $urlRouterProvider, $locationProvider) {

	$urlRouterProvider.otherwise("/home");

	$stateProvider
    .state('home', {
      url: "/home",
      templateUrl: "views/home.html"
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





	
	
	
	
	