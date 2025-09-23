// Paquete donde se encuentra la vista
package com.example.application.views.inicio;

// Librería de íconos
import org.vaadin.lineawesome.LineAwesomeIconUrl;

// Componentes Vaadin necesarios
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import java.util.ArrayList;
import java.util.List;

// Define la vista con título, ruta y posición en el menú
@PageTitle("Gestión de Estudiantes")
@Route("gestion-estudiantes")
@Menu(order = 4, icon = LineAwesomeIconUrl.USER) // Ícono de usuario en el menú
public class GestionEstudiantesView extends VerticalLayout {

    // Lista en memoria que almacena los estudiantes registrados
    private List<Estudiante> estudiantes = new ArrayList<>();

    // Grid para mostrar los datos de los estudiantes
    private Grid<Estudiante> grid = new Grid<>(Estudiante.class, false);

    // Constructor de la vista
    public GestionEstudiantesView() {
        addClassName("gestion-estudiantes-view");

        // Título principal
        H2 title = new H2("Gestión de Estudiantes");
        title.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);

        // Campos del formulario
        TextField nombreField = new TextField("Nombre del estudiante");
        TextField codigoField = new TextField("Código del estudiante");
        TextField cursoField = new TextField("Curso");

        // Botón para registrar estudiantes
        Button registrarButton = new Button("Registrar Estudiante");

        // Texto para mensajes informativos o de error
        Paragraph info = new Paragraph("");

        // Acción al hacer clic en el botón
        registrarButton.addClickListener(e -> {
            String nombre = nombreField.getValue();
            String codigo = codigoField.getValue();
            String curso = cursoField.getValue();

            // Validación de campos vacíos
            if (nombre.isEmpty() || codigo.isEmpty() || curso.isEmpty()) {
                info.setText("⚠️ Por favor complete todos los campos.");
                return;
            }

            // Crear un nuevo estudiante y agregarlo a la lista
            Estudiante nuevo = new Estudiante(nombre, codigo, curso);
            estudiantes.add(nuevo);

            // Actualizar la tabla con los nuevos datos
            grid.getDataProvider().refreshAll();

            // Mostrar mensaje de éxito
            info.setText("✅ Estudiante registrado: " + nombre);

            // Limpiar los campos
            nombreField.clear();
            codigoField.clear();
            cursoField.clear();
        });

        // Configurar columnas del grid (tabla)
        grid.addColumn(Estudiante::getNombre).setHeader("Nombre");
        grid.addColumn(Estudiante::getCodigo).setHeader("Código");
        grid.addColumn(Estudiante::getCurso).setHeader("Curso");

        // Establecer los datos a mostrar en el grid
        grid.setItems(estudiantes);

        // Layout para alinear campos horizontalmente
        HorizontalLayout formLayout = new HorizontalLayout(nombreField, codigoField, cursoField, registrarButton);
        formLayout.setSpacing(true);

        // Agregar todos los componentes a la vista
        add(title, formLayout, info, grid);
    }

    // Clase interna que representa un estudiante
    public static class Estudiante {
        private String nombre;
        private String codigo;
        private String curso;

        // Constructor
        public Estudiante(String nombre, String codigo, String curso) {
            this.nombre = nombre;
            this.codigo = codigo;
            this.curso = curso;
        }

        // Métodos getter (necesarios para el grid)
        public String getNombre() {
            return nombre;
        }

        public String getCodigo() {
            return codigo;
        }

        public String getCurso() {
            return curso;
        }
    }
}
