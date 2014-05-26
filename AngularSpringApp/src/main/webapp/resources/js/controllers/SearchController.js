'use strict';

/**
 * SearchController
 * @constructor
 */
var SearchController = function($scope, $http) {
	$scope.searchButtonLabel = "Buscar";
	$scope.searching = false;
    $scope.searchTweets = function(searchVo) {
    	$scope.searching = true;
    	$scope.searchButtonLabel = "Buscando ...";
        $http.get('search/tweets/' + searchVo.queryText).success(function(tweetList){
        	$scope.searchButtonLabel = "Buscar";
        	$scope.searching = false;
            $scope.tweets = tweetList;
        });
    };


};