package cz.fit.cvut.BooleanModel.Domain;

import cz.fit.cvut.BooleanModel.Lemmatizer.Lemmatizer;

import java.util.*;

public class Document {
    private String document;
    private Integer fileId;
    private TreeSet<String> lemmas ;

    Document(String document, Integer id) {
        this.document = document;
        this.fileId = id;
        Lemmatizer lemmatizer = new Lemmatizer();
        this.lemmas = lemmatizer.lemmatize(document);
    }
    
    public String getDocument() {
        return document;
    }

    public Integer getFileId() { return fileId; }

    public TreeSet<String> getLemmas() {
        return lemmas;
    }   
    
}
