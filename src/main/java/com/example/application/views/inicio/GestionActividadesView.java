package com.example.application.views.inicio;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
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

@PageTitle("Gestión de Actividades") // Título de la vista (aparece en la pestaña del navegador)
@Route("gestion-actividades") // Ruta URL de la vista
@Menu(order = 3, icon = LineAwesomeIconUrl.FILE) // Orden y ícono en el menú lateral
public class GestionActividadesView extends VerticalLayout {

    // Lista temporal para almacenar las actividades registradas
    private List<Actividad> actividades = new ArrayList<>();

    // Tabla (Grid) para mostrar las actividades registradas
    private Grid<Actividad> grid = new Grid<>(Actividad.class, false);

    public GestionActividadesView() {
        addClassName("gestion-actividades-view");

        // Título principal de la vista
        H2 title = new H2("Gestión de Actividades para Estudiantes");
        title.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);

        // Campos del formulario
        TextField estudianteField = new TextField("Nombre del Estudiante");
        TextField actividadField = new TextField("Nombre de la Actividad");

        // ComboBox para seleccionar el tipo de actividad
        ComboBox<String> tipoCombo = new ComboBox<>("Tipo de Actividad");
        tipoCombo.setItems("Tarea", "Examen", "Taller", "Proyecto");

        // Botón para registrar la actividad
        Button registrarButton = new Button("Registrar Actividad");

        // Agrupación horizontal de campos y botón
        HorizontalLayout inputLayout = new HorizontalLayout(estudianteField, actividadField, tipoCombo, registrarButton);
        inputLayout.setSpacing(true);

        // Configuración de columnas de la tabla
        grid.addColumn(Actividad::getEstudiante).setHeader("Estudiante");
        grid.addColumn(Actividad::getNombreActividad).setHeader("Actividad");
        grid.addColumn(Actividad::getTipo).setHeader("Tipo");

        // Elemento para mostrar mensajes de validación o éxito
        Paragraph mensaje = new Paragraph();

        // Acción al hacer clic en "Registrar Actividad"
        registrarButton.addClickListener(e -> {
            String estudiante = estudianteField.getValue();
            String actividad = actividadField.getValue();
            String tipo = tipoCombo.getValue();

            // Validación de campos
            if (estudiante.isEmpty() || actividad.isEmpty() || tipo == null) {
                mensaje.setText("⚠️ Todos los campos son obligatorios.");
                return;
            }

            // Crear nueva actividad y agregarla a la lista
            Actividad nuevaActividad = new Actividad(estudiante, actividad, tipo);
            actividades.add(nuevaActividad);

            // Actualizar el grid con la nueva lista
            grid.setItems(actividades);

            // Mostrar mensaje de éxito
            mensaje.setText("✅ Actividad registrada correctamente.");

            // Limpiar los campos del formulario
            estudianteField.clear();
            actividadField.clear();
            tipoCombo.clear();
        });

        // Agregar todos los componentes a la vista principal
        add(title, inputLayout, mensaje, grid);
    }

    // Clase interna para representar una Actividad
    public static class Actividad {
        private String estudiante;
        private String nombreActividad;
        private String tipo;

        public Actividad(String estudiante, String nombreActividad, String tipo) {
            this.estudiante = estudiante;
            this.nombreActividad = nombreActividad;
            this.tipo = tipo;
        }

        public String getEstudiante() {
            return estudiante;
        }

        public String getNombreActividad() {
            return nombreActividad;
        }

        public String getTipo() {
            return tipo;
        }
    }
}
