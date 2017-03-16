var Anunciantes = angular.module("Anunciantes", []);
Anunciantes.controller("anunciantesCtrl", ['$scope', '$http', function($scope, $http, $rootScope) {
	$scope.usuarios = {};

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
			window.open('/anunciantes/'+id);
		}, function unsucessCallback(response){
			alert("Usuario nao cadastrado!");
		});
	}

}]);



var Anunciante = angular.module("Anunciante", []);
Anunciante.controller("anuncianteCtrl", ['$scope', '$http', function($scope, $http, $rootScope) {
	$scope.usuario = {};	

	function carregarUsuario(){
		$http({
			method:'GET',
			url:'http://localhost:8080/anunciantes/'+$rootScope.id
		}).then(function sucessCallback(response){
			$scope.usuario = response.data;
		}, function unsucessCallback(response){
			window.open('/404');
		});
		carregarUsuario();
	}
}]);
