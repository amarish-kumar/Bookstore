package performanceTest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





public class PerformanceTest {

	private int numClient;
	private List<Long> resTimes;
	public PerformanceTest(int c) {
		numClient = c;
		resTimes = new ArrayList<Long>();
	}
	
	private class ClientThread implements Runnable {

		@Override
		public void run() {
			
			try {
				URL url = new URL("http://localhost:8080/4413project/rest/OrderService/Order/b001");
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				long startTime = System.currentTimeMillis();
				urlConnection.connect();
				if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
					long stopTime = System.currentTimeMillis();
					urlConnection.disconnect();
					long responseTime = stopTime - startTime;
					resTimes.add(responseTime);
					System.out.println("response time in: " + responseTime + "ms");
				}else {
					System.out.println("no responses");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}

	
	
	@SuppressWarnings("static-access")
	public void runChilds() throws InterruptedException{
		for(int i = 0; i < numClient; i++){
			ClientThread c = new ClientThread();
			Thread t = new Thread(c);
			t.sleep(3000);
			t.start();
			//t.join();
		}
	
	}
	
	
	/**
	 * @return the numClient
	 */
	public int getNumClient() {
		return numClient;
	}


	/**
	 * @return the resTimes
	 */
	public List<Long> getResTimes() {
		return resTimes;
	}

	public Long calculateAvg(List<Long> l){
		Long sum = (long) 0;
		for(Long num : l){
			sum += num;
			
		}
		Long avg = sum/numClient;
		return avg;
	}

	public static void main(String[] args) throws IOException, InterruptedException{
		
		//test for 10 clients
		Map<Integer,Long> map = new HashMap<Integer,Long>();
		int clients = 10;
		
		for(int i = 1; i <= clients; i++){
			PerformanceTest pt = new PerformanceTest(i);
			pt.runChilds();
			Thread.sleep(5000);
			Long avg = pt.calculateAvg(pt.getResTimes());
			map.put(i,avg);
			System.out.println("end of run with: " + i + " numbers of requests with average response time " + avg + "ms");
		}
	
		
		 
		 
    }

}
