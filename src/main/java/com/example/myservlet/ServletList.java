package com.example.myservlet;

import com.example.myservlet.logic.Model;
import com.example.myservlet.logic.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/get")
public class ServletList extends HttpServlet {

    Model model = Model.getInstance();

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();

        StringBuffer jb = new StringBuffer();
        String line;

        int id = 0;

        if (!request.getParameterMap().isEmpty())
            id = Integer.parseInt(request.getParameter("id"));

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        }
        catch (Exception e) {
            System.out.println("Error");
        }

        JsonObject jobj = gson.fromJson(jb.toString(), JsonObject.class);

        if (request.getParameterMap().isEmpty()) {
                    id = (jobj.get("id").getAsString() == null)
                            ? 0
                            : jobj.get("id").getAsInt();
        }

        if (id == 0) {
//            Map<String, String> jsonMap = new HashMap<>();
//
//            Map<String, String[]> map = request.getParameterMap();
//            map.forEach((key,value) -> { jsonMap.put(key, value[0]); });
//
//            String jstring = gson.toJson(jsonMap);

            pw.print(gson.toJson(model.getFromList()));
        }
        else if (model.getById(id) != null) {

            pw.print(gson.toJson(model.getById(id)));
        }

//        if(id== 0) {
//            pw.print("<html>" +
//                    "<h3>Доступные пользователи:</h3><br/>" +
//                    "ID пользователя: " +
//                    "<ul>");
//
//            for (Map.Entry<Integer, User> entry : model.getFromList().entrySet()) {
//                pw.print("<li>" + entry.getKey() + "</li>" +
//                        "<ul>" +
//                        "<li>Имя: " + entry.getValue().getName() + "</li>" +
//                        "<li>Фамилия" + entry.getValue().getSurname() +
//                        "<li>Зарплата" + entry.getValue().getSalary() +
//                        "<ul>");
//            }
//            pw.print("</ul>" +
//                    "<a href=\"index.jsp\">Домой</a>" +
//                    "</html>"
//            );
//        }
//        else if (id > 0) {
//            if (id > model.getFromList().size()) {
//                pw.print("<html>" +
//                        "<h3>Такого пользователя нет</h3>" +
//                        "<a href=\"index.jsp\">Домой</a>" +
//                        "</html>");
//            } else {
//                pw.print("<html>" +
//                        "<h3>Запрошенный пользователь</h3>" +
//                        "<br/>" +
//                        "Имя:" + model.getFromList().get(id).getName() + "<br/>" +
//                        "Фамилия:" + model.getFromList().get(id).getSurname() + "<br/>" +
//                        "Зарплата:" + model.getFromList().get(id).getSalary() + "<br/>" +
//                        "<a href=\"index.jsp\">Домой</a>" +
//                        "</html>"
//                );
//            }
//        } else {
//            pw.print("<html>" +
//                    "<h3>ID должен быть больше нуля!</h3>" +
//                    "<a href=\"index.jsp\">Домой</a>" +
//                    "</html>");
//        }
    }
}
