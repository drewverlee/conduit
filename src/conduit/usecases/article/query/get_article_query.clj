(ns conduit.usecases.article.query.get-article-query
  (:require [clojure.spec.alpha :as s]
            [conduit.domain.article :as article]))

(s/def ::get-article-query
  (s/keys :req-un [::article/slug]))

(defn my-filter
  [f coll]
  (->> coll
       (filter f)
       first))

(defn get-article
  [database]
  (fn [query]
    (when (s/valid? ::get-article-query query)
      (let [{:keys [slug]} query]
        (->> database
             (my-filter #(= (get % :slug) slug)))))))
 
