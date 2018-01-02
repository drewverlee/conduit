(ns conduit.presentation.presenters.get-article-presenter
  (:require [clj-time.core :as time]))

(defn present [article]
  (-> article
      (update :createdAt str)
      (update :updatedAt str)))
