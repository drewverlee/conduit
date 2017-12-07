(ns conduit.usecases.article.query.get-articles-query
  (:require [clojure.spec.alpha :as s]
            [conduit.domain.article :as article]))


(s/def ::get-article-query
  (s/keys :req-un [::article/tag
                   ::article/username
                   ::article/favorited]
          :opt-un [::article/limit
                   ::article/offset]))

(defn get-article-list
  [database]
  (fn [query]
    (when (s/valid? ::get-article-query query)
      (let [tag (get query ::article/tag)
            limit (get query ::article/limit 20)
            offset (get query ::article/offset 0)
            author (get query ::article/author)
            favorited (get query ::article/favorited)
            created-at (get query ::article/createdAt 20)
            x (if tag (filter #(= % tag) database) database)
            y (if author (filter #(= % author) x) x)
            z (if favorited (filter #(= % favorited) y) y)]
      (->> z
           (take limit)
           (drop offset)
           (sort-by ::article/createdAt))))))
