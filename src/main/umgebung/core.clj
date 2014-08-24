(ns umgebung.core)

(defn prop
  "A property."
  [key default doc]
  {key {:default default
        :doc doc}})

(defn props
  "A set of properties."
  [& props]
  (merge props))

(defn read-prop
  "Read a property from a source. If there is a value in source set :value and :source in the property's value."
  [key value src]
  (let [l (s/lookup src
                    key)]
    (if (nil? l
              [key value]
              [key (assoc value
                          :value
                          l
                          :source
                          src)]))))

(defn read-props
  "Read a set of properties from src hashmap converting keys with the conv function to the format of src."
  [props src]
  (into {}
        (for [[k v] props]
          (read-prop k v src))))
