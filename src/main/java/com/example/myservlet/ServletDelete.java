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
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/remove")
public class ServletDelete extends HttpServlet {
    Model model = Model.getInstance();

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer jb = new StringBuffer();
        String line;

        PrintWriter pw = response.getWriter();

        request.setCharacterEncoding("UTF-8");

        response.setContentType("application/json;charset=utf-8");

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

        int id = jobj.get("id").getAsInt();

        if (id > 0 && id <= model.getFromList().size()) {

            model.remove(id);

            pw.print(gson.toJson(model.getFromList()));
        }
    }
}
