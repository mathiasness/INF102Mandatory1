# Wordle - Semester Assignment 1

<img align="right" src="images/Wordle_example.gif" width="400"/>

## Project Topic

The topic of this project was to implement **Wordle**. In this game, the goal is to guess a hidden 5-letter word. For each guess, the game provides feedback about whether the letters used were correct and in the correct position. The feedback is given in the form of colored tiles:
* **Green**: The letter in this position matches the secret word.
* **Yellow**: The letter exists in the secret word but not in this position.
* **Gray**: The letter is not in the word (at least after removing one copy for each tile already colored Green or Yellow).

The example on the right shows a game in progress where the word to guess was *Sunny*. When guessing a word, it has to be a word from the English dictionary. To make the game fair, the answer will be a relatively common word.

Wordle uses two lists of words: one includes all the words in the English dictionary, and the other includes the common words that can be the answer.

## Project Implementation

In this project, I completed the implementation of the game and developed an AI that can play the game.

To get a feel for the game, you can play it here: [Wordle Game](https://wordlegame.org/)

## Evaluation Criteria

All code submitted for this project was evaluated on five points:
- **Functional Correctness**: Ensuring the program performs as intended.
- **Quality of AI**: Assessing how well the AI can play the game.
- **Runtime**: Evaluating the efficiency of the solution.
- **Runtime Analysis**: Adding comments to each method about its Big-O runtime.
- **Code Quality**: Ensuring the code is readable and maintainable.

**IMPORTANT**: When implementing the code, a runtime analysis was provided in [svar.md](svar.md).

## Overview

The program uses the Model-View-Controller design pattern, similar to that of Tetris from INF101.

The most important classes in this program are:
- `WordleCharacter`: Represents a single letter in a guess.
- `Wordle`: Represents the game itself, containing the logic for playing the game.
- `WordleAI`: The class responsible for implementing the AI logic.

## Tasks

### Task 1: Implement Basic Functionality
In this task, I implemented the basic game mechanics: guessing a word and providing feedback based on the guess.

### Task 2: Create the AI
In this task, I developed an AI that can play the game by making intelligent guesses.

### Task 3: Optimize the AI
In this task, I optimized the AI to reduce the number of guesses it takes to find the correct word.

### Task 4: Advanced AI Strategies
In this task, I implemented advanced AI strategies to further enhance the AI's performance and compared my AI's performance to a baseline strategy such as `FrequencyStrategy`.

## Efficiency Considerations

Correct and appropriate use of algorithms and data structures was crucial in this project. I utilized `LinkedList`, `ArrayList`, `HashMap`, `HashSet`, `PriorityQueue`, etc., as needed. For each method, a Big-O runtime analysis was provided considering parameters like:
* `n` - number of words in the list allWords
* `m` - number of words in the list possibleWords
* `k` - number of letters in the wordleWords

## Author
Task was completed by Mathias Hop Ness, with a pre existing code base from the Institute of Informatics at UIB.