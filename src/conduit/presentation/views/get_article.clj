(ns conduit.presentation.views.get-article
  (:require [hiccup.core :as h]))

(defn main [article]
  [:div
   [:h1 (get article :title)]
   [:h1 (get article :createdAt)]
   [:h1 (get article :updatedAt)]])

(defn view [article]
  (h/html (main article)))
