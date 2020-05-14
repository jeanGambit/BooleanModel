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
        req.setAttribute("smallSmartTime", 0);
        req.setAttribute("smallResult", null);
        req.setAttribute("smallCoeff", 0);
        req.setAttribute("smallTotal", 0);

        req.setAttribute("mediumSimpleTime", 0);
        req.setAttribute("mediumSmartTime", 0);
        req.setAttribute("mediumResult", null);
        req.setAttribute("mediumCoeff", 0);
        req.setAttribute("mediumTotal", 0);

        req.setAttribute("bigSimpleTime", 0);
        req.setAttribute("bigSmartTime", 0);
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
        long simpleSearchTime = 0;
        long smartSearchTime = 0;
        try {
            simpleSearchTime = service.calculateSimpleSearchTime(BooleanModelService.DB.SMALL, query);
            smartSearchTime = service.calculateSmartSearchTime(BooleanModelService.DB.SMALL, query);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("query", "Invalid Input");
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }
        ArrayList<String> result = service.getResult();

        req.setAttribute("smallSimpleTime", simpleSearchTime);
        req.setAttribute("smallSmartTime",  smartSearchTime);
        req.setAttribute("smallCoeff",   simpleSearchTime / smartSearchTime);
        req.setAttribute("smallResult",     result);
        req.setAttribute("smallTotal",      result.size());
        System.out.println("DataBase: SmallDB, SimpleSearchTime: " + simpleSearchTime + ", SmartSearchTime: " + smartSearchTime
                            + ", Coefficient: " + simpleSearchTime/smartSearchTime + ", Total files: " + result.size());
        System.out.println("Found files: " + result.toString());

        service.clearResults();
        try {
            simpleSearchTime = service.calculateSimpleSearchTime(BooleanModelService.DB.MEDIUM, query);
            smartSearchTime = service.calculateSmartSearchTime(BooleanModelService.DB.MEDIUM, query);
        } catch (Exception e) {
            e.printStackTrace();

        }
        result = service.getResult();

        req.setAttribute("mediumSimpleTime",  simpleSearchTime);
        req.setAttribute("mediumSmartTime", smartSearchTime);
        req.setAttribute("mediumCoeff",    simpleSearchTime / smartSearchTime);
        req.setAttribute("mediumResult",      result);
        req.setAttribute("mediumTotal",       result.size());
        System.out.println("DataBase: MediumDB, SimpleSearchTime: " + simpleSearchTime + ", SmartSearchTime: " + smartSearchTime
                + ", Coefficient: " + simpleSearchTime/smartSearchTime + ", Total files: " + result.size());
        System.out.println("Found files: " + result.toString());


        service.clearResults();
        try {
            simpleSearchTime = service.calculateSimpleSearchTime(BooleanModelService.DB.BIG, query);
            smartSearchTime = service.calculateSmartSearchTime(BooleanModelService.DB.BIG, query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result = service.getResult();

        req.setAttribute("bigSimpleTime",  simpleSearchTime);
        req.setAttribute("bigSmartTime", smartSearchTime);
        req.setAttribute("bigCoeff",    simpleSearchTime / smartSearchTime);
        req.setAttribute("bigResult",      result);
        req.setAttribute("bigTotal",       result.size());
        System.out.println("DataBase: BigDB, SimpleSearchTime: " + simpleSearchTime + ", SmartSearchTime: " + smartSearchTime
                + ", Coefficient: " + simpleSearchTime/smartSearchTime + ", Total files: " + result.size());
        System.out.println("Found files: " + result.toString());

        req.setAttribute("query", query);

        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }
}
