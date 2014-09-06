(ns umgebung.property-test
  (:require [clojure.test :refer :all]
            [umgebung.core :as c]
            [umgebung.property :as p]))

(deftest propmap
  []
  (testing "Creating property map from a set of properties."
    (is (= (p/propmap [(c/prop :key1 1 "1")
                       (c/prop :key2 2 "2")])
           {:key1 {:default 1
                   :doc "1"}
            :key2 {:default 2
                   :doc "2"}}))))

(deftest values
  []
  (testing "Extracting only values of a set of properties."
    (is (= (p/values [(assoc (c/prop :key1 1 "1") :value 1.0)
                      (assoc (c/prop :key2 2 "2") :value 2.0)])
           {:key1 1.0
            :key2 2.0}))))
