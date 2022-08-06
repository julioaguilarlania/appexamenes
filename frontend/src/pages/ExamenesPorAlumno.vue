<template>
    <div class="q-pa-md">
    <q-table title="Exámenes Presentados" :rows="examenes" :columns="columnasExamenes"
     row-key="idExamen" dense
     :pagination.sync="paginacion" :rows-per-page-options="[0]" hide-bottom>
      </q-table>
      <q-table title="Exámenes Disponibles" :rows="examenesDisponibles" :columns="columnasExamenesDisponibles"
     row-key="idExamen" dense
     :pagination.sync="paginacion" :rows-per-page-options="[0]" hide-bottom>
        <template v-slot:body-cell-acciones="props">
          <q-td :props="props">
            <q-btn dense round flat color="grey" :to="{path:'/aplicar_examen/'+props.row.idExamen + '/' + idAlumno}" icon="edit" title="Contestar"></q-btn>
          </q-td>
        </template>
      </q-table>
      </div>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'ExamenesPage',
  data: function() {
    return {
        idAlumno: this.$route.params.idal,
       examenes: [],
       examenesDisponibles: [],
       editando: false,
        expSeleccionada: {id:1,label:"Desarrollo de Software"},
        exps: [{id:1,label:"Desarrollo de Software"}],
        nuevoExamen: { },
        confirm: false,
        columnasExamenes: [
        {
          name: "examen.nombre",
          label: "Nivel",
          field: "nombre",
          align: "left"
        },
        {
          name: "calificacion",
          label: "Calificación",
          field: "calificacion",
          align: "center"
        }
      ],
        columnasExamenesDisponibles: [
        {
          name: "nombre",
          label: "Nivel",
          field: "nombre",
          align: "left"
        },
        {
          name:"acciones",
          label:"Acciones",
          field:"",
          align:"center"
        }
      ],
      paginacion: { page: 1, rowsPerPage: 0}
    }
  },
  mounted: function() {
    this.cargarExamenes();
  },
  methods: {
    cargarExamenes: function() {
        console.log("cargarExamenes()");
        this.$api.get("examen-alumnos?idAlumno=" + this.idAlumno)
            .then(respuesta => {
                console.log("GET ejecutado");
                this.examenes = respuesta.data;
                this.examenes.forEach(e => { e.nombre = e.examen.nombre; });
            })
            .catch(err => {console.log("ERROR"); console.log(err);});
        this.$api.get("examendisponibles?idAlumno=" + this.idAlumno)
            .then(respuesta => {
                this.examenesDisponibles = respuesta.data;
            })
            .catch(err => {console.log("ERROR"); console.log(err);});
    }
  }
})
</script>
