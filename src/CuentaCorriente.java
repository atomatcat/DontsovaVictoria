public class CuentaCorriente {
    String dni;
    String nombre;
    double saldo;

    CuentaCorriente(String dni, String nombre){
        this.dni = dni;
        this.nombre = nombre;
        double saldo = 0;
    }

    boolean reintegro(double cant){
            boolean operacionPosible;
            if(saldo >= cant){
                saldo -= cant;
                operacionPosible = true;
            }else{
                System.out.println("No hay dinero suficiente.");
                operacionPosible = false;
            }
            return  operacionPosible;
    }

    void ingreso(double cant){
        saldo += cant;
    }

    void mostrarInformacion(){
        System.out.println("Nombre: "+nombre);
        System.out.println("DNI: "+dni);
        System.out.println("Saldo: "+saldo);
        System.out.println(". . . . . . . . . . . . . . . . .");
    }
}
