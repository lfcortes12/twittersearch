'use strict';

var AngularSpringApp = {};

var App = angular.module('searchtweets', ['searchtweets.filters', 'searchtweets.services', 'searchtweets.directives']);

// Declare app level module which depends on filters, and services
App.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/cars', {
        templateUrl: 'cars/layout',
        controller: CarController
    });

    $routeProvider.when('/trains', {
        templateUrl: 'trains/layout',
        controller: TrainController
    });
    
    $routeProvider.when('/railwaystations', {
        templateUrl: 'railwaystations/layout',
        controller: RailwayStationController
    });
    
    $routeProvider.when('/search', {
        templateUrl: 'search/layout',
        controller: SearchController
    });

    $routeProvider.otherwise({redirectTo: '/cars'});
}]);
