<template>
  <q-page>
      <div class="q-pa-md row">
      <q-space />
    <q-btn icon="add" color="primary" @click="agregarReactivo()"> Agregar</q-btn>
</div>
    <div class="q-pa-md">
    <q-table title="Reactivos" :rows="reactivos" :columns="columnasReactivos" row-key="idReactivo" dense
 :pagination.sync="paginacion" :rows-per-page-options="[0]" hide-bottom>
        <template v-slot:body-cell-acciones="props">
          <q-td :props="props">
            <q-btn dense round flat color="grey" @click="editarReactivo(props)" icon="edit"></q-btn>
            <q-btn dense round flat color="grey" @click="eliminarReactivo(props)" icon="delete"></q-btn>
          </q-td>
        </template>
      </q-table>
      </div>
      <div>
<div q-pa-md q-gutter-sm>
<q-dialog v-model="editando" persistent padding>
<q-card>
    <div class="q-pa-md">
    <q-form style="min-width: 480px">
    <q-select filled v-model="expSeleccionada" :options="exps" label="Experiencia Educativa" dense />
    <q-input
        filled
        v-model="nuevoReactivo.texto"
        label="Pregunta *" dense
        :rules="[ val => val && val.length > 0 || 'Captura una pregunta']"></q-input>
    <q-input
        filled
        v-model="nuevoReactivo.opcion1"
        label="Opción 1 *" dense
        :rules="[ val => val && val.length > 0 || 'Captura una opción 1']"></q-input>
    <q-input
        filled
        v-model="nuevoReactivo.opcion2"
        label="Opción 2 *" dense
        :rules="[ val => val && val.length > 0 || 'Captura una opción 2']"></q-input>
    <q-input
        filled
        v-model="nuevoReactivo.opcion3"
        label="Opción 3 *" dense
        :rules="[ val => val && val.length > 0 || 'Captura una opción 3']"></q-input>
    <q-input
        filled dense
        v-model="nuevoReactivo.opcion4"
        label="Opción 4"
        ></q-input>
    <q-input
        filled
        v-model="nuevoReactivo.opcionCorrecta"
        label="Opción Correcta"
        :rules="[ val => val && val >= 1 && val <= 4 || 'Captura el número de la opción correcta']"></q-input>
        <div class="row">
    <q-btn label="Guardar" type="submit" color="primary" @click="guardarReactivo()"/>
    <q-space/>
    <q-btn label="Cancelar" color="secondary"  v-close-popup />
    </div>
</q-form>
</div>
</q-card>
</q-dialog>
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
  </q-page>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'ReactivosPage',
  data: function() {
    return {
      editando: false,
      reactivos: [],
      nuevoReactivo: {},
      reactivoPorEliminar: {},
      expSeleccionada: {id:1,label:"Desarrollo de Software"},
      exps: [{id:1,label:"Desarrollo de Software"}],
      confirm: false,
      columnasReactivos: [
        {
          name: "texto",
          label: "Pregunta",
          field: "texto",
          align: "left"
        },
        {
          name:"acciones",
          label:"Acciones",
          field:"",
          align:"center"
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
        this.$api.get("reactivo")
            .then(respuesta => {
                console.log("GET ejecutado");
                this.reactivos = respuesta.data;
            })
            .catch(err => {console.log("ERROR"); console.log(err);});
    },
    agregarReactivo: function() {
        console.log("agregarReactivo()");
        this.editando = true;
    },
    guardarReactivo: function() {
        console.log("guardarReactivo()");
        this.nuevoReactivo.idExperienciaEducativa = 
        { idExperienciaEducativa: this.expSeleccionada.id };
        if (this.nuevoReactivo.idReactivo) {
        this.$api.put("reactivo/"+this.nuevoReactivo.idReactivo, this.nuevoReactivo)
            .then(respuesta => {
                console.log("PUT ejecutado");
                this.editando = false;
                this.nuevoReactivo = {};
                this.cargarReactivos();
            })
            .catch(err => {console.log("ERROR actualizando reactivo"); console.log(err);});
        }
        else {
        this.$api.post("reactivo", this.nuevoReactivo)
            .then(respuesta => {
                console.log("POST ejecutado");
                this.editando = false;
                this.nuevoReactivo = {};
                this.cargarReactivos();
            })
            .catch(err => {console.log("ERROR guardando reactivo"); console.log(err);});
        }
    },
    editarReactivo: function(reactivo) {
        console.log("editarReactivo()");
        console.log(reactivo);
        this.nuevoReactivo = reactivo.row;
        this.editando = true;
    },
    eliminarReactivo: function(reactivo) {
        console.log("eliminarReactivo()");
        this.reactivoPorEliminar = reactivo.row;
        this.confirm = true;
    },
    eliminarConfirmado: function() {
        this.$api.delete("reactivo/"+this.reactivoPorEliminar.idReactivo)
            .then(respuesta => {
                console.log("DELETE ejecutado");
                this.confirm = false;
                this.cargarReactivos();
            })
            .catch(err => {console.log("ERROR borrando reactivo"); console.log(err);});
    }
}

})
</script>
