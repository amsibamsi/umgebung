(ns umgebung.property)

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
