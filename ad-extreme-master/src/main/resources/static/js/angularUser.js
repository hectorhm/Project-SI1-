var companyAnunciantes = angular.module("AD-Extreme", []);
companyAnunciantes.controller("anunciantesUserCtrl", ['$scope', '$http', function($scope, $http, $rootScope) {
	$scope.usuarios = [];
	$rootScope.usuario = {};

	function carregarUsuarios(){
		$http({
			method:'GET', 
			url:'http://localhost:8080/anunciantes/'
		}).then(function sucessCallback(response){
			$scope.usuarios = response.data;
		}, function unsucessCallback(response){
			alert("Nenhum usuario cadastrado!")
		});
	}
	carregarUsuarios();

	function anunciante(id){
		$http({
			method: 'GET',
			url:'http://localhost:8080/anunciantes/'+id
		}).then(function sucessCallback(response){
			$rootScope.id = id;
			window.open('/user/anunciantes/'+id);
		}, function unsucessCallback(response){
			alert("Usuario nao cadastrado!");
		});
	}

}]);



var companyAnunciante = angular.module("AD-Extreme", []);
companyAnunciante.controller("anuncianteUserCtrl", ['$scope', '$http', function($scope, $http, $rootScope) {
	$scope.usuario = {};	

	function carregarUsuario(){
		$http({
			method:'GET',
			url:'http://localhost:8080/anunciantes/'
		}).then(function sucessCallback(response){
			$scope.usuario = response.data;
		}, function unsucessCallback(response){
			window.open('/404');
		});
		carregarUsuario();
	}
}]);
