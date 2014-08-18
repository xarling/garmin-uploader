'use strict';

angular.module('garminUploader').factory('ActivityService', ['$resource', function ($resource) {
  var UserService = $resource('/api/fit/:id', {id: '@id' }, {
      update: { method: 'PUT' },
      remove: { method: 'DELETE', params: { id: '@id' }, headers: { 'Content-Type': 'application/json' } }
    });
  return UserService;
}]);