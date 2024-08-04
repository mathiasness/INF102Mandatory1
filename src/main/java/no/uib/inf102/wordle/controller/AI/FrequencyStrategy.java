package no.uib.inf102.wordle.controller.AI;

import no.uib.inf102.wordle.model.word.WordleWord;
import no.uib.inf102.wordle.model.word.WordleWordList;


/**
 * This strategy finds the word within the possible words which has the highest expected
 * number of green matches.
 */
public class FrequencyStrategy implements IStrategy {
    private WordleWordList guesses;

    public FrequencyStrategy() {
        reset();
    }

    @Override
    public String makeGuess(WordleWord feedback) { //O(mk)
        if (feedback != null) {
        	guesses.eliminateWords(feedback); //O(mk)
            return guesses.findBestWordInList(guesses.possibleAnswers()); //O(mk)
        } else return guesses.findBestWordInList(guesses.possibleAnswers()); //O(mk)
    }

    @Override
    public void reset() {
        guesses = new WordleWordList();
    }
}