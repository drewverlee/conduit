(ns conduit.usecases.article.command.create-article-test
  (:require [conduit.usecases.article.command.create-article :as sut]
            [clojure.test :refer :all]))

(deftest create-article-tests
  (let [article {:title "How to train your dragon"
                 :description "Ever wonder how?"
                 :body "You have to believe"
                 :tagList ["reactjs" "angularjs" "dragons"]}
        mock #(conj [] %)]

    (testing "Article added to database"
      (is (= article
             ((sut/create-article mock) article))))

    (testing "Bad article not added to database"
      (is (= nil
             ((sut/create-article mock) {}))))

    (testing "Bad article not added to database"
      (is (= nil
             ((sut/create-article mock) {:title "Title" :body "Body"}))))))


(deftest optional-taglist-article
  (let [article {:title "How to train your dragon"
                 :description "Ever wonder how?"
                 :body "You have to believe"}
        mock #(conj [] %)]

    (testing "Article added to database"
      (is (= article
             ((sut/create-article mock) article))))

    (testing "Bad article not added to database"
      (is (= nil
             ((sut/create-article mock) {}))))))
