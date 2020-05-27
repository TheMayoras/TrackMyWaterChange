"use strict";

app.config([
  "$routeProvider",
  function config($routeProvider) {
    $routeProvider
      .otherwise({
        template: "<index></index>"
      });
  },
]);

