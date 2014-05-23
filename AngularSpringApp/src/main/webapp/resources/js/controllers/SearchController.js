'use strict';

/**
 * SearchController
 * @constructor
 */
var SearchController = function($scope, $http) {
    $scope.searchTweets = function(searchVo) {
        $http.get('search/tweets/' + searchVo.queryText).success(function(tweetList){
            $scope.tweets = tweetList;
        });
    };


};