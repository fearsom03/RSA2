package com.company;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class DH {
    private static BigInteger p,g;
    private static BigInteger knumber;
    private static Random r;

    public static void main(String[] args){
        int a,b,c;
        r = new Random();
        Scanner sc = new Scanner(System.in);
        System.out.println("Number for P");
        int randNum = r.nextInt(3000);
        if (randNum<0){
            randNum=r.nextInt(3000);
        }
//        p = BigInteger.probablePrime(1024,r);
        p=BigInteger.valueOf(randNum);
        while (p.intValue()<0){
            p = BigInteger.probablePrime(1024,r);
        }
        p=p.subtract(BigInteger.valueOf(1));
        p=p.nextProbablePrime();
        System.out.println("Your P is :" +p);
        for(int i=p.intValue()-1500; i<(p.intValue() - 1); i++){
                if (i>0){
                BigInteger number= BigInteger.valueOf(i-1);
                number=number.nextProbablePrime();
                g = number;
                break;}

        }
        System.out.println("1st persons key :");
        a = sc.nextInt();
        System.out.println("2nd persons key :");
        b = sc.nextInt();
        System.out.println("3rd persons key :");
        c = sc.nextInt();


        System.out.println("G is: " + g);
        a = finder(a);
        b = finder(b);
        c = finder(c);

        findK(a,b,c);

    }

    public static int finder(int s){
        knumber = g;
        for(int i=1; i<s; i++){
            knumber = knumber.multiply(g);
        }
        knumber = knumber.mod(p);
        return knumber.intValue();
    }

    public static void findK(int a, int b, int c){
        BigInteger sum = BigInteger.valueOf(c).multiply(BigInteger.valueOf(a).multiply(BigInteger.valueOf(b)));
        knumber = knumber.multiply(g);
        for(int i=1; i<sum.intValue(); i++){
            knumber = knumber.multiply(g);
        }
        knumber = knumber.mod(p);
        System.out.println("K is : " + knumber);
    }

    public static boolean checkPrime(int n){
        int i,m=0,flag=0;
        m=n/2;
        if(n==0||n==1){
            return false;
        }else{
            for(i=2;i<=m;i++){
                if(n%i==0){
                    flag=1;
                    break;
                }
            }
            if(flag==0){
                return true;
            }
            else{
                return false;
            }
        }
    }

}


