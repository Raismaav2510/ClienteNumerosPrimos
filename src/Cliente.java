import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
    public static void main(String[] args) {
        String ipLocal = "localhost";
        String ipServidor = "localhost";
        int puerto = 8080;

        try {
            System.setProperty("java.rmi.server.hostname", ipLocal);
            Registry registry = LocateRegistry.getRegistry(ipServidor, puerto);
            Controlador controlador = (Controlador) registry.lookup("NumerosPrimos");
            Interfaz interfaz = new Interfaz(controlador);
            interfaz.setVisible(true);
        } catch (RemoteException e) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        }
    }
}