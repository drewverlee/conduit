(ns conduit.usecases.article.command.create-article
  (:require [clojure.spec.alpha :as s]))

(s/def ::title string?)
(s/def ::description string?)
(s/def ::body string?)
(s/def ::tag string?)
(s/def ::tagList (s/coll-of ::tag))


(s/def ::create-article-command
  (s/keys :req-un [::title
                   ::description
                   ::body]
          :opt-un [::tagList]))

(defn create-article
  [database]
  (fn [article]
    (when (s/valid? ::create-article-command article)
      database article)))
