package com.example.application.views.inicio;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import java.util.ArrayList;
import java.util.List;

/**
 * Vista para la gestión de cursos en la aplicación.
 * <p>
 * Esta clase representa una vista en Vaadin que permite a los usuarios:
 * <ul>
 *     <li>Agregar nuevos cursos introduciendo el nombre y el código</li>
 *     <li>Visualizar una lista de cursos agregados en una tabla</li>
 * </ul>
 * 
 * Los datos de los cursos se almacenan en una lista en memoria (no persistente).
 * 
 * <p><strong>Ruta de la vista:</strong> {@code /gestion-cursos}</p>
 * <p><strong>Ícono:</strong> 📚 (Archivo - {@link LineAwesomeIconUrl#FILE})</p>
 * <p><strong>Orden en el menú:</strong> 2</p>
 *
 * @author
 */
@PageTitle("Gestión de Cursos")
@Route("gestion-cursos")
@Menu(order = 2, icon = LineAwesomeIconUrl.FILE)
public class GestionCursosView extends VerticalLayout {

    /** Lista en memoria que almacena los cursos ingresados */
    private List<Curso> cursos = new ArrayList<>();

    /** Tabla que muestra los cursos */
    private Grid<Curso> grid = new Grid<>(Curso.class);

    /**
     * Constructor principal de la vista.
     * <p>
     * Inicializa los componentes de la interfaz gráfica incluyendo:
     * un formulario para ingresar cursos y una tabla para mostrarlos.
     * </p>
     */
    public GestionCursosView() {
        // Clase CSS personalizada
        addClassName("gestion-cursos-view");

        // Título principal
        H2 title = new H2("Gestión de Cursos");
        title.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);

        // Campos de entrada
        TextField nombreCursoField = new TextField("Nombre del Curso");
        TextField codigoCursoField = new TextField("Código del Curso");

        // Botón para agregar curso
        Button agregarButton = new Button("Agregar Curso", e -> {
            String nombre = nombreCursoField.getValue();
            String codigo = codigoCursoField.getValue();

            if (!nombre.isEmpty() && !codigo.isEmpty()) {
                Curso nuevoCurso = new Curso(nombre, codigo);
                cursos.add(nuevoCurso);
                grid.setItems(cursos); // Refrescar la tabla con la nueva lista
                nombreCursoField.clear();
                codigoCursoField.clear();
            }
        });

        // Layout horizontal para el formulario de entrada
        HorizontalLayout formLayout = new HorizontalLayout(
            nombreCursoField,
            codigoCursoField,
            agregarButton
        );

        // Configuración de la tabla
        grid.setColumns("nombre", "codigo");
        grid.setItems(cursos);

        // Agrega todos los componentes al layout principal
        add(title, formLayout, grid);
    }

    /**
     * Clase interna que representa un curso.
     * <p>
     * Cada curso contiene dos atributos:
     * <ul>
     *     <li><strong>nombre:</strong> nombre del curso</li>
     *     <li><strong>codigo:</strong> código identificador del curso</li>
     * </ul>
     * </p>
     */
    public static class Curso {

        /** Nombre del curso */
        private String nombre;

        /** Código identificador del curso */
        private String codigo;

        /**
         * Constructor del curso.
         *
         * @param nombre nombre del curso
         * @param codigo código del curso
         */
        public Curso(String nombre, String codigo) {
            this.nombre = nombre;
            this.codigo = codigo;
        }

        /**
         * Obtiene el nombre del curso.
         *
         * @return el nombre del curso
         */
        public String getNombre() {
            return nombre;
        }

        /**
         * Obtiene el código del curso.
         *
         * @return el código del curso
         */
        public String getCodigo() {
            return codigo;
        }
    }
}