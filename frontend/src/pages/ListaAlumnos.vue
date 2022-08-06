<template>
    <div class="q-pa-md">
    <q-table title="Alumnos" :rows="alumnos" :columns="columnasAlumnos"
     row-key="idAlumno" dense
     :pagination.sync="paginacion" :rows-per-page-options="[0]" hide-bottom>
    <template v-slot:body-cell-acciones="props">
          <q-td :props="props">
    <q-btn dense round flat color="grey" :to="{path:'/misexamenes/'+props.row.idAlumno}" icon="pageview" title="Navegar como"></q-btn>
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
        idExpEd: this.$route.params.idee,
        alumnos: [],
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
          name:"acciones",
          label:"Ver como",
          field:"",
          align:"center"
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
        this.$api.get("alumno/?idee=1")
            .then(respuesta => {
                console.log("GET ejecutado");
                this.alumnos = respuesta.data
            })
            .catch(err => {console.log("ERROR"); console.log(err);});
    }
  }
})
</script>
