package cz.fit.cvut.BooleanModel.Parser.AbstractSearchTree;

import java.util.TreeSet;

public class OrOperator extends SearchExpression {
    private SearchExpression leftOperand;
    private SearchExpression rightOperand;

    public OrOperator(TreeSet<Integer> docIds, SearchExpression leftOperand, SearchExpression rightOperand) {
        super(docIds);
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public TreeSet<Integer> getSmartSearchResult() {
        TreeSet<Integer> tmp = leftOperand.getSmartSearchResult();
        tmp.addAll(rightOperand.getSmartSearchResult());
        return tmp;
    }

    @Override
    public TreeSet<Integer> getSimpleSearchResult() {
        TreeSet<Integer> tmp = leftOperand.getSimpleSearchResult();
        tmp.addAll(rightOperand.getSimpleSearchResult());
        return tmp;
    }
}
