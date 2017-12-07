(ns conduit.domain.article
  (:require [clojure.spec.alpha :as s]))


(s/def ::slug string?)
(s/def ::title string?)
(s/def ::description string?)
(s/def ::body string?)

(s/def ::tagList (s/coll-of string?))

(s/def ::createdAt inst?)
(s/def ::updatedAt inst?)
(s/def ::favorited boolean?)
(s/def ::favoritesCount int?)

(s/def ::username string?)
(s/def ::bio string?)
(s/def ::image string?)
(s/def ::following boolean?)
(s/def ::author (s/keys :req-un [::username ::bio ::image ::following]))

(s/def ::article
  (s/keys :req-un [::slug
                   ::title
                   ::description
                   ::body
                   ::tagList
                   ::createdAt
                   ::updatedAt
                   ::favorited
                   ::favoritesCount
                   ::author]))


(s/def ::articles (s/coll-of ::article))

(s/def ::articlesCount int?)
(s/def ::limit int?)
(s/def ::offset int?)
