package dev.manyroads.streams.inputstreams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try(BufferedReader buf = new BufferedReader(new InputStreamReader(System.in))){

        StringBuilder sb = new StringBuilder(buf.readLine());
            System.out.println(sb.reverse());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
