package org.whs.Tournament.Util;

public class OutputFormatter {
    public void title(String text) {
        horitzontalLine(text.length());
        System.out.println(text);
        horitzontalLine(text.length());
    }

    public void horitzontalLine(int length) {
        for (int i = 0 ; i < length; i++) {
            System.out.print("-");
        }
        System.out.print("\n");
    }

    public void slowText(String text) {
        String[] x = text.split("");
        try {
            for (int i=0; i< x.length; i++) {
                Thread.sleep(50);
                System.out.print(x[i]);
            }
        } catch (InterruptedException e)  {
         e.printStackTrace();
      }
        System.out.print("\n");
    }

    public void print(String msg) {
        System.out.println(msg);
    }
}