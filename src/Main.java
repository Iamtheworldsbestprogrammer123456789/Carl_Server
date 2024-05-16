import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Main {
    public static void main(String[] args) {

        int portnumber = 8880;
        if (args.length >= 1){
            portnumber = Integer.parseInt(args[0]);
        }
        try{
            MulticastSocket serverMulticastSocket = new MulticastSocket(portnumber);

            InetAddress group = InetAddress.getByName("224.4.5.6");

            serverMulticastSocket.joinGroup(group);
            System.out.println("joinGroup method is called...");
            boolean infinite = true;

                byte buf[] = new byte[1024];
                DatagramPacket data = new DatagramPacket(buf, buf.length);
                serverMulticastSocket.receive(data);
                String msg = new String(data.getData()).trim();
                String resultat = räknare(msg);
                System.out.println("Resultat: " + resultat);
                DatagramPacket resultatet = new DatagramPacket(resultat.getBytes(), resultat.getBytes().length, group, portnumber);
                serverMulticastSocket.send(resultatet);

            serverMulticastSocket.close();
        }catch (IOException ie){
            ie.printStackTrace();
        }
    }
    private static String räknare(String matteTal){
        if(matteTal.contains("+")){
            String[] tal =matteTal.split("\\+");
            int tal1 = Integer.parseInt(tal[0]);
            int tal2 = Integer.parseInt(tal[1]);
            String talet = String.valueOf(tal1+tal2);
            return tal[0] + "+"+ tal[1] + " = " + talet;
        }
        else if(matteTal.contains("-")){
            String[] tal =matteTal.split("-");
            int tal1 = Integer.parseInt(tal[0]);
            int tal2 = Integer.parseInt(tal[1]);
            String talet = String.valueOf(tal1-tal2);
            return tal[0] + "-"+ tal[1] + " = " + talet;
        }
        else if(matteTal.contains("*")){
            String[] tal =matteTal.split("\\*");
            int tal1 = Integer.parseInt(tal[0]);
            int tal2 = Integer.parseInt(tal[1]);
            String talet = String.valueOf(tal1*tal2);
            return tal[0] + "*"+ tal[1] + " = " + talet;
        }
        else if(matteTal.contains("/")){
            String[] tal =matteTal.split("/");
            int tal1 = Integer.parseInt(tal[0]);
            int tal2 = Integer.parseInt(tal[1]);
            String talet = String.valueOf(tal1/tal2);
            return tal[0] + "/"+ tal[1] + " = " + talet;
        }
        else {
            return "Fel input";
        }
    }
}