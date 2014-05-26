'use strict';

var AngularSpringApp = {};

var App = angular.module('searchtweets', ['searchtweets.filters', 'searchtweets.services', 'searchtweets.directives', 'leaflet-directive']);

App.factory('mySharedService', function($rootScope) {
    var sharedService = {};
    
    sharedService.message = '';
    sharedService.tweets = null;

    sharedService.prepForBroadcast = function(msg, tweetsList) {
        this.message = msg;
        this.tweets = tweetsList;
        this.broadcastItem();
    };

    sharedService.broadcastItem = function() {
        $rootScope.$broadcast('handleBroadcast', this);
    };

    return sharedService;
});

// Declare app level module which depends on filters, and services
App.config(['$routeProvider', function ($routeProvider) {
	
    $routeProvider.when('/search', {
        templateUrl: 'search/layout',
        controller: SearchController
    });

    $routeProvider.otherwise({redirectTo: '/search'});
}]);

//SearchController.$inject = ['$scope', 'mySharedService'];
//GoogleMapsController.$inject = ['$scope', 'mySharedService'];
