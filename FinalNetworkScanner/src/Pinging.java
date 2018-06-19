import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pinging extends Thread{
	private Object[] msg;
	private String ip;
	
	public Pinging(String ip){
		this.ip =ip;
		msg = new Object[4];
	}
	@Override
	public void run() {
		BufferedReader br = null;
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec("ping -a "+ip);
			msg[0] = ip;
			br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.indexOf("[") >= 0) {
					msg[2] = line.substring(5, line.indexOf("[") - 1);
				}
				if (line.indexOf("ms") >= 0) {
					msg[1] = line.substring(line.indexOf("ms") - 1, line.indexOf("ms") + 2);
					msg[3] = line.substring(line.indexOf("TTL=") + 4, line.indexOf("TTL=" + 7));
					break;
				}
			}
		} catch (Exception e) {

		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public Object[] getMsg() {
		try {
			join();
		} catch (InterruptedException e) {
			return null;
		}
		return msg;
	}
	

}
