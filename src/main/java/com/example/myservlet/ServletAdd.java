package com.example.myservlet;

import com.example.myservlet.logic.Model;
import com.example.myservlet.logic.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.net.ssl.ManagerFactoryParameters;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/add")
public class ServletAdd extends HttpServlet {

    private AtomicInteger counter = new AtomicInteger(4);
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();

        StringBuffer jb = new StringBuffer();
        String line;

        String name = null;
        String surname = null;
        double salary = 0.0;

        if (!request.getParameterMap().isEmpty()) {
            name = request.getParameter("name").toString();
            surname = request.getParameter("surname").toString();
            salary = Double.parseDouble(request.getParameter("salary"));
        }

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
            name = jobj.get("name").getAsString();
            surname = jobj.get("surname").getAsString();
            salary = jobj.get("salary").getAsDouble();
        }

        if (!(name == null ||
                surname == null)) {

            User user = new User(name, surname, salary);
            model.add(user, counter.getAndIncrement());

            pw.print(gson.toJson(model.getFromList()));
        }
    }
}
