package no.uib.inf102.wordle.controller.AI;

import no.uib.inf102.wordle.model.word.WordleWord;
import no.uib.inf102.wordle.model.word.WordleWordList;

/**
 * This strategy works in the same way as the FrequencyStrategy, but it has an extra step to
 * prevent wasted guesses. This strategy will instead use guesses to gain information when the list
 * of possible answers is too big.
 */
public class MyOwnAI implements IStrategy {
    private WordleWordList guesses;
    private String stringOfGuesses = "";

    public MyOwnAI() {
        reset();
    }

    @Override
    public String makeGuess(WordleWord feedback) {
        String guess;
        if (feedback != null) guesses.eliminateWords(feedback);
        
        if (feedback != null && guesses.size() > 11) guess = guesses.getInfo(stringOfGuesses);
        else guess = guesses.findBestWordInList(guesses.possibleAnswers());
        stringOfGuesses += guess;
        return guess; 
    }

    @Override
    public void reset() {
        guesses = new WordleWordList();
        stringOfGuesses = "";
    }
    
}
