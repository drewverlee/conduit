(ns conduit.usecases.article.query.get-articles-query
  (:require [clojure.spec.alpha :as s]
            [conduit.domain.article :as article]))

(s/def ::get-article-query
  (s/keys :opt-un [::article/tag
                   ::article/username
                   ::article/favorited
                   ::article/limit
                   ::article/offset]))

(defn my-filter
  [ks v coll]
  (if (some? v)
    (filter #(= v (get-in % ks)) coll)
    coll))

(defn my-coll-filter
  [ks v articles]
  (if (some? v)
    (filter (fn [article]
              (some #{v} (get-in article ks)))
            articles)
    articles))

(defn get-article-list
  [database]
  (fn [query]
    (when (s/valid? ::get-article-query query)
      (let [{:keys [tag username favorited limit offset]
             :or {limit 20 offset 0}} query]
        (->> database
             (my-filter [:author :username] username)
             (my-coll-filter [:tagList] tag)
             (my-filter [:favorited] favorited))))))
