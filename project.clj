(defproject avro-demo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.damballa/abracad "0.4.12"]
                 [clj-kafka "0.3.1"]]
  :main ^:skip-aot avro-demo.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
