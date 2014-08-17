(ns umgebung.core
  (:require [clojure.string :as s]))

(defn prop
  "A property."
  [key value]
  {key value})

(defn prefs
  "A set of properties."
  [& props]
  (merge props))

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
  (s/replace (str key)
             #"-"
             "."))

(defn sys->key
  "Convert a system property name to a key."
  [name]
  (keyword (s/replace name
                      #"\."
                      "-")))

(defn read-env
  "Read the environment and return all keys/values."
  []
  (System/getenv))

(defn read-sys
  "Read the system properties and return all keys/values."
  []
  (System/getProperties))

(defn reader
  "A reader contains a read function to read keys/values and a converter function to translate keys from the source format."
  [read-fn conv-fn]
  )

(defn writer
  "A writer contains a write function to write keys/values and a converter function to translate keys to the target format."
  [write-fn conv-fn]
  )

(defn config
  "Contains preferences and one or more readers."
  [prefs reader & readers]
  )

(defn ???
  "Contains preferences and a writer."
  [prefs writer]
  )

;(defn get-env
;  "get environment configuration for configuration keys."
;  [keys]
;  (into {}
;        (for [[k v] (select-keys (System/getenv)
;                                 (map cfg->env
;                                      keys))]
;          [(keyword (env->cfg k)) v])))
