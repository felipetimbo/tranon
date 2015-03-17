package br.ufc.tranon.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

public class Combinations<T> implements Iterable<List<T>>{
	
    private final T[] elements;
    private final int[] combinationIndices;
    private long remainingCombinations;
    private long totalCombinations;
    private final Set<Integer> indicesNaoComputaveis;
    private final List<T> destination;
    private final HashMap<T, Integer> elementsIndices;

    public Combinations(T[] elements, int combinationLength)
    {
        if (combinationLength > elements.length){
            throw new IllegalArgumentException("Combination length cannot be greater than set size.");
        }

        this.elements = elements.clone();
        this.combinationIndices = new int[combinationLength];
        this.indicesNaoComputaveis = Sets.newHashSet();
        this.destination = new ArrayList<T>(elements.length);
        this.elementsIndices = new HashMap<T, Integer>();
        
        for(int i = 0; i < elements.length; i++){
        	this.elementsIndices.put(elements[i], i);
        }

        BigInteger sizeFactorial = Maths.bigFactorial(elements.length);
        BigInteger lengthFactorial = Maths.bigFactorial(combinationLength);
        BigInteger differenceFactorial = Maths.bigFactorial(elements.length - combinationLength);
        BigInteger total = sizeFactorial.divide(differenceFactorial.multiply(lengthFactorial));
        
        if (total.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0){
            throw new IllegalArgumentException("Total number of combinations must not be more than 2^63."); 
        }

        totalCombinations = total.longValue();
        reset();
    }


    @SuppressWarnings("unchecked")
    public Combinations(Collection<T> elements,  int combinationLength){
        this(elements.toArray((T[]) new Object[elements.size()]),  combinationLength);
    }


    /**
     * Reset the combination generator.
     */
    public final void reset(){
        for (int i = 0; i < combinationIndices.length; i++){
            combinationIndices[i] = i;
        }
        remainingCombinations = totalCombinations;
    }

    public long getRemainingCombinations(){
        return remainingCombinations;
    }

    public boolean hasMore() {
		return nextCombinationIsValid();
    }

    private boolean nextCombinationIsValid() {
    	try{
	    	boolean combinacaoValida = false;
	    	boolean wasReseted = false;
	    	while(!combinacaoValida){
	    		
	    		combinacaoValida = true;
	    		if(!wasReseted){
		    		generateNextCombinationIndices();
	    		}
	    		destination.clear();
	    		for (int i = 0; i < combinationIndices.length; i++){
	    			if(!indicesNaoComputaveis.contains(combinationIndices[i])){
	    				destination.add(elements[combinationIndices[i]]);
	    				wasReseted = false;
	    			}else{
	    				reset2(i);
	    				combinacaoValida = false;
	    				wasReseted = true;
	    				break;
	    			}
	    		}
	    		
	    	}
    	}catch(Exception e){
    		return false;
    	}
    	return true;
	}

	private void reset2(int k) throws Exception{
		
		BigInteger sizeFactorial1 = Maths.bigFactorial(elements.length - combinationIndices[k]);
        BigInteger lengthFactorial1 = Maths.bigFactorial(combinationIndices.length - k);
        BigInteger differenceFactorial1 = Maths.bigFactorial(elements.length - combinationIndices[k] - (combinationIndices.length - k));

        BigInteger sizeFactorial2 = Maths.bigFactorial(elements.length - combinationIndices[k] - 1);
        BigInteger differenceFactorial2 = Maths.bigFactorial(Math.max(0, (elements.length - combinationIndices[k] - 1) - (combinationIndices.length - k)));
        
        BigInteger total1 = sizeFactorial1.divide(differenceFactorial1.multiply(lengthFactorial1));
        BigInteger total2 = sizeFactorial2.divide(differenceFactorial2.multiply(lengthFactorial1));
		
        BigInteger numCombinacoesASubtrair = total1.subtract(total2);
        remainingCombinations -= numCombinacoesASubtrair.intValue();
        
        while (combinationIndices[k] == elements.length - combinationIndices.length + k){
            k--;
        }
        ++combinationIndices[k];
        for (int j = k + 1; j < combinationIndices.length; j++){
            combinationIndices[j] = combinationIndices[k] + j - k;
        }
		
	}

    private void generateNextCombinationIndices(){
        if (remainingCombinations == 0){
            throw new IllegalStateException("There are no combinations remaining.");
        }
        else if (remainingCombinations < totalCombinations){
            int i = combinationIndices.length - 1;
            while (combinationIndices[i] == elements.length - combinationIndices.length + i){
                i--;
            }
            ++combinationIndices[i];
            for (int j = i + 1; j < combinationIndices.length; j++){
                combinationIndices[j] = combinationIndices[i] + j - i;
            }
        }
        --remainingCombinations;
    }

    public long getTotalCombinations() {
        return totalCombinations;
    }

    public List<T> nextCombination() {
        return destination;
    }
    
	public void setIndicesNaoComputaveis(T elementNaoComputavel) {
		this.indicesNaoComputaveis.add(elementsIndices.get(elementNaoComputavel)); 
	}
    
	   public Iterator<List<T>> iterator()
	    {
	        return new Iterator<List<T>>()
	        {
	            public boolean hasNext()
	            {
	                return hasMore();
	            }


	            public List<T> next()
	            {
	                return nextCombination();
	            }


	            public void remove()
	            {
	                throw new UnsupportedOperationException("Iterator does not support removal.");
	            }
	        };
	    }
	   
}