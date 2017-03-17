var anunciantes = angular.module("AD-Extreme", []);
anunciantes.controller("anunciantesUserCtrl", ['$scope', '$http', function($scope, $http) {
	$scope.usuarios = function(){
		$http.GET('/users').then(sucess, error);
	}
	function sucess(response){
		$scope.usuarios = response.data;
	}
	function error(){
		$scope.usuarios = [];
	}
}]);
