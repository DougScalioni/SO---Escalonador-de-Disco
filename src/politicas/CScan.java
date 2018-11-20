package politicas;

import disco.Disco;
import disco.Requisicao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CScan {

    public static void CScan (Disco ds){
        Disco disco = new Disco(ds);
        List<Requisicao> reqs = disco.getRequisicoes();
        int totalAccessTime = 0;
        int totalWaitTime = 0;
        int currentTime = 0;

        Collections.sort(reqs, Comparator.comparing(Requisicao::getB));
        int nReqs = reqs.size();

        int i = 0;

        while (i<reqs.size()-1&&reqs.get(i+1).getB()<=disco.getCurrentPosition()){
            if (i<reqs.size()-1)
                i++;
        }


        currentTime = reqs.get(i).getA();

        while (reqs.size()>0){
            int size = reqs.size();
            int n = 0;
            List<Requisicao> atendidas = new ArrayList<Requisicao>();
            for (n = i; n<size;n++){

                if (reqs.get(n).getA()<=currentTime) {
                    Requisicao r = reqs.get(n);
                    int espera = currentTime - r.getA();
                    totalWaitTime += espera;
                    int accessTime = r.getB() + disco.goTo(r.getB()) + r.getD(); //latency + seektime + transfer
                    currentTime += accessTime;
                    totalAccessTime += accessTime;

                    atendidas.add(r);
                }
            }

            i=0;

            for (Requisicao r : atendidas)
                reqs.remove(r);

            if (!reqs.isEmpty()) {
                currentTime += disco.goTo(disco.getNumSectors() - 1);
                currentTime += disco.goTo(0);
            }
        }


        double medAccessTime = totalAccessTime / nReqs;
        double medWaitTime = totalWaitTime / nReqs;

        System.out.println("CScan");
        System.out.println("-AccessTime="+medAccessTime);
        System.out.println("-WaitingTime="+medWaitTime);
        System.out.println();


    }

}
