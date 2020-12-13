; Разработать приложение для редактирования текста несколькими пользователями в многозадачном режиме,
; каждое действие пользователя заключается в замене определенного диапазона в тексте новым текстом,
; если два пользователя одновременно отредактировали один и тот же текст то применяются только изменения первого,
; но если пользователи редактируют разные части текста, то они не должны друг-другу мешать...

; Define some text...
(def lorem "Lorem ipsum dolor sit amet, consectetur adipiscing elit...")

; Make text as vector...
(def text (vec (seq (char-array lorem))))

; Make this text for changing...
(def textForChanging (atom text))

; Define some other text as vector...
(def otherText [1 2 3 4 5 6 7])

; Make function to change text with other, starting in some position...
(defn change-text [text, firstIndex, lastIndex, newText]
      (loop [index firstIndex, count (- lastIndex firstIndex), indexOfNewText 0, v []]
            (if (zero? count) text
                              (recur
                                    (inc index)
                                    (dec count)
                                    (inc indexOfNewText)
                                    (swap! text assoc index (newText indexOfNewText))))))


; Change some diapason of text with other integer vector...
(change-text textForChanging 1 5 otherText)