(ns modern-cljs.core
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found files resources]]))

(defroutes handler
  (GET "/" [] "Hello from Compojure!") ;; logic
  (files "/" {:root "target"}) ;; static
  (resources "/" {:root "target"}) ;; others
  (not-found "Page Not Found")) ;; 404
