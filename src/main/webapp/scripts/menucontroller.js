'use strict';

/**
 * De menucontroller, actief op elke pagina
 * @param  {[type]} $scope
 * @param  {[type]} $location
 * @return {[type]}
 */
angular.module('garminUploader').controller('MenuController', ['$scope', '$location', function ($scope, $location) {
	
	$scope.isActive = function (viewLocation) { 
    if ($location.path() != undefined) {
      
      if($location.path().indexOf(viewLocation) > -1) {
      	return true;
      }
    } 
}}]);