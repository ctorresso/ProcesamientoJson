package com.company;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new Gson();
        Type saleListType = new TypeToken<ArrayList<Sale>>(){}.getType();
        List<Sale> sales = gson.fromJson(new FileReader ( "sales_array.json"), saleListType);
        Map<String,List<Sale>> listGroup= sales.stream().collect(Collectors.groupingBy(Sale :: getDepartment));
        listGroup.forEach((s, saleListGroup) -> System.out.println("Departamento - [" +s+ " - Cantidad de Ventas: " +saleListGroup.size() + " - Ventas Totales: " + saleListGroup.stream().mapToDouble(Sale::getSales).sum()+ "]"));
    }
}
