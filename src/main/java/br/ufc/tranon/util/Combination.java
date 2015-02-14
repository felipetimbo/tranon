package br.ufc.tranon.util;

import java.util.*;

public class Combination {

	public static List<List<Long>> getCombinations(List<Long> input, int size){

		//base case of requested combination length reached
		if (size == 0){
			List<List<Long>> result = new ArrayList<List<Long>>();
			List<Long> l = new ArrayList<Long>();
			result.add(l);
			return result;
		}

		//base case of empty List<Long>
		if (input.isEmpty())
			return new ArrayList<List<Long>>();

		List<List<Long>> finalResult = new ArrayList<List<Long>>();

		//take first character
		Long prefix = input.get(0);

		//delete first character from List<Long> now
		List<Long> restOfList = input.subList(1, input.size());

		//get all combinations of length k-1
		List<List<Long>> intermediate = getCombinations(restOfList, size-1);

		//add prefix back to all combinations of k-1 length
		for(List<Long> s : intermediate){
			s.add(0, prefix);
			finalResult.add(s);
		}

		//now get rest of combinations of length k
		List<List<Long>> tailResult = getCombinations(restOfList, size);

		//add all to the final result
		finalResult.addAll(tailResult);

		return finalResult;

	}
	
	public static void main(String[] args) {
		
		List<Long> list = new ArrayList<Long>();
		list.add(1l);
		list.add(2l);
		list.add(3l);
		list.add(4l);
		list.add(5l);
		list.add(6l);
		list.add(7l);
		list.add(8l);
		list.add(9l);
		list.add(10l);
		list.add(11l);
		list.add(12l);
		
		List<List<Long>> s = Combination.getCombinations(list, 2);
		for(List<Long> l : s){
			System.out.println(l);
			
		}
	}
}