package com.techelevator;

import java.util.Scanner;

/*
 In case you've ever pondered how much you weight on Mars, here's the calculation:
 	Wm = We * 0.378
 	where 'Wm' is the weight on Mars, and 'We' is the weight on Earth
 
Write a command line program which accepts a series of Earth weights from the user  
and displays each Earth weight as itself, and its Martian equivalent.

 $ MartianWeight 
 
Enter a series of Earth weights (space-separated): 98 235 185
 
 98 lbs. on Earth, is 37 lbs. on Mars.
 235 lbs. on Earth, is 88 lbs. on Mars.
 185 lbs. on Earth, is 69 lbs. on Mars. 
 */
public class MartianWeight {

	public static void main(String[] args) {
		System.out.print("Please enter the weights on Earth: ");

		Scanner input = new Scanner(System.in);

		String numbers = input.nextLine();

		String[] earthWeightsFromUser = numbers.split(" ");

		double[] earthWeights = new double[earthWeightsFromUser.length];

		for (int i = 0; i < earthWeights.length; i++){
			earthWeights[i] = Double.parseDouble(earthWeightsFromUser[i]);
		}
		double marsWeights = 0;



		for (int i = 0; i < earthWeights.length; i++){
			marsWeights = earthWeights[i] * 0.378;
			System.out.println(earthWeights[i]+ " lbs on Earth is equal to " + marsWeights + "lbs on Mars.");


		}



	}

}
