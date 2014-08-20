(ns umgebung.core
  (:require [clojure.string :as s]))

(defn prop
  "A property."
  [key default doc]
  {key {:default default
        :doc doc}})

(defn props
  "A set of properties."
  [& props]
  (into {}
        props))

(defn key->env
  "Convert a key to an environment variable name."
  [key]
  (s/upper-case (s/replace (str key)
                           #"-"
                           "_")))

(defn env->key
  "Convert a environment variable name to a key."
  [name]
  (keyword (s/lower-case (s/replace name
                                    #"_"
                                    "-"))))

(defn key->sys
  "Convert a key to a system property name."
  [key]
  (s/replace (name key)
             #"-"
             "."))

(defn sys->key
  "Convert a system property name to a key."
  [name]
  (keyword (s/replace name
                      #"\."
                      "-")))

(defn get-env
  "Read the environment and return all keys/values."
  []
  (System/getenv))

(defn get-sys
  "Read the system properties and return all keys/values."
  []
  (System/getProperties))

(defn read-props
  "Read a set of properties from src hashmap converting keys with the conv function to the format of src."
  [props src conv]
  (into {}
        (for [[k v] props]
          [k (assoc v
                    :value
                    (get src
                         (conv k)))])))

;(defn get-env
;  "get environment configuration for configuration keys."
;  [keys]
;  (into {}
;        (for [[k v] (select-keys (System/getenv)
;                                 (map cfg->env
;                                      keys))]
;          [(keyword (env->cfg k)) v])))
