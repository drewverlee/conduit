(ns conduit.usecases.article.query.get-articles-query
  (:require [clojure.spec.alpha :as s]
            [conduit.domain.article :as article]))


(s/def ::get-article-query
  (s/keys :req-un [::article/tag
                   ::article/username
                   ::article/favorited]
          :opt-un [::article/limit
                   ::article/offset]))

(defn my-filter
  [ks v coll]
  (if (some? v)
    (filter #(= v (get-in % ks)) coll)
    coll))

(defn get-article-list
  [database]
  (fn [query]
    (when (s/valid? ::get-article-query query)
      (let [{:keys tag username favorited limit offset}
            :or {limit 20 offset 0}]
      (->> database
           (my-filter [:favorited] favorited)
