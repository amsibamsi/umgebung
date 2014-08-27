(ns umgebung.core
  (:require [umgebung.source :as s]
            [umgebung.property :as p]))

(defn prop
  "A property."
  [key default doc]
  {:key key
   :default default
   :doc doc})

(defn env
  "Lookup a set of keys in the environment and system properties. Values in the latter overwrite the former. Return a hashmap with keys/values."
  [keys]

  )

(defn prop-env
  "Lookup a set of properties in the environment and system properties. Values in the latter overwrite the former. Return a hashmap with keys/properties."
  [props]
  (p/values (s/find-props
              [(s/env)
               (s/sys)]
              props)))

(defn prop-env-all
  "Like env, but return the full property instead of just the values."
  [props]
  (p/propmap (s/find-props
               [(s/env)
                (s/sys)]
               props)))
