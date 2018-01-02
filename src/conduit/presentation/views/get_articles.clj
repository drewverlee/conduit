(ns conduit.presentation.views.get-articles
  (:require [hiccup.core :as h]))

(defn main [articles]
  [:div
   [:ul
    (for [article articles]
      [:li article])]])

(defn view [articles]
  (h/html (main articles)))
