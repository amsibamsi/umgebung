(ns umgebung.core)

(defn prop
  "A property."
  [key default doc]
  {:key key
   :default default
   :doc doc})
