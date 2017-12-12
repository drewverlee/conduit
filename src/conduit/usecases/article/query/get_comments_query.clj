(ns conduit.usecases.article.query.get-comments-query
  (:require [clojure.spec.alpha :as s]
            [conduit.domain.article :as article]))

(defn get-article-comments
  [database]
  (fn [] database))
