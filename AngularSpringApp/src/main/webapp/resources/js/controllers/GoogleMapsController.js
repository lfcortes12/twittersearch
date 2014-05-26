'use strict';

var GoogleMapsController = function($scope, $http, mySharedService) {
	 
	 angular.extend($scope, {
	        bogota: {
	        	lat: 4.605042,
	            lng: -74.092691,
	            zoom: 10
	        },
	        markers: {},
	        layers: {
	            baselayers: {
	                osm: {
	                    name: 'OpenStreetMap',
	                    url: 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
	                    type: 'xyz'
	                },

	            }
	        },
	        defaults: {
	            scrollWheelZoom: true
	        }
    });
	
	$scope.bogota.lat = 4.605042;
	$scope.bogota.lng = -74.092691;
     
    $scope.$on('handleBroadcast', function(event, mySharedService) {
    	 var i = 0;
    	 $scope.bogota.lat = 4.605042;
    	 $scope.bogota.lng = -74.092691;
    	 $scope.markers = {};
    	 
    	 for(var tweet in mySharedService.tweets) {
    		 var marker = new Object();
    		 marker.message = mySharedService.tweets[tweet].tweet;
    		 marker.lat = mySharedService.tweets[tweet].lat;
    		 marker.lng = mySharedService.tweets[tweet].lng;
    		 $scope.markers[i] = {
                 lat: Number(mySharedService.tweets[tweet].lat),
                 lng: Number(mySharedService.tweets[tweet].lng),
                 message: mySharedService.tweets[tweet].tweet
             };
    		 
    		 i++;
    	 }
    	 
     });
     


};