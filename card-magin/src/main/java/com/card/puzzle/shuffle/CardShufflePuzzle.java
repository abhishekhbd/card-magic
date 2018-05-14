package com.card.puzzle.shuffle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Card Shuffle magic puzzle.
 * @author abhishek.sharma
 *
 */
public class CardShufflePuzzle {

	public static void main(String[] args) {
		CardShufflePuzzle cardShufflePuzzle = new CardShufflePuzzle();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Number of cards: \t");
		int numberOfCards=sc.nextInt();  
		List<Card> cardInHandTemp = new ArrayList<Card>();

		int k = 1;
		/**
		 * Add the known cards to the list in alternate position.
		 */
		for(int i = 0; i < numberOfCards; i++) {
			if(i%2==0){
				cardInHandTemp.add(cardShufflePuzzle.new Card(k, i));
				k++;
			} else {
				cardInHandTemp.add(cardShufflePuzzle.new Card(0, i));
			}
		}

		List<Card> cardInDesk = new ArrayList<Card>();

		int j = 0;
		while(!cardInHandTemp.isEmpty()){
			Card card = cardInHandTemp.get(0);
			if(j % 2 == 0 || cardInHandTemp.size() ==1){

				if(card.getNumber() == 0){
					card.setNumber(cardInDesk.get(cardInDesk.size() - 1).getNumber() + 1);
				}

				/**
				 * Remove the card from hand.
				 */
				cardInHandTemp.remove(0);

				/**
				 * Add card to the Desk.
				 */
				cardInDesk.add(card);

			}else{
				/**
				 * Add the card to the last position.
				 */
				cardInHandTemp.remove(0);
				cardInHandTemp.add(card);
			}
			j++;
		}

		System.out.println("Card in desk: " + cardInDesk);

		/**
		 * Sorting to getting the friendly list
		 */
		Collections.sort(cardInDesk, cardShufflePuzzle.new CardComparator());
		System.out.println("Sequence in hand: " + cardInDesk);
	}

	class CardComparator implements Comparator<Card>{

		@Override
		public int compare(Card o1, Card o2) {
			return (o1.getIndex() - o2.getIndex());
		}

	}

	class Card {

		/**
		 * Card Number.
		 */
		int number;

		/**
		 * Card index.
		 */
		int index;

		public Card(int number, int index) {
			this.number = number;
			this.index = index;
		}

		public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public int getIndex() {
			return index;
		}

		@Override
		public String toString() {
			return "Card [index=" + index + ", number=" + number + "]";
		}
	}
}