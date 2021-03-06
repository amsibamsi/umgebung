(ns umgebung.core
  (:require [umgebung.source :as s]
            [umgebung.property :as p]))

(defn prop
  "A property."
  ([key default doc]
   {:key key
    :default default
    :doc doc})
  ([key]
   {:key key}))

(defn prop-cfg
  "Lookup a set of properties in the environment and system properties. Values in the latter overwrite the former. Return a hashmap with keys/values."
  [props]
  (p/values (s/find-props [(s/env)
                           (s/sys)]
                          props)))

(defn prop-cfg-all
  "Like prop-env, but return the full property instead of just the values."
  [props]
  (p/propmap (s/find-props [(s/env)
                            (s/sys)]
                           props)))

(defn cfg
  "Lookup a set of keys in the environment and system properties. Values in the latter overwrite the former. Return a hashmap with keys/values."
  [keys]
    (prop-cfg (map prop
                   keys)))
