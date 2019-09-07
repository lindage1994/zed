package com.example.zed;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Author: zed
 * @Date: 2019/8/5 17:33
 * @Description:
 */
public class ReadFile {
    public static void main(String[] args) throws Exception {

        Set<String> names1 = new TreeSet<>();
        Set<String> names2 = new TreeSet<>();

        File table1 = new File("E:\\table1.txt");
        File table2 = new File("E:\\table2.txt");

        InputStreamReader reader1 = new InputStreamReader(new FileInputStream(table1));
        BufferedReader br = new BufferedReader(reader1);

        String line = "";
        line = br.readLine();
        while (line != null) {
            names1.add(line.trim());
            line = br.readLine();
        }

        InputStreamReader reader2 = new InputStreamReader(new FileInputStream(table2));
        BufferedReader br2 = new BufferedReader(reader2);

        String line2 = "";
        line2 = br2.readLine();
        while (line2 != null) {
            names2.add(line2.trim());
            line2 = br2.readLine();
        }

        System.out.println(names1);
        System.out.println(names2);

        Set<String> union = new TreeSet<>();
        Set<String> inSet = new TreeSet<>();
        Set<String> diffSet = new TreeSet<>();

        union.addAll(names1);
        union.addAll(names2);

        inSet.addAll(names2);
        inSet.retainAll(names1);

        diffSet.addAll(names2);
        diffSet.removeAll(names1);

        System.out.println(union);
        System.out.println(inSet);
        System.out.println(union.size());
        System.out.println(inSet.size());
        System.out.println(diffSet);
        System.out.println(diffSet.size());

    }
}
