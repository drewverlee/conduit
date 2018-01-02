(ns conduit.presentation.presenters.get-articles-presenter
  (:require [conduit.presentation.views.get-articles :as view]))

(defn present [articles]
  (view/view (map :title articles)))
