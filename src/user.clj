(ns user
  (:require [ring.adapter.jetty :as jetty]))

(defn hello []
  (println "Hello world"))

(defn handler [req]
  {:status  200
   :headers {"Content-Type" "text/plain"}
   :body    "Hello world"})

(comment
  ;; Start jetty web server
  (def _server
    (jetty/run-jetty handler {:port 3330 :join? false}))

  ;; Stop the server
  (.stop _server)
  )
