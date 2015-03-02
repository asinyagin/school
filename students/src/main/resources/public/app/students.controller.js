(function () {
  angular.module('school-students')
      .controller('StudentsController', ['$scope', '$http', StudentController]);

  function StudentController($scope, $http) {
    $scope.students = [];
    $http.get('/students/')
        .success(function(data) {
          $scope.students = data;
        })
        .error(function() {
          console.log('error');
        });
  }
})();