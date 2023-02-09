import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

class Mechanic {
    private String firstName;
    private String lastName;
    private String company;

    public Mechanic(String firstName, String lastName, String company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
    }

    public void carryOutMaintenance() {
        System.out.println(firstName + " " + lastName + " from " + company + " is carrying out maintenance.");
    }

    public void fixCar() {
        System.out.println(firstName + " " + lastName + " from " + company + " is fixing the car.");
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }
}

class Transport {
    private String driverName;
    private Set<Mechanic> mechanics;

    public Transport(String driverName) {
        this.driverName = driverName;
        this.mechanics = new HashSet<Mechanic>();
    }

    public void addMechanic(Mechanic mechanic) {
        mechanics.add(mechanic);
    }

    public String getDriverName() {
        return driverName;
    }

    public Set<Mechanic> getMechanics() {
        return mechanics;
    }
}
class ServiceStation {
    private Queue<Transport> transportQueue;
    public ServiceStation() {
        this.transportQueue = new LinkedList<Transport>();
    }
    public void addTransport(Transport transport) {
        if (!(transport.getDriverName().startsWith("Bob"))) {
            transportQueue.offer(transport);
        }
    }
    public void carryOutTechnicalInspection() {
        Transport transport = transportQueue.poll();
        if (transport != null) {
            System.out.println("Carrying out technical inspection for transport with driver: " + transport.getDriverName());
        } else {
            System.out.println("No transports in queue for technical inspection");
        }
    }
}


public class RaceCar {
    private static Map<Transport, Set<Mechanic>> transportMechanicsMap = new HashMap<Transport, Set<Mechanic>>();
    private static ServiceStation serviceStation = new ServiceStation();

    public static void main(String[] args) {
        Transport car1 = new Transport("John Doe");
        Set<Mechanic> car1Mechanics = new HashSet<Mechanic>();
        car1Mechanics.add(new Mechanic("Jane", "Doe", "ABC Mechanics"));
        car1Mechanics.add(new Mechanic("John", "Smith", "XYZ Mechanics"));
        transportMechanicsMap.put(car1, car1Mechanics);
        serviceStation.addTransport(car1);

        Transport truck1 = new Transport("Jane Smith");
        Set<Mechanic> truck1Mechanics = new HashSet<Mechanic>();
        truck1Mechanics.add(new Mechanic("Tom", "Brown", "DEF Mechanics"));
        truck1Mechanics.add(new Mechanic("Sarah", "Johnson", "GHI Mechanics"));
        transportMechanicsMap.put(truck1, truck1Mechanics);
        serviceStation.addTransport(truck1);

        Transport bus1 = new Transport("Bob Johnson");
        Set<Mechanic> bus1Mechanics = new HashSet<Mechanic>();
        bus1Mechanics.add(new Mechanic("Emily", "Davis", "JKL Mechanics"));
        bus1Mechanics.add(new Mechanic("Michael", "Anderson", "MNO Mechanics"));
        transportMechanicsMap.put(bus1, bus1Mechanics);
        serviceStation.addTransport(bus1);

        for (Map.Entry<Transport, Set<Mechanic>> entry : transportMechanicsMap.entrySet()) {
            System.out.println("Driver name: " + entry.getKey().getDriverName());
            System.out.println("Mechanics: ");
            for (Mechanic mechanic : entry.getValue()) {
                System.out.println(mechanic.getFirstName() + " " + mechanic.getLastName() + " from " + mechanic.getCompany());
            }
        }
        serviceStation.carryOutTechnicalInspection();
        serviceStation.carryOutTechnicalInspection();
        serviceStation.carryOutTechnicalInspection();
        serviceStation.carryOutTechnicalInspection();
    }
}
