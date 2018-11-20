package disco;

public class Requisicao {

    private int A; //tempo de chegada
    private int B; //cilindro desejado   -> latency
    private int C; //trilha desejada     -> seektime
    private int D; //quantidade de dados -> transfer time

    public Requisicao (int A, int B, int C, int D){
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
    }

    public int getA() {
        return A;
    }

    public int getB() {
        return B;
    }

    public int getC() {
        return C;
    }

    public int getD() {
        return D;
    }


    @Override
    public String toString (){
        return ("A: "+ this.A +",B: "+ this.B +",C: "+ this.C +",D: "+ this.D );
    }

}
