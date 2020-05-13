package cz.fit.cvut;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(
        name = "mainservlet",
        urlPatterns = "/BooleanModelApp"
)
public class MainServlet extends HttpServlet {

    private BooleanModelService service = new BooleanModelService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("smallSimpleTime", 0);
        req.setAttribute("smallBooleanTime", 0);
        req.setAttribute("smallResult", null);
        req.setAttribute("smallCoeff", 0);
        req.setAttribute("smallTotal", 0);

        req.setAttribute("mediumSimpleTime", 0);
        req.setAttribute("mediumBooleanTime", 0);
        req.setAttribute("mediumResult", null);
        req.setAttribute("mediumCoeff", 0);
        req.setAttribute("mediumTotal", 0);

        req.setAttribute("bigSimpleTime", 0);
        req.setAttribute("bigBooleanTime", 0);
        req.setAttribute("bigResult", null);
        req.setAttribute("bigCoeff", 0);
        req.setAttribute("bigTotal", 0);

        req.setAttribute("query", "Input query");

        RequestDispatcher view = req.getRequestDispatcher("index.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query;
        query = req.getParameter("query");
        if (query == null) {
            query = "";
        }
        System.out.println("Query: [" + query + "]");

        service.clearResults();
        long simpleSearchTime = service.calculateSimpleSearchTime(BooleanModelService.DB.SMALL, query);
        long booleanSearchTime = service.calculateBooleanSearchTime(BooleanModelService.DB.SMALL, query);
        ArrayList<String> result = service.getResult();

        req.setAttribute("smallSimpleTime",  simpleSearchTime);
        req.setAttribute("smallBooleanTime", booleanSearchTime);
        req.setAttribute("smallCoeff",    simpleSearchTime / booleanSearchTime);
        req.setAttribute("smallResult",      result);
        req.setAttribute("smallTotal",       result.size());
        System.out.println("DataBase: SmallDB, SimpleSearchTime: " + simpleSearchTime + ", SmartSearchTime: " + booleanSearchTime
                            + ", Coefficient: " + simpleSearchTime/booleanSearchTime + ", Total files: " + result.size());
        System.out.println("Found files: " + result.toString());

        service.clearResults();
        simpleSearchTime = service.calculateSimpleSearchTime(BooleanModelService.DB.MEDIUM, query);
        booleanSearchTime = service.calculateBooleanSearchTime(BooleanModelService.DB.MEDIUM, query);
        result = service.getResult();

        req.setAttribute("mediumSimpleTime",  simpleSearchTime);
        req.setAttribute("mediumBooleanTime", booleanSearchTime);
        req.setAttribute("mediumCoeff",    simpleSearchTime / booleanSearchTime);
        req.setAttribute("mediumResult",      result);
        req.setAttribute("mediumTotal",       result.size());
        System.out.println("DataBase: MediumDB, SimpleSearchTime: " + simpleSearchTime + ", SmartSearchTime: " + booleanSearchTime
                + ", Coefficient: " + simpleSearchTime/booleanSearchTime + ", Total files: " + result.size());
        System.out.println("Found files: " + result.toString());


        service.clearResults();
        simpleSearchTime = service.calculateSimpleSearchTime(BooleanModelService.DB.BIG, query);
        booleanSearchTime = service.calculateBooleanSearchTime(BooleanModelService.DB.BIG, query);
        result = service.getResult();

        req.setAttribute("bigSimpleTime",  simpleSearchTime);
        req.setAttribute("bigBooleanTime", booleanSearchTime);
        req.setAttribute("bigCoeff",    simpleSearchTime / booleanSearchTime);
        req.setAttribute("bigResult",      result);
        req.setAttribute("bigTotal",       result.size());
        System.out.println("DataBase: BigDB, SimpleSearchTime: " + simpleSearchTime + ", SmartSearchTime: " + booleanSearchTime
                + ", Coefficient: " + simpleSearchTime/booleanSearchTime + ", Total files: " + result.size());
        System.out.println("Found files: " + result.toString());

        req.setAttribute("query", query);

        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }
}
