package no.uib.inf102.wordle.model.word;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.swing.RowFilter.Entry;

import no.uib.inf102.wordle.resources.GetWords;

/**
 * This class describes a structure of two lists for a game of Wordle: The list
 * of words that can be used as guesses and the list of words that can be possible answers.
 */
public class WordleWordList {

	/**
	 * All words in the game. These words can be used as guesses.
	 */
	private List<String> allWords;

	/**
	 * A subset of <code>allWords</code>. <br>
	 * </br>
	 * These words can be the answer to a wordle game.
	 */
	private List<String> possibleAnswers;

	/**
	 * Create a WordleWordList that uses the full words and limited answers of the
	 * GetWords class.
	 */
	public WordleWordList() {
		this(GetWords.ALL_WORDS_LIST, GetWords.ANSWER_WORDS_LIST);
	}

	/**
	 * Create a WordleWordList with the given <code>words</code> as both guesses and
	 * answers.
	 * 
	 * @param words
	 */
	public WordleWordList(List<String> words) {
		this(words, words);
	}

	/**
	 * Create a WordleWordList with the given lists as guessing words and answers.
	 * <code>answers</code> must be a subset of <code>words</code>.
	 * 
	 * @param words   The list of words to be used as guesses
	 * @param answers The list of words to be used as answers
	 * @throws IllegalArgumentException if the given <code>answers</code> were not a
	 *                                  subset of <code>words</code>.
	 */
	public WordleWordList(List<String> words, List<String> answers) {
		HashSet<String> wordsSet = new HashSet<String>(words);
		if (!wordsSet.containsAll(answers))
			throw new IllegalArgumentException("The given answers were not a subset of the valid words.");

		this.allWords = new ArrayList<>(words);
		this.possibleAnswers = new ArrayList<>(answers);
	}

	/**
	 * Get the list of all guessing words.
	 * 
	 * @return all words
	 */
	public List<String> getAllWords() {
		return allWords;
	}

	/**
	 * Returns the list of possible answers.
	 * 
	 * @return
	 */
	public List<String> possibleAnswers() {
		return possibleAnswers;
	}

	/**
	 * Eliminates words from the possible answers list using the given
	 * <code>feedback</code>
	 * 
	 * @param feedback
	 */
	public void eliminateWords(WordleWord feedback) { //O(mk)
		List<String> newPossibleAnswers = new ArrayList<>(size()); //O(1)

		for (String word : possibleAnswers) { //m iterasjoner O(mk)
			if (WordleWord.isPossibleWord(word, feedback)) { //O(k)
				newPossibleAnswers.add(word); //O(1)
			}
		}

		possibleAnswers = newPossibleAnswers; //O(1)
	}

	/**
	 * Returns the amount of possible answers in this WordleWordList
	 * 
	 * @return size of
	 */
	public int size() {
		return possibleAnswers.size();
	}

	/**
	 * Removes the given <code>answer</code> from the list of possible answers.
	 * 
	 * @param answer
	 */
	public void remove(String answer) {
		possibleAnswers.remove(answer);

	}

	/**
	 * Returns the word length in the list of valid guesses.
	 * @return
	 */
	public int wordLength() {
		return allWords.get(0).length();
	}

	/////////////////////////////////////////////////////
	//Self-made methods for FrequencyStrategy and MyOwnAI
	/////////////////////////////////////////////////////

	/**
	 * Method finds the best word available in the list, based on a scoring system
	 * that counts the frequency of characters in each index of the words.
	 * @param list the list of words to chose from
	 * @return String, best word available in the list
	 */
	public String findBestWordInList(List<String> list) { //O(mk)
		List<HashMap<Character, Integer>> indexCharScores = indexCharScores(); //O(mk)

		int highestScore = 0; //O(1)
		String bestWord = ""; //O(1)
		for (String word : list) { //m iterasjoner O(mk)

			int score = 0; //O(1)
			for (int i = 0; i < wordLength(); i ++) {  //k iterasjoner O(k)
				char c = word.charAt(i); //O(1)
				score += indexCharScores.get(i).getOrDefault(c, 0); //O(1)
			}

			if (score > highestScore) { //O(1)
					highestScore = score; //O(1)
					bestWord = word; //O(1)
			}
		}

		return bestWord;
	}

	/**
	 * method counts frequency of each char in the given index of the words in the list
	 * @return List of HashMaps with frequency of each character in the each index
	 */
	private List<HashMap<Character, Integer>> indexCharScores() { //O(mk)
		List<HashMap<Character, Integer>> indexCharScores = new ArrayList<>(wordLength()); //O(1)

		for (int i = 0; i < wordLength(); i ++) { //k iterasjoner O(mk)
			HashMap<Character, Integer> charScores = new HashMap<>(); //O(1)

			for (String word : possibleAnswers ) { //m iterasjoner O(m)
				char c = word.charAt(i); //O(1)
				charScores.put(c, charScores.getOrDefault(c, 0) + 1); //O(1)
			}
			indexCharScores.add(charScores); //O(1)
		}
	
		return indexCharScores;
	}

	/**
	 * Method creates a list of words from the allWords list, that does not contain any 
	 * of the letters in the given string and have no repeating characters
	 * @param lettersUsed String of letters that are not allowed in the words
	 * @return List of words that does not contain any of the letters in the given string
	 */
	public String getInfo(String lettersUsed) { //O(nk)
		List<String> unusedLetterWords = new ArrayList<>(); //O(1)
		words:
			for (String word : allWords) { //n iterasjoner O(nk)
				if (!wordIsAllUniqueChars(word)) continue words; //O(k)
				for (int i = 0; i < lettersUsed.length(); i ++) { //k iterasjoner O(k)
					if (word.contains(lettersUsed.charAt(i) + "")) continue words; //O(1)
				}
				unusedLetterWords.add(word); //O(1)
			}

		if (unusedLetterWords.size() == 0) return findBestWordInList(possibleAnswers); //O(mk)
		else return findBestWordInList(unusedLetterWords); //O(nk)
	}

	/**
	 * Method checks if the given word has all unique characters
	 * @param word String to be checked
	 * @return boolean, true if all characters are unique, false if not
	 */
	private boolean wordIsAllUniqueChars(String word) { //O(k)
		HashSet<Character> charSet = new HashSet<>(); //O(1)

		for (int i = 0; i < wordLength(); i ++) { //k iterasjoner O(k)
			char c = word.charAt(i); //O(1)
			if (charSet.contains(c)) { //O(1)
				return false;
			} else {
				charSet.add(c); //O(1)
			}
		}

		return true;
	}

}
