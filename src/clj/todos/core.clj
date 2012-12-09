(ns todos.core
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes
  ; Redirect to static index.html as root for now.
  (GET "/" [] (ring.util.response/redirect "/index.html"))
  ; Serve all static assets in resources/public
  (route/resources "/" )
  ; Handle 404
  (route/not-found "Page not found"))

(def handler
  (handler/site app-routes))
