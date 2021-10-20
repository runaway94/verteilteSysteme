package parkingCars3;

import java.util.ArrayDeque;
import java.util.Deque;

//passive klasse
public class Parkhaus {

	// Erzeuger: der Fahrzeighersteller
	// Verbraucher: Kunden

	private final Object monitor;
	private final Deque<Auto> autos;
	private final short stellplatzAnzahl;

	public Parkhaus(short stellplatzAnzahl) {
		this.monitor = new Object();
		this.autos = new ArrayDeque<>();
		this.stellplatzAnzahl = stellplatzAnzahl;
	}

	public void parkeNeuesAutoInHalle(Auto auto) {
		synchronized (monitor) {
			while (autos.size() == stellplatzAnzahl) {
				try {
					System.out.println("Es gtibt keine freien Stellplätze mehr. Los wir müssen verkaufen! :)");
					monitor.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			monitor.notify();
			System.out.println(auto.getName() + " wurde in der Halle geparkt!");
			this.autos.push(auto);
		}
	}

	public void autoVerkauft(String name) {
		synchronized (monitor) {
			System.out.println(name + " möchte gerne ein Auto kaufen. ");
			while (autos.size() == 0) {
				try {
					System.out.println(" Sorry " + name + ", wir haben keine Autos mehr! Es sollten aber bald welche reinkommen! :)");
					monitor.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			monitor.notifyAll();
			Auto pop = autos.pop();
			System.out.println("Herzlichen Glückwunsch, "+ name + ", Sie haben " + pop.getName() + " erworben! Viel Spaß damit :)");		}
	}

}
