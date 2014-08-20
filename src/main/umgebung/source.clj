(ns umgebung.source
  (:require [umgebung.core :as c]
            [umgebung.convert :as v]))

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

(defn src-sys
  "The default system properties source."
  []
  (src "system"
       (sys-props)
       c/conv-sys))

(defn src-env
  "The default environment source."
  []
  (src "environment"
       (sys-env)
       c/conv-env))

(defn get
  "Read a property from a source. Returns new property with the value set/overwritten."
  [src prop]
  (c/val prop
         (get (get src
                   :data)
              (v/to (get src
                         :conv)
                    (get prop
                         :key)))))
