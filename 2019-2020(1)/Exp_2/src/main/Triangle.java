package main;

class Triangle {
    double a, b ,c;
    Triangle(double a, double b, double c){
        if (a+b<=c || a+c<=b || b+c<=a) {
            final double _a = Math.min(a, Math.min(b, c));
            this.a=this.b=this.c=_a;
            return;
        }

        this.a = a;
        this.b = b;
        this.c = c;
    }

    double area(){
        double p = (a+b+c)/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

}
