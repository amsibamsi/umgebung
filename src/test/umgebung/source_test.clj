(ns umgebung.source-test
  (:require [clojure.test :refer :all]
            [umgebung.core :as c]
            [umgebung.source :as s]
            [umgebung.convert :as v]))

(defn mock-sys
  "A mocked system source."
  []
  (s/src "system mock"
         {"sys.key" "value1"
          "shared.key" "sysvalue"}
         (v/sys)))

(defn mock-env
  "A mocked environment source."
  []
  (s/src "environment mock"
         {"ENV_KEY" "value2"
          "SHARED_KEY" "envvalue"}
         (v/env)))

(defn mock-prop
  "A mocked property"
  []
  (c/prop :shared-key
          "defaultvalue"
          "test property"))

(deftest value
  []
  (testing "Getting a value from a source."
    (is (= (s/value (mock-sys)
                    :sys-key)
           "value1")
        "Get a system property")
    (is (= (s/value (mock-env)
                    :env-key)
           "value2")
        "Get a environment variable")))

(deftest read-prop
  []
  (testing "Reading a property from a source."
    (is (= (s/read-prop (mock-sys)
                        (mock-prop))
           (assoc (mock-prop)
                  :value
                  "sysvalue"))
        "Reading from system properties")
    (is (= (s/read-prop (mock-env)
                        (mock-prop))
           (assoc (mock-prop)
                  :value
                  "envvalue"))
        "Reading from environment")))

(deftest find-props
  []
  (testing "Finding a property in multiple sources"
    (is (= (s/find-props [(mock-env) (mock-sys)]
                         [(mock-prop)])
           [(assoc (mock-prop)
                   :value
                   "sysvalue")])
        "System props take precedence")
    (is (= (s/find-props [(mock-sys) (mock-env)]
                         [(mock-prop)])
           [(assoc (mock-prop)
                   :value
                   "envvalue")])
        "Environment takes precedence")))
