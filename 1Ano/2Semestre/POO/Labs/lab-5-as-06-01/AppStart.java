public class AppStart {

    public static void main(String[] args) {

        Transport t = new Transport();
        Transport gt = new GroundTransportation("FD-34-AS");
        Transport at = new AirTransportation("Tiger", 32);
        Transport at2 = new AirTransportation("Sasd", 12);
        Transport lorry = new Lorry("asadadsdsa", 2 , 3);
        Transport van = new Van("saddsad", 2);


        gt.setPrice(32100);
        at.setPrice(345000);


        ShippingCompany sc = new ShippingCompany("RELIABLE-MOVING");

        sc.add(gt);
        sc.add(at);
        sc.add(at2);
        sc.add(lorry);
        sc.add(van);



        sc.makeTransportation("T-002", "Origin", "Dest", 312.12);
        System.out.println(sc.toString());

        sc.finalizeTransportation("T-002");

        System.out.println(sc.toString());


    }
}
