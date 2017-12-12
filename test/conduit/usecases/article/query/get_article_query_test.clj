(ns conduit.usecases.article.query.get-article-query-test
  (:require [conduit.usecases.article.query.get-article-query :as sut]
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

(deftest get-article-by-slug-tests
  (let [execute (sut/get-article db)]
    (testing "Get Alice article"
      (let [query {:slug "Alice in Wonderland"}
            result (execute query)]
        (is (not-empty result))
        (is (= "Alice in Wonderland" (get result :title)))))

    (testing "Article does not exist"
      (let [query {:slug "Some random long slug that does not exist"}
            result (execute query)]
        (is (nil? result))))

    (testing "Invalid query"
      (let [query {:slug nil}
            result (execute query)]
        (is (nil? result))))))
