public class Main {
    public static void main(String[] args) {
		CuentaCorriente c;
        c=new CuentaCorriente("12345678-A","Pepe");
        c.ingreso(1000);
        c.reintegro(250);
        c.mostrarInformacion();
        System.out.println("Puedo sacar 500 euros: "+c.reintegro(700));
        System.out.println("Puedo sacar 300 euros: "+c.reintegro(300));

	}
}
