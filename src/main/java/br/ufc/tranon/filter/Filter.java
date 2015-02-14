package br.ufc.tranon.filter;

public interface Filter<T,E> {
    public boolean isMatched(T object, E text);
}
