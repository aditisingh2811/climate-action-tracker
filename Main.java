import java.io.*;
import java.util.*;
public class Main
{
    public static void main(String args[])throws IOException
    {
        double electricity,gas,miles,publicTrans,waste,footprint;
        Scanner sc=new Scanner(System.in);
        
        System.out.println("Carbon Footprint Calculator");
        System.out.println("===========================");
        System.out.println("Please enter the following details:");
        
        System.out.print("Electricity usage in kWh:");
        electricity=sc.nextDouble();
        System.out.print("Gas usage in therms:");
        gas=sc.nextDouble();
        System.out.print("Miles driven per year:");
        miles=sc.nextDouble();
        System.out.print("Public transportation expenses per year:");
        publicTrans=sc.nextDouble();
        System.out.print("Total pounds of waste produced per week:");
        waste=sc.nextDouble();
        
        Main obj=new Main();
        footprint=obj.calculateElectricity(electricity)+obj.calculateGas(gas)+obj.calculateTransportation(miles,publicTrans)+obj.calculateWaste(waste);
        
        Reduction obj1=new Reduction();
        System.out.println("Your annual carbon footprint is "+footprint+" metric tons of CO2 equivalentQ!");
        double avgfootprint;
        avgfootprint=obj1.averageFootprint(footprint);
        System.out.println("Your average carbon footprint per day is "+avgfootprint+" metric tons of CO2 equivalent!");
        
        if(footprint>2.1)
        {
            double red=obj1.calculateReduction(footprint);
            System.out.println("Recommended carbon footprint reduction:"+red);
            String tip=obj1.getTips(red);
            System.out.println(tip);
        }
    }
    double calculateElectricity(double e)
    {
        double footprint;
        footprint=e*1.37;
        return footprint;
    }
    double calculateGas(double g)
    {
        double footprint;
        footprint=g*11.7;
        return footprint;
    }
    double calculateTransportation(double m,double p)
    {
        double footprint;
        footprint=(m*0.4)+(p*0.03);
        return footprint;
    }
    double calculateWaste(double w)
    {
        double footprint;
        footprint=w*0.35;
        return footprint;
    }
}
class Reduction extends Main
{
    double averageFootprint(double f)
    {
        double avg;
        avg=f/365;
        return avg;
    }
    double calculateReduction(double f)
    {
        double reduction,rfactor;
        rfactor=reductionFactor(f);
        reduction=(rfactor/f)*100;
        return reduction;
    }
    double reductionFactor(double r)
    {
        double factor;
        double limit=2.1;
        factor=r-limit;
        return factor;
    }
    String getTips(double reduc)
    {
        String tips="";
        if(reduc>=10.0)
        {
            tips+="- Reduce air travel by taking trains or buses instead.";
        }
        else if(reduc>=7.0 && reduc<10.0)
        {
            tips+="- Replace your car with an electric or hybrid vehicle, or do car pools.";
        }
        else if(reduc>=4.0 && reduc<7.0)
        {
            tips+="- Switch to LED light bulbs and turn off lights when not in use.";
        }
        else if(reduc>=2.0 && reduc<4.0)
        {
            tips+="- Install solar panels to power your home.";
        }
        else
        {
            tips+="Take shorter showers to reduce water shortage.";
        }
        return tips;
    }
}