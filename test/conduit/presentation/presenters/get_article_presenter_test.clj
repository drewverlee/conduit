(ns conduit.presentation.presenters.get-article-presenter-test
  (:require [clojure.test :refer :all]
            [conduit.presentation.presenters.get-article-presenter :as sut]
            [clj-time.core :as time]))

(def article {:slug "how-to-train-your-dragon"
              :title "How to train your dragon"
              :description "Ever wonder how?"
              :body "It takes a Jacobian"
              :tagList ["dragons", "training"]
              :createdAt (time/date-time 2016 2 18 3 22 56)
              :updatedAt (time/date-time 2016 2 18 3 48 35)
              :favorited false
              :favoritesCount 0
              :author {:username "jake"
                       :bio "I work at statefarm"
                       :image "https://i.stack.imgur.com/xHWG8.jpg"
                       :following false}})

(deftest present-article
  (let [result (sut/present article)]
    (testing "Created as is string"
      (is (string? (get result :createdAt)))
      (is (string? (get result :updatedAt))))))
