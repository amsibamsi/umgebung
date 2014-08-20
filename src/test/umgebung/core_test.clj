(ns umgebung.core-test
  (:require [clojure.test :refer :all]
            [umgebung.core :as c]))

(deftest read-sys
  (testing "Read a property from the system properties."
    (let [e (c/read-props (c/props (c/prop :java-version
                                           nil
                                           "Java Version"))
                          (c/get-sys)
                          c/key->sys)
          v (get e :java-version)]
      (is (get v :value) (get (System/getProperties)
                              "java.version")))))
