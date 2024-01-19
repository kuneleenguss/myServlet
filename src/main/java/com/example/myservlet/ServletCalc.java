package com.example.myservlet;


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
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/calc")
public class ServletCalc extends HttpServlet {
    Gson gson = new GsonBuilder().setPrettyPrinting().serializeSpecialFloatingPointValues().create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        response.setContentType("application/json;charset=utf-8");

        StringBuffer jb = new StringBuffer();
        String line;

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

        int a = jobj.get("a").getAsInt();
        int b = jobj.get("b").getAsInt();
        String math = jobj.get("math").getAsString();
        double result = switch (math) {

            case "*": yield a * b;

            case "-": yield a - b;

            case "+": yield a + b;

            case "/": if(b != 0)
                    yield a / b;
                else
                    yield Double.NaN;

            default:
                throw new IllegalStateException("Unexpected value: " + math);
        };

        Map<String, Double> resultMap = Map.of("result", result);

        PrintWriter pw = response.getWriter();
        pw.print(gson.toJson(resultMap));
    }
}
