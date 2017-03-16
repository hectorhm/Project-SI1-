var companyanunciantes = angular.module("adextreme", []);
companyanunciantes.controller("anunciantesCompanyCtrl", ['$scope', '$http', function($scope, $http, $rootScope) {
	$scope.usuarios = [];
	$rootScope.usuario = {};

	function carregarusuarios(){
		$http({
			method:'GET', 
			url:'http://localhost:8080/anunciantes/'
		}).then(function sucessCallback(response){
			$scope.usuarios = response.data;
		}, function unsucessCallback(response){
			alert("Nenhum usuario cadastrado!")
		});
	}
	carregarusuarios();

	function anunciante(id){
		$http({
			method: 'GET',
			url:'http://localhost:8080/anunciantes/'+id
		}).then(function sucessCallback(response){
			$rootScope.id = id;
			window.open('/company/anunciantes/'+id);
		}, function unsucessCallback(response){
			alert("Usuario nao cadastrado!");
		});
	}

}]);



var companyanunciante = angular.module("adextreme", []);
companyanunciante.controller("anuncianteCompanyCtrl", ['$scope', '$http', function($scope, $http, $rootScope) {
	$scope.usuario = {};	

	function carregarusuario(){
		$http({
			method:'GET',
			url:'http://localhost:8080/anunciantes/'
		}).then(function sucessCallback(response){
			$scope.usuario = response.data;
		}, function unsucessCallback(response){
			window.open('/404');
		});
		carregarusuario();
	}
}]);
