(function () {
  angular.module('school-students')
      .controller('StudentsController', ['$scope', '$http', '$interval', StudentController]);

  function StudentController($scope, $http, $interval) {
    $scope.students = [];
    loadStudents();
    $interval(loadStudents, 1000);

    function loadStudents() {
      $http.get('/students/')
          .success(function(data) {
            $scope.students = data;
          })
          .error(function() {
            console.log('error');
          });
    }
  }
})();