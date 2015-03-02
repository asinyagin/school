(function () {
  angular.module('school-philosophers')
      .controller('PhilosophersController', ['$scope', '$http', PhilosophersController]);

  function PhilosophersController($scope, $http) {
    $scope.philosophers = [];
    $http.get('/philosophers/')
        .success(function(data) {
          $scope.philosophers = data;
        })
        .error(function() {
          console.log('error');
        });
  }
})();