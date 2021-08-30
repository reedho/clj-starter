(ns user
  (:require [ring.adapter.jetty :as jetty]
            [ring.util.response]
            [compojure.core]
            [compojure.route]))

(defn hello []
  (println "Hello world"))

(defn handler [req]
  {:status  200
   :headers {"Content-Type" "text/plain"}
   :body    "Hello world"})

(defn handler1 [req]
  {:status  200
   :headers {"Content-Type" "text/plain"}
   :body    (-> (ring.util.response/response "Hello world")
                (ring.util.response/content-type "text/plain"))})


(compojure.core/defroutes app-route
  (compojure.core/GET "/" [] "Hello world")
  (compojure.core/GET "/ping" [] (-> (ring.util.response/response "PONG")
                                     (ring.util.response/content-type "text/plain")))
  (compojure.route/not-found "Page not found"))


(comment
  ;; Start jetty web server
  (def _server
    (jetty/run-jetty app-route {:port 3330 :join? false}))

  ;; Stop the server
  (.stop _server)
  )


(comment
  (require '[ring.util.response])
  (ring.util.response/response "Hello world")
  (ring.util.response/header {} "Content-Type" "text/plain")
  (ring.util.response/content-type {} "text/plain")
  (ring.util.response/redirect "https://google.com")
  (ring.util.response/file-response "README.md")
  )
