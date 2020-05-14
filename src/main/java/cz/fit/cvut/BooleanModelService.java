package cz.fit.cvut;

import cz.fit.cvut.BooleanModel.Domain.LemmaStorage;
import cz.fit.cvut.BooleanModel.Domain.Document;
import cz.fit.cvut.BooleanModel.Domain.DataBase;
import cz.fit.cvut.BooleanModel.Lemmatizer.Lemmatizer;
import cz.fit.cvut.BooleanModel.Parser.Parser;
import cz.fit.cvut.BooleanModel.Parser.AbstractSearchTree.SearchExpression;

import java.util.ArrayList;
import java.util.TreeSet;

public class BooleanModelService {
    private Parser smallParser;
    private Parser mediumParser;
    private Parser bigParser;

    private long booleanSearchTime;
    private long simpleSearchTime;
    private ArrayList<String> result;

    public enum DB {SMALL, MEDIUM, BIG}

    public BooleanModelService() {
        DataBase smallDB  = new DataBase("smallDB");
        LemmaStorage smallLemmaStorage  = new LemmaStorage();

        DataBase mediumDB = new DataBase("mediumDB");
        LemmaStorage mediumLemmaStorage = new LemmaStorage();

        DataBase bigDB    = new DataBase("bigDB");
        LemmaStorage bigLemmaStorage    = new LemmaStorage();

        Lemmatizer lemmatizer = new Lemmatizer();
        this.result = new ArrayList<>();

        /* Preprocessing */
        System.out.println("Preprocessing is started...");

        /* Extraction of terms and lemmatizing */
        for (Document document : smallDB.getDocuments())
            smallLemmaStorage.addLemmas(lemmatizer.lemmatize(document.getDocument()),  document.getFileId());
        System.out.println("SmallDB processed.");
        smallLemmaStorage.printStorage();

        for (Document document : mediumDB.getDocuments())
            mediumLemmaStorage.addLemmas(lemmatizer.lemmatize(document.getDocument()), document.getFileId());
        System.out.println("MediumDB processed.");
        mediumLemmaStorage.printStorage();

        for (Document document : bigDB.getDocuments())
            bigLemmaStorage.addLemmas(lemmatizer.lemmatize(document.getDocument()),    document.getFileId());
        System.out.println("BigDB processed");
        bigLemmaStorage.printStorage();

        smallParser  = new Parser(smallDB,  smallLemmaStorage);
        mediumParser = new Parser(mediumDB, mediumLemmaStorage);
        bigParser    = new Parser(bigDB,    bigLemmaStorage);

        System.out.println("Preprocessing is done.");
    }

    public long calculateSimpleSearchTime(DB db, String query) throws Exception {
        /* Query parsing */
        SearchExpression tree = parseQuery(db, query);

        /* Query execution */
        long start = System.nanoTime();
        tree.getSimpleSearchResult();
        this.simpleSearchTime = System.nanoTime() - start;

        return this.simpleSearchTime;
    }

    public long calculateSmartSearchTime(DB db, String query) throws Exception {
        TreeSet<Integer> res;
        /* Query parsing */
        SearchExpression tree = parseQuery(db, query);

        /* Query execution */
        long start = System.nanoTime();
        res = tree.getSmartSearchResult();
        this.booleanSearchTime = System.nanoTime() - start;

        for (Integer i: res) {
            result.add(i.toString());
        }

        return this.booleanSearchTime;
    }

    public ArrayList<String> getResult() {
        return this.result;
    }

    public void clearResults() {
        this.simpleSearchTime = 0;
        this.booleanSearchTime = 0;
        this.result = new ArrayList<>();
    }

    private SearchExpression parseQuery(DB db, String query) throws Exception {
        SearchExpression tmp = null;
        try {
            switch (db) {
                case SMALL:
                    tmp = smallParser.parse(query);
                    break;
                case MEDIUM:
                    tmp = mediumParser.parse(query);
                    break;
                case BIG:
                    tmp = bigParser.parse(query);
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Couldn't pars query");
            ex.printStackTrace();
            throw new Exception("Invalid query: " + query);
        }
        return tmp;
    }
}
