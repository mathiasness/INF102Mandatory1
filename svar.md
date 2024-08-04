# Runtime Analysis
For each method of the tasks give a runtime analysis in Big-O notation and a description of why it has this runtime.

**If you have implemented new methods not listed you must add these as well, e.g. any helper methods. You need to show how you analyzed any methods used by the methods listed below.**

The runtime should be expressed using these three parameters:
   * `n` - number of words in the list allWords
   * `m` - number of words in the list possibleWords
   * `k` - number of letters in the wordleWords

## Task 1 - matchWord
* `WordleAnswer::matchWord`: O(k)
    * I used HashMap to map each char and how many times the char occurs in the answer. The first step is to declare which letters are CORRECT, the next steps fills in WRONG_POSITION based on how many of the given char are left in the HashMap. Finally the rest are WRONG. The method consists of 4 for-loops with "k" iterations, and constants of O(1) operations. O(k) + O(k) = O(k). This results in a run time of O(k).
    Run time for each line is commented in the code.

## Task 2 - EliminateStrategy
* `WordleWordList::eliminateWords`: O(mk)
    * I created a new ArrayList, this is to minimize the number of operations instead of removing an element from the original list. Removing an element forces all indices behind to adjust to the change. The method consists of a for-loop with m iterations. Every iteration calls the isPossibleWord method which has a run time of O(k). This results in a run time of O(mk).
    Run time for each line is commented in the code.

## Task 3 - FrequencyStrategy
* `FrequencyStrategy::makeGuess`: O(mk)
    * The makeGuess method consists of two methods; eliminateWords from the previous strategy and findBestWordInList which returns a String with the word most likely to give green matches. findBestWord has a run time of O(mk).
    O(mk) + O(mk) = O(mk).
    Run time for each line is commented in the code.

    findBestWordInList:
    This method finds the best word based on a scoring system. The way it works is by creating a List of HashMaps which contains the frequency of each letter in each index. This list is created by a helper-method called indexCharScores. This method has a run time of O(mk). The method then iterates through every word in the list, and every letter in that word giving it a score. This double for-loop has m iterations and k iterations, resulting in 
    O(mk). Finally the word with the highest score is returned to be guessed. The first word will always be "slate".
    O(mk) + O(mk) = O(mk).

    indexCharScore:
    This method consist consists of a double for-loop with with k iterations and m iterations. The method creates a HashMap for each index in a word and saves each char and its frequency. 
    O(k) * O(m) = O(mk).

# Task 4 - Make your own (better) AI
For this task you do not need to give a runtime analysis. 
Instead, you must explain your code. What was your idea for getting a better result? What is your strategy?

MyOwnAi builds on the Frequency strategy. The method introduces a new method getInfo. If the possibleAnswers list contains more then 11 words, the strategy will use the next guess to get more info. I tried different constants for the list size and found that 11 was the best number. A smaller number, gives a more accurate guess. However, a bigger number gives the AI a greater chance of "getting lucky" and guessing the correct word in two guesses which is important for a good average score. MyOwnAI has an average guesses of 3.475 which is slightly better than the frequency strategy which has an average of 3.5.

getInfo:
This method creates a list of words from the allWords list, which have unique chars and does not contain any of the letters used in previous guesses. The method then returns a string to be guessed with the findBestWordInList method using the new list of unusedletters. This way the AI will not waste guesses on words with chars we already know.

wordIsAllUniqueChars:
This helper-method simply checks the given string for repeating letters. The method creates a HashSet and adds each char in the string to the set if the char is not already in the set. If the char is already in the set, the method will return false. Returns true if non-repeating, false if not.
