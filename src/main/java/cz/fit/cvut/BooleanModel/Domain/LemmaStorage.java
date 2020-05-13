package cz.fit.cvut.BooleanModel.Domain;

import java.util.*;

public class LemmaStorage{
    private HashMap<String, Lemma> storage;

    public LemmaStorage() {
        this.storage = new HashMap<>();
    }

    public void addLemmas (TreeSet<String> lemmas, Integer docId) {
        for (String lemma : lemmas) {
            if (storage.containsKey(lemma)) {
                storage.get(lemma).addDoc(docId);
            } else {        
                storage.put(lemma, new Lemma(lemma, docId));
            }
        }
    }
    
    public Lemma getLemma (String lemma) {
        Lemma tmp = storage.get(lemma);
        return tmp == null ? new Lemma("") : tmp;
    }

    public void printStorage() {
        this.storage.forEach((k,v) -> System.out.println(v.toString()));
        System.out.println( "Storage size: " + this.storage.size());
    }
}