package cn.lcf.entity;

import com.sun.org.apache.xalan.internal.xsltc.dom.ArrayNodeListIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by LCF on 2014/10/19.
 */
public class LetterFrequency {
    public LetterFrequency() {
    }

    private double frequency;
    private char letter;

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }
    public static List<LetterFrequency> generateData(){
        List<LetterFrequency> list = new ArrayList<LetterFrequency>();
        for(int i=0; i<10;i++){
            int l =i+65;
            LetterFrequency letterFrequency = new LetterFrequency();
            letterFrequency.setLetter((char)l);
            letterFrequency.setFrequency((double)new Random().nextInt(100)/(double)100);
            list.add(letterFrequency);
        }
        return list;
    }
}
