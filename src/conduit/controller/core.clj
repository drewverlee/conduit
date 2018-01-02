(ns conduit.controller.core
  (:require [bidi.bidi :as bidi]
            [conduit.usecases.article.query.get-article-query :as get-article-query]
            [conduit.presentation.presenters.get-article-presenter :as get-article-presenter]
            [conduit.usecases.article.query.get-articles-query :as get-articles-query]
            [conduit.presentation.presenters.get-articles-presenter :as get-articles-presenter]
            [conduit.infrastructure.database :as db]))

(def routes ["/" {"index.html" :index
                  "article/" {"" :article/index
                              [:slug] :article/slug}}])

(defmulti handler (fn [{:keys [handler]} fn] handler))

(defmethod handler :index
  [request f]
  "Home")

(defmethod handler :article/index
  [request f]
  (f {:tag "dragons"
      :username "jake"
      :favorited false}))

(defmethod handler :article/slug
  [request f]
  (f {:slug (get-in request [:route-params :slug])}))

(defmethod handler :default
  [request f]
  "Invalid")

(defn handle-request
  [request f]
  (handler (bidi/match-route routes request) f))

;; examples
;; => (handle-request "/article/" (get-articles-query/get-articles db/db get-articles-presenter/present))
;; "<div><ul><li>How to train your dragon</li></ul></div>"
;; => (handle-request "/article/how-to-train-your-dragon" (get-article-query/get-article db/db get-article-presenter/present))
;; "<div><h1>How to train your dragon</h1><h1>2016-02-18T03:22:56.637Z</h1><h1>2016-02-18T03:48:35.824Z</h1></div>"
