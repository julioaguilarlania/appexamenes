<template>
    <div class="q-pa-md row">
      <q-space />
    <q-btn icon="add" color="primary" @click="agregarExamen()"> Agregar</q-btn>
    </div>
    <div class="q-pa-md">
    <q-table title="Exámenes" :rows="examenes" :columns="columnasExamenes"
     row-key="idExamen" dense
     :pagination.sync="paginacion" :rows-per-page-options="[0]" hide-bottom>
        <template v-slot:body-cell-acciones="props">
          <q-td :props="props">
          <div v-if="!props.row.cerrado">
            <q-btn dense round flat color="grey" :to="{path:'seleccion_reactivos/'+props.row.idExamen}" icon="library_add_check" title="Seleccionar reactivos"></q-btn>
            <q-btn dense round flat color="grey" @click="editarExamen(props)" icon="edit" title="Editar"></q-btn>
            <q-btn dense round flat color="grey" @click="eliminarExamen(props)" icon="delete" title="Eliminar"></q-btn>
            </div>
            <div v-else>
            <q-btn dense round flat color="grey" :to="{path:'calificaciones_examen/'+props.row.idExamen}" icon="pageview" title="Ver calificaciones"></q-btn>
            </div>
          </q-td>
        </template>
      </q-table>
      </div>
<q-dialog v-model="editando" persistent padding>
<q-card>
    <div class="q-pa-md">
    <q-form style="min-width: 480px">
    <q-select filled v-model="expSeleccionada" :options="exps" label="Experiencia Educativa" dense />
    <q-input
        filled
        v-model="nuevoExamen.nombre"
        label="Nivel *" dense
        :rules="[ val => val && val.length > 0 || 'Captura un nivel']"></q-input>
    <q-input
        filled
        label="Abre en *" dense
        v-model="nuevoExamen.momentoApertura">
        <template v-slot:prepend>
        <q-icon name="event" class="cursor-pointer">
          <q-popup-proxy cover transition-show="scale" transition-hide="scale">
            <q-date v-model="nuevoExamen.momentoApertura" mask="YYYY-MM-DD HH:mm">
              <div class="row items-center justify-end">
                <q-btn v-close-popup label="Close" color="primary" flat />
              </div>
            </q-date>
          </q-popup-proxy>
        </q-icon>
      </template>

      <template v-slot:append>
        <q-icon name="access_time" class="cursor-pointer">
          <q-popup-proxy cover transition-show="scale" transition-hide="scale">
            <q-time v-model="nuevoExamen.momentoApertura" mask="YYYY-MM-DD HH:mm" format24h>
              <div class="row items-center justify-end">
                <q-btn v-close-popup label="Close" color="primary" flat />
              </div>
            </q-time>
          </q-popup-proxy>
        </q-icon>
      </template></q-input>
    <q-input
        filled
        label="Cierra en *" dense
        v-model="nuevoExamen.momentoCierre">
        <template v-slot:prepend>
        <q-icon name="event" class="cursor-pointer">
          <q-popup-proxy cover transition-show="scale" transition-hide="scale">
            <q-date v-model="nuevoExamen.momentoCierre" mask="YYYY-MM-DD HH:mm">
              <div class="row items-center justify-end">
                <q-btn v-close-popup label="Close" color="primary" flat />
              </div>
            </q-date>
          </q-popup-proxy>
        </q-icon>
      </template>

      <template v-slot:append>
        <q-icon name="access_time" class="cursor-pointer">
          <q-popup-proxy cover transition-show="scale" transition-hide="scale">
            <q-time v-model="nuevoExamen.momentoCierre" mask="YYYY-MM-DD HH:mm" format24h>
              <div class="row items-center justify-end">
                <q-btn v-close-popup label="Close" color="primary" flat />
              </div>
            </q-time>
          </q-popup-proxy>
        </q-icon>
      </template></q-input>
      <div class="row">
    <q-btn label="Guardar" type="submit" color="primary" @click="guardarExamen()"/>
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
          <span class="q-ml-sm">¿Estás seguro de borrar este examen?</span>
        </q-card-section>

        <q-card-actions align="right">
          <q-btn flat label="Borrar" color="primary" @click="eliminarConfirmado()" />
          <q-btn flat label="Cancelar" v-close-popup />
        </q-card-actions>
      </q-card>
    </q-dialog>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'ExamenesPage',
  data: function() {
    return {
       examenes: [],
       editando: false,
        expSeleccionada: {id:1,label:"Desarrollo de Software"},
        exps: [{id:1,label:"Desarrollo de Software"}],
        nuevoExamen: { },
        confirm: false,
        columnasExamenes: [
        {
          name: "nombre",
          label: "Nivel",
          field: "nombre",
          align: "left"
        },
        {
          name: "apertura",
          label: "Abre en",
          field: "momentoApertura",
          align: "left"
        },
        {
          name: "cierra",
          label: "Cierra en",
          field: "momentoCierre",
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
        this.$api.get("examen")
            .then(respuesta => {
                console.log("GET ejecutado");
                this.examenes = respuesta.data;
            })
            .catch(err => {console.log("ERROR"); console.log(err);});
        console.log("EXAMENES:" + this.examenes);
    },
    agregarExamen: function() {
        console.log("agregarExamen()");
        this.editando = true;
    },
    guardarExamen: function() {
        console.log("guardarExamen()");
        this.nuevoExamen.idExperienciaEducativa =
        { idExperienciaEducativa: this.expSeleccionada.id };
        this.nuevoExamen.momentoApertura = this.nuevoExamen.momentoApertura.replace(" ","T");
        this.nuevoExamen.momentoCierre = this.nuevoExamen.momentoCierre.replace(" ","T");
        if (this.nuevoExamen.idExamen) {
            this.$api.put("examen/"+this.nuevoExamen.idExamen, this.nuevoExamen)
            .then(respuesta => {
                console.log("PUT ejecutado");
                this.editando = false;
                this.nuevoExamen = {};
                this.cargarExamenes();
            })
            .catch(err => {console.log("ERROR actualizando examen"); console.log(err);});
        }
        else {
        this.$api.post("examen", this.nuevoExamen)
            .then(respuesta => {
                console.log("POST ejecutado");
                this.editando = false;
                this.nuevoExamen = {};
                this.cargarExamenes();
            })
            .catch(err => {console.log("ERROR guardando examen"); console.log(err);});
        }
    },
    editarExamen: function(ex) {
        console.log("editarExamen()");
        this.nuevoExamen = ex.row;
        this.editando = true;
    },
    eliminarExamen: function(ex) {
        console.log("elimarExamen()");
        this.examenPorEliminar = ex.row;
        this.confirm = true;
    },
    eliminarConfirmado: function() {
        this.$api.delete("examen/"+this.examenPorEliminar.idExamen)
            .then(respuesta => {
                console.log("DELETE ejecutado");
                this.confirm = false;
                this.cargarExamenes();
            })
            .catch(err => {console.log("ERROR borrando examen"); console.log(err);});
    }
  }
})
</script>
