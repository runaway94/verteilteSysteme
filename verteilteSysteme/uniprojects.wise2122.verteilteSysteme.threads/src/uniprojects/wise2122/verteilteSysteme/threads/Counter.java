package uniprojects.wise2122.verteilteSysteme.threads;

public class Counter extends Thread {
	
	public void run() {
		for(int i=0;i<5000;i++) {
			System.out.println(i);
			
		}
	}

}
