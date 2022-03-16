package main;


import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// clase factura
class Factura{
    String descripcion;
    int importe;
    Long codigo;
    Integer cantidad;
    LocalDateTime fecha;

    Factura(String descripcion,int importe, Integer cantidad){
        this.descripcion=descripcion;
        this.importe=importe;
        this.cantidad = cantidad;
        Random aleatorio = new SecureRandom();
        this.codigo = aleatorio.nextLong();
        this.fecha = LocalDateTime.now();
    }

    int getImporte(){
        return importe;
    }

    public Long getCodigo() {
        return codigo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "descripcion='" + descripcion + '\'' +
                ", importe=" + importe +
                ", codigo=" + codigo +
                ", cantidad=" + cantidad +
                ", fecha=" + fecha +
                '}';
    }
}

public class JavaApplication3 {


    public static void main(String[] args) {
        // registros de la data
        Factura f=new Factura("ordenador",1000, 1);
        Factura f2=new Factura("movil",300, 1);
        Factura f3=new Factura("imporesora",200, 1);
        Factura f4=new Factura("imac",1500, 1);
        Factura f5=new Factura("Play Station",1300, 5);
        Factura f6=new Factura("Televisor",2500, 3);

        // generar una lista
        List<Factura> lista=new ArrayList<Factura>();

        // agregar los productos de la factura
        lista.add(f);
        lista.add(f2);
        lista.add(f3);
        lista.add(f4);
        lista.add(f5);
        lista.add(f6);

        Predicate<Factura> predicado= new Predicate<Factura>() {
            @Override
            public boolean test(Factura t) {
                return t.getImporte()>300;
            }
        };

        Factura facturaFiltro=lista.stream()
                .filter(predicado).findFirst().get();

        System.out.println("Imprimiendo la Factura Única..." + "\n");
        System.out.println("FACTURA UNICA : "+facturaFiltro.getImporte());


        /*
        // filtraje funcional con streams
        Factura facturaFiltro=lista.stream()
                .filter(elemento->elemento.getImporte()>300)
                .findFirst()
                .get();
        System.out.println(facturaFiltro.getImporte());
        */

        List<Factura> facturaFiltroCantidad = lista.stream()
                .filter(element -> element.getCantidad() >= 2)
                .collect(Collectors.toList());

        System.out.println("\n" + "Imprimiendo filtro con cantidades mayores o iguales a 2..." + "\n");
        facturaFiltroCantidad.forEach(System.out::println);

        List<Factura> facturasFiltroFechas = lista.stream()
                .filter(element -> element.getFecha().toString().contains("2022"))
                .collect(Collectors.toList());

        System.out.println("\n" + "Imprimiendo facturas del año 2022..." + "\n");
        facturasFiltroFechas.forEach(System.out::println);

        List<Factura> facturasFiltroCodigo = lista.stream()
                .filter(element -> element.getCodigo() == lista.get(0).getCodigo())
                .collect(Collectors.toList());


        System.out.println("\n" + "Imprimiendo facturas con código: " + lista.get(0).getCodigo() + "\n");
        facturasFiltroCodigo.forEach(System.out::println);
    }

}
