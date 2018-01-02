(ns conduit.controller.core
  (:require [bidi.bidi :as bidi]))


(def routes ["/" {"index.html" :index
                  "articles/" {"articles.html" :article/index
                               [:id "/article.html"] :article/id}}])

(defmulti handler :handler)

(defmethod handler :index
  [request]
  "Home")

(defmethod handler :article/index
  [request]
  "Article Index")

(defmethod handler :article/id
  [request]
  (get-in request [:route-params :id]))

(defmethod handler :default
  [request]
  "Invalid")

(defn handle-request [request]
  (->> request
       (bidi/match-route routes)
       handler))

(bidi/match-route routes "/index.html")

(handle-request "/index.html")
(handle-request "/articles/articles.html")
(handle-request "/articles/123/article.html")
