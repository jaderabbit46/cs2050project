import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 */
public class cs2050project 
{
    public static void main(String[] args) 
    {
        Hangar hangar1 = new Hangar();
        hangar1.showMenu(hangar1);
    }
}

/**
 * 
 */
class Hangar 
{

    ArrayList<Drone> dronesList = new ArrayList<Drone>(); // We will use Arraylists

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
            if (dronesList.get(i).getManufacturer() == manufacturer) 
            {
                count++;
            }
        }

        return count;
    }

    public void searchDronesByManufacturerAndType(ArrayList<Drone> dronesList, String manufacturer, String type) 
    {

        if (dronesList == null || dronesList.isEmpty())
        {
            System.out.println("Error. No Drones in Hangar.");
            return;
        }

        ArrayList<Drone> searchList = new ArrayList<Drone>();

        for (int i = 0; i < dronesList.size(); i++)
        {
            if (dronesList.get(i).getManufacturer().equalsIgnoreCase(manufacturer))
            {
               if(dronesList.get(i) instanceof PriorityDrone && type.equalsIgnoreCase("Priority"))
               {
                    searchList.set(i, dronesList.get(i));
               }
               if(dronesList.get(i) instanceof StandardDrone && type.equalsIgnoreCase("Standard"))
               {
                    searchList.set(i, dronesList.get(i));
               }
            }
        }
    }

    public void generateReportSortedByManufacturingYear(ArrayList<Drone> dronesList) 
    {

        ArrayList<Drone> sortedByYear = new ArrayList<>(dronesList);

        for (int i = 0; i < sortedByYear.size() - 1; i++)
        {
            int min = i;

            for (int j = i + 1; j < sortedByYear.size(); j++)
            {
                if (sortedByYear.get(j).getManufacturerYear() < sortedByYear.get(min).getManufacturerYear())
                {
                    min = j;
                }
            }

            // Use <arraylist>.set(i, temp)
            if (min != i)
            {
                Drone temp = sortedByYear.get(i);
                sortedByYear.set(i, sortedByYear.get(min));
                sortedByYear.set(min, temp);
            }
        }

    }

    public void generateReportSortedByPayloadCapacity(ArrayList<Drone> dronesList) 
    {
        /**
         * TODO:
         * 1. Sort list into new Array by Payload Capacity (use Clone or something)
         * 2. Do a simple comparison sort (i.e. Selection sort)
         * 3. Sort by payloadKG and sort into arraylist of drones
         * 4. Print the output of the list (either during runtime or after the sort)
         */

        ArrayList<Drone> sortedPayloadKG = new ArrayList<>(dronesList);

        for (int i = 0; i < sortedPayloadKG.size() - 1; i++)
        {
            int min = i;
            
            for (int j = i + 1; j < sortedPayloadKG.size(); j++)
            {
                if (sortedPayloadKG.get(j).getPayloadKg() < sortedPayloadKG.get(min).getPayloadKg())
                {
                    min = j;
                }
            }

            // Use <arraylist>.set(i, temp)
            if (min != i)
            {
                Drone temp = sortedPayloadKG.get(i);
                sortedPayloadKG.set(i, sortedPayloadKG.get(min));
                sortedPayloadKG.set(min, temp);
            }
        }

    }

    public void displayHangarInventory() 
    {
        // Display formatted printout of inventory
        for (Drone currentDrone : dronesList) 
        {
            System.out.println(currentDrone);
        }
    }

    public void showMenu(Hangar hangar)
    {
        Scanner input = new Scanner(System.in);
        boolean runtime = true;

        String menu[] = {
            "1. Load Drones from CSV",
            "2. Display Hangar Inventory",
            "3. Search Drones (Manufacturer & Type)",
            "4. View Inventory Sorted by Payload (Manual Sort)",
            "5. View Inventory Sorted by Year (Manual Sort)",
            "6. Count Drones by Manufacturer",
            "7. Exit"
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
                        displayHangarInventory();
                        break;
                    case 3:
                        // Search Drones (Manufacturer & Type)
                        System.out.println("Type the Manufacturer: ");
                        String manufacturer = input.next();
                        input.nextLine();

                        System.out.println("Type S for Standard and P for Priority: ");
                        String droneType = input.next();
                        input.nextLine();
                        if (droneType.equalsIgnoreCase("S") || droneType.equalsIgnoreCase("P")) 
                        {
                            searchDronesByManufacturerAndType(dronesList, manufacturer, droneType);
                        } 
                        else 
                        {
                            System.out.println("Error. Invalid character. Expected S or P. Got: " + droneType);
                        }
                        
                        break;
                    case 4:
                        // View Inventory by Payload
                        displayHangarInventory();
                        break;
                    case 5:
                        // View Inventory by Year
                        generateReportSortedByManufacturingYear(dronesList);
                        break;
                    case 6:
                        // Count drone by manufacturer
                        System.out.println("Type the Manufacturer: ");
                        manufacturer = input.next();
                        input.nextLine();

                        int count = getCountByManufacturer(manufacturer);
                        System.out.println("Drones by " + manufacturer + " " + count);

                        break;
                    case 7:
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
     * 
     * @param line
     * @param LineNumber
     * @return
     */
    private static Drone parseDroneLine(String line, int lineNumber) 
    {

        if (line.trim().isEmpty() || line == null) 
        {
            return null;
        }

        String[] fields = line.split(","); // CSV -> Comma Seperated Values

        // Utilize array of our four inputs that we need: If P or S, Name, Year, Weight
        // Limit and store them into variables
        String type = fields[0].trim();
        String name = fields[1].trim();
        String year = fields[2].trim();
        String payload = fields[3].trim();

        // Fields Check
        if (fields.length != 4) 
        {
            System.out.println("Error. Invalid number of fields at line " + lineNumber + ".\nExpected 4, recieved "+ fields.length);
            return null;
        }
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
            System.out.println("Error. Line at " + lineNumber + " has invalid year and or weight capacity(Is it a number?).");
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

    public Drone(String type, String manufacturerName, int manufacturerYear, double payloadKg) 
    {
        this.manufacturerName = manufacturerName;
        this.manufacturerYear = manufacturerYear;
        this.payloadKg = payloadKg;
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

    @Override
    public String toString() 
    {
        String toString = "Manufacturer: " + this.manufacturerName + " Manufactured: " + this.manufacturerYear + " Weight Limit (KG): " + this.payloadKg;
        return toString;
    }
}

/**
 * Priority Drone
 * extends Drone
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
        return super.getType() + " " + super.toString();
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
        return super.getType() + " " + super.toString();
    }

}