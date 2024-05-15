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

            while(infinite){
                byte buf[] = new byte[1024];
                DatagramPacket data = new DatagramPacket(buf, buf.length);
                serverMulticastSocket.receive(data);
                String msg = new String(data.getData()).trim();
                System.out.println("Message recived from client = " + msg);
            }
            serverMulticastSocket.close();
        }catch (IOException ie){
            ie.printStackTrace();
        }
    }
}