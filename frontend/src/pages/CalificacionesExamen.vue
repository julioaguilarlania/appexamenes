<template>
    <div class="q-pa-md">
    <div class="q-pa-md">
    <b>Examen: {{ examen.nombre }}</b><br/>
    Aplicado en: {{ examen.momentoApertura }}
    </div>
    <q-table title="Alumnos" :rows="alumnos" :columns="columnasAlumnos"
     row-key="idAlumno" dense
     :pagination.sync="paginacion" :rows-per-page-options="[0]" hide-bottom>
      </q-table>
      </div>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'ExamenesPage',
  data: function() {
    return {
        idExamen: this.$route.params.idex,
        alumnos: [],
        examen: {},
        expSeleccionada: {id:1,label:"Desarrollo de Software"},
        exps: [{id:1,label:"Desarrollo de Software"}],
        columnasAlumnos: [
        {
          name: "nombres",
          label: "Nombre(s)",
          field: "nombres",
          align: "left"
        },
        {
          name: "apellidos",
          label: "Apellidos",
          field: "apellidos",
          align: "left"
        },
        {
          name: "calificacion",
          label: "Calificación",
          field: "calificacion",
          align: "center"
        }
      ],
      paginacion: { page: 1, rowsPerPage: 0}
    }
  },
  mounted: function() {
    this.cargarAlumnos();
  },
  methods: {
    cargarAlumnos: function() {
        console.log("cargarAlumnos()");
        this.$api.get("examen/" + this.idExamen)
            .then(respuesta => {
                this.examen = respuesta.data;
            })
            .catch(err => {console.log("ERROR"); console.log(err);});
        this.$api.get("examen-alumnos/completo?idExamen=" + this.idExamen)
            .then(respuesta => {
                console.log("GET ejecutado");
                this.alumnos = respuesta.data
                this.alumnos.forEach(al => {if (!al.momentoFin){ al.calificacion = "NP"; }});
            })
            .catch(err => {console.log("ERROR"); console.log(err);});
    }
  }
})
</script>
