(ns conduit.controller.core
  (:require [bidi.bidi :as bidi]
            [conduit.usecases.article.query.get-article-query :as get-article-query]
            [conduit.presentation.presenters.get-article-presenter :as get-article-presenter]
            [conduit.infrastructure.database :as db]))

(def routes ["/" {"index.html" :index
                  "article/" {"" :article/index
                              [:slug] :article/slug}}])

(defmulti handler :handler)

(defmethod handler :index
  [request]
  "Home")

(defmethod handler :article/index
  [request]
  "Article Index")

(defmethod handler :article/slug
  [request]
  ((get-article-query/get-article db/db get-article-presenter/present)
   {:slug (get-in request [:route-params :slug])}))

(defmethod handler :default
  [request]
  "Invalid")

(defn handle-request [request]
  (->> request
       (bidi/match-route routes)
       handler))

(handle-request "/article/how-to-train-your-dragon")
