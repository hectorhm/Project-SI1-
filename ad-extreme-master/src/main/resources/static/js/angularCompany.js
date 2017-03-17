var anunciantes = angular.module("AD-Extreme", []);
anunciantes.controller("anunciantesCompanyCtrl", ['$scope', '$http', '$rootScope', function($scope, $http, $rootScope) {
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

