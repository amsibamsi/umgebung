(ns umgebung.core
  (:require [umgebung.source :as s]))

(defn prop
  "A property."
  [key default doc]
  {:key key
   :default default
   :doc doc})

(defn read-prop
  "Read a property from a source. If there is a value found in source set :value and :source in the property."
  [prop src]
  (let [k (:key prop)
        v (s/lookup src
                    k)]
    (if (nil? v)
      prop
      (assoc prop
             :value
             v
             :source
             src))))

(defn read-props
  "Read a set of properties from src hashmap converting keys with the conv function to the format of src."
  [props src]
  (into {}
        (for [[k v] props]
          (read-prop k v src))))
