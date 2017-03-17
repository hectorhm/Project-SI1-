var companyAnunciantes = angular.module("AD-Extreme", []);
companyAnunciantes.controller("anunciantesCompanyCtrl", ['$scope', '$http', '$rootScope', function($scope, $http, $rootScope) {
	$scope.usuarios = [];
	$rootScope.usuario = {};

	function carregarUsuarios(){
		$http({
			method:'GET', 
			url:'dados/anunciantes'
		}).then(function sucessCallback(response){
			$scope.usuarios = response.data;
		}, function unsucessCallback(response){
			console.log(response.status);
		});
	}
	carregarUsuarios();

	function anunciante(id){
		$http({
			method: 'GET',
			url:'dados/anunciantes/'+id
		}).then(function sucessCallback(response){
			window.open('/company/anunciantes/'+id);
		}, function unsucessCallback(response){
			console.log(response.status);
			alert("Usuario nao cadastrado!");
		});
	}

}]);

companyAnunciantes.controller("anuncianteCompanyCtrl", ['$scope', '$http', '$rootScope', function($scope, $http, $rootScope) {
	$scope.usuario = {};	

	function carregarUsuario(){
		$http({
			method:'GET',
			url:'http://localhost:8080/anunciantes'
		}).then(function sucessCallback(response){
			$scope.usuario = response.data;
		}, function unsucessCallback(response){
			window.open('/404');
		});
		carregarUsuario();
	}
}]);
