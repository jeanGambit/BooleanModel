package cz.fit.cvut.BooleanModel.Parser;

import cz.fit.cvut.BooleanModel.Domain.DataBase;
import cz.fit.cvut.BooleanModel.Domain.LemmaStorage;
import cz.fit.cvut.BooleanModel.Lemmatizer.Lemmatizer;
import cz.fit.cvut.BooleanModel.Parser.AbstractSearchTree.*;

import java.util.*;

public class Parser {
    private Lemmatizer lemmatizer;
    private Queue<String> query;
    private DataBase dataBase;
    private TreeSet<Integer> docIds;
    private LemmaStorage lemmaStorage;

    public Parser(DataBase dataBase, LemmaStorage lemmaStorage) {
        this.lemmatizer = new Lemmatizer();
        this.query = new ArrayDeque<>();
        this.dataBase = dataBase;
        this.lemmaStorage = lemmaStorage;
        this.docIds = new TreeSet<>();
        for (int i = 0; i < dataBase.getDocuments().size(); i++) {
            this.docIds.add(i+1);
        }
    }

    public SearchExpression parse(String inputQuery) throws Exception {
        this.query = tokenize(inputQuery);
        return E();
    }

    private Queue<String> tokenize(String input) {
        Queue<String> tokens = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(input);
        while (st.hasMoreTokens()) {
            tokens.add(st.nextToken());
        }
        return tokens;
    }

    public SearchExpression E() throws Exception {
        if (query.isEmpty() || query.peek().equals("AND") || query.peek().equals("OR") || query.peek().equals(")")) {
            throw new Exception("Invalid query" + query.toString());
        }
        return E_1(T());
    }

    private SearchExpression E_1(SearchExpression leftOperand) throws Exception {
        if (query.isEmpty() || query.peek().equals(")")) {
            return leftOperand;
        }
        if (query.peek().equals("OR")) {
            query.remove();
            return new OrOperator(docIds, leftOperand, E_1(T()));
        }
        throw new Exception("Invalid query");
    }

    public SearchExpression T() throws Exception {
        if (query.isEmpty() || query.peek().equals("OR") || query.peek().equals("AND") || query.peek().equals(")"))
            throw new Exception("Invalid query");

        return T_1(F());
    }

    private SearchExpression T_1(SearchExpression leftOperand) throws Exception {
        if (query.isEmpty() || query.peek().equals("OR") || query.peek().equals(")")) {
            return leftOperand;
        }

        if (query.peek().equals("AND")) {
            query.remove();
            return new AndOperator(docIds, leftOperand, T_1(F()));
        }
        throw new Exception("Invalid query");
    }

    private SearchExpression F() throws Exception {
        if (query.isEmpty() || query.peek().equals("OR") || query.peek().equals("AND") || query.peek().equals(")")) {
            throw new Exception("Invalid query");
        }
        if (query.peek().equals("(")) {
            return F_1();
        }
        if (query.peek().equals("NOT")) {
            query.remove();
            return new NotOperator(docIds, F_1());
        }

        return F_1();
    }

    private SearchExpression F_1() throws Exception {
        if (query.isEmpty() || query.peek().equals("AND") || query.peek().equals("OR") || query.peek().equals(")") || query.peek().equals("NOT") ) {
            throw new Exception("Invalid query");
        }
        if (query.peek().equals("(")) {
            query.remove();
            SearchExpression tmp = E();
            if (query.isEmpty() /*|| !query.peek().equals(")")*/) {
                throw new Exception("Invalid query");
            }
            query.remove();
            return tmp;
        }
        String lemmaStr = query.peek();
        query.remove();
        return new LemmaNode(this.docIds, this.lemmaStorage.getLemma(this.lemmatizer.lemmatizeOne(lemmaStr)), this.dataBase);
    }
}
