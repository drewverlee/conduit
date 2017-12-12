(ns conduit.usecases.article.query.get-comments-query-test
  (:require [conduit.usecases.article.query.get-comments-query :as sut]
            [clojure.test :refer :all]))

(def comments [{:id 1
                :createdAt "2016-02-18T03:22:56.637Z",
                :updatedAt "2016-02-18T03:22:56.637Z",
                :body "It takes a Jacobian",
                :author {:username "jake",
                         :bio "I work at statefarm",
                         :image "https://i.stack.imgur.com/xHWG8.jpg",
                         :following false}}
               {:id 2
                :createdAt "2017-02-18T03:22:56.637Z",
                :updatedAt "2017-02-18T03:22:56.637Z",
                :body "It takes a Other thing",
                :author {:username "Alice",
                         :bio "I work at statefarm",
                         :image "https://i.stack.imgur.com/xHWG8.jpg",
                         :following false}}])

(deftest get-article-comments-test
  (let [execute (sut/get-article-comments comments)]
    (testing "Get All"
      (let [result (execute)]
        (is (not-empty result))
        (is (= 2 (count result)))
        (is (= "It takes a Jacobian" (get (first result) :body)))))))
