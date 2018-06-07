import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.net.InetAddress;
import java.util.GregorianCalendar;

public class NetworkScaner_JAY extends Frame{
	public NetworkScaner_JAY(){
		super("ping");
		
		Panel p = new Panel();
		p.setLayout(new BorderLayout());
		TextArea ta = new TextArea();
		p.add(ta);
		add(p);
		setSize(600,700);
		setVisible(true);
		long finish = 0;
	    long start = new GregorianCalendar().getTimeInMillis();
	 
		
		try{
        	for(int i=1;i<255;i++) {
            InetAddress address = InetAddress.getByName("192.168.0."+i);//상대편 주소
            boolean reachable = address.isReachable(200);

            if(reachable==true) {
            	finish = new GregorianCalendar().getTimeInMillis();
            	ta.append(address+" is ready.        |"+(finish - start + "ms \n"));
            	}
        	else {
        		ta.append(address+" is not ready.\n");}
        	}
        } catch (Exception e){
            e.printStackTrace();
        }
	}
	public static void main(String[] args) {
		new NetworkScaner_JAY();
	}
}

