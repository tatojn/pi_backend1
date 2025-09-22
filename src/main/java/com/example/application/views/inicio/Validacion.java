package com.example.application.views.inicio;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;    
@PageTitle("Validacion")
@Route("validacion")
@Menu(order = 2, icon = LineAwesomeIconUrl.FILE)    



public class Validacion extends CalculadoraNotas{

     private String nombre;

    public Validacion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    //public String resumenNotas() {
      //  return "Estudiante: " + nombre + " Promedio: " + calcularPromedio(double nota1, double nota2, double nota3);
    //}

    public double calcularPromedio(double nota1, double nota2, double nota3) {
    return (nota1 + nota2 + nota3) / 3;
}

public String resumenNotas(double nota1, double nota2, double nota3) {
    return "Estudiante: " + nombre + " Promedio: " + calcularPromedio(nota1, nota2, nota3);
}   

    public Validacion() {
       
    }

    
    public void Nota(double nota) {
        if (nota >= 0 && nota<= 5) {
            
        } else {
            System.out.println("Nota inválida. Debe estar entre 0 y 5.");
        }


    }




}
