(function () {
  angular.module('school-philosophers')
      .controller('PhilosophersController', ['$scope', '$http', '$interval', PhilosophersController]);

  function PhilosophersController($scope, $http, $interval) {
    $scope.philosophers = [];
    loadPhilosophers();
    $interval(loadPhilosophers, 1000);

    function loadPhilosophers() {
      $http.get('/philosophers/')
          .success(function(data) {
            $scope.philosophers = data;
          })
          .error(function() {
            console.log('error');
          });
    }
  }
})();