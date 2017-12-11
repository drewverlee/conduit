(ns conduit.usecases.article.query.get-articles-query-test
  (:require [conduit.usecases.article.query.get-articles-query :as sut]
            [clojure.test :refer :all]))

(def db [{:slug "how-to-train-your-dragon",
          :title "How to train your dragon",
          :description "Ever wonder how?",
          :body "It takes a Jacobian",
          :tagList ["dragons", "training"],
          :createdAt "2016-02-18T03:22:56.637Z",
          :updatedAt "2016-02-18T03:48:35.824Z",
          :favorited false,
          :favoritesCount 0,
          :author {:username "jake",
                   :bio "I work at statefarm",
                   :image "https://i.stack.imgur.com/xHWG8.jpg",
                   :following false}}
         {:slug "Alice in Wonderland"
          :title "Alice in Wonderland"
          :description "Mushrooms"
          :body "I like mushrooms"
          :tagList ["alice", "mushrooms"],
          :createdAt "2017-02-18T03:22:56.637Z",
          :updatedAt "2017-02-18T03:48:35.824Z",
          :favorited true,
          :favoritesCount 0,
          :author {:username "alice",
                   :bio "I work at kingdom",
                   :image "https://i.stack.imgur.com/xHWG8.jpg",
                   :following false}}])

(deftest get-articles-by-favorited-tests
  (let [execute (sut/get-article-list db)]
    (testing "Get Favorited"
      (let [query {:tag "" :username "" :favorited true}
            result (execute query)]
        (is (not-empty result))
        (is (= 1 (count result)))
        (is (= "Alice in Wonderland" (get (first result) :title)))))

    (testing "Get Not Favorited"
      (let [query {:tag "" :username "" :favorited false}
            result (execute query)]
        (is (not-empty result))
        (is (= 1 (count result)))
        (is (= "How to train your dragon" (get (first result) :title)))))))

;; (deftest get-articles-by-author-tests
;;   (let [execute (sut/get-article-list db)]
;;     (testing "Get Jakes Articles"
;;       (let [query {:tag "" :username "jake" :favorited nil}
;;             result (execute query)]
;;         (is (not-empty result))
;;         (is (= 1 (count result)))
;;         (is (= "Alice in Wonderland" (get (first result) :title)))))

;;     (testing "Get Alice articles"
;;       (let [query {:tag "" :username "alice" :favorited nil}
;;             result (execute query)]
;;         (is (not-empty result))
;;         (is (= 1 (count result)))
;;         (is (= "How to train your dragon" (get (first result) :title)))))))
