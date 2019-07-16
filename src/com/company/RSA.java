package com.company;

import java.io.*;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RSA
{
    private BigInteger p;
    private BigInteger q;
    private BigInteger N;
    private BigInteger phi;
    private BigInteger e;
    private BigInteger d;
    private int bitlength = 1024;
    private Random r;

    public RSA()
    {
        r = new Random();
        p = BigInteger.probablePrime(bitlength, r);
        q = BigInteger.probablePrime(bitlength, r);
        N = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitlength / 2, r);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0)
        {
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);
        System.out.println("Your P = "+p);
        System.out.println("Your Q = "+q);
        System.out.println("Your N = "+N);
        System.out.println("Your Phi = "+phi);
        System.out.println("Your E = "+e);
        System.out.println("Your D = "+d);


    }

    public RSA(BigInteger e, BigInteger d, BigInteger N)
    {
        this.e = e;
        this.d = d;
        this.N = N;
    }


    public static void main(String[] args) throws IOException
    {
        PrintWriter out = null;
        PrintWriter dec = null;
        try {
            out = new PrintWriter("encrypt.txt");
            dec = new PrintWriter("decrypt.txt");
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }



        RSA rsa = new RSA();
        DataInputStream in = new DataInputStream(System.in);
        String teststring;
        System.out.println("Enter the plain text:");
        teststring = in.readLine();
        System.out.println("Encrypting String: " + teststring);
        System.out.println("String in Bytes: "
                + bytesToString(teststring.getBytes()));
// encrypt

        byte[] encrypted = rsa.encrypt(teststring.getBytes());
//        encrypt+=encrypted;
        out.print(bytesToString(encrypted));
        out.close();
// decrypt
        byte[] decrypted = rsa.decrypt(encrypted);
//        decrypt+=decrypted;
        dec.print(bytesToString(decrypted));
        dec.close();

        System.out.println("Decrypting Bytes: " + bytesToString(decrypted));
        System.out.println("Decrypted String: " + new String(decrypted));
    }

    private static String bytesToString(byte[] encrypted)
    {
        String test = "";
        for (byte b : encrypted)
        {
            test += Byte.toString(b)+" ";
        }
        return test;
    }

    // Encrypt message
    public byte[] encrypt(byte[] message)
    {
        return (new BigInteger(message)).modPow(e, N).toByteArray();
    }

    // Decrypt message
    public byte[] decrypt(byte[] message)
    {
        return (new BigInteger(message)).modPow(d, N).toByteArray();
    }


//    public static String Encrypt(int e, int n, String text,RSA rsa){
//        System.out.println("Start encr");
//        char encrypt_ch, decrypt;
//        BigInteger char_num = null;
//        for(int i=0; i<text.length(); i++){
//            encrypt_ch = text.charAt(i);
//            char_num = BigInteger.valueOf(encrypt_ch);
//
//            BigInteger sum = char_num;
//            for(int j=1; j<e; j++){
//                sum = sum.multiply(char_num);
//            }
//
//            sum = sum.mod(rsa.N);
//            decrypt = (char) sum.intValue();
//            encrypt += decrypt;
//        }
//        System.out.println("Return");
//        System.out.println(encrypt.toString());
//        return encrypt;
//    }
//
//    public static String Decrypt(int d, BigInteger n, String encrypt){
//        char decrypt_ch, convert_char;
//        int char_num;
//        for(int i=0; i<encrypt.length(); i++){
//            decrypt_ch = encrypt.charAt(i);
//            char_num = decrypt_ch;
//
//            BigInteger sum =BigInteger.valueOf(char_num);
//            BigInteger n2 = n;
//            for(int j=1; j<d; j++){
//                sum = sum.multiply(BigInteger.valueOf(char_num));
//            }
//            sum =  sum.mod(n2);
//            convert_char = (char) sum.intValue();
//            decrypt += convert_char;
//        }
//
//        System.out.println(decrypt.toString());
//        return decrypt;
//    }
}
