;; From this article
;; https://www.grammarly.com/blog/engineering/building-etl-pipelines-with-clojure-and-transducers/

(ns quietly.example-one
  (:require [clojure.java.io :as io]
            [cheshire.core :refer :all]))


(letfn [(rand-obj []
          (case (rand-int 3)
            0 {:type "number" :number (rand-int 1000)}
            1 {:type "string" :string (apply str (repeatedly 30 #(char (+ 33 (rand-int 90)))))}
            2 {:type "empty"}))]
  (with-open [f (io/writer "/tmp/dummy.json")]
    (binding [*out* f]
      (dotimes [_ 100000]
        (println (generate-string (rand-obj)))))))

;; {"type":"number","number":718}
;; {"type":"empty"}
;; {"type":"string","string":"@vp;vPWoRt]m#Ou#TW)`8BU)/x1>8f"}
;; {"type":"string","string":"J^!05JJ^(zYrKB+\"<GU=y#9E9yfn!f"}
;; {"type":"number","number":312}
;; {"type":"string","string":"';k/rK-1J1O,mqSJ3Xpdo-Y;[)5*MC"}
;; {"type":"number","number":3}
;; {"type":"empty"}
