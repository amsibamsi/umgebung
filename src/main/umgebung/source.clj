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
       (v/sys)))

(defn env
  "The default environment source."
  []
  (src "environment"
       (sys-env)
       (v/env)))

(defn value
  "Get the value for a key from a source."
  [src key]
  (get (:data src)
       (v/to (:conv src)
             key)))

(defn read-prop
  "Read a property from a source. If there is a value found in source set :value in the property to it."
  [src prop]
  (let [k (:key prop)
        v (value src
                 k)]
    (if (nil? v)
      prop
      (assoc prop
             :value
             v))))

(defn read-props
  "Read a set of properties from a source."
  [src props]
  (map #(read-prop src
                   %)
       props))

(defn find-props
  "Read a set of properties from multiple sources. Values found in later sources overwrite values from previous sources."
  [srcs props]
  (loop [s (first srcs)
         ss (rest srcs)
         p props]
    (if (empty? s)
      p
      (recur (first ss)
             (rest ss)
             (read-props s p)))))

(defn find-keys
  "Read a set of keys from multiple sources. Values found in later sources overwrite values from previous sources."
  )
