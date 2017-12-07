(ns conduit.usecases.article.command.create-article-test
  (:require [conduit.usecases.article.command.create-article-command :as sut]
            [clojure.test :refer :all]))

(deftest create-article-tests
  (let [article {:title "How to train your dragon"
                 :description "Ever wonder how?"
                 :body "You have to believe"
                 :tagList ["reactjs" "angularjs" "dragons"]}
        mock #(conj [] %)
        execute (sut/create-article mock)]

    (testing "Conforming Article added to database"
      (is (= article
             (execute article))))

    (testing "Empty article not added to database"
      (is (= nil
             (execute {}))))

    (testing "Unconforming article not added to database"
      (is (= nil
             (execute {:title "Title" :body "Body"}))))))


(deftest optional-taglist-article
  (let [article {:title "How to train your dragon"
                 :description "Ever wonder how?"
                 :body "You have to believe"}
        mock #(conj [] %)
        execute (sut/create-article mock)]

    (testing "Article with tag list added to database"
      (let [taglist ["clojure", "clean"]
            article_with_taglist (assoc article :tagList taglist)]
        (is (= article_with_taglist
               (execute article_with_taglist)))))

    (testing "Article without tag list added to database"
      (is (= article
             (execute article))))))
