package politicas;

import disco.Disco;
import disco.Requisicao;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FCFS {


    public static void FCFS (Disco ds){
        Disco disco = new Disco(ds);
        List<Requisicao> reqs = disco.getRequisicoes();
        int totalAccessTime = 0;
        int totalWaitTime = 0;
        int currentTime = 0;




        Collections.sort(reqs, Comparator.comparing(Requisicao::getA));
        int nReqs = reqs.size();


            for (Requisicao r: reqs){
                if(r.getA()> currentTime){
                    currentTime = r.getA();
                }

                int espera = currentTime - r.getA();
                totalWaitTime += espera;
                int accessTime = r.getB() + disco.goTo(r.getB()) + r.getD(); //latency + seektime + transfer
                currentTime += accessTime;
                totalAccessTime += accessTime;

            }


        double medAccessTime = totalAccessTime / nReqs;
        double medWaitTime = totalWaitTime / nReqs;

        System.out.println("FCFS");
        System.out.println("-AccessTime="+medAccessTime);
        System.out.println("-WaitingTime="+medWaitTime);
        System.out.println();


    }

}
