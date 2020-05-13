package cz.fit.cvut.BooleanModel.Parser.AbstractSearchTree;

import cz.fit.cvut.BooleanModel.Domain.DataBase;
import cz.fit.cvut.BooleanModel.Domain.Lemma;

import java.util.TreeSet;

public class LemmaNode extends SearchExpression {
    private Lemma lemma;
    private DataBase dataBase;

    public LemmaNode(TreeSet<Integer> docIds, Lemma lemma, DataBase dataBase) {
        super(docIds);
        this.lemma = lemma;
        this.dataBase = dataBase;
    }

    @Override
    public TreeSet<Integer> getSmartSearchResult() {
        return (TreeSet<Integer>) this.lemma.getDocumentIds().clone();
    }

    @Override
    public TreeSet<Integer> getSimpleSearchResult() {
        TreeSet<Integer> res = new TreeSet<>();
        for (Integer i : this.docIds ) {
            if (this.dataBase.getDocumentById(i).getLemmas().contains(this.lemma.getLemma())) {
                res.add(i);
            }
        }
        return res;
    }
}
