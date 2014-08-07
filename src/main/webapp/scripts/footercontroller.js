'use strict';

/**
 * De footer controller
 * @param  {[type]} $scope
 * @return {[type]}
 */
angular.module('garminUploader').controller('FooterController', ['$scope', function($scope) {
  $scope.fullYear = new Date().getFullYear();
}]);