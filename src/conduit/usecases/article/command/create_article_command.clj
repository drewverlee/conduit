(ns conduit.usecases.article.command.create-article-command
  (:require [clojure.spec.alpha :as s]
            [conduit.domain.article :as article]))


(s/def ::create-article-command
  (s/keys :req-un [::article/title
                   ::article/description
                   ::article/body]
          :opt-un [::article/tagList]))

(defn create-article
  [database]
  (fn [command]
    (when (s/valid? ::create-article-command command)
      database command)))
