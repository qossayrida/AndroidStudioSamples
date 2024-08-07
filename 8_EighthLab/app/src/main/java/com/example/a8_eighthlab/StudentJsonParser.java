package com.example.a8_eighthlab;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class StudentJsonParser {

    public static List<Student> getObjectFromJson(String json) {
        List<Student> students = new ArrayList<>();
        if (json == null || json.isEmpty()) {
            return students; // Return empty list if JSON is null or empty
        }

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject studentObject = jsonArray.getJSONObject(i);
                Student student = new Student();
                student.setID(studentObject.optInt("id", -1)); // Use default value if key is missing
                student.setName(studentObject.optString("name", "Unknown")); // Use default value if key is missing
                student.setAge(studentObject.optDouble("age", 0.0)); // Use default value if key is missing
                student.setCity(studentObject.optString("city", "Unknown")); // Use default value if key is missing

                students.add(student);
            }
        } catch (JSONException e) {
            e.printStackTrace(); // Log error message
        }
        return students;
    }
}

