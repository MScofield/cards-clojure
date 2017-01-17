(ns cards-clojure.core
  (:gen-class))

(def suits [:clubs :spades :hears :diamonds])
(def ranks (range 1 14))

(defn create-deck [] 
  (set
    (for [suit suits
          rank ranks]
      {:suit suit
       :rank rank})))

(defn create-hands [deck]
  (set
    (for [c1 deck
          c2 (disj deck c1)
          c3 (disj deck c1 c2)
          c4 (disj deck c1 c2 c3)]
      #{c1 c2 c3 c4})));;hashset hands of hashsets hand

(defn flush? [hand]
  ;; return true for each hand that contains only 1 suit
  (= 1 (count (set (map :suit hand)))))

(defn straight-flush? [hand]
  ;;return true for each hand that contains only 1 suit
  ;;AND contains four cards of sequential rank
  (let [ranks (map :rank hand)
        [e f g h] ranks
        sorted (sort [e f g h])
        [a b c d] sorted]
    (and (= a (- b 1) (- c 2) (- d 3))
       (= 1 (count (set (map :suit hand)))))))


(defn straight? [hand]
  ;;return true for each hand that contains
  ;;four cards of sequential rank
  (let [ranks (map :rank hand)
        [e f g h] ranks
        sorted (sort [e f g h])
        [a b c d] sorted]
    (= a (- b 1) (- c 2) (- d 3))))

(defn four-of-a-kind? [hand]
  ;;return true for each hand that contains only 1 rank
  (= 1 (count (set (map :rank hand)))))
       

(defn three-of-a-kind? [hand]
  ;;return true for each hand that contains
  ;;3 cards of matching rank
  (let [ranks (map :rank hand)
        [a b c d] ranks]
    (or (= a b c)
        (= b c d)
        (= a c d)
        (= a b d))))
        
(defn two-pair? [hand]
  ;;return true for each hand that contains two sets of equal rank
  (let [ranks (map :rank hand)
        [a b c d] ranks]
    (or (and (= a b)(= c d))
        (and (= a c)(= b d))
        (and (= a d)(= b c)))))
        
(defn -main []
  (let [deck (create-deck)
        hands (create-hands deck)
        flush-hands (filter flush? hands)
        straight-flush-hands (filter straight-flush? hands)
        straight-hands (filter straight? hands)
        four-of-a-kind-hands (filter four-of-a-kind? hands)
        three-of-a-kind-hands (filter three-of-a-kind? hands)
        two-pair-hands (filter two-pair? hands)]
    (println 
      "flushes:"(count flush-hands);;tested, working
      "straight-flushes:"(count straight-flush-hands);;tested, working
      "straights:"(count straight-hands);;tested, working
      "four-of-a-kind:"(count four-of-a-kind-hands);;tested, working
      "three-of-a-kind:"(count three-of-a-kind-hands);;tested, working
      "two-pair:"(count two-pair-hands)
      "your-mom:")));;tested, working
