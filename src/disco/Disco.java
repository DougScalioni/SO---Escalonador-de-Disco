package disco;

import java.util.ArrayList;
import java.util.List;

public class Disco {

    int numSectors;
    int numTracks;
    int startPosition;
    int currentPosition;
    List<Requisicao> requisicoes;


    public Disco (int numSec, int numTra, int startPos, List<Requisicao> requisicoes){
        this.numSectors = numSec;
        this.numTracks = numTra;
        this.startPosition = startPos;
        this.currentPosition = this.startPosition;
        this.requisicoes = requisicoes;
    }

    public Disco(Disco another) {
        this.numSectors = another.numSectors;
        this.numTracks = another.numTracks;
        this.startPosition = another.startPosition;
        this.currentPosition = this.startPosition;
        List<Requisicao> copy = new ArrayList<Requisicao>(another.getRequisicoes());
        this.requisicoes = copy;
    }

    public void setRequisicoes (List<Requisicao> reqs){
        this.requisicoes = reqs;
    }

    public void setCurrentPosition (int pos){
        this.currentPosition = pos;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public int getNumSectors() {
        return numSectors;
    }

    public List<Requisicao> getRequisicoes() {
        return requisicoes;
    }

    public int goTo(int pos){
        int posPartida = currentPosition;
        setCurrentPosition(pos);
        return Math.abs(pos-posPartida);
    }

    @Override
    public String toString(){
        String reqs ="";
        for (Requisicao r: requisicoes){
            reqs += r.toString() + "\n";
        }
        return ("Numero de setores " +this.numSectors + "\nNumero de trilhas: " + this.numTracks +"\nPosiçao de inicio: "+this.startPosition + "\n" + reqs) ;
    }
}
