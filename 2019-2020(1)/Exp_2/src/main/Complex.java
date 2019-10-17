package main;

public class Complex {
    private int real, imagin;
    Complex() {
        real = 0;
        imagin = 0;
    }
    Complex(int real, int imagin){
        this.real = real;
        this.imagin = imagin;
    }
    Complex complexAdd(Complex complex){
        return new Complex(this.real+complex.real, this.imagin+complex.imagin);
    }

    @Override
    public String toString() {
        if(imagin >= 0) {
            return real+"+"+imagin+"i";
        } else {
            return real+""+imagin+"i";
        }
    }
}
