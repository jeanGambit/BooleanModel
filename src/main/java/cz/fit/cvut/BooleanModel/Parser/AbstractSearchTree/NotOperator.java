package cz.fit.cvut.BooleanModel.Parser.AbstractSearchTree;


import java.util.TreeSet;

public class NotOperator extends SearchExpression {
    private SearchExpression expression;

    public NotOperator(TreeSet<Integer> docIds, SearchExpression expression) {
        super(docIds);
        this.expression = expression;
    }

    @Override
    public TreeSet<Integer> getSmartSearchResult() {
        TreeSet<Integer> tmp = new TreeSet<>(this.docIds);
        tmp.removeAll(this.expression.getSmartSearchResult());
        return tmp;
    }

    @Override
    public TreeSet<Integer> getSimpleSearchResult() {
        TreeSet<Integer> tmp = new TreeSet<>(this.docIds);
        tmp.removeAll(this.expression.getSimpleSearchResult());
        return tmp;
    }
}
