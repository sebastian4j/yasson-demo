package com.sebastian.yasson;

import java.io.InputStream;
import javax.json.Json;
import javax.json.bind.JsonbBuilder;

/**
 * demo usando jsonp y jsonb
 * @author sebastian
 */
public class YassonDemo {

    public static InputStream leer() {
        return YassonDemo.class.getClassLoader().getResourceAsStream("persona.json");
    }

    public static void main(String[] args) {
        System.out.println("JSONP=========");
        jsonp();
        System.out.println("JSONB=========");
        jsonb();
    }

    private static void jsonb() {
        var jb = JsonbBuilder.create();
        var p = jb.fromJson(leer(), Persona.class);
        System.out.println(p);
        System.out.println(jb.toJson(p));
    }

    private static void jsonp() {
        // memoria
        var reader = Json.createReader(leer());
        var jobj = reader.readObject();
        System.out.println(jobj);
        var json = Json.createObjectBuilder().add("nombre", "Elías").build();
        System.out.println(json);
        // eventos
        var parser = Json.createParser(YassonDemo.class.getClassLoader().getResourceAsStream("persona.json"));
        while (parser.hasNext()) {
            var e = parser.next();
            System.out.print(e.name());
            switch (e) {
                case KEY_NAME:
                    System.out.print(" - " + parser.getString());
                    break;
                case VALUE_STRING:
                    System.out.print(" - " + parser.getString());
                    break;
                case VALUE_NUMBER:
                    System.out.print(" - " + parser.getString());
            }
            System.out.println();
        }
    }

}
