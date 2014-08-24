(ns umgebung.source
  (:require [umgebung.convert :as v]))

(defn sys-env
  "Return a hashmap with all environment keys/values."
  []
  (System/getenv))

(defn sys-props
  "Return a hashmap with all system property keys/values."
  []
  (System/getProperties))

(defn src
  "A source combines a name, a hashmap with key/value data and a converter."
  [name data conv]
  {:name name
   :data data
   :conv conv})

(defn sys
  "The default system properties source."
  []
  (src "system"
       (sys-props)
       v/sys))

(defn env
  "The default environment source."
  []
  (src "environment"
       (sys-env)
       v/env))

(defn lookup
  "Read a key from a source."
  [src key]
  (get (:data src)
       (v/to (:conv src)
             key)))
