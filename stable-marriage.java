//Jacob Hagen
//Project 1
//2/25/2022
//read in a formatted input file and finds instabilities according to the stable marriage problem


import java.util.*;
import java.io.*;
public class Project1{
	//this method checks if a person would prefer a target based on their preferences and their current match
	static boolean prefers(int prefs[][], int x, int target, int currentmatch, int n){
		for(int i=0; i < n; i++){
			if(prefs[x-1][i] == currentmatch){return true;}
			if(prefs[x-1][i] == target){return false;}
		}
		return false;
	}
	private static int checkStable(){
		Scanner sc = null;
      try {
		sc = new Scanner(new File("input.txt"));
	  }
	  catch(FileNotFoundException e) {
         System.out.println("Did you forget the input file?");
         System.exit(1);
      }
		//initialize all necessary arrays
		int pos = 0, n = sc.nextInt();
		int instabilities = 0;
		int[][] men = new int[n][n];
		int[][] women = new int[n][n];
		int[] menMatch = new int[n];
		int[] womenMatch = new int[n];
		//fill all the arrays
		for(int i =0;i<n;i++){
			for(int j =0;j<n;j++){
				men[i][j] = sc.nextInt();
			}
		}
		for(int i =0;i<n;i++){
			for(int j =0;j<n;j++){
				women[i][j] = sc.nextInt();
			}
		}
		for(int i=0; i<n;i++){
			int temp;
			int garb = sc.nextInt(); // this assumes the matches come in order 1-n so we discard the nextInt
			temp = sc.nextInt(); //read the match of index i
			womenMatch[temp-1] = i+1;
			menMatch[i] = temp;
		}
		for(int i =1;i<n+1;i++){ //loop through all possible matches
			for(int j =1;j<n+1;j++){
				if(menMatch[i-1] == j){continue;}//if current pair is already matched
				if(prefers(men, i, j, menMatch[i-1], n)){continue;} //if current woman is current man's preference
				if(prefers(women, j, i, womenMatch[j-1], n)){continue;}//if the current man is the current woman's preference
				instabilities++; //if the above lines are false there is an instability to count
			}
		}
		return instabilities;
	}
	public static void main(String args[]){
		System.out.println(checkStable());
	}
}