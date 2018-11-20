package arquivos;
import disco.Disco;
import disco.Requisicao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static politicas.CLook.CLook;
import static politicas.CScan.CScan;
import static politicas.FCFS.FCFS;
import static politicas.SSTF.SSTF;
import static politicas.Scan.Scan;

public class Reader {

    public static void main (String[] args) throws Exception{
        File file = new File("/home/doug/Documents/Estudo/test.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        Disco disco = getDisco(br);
        disco.setRequisicoes(getRequisicoes(br));

        FCFS(disco);
        Scan(disco);
        CScan(disco);
        CLook(disco);
        SSTF(disco);
    }


    public List<Requisicao> read ()throws Exception{
        File file = new File("/home/doug/Documents/Estudo/test.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

      return null;
    }

    public static Disco getDisco(BufferedReader br) throws Exception{
        String st;
        st = br.readLine();
        String numSectorsString = st.substring(st.lastIndexOf("=") + 1);
        int numSectors = Integer.parseInt(numSectorsString);

        st = br.readLine();
        String numTracksString = st.substring(st.lastIndexOf("=") + 1);
        int numTracks = Integer.parseInt(numTracksString);

        st = br.readLine();
        String startPositionString = st.substring(st.lastIndexOf("=") + 1);
        int startPosition = Integer.parseInt(startPositionString);

        Disco disco = new Disco(numSectors, numTracks, startPosition, new ArrayList<Requisicao>() );
        return disco;
    }

    public static List<Requisicao> getRequisicoes (BufferedReader br) throws Exception{
        String st;
        List<Requisicao> reqs = new ArrayList<Requisicao>();

        boolean next = true;
        st = br.readLine();

        while (next){
            if (st.equals(";"))
                next = false;
            else{

            String req = st.substring(st.lastIndexOf("=") + 1);
            String[] parametros = req.split(",");
            int A = Integer.parseInt(parametros[0]);
            int B = Integer.parseInt(parametros[1]);
            int C = Integer.parseInt(parametros[2]);
            int D = Integer.parseInt(parametros[3]);

            Requisicao r = new Requisicao(A, B, C, D);
            reqs.add(r);
            st = br.readLine();
            }
        }
        return reqs;
    }



}
