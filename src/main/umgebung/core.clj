(ns umgebung.core)

(defn prop
  "A property."
  [key default doc]
  {:key key
   :default default
   :doc doc})

(defn props
  "A set of properties."
  [& props]
  (merge props))

(defn read-props
  "Read a set of properties from src hashmap converting keys with the conv function to the format of src."
  [props src conv]
  (into {}
        (for [[k v] props]
          [k (assoc v
                    :value
                    (get src
                         (conv k)))])))
