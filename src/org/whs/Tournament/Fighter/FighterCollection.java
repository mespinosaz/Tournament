package org.whs.Tournament.Fighter;

import java.util.ArrayList;
import java.util.Collections;

public class FighterCollection {
    private ArrayList<Fighter> collection;

    public FighterCollection() {
        collection = new ArrayList<Fighter>();
    }

    public FighterCollection(int size) {
        collection = new ArrayList<Fighter>(size);
    }

    public void shuffle() {
        Collections.shuffle(collection);
    }

    public void add(Fighter fighter) {
        collection.add(fighter);
    }

    public void set(int index, Fighter fighter) {
        collection.set(index,fighter);
    }

    public Fighter get(int index) {
        return collection.get(index);
    }

    public void reverse() {
        Collections.reverse(collection);
    }

    public int size() {
        return collection.size();
    }

    public void ensureCapacity(int capacity) {
        collection.ensureCapacity(capacity);
    }
}