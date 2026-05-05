package Iteration2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;


/**
 * CS2050 Iteration02
 * Drone hangar that manages drones with various methods.
 * 
 */
public class CS2050Iteration2
{
    public static void main(String[] args)
    {
        Hangar hangar1 = new Hangar();
        hangar1.showMenu(hangar1);
    }
}

/**
 * Driver Class
 * Hangar that stores, sorts, searches, and contains menuing for drones.
 */
class Hangar 
{
    ArrayList<Drone> dronesList = new ArrayList<Drone>(); // We will use Arraylists
    Map<Integer, Drone> dronesMap = new HashMap<Integer, Drone>(); // Use Integer since our id number is an int
    Queue<Drone> droneMaintenanceQueue = new Queue<Drone>(); // Use Queue Class

    /**
     * Iterates through ArrayList in Hangar Class and increments counter if
     * manufacturer name matches input string.
     * 
     * @param manufacturer
     * @return count
     */
    public int getCountByManufacturer(String manufacturer) 
    {
        int count = 0;

        for (int i = 0; i < dronesList.size(); i++) 
        {
            if (dronesList.get(i).getManufacturer().equalsIgnoreCase(manufacturer)) 
            {
                count++;
            }
        }

        return count;
    }
    /**
     * Checks the drones ArrayList for the specified manufacturer name and drone type (priority or standard)
     * If it matches both criteria, then adds it to a new list and runs collections.sort. Returns the list
     * @param dronesList
     * @param manufacturer
     * @param type
     */
    public ArrayList<Drone> searchDronesByManufacturerAndType(ArrayList<Drone> dronesList, String manufacturer, String type) 
    {
        ArrayList<Drone> searchList = new ArrayList<>();

        for (int i = 0; i < dronesList.size(); i++)
        {
            if (dronesList.get(i).getManufacturer().equalsIgnoreCase(manufacturer))
            {
               if (type.equalsIgnoreCase("Priority") || type.equalsIgnoreCase("P"))
               {
                    searchList.add(dronesList.get(i));
               }
               if (type.equalsIgnoreCase("Standard") || type.equalsIgnoreCase("S"))
               {
                    searchList.add(dronesList.get(i));
               }
            }
        }
        searchList.sort(Comparator.comparingInt(Drone::getIDnumber)); // Use java collections comparator to return a sorted list

        return searchList; // Return an array (Collections result)

    }

    /**
     * Creates a copy of dronesList and uses Selection sort to make a sorted arrayList by year (oldest to latest)
     * @param dronesList
     */
    public ArrayList<Drone> generateReportSortedByManufacturingYear(ArrayList<Drone> dronesList) 
    {
        ArrayList<Drone> sortedByYear = new ArrayList<>(dronesList);

        sortedByYear.sort(Comparator.comparing(Drone::getManufacturerYear));

        return sortedByYear; // Return collections result 
    }

    /**
     * Creates a copy of dronesList and uses selection sort to make a sorted arrayList by payload capacity.
     * @param dronesList
     */
    public ArrayList<Drone> generateReportSortedByPayloadCapacity(ArrayList<Drone> dronesList) 
    {

        ArrayList<Drone> sortedPayloadKG = new ArrayList<>(dronesList);

        sortedPayloadKG.sort(Comparator.comparing(Drone::getPayloadKg));

        return sortedPayloadKG;
    }

    /**
     * Displays every drone in droneList
     */
    public void displayHangarInventory()
    {
        for (int i = 0; i < dronesList.size(); i++) 
        {
            System.out.println(dronesList.get(i));
        }
    }

    /**
     * Searches the hashmap and returns a drone if found.
     * @param dronesList
     * @return
     */
    public Drone searchDroneByID(int id)
    {
        if (dronesMap.containsKey(id))
        {
            return dronesMap.get(id);
        }
        else
        {
            return null;
        }

    }

    /**
     * Addes a specified drone to the queue linked list. 
     * @param drone
     */
    public void addDroneToMaintenanceQueue(Drone drone)
    {
        if (!droneMaintenanceQueue.isEmpty())
        {
            droneMaintenanceQueue.enqueue(drone);
        }
        else
        {
            return;
        }
    }

    /**
     *  Returns the drone at the top of the maintenance queue
     * @param queue
     */
    public Drone viewNextDrone()
    {   
        if (!droneMaintenanceQueue.isEmpty())
        {
            return droneMaintenanceQueue.peek();
        }
        else
        {
            return null;
        }
    }

    /**
     * Processes the drone in the specified linked list queue for the Drone Queue
     * @param queue
     */
    public void processDrone()
    {
        if (!droneMaintenanceQueue.isEmpty())
        {
            droneMaintenanceQueue.dequeue();
        }
        else 
        {
            return;
        }

    }

    /**
     * Handles arrayList printing from helper methods (sorting, searching)
     * @param list
     */
    public void printList(ArrayList<Drone> list)
    {
        for (Drone drone : list)
        {
            System.out.println(drone.toString());
        }
    }
    
    /**
     * Menu system that uses case switching to determine output
     * This menu must cover null cases since a method should assume null case will not happen 
     * @param hangar
     */
    public void showMenu(Hangar hangar)
    {
        Scanner input = new Scanner(System.in);
        int searchID;
        boolean runtime = true;

        String menu[] = {
            "1. Load Drones from CSV",
            "2. Display Hangar Inventory",
            "3. Search Drones (Manufacturer & Type)",
            "4. View Inventory Sorted by Payload",
            "5. View Inventory Sorted by Year",
            "6. Count Drones by Manufacturer",
            "7. Search Drone by ID",
            "8. Add Drone to Maintenance Queue",
            "9. View Next Drone in Queue",
            "10. Process Next Drone in Queue",
            "11. Display Queue",
            "12. Exit"
        };

        while (runtime) {
            System.out.println("=== Drone Hangar Menu ===");

            for (int i = 0; i < menu.length; i++) 
            {
                System.out.println(menu[i]);
            }

            System.out.println("Enter your Choice: ");

            if (input.hasNextInt()) 
            {
                int response = input.nextInt();
                input.nextLine();

                switch (response) 
                {
                    case 1:
                        // Load drones from CSV
                        System.out.println("Type filename");
                        String filename = input.next();
                        input.nextLine();
                        readFromCSV(hangar, filename);
                        break;
                    case 2:
                        // Display Hangar inventory

                        if (dronesList == null || dronesList.isEmpty())
                        {
                            System.out.println("Error. No Drones in Hangar.");
                        }
                        else
                        {
                            displayHangarInventory();
                        }
                        break;
                    case 3:
                        // Search Drones (Manufacturer & Type)
                        String manufacturer;
                        String droneType;
                        if (dronesList == null || dronesList.isEmpty())
                        {
                            System.out.println("Error. No Drones in Hangar.");
                        }
                        else
                        {
                            System.out.println("Type the Manufacturer: ");
                            manufacturer = input.next();
                            input.nextLine();

                            System.out.println("Type S for Standard and P for Priority: ");
                            droneType = input.next();
                            input.nextLine();

                            // Ultimately made it more type safe for testing so it checks both single letters and the word
                            if (droneType.equalsIgnoreCase("S") || droneType.equalsIgnoreCase("P")) 
                            {
                                printList(searchDronesByManufacturerAndType(dronesList, manufacturer, droneType));
                            } 
                            else if (droneType.equalsIgnoreCase("Standard") || droneType.equalsIgnoreCase("Priority"))
                            {
                                printList(searchDronesByManufacturerAndType(dronesList, manufacturer, droneType));
                            }
                            else 
                            {
                                System.out.println("Error. Invalid character. Expected S or P. Got: " + droneType);
                            }
                        }
                        break;
                    case 4:
                        // View Inventory by Payload
                        if (dronesList == null || dronesList.isEmpty())
                        {
                            System.out.println("Error. No Drones in Hangar.");
                        }
                        else
                        {
                            printList(generateReportSortedByPayloadCapacity(dronesList));
                        }
                        break;
                    case 5:
                        // View Inventory by Year
                        if (dronesList == null || dronesList.isEmpty())
                        {
                            System.out.println("Error. No Drones in Hangar.");
                        }
                        else
                        {
                            printList(generateReportSortedByManufacturingYear(dronesList));
                        }
        
                        break;
                    case 6:
                        // Count drone by manufacturer
                        if (dronesList == null || dronesList.isEmpty())
                        {
                            System.out.println("Error. No Drones in Hangar.");
                        }
                        else
                        {
                            System.out.println("Type the Manufacturer: ");
                            manufacturer = input.next();
                            input.nextLine();
                            int count = getCountByManufacturer(manufacturer);
                            System.out.println("Drones by " + manufacturer + ": " + count);
                        }
                        break;
                    case 7:
                        // Search drone by ID
                        System.out.println("Type the ID you want to search (IDs start at 1000)");
    
                        if (input.hasNextInt())
                        {
                            searchID = input.nextInt();
                            input.nextLine();
                        
                            Drone idDrone = searchDroneByID(searchID);
                            if(idDrone != null)
                            {
                                System.out.println("Drone ID search result: " + idDrone.toString());
                            }
                            else
                            {
                                System.out.println("Error: Invalid ID input (Is it a number?)");
                            }
                        }
                        break;
                    case 8:
                        // Add drone to maintenance queue
                        System.out.println("What ID do you want to add to the Maintenance queue?");
                        
                        if (input.hasNextInt())
                        {
                        searchID = input.nextInt();
                        input.nextLine(); 
                        if (searchDroneByID(searchID) != null)
                        {
                            if (!droneMaintenanceQueue.contains(searchDroneByID(searchID)))
                                {
                                    System.out.println("Drone ID: " + searchID + " Added to Maintenance Queue");
                                    droneMaintenanceQueue.enqueue(searchDroneByID(searchID));
                                }
                                else
                                {
                                    System.out.println("Error. Drone already in queue. ");
                                }
                            }
                            else
                            {
                                System.out.println("Error. Invalid ID value (Does it exist?)");
                            }
                        }
                        break;
                    case 9:
                        // View next drone in queue
                        if (droneMaintenanceQueue.isEmpty())
                        {
                            System.out.println("Error. Maintenance Queue is empty.");
                        }
                        else
                        {
                            System.out.println(droneMaintenanceQueue.peek().toString());
                        }   

                        break;
                    case 10:
                        // Process next drone
                        if (droneMaintenanceQueue.isEmpty())
                        {
                            System.out.println("Error. Maintenance Queue is empty.");
                        }
                        else
                        {
                            System.out.println(droneMaintenanceQueue.peek().toString() + " Marked as Processed.");
                            droneMaintenanceQueue.dequeue();
                        }   

                        break;
                    case 11:
                        // View queue
                        if (droneMaintenanceQueue.isEmpty())
                        {
                            System.out.println("Error. Maintenance Queue is empty.");
                        }
                        else
                        {
                           droneMaintenanceQueue.printQueue();
                        }

                        break;
                    case 12:
                        // Exit
                        System.out.println("Exiting...");
                        runtime = false;
                        break;
                    default:
                        System.out.println("Invalid selection. Please pick from 1-" + menu.length);
                        break;
                }
            } 
            else 
            {
                System.out.println("Invalid input, please insert a valid number.");
                input.nextLine();
            }

        }

        input.close();
    }

    /**
     * Adds a drone to the hangar. Used in CSVreader, has null check to allow other
     * ways of adding a drone
     * 
     * @param drone
     * @return
     */
    public boolean addDrone(Drone drone) 
    {
        // redudancy null check
        if (drone != null) 
        {
            this.dronesList.add(drone);
            
            // Add a new drone to the HashMap -> dronesMap as well
            this.dronesMap.put(drone.getIDnumber(), drone); 
            return true;
        } else 
        {
            return false;
        }
    }

    /**
     * Read From CSV uses an input CSV file in directory to load and then parse to a
     * readable format.
     * 
     * @param hangar
     * @param fileName
     */
    public static void readFromCSV(Hangar hangar, String fileName) 
    {
        int totalLinesRead = 0;

        if (hangar == null) 
        {
            System.out.println("Error. Hangar not found.");
            return;
        }

        try (Scanner fileScan = new Scanner(new File(fileName))) 
        {
            // Continuous checking to iterate through entire file
            while (fileScan.hasNextLine()) 
            {
                String line = fileScan.nextLine();

                // Increment totalLinesRead to keep track of lines and add drones
                totalLinesRead = totalLinesRead + 1;
                boolean shouldProcess = true;

                if (line == null || line.trim().isEmpty()) 
                {
                    shouldProcess = false;
                }

                // If conditionals above are true then we can proceed and prepare to parse and
                // add a drone
                if (shouldProcess == true) 
                {
                    // Send to helper method to parse the drone line into a new drone
                    Drone newDrone = parseDroneLine(line, totalLinesRead);

                    if (newDrone != null) 
                    {
                        boolean added = hangar.addDrone(newDrone);
                        if (added == false) 
                        {
                            System.out.println("Error. Unable to add Drone.");
                        }

                    }
                }
            }
        }catch (FileNotFoundException ex) 
        {
            System.out.println("Error. Could not open file: " + fileName);
            return;
        }
    }

    /**
     * Helper method to read a line given by readFromCSV. Returns a valid drone.
     * @param line
     * @param LineNumber
     * @return
     */
    private static Drone parseDroneLine(String line, int lineNumber) 
    {

        if (line == null || line.trim().isEmpty()) 
        {
            return null;
        }

        String[] fields = line.split(","); // CSV -> Comma Seperated Values
        
        // Fields Check since split creates an array with segmented indexes based off of a comma.
        if (fields.length != 4) 
        {
            System.out.println("Error. Invalid number of fields at line " + lineNumber + ".\nExpected 4, recieved "+ fields.length);
            return null;
        }

        // Utilize array of our four inputs that we need: If P or S, Name, Year, Weight
        // Limit and store them into variables
        String type = fields[0].trim();
        String name = fields[1].trim();
        String year = fields[2].trim();
        String payload = fields[3].trim();

        // Name Check
        if (name.isEmpty()) 
        {
            System.out.println("Error. Line at " + lineNumber + ". No manufacturer name found.");
            return null;
        }

        // Year check
        int manufacturerYear;
        double payloadKg;
        try 
        {
            manufacturerYear = Integer.parseInt(year);
            payloadKg = Double.parseDouble(payload);

        }catch (NumberFormatException ex) 
        {
            System.out.println("Error. Line at " + lineNumber + " has invalid year and or weight capacity(Is it a number?)");
            return null;
        }

        // Type check looks for S or P, and then sets type to a more 
        if (type.equalsIgnoreCase("S")) 
        {
            type = "Standard";
            Drone parsedDrone = new StandardDrone(type, name, manufacturerYear, payloadKg);
            return parsedDrone;
        } 
        else if (type.equalsIgnoreCase("P")) 
        {
            type = "Priority";
            Drone parsedDrone = new PriorityDrone(type, name, manufacturerYear, payloadKg);
            return parsedDrone;
        } 
        else 
        {
            System.out.println("Error. Invalid drone type at line " + lineNumber + ".\nExpected S or P. Got " + type);
            return null;
        }
    }
}

/**
 * Abstract Drone class used for creation of Priority and Standard Drones.
 * getType() is abstract used to denote which type of Drone it is.
 * Concrete drone classes Priority and Standard specifies its type aswell in its constructor.
 */
abstract class Drone 
{
    private String manufacturerName;
    private int manufacturerYear;
    private double payloadKg;
    private String type;
    // ID start value will be 1000 for the sake of the assignment
    private static int ID = 1000; 
    private int droneID;

    public Drone(String type, String manufacturerName, int manufacturerYear, double payloadKg) 
    {
        this.manufacturerName = manufacturerName;
        this.manufacturerYear = manufacturerYear;
        this.payloadKg = payloadKg;
        this.type = type;

        // Hashmap ID numbers 
        // essentially is an internal ID tracker (integer) for tracking how many drones exist at a given time 
        this.droneID = ID;
        ID++;
    }

    public String getManufacturer()
    {
        return this.manufacturerName;
    }

    public int getManufacturerYear() 
    {
        return this.manufacturerYear;
    }

    public double getPayloadKg() 
    {
        return this.payloadKg;
    }

    public String getType()
    {
        return this.type;
    }

    public int getIDnumber()
    {
        return this.droneID;
    }

    @Override
    public String toString() 
    {
        String toString = "Manufacturer: " + this.manufacturerName + " | " + "Manufactured: " + this.manufacturerYear + " | " + " Weight Limit (KG): " + this.payloadKg;
        return toString;
    }
}

/**
 * Priority Drone
 */
class PriorityDrone extends Drone 
{
    public PriorityDrone(String type, String manufacturerName, int manufacturerYear, double payloadKg) 
    {
        super(type, manufacturerName, manufacturerYear, payloadKg);
    }

    @Override
    public String toString()
    {
        return "D" + super.getIDnumber() + " | " + super.getType() + " | " + super.toString();
    }

}

/**
 * Standard Drone
 */
class StandardDrone extends Drone 
{
    public StandardDrone(String type, String manufacturerName, int manufacturerYear, double payloadKg) 
    {
        super(type, manufacturerName, manufacturerYear, payloadKg);
    }

    @Override
    public String toString()
    {
        return "D" + super.getIDnumber() + " | " + super.getType() + " | " + super.toString();
    }

}

/**
 * Queue is used to create and manage queue operations 
 * Use <E> for elements
 */
class Queue<E>
{
    LinkedList<E> queue;

    public Queue()
    {
        queue = new LinkedList<E>();
    }

    public E dequeue()
    {
        // Linked list implementation requires poll for Queue operations (FIFO)
        // Removes Head of linked list
        return queue.poll();
    }

    public void enqueue(E object)
    {
        // Linked list implementation for queues needs offer instead of add for FIFO
        queue.offer(object);
    }

    public E peek()
    {
        return queue.peek();
    }

    public boolean isEmpty()
    {
        return queue.isEmpty();
    }

    public void printQueue()
    {
        for(E object : queue)
        {
            System.out.println(object);
        }
    }

    /**
     * Checks if item is in the queue already.
     * @param item
     * @return
     */
    public boolean contains(E item)
    {
        for (E object : this.queue)
        {
            if (item == object)
            {
                return true;
            }
        }

        return false;
    }

}