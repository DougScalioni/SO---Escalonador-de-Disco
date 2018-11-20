package politicas;

import disco.Disco;
import disco.Requisicao;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SSTF {

    public static void SSTF(Disco ds) {
        Disco disco = new Disco(ds);
        List<Requisicao> reqs = disco.getRequisicoes();
        int totalAccessTime = 0;
        int totalWaitTime = 0;
        int currentTime = 0;

        Collections.sort(reqs, Comparator.comparing(Requisicao::getB));
        int nReqs = reqs.size();

        int i = 0;


        currentTime = reqs.get(i).getA();

        while (reqs.size() > 0) {

            Requisicao r;
            if (reqs.size() > 1) {
                while (i < reqs.size() - 1 && reqs.get(i + 1).getB() <= disco.getCurrentPosition()) {
                    if (i < reqs.size() - 1)

                        i++;

                }

                Requisicao esquerda, direita;
                if (i == 0) {
                    esquerda = reqs.get(0);
                    direita = reqs.get(1);
                } else {
                    esquerda = reqs.get(i - 1);
                    direita = reqs.get(i);
                }

                int distEsquerda = Math.abs(disco.getCurrentPosition() - esquerda.getB());
                int distDireita = Math.abs(disco.getCurrentPosition() - direita.getB());


                if (distEsquerda < distDireita)
                    r = esquerda;
                else
                    r = direita;
            } else
                r = reqs.get(0);

            int espera = currentTime - r.getA();
            totalWaitTime += espera;
            int accessTime = r.getB() + disco.goTo(r.getB()) + r.getD(); //latency + seektime + transfer
            currentTime += accessTime;
            totalAccessTime += accessTime;


            reqs.remove(r);
            i = 0;

        }


        double medAccessTime = totalAccessTime / nReqs;
        double medWaitTime = totalWaitTime / nReqs;

        System.out.println("SSTF");
        System.out.println("-AccessTime=" + medAccessTime);
        System.out.println("-WaitingTime=" + medWaitTime);
        System.out.println();

    }


}
