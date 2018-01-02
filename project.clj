(defproject conduit "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-RC2"]
                 [org.clojure/spec.alpha "0.1.143"]
                 [org.clojure/test.check "0.10.0-alpha2"]
                 [bidi "2.1.2"]
                 [clj-time "0.14.2"]
                 [hiccup "2.0.0-alpha1"]
                 [integrant "0.6.3"]]
  :main ^:skip-aot conduit.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
