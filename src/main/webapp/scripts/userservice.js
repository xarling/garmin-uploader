'use strict';

angular.module('garminUploader').factory('UserService', ['$resource', function ($resource) {
  var UserService = $resource('/api/user/:id', {id: '@id' }, {
      update: { method: 'PUT' },
      remove: { method: 'DELETE', params: { id: '@id' }, headers: { 'Content-Type': 'application/json' } }
    });
  return UserService;
}]);