(ns wsserver.core
  (:require [clojure.data.json :as json])
  (:gen-class))

(defn timed-ping
  "Time an .isReachable ping to a given domain"
  [domain timeout]
  (let [addr (java.net.InetAddress/getByName domain)
        start (. System (nanoTime))
        result (.isReachable addr timeout)
        total (/ (double (- (. System (nanoTime)) start)) 1000000.0)]
    {:time total
     :result result}))
(defn ping
  [ip]
  (timed-ping ip 500))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(def nodes (json/read-json (slurp "nodes.json")))
 
(type nodes)
(:ip (first nodes))
(map :ip nodes)
(timed-ping (:ip (first nodes)) 500)
(def newnodes (map conj nodes (pmap ping (map :ip nodes))))
newnodes
