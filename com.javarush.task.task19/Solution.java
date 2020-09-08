package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        BufferedReader fReader = new BufferedReader(new FileReader(console.readLine()));
        BufferedReader ffReader = new BufferedReader(new FileReader(console.readLine()));
        console.close();

        List<String> fContent = new ArrayList<>();
        List<String> ffContent = new ArrayList<>();

        String line;
        while ((line = fReader.readLine()) != null) {
            fContent.add(line);
        }

        while ((line = ffReader.readLine()) != null) {
            ffContent.add(line);
        }
        ffReader.close();
        fReader.close();

        int i = 0;
        int j = 0;
        while (i < fContent.size() || j < ffContent.size()) {
            // if the end of the first file has been reached
            if (i == fContent.size()) {
                lines.add(new LineItem(Type.ADDED, ffContent.get(j)));
                break;
            }

            // if the end of the second file has been reached
            if (j == ffContent.size()) {
                lines.add(new LineItem(Type.REMOVED, fContent.get(i)));
                break;
            }

            if (fContent.get(i).equals(ffContent.get(j))) {
                lines.add(new LineItem(Type.SAME, fContent.get(i)));
                i++; j++;
            } else if (fContent.get(i + 1).equals(ffContent.get(j))) {
                lines.add(new LineItem(Type.REMOVED, fContent.get(i)));
                i++;
            } else if (fContent.get(i).equals(ffContent.get(j + 1))) {
                lines.add(new LineItem(Type.ADDED, ffContent.get(j)));
                j++;
            }
        }


//        for (LineItem lineItem: lines)
//            System.out.println(lineItem);
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }

//        @Override
//        public String toString() {
//            return type + " " + line;
//        }
    }
}
