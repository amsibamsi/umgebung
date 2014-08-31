(ns umgebung.convert-test
  (:require [clojure.test :refer :all]
            [umgebung.convert :as v]))

(deftest key->env
  []
  (testing "Conversion from keys to environment variable names."
    (is (= (v/key->env :key)
           "KEY")
        "Simple key")
    (is (= (v/key->env :a-key)
           "A_KEY")
        "Two word key")))

(deftest env->key
  []
  (testing "Conversion from environment variables names to keys."
    (is (= (v/env->key "KEY")
           :key)
        "Simple key")
    (is (= (v/env->key "A_KEY")
           :a-key)
        "Two word key")))

(deftest key->sys
  []
  (testing "Conversion from keys to system property names."
    (is (= (v/key->sys :key)
           "key")
        "Simple key")
    (is (= (v/key->sys :a-key)
           "a.key")
        "Two word key")))

(deftest sys->key
  []
  (testing "Conversion from system property names to keys."
    (is (= (v/sys->key "key")
           :key)
        "Simple key")
    (is (= (v/sys->key "a.key")
           :a-key)
        "Two word key")))

(deftest to
  []
  (testing "Converting to the original key."
    (let [ce (v/env)
          cs (v/sys)]
      (is (= (v/to ce
                   :a-key)
             "A_KEY")
          "Convert to environment variable")
      (is (= (v/to cs
                   :a-key)
             "a.key")
          "Convert to system property"))))

(deftest from
  []
  (testing "Converting from the original key."
    (let [ce (v/env)
          cs (v/sys)]
      (is (= (v/from ce
                     "A_KEY")
             :a-key)
          "Convert from environment variable")
      (is (= (v/from cs
                     "a.key")
             :a-key)
          "Convert from system property"))))
