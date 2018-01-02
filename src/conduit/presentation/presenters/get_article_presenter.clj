(ns conduit.presentation.presenters.get-article-presenter
  (:require [clj-time.core :as time]
            [conduit.presentation.views.get-article :as view]))

(defn present [article]
  (-> article
      (update :createdAt str)
      (update :updatedAt str)
      (view/view)))
