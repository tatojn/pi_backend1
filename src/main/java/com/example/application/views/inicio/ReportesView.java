package com.example.application.views.inicio;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Reportes")
@Route("reportes")
@Menu(order = 3, icon = LineAwesomeIconUrl.FILE_ALT_SOLID) // aparece en el menú
public class ReportesView extends VerticalLayout {

    public ReportesView() {
        setSpacing(false);
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        // 🎨 Estilo básico igual que en Inicio
        getStyle().set("background-color", "#b1cddeff");
        getStyle().set("text-align", "center");

        // Título
        H2 titulo = new H2("📊 Reportes Simples de Notas");
        add(titulo);

        // Crear una lista de datos ficticios (ejemplo)
        List<ReporteNotas> datos = new ArrayList<>();
        datos.add(new ReporteNotas("Juan Pérez", "Matemáticas", 3.5));
        datos.add(new ReporteNotas("María López", "Inglés", 4.2));
        datos.add(new ReporteNotas("Carlos Díaz", "Historia", 2.8));
        datos.add(new ReporteNotas("Ana Torres", "Ciencias", 3.9));

        // Grid para mostrar los reportes
        Grid<ReporteNotas> grid = new Grid<>(ReporteNotas.class, false);
        grid.addColumn(ReporteNotas::getEstudiante).setHeader("Estudiante");
        grid.addColumn(ReporteNotas::getMateria).setHeader("Materia");
        grid.addColumn(ReporteNotas::getNota).setHeader("Nota");

        grid.setItems(datos);
        grid.setWidth("80%");
        add(grid);

        // Botón de acción (ejemplo: generar PDF, en este caso solo un mensaje)
        Button exportar = new Button("Generar Reporte PDF");
        exportar.addClickListener(e -> {
            exportar.setText("📑 Reporte generado (ejemplo)");
        });

        add(exportar);
    }

    // Clase auxiliar para los datos
    public static class ReporteNotas {
        private String estudiante;
        private String materia;
        private double nota;

        public ReporteNotas(String estudiante, String materia, double nota) {
            this.estudiante = estudiante;
            this.materia = materia;
            this.nota = nota;
        }

        public String getEstudiante() {
            return estudiante;
        }

        public String getMateria() {
            return materia;
        }

        public double getNota() {
            return nota;
        }
    }
}
