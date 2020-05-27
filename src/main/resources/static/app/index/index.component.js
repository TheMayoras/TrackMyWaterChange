"use strict";

angular.module("index").component("index", {
  templateUrl: "app/index/index.template.html",
  controller: function ($scope) {
    $scope.data = "Ben";
  },
});
