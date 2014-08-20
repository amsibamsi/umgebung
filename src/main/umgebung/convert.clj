(ns umgebung.convert
  (:require [clojure.string :as s]))

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

(defn conv
  "A converter provides two conversion functions. One to translate keys to a source, and one to convert them from a source."
  [to-fn from-fn]
  {:to to-fn
   :from from-fn})

(defn conv-sys
  "The default system properties converter. Converts :a-key to 'a.key'."
  []
  (conv key->sys
        sys->key))

(defn conv-env
  "The default environment converter. Converts :a-key to 'A_KEY'."
  []
  (conv key->env
        env->key))

(defn to
  "Converts a key to a source."
  [conv key]
  (apply (get conv
              :to)
         key))

(defn from
  "Converts a key from a source."
  [conv key]
  (apply (get conv
              :from)
         key))
