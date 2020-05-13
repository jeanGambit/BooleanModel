package cz.fit.cvut.BooleanModel.Parser.AbstractSearchTree;


import java.util.TreeSet;

public class AndOperator extends SearchExpression {
    private SearchExpression leftOperand;
    private SearchExpression rightOperand;

    public AndOperator(TreeSet<Integer> docIds, SearchExpression leftOperand, SearchExpression rightOperand) {
        super(docIds);
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public TreeSet<Integer> getSmartSearchResult() {
        TreeSet<Integer> tmp = leftOperand.getSmartSearchResult();
        tmp.retainAll(rightOperand.getSmartSearchResult());
        return tmp;
    }

    @Override
    public TreeSet<Integer> getSimpleSearchResult() {
        TreeSet<Integer> tmp = leftOperand.getSimpleSearchResult();
        tmp.retainAll(rightOperand.getSimpleSearchResult());
        return tmp;
    }
}
