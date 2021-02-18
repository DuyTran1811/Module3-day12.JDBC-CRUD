package controller;

import model.SmartPhone;
import service.ISmartPhoneService;
import service.SmartPhoneService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SmartPhoneServlet", value = "/smartphones")
public class SmartPhoneServlet extends HttpServlet {
    private static final ISmartPhoneService smartPhoneService = new SmartPhoneService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreate(request, response);
                break;
            case "edit":
                showEdit(request, response);
                break;
            case "delete":
                deleteSmartPhone(request, response);
                break;
            default:
                listSmartPhone(request, response);
                break;
        }
    }

    private void deleteSmartPhone(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        smartPhoneService.delete(id);
        List<SmartPhone> list = smartPhoneService.fillAll();
        request.setAttribute("list", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        SmartPhone smartPhone = smartPhoneService.fillById(id);
        RequestDispatcher dispatcher;
        if (smartPhone == null) {
            dispatcher = request.getRequestDispatcher("index.jsp");
        } else {
            request.setAttribute("sp", smartPhone);
            dispatcher = request.getRequestDispatcher("view/edit.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void listSmartPhone(HttpServletRequest request, HttpServletResponse response) {
        List<SmartPhone> list = smartPhoneService.fillAll();
        request.setAttribute("list", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createSmartPhone(request, response);
                break;
            case "edit":
                editSmartPhone(request, response);
                break;
            case "search":
                sreachByName(request, response);
                break;
        }
    }

    private void editSmartPhone(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String brand = request.getParameter("brand");
        String name = request.getParameter("name");
        float price = Float.parseFloat(request.getParameter("price"));
        String year = request.getParameter("year");
        String sizecreen = request.getParameter("sizescreen");
        SmartPhone smartPhone = new SmartPhone(id, brand, name, price, year, sizecreen);
        smartPhoneService.update(smartPhone);
        try {
            response.sendRedirect("/smartphones");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createSmartPhone(HttpServletRequest request, HttpServletResponse response) {
        String brand = request.getParameter("brand");
        String name = request.getParameter("name");
        float price = Float.parseFloat(request.getParameter("price"));
        String year = request.getParameter("year");
        String sizescreen = request.getParameter("sizescreen");
        SmartPhone smartPhone = new SmartPhone(brand, name, price, year, sizescreen);
        smartPhoneService.create(smartPhone);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    private void sreachByName(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        List<SmartPhone> sp = smartPhoneService.searchByIdName(name);
        request.setAttribute("list",sp);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/list.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
