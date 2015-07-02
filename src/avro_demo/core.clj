(ns avro-demo.core
  (:require [abracad.avro :as avro]
            [clojure.java.io :as io]
            [clj-kafka.zk :as zk]
            [clj-kafka.new.producer :as p]
            [clj-kafka.consumer.zk :as c]
            [clj-kafka.core :refer :all]))

(def vote-schema
  (avro/parse-schema (io/input-stream "resources/uservoteevent.avro")))

(def vote (->> ["hr" "john" true] (avro/binary-encoded vote-schema)))

(zk/brokers {"zookeeper.connect" "127.0.0.1:2181"})

(with-open [p (p/producer {"bootstrap.servers" "127.0.0.1:9092"} (p/byte-array-serializer) (p/byte-array-serializer))]
  @(p/send p (p/record "votes" (.getBytes "hello world!"))))

(with-open [p (p/producer {"bootstrap.servers" "127.0.0.1:9092"} (p/byte-array-serializer) (p/byte-array-serializer))]
  @(p/send p (p/record "vote" vote)))

(def config {"zookeeper.connect" "localhost:2181"
             "group.id" "clj-kafka.consumer"
             "auto.offset.reset" "smallest"
             "auto.commit.enable" "false"})

(def consum (c/consumer config))
(def stream (c/messages consum "vote"))
;(take 2 (c/messages consum "votess"))
