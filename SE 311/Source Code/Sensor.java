// PROJECT3 - Smart City Application

// Fercan Þen	- 20150602038
// Naz Tekinalp - 20160602033
// Mert Kesimli - 20160602017

// Patterns used on this project are:
// Factory Pattern
// Singleton Pattern
// Composite Pattern
// Visitor Pattern
// Observer Pattern


import java.util.ArrayList;

// Factory Pattern Starts

// SensorCreator => Creator
abstract class SensorCreator { 
	abstract public Sensor createSensor();
}

// Singleton is implemented on Concrete Creators with getInstance()
// PollutionCreator => Concrete Creator
class PollutionCreator extends SensorCreator {

	public Sensor createSensor() {
		PollutionSensor sensor = new PollutionSensor("Pollution Sensor", false, "AQI", 105.7, "Apartment");
		return sensor;
	}

	public static PollutionCreator getInstance() { 
		if (uniqueInstance == null) {
			uniqueInstance = new PollutionCreator();
		}
		return uniqueInstance;
	}
	private static PollutionCreator uniqueInstance = null;
}

// TemperatureCreator => Concrete Creator
class TemperatureCreator extends SensorCreator {

	public Sensor createSensor() {
		TemperatureSensor sensor = new TemperatureSensor("Temperature Sensor", false, "C°", 45.3, "Apartment");
		return sensor;
	}

	public static TemperatureCreator getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new TemperatureCreator();
		}
		return uniqueInstance;
	}
	private static TemperatureCreator uniqueInstance = null;
}

// CongestionCreator => Concrete Creator
class CongestionCreator extends SensorCreator {

	public Sensor createSensor() {
		CongestionSensor sensor = new CongestionSensor("Congestion Sensor", false, "km/h", 100, "Pole");
		return sensor;
	}

	public static CongestionCreator getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new CongestionCreator();
		}
		return uniqueInstance;
	}
	private static CongestionCreator uniqueInstance = null;
}

// NoiseCreator => Concrete Creator
class NoiseCreator extends SensorCreator {

	public Sensor createSensor() {
		NoiseSensor sensor = new NoiseSensor("Noise Sensor", false, "dB", 75, "Pole");
		return sensor;
	}

	public static NoiseCreator getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NoiseCreator();
		}
		return uniqueInstance;
	}
	private static NoiseCreator uniqueInstance = null;
}

// Concrete Creators Ends

// Concrete Products Starts

// PollutionSensor => Concrete Product
class PollutionSensor extends Sensor {

	public PollutionSensor(String sensorName, boolean sensorStatus, String unit, double sensorValue, String installationType) {
		super(sensorName, sensorStatus, unit, sensorValue, installationType);
		sensorName = new String("Pollution Sensor");
		unit = new String("AQI");
		
		System.out.println("Pollution Sensor is created");
	}

	public void Accept(Visitor visitor) {
		visitor.Visit(this);
	}

	@Override
	public void sensorCalculate(double sensorValue) {
		if (getSensorValue() > 100) {
			
			System.out.println(displaySensorName() + " is above 100" + getUnit() + ", current value is: " + getSensorValue()
			+ getUnit());
			NotifyCitizen();
			
		}
		
	}

	@Override
	public void checkSensorStatus(boolean sensorStatus) {
		if (getSensorStatus() == false) {
			NotifyEngineer();
		}
	}
}

// TemperatureSensor => Concrete Product
class TemperatureSensor extends Sensor {
	public TemperatureSensor(String sensorName, boolean sensorStatus, String unit, double sensorValue, String installationType) {
		super(sensorName, sensorStatus, unit, sensorValue, installationType);
		sensorName = new String("Temperature Sensor");
		unit = new String("C°");
		System.out.println("Temperature Sensor is created");

	}

	public void Accept(Visitor visitor) {
		visitor.Visit(this);
	}

	@Override
	public void sensorCalculate(double sensorValue) {
		if (getSensorValue() < 0) {
			System.out.println(displaySensorName() + " is below 0" + getUnit() + ", current value is: "
					+ getSensorValue() + getUnit());
			NotifyCitizen();
			
		}
	}

	@Override
	public void checkSensorStatus(boolean sensorStatus) {
		if (getSensorStatus() == false) {
	
			NotifyEngineer();
			
		}
	}
}

// CongestionSensor => Concrete Product
class CongestionSensor extends Sensor {
	public CongestionSensor(String sensorName, boolean sensorStatus, String unit, double sensorValue, String installationType) {
		super(sensorName, sensorStatus, unit, sensorValue, installationType);
		sensorName = new String("Congestion Sensor");
		unit = new String("km/h");
		System.out.println("Congestion Sensor is created");
	}

	public void Accept(Visitor visitor) {
		visitor.Visit(this);
	}

	@Override
	public void sensorCalculate(double sensorValue) {
		if (getSensorValue() < 10) {
			System.out.println(displaySensorName() + " is below 10" + getUnit() + ", current value is: "
					+ getSensorValue() + getUnit());
			NotifyCitizen();
			
		}
	}

	@Override
	public void checkSensorStatus(boolean sensorStatus) {
		if (getSensorStatus() == false) {
			
			NotifyEngineer();
			
		}
	}
}

// NoiseSensor => Concrete Product
class NoiseSensor extends Sensor {
	public NoiseSensor(String sensorName, boolean sensorStatus, String unit, double sensorValue, String installationType) {
		super(sensorName, sensorStatus, unit, sensorValue, installationType);
		sensorName = new String("Noise Sensor");
		unit = new String("dB");
		System.out.println("Noise Sensor is created");
	}

	public void Accept(Visitor visitor) {
		visitor.Visit(this);
	}

	@Override
	public void sensorCalculate(double sensorValue) {
		if (getSensorValue() > 85) {
			System.out.println(displaySensorName() + " is above 85" + getUnit() + ", current value is: "
					+ getSensorValue() + getUnit());
			NotifyCitizen();
			
		}
	}

	@Override
	public void checkSensorStatus(boolean sensorStatus) {
		if (getSensorStatus() == false) {
			
			NotifyEngineer();
			
		}
	}
}

// Concrete Products Ends

// Factory Pattern Ends

// Composite Pattern Starts

// Neighborhood => Component
interface Neighborhood {
	void Add(Neighborhood n);
	void Remove(Neighborhood n);
	void Display(int indent);
	public String getName();
}

// Pole => Leaf 1
class Pole implements Neighborhood {
	public String name;

	public String getName() {
		return name;
	}

	public Pole(String name) {
		this.name = name;
	}

	public void Add(Neighborhood c) {
		System.out.println("Cannot add to a Pole.");
	}

	public void Remove(Neighborhood c) {
		System.out.println("Cannot remove from a Pole.");
	}

	public void InstallSensor(Pole pole) {
		SensorCreator creator = new PollutionCreator();
		SensorCreator creator2 = new TemperatureCreator();
		SensorCreator creator3 = new CongestionCreator();
		SensorCreator creator4 = new NoiseCreator();
		creator.createSensor();
		creator2.createSensor();
		creator3.createSensor();
		creator4.createSensor();
		System.out.println("All sensors installed to " + pole.getName());
	}

	@Override
	public void Display(int indent) {
		for (int i = 1; i <= indent; i++)
			System.out.print("-");
		System.out.println(" " + name);
	}

}

// Apartment => Leaf 2
class Apartment implements Neighborhood {
	public String name;

	public String getName() {
		return name;
	}

	public Apartment(String name) {
		this.name = name;
	}

	public void InstallSensor(Apartment apartment) {
		SensorCreator creator = new PollutionCreator();
		SensorCreator creator2 = new TemperatureCreator();
		SensorCreator creator3 = new CongestionCreator();
		SensorCreator creator4 = new NoiseCreator();
		creator.createSensor();
		creator2.createSensor();
		creator3.createSensor();
		creator4.createSensor();
		System.out.println("All sensors installed to " + apartment.getName());
	}

	public void Add(Neighborhood d) {
		System.out.println("Cannot add to a Apartment.");
	}

	public void Remove(Neighborhood d) {
		System.out.println("Cannot remove from a Apartment.");
	}

	@Override
	public void Display(int indent) {
		for (int i = 1; i <= indent; i++)
			System.out.print("-");
		System.out.println(" " + name);
	}

}

// Street => Composite
class Street implements Neighborhood {
	private String name;

	public String getName() {
		return name;
	}

	public Street(String name) {
		this.name = name;
	}

	public void Add(Neighborhood e) {
		elements.add(e);
	};

	public void Remove(Neighborhood e) {
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).getName() == e.getName()) {
				elements.remove(i);
				return;
			}
		}
	}

	public void Display(int indent) {
		for (int i = 1; i <= indent; i++)
			System.out.print("-");
		System.out.println("+ " + getName());

		// Display each child element on this node
		for (int i = 0; i < elements.size(); i++) {
			elements.get(i).Display(indent + 2);
		}
	}
	private ArrayList<Neighborhood> elements = new ArrayList<Neighborhood>();
}

// Composite Pattern Ends

// Visitor Pattern Starts

interface Element {
	public void Accept(Visitor visitor);
}

interface Visitor {
	public void Visit(TemperatureSensor element);
	public void Visit(PollutionSensor element);
	public void Visit(NoiseSensor element);
	public void Visit(CongestionSensor element);
}

// DataMonitoringDivision => Object Structure
class DataMonitoringDivision {
	public void Add(Sensor sensor) {
		sensors.add(sensor);
	};

	public void Accept(Visitor visitor) {
		// Set argument to something that helps
		// Tell the Observers what happened
		for (int i = 0; i < sensors.size(); i++) {
			sensors.get(i).Accept(visitor);
		}
	}
	private ArrayList<Sensor> sensors = new ArrayList<Sensor>();
}

// Malfunction => Concrete Visitor
class Malfunction implements Visitor {
	static int malfunctionCounter;

	public void Visit(PollutionSensor element) {
		boolean sensorStatus = element.getSensorStatus();
		element.setSensorStatus(element.getSensorStatus());
		System.out.println(element.getSensorStatus());

		if (sensorStatus == false) {
			malfunctionCounter++;
		}
	}

	public void Visit(TemperatureSensor element) {
		boolean sensorStatus = element.getSensorStatus();
		element.setSensorStatus(element.getSensorStatus());
		System.out.println(element.getSensorStatus());

		if (sensorStatus == false) {
			malfunctionCounter++;
		}
	}

	public void Visit(CongestionSensor element) {
		boolean sensorStatus = element.getSensorStatus();
		element.setSensorStatus(element.getSensorStatus());
		System.out.println(element.getSensorStatus());

		if (sensorStatus == false) {
			malfunctionCounter++;
		}
	}

	public void Visit(NoiseSensor element) {
		boolean sensorStatus = element.getSensorStatus();
		element.setSensorStatus(element.getSensorStatus());
		System.out.println(element.getSensorStatus());

		if (sensorStatus == false) {
			malfunctionCounter++;
		}
	}
}
//Visitor Pattern Ends

// Observer Pattern Starts

// Observer  => Abstract Observer
interface Observer {
	public void Update(Sensor sensor);
}

// Citizen => Concrete Observer
class Citizen implements Observer {
	private Sensor _sensor;
	private String _citizen_name;
	private boolean _sensor_status;
	private String _sensor_name;
	private double _sensor_value;
	private String _sensor_unit;

	public Citizen(String name) {
		_citizen_name = name;
	}

	public void Update(Sensor sensor) {
		_sensor = sensor;
		_sensor_name = sensor.displaySensorName();
		_sensor_status = sensor.getSensorStatus();
		_sensor_value = sensor.getSensorValue();
		_sensor_unit = sensor.getUnit();
		System.out.println("Notified " + _citizen_name + " of " + _sensor_name + "'s " + "change to " + _sensor_value
				+ " " + _sensor_unit);
	}

	public Sensor getSensor() {
		return _sensor;
	}

	public void setSensor(Sensor value) {
		_sensor = value;
	}

	public String getCitizenName() {
		return _citizen_name;
	}
	
}

// Engineer => Concrete Observer
class Engineer implements Observer {
	private Sensor _sensor;
	private String _engineer_name;
	private boolean _sensor_status;
	private String _sensor_name;
	private double _sensor_value;
	private String _sensor_unit;

	public Engineer(String name) {
		_engineer_name = name;
	}

	public void Update(Sensor sensor) {
		_sensor = sensor;
		_sensor_name = sensor.displaySensorName();
		_sensor_status = sensor.getSensorStatus();
		_sensor_value = sensor.getSensorValue();
		_sensor_unit = sensor.getUnit();
		System.out
				.println("Notified " + _engineer_name + " of " + _sensor_name + "'s " + "change to " + _sensor_status);
	}

	public boolean Reset() {
		if (_sensor_status == false) {
			_sensor_status = true;
			System.out.println("Sensor has been reset");
		} else {
			System.out.println("Sensor is already working");
		}
		return _sensor_status;
	}

	public Sensor getSensor() {
		return _sensor;
	}

	public void setSensor(Sensor value) {
		_sensor = value;
	}

	public String getEngineerName() {
		return _engineer_name;
	}
}

// Sensor is "Subject" for Observer Pattern
// Sensor is "Product" for Factory Pattern
// Sensor is "Element" for Visitor Pattern

// All patterns connected via Sensor
abstract public class Sensor implements Element {

	protected String sensorName;
	private boolean sensorStatus;
	private String unit;
	private double sensorValue;
	private String installationType;

	abstract public void sensorCalculate(double sensorValue);

	abstract public void checkSensorStatus(boolean sensorStatus);

	public String displaySensorName() {
		return sensorName;
	}

	public Sensor(String sensorName, boolean sensorStatus, String unit, double sensorValue, String installationType) {
		this.sensorName = sensorName;
		this.sensorStatus = sensorStatus;
		this.unit = unit;
		this.sensorValue = sensorValue;
		this.installationType = installationType;
	}

	public boolean getSensorStatus() {
		return sensorStatus;
	};

	public void setSensorStatus(boolean value) {
		sensorStatus = value;
		NotifyEngineer();
	};

	public double getSensorValue() {
		return sensorValue;
	};

	public void setSensorValue(double value) {
		sensorValue = value;
		NotifyCitizen();
	};

	public String getUnit() {
		return unit;
	};

	public void setUnit(String value) {
		unit = value;
	};
	
	public String getInstallationType() {
		return installationType;
	};

	public void setInstallationType(String value) {
		installationType = value;
	};

	// Register the Observers
	public void AttachCitizen(Citizen citizen) {
		citizens.add(citizen);
	}

	// Unregister from the list of Observers.
	public void DetachCitizen(Citizen citizen) {
		for (int i = 0; i < citizens.size(); i++) {
			if (citizens.get(i).getCitizenName() == citizen.getCitizenName()) {
				citizens.remove(i);
				return;
			}
		}
	}

	// Notify the Observers.
	public void NotifyCitizen() {
		// set argument to something that helps
		// tell the Observers what happened
		for (int i = 0; i < citizens.size(); i++) {
			citizens.get(i).Update(this);
		}
	}

	// Register the Observers
	public void AttachEngineer(Engineer engineer) {
		engineers.add(engineer);
	}

	// Unregister from the list of Observers.
	public void DetachCitizen(Engineer engineer) {
		for (int i = 0; i < engineers.size(); i++) {
			if (engineers.get(i).getEngineerName() == engineer.getEngineerName()) {
				engineers.remove(i);
				return;
			}
		}
	}

	// Notify the Observers.
	public void NotifyEngineer() {
		// set argument to something that helps
		// tell the Observers what happened
		for (int i = 0; i < engineers.size(); i++) {
			engineers.get(i).Update(this);
		}
	}

	protected ArrayList<Engineer> engineers = new ArrayList<Engineer>();
	protected ArrayList<Citizen> citizens = new ArrayList<Citizen>();

	public static void main(String[] args) {
		
		DataMonitoringDivision e = new DataMonitoringDivision();
		e.Add(new NoiseSensor("NoiseSensor1", false, "dB", 89, "Apartment"));
		e.Add(new CongestionSensor("CongestionSensor1", false, "km/h", 4, "Pole"));
		e.Add(new PollutionSensor("PollutionSensor1", true, "AQI", 80, "Apartment"));
		e.Add(new TemperatureSensor("TemperatureSensor1", true, "C", 32.1 , "Pole"));

		Citizen ctz = new Citizen("Naz");
		Engineer eng = new Engineer("Erdem");

		PollutionSensor sensor1 = new PollutionSensor("PollutionSensor1", true, "AQI", 120, "Pole");
		NoiseSensor sensor2 = new NoiseSensor("NoiseSensor1", false, "dB", 95, "Apartment");
		CongestionSensor sensor3 = new CongestionSensor("CongestionSensor1", false, "km/h", 1, "Apartment");
		TemperatureSensor sensor4 = new TemperatureSensor("TemperatureSensor1", true, "C", -7, "Apartment");

		sensor2.AttachCitizen(ctz);
		sensor1.AttachEngineer(eng);
		sensor4.AttachEngineer(eng);
		sensor3.AttachCitizen(ctz);

		sensor4.setSensorStatus(false);
		sensor1.setSensorStatus(false);
		sensor2.setSensorStatus(true);
		sensor2.setSensorStatus(false);
		sensor3.setSensorStatus(false);
		sensor3.setSensorStatus(true);
		
		

		sensor1.checkSensorStatus(true);
		sensor1.sensorCalculate(100);
		sensor2.sensorCalculate(90);
		sensor3.sensorCalculate(8);
		sensor4.sensorCalculate(-40);

		
		Neighborhood root = new Street("Prof. Dr. Yusuf Vardar Sk.");
		root.Add(new Pole("Best Pole ever Made"));
		root.Add(new Apartment("Folkart Apt."));
		
		Neighborhood comp = new Street("Gul Sk.");
		Apartment apt = new Apartment("Firuz Apt.");
		comp.Add(apt);
		Pole pole = new Pole("Worst Pole Ever Made");
		comp.Add(pole);
		root.Add(comp);
		apt.InstallSensor(apt);
		pole.InstallSensor(pole);
	
		root.Display(1);
		
		System.out.println();
        System.out.println("Malfunctions: (True for Working, False for Malfunction)");
        e.Accept(new Malfunction());
        System.out.println("Total Malfunction number: " + Malfunction.malfunctionCounter);
	}

}
