"use strict";

angular
  .module("app")
  .config([
    "$routeProvider",
    function config($routeProvider) {
      $routeProvider
        .when("/", {
          template: "<index></index>",
        })
        .when("/user/login", {
          template: "<login></login>",
        })
        .otherwise("/");
    },
  ])
  .factory("httpResponseInterceptor", [
    "$q",
    "$rootScope",
    "$location",
    function ($q, $rootScope, $location) {
      return {
        responseError: function (rejection) {
          // response was 401, and not at login page, so redirect
          if (rejection.status === 401 && $location.path() !== "/user/login") {
            // redirect to the user login page
            var path = $location.path();
            $location.path("/user/login");
            $location.search({ redirect: path });
            return;
          }
          return $q.reject(rejection);
        },
      };
    },
  ])
  .config(function ($httpProvider) {
    $httpProvider.interceptors.push("httpResponseInterceptor");
  });
