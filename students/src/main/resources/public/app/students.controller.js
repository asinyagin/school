(function () {
  angular.module('school-students')
      .controller('StudentsController', ['$scope', '$http', StudentController]);

  function StudentController($scope, $http) {
    $scope.students = [
      {
        id: 123,
        philosophers: [
          {
            name: 'Asdf',
            fired: true
          }
        ]
      }
    ];
    $http.get('/students/')
        .success(function(data) {
          $scope.students = data;
        })
        .error(function() {
          console.log('error');
        });
  }
})();