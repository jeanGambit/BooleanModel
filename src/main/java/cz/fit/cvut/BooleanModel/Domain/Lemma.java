package cz.fit.cvut.BooleanModel.Domain;

import java.util.Objects;
import java.util.TreeSet;

public class Lemma {
    private String lemma;
    private TreeSet<Integer> documentIds;

    public Lemma(String lemma) {
        this.lemma = lemma;
        this.documentIds = new TreeSet<>();
    }
    
    public Lemma(String lemma, Integer docId) {
        this.lemma = lemma;
        this.documentIds = new TreeSet<>();
        this.documentIds.add(docId);
    }
  
    public void addDoc ( Integer docId ) {
        this.documentIds.add(docId);
    }
    
    public String getLemma() {
        return lemma;
    }

    public TreeSet<Integer> getDocumentIds() {
        return documentIds;
    }

    @Override
    public String toString() {
        return lemma + ": documentIds = " + documentIds.toString();
    }
}
