package cz.fit.cvut.BooleanModel.Parser.AbstractSearchTree;

import java.util.TreeSet;

public abstract class SearchExpression {
    protected TreeSet<Integer> docIds;

    public SearchExpression(TreeSet<Integer> docIds) {
        this.docIds = docIds;
    }

    public abstract TreeSet<Integer> getSmartSearchResult();

    public abstract TreeSet<Integer> getSimpleSearchResult();
}
