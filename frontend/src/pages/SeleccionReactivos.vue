<template>
  <q-page>
    <div class="q-pa-md row">
      <q-space />
    <q-btn icon="add" color="primary" @click="guardarReactivos()"> Guardar</q-btn>
    </div>
    <div class="q-pa-md">
    <q-table title="Reactivos" :rows="reactivos" :columns="columnasReactivos" row-key="idReactivo" dense
    :pagination.sync="paginacion" :rows-per-page-options="[0]" hide-bottom
      selection="multiple"       v-model:selected="seleccionados">
      </q-table>
      <div>
<div q-pa-md q-gutter-sm>
  <q-dialog v-model="confirm" persistent>
      <q-card>
        <q-card-section class="row items-center">
          <q-avatar icon="delete" color="primary" text-color="white" />
          <span class="q-ml-sm">¿Estás seguro de borrar este reactivo?</span>
        </q-card-section>

        <q-card-actions align="right">
          <q-btn flat label="Borrar" color="primary" @click="eliminarConfirmado()" />
          <q-btn flat label="Cancelar" v-close-popup />
        </q-card-actions>
      </q-card>
    </q-dialog>
</div>
</div>
      </div>
  </q-page>
</template>

<script>
import { defineComponent } from 'vue'
import { ref } from 'vue'

export default defineComponent({
  name: 'ReactivosPage',
  data: function() {
    return {
      idExamen: this.$route.params.idex,
      editando: false,
      reactivos: [],
      seleccion: [],
      seleccionados: [],
      expSeleccionada: {id:1,label:"Desarrollo de Software"},
      exps: [{id:1,label:"Desarrollo de Software"}],
      confirm: false,
      columnasReactivos: [
        {
          name: "texto",
          label: "Pregunta",
          field: "texto",
          align: "left"
        }
      ]
    };
  },
mounted: function() {
    this.cargarReactivos();
},
methods: {
    cargarReactivos: function() {
        console.log("cargarReactivos()");
        const urlReactivos = "reactivo";
        const urlSeleccion = "examen-reactivos?idExamen=" + this.idExamen;
        Promise.all([this.$api.get(urlReactivos), this.$api.get(urlSeleccion)])
          .then(([{data:datareacs},{data:datasel}]) => {
            this.reactivos = datareacs;
            this.seleccion = datasel;
            console.log(this.seleccion);
            this.seleccion.idsReactivos.forEach(id => {
              this.seleccionados.push({idReactivo:id});
            });
          });
    },
    guardarReactivos: function() {
      console.log("guardarReactivos()");
      let idsReactivos = [];
      this.seleccionados.forEach(r => { idsReactivos.push(r.idReactivo);});
      this.$api.post("examen-reactivos", {"idExamen":this.idExamen, "idsReactivos": idsReactivos})
        .then(respuesta => {
          console.log("POST examen-reactivos exitoso")
          this.$router.push("/examenes")
        })
        .catch();
    }
}

})
</script>
