(ns umgebung.core
  (:require [umgebung.source :as s]))

(defn prop
  "A property."
  [key default doc]
  {:key key
   :default default
   :doc doc})

(defn propmap
  "Convert a set of properties to a hashmap where the key is removed from the property and used in the hashmap."
  [props]
  (into {}
        (for [p props]
          [(:key p)
           (dissoc p :key)])))

(defn values
  "Convert a set of properties to a hashmap where only key and value from the property are added."
  [props]
  (into {}
        (for [p props]
          [(:key p)
           (:value p)])))

(defn env
  "Lookup a set of properties in the environment and system properties. Values in the latter overwrite the former. Return a hashmap with keys/values."
  [props]
  (values (s/find-props
             [(s/env)
              (s/sys)]
             props)))
