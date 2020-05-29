"use strict";

angular.module("user").component("login", {
  templateUrl: "app/user/login/login.template.html",
  controller: [
    "$location",
    "$scope",
    "$http",
    function ($location, $scope, $http) {
      var self = this;

      self.onSuccess = function () {
        // get the redirect url
        // default to the home screen
        var redirectUrl = $location.search().redirect;
        if (!redirectUrl) {
          redirectUrl = "/home";
        }

        // tell angular to set the url to the redirect
        $location.url(redirectUrl);
      };

      $scope.attemptAuthentication = function (form, user) {
        // we didn't get a valid user or username
        if (!user || !user.username || !user.password) {
          return;
        }

        $http
          .post("/user/login", user)
          .then(self.onSuccess, function (_reason) {
            $scope.error = "Invalid username or password";
            form.$setUntouched(); // clear the errors on textboxes
          });
      };
    },
  ],
});
