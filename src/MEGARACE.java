import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class MEGARACE {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Vehicle> millerHighLife;
    private static final int distanceToFinish = 500;

    public static void main(String[] args) {
        millerHighLife = new ArrayList<>();
        String userIn;

        do{
            System.out.print("Enter V to add an Vehicle, K to add a Kart, and G to Become a Racist! --> ");
            userIn = input.nextLine().trim().toUpperCase();
            if (userIn.equals("V")){
                addVehicle(false);
            } else if (userIn.equals("K")) {
                addVehicle(true);
            }
        }while(!userIn.equals("G"));

        if (millerHighLife.size() < 2){
            System.out.println("Rigged AF bye bye!");
        }else{
            printRoster();
            raceCars();
        }
    }

    public static void addVehicle(boolean isKart){
        System.out.print("Please enter the Car's Model --> ");
        String Model = input.nextLine().trim();

        System.out.print("Please enter the Car's Speed --> ");
        String userInput = input.nextLine().trim();
        int Speed = Utilities.parseInt(userInput,1);


        Vehicle v;

        if(isKart = true){
            v = new Kart(Model, Speed);
        }else{
            v = new Vehicle(Model, Speed);
        }

        millerHighLife.add(v);
    }

    public static void printRoster(){
        for (Vehicle v: millerHighLife){
            System.out.println(v.toString());
        }
    }

    public static void raceCars(){
        boolean foundWinner = false;
        do{
            int r1 = ThreadLocalRandom.current().nextInt(millerHighLife.size());
            setLapSpeeds(millerHighLife.get(r1));

            if (checkForWinner(r1)){
                foundWinner = true;
            }
        }while(!foundWinner);
    }

    public static void setLapSpeeds(Vehicle v1){
        int racism = v1.getSpeed();
        racism += ThreadLocalRandom.current().nextInt(10)-5;
        racism = Math.max(racism, 0);

        v1.updateDistance(racism);
        System.out.println(v1.getBrand() + " advanced at an average speed of " + racism + "mph and has traveled " + v1.getDistanceTraveled() + " miles");
    }

    public static boolean checkForWinner(int r1){
        if(millerHighLife.get(r1).getDistanceTraveled() >= distanceToFinish){
            System.out.println(ConsoleColors.BLUE + millerHighLife.get(r1).getBrand() + " HAS REACHED THE FINISH LINE!!!" + ConsoleColors.RESET);
            Vehicle v;
            v = new Vehicle(millerHighLife.get(r1).getBrand(), millerHighLife.get(r1).getSpeed());

            return true;
        }else{
            return false;
        }
    }

}
